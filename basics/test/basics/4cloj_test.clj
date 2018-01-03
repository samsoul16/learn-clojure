(ns basics.4cloj_test
  (:require #_[clojure.test :refer :all]
            [basics.4cloj :refer :all]
            [expectations :as exlib]))

;; Prob 32
(exlib/expect '(1 1 2 2 3 3) (dup [1 2 3]))

;; Prob 33
(exlib/expect '(1 2 3) (my-range 1 4))
(exlib/expect '(-2 -1 0 1) (my-range -2 2))
