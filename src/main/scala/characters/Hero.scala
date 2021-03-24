package characters

import env.Weapon
import env.Room

class Hero(name: String) extends Creature(name){

    var experience = 0
    var weapons = scala.collection.mutable.Map[String,Weapon]() 

    override def calcStrikeDamage(opponent: Creature): Int = {
        val r = scala.util.Random

        if (this.block(opponent)==0) return 0

        var baseDamage = this.weapons("main").baseDamage
        var damage = baseDamage + baseDamage*(r.nextInt(this.level) - r.nextInt(opponent.level))/100

        // adjust damage with luck
        var rnd = r.nextFloat()
        if (rnd<this.luck.toFloat/100) {
            print("Luck!!! ")
            damage = damage + damage*r.nextInt(50)/100
        }

        // adjust damage with strength
        return damage + damage*(this.strength-opponent.defense)/100
    }

    def equip(weapon: Weapon):Unit = {
        this.weapons("main") = weapon
    }

    def enter(room: Room):Unit = {
        println(room.message+"\n")
        for ((i, monster) <- room.monsters_obj) {

            println("There is a %s%s in the room. ".format(if (monster.dead) "dead " else "", monster.name))
            if (!monster.dead) {
                if (monster.message!="") println(monster.message)

                var monsterHealth = 0
                if (monster.aggresiveness) {
                    monsterHealth = monster.health
                    monster.engage(this)
                    //level up if you are not dead
                    if (this.health>0) this.levelUp(monsterHealth)
                }
            }
        }
    }

    def levelUp(additionalExp: Int): Unit = {
        this.experience += additionalExp
        if (this.experience>=100*this.level){
            this.level+=1
            this.health = this.level * 100
            this.luck += 1
            this.strength += 10
            this.defense += 5
            println(s"You are now level ${this.level}\n")
        }
    }
}