(ns lab1-test
  (:require [clojure.test :refer [deftest is testing]]
            [lab1 :refer :all]))

;; (def input-problem-3 600851475143)
;; (def input-problem-3 333333333)
(def input-problem-3 13195)
(def input-problem-28 101)

;; (def answer-problem-3 333667)
(def answer-problem-3 29)
(def answer-problem-28 692101)

(deftest problem-28-test
  (testing "Problem 28: Number Spiral Diagonals"
    (is (= answer-problem-28 (tail-rec-spiral-diagonals input-problem-28)))
    (is (= answer-problem-28 (rec-spiral-diagonals input-problem-28)))
    (is (= answer-problem-28 (lazy-and-modul-spiral-diagonals input-problem-28)))))


(deftest problem-3-test
  (testing "Problem 3: Largest Prime Factor"
    (is (= answer-problem-3 (tail-rec-largest-prime-factor input-problem-3)))
    (is (= answer-problem-3 (loop-largest-prime-factor input-problem-3)))
    (is (= answer-problem-3 (modul-largest-prime-factor input-problem-3)))
    (is (= answer-problem-3 (lazy-and-map-largest-prime-factor input-problem-3)))))

