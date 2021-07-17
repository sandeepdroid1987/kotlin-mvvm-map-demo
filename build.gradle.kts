plugins {
  id(ScriptPlugins.infrastructure)
  id(ScriptPlugins.secretPlugin)


}

buildscript {

  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath (BuildPlugins.androidGradlePlugin)
    classpath (BuildPlugins.kotlinGradlePlugin)
    classpath (BuildPlugins.secretPlugin)
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}