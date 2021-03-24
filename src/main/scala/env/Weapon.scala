package env

class Weapon(override val name: String, override val removable: Boolean = true, val baseDamage: Int) 
    extends GameObject(name, removable){

}