(set-env!
 :source-paths #{"src/quiescent-toolbox"}
 :dependencies '[[adzerk/bootlaces "0.1.11" :scope "test"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "1.0.0")
(bootlaces! +version+)

(task-options!
 pom  {:project     'tscheibl/quiescent-toolbox
       :version     +version+
       :description "Utility library supplementing the Clojurescript React wrapper Quiescent"
       :url         "https://github.com/tscheibl/quiescent-toolbox"
       :scm         {:url "https://github.com/tscheibl/quiescent-toolbox.git"}
       :license     {"EPL" "https://github.com/tscheibl/quiescent-toolbox/blob/master/LICENSE"}})
