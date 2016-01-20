(ns hslu.valuessol)

;; numbers
;; strings

;; symbols
;; functions
;; boolean true if

;; vectors
;; let
;; maps
;; keywords
;; sets

;; vector
;; ArrayList vs Vector
;; get conj assoc update

;; maps
;; get assoc dissoc update

;; sets
;; conj disj

;; accounts example

;; atom
;; swap!

(defn transfer [accounts [from to amount]]
  (let [from-current (get accounts from)
        to-current (get accounts to)]
    (assoc accounts
      from (- from-current amount)
      to (+ to-current amount))))
