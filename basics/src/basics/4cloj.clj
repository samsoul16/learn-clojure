(ns basics.4cloj)

;;4 Clojure Problems

;; Prob 27
(
 (fn [arg]
  (if (=  (seq arg) (into '() arg))
      true
      false )
   )
 "mom")

;;  Prob 28
;; Soln 1
(defn f [arg1]
   (mapcat
    (fn myflat [x]
      (if (coll? x )
        (if (empty? x)
          x
          (concat (myflat (first x)) (myflat (rest x)))
          )
        [x])
      )
    arg1)
)

;; Soln 2
(defn b [x]
  (reduce (fn flat [acc item]
            (if (coll? item)
              (reduce flat acc item)
              (conj acc item)))
          '()
          x))

;; Soln 3
(fn [l] (mapcat (fn flat [x] (if (coll? x) (mapcat flat x) [x])) l) )

;; Prob 33

(defn repl_seq [ls n]
  (for [i ls
        j (range n)]
    i))

;; Prob 156
(defn map_defaults [no x]
  (into {} (for [i x]
             [i no])))

;; Prob 157
(defn index_seq [x]
  (map (fn [a b] [a b]) x (range (count x))))


;; Prob 32
;; http://www.4clojure.com/problem/32
(defn dup [x]
  (mapcat vector x x))


;; Prob 33
;; http://www.4clojure.com/problem/34
(defn my-range [start end]
  (take-while #(< % end) (iterate inc start)))
