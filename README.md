Gist of Acceptance criteria
--------------------------------

* Since NASA is releasing one image per day, once image is successfully downloaded to user device
for that day, same should be referred locally for all subsequent user attempts in that day
* App should download image when day changes and image is not available (in device) for that day,
provided Internet connectivity available otherwise it will display last available image from device

Architecture
------------
![Screenshot](/docs/Architecture.png)

User journey flow
-----------------
![Screenshot](/docs/Flow.png)

Screen shots
-----------------
![Screenshot](/docs/screenshot-nodata-firsttime.png, "very first time")
![Screenshot](/docs/screenshot-image-network.png, "downloading from cloud")
![Screenshot](/docs/screenshot-image-local.png, "referring from local database")
![Screenshot](/docs/screenshot-image-last-saved.png, "referring last saved")

Stuff used
-----------------------
* LiveData
* ROOM
* Retrofit
* ViewModel
* Sealed class
* Room in-memory test-cases

Improvements
------------
* Better image loader library should be used. e.g. Glide
* Espresso tests for acceptance testing, more unit tests for business logic (e.g. Repository)
* Component instances can be better managed/ingested through DI library. e.g. Hilt
* Android framework dependent components should be abstracted (through interface) and should be referred
via Dependency Inversion principal
* Warning/errors should be monitored using Lint tool