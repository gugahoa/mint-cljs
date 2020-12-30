(ns mint.frontend.app
  (:require
   [reagent.dom :as rdom]
   [mint.frontend.format-date :as fmt])
  (:require-macros [mint.reader.core :as reader]))

(defn make-article-summary [{id :id date :date title :title summary :summary}]
  [:div {:key id}
   [:p {:class "text-sm text-gray-500"}
    [:time {:dateTime date} (fmt/format-date date)]]
   [:a {:href "#" :class "mt-2 block"}
    [:p {:class "text-xl font-semibold text-gray-900"} title]
    [:p {:class "mt-3 text-base text-gray-500"} summary]]
   [:div {:class "mt-3"}
    [:a {:href "#" :class "text-base font-semibold text-indigo-600 hover:text-indigo-500"} "Read full article"]]])

(defn subscription-form []
  [:div {:class "mt-6 flex flex-col sm:flex-row lg:mt-0 lg:justify-end"}
   [:div {:class "mt-2 flex-shrink-0 w-full flex rounded-md shadow-sm sm:mt-0 sm:w-auto sm:inline-flex"}
    [:a {:href "https://buttondown.email/gustavo.aguiar" :target "_blank" :class "w-full bg-indigo-600 px-4 py-2 border border-transparent rounded-md flex items-center justify-center text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:w-auto sm:inline-flex"} "Subscribe"]]])

(defn root-component []
  [:div {:class "bg-white pt-16 pb-20 px-4 sm:px-6 lg:pt-24 lg:pb-28 lg:px-8"}
   [:div {:class "relative max-w-lg mx-auto divide-y-2 divide-gray-200 lg:max-w-7xl"}
    [:div
     [:h2 {:class "text-3xl tracking-tight font-extrabold text-gray-900 sm:text-4xl"} "Blog"]
     [:div {:class "mt-3 sm:mt-4 lg:grid lg:grid-cols-2 lg:gap-5 lg:items-center"}
      [:p {:class "text-xl text-gray-500"} "Get monthly articles in your inbox about functional programming languages"]
      [subscription-form]]]
    [:div {:class "mt-6 pt-10 grid gap-16 lg:grid-cols-2 lg:gap-x-5 lg:gap-y-12"}
     (map make-article-summary (reader/posts-index))]]])

(defn ^:dev/after-load start []
  (rdom/render [root-component] (js/document.getElementById "root")))

(defn ^:export init []
  (start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))
