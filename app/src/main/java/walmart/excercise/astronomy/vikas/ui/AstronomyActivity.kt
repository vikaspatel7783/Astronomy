package walmart.excercise.astronomy.vikas.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import walmart.excercise.astronomy.vikas.R
import walmart.excercise.astronomy.vikas.db.entity.Astronomy
import walmart.excercise.astronomy.vikas.utils.BitmapConverter


class AstronomyActivity : AppCompatActivity() {

    private val astronomyViewModel: AstronomyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astronomy)

        findViewById<Button>(R.id.btn_show_image).setOnClickListener {
            showProgressbar(true)

            astronomyViewModel.getAstronomy(Astronomy.toDate(System.currentTimeMillis())).observe(this, { astronomy ->
                showProgressbar(false)

                findViewById<ImageView>(R.id.astronomy_image).setImageBitmap(BitmapConverter.toBitmap(astronomy.image))
                findViewById<TextView>(R.id.astronomy_explanation).text = astronomy.explanation
                Toast.makeText(this, astronomy.date, Toast.LENGTH_LONG).show()
            })

        }
    }

    private fun showProgressbar(show: Boolean) {
        findViewById<ProgressBar>(R.id.download_progress).visibility = if (show) View.VISIBLE else View.GONE
    }
}