; example facts:
; (assert (store has-meat))
; (assert (store has-fish))
; (assert (money 11))


(defrule rule1
	(purchase meat)
	=>
	(assert (serve red-wine))
)

(defrule rule2
	(purchase fish)
	=>
	(assert (serve white-wine))
)

(defrule rule3
	(store has-fish)
	(enough-money buy-fish)
	=>
	(assert (purchase fish))
)

(defrule rule4
	(store has-meat)
	(enough-money buy-meat)
	=>
	(assert (purchase meat))
)

(defrule rule5
	(money ?x)
	(test (>= ?x 10))
	=>
	(assert (enough-money buy-meat))
)

(defrule rule6
	(money ?x)
	(test (< ?x 10))
	(test (> ?x 5))
	=>
	(assert (enough-money buy-fish))
)

(defrule rule7
	(serve white-wine)
	=>
	(assert (wear white-coat))
)

(defrule rule8
	(purchase meat)
	=>
	(assert (wear red-coat))
)
