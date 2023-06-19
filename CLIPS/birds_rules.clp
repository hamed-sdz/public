; example facts:
; (assert (bird b1))
; (assert (penguin p1))
; (assert (canary c1))


(defrule bird-rule
	(bird ?x)
	=>
	(assert
		(moves-with-two-wings ?x)
		(has-a-variety-of-colors ?x)
		(usually-feeds-on-earthworms ?x)
	)
)

(defrule penguin-rule
	(penguin ?x)
	=>
	(assert
		(bird ?x)
	)
)

(defrule penguin-exceptions-rule
	(penguin ?x)
	?f1 <- (moves-with-two-wings ?x)
	?f2 <- (usually-feeds-on-earthworms ?x)
	=>
	(assert
		(moves-using-legs ?x)
		(eats-fish ?x)
	)
	(retract ?f1 ?f2)
)

(defrule canary-rule
	(canary ?x)
	=>
	(assert
		(bird ?x)
		(usually-seen-in-yellow ?x)
	)
)
