package kz.biyane.room.first_page

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//@Fts4
@Entity(tableName = "user", indices = [Index(value = ["first_name", "last_name"])])
class User(
    // if FTS used, @PrimaryKey @ColumnInfo(name = "rowid")  must use this type and column name
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    val name: String,
    @ColumnInfo(name = "age") val age: String?,
    @ColumnInfo(name = "region") val region: String?,
    val ignoredField: String?
)

//@Entity(ignoredColumns = ["ignoredField"])
//class DerivedUser : User(0, "", "", "", "", "")