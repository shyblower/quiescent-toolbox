(ns quiescent-toolbox.dom)

(defn- component-fn-symbol [js-namespace tag]
  (symbol (str "js/" js-namespace "." (name tag))))

(defn tag-definition
  "Return a form to defining a wrapper function for a ReactJS tag
  component."
  [js-namespace tag]
  `(defn ~tag [& args#]
     ~(str "Return a component for " tag)
     (let [a# (make-array 0)]
       (.push a# (cljs.core/clj->js (first args#)))
       (doseq [arg# (rest args#)] (.push a# arg#))
       (.apply (.createFactory js/React ~(component-fn-symbol js-namespace tag)) nil a#))))

(defmacro define-tags
  "Macro which expands to a do block which contains a defmacro for
  each specified composite tag. The resulting macros take
  an (optional) properties argument, and any number of child
  arguments. The properties argument may be a Clojure map or a JS
  object."
  [js-namespace & tags]
  `(do (do ~@(clojure.core/map #(tag-definition js-namespace %) tags))
     (def ~'defined-tags
       ~(zipmap (map (comp keyword name) tags)
                tags))))
