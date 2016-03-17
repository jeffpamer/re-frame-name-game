(ns re-frame-name-game.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re-frame-name-game.handlers]
              [re-frame-name-game.subs]
              [re-frame-name-game.views :as views]
              [re-frame-name-game.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/name-game]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
