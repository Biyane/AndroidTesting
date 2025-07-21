package kz.biyane.room.first_page

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book(
    @PrimaryKey(autoGenerate = true) val bookId: Long,
    val name: String,
    @ColumnInfo(name = "user_id") val userId: String,
)

fun main() {
    Book::class.java
}