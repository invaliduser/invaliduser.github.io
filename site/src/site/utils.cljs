(ns site.utils
  (:require [markdown-to-hiccup.core :refer [md->hiccup component]]))

(defn mth [s]
  (-> s
      (md->hiccup)
      (component)))
