package walmart.excercise.astronomy.vikas.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import walmart.excercise.astronomy.vikas.InstanceFactory
import walmart.excercise.astronomy.vikas.db.entity.Astronomy

class AstronomyViewModel: ViewModel() {

    private val liveAstronomy = MutableLiveData<Astronomy>()
    fun getAstronomy(date: String): MutableLiveData<Astronomy> {
        viewModelScope.launch(Dispatchers.IO) {
            val astronomy = InstanceFactory.astronomyRepositoryImpl.getAstronomy(date)
            liveAstronomy.postValue(astronomy)
        }
        return liveAstronomy
    }

}