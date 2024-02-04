(ns electric-starter-app.main
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]))

;; Saving this file will automatically recompile and update in your browser

(e/defn Main [ring-request]
  (e/client
    (binding [dom/node js/document.body]
      (let [!state (atom 0)]
        (dom/p (dom/text (e/watch !state)))

        (ui/button (e/fn [] (swap! !state inc))
                   (dom/text "+"))

        (dom/br)
        (dom/br)

        (ui/button (e/fn [] (swap! !state dec))
                   (dom/text "-"))
        (dom/br)
        (dom/br)

        (ui/button (e/fn [] (reset! !state 0))
                   (dom/text "reset"))))))
