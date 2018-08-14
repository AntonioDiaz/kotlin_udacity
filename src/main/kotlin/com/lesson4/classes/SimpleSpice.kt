package com.lesson4.classes

class Spice (var name: String, private var spiceness:String = "mild") {
    val heat: Int
        get() {
            return when(spiceness) {
                "mild" -> 3
                else -> 0
            }
        }
}

class SimpleSpice {
    var name: String = "curry"
    var spiceness = "mild"
    private val heat: Int
        get() {
            when(spiceness) {
                "hot" ->
                    return 5
                "mild" ->
                    return 3
                else ->
                    return 0
            }
        }
}

fun main(args: Array<String>) {
    var mySimpleSpice = SimpleSpice();
    println("name -> ${mySimpleSpice.name}")
    println("spiceness -> ${mySimpleSpice.spiceness}")

    var listOfSpiceFood = listOf<Spice>(Spice("pepino"), Spice("tomate", "hot"))
    println("list before:")
    for (e in listOfSpiceFood) {
        println("food: " + e.name)
    }

    val listFiltered = listOfSpiceFood.filter { it.heat <= 0 }
    println("list after filter:")
    for (e in listFiltered) {
        println("food: " + e.name)
    }


}