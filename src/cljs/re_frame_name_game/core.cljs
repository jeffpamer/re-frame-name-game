(ns re-frame-name-game.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re-frame-name-game.handlers]
              [re-frame-name-game.subs]
              [re-frame-name-game.views :as views]
              [re-frame-name-game.config :as config]
              [ajax.core :refer [GET]]))

(when config/debug?
  (println "dev mode"))

(defn fetch-employees []
  (GET "http://api.namegame.willowtreemobile.com/" {:response-format :json
                                                    :keywords? true
                                                    :handler #(re-frame/dispatch [:update-employees [:employees (shuffle %)]])}))

(defn mount-root []
  (reagent/render [views/name-game]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (fetch-employees)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
