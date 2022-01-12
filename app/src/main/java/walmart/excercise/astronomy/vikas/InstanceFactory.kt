package walmart.excercise.astronomy.vikas

import walmart.excercise.astronomy.vikas.db.AstronomyDatabase
import walmart.excercise.astronomy.vikas.network.PlanetaryService
import walmart.excercise.astronomy.vikas.repository.AstronomyRepository

class InstanceFactory {

    companion object {

        lateinit var astronomyDatabase: AstronomyDatabase
        lateinit var planetaryService: PlanetaryService
        lateinit var astronomyRepositoryImpl: AstronomyRepository

        fun instantiate(astronomyDatabase: AstronomyDatabase,
                        planetaryService: PlanetaryService,
                        astronomyRepository: AstronomyRepository) {
            this.astronomyDatabase = astronomyDatabase
            this.planetaryService = planetaryService
            this.astronomyRepositoryImpl = astronomyRepository
        }
    }
}