(ns site.core
  (:require [rum.core :as rum]
            [site.logic.routes :as routes]))


(rum/defc main < rum/reactive [atm]
  [:div ((rum/react routes/holder))])

(rum/mount (main) (js/document.querySelector "#app"))

(routes/init!)
