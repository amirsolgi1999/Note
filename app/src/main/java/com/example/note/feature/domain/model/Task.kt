package com.example.note.feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val title : String,
    val description:String?,
    val timestamp:Long,
    var isDone:Boolean,
    var isFavorite:Boolean,
    @PrimaryKey
    var id:Int? = null
)

class InvalidTaskException(message:String):Exception(message)
