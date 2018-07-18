
# Kotlin Bootcamp for Programmers by Google
    
  http://classroom.udacity.com/courses/ud9011/
  
  This reference summarizes the topics covered in the Kotlin Bootcamp course in the form of code snippets.
  
  See the Kotlin Language Documentation for full reference. 
  
  See the Kotlin Koans for more snippets to practice with. 
  
  See the Kotlin Bootcamp course if you need anything explained. 

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Contents**

- [Lesson 0: Introduction](#lesson-0-introduction)
- [Lesson 1: Kotlin Basics](#lesson-1-kotlin-basics)
    - [Hello Kotlin function](#hello-kotlin-function)
    - [Operators](#operators)
    - [Type conversion](#type-conversion)
    - [Number formatting](#number-formatting)
    - [Nullability](#nullability)
    - [Strings / String Templates](#strings--string-templates)
    - [if/else](#ifelse)
    - [when](#when)
    - [listOf / mutableListOf](#listof--mutablelistof)
    - [arrayOf / mutableArrayOf / intArray?](#arrayof--mutablearrayof--intarray)
    - [for loop](#for-loop)
- [Lesson 2: Functions](#lesson-2-functions)
    - [Functions](#functions)
    - [Compact Functions](#compact-functions)
    - [Filters](#filters)
    - [Lambas (anonymous functions)](#lambas-anonymous-functions)
    - [Higher order functions (fun with fun arg)](#higher-order-functions-fun-with-fun-arg)
- [Lesson 3: Classes](#lesson-3-classes)
    - [class](#class)
    - [Visibility](#visibility)
    - [Inheritance](#inheritance)
    - [Abstract classes](#abstract-classes)
    - [Interfaces](#interfaces)
    - [Data Classes](#data-classes)
    - [Composition](#composition)
    - [Singleton / object](#singleton--object)
    - [enum](#enum)
- [Lesson 4](#lesson-4)
- [Lesson 5](#lesson-5)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->


## Lesson 0: Introduction

- Install JDK if you don't have it
- Link to downloading  IntelliJ
- Starting the interpreter: Tools > Kotlin > Kotlin REPL

## Lesson 1: Kotlin Basics

#### Hello Kotlin function

```kotlin
fun printHello () {
    println ("Hello Kotlin")
}
```

```
printHello()
//Hello Kotlin program
```

```kotlin
fun main (args: Array<String>) {
   println("Hello ${args[0]} ")
}
```
#### Operators
- *, fish.times(6)
- /, fish.div(10)
- +, fish.plus(3)
- -, fish.minus(3)
      
#### Type conversion 
```kotlin
1.toLong()
1.toString()
```
#### Number formatting
```kotlin
val oneMillion = 1_000_000
val socialSecurityNumber = 999_99_9999L
val (immutable) & var (mutable)
```

```kotlin
val aquarium = "my aquarium"
var fish = 50
var snails : Int = 12
```


#### Nullability
```kotlin
var rocks: Int = null //Error
var marbles: Int? = null
fishFoodTreats?.dec()

var lotsOfFish: List<String?> = listOf(null, null)

return fishFoodTreats?.dec() ?:0
goldfish!!.eat
```
#### Strings / String Templates
```kotlin
"hello" + "fish" + "!"
"I have $numberOfFish fish"
"Print ${ numberOfFish + 5 } fish"
"fish" == "fish"
val message = "You are ${ if (length < 5) "fried" else "safe" } fish"
```

#### if/else
```kotlin
if (numberOfFish > numberOfPlants) {     
   println("Good ratio!") 
} else {
    println("unhealthy ratio")
}
```

```kotlin
if (fish in 1..100) println(fish)
``


```kotlin
val isHot = if (temperature > 90) true else false
```
 
#### when
```kotlin
when (numberOfFish) { 
  0  -> println("Empty tank") 
  in 1..50 -> println("Got fish!") 
  else -> println("Perfect!") 
}
```

#### listOf / mutableListOf
```kotlin
val myList = 
   mutableListOf("tuna",,"shark")
```


```kotlin
myList.remove("shark") // OK!
```


```kotlin
val swarm = listOf(fish, plants)
```

#### arrayOf / mutableArrayOf / intArray?
```kotlin
val school = arrayOf("tuna","salmon","shark")
```
```kotlin
val mix = arrayOf("fish", 2)
```
```kotlin
println(Arrays.toString(intArrayOf(2, "foo")))

val bigSwarm = arrayOf(swarm, arrayOf("dolphin","whale","orka"))

val array = Array (5) { it * 2 }
```

#### for loop
```kotlin
for (element in swarm) {...}
for ((index, element) in swarm.withIndex()) {
    println("Fish at $index is $element")
}

for (i in 'b'..'g') print(i)
 
for (i in 1..5) print(i) 

for (i in 5 downTo 1) print(i)

for (i in 3..6 step 2) print(i) // Prints: 35
```        






## Lesson 2: Functions
#### Functions
```kotlin
fun randomDay(): String {return "Monday"}

fun fishFood (hour: Int, day: String = "Tuesday"): String {}

fun isTooHot(temperature: Int): Boolean = temperature > 30
```

#### Compact Functions
```kotlin
fun isTooHot(temperature: Int) = temperature > 30

fun shouldChangeWater (day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    return when {
       isTooHot(temperature)-> true
       else  -> false
    }
}

fun getDirtySensorReading() = return 20

fun shouldChangeWater (day: String, temperature: Int = 22, dirty: Int = getDirtySensorReading()) {...}
```

#### Filters
```kotlin
println( decorations.filter {it[0] == 'p'})
```

#### Lambas (anonymous functions)
```kotlin
{ println("Hello") }()
val waterFilter = { dirty: Int -> dirty / 2 }
val waterFilter : (Int) -> Int = { dirty -> dirty / 2 }
```

#### Higher order functions (fun with fun arg)
```kotlin
fun updateDirty(dirty: Int, operation: (Int) -> Int): Int {
   return operation(dirty)
}
updateDirty(50, ::increaseDirty)
```


## Lesson 3: Classes
#### class
```kotlin
class Aquarium(var length: Int = 100, var width: Int = 20, var height: Int = 40) {


constructor(numOfFish: Int): this() {

    init {
     // do stuff
    }

    val volume: Int 
       get() {
         return w * h * l / 1000
       }

    init {
       // do stuff with volume
    }
}
```
#### Visibility
package:
- public - default. Everywhere
- private - file
- internal - module

class:
- sealed - only subclass in same file

inside class: 
- public - default. Everywhere.
- private - inside class, not subclasses
- protected - inside class and subclasses
- internal - module

#### Inheritance
```kotlin
open class Aquarium ?.. {
    open var water = volume * 0.9
    open var volume
}
```

```kotlin
class TowerTank (): Aquarium() {
    override var volume: Int
    get() = (w * h * l / 1000 * PI).toInt()
    set(value) {
       h = (value * 1000) / (w * l)
    }
}
```

#### Abstract classes
```kotlin
abstract class AquariumFish {
   abstract val color: String
}

class Shark: AquariumFish() {
    override val color = "gray"
}

class Plecostomus: AquariumFish() {
    override val color = "gold"
}
```

#### Interfaces
```kotlin
interface FishAction  {
   fun eat()
}


class Shark: AquariumFish(), FishAction {
   override val color = "gray"
   override fun eat() {
       println("hunt and eat fish")
   }
}

fun feedFish(fish: FishAction) {
   // make some food then
    fish.eat()
}
```

#### Data Classes
```kotlin
data class Decorations(val rocks: String, val wood: String, val diver: String){ }

val d = Decorations("crystal", "wood", "diver")
val (rock, wood, diver) = d

dataClassInstance1.equals(dataClassInstance2)
val dataClassInstance3.copy(dataClassInstance2)
```


#### Composition
```kotlin
fun main (args: Array<String>) {
   delegate()
}

fun delegate() {
   val pleco = Plecostomus()
   println("Fish has has color ${pleco.color}")
   pleco.eat()
}

interface FishAction {
   fun eat()
}

interface FishColor {
   val color: String
}

object GoldColor : FishColor {
   override val color = "gold"
}

class PrintingFishAction(val food: String) : FishAction {
   override fun eat() {
       println(food)
   }
}

class Plecostomus (fishColor: FishColor = GoldColor):
       FishAction by PrintingFishAction("eat a lot of algae"),
       FishColor by fishColor
```

#### Singleton / object
```kotlin
object MobyDickWhale {
    val author = "Herman Melville"
}
```


#### enum
```kotlin
enum class Color(val rgb: Int) {
   RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
}
Color.RED
```

## Lesson 4

## Lesson 5