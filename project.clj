(defproject lab-1 "0.1"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [lambdaisland/kaocha "1.0.887"]]
  :main lab1
  :aliases 
  {:test {:extra-paths ["test"]
          :main-opts ["-m" "kaocha.runner"]}})