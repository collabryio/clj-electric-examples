(ns electric-starter-app.main
  (:require [hyperfiddle.electric :as e]
            [toggle-example.toggle-code :refer [ToggleFunction]]
            [hyperfiddle.electric-dom2 :as dom]))

;; Saving this file will automatically recompile and update in your browser

(e/defn Main [ring-request]
  (e/client
    (binding [dom/node js/document.body]
      (e/server (ToggleFunction.)))))
