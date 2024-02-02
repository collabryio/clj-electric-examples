(ns sample-module.sample-controller
  (:require [hyperfiddle.electric :as e]
    [hyperfiddle.electric-dom2 :as dom]))

(e/defn SimpleMain []
        (e/client
          (dom/text "This is the SimpleMain")
          (dom/br)
          (dom/br)
          (dom/text "First Line")
          (dom/br)
          (dom/text "Second Line")))