package excercise.astronomy.mobile

import excercise.astronomy.mobile.db.entity.Astronomy
import java.lang.Exception

sealed class ResultStatus {

    data class Success(val astronomy: Astronomy?, val dataSource: String?): ResultStatus()

    data class Failure(val exception: Exception): ResultStatus()
}