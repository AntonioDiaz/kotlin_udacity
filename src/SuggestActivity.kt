fun main(args: Array<String>) {

}

fun whatShouldIDoToday(mood: String, weather: String = "sunny", temperture: Int = 24): String {
    return if (mood == "happy" && weather == "Sunny")
         "go for a walk"
    else
         "Stay home and read."
}