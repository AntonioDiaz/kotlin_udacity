import com.examplesimport.DownloadPodcastFromIvoox
import com.github.kittinunf.fuel.core.FuelManager
import org.junit.Test

import org.junit.Assert.*
import java.net.URL
import org.jsoup.Jsoup
import java.io.File


class DownloadPodcastFromIvooxTest {


    @Test
    fun generateUrlListTest() {
        val download = DownloadPodcastFromIvoox(DownloadPodcastFromIvoox.NUM_PAGES, DownloadPodcastFromIvoox.URL)
        val generateUrlList = download.generateUrlList()
        assertEquals(16, generateUrlList.size)
        assertEquals("https://www.ivoox.com/podcast-cultureta_sq_f1249913_1.html", generateUrlList[0])
        assertEquals("https://www.ivoox.com/podcast-cultureta_sq_f1249913_16.html", generateUrlList[15])
    }

    @Test
    fun getHtmlFromUrl(){
        val url = URL("https://www.ivoox.com/podcast-cultureta_sq_f1249913_16.html")
        val readText = url.readText()
        assertTrue(readText.isNotEmpty())
        assertTrue(readText.contains("Carlos Alsina"))
        val s = readText.split("\n").filter { it.contains("itemprop") }[0]

    }

    @Test
    fun jSoupTest() {
        val url = "https://www.ivoox.com/podcast-cultureta_sq_f1249913_16.html"
        val document = Jsoup.connect(url).get()

        val allMetas = document.select("meta")
        val element = allMetas.filter { it.attr("itemprop").equals("url") }

        assertEquals(15,element.size)
        assertEquals("https://www.ivoox.com/cultureta-bonus-track-la-mezcla-fantasia-audios-mp3_rf_9531766_1.html", element[0].attr("content"))

    }


    @Test
    fun selectDetailsTest(){
        val urlStr = "https://www.ivoox.com/podcast-cultureta_sq_f1249913_16.html"
        val selectAllDetailsUrl = DownloadPodcastFromIvoox(DownloadPodcastFromIvoox.NUM_PAGES, DownloadPodcastFromIvoox.URL).selectAllDetailsUrl(urlStr)
        assertEquals(15,selectAllDetailsUrl.size)
    }

    @Test
    fun selectDownloadUrl(){
        val url = URL("https://www.ivoox.com/cultureta-bonus-track-la-mezcla-fantasia-audios-mp3_rf_9531766_1.html")
        val readText = url.readText()
        assertTrue(readText.split("\n").isNotEmpty())
        val myLine = readText.split("\n").filter { it.contains("\$('.downloadlink').load('") }[0]
        assertTrue(myLine.isNotEmpty())
        assertEquals(5, myLine.split("'").size)
        assertEquals("downloadlink_mm_9531766_7_b_1.html?tpl2=ok", myLine.split("'")[3])
    }

    @Test
    fun translateDateFormatTest() {
        val dateInput = "28/11/2015"
        val newFormat:String = DownloadPodcastFromIvoox.translateDateFormat(dateInput)
        assertEquals("2015-11-28", newFormat)
    }

    @Test
    fun getMp3NameFromUrl() {
        val urlStr = "https://www.ivoox.com/cultureta-bonus-track-la-mezcla-fantasia-audios-mp3_rf_9531766_1.html"
        var title = DownloadPodcastFromIvoox.getMp3Title(urlStr)
        assertEquals("cultureta-bonus-track-la-mezcla-fantasia", title)
    }

    @Test
    fun selectMp3InfoTest(){
        val urlStr = "https://www.ivoox.com/cultureta-bonus-track-la-mezcla-fantasia-audios-mp3_rf_9531766_1.html"
        val url = java.net.URL(urlStr)
        val readText = url.readText()
        val document = Jsoup.parse(readText)
        assertNotNull(document)
        assertEquals("28/11/2015", document.select("span.icon-date").first().text())
        val (name, mp3Url) = DownloadPodcastFromIvoox.getMp3Info(urlStr)
        assertEquals("2015-11-28_cultureta-bonus-track-la-mezcla-fantasia.mp3", name)
        assertEquals("downloadlink_mm_9531766_7_b_1.html?tpl2=ok", mp3Url)
    }


    @Test
    fun selectUrlMp3() {
        val url = "https://www.ivoox.com/downloadlink_mm_9531766_7_b_1.html?tpl2=ok"
        val document = Jsoup.connect(url).get()
        val link = document.select("a").first()
        val urlMp3 = link.attr("href")
        assertEquals("https://www.ivoox.com/cultureta-bonus-track-la-mezcla-fantasia_md_9531766_1.mp3?t=lamiop6neKOnnw..", urlMp3)
    }

    @Test
    fun getAllUrlOfMp3Test_15(){
        val download = DownloadPodcastFromIvoox(DownloadPodcastFromIvoox.NUM_PAGES, DownloadPodcastFromIvoox.URL)
        val allUrlOfMp3 = download.getAllUrlOfMp3(15)
        assertEquals(20, allUrlOfMp3.size)
    }

    @Test
    fun getAllUrlOfMp3Test_16(){
        val download = DownloadPodcastFromIvoox(DownloadPodcastFromIvoox.NUM_PAGES, DownloadPodcastFromIvoox.URL)
        val allUrlOfMp3 = download.getAllUrlOfMp3(16)
        assertEquals(16, allUrlOfMp3.size)
        val (name, urlStr) = allUrlOfMp3.last()
        assertEquals(name, "2015-01-16_american-sniper-historia-del-francotirador-chris-kyle.mp3")
        assertEquals("https://www.ivoox.com/downloadlink_mm_8765770_35_b_1.html?tpl2=ok", urlStr);
    }

