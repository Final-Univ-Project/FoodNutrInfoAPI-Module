package kr.hs.mealapi

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MealAPI {
    // API 식품 영양정보 요청 URL
    val reqURL = "http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/getFoodNtrItdntList"
    // API 키값
    val ServiceKey = ""

    // 식품 영양정보 API 요청 데이터 출력결과
    fun getData(descKor: String, pageNo: Int, numOfRows: Int, bgnYear: Int, plant: String): StringBuilder {
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
        return sb
    }
}