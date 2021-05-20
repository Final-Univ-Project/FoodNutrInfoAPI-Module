package kr.hs.mealapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // API 식품 영양정보 요청 URL
        val reqURL = "http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/getFoodNtrItdntList"
        // API 키값
        val ServiceKey = ""

        // 식품이름
        val descKor = "바나나칩"
        // 페이지 번호
        val pageNo = 1
        // 한페이지 결과수
        val numOfRows = 3
        // 구축년도
        val bgnYear = 2017
        // 가공업체
        val plant = "(유)돌코리아"

        var urlBuilder: StringBuilder = StringBuilder(reqURL)
        urlBuilder.append("?${URLEncoder.encode("ServiceKey", "UTF-8")}=${ServiceKey}")
        urlBuilder.append("&${URLEncoder.encode("desc_kor", "UTF-8")}=${descKor}")
        urlBuilder.append("&${URLEncoder.encode("pageNo", "UTF-8")}=${pageNo}")
        urlBuilder.append("&${URLEncoder.encode("numOfRows", "UTF-8")}=${numOfRows}")
        urlBuilder.append("&${URLEncoder.encode("bgn_year", "UTF-8")}=${bgnYear}")
        urlBuilder.append("&${URLEncoder.encode("animal_plant", "UTF-8")}=${plant}")


        val url: URL = URL(urlBuilder.toString())
        var conn: HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Content-type", "application/json")

        println(" - Response code: ${conn.responseCode}")

        var rd: BufferedReader
        if (conn.responseCode >= 200 && conn.responseCode <= 300) {
            rd = BufferedReader(InputStreamReader(conn.inputStream))
        } else {
            rd = BufferedReader(InputStreamReader(conn.inputStream))
        }

        var sb: StringBuilder = StringBuilder()
        var line: String
        line = rd.readLine()
        while (line != null) {
            sb.append(line)
            line = rd.readLine()
        }

        rd.close()
        conn.disconnect()

        println(sb.toString())
    }
}