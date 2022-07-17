package com.wxy3265.graduate

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class InfoActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
//        定义数据库
        val dbHelper = MyDatabaseHelper(this, "GraduateStudents.db", 2)
        val db = dbHelper.writableDatabase
        val cursor = db.query("Student", null, null, null, null, null, null, null)
//        定义变量
        var edit: Boolean = false
        var name: String = "未填写"
        var birth: String = "未填写"
        var phone: String = "未填写"
        var qq: String = "未填写"
        var wx: String = "未填写"
        var mail: String = "未填写"
        var wb: String = "未填写"
        var location: String = "未填写"
        var city: String = "未填写"
        var school: String = "未填写"
//       读取数据库
        if (cursor.moveToFirst()) {
            do {
                Log.d("ReadData", "start Read")
                Log.d("ReadData", cursor.getColumnIndex("name").toString())
                name = cursor.getString(cursor.getColumnIndex("name"))
                Log.d("ReadData", cursor.getColumnIndex("birth").toString())
                birth = cursor.getString(cursor.getColumnIndex("birth"))
                Log.d("ReadData", cursor.getColumnIndex("phone").toString())
                phone = cursor.getString(cursor.getColumnIndex("phone"))
                Log.d("ReadData", cursor.getColumnIndex("qq").toString())
                qq = cursor.getString(cursor.getColumnIndex("qq"))
                Log.d("ReadData", cursor.getColumnIndex("wx").toString())
                wx = cursor.getString(cursor.getColumnIndex("wx"))
                Log.d("ReadData", cursor.getColumnIndex("mail").toString())
                mail = cursor.getString(cursor.getColumnIndex("mail"))
                Log.d("ReadData", cursor.getColumnIndex("wb").toString())
                wb = cursor.getString(cursor.getColumnIndex("wb"))
                Log.d("ReadData", cursor.getColumnIndex("loc").toString())
                location = cursor.getString(cursor.getColumnIndex("loc"))
                Log.d("ReadData", cursor.getColumnIndex("city").toString())
                city = cursor.getString(cursor.getColumnIndex("city"))
                Log.d("ReadData", cursor.getColumnIndex("school").toString())
                school = cursor.getString(cursor.getColumnIndex("school"))
            } while (cursor.moveToNext())
        }
        cursor.close()
        var oname = name
//        定义控件
        val info_name: EditText = findViewById(R.id.info_name)
        val info_birth: EditText = findViewById(R.id.info_birth)
        val info_phone: EditText = findViewById(R.id.info_phone)
        val info_qq: EditText = findViewById(R.id.info_qq)
        val info_wx: EditText = findViewById(R.id.info_wx)
        val info_mail: EditText = findViewById(R.id.info_mail)
        val info_wb: EditText = findViewById(R.id.info_wb)
        val info_location: EditText = findViewById(R.id.info_location)
        val info_city: EditText = findViewById(R.id.info_city)
        val info_school: EditText = findViewById(R.id.info_school)
        val info_tname: TextView = findViewById(R.id.info_tname)
        val info_tbirth: TextView = findViewById(R.id.info_tbirth)
        val info_tphone: TextView = findViewById(R.id.info_tphone)
        val info_tqq: TextView = findViewById(R.id.info_tqq)
        val info_twx: TextView = findViewById(R.id.info_twx)
        val info_tmail: TextView = findViewById(R.id.info_tmail)
        val info_twb: TextView = findViewById(R.id.info_twb)
        val info_tlocation: TextView = findViewById(R.id.info_tlocation)
        val info_tcity: TextView = findViewById(R.id.info_tcity)
        val info_tschool: TextView = findViewById(R.id.info_tschool)
        val ibutton_edit: Button = findViewById(R.id.ibutton_edit)
//        初始化控件
        info_name.setText(name)
        info_tname.setText(name)
        info_name.isVisible = false
        info_birth.setText(birth)
        info_tbirth.setText(birth)
        info_birth.isVisible = false
        info_phone.setText(phone)
        info_tphone.setText(phone)
        info_phone.isVisible = false
        info_qq.setText(qq)
        info_tqq.setText(qq)
        info_qq.isVisible = false
        info_wx.setText(wx)
        info_twx.setText(wx)
        info_wx.isVisible = false
        info_mail.setText(mail)
        info_tmail.setText(mail)
        info_mail.isVisible = false
        info_wb.setText(wb)
        info_twb.setText(wb)
        info_wb.isVisible = false
        info_location.setText(location)
        info_tlocation.setText(location)
        info_location.isVisible = false
        info_city.setText(city)
        info_tcity.setText(city)
        info_city.isVisible = false
        info_school.setText(school)
        info_tschool.setText(school)
        info_school.isVisible = false
