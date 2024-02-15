file:///C:/Users/Asus%20VivoBook%2011TH/Desktop/scala_project/employes_management/src/main/scala/gui/EmployeeGUI.scala
### scala.reflect.internal.FatalError: 
  bad constant pool index: 0 at pos: 48445
     while compiling: <no file>
        during phase: globalPhase=<no phase>, enteringPhase=<some phase>
     library version: version 2.13.8
    compiler version: version 2.13.8
  reconstructed args: -classpath <WORKSPACE>\employes_management\.bloop\root\bloop-bsp-clients-classes\classes-Metals-BdbYDQc6T_KFUwp0iP8eJw==;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\sourcegraph\semanticdb-javac\0.9.9\semanticdb-javac-0.9.9.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.8\scala-library-2.13.8.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\scala\mongo-scala-driver_2.13\4.4.0\mongo-scala-driver_2.13-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalafx\scalafx_2.13\15.0.1-R20\scalafx_2.13-15.0.1-R20.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-reflect\2.13.8\scala-reflect-2.13.8.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\scala\mongo-scala-bson_2.13\4.4.0\mongo-scala-bson_2.13-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\mongodb-driver-reactivestreams\4.4.0\mongodb-driver-reactivestreams-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\bson\4.4.0\bson-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\mongodb-driver-core\4.4.0\mongodb-driver-core-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\reactivestreams\reactive-streams\1.0.2\reactive-streams-1.0.2.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\projectreactor\reactor-core\3.2.22.RELEASE\reactor-core-3.2.22.RELEASE.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: EmptyTree
       tree position: <unknown>
            tree tpe: <notype>
              symbol: null
           call site: <none> in <none>

== Source file context for tree position ==



occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.13.8
Classpath:
<WORKSPACE>\employes_management\.bloop\root\bloop-bsp-clients-classes\classes-Metals-BdbYDQc6T_KFUwp0iP8eJw== [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\sourcegraph\semanticdb-javac\0.9.9\semanticdb-javac-0.9.9.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.8\scala-library-2.13.8.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\scala\mongo-scala-driver_2.13\4.4.0\mongo-scala-driver_2.13-4.4.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalafx\scalafx_2.13\15.0.1-R20\scalafx_2.13-15.0.1-R20.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-reflect\2.13.8\scala-reflect-2.13.8.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\scala\mongo-scala-bson_2.13\4.4.0\mongo-scala-bson_2.13-4.4.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\mongodb-driver-reactivestreams\4.4.0\mongodb-driver-reactivestreams-4.4.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\bson\4.4.0\bson-4.4.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\mongodb-driver-core\4.4.0\mongodb-driver-core-4.4.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\reactivestreams\reactive-streams\1.0.2\reactive-streams-1.0.2.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\projectreactor\reactor-core\3.2.22.RELEASE\reactor-core-3.2.22.RELEASE.jar [exists ]
Options:
-Yrangepos -Xplugin-require:semanticdb


action parameters:
offset: 3928
uri: file:///C:/Users/Asus%20VivoBook%2011TH/Desktop/scala_project/employes_management/src/main/scala/gui/EmployeeGUI.scala
text:
```scala
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.stage._
import org.mongodb.scala._
import org.mongodb.scala.model.Filters.equal
import models.Employee

import scala.concurrent.ExecutionContext.Implicits.global

object EmployeeGUI extends JFXApp {

  val connectionString = "mongodb://localhost:27017"
  val client: MongoClient = MongoClient(connectionString)
  val database: MongoDatabase = client.getDatabase("employes_management")
  val employeeRepository = new EmployeeRepository(database)

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

  def showAddEmployeeDialog(): Unit = {
    val dialog = new Dialog[Unit]() {
      dialog.initOwner(stage)
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
          val newEmployee = Employee(name, position, salaryValue)
          employeeRepository.addEmployee(Document(newEmployee.asBson))
            .foreach(_ => {
              dialog.close()
              println("Employee added successfully")
            })
        case scala.util.Failure(exception) =>
          new Alert(Alert.AlertType.Error) {
            initOwner(dialog)
            title = "Error"
            headerText = "Invalid salary value"
            contentText = "Please enter a valid number for salary."
          }.showAndWait()
      }
    }

    dialog.dialogPane().content = new VBox() {
      spacing = 10
      children = Seq(gridPane, addButton)
    }

    dialog.showAndWait()
  }

  def showViewEmployeeDialog(): Unit = {
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
      val id = idTextField.te@@xt.value
      employeeRepository.getEmployeeById(id)
        .map(_.map(Document => Employee.asEmployee(Document)))
        .foreach {
          case Some(employee) => new Alert(Alert.AlertType.Information) {
            initOwner(dialog)
            title = "Employee Details"
            headerText = "Employee found:"
            contentText = s"Name: ${employee.name}\nPosition: ${employee.position}\nSalary: ${employee.salary}"
          }.showAndWait()
          case None => new Alert(Alert.AlertType.Error) {
            initOwner(dialog)
            title = "Error"
            headerText = "Employee not found"
            contentText = "No employee found with the given ID."
          }.showAndWait()
        }
    }

    dialog.dialogPane().content = new VBox() {
      spacing = 10
      children = Seq(gridPane, viewButton)
    }

    dialog.showAndWait()
  }

  def showUpdateEmployeeDialog(): Unit = {
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
                dialog.close()
                println("Employee updated successfully")
              } else {
                new Alert(Alert.AlertType.Error) {
                  initOwner(dialog)
                  title = "Error"
                  headerText = "Failed to update employee"
                  contentText = "Unable to update employee with the given ID."
                }.showAndWait()
              }
            )
        case scala.util.Failure(exception) =>
          new Alert(Alert.AlertType.Error) {
            initOwner(dialog)
            title = "Error"
            headerText = "Invalid salary value"
            contentText = "Please enter a valid number for salary."
          }.showAndWait()
      }
    }

    dialog.dialogPane().content = new VBox() {
      spacing = 10
      children = Seq(gridPane, updateButton)
    }

    dialog.showAndWait()
  }

  def showDeleteEmployeeDialog(): Unit = {
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
            dialog.close()
            println("Employee deleted successfully")
          } else {
            new Alert(Alert.AlertType.Error) {
              initOwner(dialog)
              title = "Error"
              headerText = "Failed to delete employee"
              contentText = "Unable to delete employee with the given ID."
            }.showAndWait()
          }
        )
    }

    dialog.dialogPane().content = new VBox() {
      spacing = 10
      children = Seq(gridPane, deleteButton)
    }

    dialog.showAndWait()
  }

  // Close the MongoDB client when done
  client.close()
}

```



#### Error stacktrace:

```
scala.reflect.internal.Reporting.abort(Reporting.scala:69)
	scala.reflect.internal.Reporting.abort$(Reporting.scala:65)
	scala.reflect.internal.SymbolTable.abort(SymbolTable.scala:28)
	scala.tools.nsc.symtab.classfile.ClassfileParser$ConstantPool.errorBadIndex(ClassfileParser.scala:407)
	scala.tools.nsc.symtab.classfile.ClassfileParser$ConstantPool.getExternalName(ClassfileParser.scala:262)
	scala.tools.nsc.symtab.classfile.ClassfileParser.readParamNames$1(ClassfileParser.scala:853)
	scala.tools.nsc.symtab.classfile.ClassfileParser.parseAttribute$1(ClassfileParser.scala:859)
	scala.tools.nsc.symtab.classfile.ClassfileParser.$anonfun$parseAttributes$6(ClassfileParser.scala:936)
	scala.tools.nsc.symtab.classfile.ClassfileParser.parseAttributes(ClassfileParser.scala:936)
	scala.tools.nsc.symtab.classfile.ClassfileParser.parseMethod(ClassfileParser.scala:635)
	scala.tools.nsc.symtab.classfile.ClassfileParser.parseClass(ClassfileParser.scala:548)
	scala.tools.nsc.symtab.classfile.ClassfileParser.$anonfun$parse$2(ClassfileParser.scala:174)
	scala.tools.nsc.symtab.classfile.ClassfileParser.$anonfun$parse$1(ClassfileParser.scala:159)
	scala.tools.nsc.symtab.classfile.ClassfileParser.parse(ClassfileParser.scala:142)
	scala.tools.nsc.symtab.SymbolLoaders$ClassfileLoader.doComplete(SymbolLoaders.scala:342)
	scala.tools.nsc.symtab.SymbolLoaders$SymbolLoader.$anonfun$complete$2(SymbolLoaders.scala:249)
	scala.tools.nsc.symtab.SymbolLoaders$SymbolLoader.complete(SymbolLoaders.scala:247)
	scala.reflect.internal.Symbols$Symbol.completeInfo(Symbols.scala:1561)
	scala.reflect.internal.Symbols$Symbol.info(Symbols.scala:1533)
	scala.reflect.internal.Definitions.scala$reflect$internal$Definitions$$enterNewMethod(Definitions.scala:47)
	scala.reflect.internal.Definitions$DefinitionsClass.String_$plus$lzycompute(Definitions.scala:1256)
	scala.reflect.internal.Definitions$DefinitionsClass.String_$plus(Definitions.scala:1256)
	scala.reflect.internal.Definitions$DefinitionsClass.syntheticCoreMethods$lzycompute(Definitions.scala:1577)
	scala.reflect.internal.Definitions$DefinitionsClass.syntheticCoreMethods(Definitions.scala:1559)
	scala.reflect.internal.Definitions$DefinitionsClass.symbolsNotPresentInBytecode$lzycompute(Definitions.scala:1590)
	scala.reflect.internal.Definitions$DefinitionsClass.symbolsNotPresentInBytecode(Definitions.scala:1590)
	scala.reflect.internal.Definitions$DefinitionsClass.init(Definitions.scala:1646)
	scala.tools.nsc.Global$Run.<init>(Global.scala:1226)
	scala.tools.nsc.interactive.Global$TyperRun.<init>(Global.scala:1331)
	scala.tools.nsc.interactive.Global.newTyperRun(Global.scala:1354)
	scala.tools.nsc.interactive.Global.<init>(Global.scala:294)
	scala.meta.internal.pc.MetalsGlobal.<init>(MetalsGlobal.scala:40)
	scala.meta.internal.pc.ScalaPresentationCompiler.newCompiler(ScalaPresentationCompiler.scala:434)
```
#### Short summary: 

scala.reflect.internal.FatalError: 
  bad constant pool index: 0 at pos: 48445
     while compiling: <no file>
        during phase: globalPhase=<no phase>, enteringPhase=<some phase>
     library version: version 2.13.8
    compiler version: version 2.13.8
  reconstructed args: -classpath <WORKSPACE>\employes_management\.bloop\root\bloop-bsp-clients-classes\classes-Metals-BdbYDQc6T_KFUwp0iP8eJw==;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\sourcegraph\semanticdb-javac\0.9.9\semanticdb-javac-0.9.9.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.8\scala-library-2.13.8.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\scala\mongo-scala-driver_2.13\4.4.0\mongo-scala-driver_2.13-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalafx\scalafx_2.13\15.0.1-R20\scalafx_2.13-15.0.1-R20.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-reflect\2.13.8\scala-reflect-2.13.8.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\scala\mongo-scala-bson_2.13\4.4.0\mongo-scala-bson_2.13-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\mongodb-driver-reactivestreams\4.4.0\mongodb-driver-reactivestreams-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\bson\4.4.0\bson-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\mongodb\mongodb-driver-core\4.4.0\mongodb-driver-core-4.4.0.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\reactivestreams\reactive-streams\1.0.2\reactive-streams-1.0.2.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\projectreactor\reactor-core\3.2.22.RELEASE\reactor-core-3.2.22.RELEASE.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: EmptyTree
       tree position: <unknown>
            tree tpe: <notype>
              symbol: null
           call site: <none> in <none>

== Source file context for tree position ==

