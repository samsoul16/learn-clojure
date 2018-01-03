(ns basics.proj_euler
  (:gen-class)
  (:require [clojure.math.numeric-tower :as math]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; Prob 1 Sum of multiples of 3 & 5
(defn sumofmul [n]
  (reduce + (filter #(or (zero? (mod % 3)) (zero? (mod % 5))) (range 1 n))))

;; Prob 2 Even Fibonacci No
(defn fib [n]
  (if (zero? n)
    0
    (if (= 1 n)
      1
      (+ (fib (- n 1)) (fib (- n 2))))))

(defn sumfib [n]
  (reduce + (filter #(and (<= % 4000000) (even? %)) (map #(fib %) (range 2 (+ n 2))))))

;; Prob 3
(defn fact [n c faclis]
  (if (> c (/ n 2))
    faclis
    (if (zero?  (mod n c))
      (fact n (inc c)  (conj faclis c))
      (fact n (inc c) faclis)
      )))

(defn prime? [x c]
  (if (> c (math/sqrt x))
    true
    (if (zero? (mod x c))
      false
      (recur x (inc c)))))

(defn largeprime [n]
  (last (filter #(prime? % 2) (fact n 2 []))))

;; Prob 4 Largest palindrome product

(defn largepalin [x]
  x)

(defn palindrome? [n]
  (if (= (str n) (clojure.string/reverse (str n)))
    true
    false))

#_(defn lp
  (loop [x 999 y 999]
    (when (> y 100)
      ())
    )
    )

;; Prob 5
(defn even_div? [no]
  (= 20
     (count (take-while #(= 0(mod no %))
                        (range 1 21)))))

(defn smallest [x]
  (if (even_div? x)
    x
    (recur (inc x))))

;; Prob 6
(defn diff_sum_sqr []
  (let [arr (range 1 101)
        sumsqr (reduce + (map (fn [x] (* x x)) arr))
        sqrsum (* (reduce + arr) (reduce + arr))]
    (- sqrsum sumsqr)))

;; Prob 7
(defn nthprime [primecount primeno count]
  (if (= 10001 primecount)
    primeno
    (if (prime? count 2)
      (recur (inc primecount) count (inc count))
      (recur primecount primeno (inc count)))))

;; Prob 8
(def in 7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450)

(defn digits [number]
  (map #(Character/digit % 10) (str number)))

(defn mulall [arr]
  (apply * arr))

(defn highmul [ls prod]
  (if (< (count ls) 13)
    prod
    (let [curr  (mulall  (take 13 ls))]
      (if (> curr prod)
        (recur (rest ls) curr)
        (recur (rest ls) prod)
        ))))

;; Prob 9

;; Prob 10
(defn sumprime [primesum count]
  (if (= 2000000 count)
    primesum
    (if (prime? count 2)
      (recur (+ primesum count) (inc count))
      (recur primesum (inc count)))))


;; Prob 12
(defn trino [n]
  ((fn [acc x]
      (if (zero? x)
        acc
        (recur (+ acc x) (dec x))))
   0 n))
