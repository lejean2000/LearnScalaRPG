package env
import org.json4s._
import org.json4s.jackson.JsonMethods._

object WorldMap{
    var map = Map[String,Any]()
    var rooms = scala.collection.mutable.Map[String,Room]()

    def load (path: String): Map[String,Any] = {
        val source = scala.io.Source.fromFile(path)
        val lines = try source.mkString finally source.close()
        implicit val formats = org.json4s.DefaultFormats
        WorldMap.map = parse(lines).extract[Map[String, Any]]
        return WorldMap.map
    }

    /** 
      * Parse room into room object
      * and save it in World Map
      * @param name
      * @return
      */
    def initRoom(name: String): Room = {
        if (!WorldMap.rooms.contains(name)){
            val roomdata = WorldMap.map(name).asInstanceOf[Map[String, Any]]
            var room = new Room(name)
            room.message = roomdata("message").asInstanceOf[String]
            room.monsters = roomdata("monsters").asInstanceOf[List[Map[String,String]]]
            room.getMonsters()
            WorldMap.save(room)
        }
        return WorldMap.rooms(name)
    }

    def save(room: Room): Unit = {
        WorldMap.rooms(room.getName()) = room
    }
}