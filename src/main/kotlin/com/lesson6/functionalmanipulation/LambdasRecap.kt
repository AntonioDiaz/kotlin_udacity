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
    fun move (where: () -> Boolean) {
        where.invoke()
    }
    fun makeMove(directionStr: String?) {
        when (directionStr) {
            "N" -> move(north)
            "S" -> move(south)
            "E" -> move(east)
            "W" -> move(west)
            else -> move(end)
        }
    }
}

fun main(args: Array<String>) {
    val game = Game()
    /*
    println(game.path)
    game.north()
    game.south()
    game.east()
    game.west()
    game.end()
    println(game.path)
    */
    while (true) {
        print("Enter a direction: N/S/E/W: ")
        game.makeMove(readLine())
        //println(game.map.getDescription())
    }
}