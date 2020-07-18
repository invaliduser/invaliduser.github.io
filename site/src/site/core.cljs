(ns site.core
  (:require [rum.core :as rum]
            [site.pages.front :as front]))

(def holder (atom front/splash))





(rum/defc main < rum/reactive [atm]
  [:div ((rum/react holder))])

(rum/mount (main) (js/document.querySelector "#app"))
