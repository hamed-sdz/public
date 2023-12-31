(defclass bird (is-a USER)
	(slot move (type SYMBOL) (default-dynamic with-two-wings))
	(slot food (type SYMBOL) (default-dynamic usually-earthworms))
	(slot color (type SYMBOL) (default-dynamic variety-of-colors))
)

(defclass penguin (is-a bird)
	(slot move (type SYMBOL) (default-dynamic using-legs))
	(slot food (type SYMBOL) (default-dynamic fish))
	(slot color (type SYMBOL))
)

(defclass canary (is-a bird)
	(slot move (type SYMBOL))
	(slot food (type SYMBOL))
	(slot color (type SYMBOL) (default-dynamic usually-seen-in-yellow))
)
