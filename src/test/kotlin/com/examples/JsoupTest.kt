package com.adiaz

import org.jsoup.Jsoup
import org.junit.Test

class JsoupTest {

    @Test
    fun shouldParseHTML() {
/*        //1. Fetching the HTML from a given URL
        Jsoup.connect("https://www.google.co.in/search?q=petardas").get().run {
            //2. Parses and scrapes the HTML response
            select("div.rc").forEachIndexed { index, element ->
                val titleAnchor = element.select("h3 a")
                val title = titleAnchor.text()
                val url = titleAnchor.attr("href")
                //3. Dumping Search Index, Title and URL on the stdout.
                println("$index. $title ($url)")
            }
        }*/
    }


    @Test
    fun shouldParsePodcast() {
/*        Jsoup.connect(DownloadPodcast.URL_PODCAST).get().run {
            val elementsByAttributeValue = getElementsByAttributeValue("data-type", "episode-audio-player")
            println("elements found " + elementsByAttributeValue.size)
            elementsByAttributeValue.forEachIndexed { index, element ->
                println("index --> $index")
                println(element.attr("data-audio"))
                DownloadPodcast().downloadUsingStream(element.attr("data-audio"), "/Users/adiaz/Desktop/$index.mp3" )
            }
        }*/
    }
}