(ns mint.reader.core (:require
                      [clojure.java.io :as io]
                      [clojure.string :as str]))

(def directory (io/file "./posts"))
(def files (filter #(.isFile %) (file-seq directory)))

(defn- raw-properties [reader]
  (take-while not-empty reader))

(defn- clean-raw-property [raw-property]
  (-> raw-property
      (str/split #":")
      second
      str/trim
      (str/split #" " 2)
      (update-in [0] keyword)))

(defn build-properties [rdr]
  (->> (line-seq rdr)
       raw-properties
       (map clean-raw-property)
       (into {})))

(defn- read-properties [file]
  (with-open [rdr (io/reader file)]
    (build-properties rdr)))

(defmacro posts-index []
  (mapv read-properties files))
