package models

import org.mongodb.scala.Document
import org.mongodb.scala.bson.ObjectId

case class Employee(id: String, name: String, position: String, salary: Double) {
  def asBson: Document = {
    Document("_id" -> id, "name" -> name, "position" -> position, "salary" -> salary)
  }
}

object Employee {
  def apply(name: String, position: String, salary: Double): Employee =
    Employee(new ObjectId().toString, name, position, salary)

  def asEmployee(document: Document): Employee = {
    Employee(
      id = document.getString("_id"),
      name = document.getString("name"),
      position = document.getString("position"),
      salary = document.getDouble("salary")
    )
  }
}
