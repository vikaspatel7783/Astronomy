package walmart.excercise.astronomy.vikas.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import walmart.excercise.astronomy.vikas.db.entity.Astronomy

@Dao
interface AstronomyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(astronomy: Astronomy): Long

    @Query("SELECT * FROM astronomy_table WHERE id = :id")
    suspend fun get(id: Long): Astronomy

    @Query("SELECT * FROM astronomy_table WHERE date = :date")
    suspend fun get(date: String): Astronomy?

    @Query("SELECT * FROM astronomy_table LIMIT 1")
    suspend fun getLastSaved(): Astronomy?

    @Query("DELETE FROM astronomy_table WHERE date = :date")
    suspend fun delete(date: String)
}