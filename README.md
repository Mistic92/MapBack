MapBack
===
This simple plugin helps maintain apk with associated mapping file from Proguard.
You will never forget to copy mapping file!

To use it in project

```groovy
buildscript {
  repositories {
      jcenter()
  }

  dependencies {
      classpath 'com.lukaszbyjos.mappingbackup:map-back:1.0.0'
  }
}

apply plugin: 'com.android.application'
apply plugin: 'com.lukaszbyjos.mappingbackup'
```

By default files are copied to rootDir/deploy/v$versionCode_$versionName. Version data are get from your build.gradle file.
If you are using Windows system, after successful build and copy, folder with files is opened.

You can customize path by using options in your build.gradle file. Below are shown default values.
Variable names "version" and "name" will be replaced with values from build.gradle.

```groovy
mapback {
    mainFolder = "deploy"
    path = vversion_name
    autoOpen = true
}
```


