;; Custom implementation of inbuilt clojure functions

;; 1. Custom map
(defn sam-map [f arg]
  (when-not (empty? arg)
    (sam-cat
     [(f (first arg))]
     (sam-map f (rest arg)))))

;; 2. Custom map with helper instead of concat
(defn sam-mcc [f arr acc]
  (if (empty? arr)
    acc
    #_(sam-mcc f (rest arr) (conj acc (f (first arr)))) ;; Sir ka method add to acc
    #_(conj  (sam-mcc f (rest arr) acc) (f (first  arr))) ;;Gives Reverse : conj adds @ last
    (cons (f (first arr)) (sam-mcc f (rest arr) acc) ) ;; cons use karke hogaya :P
    ))

;; 3. Custom reduce
(defn sam-red [f arr acc]
  (if (empty? arr)
    acc
    (sam-red f (rest arr) (f acc (first arr)))))

;; 4. Custom drop
(defn sam-drop [n arr]
  (if (zero? n)
    arr
    (sam-drop (dec n) (rest arr))))

;; 5. Custom take
(defn sam-take [n arr]
  (if (>= n (count arr))
    arr
    (if (zero? n)
      '()
      (conj (sam-take (dec n) (rest arr)) (first arr)))))

;; 6. MultiArity function
(defn tp
  ([a] (inc a))
  ([b c] (+ b c)))

;; 7. Custom take-while
(defn sam-take-while [f arr]
  (if (empty? arr)
    arr
    (if (f (first arr))
      (conj (sam-take-while f (rest arr)) (first arr)))))

;; 8. Custom drop-while
(defn sam-drop-while [f arr]
  (if (empty? arr)
    arr
    (if (f (first arr))
      (sam-drop-while f (rest arr))
      arr )))

;; 9. Custom filter
(defn sam-filter [f arr]
  (when-not (empty? arr)
    (if (f (first arr))
      (cons (first arr) (sam-filter f (rest arr)))
      (sam-filter f (rest arr)))))

;; 10. Custom some
(defn sam-some [f arr]
  (when-not (empty? arr)
    (if (f (first arr))
      true
      (sam-some f (rest arr)))))

;; 11. Custom concat for only 2 args
(defn sam-cat [ls1 ls2]
  (if (and (empty? ls1) (empty? ls2))
    '()
    (if (empty? ls2)
      (seq ls1)
      (sam-cat (conj (vec ls1) (first ls2)) (rest ls2)))))

;; 12. Custom Remove first element
(defn mod-remove [f arr]
  (letfn ([helper [f in out]
           (if (or (empty? in)
                   (f (first in)))
             (concat out (rest in))
             (recur f (rest in) (conj out (first in))))])
    (helper f arr [])))

;; 13. Custom Sort
(defn sam-sort [arr]
  (when-not (empty? arr)
    (let [min-arr (apply min arr)]
      (conj (sam-sort (mod-remove #(= min-arr %)
                                  arr))
            min-arr))))

;; 14. Custom min/max For max replace < with >
(defn sam-min-help [arr acc]
  (if (empty? arr)
    acc
    (recur (rest arr)
           (if (< acc (first arr))
             acc
             (first arr)))))


(defn sam-min [& args]
  (if (empty? (rest args))
    (first args)
    (sam-min-help (rest args) (first args))))

;; 15. Custom concat with any args
(defn add-one [acc ls]
  (if (empty? ls)
    acc
    (add-one (conj acc (first ls)) (rest ls))))

(defn all-help [acc arr]
  (if (empty? arr)
    acc
    (all-help (add-one acc (first arr))
              (rest arr))))

(defn all-cat [& args]
    (all-help [] args)
  #_(if (empty? (first (rest args)))
    (first args)
    (all-cat (add-one (first args) (second args)) (drop 2 args))))

;; 16. Custom into
(defn sam-into [out in]
  (apply conj out in))

;; 17. Custom complement
(defn sam-comp [f]
  (fn [& args]
    (not (apply f args))))

;; 18. Custom partial
(defn sam-par [f & arg]
  (fn [& args]
    (apply f (into arg args))))

(defn add [a b c d]
  (+ a b c d))

(def add2 (sam-par add 2 3))

;; 19. Custom sort-by
(defn cus-min-help [arr acc]
  (if (empty? arr)
    acc
    (recur (rest arr)
           (if (<= (second acc) (second (first arr)))
             acc
             (first arr)))))

(defn cus-min [args]
  (if (empty? (rest args))
    (first args)
    (cus-min-help (rest args) (first args))))

(defn sam-pair [f arr acc]
  (if (empty? arr)
    acc
    (sam-pair f
          (rest arr)
          (assoc acc (first arr) (f (first arr))))))

(defn sam-unpair [arr acc]
  (if (empty? arr)
    acc
    (sam-unpair (rest arr)
                (conj  acc (first (first arr))))))

(defn cus-remove [f arr]
  (letfn ([helper [f in out]
           (if (or (empty? in)
                   (f (first (first in))))
             (concat out (rest in))
             (recur f (rest in) (conj out (first in))))])
    (helper f arr [])))

(defn cus-sort [arr]
  (when-not (empty? arr)
    (let [min-arr (cus-min arr)]
      (conj (cus-sort (cus-remove #(= (first min-arr) %)
                                  arr))
            min-arr))))

(defn sam-sortby [f arr]
  (let [key-val-arr (sam-pair f arr {})
        sorted-pair (cus-sort key-val-arr)]
    (sam-unpair sorted-pair [])))
