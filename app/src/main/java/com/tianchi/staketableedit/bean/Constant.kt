package com.tianchi.staketableedit.bean

import android.os.Environment

object Constant {
    val stakeTypeArray = listOf("里程桩","测试桩","转角桩","警示桩")
    val depthArray = listOf("超长无法探测")
    val BuriedTechnologyArray = listOf("定向钻穿河","定向钻穿河路","直管平铺","平铺弯头")
    val terrainArray = listOf("铺装路面","绿化带","山坡","荒地")
    var SDCARD: String = Environment.getExternalStorageDirectory().absolutePath
    var projectName="桩表e"
}