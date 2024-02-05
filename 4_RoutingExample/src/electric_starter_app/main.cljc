(ns electric-starter-app.main
  (:require electric-starter-app.router-example
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

;; Saving this file will automatically recompile and update in your browser

(e/defn Main [ring-request]
  (e/client
    (binding [dom/node js/document.body]
      (dom/text "This is the main page")
      (e/server (electric-starter-app.router-example/Main.)))))
