package walmart.excercise.astronomy.vikas.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import walmart.excercise.astronomy.vikas.R
import walmart.excercise.astronomy.vikas.ResultStatus
import walmart.excercise.astronomy.vikas.db.entity.Astronomy
import walmart.excercise.astronomy.vikas.utils.BitmapConverter
import java.net.UnknownHostException


class AstronomyActivity : AppCompatActivity() {

    private val astronomyViewModel: AstronomyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astronomy)

        showProgressbar(true)
        setStatusText("Getting image...")

        astronomyViewModel.getTodaysAstronomy().observe(this, { resultStatus ->
            showProgressbar(false)
            setStatusText("Done.")

            handleResultStatus(resultStatus)
        })
    }

    private fun handleResultStatus(resultStatus: ResultStatus) {
        when (resultStatus) {
            is ResultStatus.Success -> {
                showAstronomy(resultStatus.astronomy, resultStatus.dataSource)
            }

            is ResultStatus.Failure -> {
                if (resultStatus.exception is UnknownHostException) {
                    Toast.makeText(this, "We are not connected to the internet, showing you the last image we have", Toast.LENGTH_LONG).show()
                    showLastSavedAstronomy()
                } else {
                    Toast.makeText(this, "Something went wrong ${resultStatus.exception}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showLastSavedAstronomy() {
        astronomyViewModel.getLastStoredAstronomy().observe(this, { lastSavedResultStatus ->
            handleResultStatus(lastSavedResultStatus)
        })
    }

    private fun showAstronomy(astronomy: Astronomy?, dataSource: String?) {
        if (null == astronomy) {
            setStatusText("No data received")
            return
        }
        setStatusText("Image date: ${astronomy.date} \nSource        : $dataSource")
        findViewById<ImageView>(R.id.astronomy_image).setImageBitmap(BitmapConverter.toBitmap(astronomy.image))
        findViewById<TextView>(R.id.astronomy_explanation).text = astronomy.explanation
    }

    private fun showProgressbar(show: Boolean) {
        findViewById<ProgressBar>(R.id.download_progress).visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setStatusText(text: String) {
        findViewById<TextView>(R.id.status_text).text = text
    }
}