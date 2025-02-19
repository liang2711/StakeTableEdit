package com.tianchi.staketableedit.utils

import java.io.File

class FileUtils {
    companion object{
        fun getFileCountInDirectory(directoryPath: String?, vararg types: String?): Int {
            val directory = File(directoryPath)
            var num = 0
            // 检查是否为有效目录
            if (directory.exists() && directory.isDirectory) {
                // 获取目录下所有文件和子目录
                val files = directory.listFiles()
                if (types.size == 0)  // 返回文件的数量，如果目录为空则返回 0
                    return files?.size ?: 0
                for (file in files!!) {
                    if (file.isDirectory) {
                        num++
                        continue
                    }
                    for (str in types) if (file.name.endsWith(str!!)) {
                        num++
                    }
                }
                return num
            } else {
                // 如果路径不是目录或不存在
                return num
            }
        }
        fun mkDir(path:String){
            var file=File(path)
            if (!file.exists())
                file.mkdir()
        }
    }
}