(defproject lab-1 "0.1"
  :dependencies [[org.clojure/clojure "1.10.3"]]
  :main lab1
  :aliases {:test {:exec ["clojure" "-A:test"]}
            :lint {:exec ["clj-kondo" "--lint" "src" "--config" ".clj-kondo/config.edn"]}
            :format {:exec ["clj-kondo" "--lint" "src" "--config" ".clj-kondo/config.edn" "--fix"]}}
 )