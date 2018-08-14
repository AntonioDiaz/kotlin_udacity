package com.examples

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.examples.DownloadPodcast.Companion.NEXT_PUB_DATE_PUNTA_NORTE
import com.examples.DownloadPodcast.Companion.URL_PUNTA_NORTE
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DownloadPodcast {

    companion object {
        const val NEXT_PUB_DATE_PUNTA_NORTE = "1531771000000"
        const val URL_PUNTA_NORTE =
                "https://www.listennotes.com/endpoints/v1/channels/c0c81141dba847cab0f66ab1bf1524d9/episodes?next_pub_date=$NEXT_PUB_DATE_PUNTA_NORTE&prev_pub_date=1532720364000&sort_type=recent_first"

        public fun getJson(url: String):JsonObject {
            val url = URL(url)
            val connection = url.openConnection()
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            return Parser().parse(connection.getInputStream()) as JsonObject
        }

        @Throws(IOException::class)
        fun downloadUsingStream(urlStr:String, file:String) {
            val url = URL(urlStr)
            val bis = BufferedInputStream(url.openStream())
            val fis = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var count = 0
            count = bis.read(buffer, 0, 1024)
            while (count != -1) {
                fis.write(buffer, 0, count)
                count = bis.read(buffer, 0, 1024)
            }
            fis.close()
            bis.close()
        }

        fun getRedirection(url:String): String? {
            val con = (URL(url).openConnection()) as HttpURLConnection
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            con.instanceFollowRedirects = false
            con.connect()
            return con.getHeaderField("Location")
        }
    }
}

fun main(args : Array<String>) {
    val PATH_TO_DOWNLOAD = "/Users/adiaz/Desktop/punta_norte/";
    var nextPubDate = NEXT_PUB_DATE_PUNTA_NORTE
    do {
        val json = DownloadPodcast.getJson(URL_PUNTA_NORTE.replace(NEXT_PUB_DATE_PUNTA_NORTE, nextPubDate))
        val message = json.get("bundle") as JsonObject
        println(message.get("next_pub_date"))
        nextPubDate = message.get("next_pub_date").toString()
        val jsonArray = message.get("episodes") as JsonArray<JsonObject>
        jsonArray.forEach { element ->
            val parse = LocalDate.parse(element.get("pub_date_iso").toString(), DateTimeFormatter.ISO_DATE_TIME)
            val fileName = PATH_TO_DOWNLOAD + parse.toString() + "_" + element.get("title").toString() + ".mp3"
            if (!File(fileName).exists()) {
                val urlAudio = element.get("audio_play_url").toString()
                val urlRedirect = DownloadPodcast.getRedirection(urlAudio)
                DownloadPodcast.downloadUsingStream(urlRedirect!!, fileName)
            }
        }
    } while (jsonArray.size>0)
}
