package excercise.astronomy.mobile

import android.app.Application
import excercise.astronomy.mobile.network.PlanetaryService
import excercise.astronomy.mobile.db.AstronomyDatabase
import excercise.astronomy.mobile.repository.AstronomyRepositoryImpl

class AstronomyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        InstanceFactory.instantiate(
                astronomyDatabase = AstronomyDatabase.getInstance(this),
                planetaryService = PlanetaryService.create(),
                astronomyRepository = AstronomyRepositoryImpl()
        )
    }
}