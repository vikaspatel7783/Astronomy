package walmart.excercise.astronomy.vikas.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import walmart.excercise.astronomy.vikas.InstanceFactory
import walmart.excercise.astronomy.vikas.ResultStatus
import walmart.excercise.astronomy.vikas.db.entity.Astronomy

class AstronomyViewModel: ViewModel() {

    private val currentAstronomy = MutableLiveData<ResultStatus>()
    private val lastAstronomy = MutableLiveData<ResultStatus>()

    fun getTodaysAstronomy(): MutableLiveData<ResultStatus> {
        viewModelScope.launch(Dispatchers.IO) {
            val resultStatus = InstanceFactory.astronomyRepositoryImpl.getAstronomy(Astronomy.toDate(System.currentTimeMillis()))
            currentAstronomy.postValue(resultStatus)
        }
        return currentAstronomy
    }

    fun getLastStoredAstronomy(): MutableLiveData<ResultStatus> {
        viewModelScope.launch(Dispatchers.IO) {
            val resultStatus = InstanceFactory.astronomyRepositoryImpl.getLastStoredAstronomy()
            lastAstronomy.postValue(resultStatus)
        }
        return lastAstronomy
    }

}