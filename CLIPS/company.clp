(deftemplate company
	(slot name)
	(multislot departments))

(deftemplate department
	(slot name)
	(multislot employees))

(deftemplate employee
	(slot name)
	(slot role))


(assert (company (name "ABC") 
		(departments
			(management) 
			(programming) 
			(sales))))
   
(assert (department (name management)))

(assert (department (name programming)))

(assert (department (name sales)))
   
(assert (employee (name Ali) 
		(role manager)
		(department programming)))

(assert (employee (name Mohammad)
		(role programmer)
		(department programming)))

(assert (employee (name Ahmed) 
		(role programmer) 
		(department programming)))
