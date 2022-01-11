package walmart.excercise.astronomy.vikas.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import walmart.excercise.astronomy.vikas.R
import walmart.excercise.astronomy.vikas.network.PlanetaryResponse
import walmart.excercise.astronomy.vikas.db.entity.Astronomy


class AstronomyActivity : AppCompatActivity() {

    private val astronomyViewModel: AstronomyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astronomy)

        findViewById<Button>(R.id.btn_show_image).setOnClickListener {
            showProgressbar(true)

//            astronomyViewModel.getFromDb(Astronomy.toDate(System.currentTimeMillis())).observe(this, {
//                findViewById<ImageView>(R.id.download_image).setImageBitmap(it)
//            })
//            showProgressbar(false)

            astronomyViewModel.fetchApiResponse().observe(this, { planetaryResponse ->
                Toast.makeText(this, planetaryResponse.title, Toast.LENGTH_LONG).show()
                downloadAndShowImage(planetaryResponse)
            })
        }
    }

    private fun downloadAndShowImage(planetaryResponse: PlanetaryResponse) {
        astronomyViewModel.downloadImage(planetaryResponse.url).observe(this, {
            showProgressbar(false)
            findViewById<ImageView>(R.id.download_image).setImageBitmap(it)

            saveToDB(it)
        })
    }

    private fun saveToDB(bitmap: Bitmap) {
        astronomyViewModel.saveToDb(bitmap)
    }

    private fun showProgressbar(show: Boolean) {
        findViewById<ProgressBar>(R.id.download_progress).visibility = if (show) View.VISIBLE else View.GONE
    }
}