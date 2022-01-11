package walmart.excercise.astronomy.vikas

import android.app.Application
import walmart.excercise.astronomy.vikas.network.PlanetaryService
import walmart.excercise.astronomy.vikas.db.AstronomyDatabase

class AstronomyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        InstanceFactory.instantiate(
            astronomyDatabase = AstronomyDatabase.getInstance(this),
            planetaryService = PlanetaryService.create()
        )
    }
}