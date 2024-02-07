import scala.io.StdIn.readLine
import org.mongodb.scala.Document
import models.Employee
import scala.concurrent.ExecutionContext.Implicits.global
import org.mongodb.scala._
import org.mongodb.scala.model.Filters.equal



object EmployeeCLI extends App {

  val connectionString = "mongodb://localhost:27017"
  val client: MongoClient = MongoClient(connectionString)
  val database: MongoDatabase = client.getDatabase("employes_management")
  val employeeRepository = new EmployeeRepository(database)

  println("Welcome to the Employee Management System CLI")

  var exit = false

  while (!exit) {
    println("\nChoose an option:")
    println("1. Add Employee")
    println("2. View Employee")
    println("3. Update Employee")
    println("4. Delete Employee")
    println("5. Exit")

    val choice = readLine("Enter your choice: ")

    choice match {
      case "1" =>
        val name = readLine("Enter employee name: ")
        val position = readLine("Enter employee position: ")
        val salary = readLine("Enter employee salary: ").toDouble
        val newEmployee = Employee(name, position, salary)
        employeeRepository.addEmployee(Document(newEmployee.asBson))
          .foreach(_ => println("Employee added successfully"))
      case "2" =>
        val id = readLine("Enter employee ID: ")
        employeeRepository.getEmployeeById(id)
          .map(_.map(Document => Employee.asEmployee(Document)))
          .foreach {
            case Some(employee) => println(employee)
            case None => println("Employee not found")
          }
      case "3" =>
        val id = readLine("Enter employee ID: ")
        val name = readLine("Enter new name: ")
        val position = readLine("Enter new position: ")
        val salary = readLine("Enter new salary: ").toDouble
        val updatedEmployee = Employee(id, name, position, salary)
        employeeRepository.updateEmployee(id, Document(updatedEmployee.asBson))
          .foreach(updated =>
            if (updated) println("Employee updated successfully")
            else println("Failed to update employee")
          )
      case "4" =>
        val id = readLine("Enter employee ID: ")
        employeeRepository.deleteEmployee(id)
          .foreach(deleted =>
            if (deleted) println("Employee deleted successfully")
            else println("Failed to delete employee")
          )
      case "5" =>
        exit = true
        println("Exiting...")
      case _ =>
        println("Invalid choice. Please enter a number from 1 to 5.")
    }
  }

  // Close the MongoDB client when done
  client.close()
}
