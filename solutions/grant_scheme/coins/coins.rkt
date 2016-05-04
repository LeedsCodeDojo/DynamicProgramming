#lang racket

(define (get-coins-by-location available-coins no-coins accumulator)
    (cond
      ((null? available-coins) accumulator)
      ((> (car available-coins) (length no-coins)) accumulator)
      (else (get-coins-by-location 
             (cdr available-coins) 
             no-coins 
             (cons (list-ref no-coins (- (car available-coins) 1)) accumulator)))))

(define (get-lowest number-list)
  (apply min number-list))

(define (minimum-coins coins-available target-value)    
  (define (loop-up value no-coins)
    (cond
      ((= value target-value) (car no-coins))
      (else (loop-up 
             (+ 1 value) 
             (cons (+ 1 (get-lowest (get-coins-by-location coins-available no-coins '()))) no-coins)))))
  
  (loop-up 0 '(0)))
  
(provide minimum-coins)