import env.Weapon, env.WorldMap, env.Room
import characters.Hero, characters.Monster

object Main {
  def main(args: Array[String]): Unit = {
    test2()
  }

  def test2(): Unit = {
    val mapResource = getClass.getResource("/map.json").getPath
    WorldMap.load(mapResource)
    var room =WorldMap.initRoom("X0Y0")
    val hero = ivan()
    hero.enter(room)
    WorldMap.save(room)
    hero.enter(room)
  }

  def test1(): Unit = {
    val sword1 = new Weapon(name = "Magic Sword", baseDamage = 50)
    val sword2 = new Weapon(name = "Magic Sword", baseDamage = 50)

    val hero = ivan()

    val opponent = new Hero("Ivan")
    opponent.health = 500
    opponent.defense = 5
    opponent.luck = 50
    opponent.level = 5
    opponent.equip(sword2)

    hero.engage(opponent)
    println(opponent.health)
  }

  def ivan(): Hero = {
    val sword = new Weapon(name = "Magic Sword", baseDamage = 80)

    val hero = new Hero("Ivan")
    hero.health = 100
    hero.luck = 10
    hero.strength = 50
    hero.level = 1
    hero.defense = 40
    hero.equip(sword)

    return hero
  }
}
