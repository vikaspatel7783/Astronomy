package excercise.astronomy.mobile.repository

import excercise.astronomy.mobile.ResultStatus

interface AstronomyRepository {

    suspend fun getAstronomy(date: String): ResultStatus

    suspend fun getLastStoredAstronomy(): ResultStatus
}