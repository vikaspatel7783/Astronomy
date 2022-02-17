package excercise.astronomy.mobile.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import excercise.astronomy.mobile.db.dao.AstronomyDao
import excercise.astronomy.mobile.db.entity.Astronomy

@Database(entities = [Astronomy::class], version = 1, exportSchema = false)
abstract class AstronomyDatabase: RoomDatabase() {

    abstract fun astronomyDao(): AstronomyDao

    companion object {

        private const val DATABASE_NAME = "AstronomyDb"

        // For Singleton instantiation
        @Volatile private var instance: AstronomyDatabase? = null

        fun getInstance(context: Context): AstronomyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AstronomyDatabase {
            return Room.databaseBuilder(context, AstronomyDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
//                            CoroutineScope(Dispatchers.IO).launch {}
                        }
                    }
                )
                //.allowMainThreadQueries()
                .build()
        }

    }
}