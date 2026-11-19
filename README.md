# MobileAppTask

Aplikacja mobilna Android realizująca część II zadania INF.04-02-25.01-SG: obsługa pralki i odkurzacza.

## Działanie

- Pralka: zatwierdzenie numeru od 1 do 12 zmienia wyświetlany numer prania. Błędne lub puste dane wyświetlają błąd i nie zmieniają zatwierdzonego programu.
- Odkurzacz: przycisk przełącza podpis Włącz / Wyłącz oraz komunikat o stanie urządzenia.
- Interfejs XML wykorzystuje liniowe układy, zdjęcia i kolory z arkusza. Stan urządzeń jest zachowywany przy odtworzeniu aktywności.

## Uruchomienie

Otwórz projekt w Android Studio i wskaż Android SDK. Projekt korzysta z Android SDK Platform 37.1, Build Tools 36.0.0, Gradle 9.5.0 i Android Gradle Plugin 9.3.3. Do kompilacji użyto JDK 21.

Uruchom aplikację na emulatorze lub urządzeniu z Androidem 15 (API 35) lub nowszym. Numer autora wpisz w zasobie candidate_number w app/src/main/res/values/strings.xml.

```sh
./gradlew :app:assembleDebug
```

APK powstaje w app/build/outputs/apk/debug/app-debug.apk.

## Sprawdzenie

Kompilacja i Android Lint zakończyły się poprawnie. Testy na emulatorze Pixel 5 z Androidem 16 sprawdziły stan początkowy, zatwierdzanie programu, granice zakresu, dane niepoprawne, przełączanie odkurzacza i odtworzenie aktywności.

```sh
./gradlew :app:connectedDebugAndroidTest
```

Źródła aplikacji znajdują się w app/src/main, a testy interakcji w app/src/androidTest. Materiały zadania pozostawiono w docs. Archiwa i APK są wynikami lokalnymi i nie są wersjonowane.
