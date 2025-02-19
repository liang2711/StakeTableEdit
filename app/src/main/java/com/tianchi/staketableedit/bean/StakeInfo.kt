package com.tianchi.staketableedit.bean

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "info_table")
@Serializable
@Parcelize
data class StakeInfo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tempNo: String,
    val pipeLine: String,
    val stakeType: String,
    val latitude: Double,
    val longitude: Double,
    val z: Double,
    val pipeDepth: String,
    val mileage: String,
    val setover: String,
    val buryTech: String,
    val isInPoint: String,
    val terrain: String,
    val imageName: String,
    val collectDate: String,
) : Parcelable {
    companion object {
        val Empty = StakeInfo(
            0,
            "",
            "",
            "",
            0.0,
            0.0,
            0.0,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }
}
