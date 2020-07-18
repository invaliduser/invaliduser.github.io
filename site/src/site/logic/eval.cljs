(ns site.logic.eval
  (:require   [rum.core :as rum]
              [cljs.tools.reader :refer [read-string]]
              [cljs.js :refer [empty-state eval js-eval]]
              [cljs.env :refer [*compiler*]]
              [cljs.pprint :refer [pprint]]))


(defn eval-str [s]
  (eval (empty-state)
        (read-string s)
        {:eval       js-eval
         :source-map true
         :context    :expr}
        (fn [result] result)))

(rum/defcs parit < (rum/local "" :v) [state]
  (let [latom (:v state)]
    [:div
     [:textarea {:value @latom :on-change #(reset! latom (.. % -target -value))}]
     [:button {:type "button" :on-click #(eval-str @latom)} "Eval"]]))
