# Sandeep MapDemo
## This demo is based on Clear architecture pattern and also uses some concepts functional programming.

## Demonstrated points :
- Scalable code structure - divided into core and features as root packages.
- Clean code architecture - domain, data and ui.
- Separation of DTOs for UI and API in order to facilitate whitelabeling in future.
- Depedency Injection
- Unit testing
- KTS build scripts

## Architecture pattern
- MVVM - LiveData, ViewModel

## Language
- Kotlin

## Local Development
Here are some useful Gradle/adb commands for executing this demo:

 * `./gradlew runApp` - Builds and install the debug apk on the current connected device.
 * `./gradlew compileApp` - Builds the debug apk.
 * `./gradlew runUnitTests` - Execute unit tests (both unit and integration).
 * `./gradlew runAcceptanceTests` - Execute acceptance and instrumentation tests in the connected device.
