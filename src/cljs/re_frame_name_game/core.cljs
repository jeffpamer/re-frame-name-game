(ns re-frame-name-game.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [re-frame-name-game.handlers]
            [re-frame-name-game.subs]
            [re-frame-name-game.views :as views]
            [re-frame-name-game.config :as config]
            [goog.net.XhrIo :as xhr]
            [cljs.core.async :refer [put! chan <!]]))

(when config/debug?
  (println "dev mode"))

(defn fetch
  [url]
  (let [c (chan)]
    (xhr/send url
      (fn [event]
        (put! c (js->clj (.getResponseJson (.-target event)) :keywordize-keys true))))
    c))

(defn fetch-employees 
  []
  (go (let [employees (<! (fetch "http://api.namegame.willowtreemobile.com/"))]
        (re-frame/dispatch [:update-employees [:employees (shuffle employees)]]))))

(defn mount-root []
  (reagent/render [views/name-game]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (fetch-employees)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
