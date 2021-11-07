package com.certified.dependencyinjectionexamplehilt.data.model

import android.graphics.Bitmap
import android.net.Uri
import android.text.format.DateUtils
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_table")
data class User(
    var name: String,
    var email: String,
    var phone: String,
    var password: String,
    val image: Uri?
) {
    @PrimaryKey
    var id: Int = 0

    @ColumnInfo(name = "date_created")
    var dateCreated = Date()

    @ColumnInfo(name = "last_modified")
    var lastModified = dateCreated
}