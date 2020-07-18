(ns site.core
  (:require [rum.core :as rum]
            [site.logic.routes :as routes :refer [page-atom init!]]))



(init!)

(rum/defc main < rum/reactive []
  (let [component-f (get-in (rum/react page-atom)
                            [:data :view])]
    [:div (component-f)]))

(rum/mount (main) (js/document.querySelector "#app"))



