This is the back-end of my employee management project. 

This back-end uses the spring boot framework to run. I use the MVC (MODEL VIEW CONTROLLER) model
to organize my project. My back-end creates an object (employee) with input from my front-end via http,
and places that data in a MySql database. 

My employeeController classes handles incoming REST requests
and returns the view/response. In this project it returns a list of everything in my database
minus the foreign key. My controller also allows the basic Create, Read, Update, and Delete 
operations, through the use of my annotated methods that allow for Post, Put, etc. I also 
included a sorting data options(ascending order) with my methods by first, last name, and ID. 

The Employee repo here is an interface that extends JpaRepository that takes in my Employee object,
and a Long for my employee id, and uses these arguments to retrieve data from my database. 

I also created a custom resource not found class that returns errors found in my code, i.e no 
employees with id + id + were found. This class spits out an error page. 

The applicaitons.prop sets up my sql db connection and establishes my hibernate settings.

