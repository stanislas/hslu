(ns hslu.quil
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(def frame-size 300)

(def max-radius 100)
(def min-radius 10)

(defn random [from to]
  (+ (Math/floor (* (Math/random) (- to from))) from))

(defn new-state []
  (let [center (/ frame-size 2)]
    {:x center
     :y center
     :radius min-radius
     :color [255 0 0]}))

(defn setup []
  (q/frame-rate 30)
  (new-state))

(defn increase-radius [old-radius]
  (min (inc old-radius) max-radius))

(defn decrease-radius [old-radius]
  (max (dec old-radius) min-radius))

(defn update-state [state]
  (update state
    :radius decrease-radius))

(defn decrease-radius-fn [state]
  (update state
          :radius increase-radius))

(defn random-colors [state]
  (assoc state :color
               [(random 0 256)
                (random 0 256)
                (random 0 256)]))

(defn move-circle [event]
  (fn [state]
    (assoc state :x (get event :x)
                 :y (get event :y))))

(defn mouse-moved [state event]
  ((comp random-colors
         decrease-radius-fn
         (move-circle event)) state))

(defn draw-state [state]
  (q/background 255 255 255)
  (apply q/stroke (get state :color))
  (q/ellipse
    (get state :x)
    (get state :y)
    (get state :radius)
    (get state :radius)))

(q/sketch
  :host "canvas"
  :size [frame-size frame-size]
  :setup setup
  :update #'update-state
  :draw #'draw-state
  :mouse-moved #'mouse-moved
  :middleware [m/fun-mode])
