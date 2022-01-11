package walmart.excercise.astronomy.vikas.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import walmart.excercise.astronomy.vikas.InstanceFactory
import walmart.excercise.astronomy.vikas.network.ImageDownloader
import walmart.excercise.astronomy.vikas.network.PlanetaryResponse
import walmart.excercise.astronomy.vikas.db.entity.Astronomy
import java.io.ByteArrayOutputStream
import java.net.URL

class AstronomyViewModel: ViewModel() {

    private val apiResponse = MutableLiveData<PlanetaryResponse>()
    private val imageResponse = MutableLiveData<Bitmap>()

    fun fetchApiResponse(): LiveData<PlanetaryResponse> {
        viewModelScope.launch(Dispatchers.IO) {
            val response: PlanetaryResponse = InstanceFactory.planetaryService.download()
            apiResponse.postValue(response)
        }
        return apiResponse
    }

    fun downloadImage(url: String): LiveData<Bitmap> {
        viewModelScope.launch(Dispatchers.IO) {
            val image = ImageDownloader().download(URL(url))
            imageResponse.postValue(image)
        }
        return imageResponse
    }

    fun saveToDb(bitmap: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val imageByteArray = byteArrayOutputStream.toByteArray()

        viewModelScope.launch(Dispatchers.IO) {
            InstanceFactory.astronomyDatabase.astronomyDao().insert(
                Astronomy(image = imageByteArray,
                date = Astronomy.toDate(System.currentTimeMillis()))
            )
        }
    }

    fun getFromDb(date: String): MutableLiveData<Bitmap> {
        viewModelScope.launch(Dispatchers.IO) {
            val astronomy = InstanceFactory.astronomyDatabase.astronomyDao().get(date)
            val bitmap = BitmapFactory.decodeByteArray(astronomy.image, 0, astronomy.image.size)
            imageResponse.postValue(bitmap)
        }
        return imageResponse
    }
}