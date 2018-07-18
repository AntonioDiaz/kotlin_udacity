fun main(args: Array<String>) {
    for (i in 0..9) {
        val birthday = getBirthday()
        val myCookie = getFortuneCookie(birthday);
        println("$i your fortune is $myCookie");
        if (myCookie=="Take it easy and enjoy life!")
            return;
    }
    randomDay()
}

fun getBirthday() : Int {
    print("Enter your birthday: ")
    return readLine()?.toIntOrNull()?:1
}

fun getFortuneCookie(birthday:Int): String {
    val fortunes = listOf<String>(
            "You will have a great day!",
            "Things will go well for you today.",
            "Take it easy and enjoy life!")
    return when(birthday) {
        in 0..7 -> "first week"
        28,31 -> "end month"
        else -> fortunes[birthday%fortunes.size]
    }
}
