package kz.biyane.room.first_page

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
class Book(
    val name: String,
    @ColumnInfo(name = "user_id") val userId: String,
)