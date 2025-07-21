package kz.biyane.room.relationships.one_to_one

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

// User Entity (Parent in one-to-many relationship)
@Entity
data class Author(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,

    @ColumnInfo("created_at") val createdAt: Long = System.currentTimeMillis()
)

// Post Entity (Child in one-to-many relationship)
@Entity(
    tableName = "post",
    foreignKeys = [
        ForeignKey(
            entity = Author::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index(value = ["user_id"])],

    )
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "post_name") val postName: String?,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "author_id")
    val authorId: Long, // Foreign key referencing User.userId

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)



@Entity
class AuthorWithPost(
    @Embedded val author: Author,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val posts: List<Post>,
)

@Entity
class PostWithAuthor(
    @Embedded val post: Post,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val author: Author,
)