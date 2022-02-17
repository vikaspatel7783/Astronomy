package walmart.excercise.astronomy.vikas.db

import org.junit.Assert
import org.junit.Test
import excercise.astronomy.mobile.db.entity.Astronomy

class DateConverterTest {

    @Test
    fun testToDateConversion() {
        val resultDate = Astronomy.toDate(System.currentTimeMillis())
        Assert.assertEquals("11/01/2022", resultDate)
    }
}