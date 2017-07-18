(defproject basics "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [expectations "2.2.0-beta1"]]
  :plugins [[lein-expectations "0.0.8"]
            [lein-autoexpect "1.9.0"]]
  :main ^:skip-aot basics.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
