(ns lab1)

;; problem 3 loop

(defn prime? [n]
  (let [sqrt (Math/sqrt n)]
    (loop [x 2]
      (if (<= x sqrt)
        (if (zero? (rem n x))
          false
          (recur (inc x)))
        true))))

(defn loop-largest-prime-factor [n]
  (loop [x 2]
    (if (zero? (rem n x))
      (if (prime? (/ n x))
        (/ n x)
        (recur (inc x)))
      (recur (inc x)))))

;; problem 3 tail recursion

(defn tail-rec-prime-factor [x n]
  (if (and (zero? (rem n x)) (prime? (quot n x)))
    (/ n x)
    (recur (inc x) n)))

(defn tail-rec-largest-prime-factor [n]
  (tail-rec-prime-factor 2 n))

;; problem 3 modul

(defn divisor? [n x]
  (zero? (rem n x)))

(defn modul-largest-prime-factor [n]
  (let [numbers (range 2 (/ n 2)),  ;generation
        prime-divisors (filter prime? (filter (fn [x] (divisor? n x)) numbers))]  ;filtration
    (last prime-divisors)))

;; проблема 3 lazy and map

(defn lazy-and-map-largest-prime-factor [n]
  (let [primes (filter some? (map #(if (prime? %) % nil) (take (- (quot n 2) 1) (iterate inc 2)))),
        prime-divisor (filter (fn [x] (divisor? n x)) primes)]
    (last prime-divisor)))

;; problem 28 tail recursion

(defn spirals [size sum add num c]
  (if (< num (* size size))
    (if (< c 4)
      (recur size (+ sum num) add (+ num add) (inc c))
      (recur size (+ sum num) (+ add 2) (+ num add) 1))
    (+ sum num)))

(defn tail-rec-spiral-diagonals [size]
  (spirals size 0 2 1 1))

;; problem 28 recursion

(defn rec-spiral-diagonals [n]
  (if (= n 1)
    1
    (+ (- (* 4 n n) (* 6 (- n 1))) (rec-spiral-diagonals (- n 2)))))

;; prblem 28 lazy sequence and modul and loop

(defn find-n [x]
  (loop [i 1]
    (if (and (< (* i i) x) (<= x (* (inc i) (inc i))))
      (inc i)
      (recur (inc i)))))

(defn diagonal? [x]
  (let [n (find-n x),
        pow (* n n)]
    (cond
      (= x pow) true
      (= x (- pow (- n 1))) true
      (= x (- pow (* 2 (- n 1)))) true
      (= x (- pow (* 3 (- n 1)))) true
      :else false)))

(defn lazy-and-modul-spiral-diagonals [n]
  (inc (reduce + (filter diagonal? (filter odd? (take (* n n) (iterate inc 2)))))))

(defn -main
  [& args]
  (tail-rec-largest-prime-factor 600851475143)
  (loop-largest-prime-factor 600851475143)
  (modul-largest-prime-factor 600851475143)
  (lazy-and-map-largest-prime-factor 600851475143)

  (tail-rec-spiral-diagonals 101)
  (rec-spiral-diagonals 101)
  (lazy-and-modul-spiral-diagonals 101))