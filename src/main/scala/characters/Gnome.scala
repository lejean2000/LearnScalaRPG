package characters

package characters

class Gnome(override val name: String) extends Monster(name){
    override val aggresiveness: Boolean = true

    override def init(level: Int): Unit = {
        super.init(level)
        this.luck = 0
        this.strength = 8 * level
        this.health = 50 * level
        this.damage = 10 * level
        this.blockChance = 20
    }

}