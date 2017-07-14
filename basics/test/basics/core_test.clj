(ns basics.core-test
  (:require [clojure.test :refer :all]
            [basics.core :refer :all]
            [basics.my_functs :refer :all]
            [expectations :as exlib]))

#_(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

;; Sam concat
(exlib/expect [1 2 3] (sam-cat [1 2] [3]))
(exlib/expect [1 2 3] (sam-cat [] [1 2 3]))
(exlib/expect [7 8 9] (sam-cat [7 8 9] []))
(exlib/expect [] (sam-cat [] []))

;; Sam Map
(exlib/expect '(4 5 6) (sam-map inc [3 4 5]))
(exlib/expect '(1 2 3 4) (sam-map dec [2 3 4 5]))
(exlib/expect '(4 3 2 1) (sam-map #(/ % 4) [16 12 8 4]))

;; Sam Map with helper instead of concat
(exlib/expect '(4 3 2 1) (sam-mcc #(/ % 4) [16 12 8 4] []))
(exlib/expect '() (sam-mcc inc [] []))

;; Sam Reduce
(exlib/expect 10 (sam-red + '(1 2 3 4) 0))
(exlib/expect 11 (sam-red #(+  (* 2 %1) %2) [1 2 3] 0))

;; Sam Drop
(exlib/expect [2 3] (sam-drop 1 [1 2 3]))
(exlib/expect [1 2 3] (sam-drop -1 [1 2 3]))
(exlib/expect '() (sam-drop 5 [1 2 3]))

;; Sam take
(exlib/expect [1] (sam-take 1 [1 2 3]))
(exlib/expect [1 2 3] (sam-take 5 [1 2 3]))
(exlib/expect '() (sam-take -1 [1 2 3]))

;; MultiArity Func
(exlib/expect 4 (tp 3))
(exlib/expect 5 (tp 3 2))

;; Sam take-while
(exlib/expect '() (sam-take-while #(> % 5) [1 2 3 4 3 2 1]))
(exlib/expect '(6 7 8) (sam-take-while #(> % 5) [6 7 8 3 2 1]))
(exlib/expect '(1 2 3 4) (sam-take-while #(> 5 %) [1 2 3 4 5 6 7 8 9 10]))

;; Sam drop-while
(exlib/expect '() (sam-drop-while #(> 5 %) [1 2 3 4 3 2 1]))
(exlib/expect '(3 2 1) (sam-drop-while #(> % 5) [6 7 8 3 2 1]))
(exlib/expect '(5 6 7 8 9 10) (sam-drop-while #(> 5 %) [1 2 3 4 5 6 7 8 9 10]))

;; Sam filter
(exlib/expect '(2 3 4 3 2) (sam-filter #(not= 1 %) [1 2 3 4 3 2 1]))
(exlib/expect '(6 7 8) (sam-filter #(> % 5) [6 7 8 3 2 1]))
(exlib/expect '(2 4 6 8 10) (sam-filter even? [1 2 3 4 5 6 7 8 9 10]))

;; Sam some
(exlib/expect true (sam-some odd? [2 4 6 1]))
(exlib/expect nil (sam-some odd? [2 4 6 8]))
(exlib/expect nil (sam-some even? []))

;; Sam Remove first element
(exlib/expect '(1 3 4 3 2 1) (mod-remove #(= 2 %) [1 2 3 4 3 2 1]))
(exlib/expect '(6 7 8 3 2 1) (mod-remove #(= 9 %) [6 7 8 3 2 1]))
(exlib/expect '(3 3) (mod-remove #(= 3 %) [3 3 3]))

;; Sam Sort
(exlib/expect [1 2 2 3 4 5 5] (sam-sort [5 1 3 2 5 2 4]))
(exlib/expect [1 2 3 4] (sam-sort [4 3 2 1]))

;; Sam min helper
(exlib/expect 3 (sam-min-help [7 6 5 4 3] 7))
(exlib/expect 1 (sam-min-help [1 2 3 4] 1))

;; Sam min
(exlib/expect 1 (sam-min 4 3 2 1))
(exlib/expect 1 (sam-min 4 3 7 8 9 2 41 52 231 1))

;; Sam add-one
(exlib/expect [1 2 3] (add-one [1 2] [3]))
(exlib/expect [1 2 3 4 5 6] (add-one [] [1 2 3 4 5 6]))
(exlib/expect [1 2 3] (add-one [1 2 3] []))

;; Sam all concat helper
(exlib/expect [1 2 3 4 5 6] (all-help [] '([1 2] [3 4] [5 6])))
(exlib/expect [1 2 3 4 5 6] (all-help [] '([] [1 2 3 4] [5 6])))
(exlib/expect [1 2 3 4 5 6] (all-help [] '([1 2 3] [] [4 5 6])))

;; Sam concat n no of lists
(exlib/expect [1 2 3 4 5 6] (all-cat [1 2] [3 4] [5 6]))
(exlib/expect [1 2 3 4 5 6] (all-cat [] [1 2 3 4] [5 6]))
(exlib/expect [1 2 3 4 5 6] (all-cat [1 2 3] [] [4 5 6]))

;; Sam into
(exlib/expect [1 2 3] (sam-into [] '(1 2 3)))
(exlib/expect '(3 2 1) (sam-into '() [1 2 3]))
(exlib/expect #{1 2 3} (sam-into #{} [1 2 3]))

;; Sam partial
(exlib/expect 14 (add2 4 5))

;; Sam min helper
(exlib/expect ["a" 1] (cus-min-help {"a" 1, "ccc" 3, "d" 1, "bb" 2} ["a" 1]))

;; Sam min from hash map
(exlib/expect ["a" 1] (cus-min {"dd" 2, "bbb" 3, "cccc" 4, "a" 1}))

;; Sam pair creates hash map of key and value after applying funcs to each ele
(exlib/expect {"dd" 2, "bbb" 3, "a" 1, "cc" 2} (sam-pair count ["dd" "bbb" "a" "cc"] {}))

;; Sam unpair hash map
(exlib/expect ["a" "bb" "ccc" "dddd"] (sam-unpair '(["a" 1] ["bb" 2] ["ccc" 3] ["dddd" 4]) []))

;; Sam cus remove one from hash map
(exlib/expect '(["aa" 2] ["bbbb" 4] ["ddd" 3]) (cus-remove #(= "b" %) '(["aa" 2] ["bbbb" 4] ["ddd" 3] ["b" 1]) ))

;; Sam cus sort key value pair
(exlib/expect '(["a" 1] ["dd" 2] ["bbb" 3] ["cccc" 4] ) (cus-sort {"dd" 2, "bbb" 3, "cccc" 4, "a" 1}))

;; Sam sort by
(exlib/expect '("a" "c" "bb" "ddd") (sam-sortby count ["bb" "a" "ddd" "c"]))
(exlib/expect '("a" "bb" "cc" "ddd" "eee") (sam-sortby count ["bb" "a" "ddd" "eee" "cc"]))
