(ns site.utils
  (:require [markdown-to-hiccup.core :refer [md->hiccup component]]
            [rum.core :as rum]))

(defn mth [s]
  (-> s
      (md->hiccup)
      (component)))

(mth "hi")


(rum/defc test-page []
  [:div "this is a test page!" ]
  )
