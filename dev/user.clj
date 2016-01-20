(ns user
  (:require [figwheel-sidecar.repl-api :as f]))

(defn start-figwheel-repl! []
  (f/start-figwheel!)
  (f/cljs-repl))

(comment
  (start-figwheel-repl!)

  (stop-autobuild)

  )
