(ns basics.proj_eul_test
  (:require [basics.proj_euler :refer :all]
            [clojure.math.numeric-tower :as math]
            [expectations :as exlib]))

;; Prob 1
(exlib/expect 23 (sumofmul 10))
(exlib/expect 45 (sumofmul 15))

;; Prob 2
(exlib/expect 1 (fib 2))
(exlib/expect 2 (fib 3))
(exlib/expect 3524578 (fib 33))
(exlib/expect 4613732 (sumfib 33))

;; Prob 3
(exlib/expect '(2 3 5 6 10 15) (fact 30 2 []))
(exlib/expect true  (prime? 29 2))
(exlib/expect false  (prime? 30 2))
#_(exlib/expect 6857 (largeprime 600851475143))

;; Prob 4
(exlib/expect true (palindrome? 909))

;; Prob 5
(exlib/expect false (even_div? 2520))
(exlib/expect false (even_div? 5000))
(exlib/expect true (even_div? 2432902008176640000))

;; Prob 6
#_(exlib/expect 2640 (diff_sum_sqr)) ;; for 10
#_(exlib/expect 41230 (diff_sum_sqr)) ;; for 20
#_(exlib/expect 25164150 (diff_sum_sqr)) ;; for 100

;; Prob 7
(exlib/expect true (prime? 2 2))
#_(exlib/expect 104743 (nthprime 0 0 2))
#_(exlib/expect 29 (nthprime 0 0 2)) ;; for 10

;; Prob 10
#_(exlib/expect 17 (sumprime 0 2)) ;; 10
;;(exlib/expect 142913828922 (sumprime 0 2)) ;; 10001

;; Prob 8
(exlib/expect '(1 2 3 4 5 6) (digits 123456))
(exlib/expect 24 (mulall [1 2 3 4]))
#_(exlib/expect 5832 (highmul (digits in) 0)) ;;  for 4digit product

;; Prob 12
(exlib/expect 28 (trino 7))
(exlib/expect [1 3 7] (fact 21 1 []))
