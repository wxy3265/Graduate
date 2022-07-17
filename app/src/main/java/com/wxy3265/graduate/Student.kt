package com.wxy3265.graduate

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


class Student(val id: String, val name: String, val birth: String, val phone: String,
                val qq: String, val wx: String, val mail: String, val wb: String,
                val loc: String, val city: String, val school: String) {
    interface AppService {
        @GET("get_data.json")
        fun getStuData(): Call<List<Student>>
    }
    interface HttpService {
        @POST
        fun postStuData(): Call<List<Student>>
    }
}