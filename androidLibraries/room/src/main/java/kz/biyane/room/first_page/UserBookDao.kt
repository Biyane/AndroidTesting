package kz.biyane.room.first_page

import androidx.lifecycle.LiveData
import androidx.room.Query

interface UserBookDao {
    @Query(
        "SELECT user.name AS userName, book.name AS bookName " +
                "FROM user, book " +
                "WHERE user.id = book.user_id"
    )
    fun loadUserAndBookNames(): LiveData<List<UserBook>>

    // You can also define this class in a separate file.
    data class UserBook(val userName: String?, val bookName: String?)
}
