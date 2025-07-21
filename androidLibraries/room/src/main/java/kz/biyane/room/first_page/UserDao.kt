package kz.biyane.room.first_page

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>


    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT first_name, last_name FROM user")
    fun loadFullName(): List<NameTuple>

    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    fun loadAllUsersBetweenAges(minAge: Int, maxAge: Int): Array<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :search " +
            "OR last_name LIKE :search")
    fun findUserWithName(search: String): List<User>

    @Query("SELECT * FROM user WHERE region IN (:regions)")
    fun loadUsersFromRegions(regions: List<String>): List<User>


    @Query(
        "SELECT * FROM user" +
                "JOIN book ON user.id = book.user_id"
    )
    fun loadUserAndBookNames(): Map<User, List<Book>>


    @Query(
        "SELECT * FROM book " +
                "INNER JOIN loan ON loan.book_id = book.id " +
                "INNER JOIN user ON user.id = loan.user_id " +
                "WHERE user.name LIKE :userName"
    )
    fun findBooksBorrowedByNameSync(userName: String): List<Book>


}
