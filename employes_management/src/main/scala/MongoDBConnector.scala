import org.mongodb.scala.{MongoClient, MongoDatabase}

object MongoDBConnector {
  private val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
  val database: MongoDatabase = mongoClient.getDatabase("employes_management")
}
