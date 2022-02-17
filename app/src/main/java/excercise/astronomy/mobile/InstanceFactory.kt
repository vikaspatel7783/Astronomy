package excercise.astronomy.mobile

import excercise.astronomy.mobile.db.AstronomyDatabase
import excercise.astronomy.mobile.network.PlanetaryService
import excercise.astronomy.mobile.repository.AstronomyRepository

class InstanceFactory {

    companion object {

        lateinit var astronomyDatabase: AstronomyDatabase
        lateinit var planetaryService: PlanetaryService
        lateinit var astronomyRepositoryImpl: AstronomyRepository

        fun instantiate(astronomyDatabase: AstronomyDatabase,
                        planetaryService: PlanetaryService,
                        astronomyRepository: AstronomyRepository) {
            Companion.astronomyDatabase = astronomyDatabase
            Companion.planetaryService = planetaryService
            astronomyRepositoryImpl = astronomyRepository
        }
    }
}