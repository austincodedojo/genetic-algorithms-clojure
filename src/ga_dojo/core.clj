(ns ga-dojo.core)

(defn evaluate-expression [symbols]
  (defn separate [symbols]
    (defn classify [symbol]
      (if (number? symbol) :digits :operators))
    (group-by classify symbols))
  (defn calculate 
    ([digits operators] (calculate (rest digits) operators (first digits)))
    ([digits operators result]
      (if (or (empty? digits) (empty? operators)) 
        result
        (recur (rest digits) (rest operators) ((first operators) (first digits) result)))))
  (let [ separated-symbols (separate symbols) ]
    (calculate (separated-symbols :digits) (separated-symbols :operators))))
  
          
      
      
