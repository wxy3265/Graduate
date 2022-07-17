package com.wxy3265.graduate

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.wxy3265.graduate.ui.login.LoginActivity
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.StringReader
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var id: String = ""
        var name: String = ""
        var birth: String = ""
        var phone: String = ""
        var qq: String = ""
        var wx: String = ""
        var mail: String = ""
        var wb: String = ""
        var location: String = ""
        var city: String = ""
        var school: String = ""
        Log.d("MainActivity", "start")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mbutton_log: Button = findViewById(R.id.mbutton_log)
        mbutton_log.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val mbutton_info: Button = findViewById(R.id.mbutton_info)
        mbutton_info.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
        val mbutton_send: Button = findViewById(R.id.mbutton_send)
        mbutton_send.setOnClickListener {
            val intent = Intent(this, SendpageActivity::class.java)
            startActivity(intent)
        }
        val dbHelper = MyDatabaseHelper(this, "GraduateStudents.db", 2)
        val db = dbHelper.writableDatabase
        val mbutton_db: Button = findViewById(R.id.mbutton_db)
        mbutton_db.setOnClickListener {
            dbHelper.writableDatabase
        }
//        网络通信
//        sendRequestWithOkHttp()
        db.delete("Student", null, null)
        var retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val appService = retrofit.create(Student.AppService::class.java)
        appService.getStuData().enqueue(object : Callback<List<Student>> {
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                val list = response.body()
                if (list != null) {
                    for (stu in list) {
                        id = stu.id
                        name = stu.name
                        Log.d("MainActivity", "name:$name")
                        birth = stu.birth
                        phone = stu.phone
                        qq = stu.qq
                        wx = stu.wx
                        mail = stu.mail
                        Log.d("MainActivity", "mail:$mail")
                        wb = stu.wb
                        location = stu.loc
                        city = stu.city
                        school = stu.school
                        val values_me = ContentValues().apply {
                            put("name", name)
                            put("birth", birth)
                            put("phone", phone)
                            put("qq", qq)
                            put("wx", wx)
                            put("mail", mail)
                            put("wb", wb)
                            put("loc", location)
                            put("city", city)
                            put("school", school)
                        }
                        db.insert("Student", null, values_me)
                    }
                }
            }
            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
//    废弃的网络通信demo
    /*private fun sendRequestWithOkHttp() {
        Log.d("MainActivity", "Go")
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.0.2.2/get_data.xml")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    Log.d("MainActivity", "not null")
                    parseXMLWithPull(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private  fun parseXMLWithPull(xmlData: String) {
        Log.d("MainActivity", "entry")
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            Log.d("MainActivity", "id is $id")
                            Log.d("MainActivity", "name is $name")
                            Log.d("MainActivity", "version is $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/
}