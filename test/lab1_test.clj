(ns lab1-test
  (:require [clojure.test :refer [deftest is testing]]
            [lab1 :refer :all]))

(def input-1-problem-3 13195)
(def input-2-problem-3 600851475143)

(def input-1-problem-28 5)
(def input-2-problem-28 1001)

(def answer-1-problem-3 29)
(def answer-2-problem-3 6857)

(def answer-1-problem-28 101)
(def answer-2-problem-28 669171001)

(deftest problem-3-test
  (testing "Problem 3: Largest Prime Factor"
    (is (= answer-1-problem-3 (tail-rec-largest-prime-factor input-1-problem-3)))
    (is (= answer-1-problem-3 (loop-largest-prime-factor input-1-problem-3)))
    (is (= answer-1-problem-3 (modul-largest-prime-factor input-1-problem-3)))
    (is (= answer-1-problem-3 (lazy-and-map-largest-prime-factor input-1-problem-3)))

    (is (= answer-2-problem-3 (tail-rec-largest-prime-factor input-2-problem-3)))
    (is (= answer-2-problem-3 (loop-largest-prime-factor input-2-problem-3)))
    (is (= answer-2-problem-3 (modul-largest-prime-factor input-2-problem-3)))
    (is (= answer-2-problem-3 (lazy-and-map-largest-prime-factor input-2-problem-3)))))

(deftest problem-28-test
  (testing "Problem 28: Number Spiral Diagonals"
    (is (= answer-1-problem-28 (tail-rec-spiral-diagonals input-1-problem-28)))
    (is (= answer-1-problem-28 (rec-spiral-diagonals input-1-problem-28)))
    (is (= answer-1-problem-28 (lazy-and-modul-spiral-diagonals input-1-problem-28)))

    (is (= answer-2-problem-28 (tail-rec-spiral-diagonals input-2-problem-28)))
    (is (= answer-2-problem-28 (rec-spiral-diagonals input-2-problem-28)))
    (is (= answer-2-problem-28 (lazy-and-modul-spiral-diagonals input-2-problem-28)))))


