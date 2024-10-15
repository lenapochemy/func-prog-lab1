# Лабораторная работа №1

Русакова Елена Дмитриевна, ИСУ 367519, группа P3317, вариант 3, 28

## [Проблема 3: Largest Prime Factor](https://projecteuler.net/problem=3)
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143?


### Решения:
+ Цикл
```clojure
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
```

+ Хвостовая рекурсия
```clojure
(defn tail-rec-prime-factor [x n]
  (if (and (zero? (rem n x)) (prime? (quot n x)))
    (/ n x)
    (recur (inc x) n)))

(defn tail-rec-largest-prime-factor [n]
  (tail-rec-prime-factor 2 n))
```

+ Модульная реализация (используются range, filter)
```clojure
(defn divisor? [n x]
  (zero? (rem n x)))

(defn modul-largest-prime-factor [n]
  (let [numbers (range 2 (/ n 2)), 
        prime-divisors (filter prime? (filter (fn [x] (divisor? n x)) numbers))] 
    (last prime-divisors)))
```
Это решение не оптимальное и на больших числах выполняется очень долго.

+ С ленивой последовательностью (iterate) и отображением (map)

```clojure
(defn lazy-and-map-largest-prime-factor [n]
  (let [primes (filter some? (map #(if (prime? %) % nil) (take (- (quot n 2) 1) (iterate inc 2)))),
        prime-divisor (filter (fn [x] (divisor? n x)) primes)]
    (last prime-divisor)))
```
Это решение также как предыдущее оказалось не оптимальным и не подходяшим для больших чисел.

+ Реализация на языке C++:
``` cpp
bool is_prime(long long int num){
    for (long long int i = 2; i <= sqrt(num); i++){
        if (num % i == 0){
            return false;
        }
    }
    return true;
}

long long int largest_prime_factors(long long int num){
    long long int max = 0;
    long long int d;
    for (long long int i = 2; i <= num / 2; i++){
        if (num % i == 0){
            d = num / i;
            if (is_prime(d)){
                return d;
            }
        }
    }
    return max;
}
```


## [Проблема 28: Number Spiral Diagonals](https://projecteuler.net/problem=28)

Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

**21** 22 23 24 **25**

20 **7** 8 **9** 10

19 6 **1** 2 11

18 **5** 4 **3** 12

**17** 16 15 14 **13**

It can be verified that the sum of the numbers on the diagonals is 101.

What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?

### Решения:
+ Хвостовая рекурсия
``` clojure
(defn spirals [size sum add num c]
  (if (< num (* size size))
    (if (< c 4)
      (recur size (+ sum num) add (+ num add) (inc c))
      (recur size (+ sum num) (+ add 2) (+ num add) 1))
    (+ sum num)))


(defn tail-rec-spiral-diagonals [size]
  (spirals size 0 2 1 1))
```

Пояснение: Если рассмотреть ряд чисел, попавших в диагонали:

 3 5 7 9 | 13 17 21 25  | 31 37 43 49... 

то можно заметить, что разница между первыми четырьмя равна 2, между следующими четырьями 4, дальше 6, и так далее разница увеличивается на 2 каждые 4 числа.

+ Рекурсия
``` clojure
(defn rec-spiral-diagonals [n]
  (if (= n 1)
    1
    (+ (- (* 4 n n) (* 6 (- n 1))) (rec-spiral-diagonals (- n 2)))))
```

Пояснение: пусть $n$ - это сторона квадрата, тогда можем заметить, что число в правом верхнем углу такого квадрата равно $n^2$, в левом верхнем $n^2 - (n - 1)$, левом нижнем $n^2 - 2(n-1)$ и в правом нижнем $n^2 - 3(n-1)$. Тогда сумма всех углов одного квадрата со стороной n равна $4n^2 - 6(n-1)$.

+ Модульная реализация (генерация - iterate, фильтрация - filter и свертка - reduce), с использованием ленивой коллекции (iterate) и цикла (loop в вспомогательной функции)

``` clojure
(defn find-n [x]
  (loop [i 1]
    (if (and (< (* i i) x) (<= x (* (inc i) (inc i))))
      (inc i)
      (recur (inc i)))))

(defn diagonal? [x]
  (let [n (find-n x),
        pow (* n n)]
    (if (= x pow)
      true
      (if (= x (- pow (- n 1)))
        true
        (if (= x (- pow (* 2 (- n 1))))
          true
          (if (= x (- pow (* 3 (- n 1))))
            true
            false))))))


(defn lazy-and-modul-spiral-diagonals [n]
  (inc (reduce + (filter diagonal? (filter odd? (take (* n n) (iterate inc 2)))))))
```
Пояснение: в этом варианте решения, используется факт из прошлого решения, что все числа в диагоналях равны $n^2$ или $n^2 - (n - 1)$ или $n^2 - 2(n-1)$ или $n^2 - 3(n-1)$

+ Реализация на языке C++:
```cpp
int spiral_diagonals(int count){
    int sum = 1;
    int num = 1;
    int add = 2;
    for (int k = 1; k <= count / 2; k++, add += 2){
        for (int j = 1; j <= 4; j++){
            num += add;
            sum += num;
        }
    }
    return sum;
}
```


## Вывод:
Во время выполнения лабораторной работы, я начала знакомство с функциональным языком программирования Clojure, увидела на примерах, как работают рекурсия, циклы, отображения, фильтрации и свертки, и как их можно использовать для решения задач.
