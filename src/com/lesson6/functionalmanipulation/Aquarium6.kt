package com.lesson6.functionalmanipulation

data class Fish (var name: String)

fun main(args: Array<String>) {
    fishExamples()
}

fun fishExamples() {
    val fish = Fish("splashy")

    with(fish.name) {
        capitalize() //it's the same than --> this.capitalize()
    }

    myWith(fish.name) {
        println(capitalize())
    }

    println(fish.run { name /* same than this.name */ })
    println(fish.apply { })

    //apply return a fish
    val fish2 = Fish("splashy").apply { name = name.capitalize() }
    println(fish2.name)

    //let return a copy of the change object.
    println(fish
            .let { it.name.capitalize() }
            .let { it + "fish" }
            .let { it.length }
            .let { it + 31 })

}

fun myWith(name: String, block: String.() -> Unit) {
    name.block()
}