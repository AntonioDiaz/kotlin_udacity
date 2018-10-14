package com.examplesimport


import com.github.kittinunf.fuel.Fuel
import org.jsoup.Jsoup
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection

class DownloadPodcastFromIvoox (private var numPages:Int, private val urlStr: String ){



    //https://www.ivoox.com/podcast-cultureta_sq_f1249913_16.html
    //<a rel="nofollow" title="Reproducir:  La Cultureta: La historia de 'Whitey' Bulger, el padrino de la mafia en Boston"
    // onclick="$:location.href=&quot;https://www.ivoox.com/cultureta-la-historia-whitey-bulger-el-audios-mp3_rf_8765755_1.html?autoplay=true&quot;;">
    //<span>REPRODUCIR</span>
    //</a>

    //https://www.ivoox.com/cultureta-bonus-track-la-mezcla-fantasia_md_9531766_1.mp3?t=lamiop6meKSqow..
    //https://www.ivoox.com/cultureta-el-estilo-literario-sorkin-y_md_9459503_1.mp3?t=lamiop6meKSvpQ..
    //https://www.ivoox.com/historias-del-valle-sin-retorno-dientes-ajo_md_9169204_1.mp3?t=lamiop6meKWqoA..
    //https://www.ivoox.com/downloadlink_mm_9169204_3_b_1.html?tpl2=ok
    //https://www.ivoox.com/downloadlink_mm_28580331_42_b_1.html?tpl2=ok


    companion object {
        const val NUM_PAGES = 16
        const val URL = "https://www.ivoox.com/podcast-cultureta_sq_f1249913_xx.html"


        @Throws(IOException::class)
        fun downloadUsingStream(urlStr:String, file:String) {
            val url = java.net.URL(urlStr)
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

        fun downloadFuelAsync(urlStr: String, filePath:String) {
            Fuel.download(urlStr).destination { response, url ->
                println("termina ... $filePath")
                File.createTempFile("temp", ".tmp", File(filePath))
            }.progress { readBytes, totalBytes ->
                val progress = readBytes.toFloat() / totalBytes.toFloat()
                println("progress $progress: $urlStr")
            }.response { req, res, result ->
                println("response $urlStr -> ${res.contentLength}")
                println("response $urlStr -> ${res.contentLength}")
            }
        }
        fun downloadFuel(urlStr: String, filePath:String) {
            println("**urlStr $urlStr")
            println("**filePath $filePath")
            var percentaje = 0;
            Fuel.download(urlStr)
                    .destination { response, url -> File(filePath).apply {
                        println(absolutePath)
                    }
                    }.progress { readBytes, totalBytes ->
                        val progress = readBytes.toFloat() / totalBytes.toFloat()
                        if ((progress*100).toInt()>percentaje) {
                            percentaje = (progress*100).toInt()
                            println("$filePath -> $percentaje")

                        }
                        //println("out Progress $percentaje of $urlStr")
                    }
                    .responseString()
        }


        fun getRedirection(url:String): String? {
            val con = (java.net.URL(url).openConnection()) as HttpURLConnection
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            con.instanceFollowRedirects = false
            con.connect()
            return con.getHeaderField("Location")
        }

        fun selectDownloadUrl(url:String): String {
            val document = Jsoup.connect(url).get()
            val link = document.select("a").first()
            return link.attr("href")
        }

        /** from urlDetails gets file_name and mp3 download url.
         * Url example: https://www.ivoox.com/cultureta-bonus-track-la-mezcla-fantasia-audios-mp3_rf_9531766_1.html */
        fun getMp3Info(urlDetails: String):Pair<String, String> {
            val url = java.net.URL(urlDetails)
            val readText = url.readText()
            val document = Jsoup.parse(readText)
            val mp3Date = translateDateFormat(document.select("span.icon-date").first().text())
            val mp3Title = getMp3Title(urlDetails)
            val mp3DownloadFileLine = readText.split("\n").filter { it.contains("\$('.downloadlink').load('") }[0]
            val mp3DownloadUrlDetails = "https://www.ivoox.com/" + mp3DownloadFileLine.split("'")[3]
            val mp3DownloadUrl = selectDownloadUrl(mp3DownloadUrlDetails)
            val fileName = mp3Date + "_" + mp3Title + ".mp3"
            return Pair(fileName, mp3DownloadUrl)
        }

        fun translateDateFormat(dateInput: String): String {
            val dateSplit = dateInput.split("/")
            return dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0]
        }

        // url input --> https://www.ivoox.com/cultureta-bonus-track-la-mezcla-fantasia-audios-mp3_rf_9531766_1.html
        // title ouput --> cultureta-bonus-track-la-mezcla-fantasia
        fun getMp3Title(urlStr: String): String {
            var name = urlStr.replace("https://www.ivoox.com/", "")
            return name.replace(Regex("-audios.*"), "")
        }

    }

    fun generateUrlList(): List<String> {
        val list = mutableListOf<String>()
        repeat(numPages) {list.add(urlStr.replace("_xx.", "_${it+1}."))}
        return list
    }

    // find all details page
    // <meta itemprop="url" content="https://www.ivoox.com/cultureta-5x02-star-trek-steven-pinker-audios-mp3_rf_28580331_1.html">
    fun selectAllDetailsUrl(urlStr: String):List<String> {
        println("getting url $urlStr")
        val document = Jsoup.connect(urlStr).get()
        val allMetas = document.select("meta")
        return allMetas.filter { it.attr("itemprop") == "url" }.map { it.attr("content") }
    }



    fun getAllUrlOfMp3(page:Int=-1):List<Pair<String, String>> {
        val allDetails = mutableListOf<String>()
        if(page==-1)
            generateUrlList().forEach {
                allDetails.addAll(selectAllDetailsUrl(it))
            }
        else
            allDetails.addAll(selectAllDetailsUrl(generateUrlList()[page -1]))
        return allDetails.map { getMp3Info(it) }
    }

    fun downloadMp3(page:Int=-1, path:String, titleSearch:String="") {
        val allUrlOfMp3 = getAllUrlOfMp3(page)
        println("allUrl " + allUrlOfMp3)
        allUrlOfMp3.forEach {
            val mp3Path = path + it.first
            if (it.first.contains("historias-del-valle") || it.first.contains("monologo-alsina")) {
                println("SKIPING $mp3Path")
            } else {
                if (!File(mp3Path).exists() && mp3Path.contains(titleSearch)) {
                    val redirectionUrl = DownloadPodcastFromIvoox.getRedirection(it.second)
                    if (redirectionUrl != null) {
                        println("mp3Path [$mp3Path]")
                        DownloadPodcastFromIvoox.downloadFuel(redirectionUrl, mp3Path)
                    }
                } else {
                    println("EXISTS file : ${mp3Path}")
                }
            }
        }
    }
}