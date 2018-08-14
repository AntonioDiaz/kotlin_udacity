package com.lesson2.basics

import java.util.*

fun main(args: Array<String>) {
    dayOfWeek()
}

fun dayOfWeek() {
    println("What day is it today?")
    val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    println( when (day) {
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> "Time has stopped"
    })
    feedTheFish()
    if (shouldChangeWater("Monday")) {
        println("Change the water today")
    }
    println(canAddFish(10.0, listOf(3, 3, 3)));
    println(canAddFish(8.0, listOf(2, 2, 2), hasDecorations = false));

    println(canAddFish(9.0, listOf(1, 1, 3), 3));
    println(canAddFish(10.0, listOf(), 7, true));

}

fun canAddFishOptimal (
        tankSize: Double,
        currentFish: List<Int>,
        fishSize: Int = 2,
        hasDecorations: Boolean = true): Boolean {
    return (tankSize * if (hasDecorations) 0.8 else 1.0) >= (currentFish.sum() + fishSize)
}


fun canAddFish(tankSize: Double,
                currentFish: List<Int>,
                fishSize: Int = 2,
                hasDecorations: Boolean= true) : Boolean {
    var totalFishSize:Double = 0.0;
    for (fish in currentFish) {
        totalFishSize += fish;
    }
    var newTankSize = tankSize;
    if (hasDecorations) {
        newTankSize*=.8;
    }
    return newTankSize >= totalFishSize + fishSize;
}

fun shouldChangeWater(day: String, temperature: Int = 22, dirty: Int = 20) : Boolean {
    return true
}


fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day);
    println("Today is $day and the fish food is $food")
}

fun randomDay(): String {
    val week = listOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    return week[Random().nextInt(7)]
}

fun fishFood(day: String) : String {
    return when (day) {
        "Monday" -> "flakes"
        else -> "mosquitoes"
    }
}

var dirty = 20;
var waterFilter = { dirty: Int -> dirty / 2}
fun feedFish(dirty: Int) = dirty + 10;


fun updateDirty(dirty: Int, operation: (Int) -> Int): Int {return operation(dirty)}

fun dirtyProcessor() {
    dirty = updateDirty(dirty, waterFilter)
    dirty = updateDirty(dirty, ::feedFish);
    dirty = updateDirty(dirty, { dirty -> dirty + 50 })
}

