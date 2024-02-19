(ns electric-starter-app.multiple-function-call
  (:require [clojure.string :as str]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui4]))

(def !current_value (atom 0))

(defn set_value [new_value] (reset! !current_value new_value))

(defn reset_value [] (reset! !current_value "NaN"))

(e/defn RenderPage []
        (e/client

          (dom/div (dom/p (dom/text "Current value is: " (e/watch !current_value))))
          (dom/br)

          (dom/div
          (dom/input (dom/props {:placeholder "Enter value you want to set"})
                     (dom/on "keydown" (e/fn [value] (when (= "Enter" (set_value value)))))))


          (dom/br)
          (dom/div
          (ui4/button (e/fn [] (reset_value)) (dom/text "Reset")))))
