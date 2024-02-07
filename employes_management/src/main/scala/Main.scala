import org.mongodb.scala._
import org.mongodb.scala.model.Filters.equal
import models.Employee

import scala.concurrent.ExecutionContext.Implicits.global 

object Main extends App {
  
  val employee = Employee("3", "Ayoub BARKI", "Data Analysis", 500000000.0)

  // MongoDB connection settings
  val connectionString = "mongodb://localhost:27017"
  val client: MongoClient = MongoClient(connectionString)
  val database: MongoDatabase = client.getDatabase("employes_management")

  // Create EmployeeRepository with the MongoDatabase instance
  val employeeRepository = new EmployeeRepository(database)

  // Create
  employeeRepository.addEmployee(Document(employee.asBson))

  // Read
  val retrievedEmployee = employeeRepository.getEmployeeById("1").map(_.getOrElse(Document()))
  println(retrievedEmployee)

  // Update
  val updatedEmployee = employee.copy(name = "Updated John Doe")
  employeeRepository.updateEmployee("1", Document(updatedEmployee.asBson))

  // Read again
  val updatedRetrievedEmployee = employeeRepository.getEmployeeById("1").map(_.getOrElse(Document()))
  println(updatedRetrievedEmployee)

  // Delete
  employeeRepository.deleteEmployee("1")

  // Close the MongoDB client when done
  client.close()
}
