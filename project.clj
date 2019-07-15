(defproject org.clojars.vladimirmarkovic86/validator-lib "0.1.36"
  :description "Validator library"
  :url "http://github.com/VladimirMarkovic86/validator-lib"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojurescript "1.10.339"]
                 [org.clojars.vladimirmarkovic86/js-lib "0.1.17"]
                 [org.clojars.vladimirmarkovic86/language-lib "0.2.34"]
                 ]

  :min-lein-version "2.0.0"
  
  :source-paths ["src/cljs"]
  
  :jar-exclusions [#"^public/"]

  :plugins [[lein-cljsbuild  "1.1.7"]
            [lein-doo "0.1.11"]
            ]

  :cljsbuild
    {:builds
      {:test
        {:source-paths ["src/cljs" "test/cljs"]
         :compiler     {:main validator-lib.test-runner
                        :optimizations :whitespace
                        :output-dir "resources/public/assets/js/out/test"
                        :output-to "resources/public/assets/js/test.js"}}
       }}
 )

