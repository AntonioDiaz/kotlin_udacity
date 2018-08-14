package com.lesson4.classes.spices

fun main(args: Array<String>) {

}

abstract class Spice (var name: String, private var spiceness:String = "mild") {
    val heat: Int
        get() {
            when(spiceness) {
                "mild" -> return 3
                else -> return 0
            }
        }
    abstract fun prepareSpice();
}

class Curry(name: String, spiceness:String): Spice(name, spiceness), Grinder {

    override fun prepareSpice() {

    }

    override fun grind() {

    }

}


interface Grinder {
    fun grind();
}