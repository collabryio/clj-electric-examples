(ns electric-starter-app.multiple-function-call
  (:require [clojure.string :as str]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui4]))

(def default_value "NaN")
(def !current_value (atom default_value))

(defn set_value [new_value] (reset! !current_value new_value))

(defn reset_value [] (reset! !current_value default_value))

(e/defn RenderPage []
        (e/client

          (dom/div
            (dom/p (dom/text "Current value on server is: " (e/watch !current_value))))
          (dom/br)

          (dom/div
            (dom/text "Here you can set value by writing to the input: ")
            (ui4/input (e/watch !current_value) (e/fn [new_value] (set_value new_value)))
            )

          (dom/br)

          (dom/div
            (let [!temp_value (atom "")]
            (dom/text "Here you cat set value by clicking to button: ")
            (ui4/input (e/watch !temp_value) (e/fn [input_value] (reset! !temp_value input_value))
                       (dom/props {:placeholder "Enter new value here"}))
            (ui4/button (e/fn [] (set_value (deref !temp_value))) (dom/text "Set value"))
            ))

          (dom/br)

          (dom/div
            (ui4/button (e/fn [] (reset_value)) (dom/text "Reset to default")))))