//        修改/保存
        ibutton_edit.setOnClickListener {
            if (edit) { //保存
                oname = name
                edit = false
//                同步控件至变量
                name = info_name.text.toString()
                birth = info_birth.text.toString()
                phone = info_phone.text.toString()
                qq = info_qq.text.toString()
                wx = info_wx.text.toString()
                mail = info_mail.text.toString()
                wb = info_wb.text.toString()
                location = info_location.text.toString()
                city = info_city.text.toString()
                school = info_school.text.toString()
//                同步变量至控件
                Toast.makeText(this, "已保存", Toast.LENGTH_SHORT).show()
                ibutton_edit.setText("修改")
                info_name.setText(name)
                info_tname.setText(name)
                info_tname.isVisible = true
                info_name.isVisible = false
                info_birth.setText(birth)
                info_tbirth.setText(birth)
                info_tbirth.isVisible = true
                info_birth.isVisible = false
                info_tphone.setText(phone)
                info_phone.setText(phone)
                info_tphone.isVisible = true
                info_phone.isVisible = false
                info_qq.setText(qq)
                info_tqq.setText(qq)
                info_tqq.isVisible = true
                info_qq.isVisible = false
                info_wx.setText(wx)
                info_twx.setText(wx)
                info_twx.isVisible = true
                info_wx.isVisible = false
                info_mail.setText(mail)
                info_tmail.setText(mail)
                info_tmail.isVisible = true
                info_mail.isVisible = false
                info_wb.setText(wb)
                info_twb.setText(wb)
                info_twb.isVisible = true
                info_wb.isVisible = false
                info_location.setText(location)
                info_tlocation.setText(location)
                info_tlocation.isVisible = true
                info_location.isVisible = false
                info_city.setText(city)
                info_tcity.setText(city)
                info_tcity.isVisible = true
                info_city.isVisible = false
                info_school.setText(school)
                info_tschool.setText(school)
                info_tschool.isVisible = true
                info_school.isVisible = false
//                更新数据库
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

                db.update("Student", values_me, "name = ?", arrayOf(oname))
            } else {
                edit = true
                ibutton_edit.setText("保存")
                info_name.setText(name)
                info_tname.setText(name)
                info_tname.isVisible = false
                info_name.isVisible = true
                info_birth.setText(birth)
                info_tbirth.setText(birth)
                info_tbirth.isVisible = false
                info_birth.isVisible = true
                info_phone.setText(phone)
                info_phone.setText(phone)
                info_tphone.isVisible = false
                info_phone.isVisible = true
                info_qq.setText(qq)
                info_tqq.setText(qq)
                info_tqq.isVisible = false
                info_qq.isVisible = true
                info_wx.setText(wx)
                info_twx.setText(wx)
                info_twx.isVisible = false
                info_wx.isVisible = true
                info_mail.setText(mail)
                info_tmail.setText(mail)
                info_tmail.isVisible = false
                info_mail.isVisible = true
                info_wb.setText(wb)
                info_twb.setText(wb)
                info_twb.isVisible = false
                info_wb.isVisible = true
                info_location.setText(location)
                info_location.setText(location)
                info_tlocation.isVisible = false
                info_location.isVisible = true
                info_city.setText(city)
                info_tcity.setText(city)
                info_tcity.isVisible = false
                info_city.isVisible = true
                info_school.setText(school)
                info_tschool.setText(school)
                info_tschool.isVisible = false
                info_school.isVisible = true
            }
        }
//        添加数据逻辑
        val ibutton_add: Button = findViewById(R.id.ibutton_add)
        ibutton_add.setOnClickListener {
            Log.d("Info", "Click_add")
            val values_me = ContentValues().apply {
                Log.d("Info", "Entry")
                put("name", name)
                Log.d("Info", "name")
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
            Log.d("Info", "End")
            db.insert("Student", null, values_me)
            Toast.makeText(this, "已添加", Toast.LENGTH_SHORT).show()
        }
    }
}