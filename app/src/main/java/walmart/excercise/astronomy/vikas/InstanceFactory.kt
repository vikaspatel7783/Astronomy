package walmart.excercise.astronomy.vikas

import walmart.excercise.astronomy.vikas.api.PlanetaryService
import walmart.excercise.astronomy.vikas.db.AstronomyDatabase

class InstanceFactory {

    companion object {

        lateinit var astronomyDatabase: AstronomyDatabase
        lateinit var planetaryService: PlanetaryService

        fun instantiate(astronomyDatabase: AstronomyDatabase, planetaryService: PlanetaryService) {
            this.astronomyDatabase = astronomyDatabase
            this.planetaryService = planetaryService
        }
    }
}