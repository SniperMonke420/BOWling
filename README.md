# BOWling
Aplikacja do rezerwacji torów na kręgielni
System do rezerwacji torów na kręgielni
---
## Wymagania wstępne
Przed rozpoczęciem upewnij się, że masz zainstalowane następujące narzędzia:
### 1. Java Development Kit (JDK): Wersja 17.0.5.
- Pobierz JDK 17 z Oracle lub OpenJDK.
- Skonfiguruj zmienną środowiskową JAVA_HOME.
- Zweryfikuj instalację, wpisując w terminalu:
  `java -version`
- Oczekiwany wynik:
  `openjdk version "17.0.5"`
### 2. Gradle: Narzędzie do budowania projektu.
- Jeśli Gradle nie jest zainstalowany, pobierz go tutaj: https://gradle.org/releases/.
- Możesz też użyć wbudowanego wrappera Gradle w projekcie (`./gradlew`).
- Zweryfikuj instalację: `gradle -v`
### 3. Docker: Do uruchomienia bazy danych PostgreSQL.
- Pobierz i zainstaluj Docker desktop: https://www.docker.com/products/docker-desktop/.
---
## Pobranie projektu z GitHub
- Skopiuj adres URL repozytorium z GitHuba: https://github.com/SniperMonke420/BOWling
- Otwórz IDE(np. Intellij) i w terminalu wpisz: `git clone https://github.com/SniperMonke420/BOWling`
- Uruchomienie bazy danych PostgreSQL w Dockerze
---
## Uruchom kontener PostgreSQL za pomocą poniższej komendy:
`docker run --name BOWling_postgres -e POSTGRES_PASSWORD=post123gres -p 5443:5432 -d postgres`
- Zweryfikuj, czy kontener działa: `docker ps`
- Opcjonalnie: Połącz się z bazą danych za pomocą klienta PostgreSQL (np. DBeaver):
    - **Host:** `localhost`
    - **Port:** `5443`
    - **Użytkownik:** `postgres`
    - **Hasło:** `post123gres`
---
## Budowanie projektu za pomocą Gradle
- Użyj Gradle Wrapper (./gradlew) do budowania projektu, aby zapewnić spójność wersji Gradle:
  `./gradlew clean build`
---

## Aplikacja powinna być gotowa do uruchomienia na twoim urządzeniu

---

# Historia commitów

## Main (Branch)
- **Initial commit**  
  Pierwszy commit po stworzeniu projektu w Springu.
- **Initial configuration, register, login and security**  
  Wstępna konfiguracja projektu, stworzony Spring Security wraz z walidacją JWT, endpointami od rejestracji i logowania.
- **Merge branch 'main' of https://github.com/SniperMonke420/BOWling**  
  Podpięcie Gita do zdalnego repozytorium pod wyżej wymienionym adresem.

---

## Login_registration_and_swagger (Branch)
- **Poprzednie commity z Main brancha**  
  Jak wyżej.
- **Login and registration tweaks + swagger**  
  Dodanie Swaggera oraz zmiana pól w encjach oraz bazie danych.
- **HTTP request exception handling and userData endpoint**  
  Stworzenie endpointu umożliwiającego zaciąganie danych o użytkowniku oraz dodanie obsługi błędów wynikających z zapytań HTTP.

---

## Bowling_alley_reservation (Branch)
- **Commity z poprzednich branchy**  
  Jak wyżej.
- **Bowling alley reservation initial commit**  
  Pierwszy commit ze wstępnie działającym systemem rezerwacji.
- **Updated Swagger because earlier version doesn't work for some reason**  
  Zaktualizowanie wersji Swaggera, ponieważ poprzednia przestała działać.
- **3x CORS config fix**  
  Trzy commity poprawiające konfigurację CORS, aby umożliwić odpalenie frontendu za pomocą Chrome (pierwsze dwa nie działały jak należy).
- **Reworked reservation system to match our system on frontend**  
  Dodanie kilku pól w tabelach i encjach oraz ich obsługa.
- **New GetAvailableAlleys endpoint and minor fixes**  
  Dodanie endpointu zwracającego wolne tory na daną godzinę oraz małe poprawki w kodzie.
- **Changed GetAvailableAlleys to match front**  
  Małe zmiany w działaniu endpointu GetAvailableAlleys w celu dopasowania do frontendu.
- **Changed GetAvailableAlleys access, fixed GetAvailableAlleys access, fixed GetAllAlleys access**  
  Trzy jedno-liniowe commity naprawiające dostęp do endpointu GetAvailableAlleys (pierwsze dwa nie działały).
- **Made checkAvailability PathVariable**  
  Zmiana endpointu GetAvailableAlleys z RequestBody na PathVariable, ponieważ Flutter nie wspiera RequestBody dla GET.
- **Fixed available endpoint access**  
  Kolejna poprawka dla dostępu do endpointu z powodu zmienionego w poprzednim commicie adresu URL endpointu.
- **Fixed GetAlleyReservationHistory endpoint to return proper data**  
  Poprawienie endpointu zwracającego historię użytkownika, aby zwracał odpowiednie dane.
- **Added updateAlley endpoint**  
  Dodanie endpointu pozwalającego na edycję torów.
- **Fixed canceling reservation response, deleting alley response, added getUserRole endpoint**  
  Zmiana odpowiedzi na usuwanie rezerwacji oraz toru i dodanie endpointu zwracającego rolę użytkownika.