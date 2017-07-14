4 Clojure Problems

# Prob 27
(
 (fn [arg]
  (if (=  (seq arg) (into '() arg))
      true
      false )
   ) 
 "mom")

# Prob 28
# Soln 1
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

# Soln 2
(defn b [x]
  (reduce (fn flat [acc item]
            (if (coll? item)
              (reduce flat acc item)
              (conj acc item)))
          '()
          x))

# Soln 3
(fn [l] (mapcat (fn flat [x] (if (coll? x) (mapcat flat x) [x])) l) )
