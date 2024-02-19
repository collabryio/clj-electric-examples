(ns electric-starter-app.main
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [electric-starter-app.multiple-function-call]))

;; Saving this file will automatically recompile and update in your browser

(e/defn Main [ring-request]
  (e/client
    (binding [dom/node js/document.body]
      (e/server (electric-starter-app.multiple-function-call/RenderPage.)))))
