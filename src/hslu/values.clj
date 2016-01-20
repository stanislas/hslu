(ns hslu.values
  (:import (java.util ArrayList)))

;; strings

;; numbers
;; expressions

;; symbols

(def a 1)

;; functions

(defn b [x]
  (+ x 3))

;; boolean true if


;; vectors
;; let

(defn c [v]
  (let [fo (get v 0)
        so (get v 1)]
    (+ fo so)))

(defn d [[fo so]]
  (+ fo so))

;; maps

;; keywords

(def stan {:first-name "stan" :age 39})

;; sets

;; ---

;; vector
;; ArrayList vs Vector

(def jl (ArrayList. [1 2 3]))

(def cv [1 2 3])

;; get conj assoc update

;; maps
;; get assoc dissoc update

;; sets
;; conj disj

;; ---

;; accounts example

(def accounts (atom {:sam 100 :stan 100 :max 100}))

(defn transfer [accounts [from to amount]]
  (let [cur-from (get accounts from)
        cur-to (get accounts to)]
    (assoc accounts
      from (- cur-from amount)
      to (+ cur-to amount))))

;; atom
;; swap!


