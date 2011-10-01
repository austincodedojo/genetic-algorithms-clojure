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

(def cs-1 '(("0" "0" "0" "1")))
(def cs-1-plus-3 '(("0" "0" "0" "1") ("1" "0" "1" "0") ("0" "0" "1" "1") ))
(def cs-1-plus-3-times-5 (concat cs-1-plus-3 '(("1" "1" "0" "0") ("0" "1" "0" "1"))))
(def cs-1-plus-3-times-times-5 (concat cs-1-plus-3 '(("1" "1" "0" "0") ("1" "1" "0" "0") ("0" "1" "0" "1"))))
(def cs-1-plus '(("0" "0" "0" "1") ("1" "0" "1" "0")))
(def cs-1-plus-plus '(("0" "0" "0" "1") ("1" "0" "1" "0") ("1" "0" "1" "0")))
(def cs-1-1-plus '(("0" "0" "0" "1") ("0" "0" "0" "1") ("1" "0" "1" "0")))
(def cs-plus-1 '(("1" "0" "1" "0") ("0" "0" "0" "1")))
(def cs-1-plus-plus-3 '(("0" "0" "0" "1") ("1" "0" "1" "0") ("1" "0" "1" "0") ("0" "0" "1" "1")))
(def cs-1-plus-times-3 '(("0" "0" "0" "1") ("1" "0" "1" "0") ("1" "1" "0" "0") ("0" "0" "1" "1") ))
(def cs-1-nil-plus-3 '(("0" "0" "0" "1") ("1" "1" "1" "1") ("1" "0" "1" "0") ("0" "0" "1" "1") ))

(deftest decode-chromosome-cs-1-plus-3
  (is (= (list + 1 3) (decode-chromosome cs-1-plus-3))))

(deftest decode-chromosome-cs-1-plus-3-times-5
  (is (= (list * (list + 1 3) 5) (decode-chromosome cs-1-plus-3-times-5))))

(deftest decode-chromosome-cs-1-plus-3-times-5-evals-to-20
  (is (= 20 (eval (decode-chromosome cs-1-plus-3-times-5)))))

;(deftest decode-chromosome-cs-1-plus-3-with-an-error-is-1-plus-3
;  (is (= (list + 1 3) (decode-chromosome cs-1-plus-3-with-an-error))))

(deftest error-correcting-cs-1-plus-3-is-cs-1-plus-3
  (is (= cs-1-plus-3 (error-correct-chromosome cs-1-plus-3))))

(deftest error-correcting-cs-1-plus-5-is-cs-1-plus-5
  (is (= cs-1-plus-3-times-5 (error-correct-chromosome cs-1-plus-3-times-5))))

(deftest error-correcting-cs-1-plus-is-1
  (is (= cs-1 (error-correct-chromosome cs-1-plus))))

(deftest error-correcting-cs-plus-1-is-1
  (is (= cs-1 (error-correct-chromosome cs-plus-1))))

(deftest error-correcting-cs-1-plus-plus-is-1
  (is (= cs-1 (error-correct-chromosome cs-1-plus-plus))))

(deftest error-correcting-cs-1-1-plus-is-1
  (is (= cs-1 (error-correct-chromosome cs-1-1-plus))))

(deftest error-correcting-cs-1-plus-plus-3-is-cs-1-plus-3
  (is (= cs-1-plus-3 (error-correct-chromosome cs-1-plus-plus-3))))

(deftest error-correcting-cs-1-plus-3-times-times-5-is-cs-1-plus-3-times-5
  (is (= cs-1-plus-3-times-5 (error-correct-chromosome cs-1-plus-3-times-times-5))))

(deftest error-correcting-cs-1-plus-times-3-is-cs-1-plus-3
  (is (= cs-1-plus-3 (error-correct-chromosome cs-1-plus-times-3))))

(deftest error-correcting-cs-1-nil-plus-3-is-1-plus-3
  (is (= cs-1-plus-3 (error-correct-chromosome cs-1-nil-plus-3))))
