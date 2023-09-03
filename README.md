# reQuestik mmultiplatform App

A simplified version of the [reQuestik example](https://localhost),
fully based on Jetpack Compose and without using any third-party libraries.

Supported targets: Android, Desktop and iOS.

## How to run

Choose a run configuration for an appropriate target in Android Studio and run it.

## Run on desktop via Gradle

`./gradlew desktop:run`

## Building native desktop distribution
```
./gradlew :desktop:packageDistributionForCurrentOS
# outputs are written to desktop/build/compose/binaries
```
