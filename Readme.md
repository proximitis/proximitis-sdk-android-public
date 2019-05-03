# Proximitis - Android (Kotlin)

## Instalace SDK 

1. Stáhněte si Proximitis SDK z Githubu (`libs/proxmitis-sdk.aar`)
2. Přidejte závislosti knihovny

a) `build.gradle` 
```
classpath "io.realm:realm-gradle-plugin:5.10.0"
```
b) `app/build.gradle`
```
apply plugin: 'kotlin-kapt'
```
```
implementation 'androidx.appcompat:appcompat:1.1.0-alpha02'
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

// Dagger2 - Library for dependency injection; Licence: ApacheV2
implementation 'com.google.dagger:dagger:2.19'
implementation 'com.google.dagger:dagger-android-support:2.19'
kapt 'com.google.dagger:dagger-compiler:2.19'

// ALTBeacon - Library for handling ble beacons; Licence: ApacheV2
implementation 'org.altbeacon:android-beacon-library:2.16.1'

// Fresco - Library for handling images view; Licence: ApacheV2
implementation 'com.facebook.fresco:fresco:1.13.0'

// Markwon - Android markdown library; Licence: ApacheV2
implementation "ru.noties.markwon:core:3.0.0"

// Retrofit2 - Library for handling network request layer; Licence: ApacheV2
implementation 'com.squareup.retrofit2:retrofit:2.5.0'
// GSON - Library for JSON manipulation + converter; Licence: ApacheV2
implementation 'com.google.code.gson:gson:2.8.5'
// GSONConverter - Gsonconverter for retrofit; Licence: ApacheV2
implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
// LoggingInterceptor - Interceptor for retrofit; Licence: ApacheV2
implementation 'com.squareup.okhttp3:logging-interceptor:3.12.1'
// RxJava2 Adapter - RxJava2 Adapter for Retrofit2; Licence: ApacheV2
implementation 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'
// ThreeTen Android Backport - An adaptation of the JSR-310 backport for Android; Licence: ApacheV2
implementation 'com.jakewharton.threetenabp:threetenabp:1.0.5'

// Realm - Local database; Licence: ApacheV2
implementation 'io.realm:realm-gradle-plugin:5.9.1'
implementation 'io.realm:realm-android-library:5.9.1'
implementation 'io.realm:realm-annotations:5.9.1'
kapt 'dk.ilios:realmfieldnameshelper:1.1.1'

// RxJava – Reactive Extensions for the JVM; Licence: ApacheV2
implementation 'io.reactivex.rxjava2:rxjava:2.2.7'
implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
```
## Start SDK 

1. Zkopírujte následující kód do `App` vaší aplikace do metody `onCreate()`:

```
ProximitisSDK.init(this, "{SDK_KEY}", MainActivity::class.java)
```
`{SDK_KEY}` - Licenční klíč pro Proximitis SDK 

2. Aktivita, do které jsou načítány fragmenty musí obsahovat v `AndroidManifest.xml`

```
android:launchMode="singleInstance"
```

3. Načtení hlavního fragmentu SDK

```
val fragment = MainFragment.getInstance()
```

5. Zajištění oprávnění k přístupu k poloze na Android 6.0 a vyšší - bez udělení uprávnění není možné skenovat beacony

```
ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestCode)
```

## Třídy

- `MainFragment` - Zajištuje historii procházení jednotlivých fragmentů `MainFragment.getInstance()`
- `CampaignsFragment` - vykresluje seznam kampaní `CampaignsFragment.getInstance()`
- `DetailFragment` - vykresluje detail kampaně `CampaignsFragment.getInstance(campaignId: String)`

### Úprava vzhledu detailu kampaně
Jednotlivé vizuální bloky při vykreslování detailu kampaně lze přepsat.
Metody vždy vrací parametr `view` vytvořený prvek k vykreslení knihovnou a `block` surová data pro vaše vykreslení. 

Jednotlivé bloky jsou:
**PAGE** a **CUSTOM_ENDPOINT**

- `renderTitle(view: View, block: Block)`
- `renderHeading(view: View, block: Block)`
- `renderParagraph(view: View, block: Block)`
- `renderOrderedList(view: View, block: Block)`
- `renderUnOrderedList(view: View, block: Block)`
- `renderImage(view: View, block: Block)`
- `renderButton(view: View, block: Block)`

**WEB_PAGE**
- `renderWebView(view: View, detailUrl: String)`

**IN_APP_EVENT**
- `sendInAppEvent(inAppEvent: String)`

Dále lze také přepsat
- `setBackground(color: Int)` - Barva pozadí 
- `renderError(view: View)` - Metoda v případě, kdy dateil kapaně nelze vykreslit

Získání UUID, které si vygeneruje SDK pro indetifikaci
```
ProximitisSDK.getInstance().deviceUUID()
```
