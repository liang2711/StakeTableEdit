package com.tianchi.staketableedit.utils

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class JsonUils {
    companion object{
        fun parseJSONListDouble(exceFiledJson: String?, type: String?): List<Double> {
            val list: MutableList<Double> = ArrayList()
            var jsonObject: JSONObject? = null
            try {
                jsonObject = JSONObject(exceFiledJson)
                val primeExlPObject = jsonObject.getJSONObject(type)
                val keys = primeExlPObject.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    list.add(primeExlPObject.getDouble(key))
                }
            } catch (e: JSONException) {
                throw RuntimeException(e)
            }
            return list
        }
        fun loadJSONFromAssets(fileName: String?,context: Context?): String? {
            val jsonString = StringBuilder()
            try {
                val inputStream: InputStream = context!!.assets.open(fileName!!)
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                while ((bufferedReader.readLine().also { line = it }) != null) {
                    jsonString.append(line)
                }
                bufferedReader.close()
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
            return jsonString.toString()
        }
        fun parseJSONList(exceFiledJson: String?, type: String?): List<String> {
            val list: MutableList<String> = java.util.ArrayList()
            var jsonObject: JSONObject? = null
            try {
                jsonObject = JSONObject(exceFiledJson)
                val primeExlPObject = jsonObject.getJSONObject(type)
                val keys = primeExlPObject.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    list.add(primeExlPObject.getString(key))
                }
            } catch (e: JSONException) {
                throw java.lang.RuntimeException(e)
            }
            return list
        }
    }
}