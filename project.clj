(defproject hslu "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]

                 [org.clojure/core.logic "0.8-alpha2"]
                 [quil "2.3.0"]]

  :plugins [[lein-cljsbuild "1.1.1"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                :figwheel {:on-jsload "hslu.core/on-js-reload"}

                :compiler {:main hslu.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/hslu.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}
               ;; This next build is an compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/hslu.js"
                           :main hslu.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"
             :css-dirs ["resources/public/css"] ;; watch and update CSS
             :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
             }

  :profiles {:dev {:dependencies [;; needed for nREPL
                                  [com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.11"]
                                  [figwheel-sidecar "0.5.0-2"]]
                   :source-paths ["dev"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
