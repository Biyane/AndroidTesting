package kz.biyane.room.first_page

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class NameTuple(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)