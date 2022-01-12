package walmart.excercise.astronomy.vikas.repository

import walmart.excercise.astronomy.vikas.InstanceFactory
import walmart.excercise.astronomy.vikas.db.entity.Astronomy
import walmart.excercise.astronomy.vikas.network.ImageDownloader
import walmart.excercise.astronomy.vikas.utils.BitmapConverter
import java.net.URL

class AstronomyRepositoryImpl {

    suspend fun getAstronomy(date: String): Astronomy {
        val astronomyDbDao = InstanceFactory.astronomyDatabase.astronomyDao()
        val astronomyNetworkApi = InstanceFactory.planetaryService

        var savedAstronomy = astronomyDbDao.get(date)

        if (null == savedAstronomy) {
            val todaysAstronomy = astronomyNetworkApi.download()

            val image = ImageDownloader().download(URL(todaysAstronomy.url))

            val astronomy = Astronomy(
                    image = BitmapConverter.toByteArray(image),
                    explanation = todaysAstronomy.explanation,
                    date = date)

            astronomyDbDao.insert(astronomy)

            savedAstronomy = astronomy
        }
        return savedAstronomy
    }

}