package com.example.notesapproomdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity
data class Notes(
    val title:String,
    val description:String?,
    @PrimaryKey @Nonnull val id:String
)
