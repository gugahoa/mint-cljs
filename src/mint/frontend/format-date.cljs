(ns mint.frontend.format-date
  (:require [clojure.string :as str]))

(def month-names
  "A vector of abbreviations for the twelve months, in order."
  ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug" "Sep" "Oct" "Nov" "Dec"])

(defn month-name
  "Returns the abbreviation for a month in the range [1..12]."
  [month]
  (get month-names (dec month)))

(defn parse-iso-date
  "Returns a vector of the year, month, and day from an ISO 8601 date string."
  [date]
  (mapv #(js/parseInt %) (str/split date #"-0?")))

(defn format-date
  "Converts an ISO 8601 date string to one of the format \"(D)D Mon YYYY\"."
  [date]
  (let [[year month day] (parse-iso-date date)]
    (str day " " (month-name month) ", " year)))
