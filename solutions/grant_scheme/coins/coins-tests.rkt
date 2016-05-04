#lang racket

(require rackunit
         "coins.rkt")

(check-equal? (minimum-coins '(1 2 5 10 20 50) 1) 
              1
              "low single coin")

(check-equal? (minimum-coins '(1 2 5 10 20 50) 3) 
              2
              "low multiple coins")

(check-equal? (minimum-coins '(1 2 5 10 20 50) 5) 
              1
              "medium single coin")

(check-equal? (minimum-coins '(1 2 5 10 20 50) 9) 
              3
              "medium multiple coins")

(check-equal? (minimum-coins '(1 2 5 10 20 50) 87) 
              5
              "lots of coins")

(check-equal? (minimum-coins '(1 4 15 20 50) 23) 
              3
              "greedy fail case")

"**********************"
"*** Tests Complete ***"
"**********************"