
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
    - [Lambas (anonymous functions)](#lambas-anonymous-functions)
    - [Higher order functions (fun with fun arg)](#higher-order-functions-fun-with-fun-arg)
- [Lesson 3](#lesson-3)
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

#### Lambas (anonymous functions)


#### Higher order functions (fun with fun arg)



## Lesson 3

## Lesson 4

## Lesson 5