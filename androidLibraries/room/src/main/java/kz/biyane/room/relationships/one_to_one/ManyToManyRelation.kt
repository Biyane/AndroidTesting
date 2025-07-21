package kz.biyane.room.relationships.one_to_one

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation


// Tag Entity (for many-to-many relationship)
@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val tagId: Long = 0,

    @ColumnInfo(name = "tag_name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String? = null
)

// Junction Table for Many-to-Many relationship between Posts and Tags
@Entity(
    tableName = "post_tag_cross_ref",
    primaryKeys = ["postId", "tagId"], // Composite primary key
    foreignKeys = [
        ForeignKey(
            entity = Post::class,
            parentColumns = ["postId"],
            childColumns = ["postId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["tagId"],
            childColumns = ["tagId"],
            onDelete = CASCADE
        )
    ],
    indices = [
        Index(value = ["postId"]),
        Index(value = ["tagId"])
    ]
)
data class PostTagCrossRef(
    val postId: Long,
    val tagId: Long,

    // Optional: You can add additional columns to the junction table
    @ColumnInfo(name = "assigned_at")
    val assignedAt: Long = System.currentTimeMillis()
)

// Many-to-Many: Post with Tags
data class PostWithTags(
    @Embedded val post: Post,

    @Relation(
        parentColumn = "postId",
        entityColumn = "tagId",
        associateBy = Junction(PostTagCrossRef::class)
    )
    val tags: List<Tag>
)

// Many-to-Many: Tag with Posts
data class TagWithPosts(
    @Embedded val tag: Tag,

    @Relation(
        parentColumn = "tagId",
        entityColumn = "postId",
        associateBy = Junction(PostTagCrossRef::class)
    )
    val posts: List<Post>
)