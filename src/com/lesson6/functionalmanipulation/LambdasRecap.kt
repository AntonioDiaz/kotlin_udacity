package com.lesson6.functionalmanipulation

enum class Directions {
    NORTH, SOUTH, EAST, WEST, START, END
}

class Game {
    val path :MutableList<Directions> = mutableListOf(Directions.START)
    val north = { path.add(Directions.NORTH) }
    val south = { path.add(Directions.SOUTH) }
    val east = { path.add(Directions.EAST) }
    val west = { path.add(Directions.WEST) }
    val end: () -> Boolean = {
        path.add(Directions.END)
        println ("Game Over [${path.joinToString(" - ")}]")
        path.clear()
        false;
    }
}

fun main(args: Array<String>) {
    val game = Game()
    println(game.path)
    game.north()
    game.south()
    game.east()
    game.west()
    game.end()
    println(game.path)
}