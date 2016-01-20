(ns hslu.quilsol
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(def frame-size 300)

(def max-radius 100)
(def min-radius 10)

(defn random [from to]
  (+ (Math/floor (* (Math/random) (- to from))) from))

(defn new-state []
  (let [center (/ frame-size 2)]
    {:x center :y center :radius min-radius :color [255 0 0]}))

(defn setup []
  (q/frame-rate 30)
  (q/background 255)
  (new-state))

(defn grow-radius [old-radius]
  (min (inc old-radius) max-radius))

(defn update-radius [f]
  (fn [state]
    (update-in state [:radius] f)))

(def grow
  (update-radius grow-radius))

(defn shrink-radius [old-radius]
  (max (dec old-radius) min-radius))

(def shrink
  (update-radius shrink-radius))

(def update-state
  grow)

(defn update-coord [event]
  (fn [state]
    (assoc state :x (get event :x)
                 :y (get event :y))))

(defn update-color [state]
  (assoc state :color [(random 0 256)
                       (random 0 256)
                       (random 0 256)]))

(defn mouse-moved [state event]
  ((comp
     update-color
     shrink
     (update-coord event))

    state))

(defn draw-state [{:keys [x y radius color]}]
  (q/background 255)
  (apply q/stroke color)
  (q/ellipse x y radius radius))

(q/sketch
  :host "canvas"
  :size [frame-size frame-size]
  :setup setup
  :update #'update-state
  :draw #'draw-state
  :mouse-moved #'mouse-moved
  :middleware [m/fun-mode])
