package env

import characters.Hero
import characters.Monster

class Room(name: String){
    var message : String = ""
    var exits = Map[String,Room]()
    var objects = Map[String,GameObject]()
    var monsters = List[Map[String,String]]()
    var monsters_obj = scala.collection.mutable.SortedMap[Int,Monster]()
    var room = Map[String,Any]()

    def getName(): String = {
        return name;
    }

    /**
      * Parse json into Monster object
      */
    def getMonsters(): Unit = {
        if (this.monsters_obj.isEmpty){
            var i = 1
            for (m <- this.monsters) {
                var monster = Monster.create(m("name"), m("type"), m("level").toInt)
                this.monsters_obj(i) = monster
                i+=1
            }
        }
    }
}