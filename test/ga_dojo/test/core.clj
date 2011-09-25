(ns ga-dojo.test.core
  (:use [ga-dojo.core])
  (:use [clojure.test]))


(deftest random-gene-is-4-bits-long 
  (is (= 4 (count (random-gene)))))

(deftest random-bit-is-one-char-string
  (is   (string? (random-bit)))
  (is   (= 1 (count (random-bit)))))

;(deftest evaluate-test-1
;  (is (= 1 (evaluate-chromosome '((0 0 0 1))))))

;(deftest evaluate-test-1
;  (is (= 3 (evaluate-chromosome '((0 0 1 1))))))

(def cs-1-plus-3 '(("0" "0" "0" "1") ("1" "0" "1" "0") ("0" "0" "1" "1") ))
(def cs-1-plus-3-times-5 (concat cs-1-plus-3 '(("1" "1" "0" "0") ("0" "1" "0" "1"))))

(deftest decode-chromosome-cs-1-plus-3
  (is (= (list + 1 3) (decode-chromosome cs-1-plus-3))))

(deftest decode-chromosome-cs-1-plus-3-times-5
  (is (= (list * (list + 1 3) 5) (decode-chromosome cs-1-plus-3-times-5))))

