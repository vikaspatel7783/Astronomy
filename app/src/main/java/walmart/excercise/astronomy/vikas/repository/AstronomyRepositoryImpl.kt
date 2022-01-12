package walmart.excercise.astronomy.vikas.repository

import walmart.excercise.astronomy.vikas.InstanceFactory
import walmart.excercise.astronomy.vikas.ResultStatus
import walmart.excercise.astronomy.vikas.db.entity.Astronomy
import walmart.excercise.astronomy.vikas.network.ImageDownloader
import walmart.excercise.astronomy.vikas.utils.BitmapConverter
import java.net.URL

class AstronomyRepositoryImpl : AstronomyRepository {

    override suspend fun getAstronomy(date: String): ResultStatus {
        val astronomyDbDao = InstanceFactory.astronomyDatabase.astronomyDao()
        val astronomyNetworkApi = InstanceFactory.planetaryService

        val savedAstronomy = astronomyDbDao.get(date)

        if (null == savedAstronomy) {

            return try {
                val todaysAstronomyFromApi = astronomyNetworkApi.download()
                val image = ImageDownloader().download(URL(todaysAstronomyFromApi.url))

                val todayAstronomy = Astronomy(
                        image = BitmapConverter.toByteArray(image),
                        explanation = todaysAstronomyFromApi.explanation,
                        date = date)

                astronomyDbDao.insert(todayAstronomy)
                ResultStatus.Success(todayAstronomy, "Network")

            } catch (ex: Exception) {
                ResultStatus.Failure(ex)
            }

        } else {
            return ResultStatus.Success(savedAstronomy, "Database")
        }
    }

    override suspend fun getLastStoredAstronomy(): ResultStatus {
        val astronomyDbDao = InstanceFactory.astronomyDatabase.astronomyDao()
        val lastSavedAstronomy = astronomyDbDao.getLastSaved()
        return ResultStatus.Success(lastSavedAstronomy, "Database")
    }

}