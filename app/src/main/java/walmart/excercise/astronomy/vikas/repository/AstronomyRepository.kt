package walmart.excercise.astronomy.vikas.repository

import walmart.excercise.astronomy.vikas.ResultStatus

interface AstronomyRepository {

    suspend fun getAstronomy(date: String): ResultStatus

    suspend fun getLastStoredAstronomy(): ResultStatus
}