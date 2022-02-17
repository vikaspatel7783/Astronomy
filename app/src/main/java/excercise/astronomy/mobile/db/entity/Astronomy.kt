package excercise.astronomy.mobile.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*


@Entity(tableName = "astronomy_table", indices = [Index(value = ["date"], unique = true)])
data class Astronomy (
    @ColumnInfo(name = "image")
    var image: ByteArray,

    @ColumnInfo(name = "explanation")
    var explanation: String,

    @ColumnInfo(name = "date")
    var date: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0


    companion object {
        fun toDate(timestamp: Long): String {
            return SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(timestamp)
        }
    }
}