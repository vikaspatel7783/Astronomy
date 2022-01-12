package walmart.excercise.astronomy.vikas

import walmart.excercise.astronomy.vikas.network.PlanetaryService
import walmart.excercise.astronomy.vikas.db.AstronomyDatabase
import walmart.excercise.astronomy.vikas.repository.AstronomyRepositoryImpl

class InstanceFactory {

    companion object {

        lateinit var astronomyDatabase: AstronomyDatabase
        lateinit var planetaryService: PlanetaryService
        lateinit var astronomyRepositoryImpl: AstronomyRepositoryImpl

        fun instantiate(astronomyDatabase: AstronomyDatabase,
                        planetaryService: PlanetaryService,
                        astronomyRepositoryImpl: AstronomyRepositoryImpl) {
            this.astronomyDatabase = astronomyDatabase
            this.planetaryService = planetaryService
            this.astronomyRepositoryImpl = astronomyRepositoryImpl
        }
    }
}