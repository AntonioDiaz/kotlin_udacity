package com.lesson6.functionalmanipulation

data class Fish (var name: String)

fun main(args: Array<String>) {
    fishExamples()
}

fun fishExamples() {
    val fish = Fish("splashy")

    with(fish.name) {
        //this.capitalize()
        capitalize()
    }

    myWith(fish.name) {
        print(capitalize())
    }
}

fun myWith(name: String, block: String.() -> Unit) {
    name.block()
}