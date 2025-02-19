package com.tianchi.staketableedit.utils

import com.tianchi.staketableedit.bean.PointInfo
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

class Tools {
    companion object{
        fun getDataClassFieldNames(clazz: Any): List<String> {
            return clazz::class.memberProperties.map { it.name }
        }
        fun getDataClassFieldNamesAndValue(clazz: Any):Map<String,String>{
           return clazz.javaClass.kotlin.declaredMemberProperties.associate { it->
               it.name to it.get(clazz).toString()
            }
        }
        fun getDataClassFieldNamesAndValueForMap(list:List<PointInfo>): List<Map<String, String>> {
            var maps:MutableList<Map<String,String>> = mutableListOf()
            list.forEach {
                var map = getDataClassFieldNamesAndValue(it)
                if (map.isNotEmpty())maps.add(map)
            }
            return maps
        }
        fun getDataClassFieldNamesAndValuesForList(names:List<String>,
                                                  maps: List<Map<String,String>>):List<List<String>>{
            var listMain:MutableList<List<String>> = mutableListOf()
            for (map in maps){
                var list= mutableListOf<String>()
                names.forEach {
                    for (key in map.keys){
                        if (key.equals(it)){
                            map.get(key)?.let { it1 -> list.add(it1) }
                            return@forEach
                        }
                    }
                }
                if (list.isNotEmpty())listMain.add(list)
            }
            return listMain
        }
    }
}