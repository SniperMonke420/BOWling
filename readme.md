System do rezerwacji torów na kręgielni

1. Przed rozpoczęciem upewnij się, że masz zainstalowane następujące narzędzia:

Java Development Kit (JDK): Wersja 17.0.5.
- Pobierz JDK 17 z Oracle lub OpenJDK.
- Skonfiguruj zmienną środowiskową JAVA_HOME.
- Zweryfikuj instalację, wpisując w terminalu:
java -version
Oczekiwany wynik:
openjdk version "17.0.5"

Gradle: Narzędzie do budowania projektu.
- Jeśli Gradle nie jest zainstalowany, pobierz go tutaj: https://gradle.org/releases/.
- Możesz też użyć wbudowanego wrappera Gradle w projekcie (./gradlew).
- Zweryfikuj instalację:
gradle -v

Docker: Do uruchomienia bazy danych PostgreSQL.
Pobierz i zainstaluj Docker desktop: https://www.docker.com/products/docker-desktop/.

2. Pobranie projektu z GitHub
- Skopiuj adres URL repozytorium z GitHuba: https://github.com/SniperMonke420/BOWling
- Otwórz IDE(np. Intellij) i w terminalu wpisz: git clone https://github.com/SniperMonke420/BOWling

3. Uruchomienie bazy danych PostgreSQL w Dockerze
- Uruchom kontener PostgreSQL za pomocą poniższej komendy:
docker run --name BOWling_postgres -e POSTGRES_PASSWORD=post123gres -p 5443:5432 -d postgres
- Zweryfikuj, czy kontener działa:
docker ps
- Opcjonalnie: Połącz się z bazą danych za pomocą klienta PostgreSQL(np. DBeaver):
Host: localhost
Port: 5443
Użytkownik: postgres
Hasło: post123gres

4. Budowanie projektu za pomocą Gradle
Użyj Gradle Wrapper (./gradlew) do budowania projektu, aby zapewnić spójność wersji Gradle:
./gradlew clean build

5. Aplikacja powinna być gotowa do uruchomienia na twoim urządzeniu
