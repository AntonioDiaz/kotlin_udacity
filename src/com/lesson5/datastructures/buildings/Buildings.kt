package com.lesson5.datastructures.buildings

open class BaseBuildingMaterial(val numberNeeded:Int=1){

}

class Wood: BaseBuildingMaterial(4)
class Brick: BaseBuildingMaterial(8)
class Building<T: BaseBuildingMaterial>(val myMaterial: T) {
    val baseMaterialsNeeded = 100;
    val actualsMaterialNeeded = myMaterial.numberNeeded * baseMaterialsNeeded;
    fun build() = println(" $actualsMaterialNeeded ${myMaterial::class.simpleName} required")
}

fun main(args: Array<String>) {
    val wood = Wood()
    println("->" + wood.numberNeeded)

    var building = Building<BaseBuildingMaterial>(wood)
    building.build()
}