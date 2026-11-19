# MobileAppTask

## Urządzenia Domowe

Rozwiązanie zadania INF.04-02-25.01-SG ze stycznia 2025: aplikacja Android oraz program konsolowy.

## Android

Projekt główny otwórz w Android Studio. Zainstaluj Android SDK Platform 37.1 oraz Build Tools 36.0.0. Projekt używa Android Gradle Plugin 9.3.3, Gradle 9.5.0 i JDK 17 lub nowszego. Kotlin jest obsługiwany przez wtyczkę Android.

Uruchom aplikację na emulatorze lub urządzeniu z Androidem 15 (API 35) lub nowszym. Numer autora uzupełnij w `app/src/main/res/values/strings.xml`, w zasobie `candidate_number`.

Budowanie APK:

```sh
./gradlew :app:assembleDebug
```

Plik APK powstaje w `app/build/outputs/apk/debug/app-debug.apk`.

W tym komputerze domyślne `~/.gradle` i `~/.android` wskazują na niezamontowany wolumin Dev. Polecenie użyte do weryfikacji, z osobnymi katalogami roboczymi:

```sh
JAVA_HOME='/Users/karol/Library/Java/JavaVirtualMachines/ms-21.0.9/Contents/Home' \
ANDROID_USER_HOME=/tmp/semestr1-android-user \
GRADLE_USER_HOME=/Volumes/Semestr1Build/gradle \
./gradlew :app:assembleDebug :app:lintDebug
```

W `local.properties` podano lokalną ścieżkę do istniejącego SDK. Na innym komputerze Android Studio ustawi własną ścieżkę; plik ten nie wchodzi do archiwum projektu.

Aplikacja używa liniowych układów XML, oryginalnych zdjęć i kolorów z arkusza. Pralka zatwierdza tylko numery 1–12. Niepoprawne, puste lub zbyt długie dane pokazują błąd i nie zmieniają zatwierdzonego programu. Odkurzacz przełącza stan i treść przycisku; napis „Status: naładowany” pozostaje stały. Stan urządzeń jest zachowany przy odtworzeniu aktywności.

Kompilację weryfikowano na kopii projektu w `/Volumes/Semestr1Build/project`, na osobnym obrazie APFS przechowywanym na dysku ADATA. Na tym komputerze obraz można ponownie zamontować poleceniem `hdiutil attach -nobrowse "/Volumes/ADATA SE880/.semestr1-build/runtime.sparseimage"`.

## Konsola

```sh
python3 konsolowa/main.py
```

Program wykorzystuje klasę bazową Device oraz klasy WashingMachine i VacuumCleaner. Podaj np. 11 (poprawny program) lub 44 (niepoprawny program). Trzy wywołania on() wypisują jeden komunikat. Następnie program wyświetla komunikat o rozładowaniu i wyłącza odkurzacz. Nie wymaga dodatkowych pakietów Pythona.

Numer zdającego uzupełnij także w komentarzu metody display_message w `konsolowa/main.py`.

## Testy

Testy Android w `DomesticDevicesTest.kt` sprawdzają zatwierdzanie programu, wartości graniczne 1 i 12, dane błędne i puste, przełączanie odkurzacza oraz zachowanie stanu po odtworzeniu aktywności. Zapisują również cztery zrzuty ekranu emulatora.

```sh
./gradlew :app:connectedDebugAndroidTest
```

## Pliki do oddania

- `konsolowa/`: kod Python i konsola.zip.
- `mobilna/`: mobilna.zip, kopie zmienionych źródeł oraz APK po udanej kompilacji.
- `dokumentacja/`: egzamin.docx, wyniki uruchomień i dostępne zrzuty.

Przed oddaniem wpisz własny numer zdającego, odśwież archiwa po tej zmianie i umieść trzy foldery w katalogu nazwanym swoim numerem. Zrzuty emulatora pokazują ekran Androida. Wymagane przez arkusz zrzuty całego pulpitu z paskiem systemowym oraz wynikami konsoli i otwartym środowiskiem należy dodatkowo wykonać ręcznie. Ewentualne nagranie płyty jest czynnością fizyczną.

## Repozytorium

Gałąź main zawiera połączone zmiany. Gałęzie feature/android-ui, feature/device-controls, feature/console-app, test/android-interactions i docs/project-documentation odpowiadają kolejnym częściom rozwiązania. Zachowano historię projektu bazowego. Wygenerowane APK, archiwa i eksporty źródeł są lokalnymi rezultatami i nie są wersjonowane; APK można odtworzyć poleceniem budowania opisanym powyżej.
