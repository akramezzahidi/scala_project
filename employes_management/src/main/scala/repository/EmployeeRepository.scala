package repository

import org.mongodb.scala.MongoClient
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Projections.excludeId
import org.mongodb.scala.result.UpdateResult
import org.mongodb.scala.model.UpdateOptions
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.{Document, SingleObservable}

import scala.concurrent.{ExecutionContext, Future}

class EmployeeRepository(client: MongoClient)(implicit ec: ExecutionContext) {
  private val database: MongoDatabase = client.getDatabase("employes_management")
  private val collection = database.getCollection("employes_management")

  def getAllEmployees(): Future[Seq[Document]] = {
    collection.find().toFuture()
  }

  def getEmployeeById(id: String): Future[Option[Document]] = {
    collection.find(equal("_id", id)).first().headOption()
  }

  def addEmployee(employee: Document): Future[Unit] = {
    collection.insertOne(employee).toFuture().map(_ => ())
  }

  def updateEmployee(id: String, updatedEmployee: Document): Future[Boolean] = {
    val update = combine(
      set("name", updatedEmployee.getString("name")), 
      set("position", updatedEmployee.getString("position")), 
      set("salary", updatedEmployee.getDouble("salary")) 
    )
    val options = new UpdateOptions().upsert(true)
    collection.updateOne(equal("_id", id), update, options).toFuture().map(_.wasAcknowledged())
  }

  def deleteEmployee(id: String): Future[Boolean] = {
    collection.deleteOne(equal("_id", id)).toFuture().map(_.wasAcknowledged())
  }
}
