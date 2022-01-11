package walmart.excercise.astronomy.vikas.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import walmart.excercise.astronomy.vikas.db.AstronomyDatabase
import walmart.excercise.astronomy.vikas.db.entity.Astronomy

@RunWith(AndroidJUnit4::class)
class AstronomyDaoTest {

    private lateinit var database: AstronomyDatabase
    private lateinit var astronomyDao: AstronomyDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AstronomyDatabase::class.java).build()
        astronomyDao = database.astronomyDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    private fun createAstronomyEntity(imageByteArray: ByteArray, date: String) = Astronomy(
            image = imageByteArray,
            date = date
    )

    @Test
    fun testAstrnomyObjectInsert() {
        val todayDate = Astronomy.toDate(System.currentTimeMillis())
        runBlocking {
            createAstronomyEntity(byteArrayOf(), todayDate)
            .let {
                val insertedId = astronomyDao.insert(it)
                val insertedAstronomy = astronomyDao.get(insertedId)
                Assert.assertEquals(todayDate, insertedAstronomy.date)
            }
        }
    }

    @Test
    fun testImageExistByDate() {
        val todayDate = Astronomy.toDate(System.currentTimeMillis())
        runBlocking {
            createAstronomyEntity(byteArrayOf(), todayDate)
                .let {
                    astronomyDao.insert(it)
                    val dbRecord = astronomyDao.get(todayDate)
                    Assert.assertEquals(todayDate, dbRecord.date)
                }
        }
    }

}