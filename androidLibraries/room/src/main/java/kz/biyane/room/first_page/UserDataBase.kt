package kz.biyane.room.first_page

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Book::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        var instance: UserDataBase? = null

        fun getInstance(context: Context): UserDataBase {
            return instance ?: synchronized(this) {
                val localInstance =
                    Room.databaseBuilder(context.applicationContext, UserDataBase::class.java, "user_database")
                        .build()
                instance = localInstance

                instance!!
            }
        }
    }
}

//val db = Room.databaseBuilder(
//    applicationContext,
//    UserDataBase::class.java, "database-name"
//).enableMultiInstanceInvalidation()
