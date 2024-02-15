package gui

import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.stage._
import org.mongodb.scala._
import org.mongodb.scala.bson.Document
import models.Employee
import repository.EmployeeRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.application.Platform
import java.text.NumberFormat

object EmployeeGUI extends JFXApp {

  val connectionString = "mongodb://localhost:27017"
  val client: MongoClient = MongoClient(connectionString)
  val employeeRepository = new EmployeeRepository(client) // Passing MongoClient to the repository

  stage = new JFXApp.PrimaryStage {
    title = "Employee Management System"
    scene = new Scene(600, 400) {
      val addBtn = new Button("Add Employee")
      val viewBtn = new Button("View Employee")
      val updateBtn = new Button("Update Employee")
      val deleteBtn = new Button("Delete Employee")

      val vbox = new VBox(10)
      vbox.getChildren.addAll(addBtn, viewBtn, updateBtn, deleteBtn)
      vbox.setAlignment(javafx.geometry.Pos.CENTER)

      addBtn.setOnAction(_ => showAddEmployeeDialog())
      viewBtn.setOnAction(_ => showViewEmployeeDialog())
      updateBtn.setOnAction(_ => showUpdateEmployeeDialog())
      deleteBtn.setOnAction(_ => showDeleteEmployeeDialog())

      root = new BorderPane {
        center = vbox
      }
    }
  }

  // Define dialog functions

  def showAddEmployeeDialog(): Unit = {
    Platform.runLater {
      val dialog = new Dialog[Unit]() {
        initOwner(stage)
        title = "Add Employee"
        headerText = "Enter employee details:"
        resizable = false
      }

      val nameLabel = new Label("Name:")
      val positionLabel = new Label("Position:")
      val salaryLabel = new Label("Salary:")

      val nameTextField = new TextField()
      val positionTextField = new TextField()
      val salaryTextField = new TextField()

      val gridPane = new GridPane {
        hgap = 10
        vgap = 10
        padding = Insets(20, 100, 10, 10)

        add(nameLabel, 0, 0)
        add(nameTextField, 1, 0)
        add(positionLabel, 0, 1)
        add(positionTextField, 1, 1)
        add(salaryLabel, 0, 2)
        add(salaryTextField, 1, 2)
      }

      val addButton = new Button("Add")
      addButton.setDefaultButton(true)
      addButton.setOnAction { _ =>
        val name = nameTextField.text.value
        val position = positionTextField.text.value
        val salary = scala.util.Try(salaryTextField.text.value.toDouble)

        salary match {
          case scala.util.Success(salaryValue) =>
            println("Adding employee...")
            val newEmployee = Employee(name, position, salaryValue)
            employeeRepository.addEmployee(Document(newEmployee.asBson))
              .foreach(_ => {
                println("Employee added successfully.")
                showSuccessMessage("Success", "Employee added successfully")
                updateUI()
                dialog.close()
              })
          case scala.util.Failure(exception) =>
            println("Invalid salary value.")
            showError("Error", "Invalid salary value. Please enter a valid number for salary.")
        }
      }

      dialog.dialogPane().content = new VBox() {
        spacing = 10
        children = Seq(gridPane, addButton)
      }

      // Handle close event
      dialog.dialogPane().getButtonTypes.add(ButtonType.Close)

      dialog.showAndWait()
    }
  }

  def showViewEmployeeDialog(): Unit = {
    Platform.runLater {
      val dialog = new Dialog[Unit]() {
        initOwner(stage)
        title = "View Employee"
        headerText = "Enter employee ID:"
        resizable = false
      }

      val idTextField = new TextField()

      val gridPane = new GridPane {
        hgap = 10
        vgap = 10
        padding = Insets(20, 100, 10, 10)

        add(new Label("Employee ID:"), 0, 0)
        add(idTextField, 1, 0)
      }

      val viewButton = new Button("View")
      viewButton.setDefaultButton(true)
      viewButton.setOnAction { _ =>
        val id = idTextField.text.value
        employeeRepository.getEmployeeById(id)
          .map(_.map(Document => Employee.asEmployee(Document)))
          .foreach {
            case Some(employee) =>
              val formattedContent = formatEmployeeDetails(employee)
              showInformationMessage("Employee Details", formattedContent)
            case None =>
              showError("Error", "Employee not found. No employee found with the given ID.")
          }
      }

      dialog.dialogPane().content = new VBox() {
        spacing = 10
        children = Seq(gridPane, viewButton)
      }

      // Handle close event
      dialog.dialogPane().getButtonTypes.add(ButtonType.Close)

      dialog.showAndWait()
    }
  }

  def formatEmployeeDetails(employee: Employee): String = {
    s"Name: ${employee.name}\nPosition: ${employee.position}\nSalary: ${formatSalary(employee.salary)}"
  }

