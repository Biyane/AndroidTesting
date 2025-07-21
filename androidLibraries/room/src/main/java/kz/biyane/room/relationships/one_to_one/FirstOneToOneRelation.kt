package kz.biyane.room.relationships.one_to_one

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction

@Entity(tableName = "userRelation")
data class UserRelation(
    @PrimaryKey val userId: Long,
    val name: String,
    val age: Int
)

@Entity(
    tableName = "profile",
    foreignKeys = [
        ForeignKey(
            entity = UserRelation::class,
            parentColumns = ["userId"],
            childColumns = ["userOwnerId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index(value = ["userOwnerId"], unique = true)]
)
data class ProfileRelation(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val userOwnerId: Long,
)

@Entity
data class UserWithProfile(
    @Embedded val user: UserRelation,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId",
    )
    val profile: ProfileRelation
)

@Dao
interface USerWithProfileDao {

    @Transaction
    @Query("SELECT age, name from UserWithProfile")
    suspend fun getUser(): UserRelation
}