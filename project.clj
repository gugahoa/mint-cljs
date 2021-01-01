(defproject mint-cljs "0.0.1"
  :description "mint-cljs"
  :license {:name "Eclipse Public License"
            :url  "https://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[reagent "1.0.0"]
                 [thheller/shadow-cljs "2.11.11"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"]
                 [com.google.javascript/closure-compiler-unshaded "v20200830"]]
  :profiles {}
  :java-source-paths ["src"]
  :jvm-opts ["-Djava.library.path=orgize-jni/target/debug:orgize-jni/target/release"]
  :javac-options ["-source" "8" "-target" "8"
                  "-Xlint:all,-options,-path"
                  "-Werror"
                  "-proc:none"]
  :pedantic? :warn)