  def formatSalary(salary: Double): String = {
    val formatter = NumberFormat.getInstance()
    formatter.setGroupingUsed(true)
    formatter.format(salary)
  }

  def showUpdateEmployeeDialog(): Unit = {
    Platform.runLater {
      val dialog = new Dialog[Unit]() {
        initOwner(stage)
        title = "Update Employee"
        headerText = "Enter employee details:"
        resizable = false
      }

      val idLabel = new Label("Employee ID:")
      val nameLabel = new Label("Name:")
      val positionLabel = new Label("Position:")
      val salaryLabel = new Label("Salary:")

      val idTextField = new TextField()
      val nameTextField = new TextField()
      val positionTextField = new TextField()
      val salaryTextField = new TextField()

      val gridPane = new GridPane {
        hgap = 10
        vgap = 10
        padding = Insets(20, 100, 10, 10)

        add(idLabel, 0, 0)
        add(idTextField, 1, 0)
        add(nameLabel, 0, 1)
        add(nameTextField, 1, 1)
        add(positionLabel, 0, 2)
        add(positionTextField, 1, 2)
        add(salaryLabel, 0, 3)
        add(salaryTextField, 1, 3)
      }

      val updateButton = new Button("Update")
      updateButton.setDefaultButton(true)
      updateButton.setOnAction { _ =>
        val id = idTextField.text.value
        val name = nameTextField.text.value
        val position = positionTextField.text.value
        val salary = scala.util.Try(salaryTextField.text.value.toDouble)

        salary match {
          case scala.util.Success(salaryValue) =>
            val updatedEmployee = Employee(id, name, position, salaryValue)
            employeeRepository.updateEmployee(id, Document(updatedEmployee.asBson))
              .foreach(updated =>
                if (updated) {
                  showSuccessMessage("Success", "Employee updated successfully")
                  updateUI()
                  dialog.close()
                } else {
                  showError("Error", "Failed to update employee. Unable to update employee with the given ID.")
                }
              )
          case scala.util.Failure(exception) =>
            showError("Error", "Invalid salary value. Please enter a valid number for salary.")
        }
      }

      dialog.dialogPane().content = new VBox() {
        spacing = 10
        children = Seq(gridPane, updateButton)
      }

      // Handle close event
      dialog.dialogPane().getButtonTypes.add(ButtonType.Close)

      dialog.showAndWait()
    }
  }

  def showDeleteEmployeeDialog(): Unit = {
    Platform.runLater {
      val dialog = new Dialog[Unit]() {
        initOwner(stage)
        title = "Delete Employee"
        headerText = "Enter employee ID:"
        resizable = false
      }

      val idTextField = new TextField()

      val gridPane = new GridPane {
        hgap = 10
        vgap = 10
        padding = Insets(20, 100, 10, 10)

        add(new Label("Employee ID:"), 0, 0)
        add(idTextField, 1, 0)
      }

      val deleteButton = new Button("Delete")
      deleteButton.setDefaultButton(true)
      deleteButton.setOnAction { _ =>
        val id = idTextField.text.value
        employeeRepository.deleteEmployee(id)
          .foreach(deleted =>
            if (deleted) {
              showSuccessMessage("Success", "Employee deleted successfully")
              updateUI()
              dialog.close()
            } else {
              showError("Error", "Failed to delete employee. Unable to delete employee with the given ID.")
            }
          )
      }

      dialog.dialogPane().content = new VBox() {
        spacing = 10
        children = Seq(gridPane, deleteButton)
      }

      // Handle close event
      dialog.dialogPane().getButtonTypes.add(ButtonType.Close)

      dialog.showAndWait()
    }
  }

  // Show success message
  def showSuccessMessage(header: String, content: String): Unit = {
    Platform.runLater {
      new Alert(Alert.AlertType.Information) {
        initOwner(stage)
        title = header
        headerText = header
        contentText = content
      }.showAndWait()
    }
  }

  // Show information message
  def showInformationMessage(header: String, content: String): Unit = {
    Platform.runLater {
      new Alert(Alert.AlertType.Information) {
        initOwner(stage)
        title = header
        headerText = header
        contentText = content
      }.showAndWait()
    }
  }

  // Show error message
  def showError(header: String, content: String): Unit = {
    Platform.runLater {
      new Alert(Alert.AlertType.Error) {
        initOwner(stage)
        title = header
        headerText = header
        contentText = content
      }.showAndWait()
    }
  }

  // Close the MongoDB client when done
  stage.setOnCloseRequest { _ =>
    println("Close button clicked!")
    client.close()
    sys.exit(0) // Exiting the application properly
  }

  // Update UI after operations
  def updateUI(): Unit = {
    Platform.runLater {
      stage.scene().getRoot.requestFocus() // Request focus to update UI
    }
  }
}
