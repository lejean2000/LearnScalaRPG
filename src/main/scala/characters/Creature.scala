package characters

import scala.util.control.Breaks._

class Creature(val name: String = ""){
    var luck: Int = 0
    var level: Int = 1
    var strength: Int = 0
    var defense: Int = 0
    var health: Int = 1
    var damage: Int = 1
    var blockChance: Int = 0
    var dead: Boolean = false

    def calcStrikeDamage(opponent: Creature): Int = ???

    def engage(opponent: Creature):Unit = {
        if (opponent.health<=0){
            println(f"${this.name} is looking at your corpse.")
        }
        breakable{
            while (this.health > 0 && opponent.health > 0){
                // bash each other until one dies
                this.strike(opponent)

                if (opponent.health > 0) opponent.strike(this)
            }
        }
    }

    def strike (opponent: Creature):Unit = {
        var strikeDamage = this.calcStrikeDamage(opponent)
        opponent.health -= strikeDamage
        print(f"${this.name} strikes for $strikeDamage damage. ")
        if (opponent.health <= 0) {
            println(f"${opponent.name} has died.\n")
            opponent.dead = true
        }
        else
            println(f"${opponent.name} health is ${opponent.health}.")
    }

    def block(opponent: Creature): Int = {
        //chance to block
        val r = scala.util.Random
        var rnd = r.nextFloat()
        if (rnd<opponent.blockChance.toFloat/100) {
            print("Block!!! ")
            return 0
        }
        return -1
    }
}