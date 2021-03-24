package characters

import _root_.characters.characters.Gnome

class Monster(override val name: String) extends Creature(name){

    val aggresiveness = true
    val message = ""

    def init(level: Int): Unit = {
        this.luck = 5
        this.level = level
        this.strength = 10 * level
        this.defense = 10 * level
        this.health = 50 * level
        this.damage = 20 * level
    }

    override def calcStrikeDamage(opponent: Creature): Int = {
        val r = scala.util.Random

        //chance to block
        if (this.block(opponent)==0) return 0

        var baseDamage = this.damage + this.damage*(r.nextInt(this.level) - r.nextInt(opponent.level))/100

        // adjust damage with luck
        var rnd = r.nextFloat()
        if (rnd<this.luck.toFloat/100) {
            print("Luck!!! ")
            baseDamage += baseDamage*r.nextInt(50)/100
        }

        // adjust damage with strength
        return baseDamage + baseDamage*(this.strength-opponent.defense)/100
    }
}

object Monster {
    def create(name: String, monsterType: String, level: Int): Monster = {
        var monster = Monster.createByType(name, monsterType)
        monster.init(level)
        return monster
    }

    def createByType(name: String, monsterType: String): Monster = monsterType match {
        case "FlyingDragon" => new FlyingDragon(name)
        case "Gnome" => new Gnome(name)
        case _ => new Monster(name)
    }
}