    @Test
    fun getAllUrlOfMp3Test_all(){
        val download = DownloadPodcastFromIvoox(DownloadPodcastFromIvoox.NUM_PAGES, DownloadPodcastFromIvoox.URL)
        val allUrlOfMp3 = download.getAllUrlOfMp3()
    }

    @Test
    fun getRedirectionTest() {
        val url = "https://www.ivoox.com/historias-del-valle-sin-retorno-dientes-ajo_md_9169204_1.mp3?t=lamio5WmdaOvpw..";
        val redirectionUrl = DownloadPodcastFromIvoox.getRedirection(url)
        assertTrue(redirectionUrl!!.isNotBlank())
    }

    @Test
    fun downloadFileTest() {
        val url = "https://www.ivoox.com/historias-del-valle-sin-retorno-dientes-ajo_md_9169204_1.mp3?t=lamio5Wmdqatow..";
        val redirectionUrl = DownloadPodcastFromIvoox.getRedirection(url)
        var filePath = "/Users/adiaz/Desktop/cultureta.mp3"
        var file = File(filePath)
        file.delete()
        assertFalse(file.exists())
        DownloadPodcastFromIvoox.downloadUsingStream(redirectionUrl!!, filePath)
        assertTrue(file.exists())
    }


    @Test
    fun downloadFileFuelTest() {
        val url = "https://www.ivoox.com/cultureta-5x05-cuando-reinaron-caballos-durante_md_29110302_1.mp3?t=lamipJiidqGrpw..";
        val redirectionUrl = DownloadPodcastFromIvoox.getRedirection(url)
        var filePath = "/Users/adiaz/Desktop/cultureta2.mp3"
        var file = File(filePath)
        file.delete()
        assertFalse(file.exists())
        DownloadPodcastFromIvoox.downloadFuel(redirectionUrl!!, filePath)
        assertTrue(file.exists())
    }

    @Test
    fun downloadMp3Test() {
        val download = DownloadPodcastFromIvoox(DownloadPodcastFromIvoox.NUM_PAGES, DownloadPodcastFromIvoox.URL)
        //download.downloadMp3(-1, "/Users/adiaz/Desktop/episodios/");
    }

    @Test
    fun testDownloadAsync() {
        println("start ....")
        val urlStr1 = "http://desprogresiva.antena3.com/mp_audios4//2015/10/23/C9177172-B3A1-4466-916A-538BBF82E39F/C9177172-B3A1-4466-916A-538BBF82E39F.mp3";
        val urlStr2 = "http://desprogresiva.antena3.com/mp_audios4//2015/10/23/C9177172-B3A1-4466-916A-538BBF82E39F/C9177172-B3A1-4466-916A-538BBF82E39F.mp3";

        val urlList = listOf<String>(urlStr1, urlStr2)

        var filePath = "/Users/adiaz/Desktop/cultureta.mp3"
        var dirPath = "/Users/adiaz/Desktop/"
        var file = File(filePath)
        file.delete();
        //assertFalse(file.exists())


        var read = -1L
        var total = -1L

        urlList.forEachIndexed { index, element ->
            println(dirPath + index)
            FuelManager().download(element).destination { _, _ ->  File(dirPath + index)}.progress { readBytes, totalBytes ->
                read = readBytes
                total = totalBytes
                println("read: $read, total: $total")

            }.response()
        }
        /* Fuel.download(urlStr).destination { response, url ->
             File("temp").apply { println(filePath) }
        }.response { req, res, result -> }*/

/*        Fuel.download(urlStr).destination { response, url ->
            File.createTempFile("temp", ".tmp")
        }.progress { readBytes, totalBytes ->
            val progress = readBytes.toFloat() / totalBytes.toFloat()
            println("progress $progress")
        }.response { req, res, result ->
            println("response $result")
        }*/
        //File.createTempFile("temp", ".tmp", file)
        //  .destination { response, url -> File("temp").apply { println(absolutePath) } }
    }

    @Test
    fun testCopyFiles() {
        var path = "/Users/adiaz/Desktop/laCultureta/"
        var pathTarget = "/Users/adiaz/Desktop/episodios/"
        assertEquals(305, File(path).list().size)
        var filterList = File(path).list().filter { it.contains("creeme") }
        assertEquals(5, filterList.size)
        filterList = File(path).list().filter { it.contains("_cultureta-") }
        filterList.forEach { File(path + it).copyTo(File (pathTarget + it), true) }
        assertEquals(filterList.size, File(pathTarget).list().size)
    }

    @Test
    fun testRenameFiles() {
        var path = "/Users/adiaz/Desktop/episodios/"
        File(path).list().forEach {
            val oldName = path + it
            val newName = path + it.replace("-039.",".")
            File(oldName).renameTo(File(newName))
        }
    }

    @Test
    fun testDeleteFiles() {
        val path = "/Users/adiaz/Desktop/episodios/"
        val text = "_monologo"
        File(path).list().filter { it.contains(text) }.forEach {
            File(path+it).delete()
        }
        assertEquals(0, File(path).list().filter { it.contains(text)}.size)
    }

    @Test
    fun testMoveFiles() {
        var path = "/Users/adiaz/Desktop/episodios/"
        var pathTarget = "/Users/adiaz/Desktop/añadidos/"
        var filterList = File(path).list().filter { it.contains("_anadido-pod") }
        filterList.forEach {
            File(path+it).copyTo(File(pathTarget+it), true)
            File(path+it).delete()
        }
    }



}