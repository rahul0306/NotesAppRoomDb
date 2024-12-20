package com.example.notesapproomdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    val title:String,
    val description:String?,
    @PrimaryKey val id:Int?=null
)
