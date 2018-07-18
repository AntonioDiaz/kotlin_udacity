import java.util.*

val rollDice = {slices:Int ->
    if (slices==0)
        0
    else
        Random().nextInt(slices*2) + 1;
}


fun rollDice2 (slices: Int) {
    if (slices==0)
        0
    else
        Random().nextInt(slices*2) + 1;
};

var rollDice2: (Int)-> Int = { slides ->
    if (slides==0) {
        0
    } else {
        Random().nextInt(slides) +1
    }
};



fun main(args: Array<String>) {
    repeat(100){
        println(rollDice(3))
    }
}
