package characters

class FlyingDragon(override val name: String) extends Monster(name){
    var airDamage = 0
    override val message: String = "The dragon is looking at you!"

    override def init(level: Int): Unit = {
        super.init(level)
        this.strength = 12 * level
        this.health = 80 * level
        this.damage = 12 * level
        this.airDamage = 5 * level
    }

    override def calcStrikeDamage(opponent: Creature): Int = {
        var baseDamage = super.calcStrikeDamage(opponent)
        // add air damage
        baseDamage += this.airDamage
        return baseDamage
    }
}