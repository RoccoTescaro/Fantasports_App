# FantaSports_App

Questo progetto ha l'obiettivo di realizzare un'applicazione web per la gestione di campionati Fanta-sports, in particolare di calcio.

__Funzionalità__:

- Creazione di leghe private di fanta-sports
- Invito di altri utenti a partecipare
- Avvio di aste e composizione di squadre
- Definizione di modalità di punteggio e regole del campionato
- Visualizzazione della classifica e dei risultati delle partite

__Requisiti__:

 - Java 8+
 - Maven
 - Spring Boot

__Struttura__:

La repository github è divisa in due parti principali:
nella cartella ```src/``` si trovano i file Java strettamente necessari ad eseguire il progetto, divisa in ```src/main``` e ```src/test``` rispettivamente per il codice sorgente e per i test.

Nella cartella principale (come ```pom.xml```, ```mvnw```, ecc.) sono presenti i file di configurazione e di 
gestione del progetto senza i quali non sarebbe possibile eseguirlo.

Nella cartella ```relation/``` sono presenti i file di relazione del progetto.

___

Come eseguire:

Clona il repository:
```Bash

git clone https://github.com/RoccoTescaro/Fantasports_App.git
```

Naviga nella directory del progetto:

```Bash

cd FantaSports_App
```


Esegui l'applicazione:

```Bash

./mvnw -q spring-boot:run
```

Apri un browser web e naviga su: ``` http://localhost:8080/ ```

Accesso all'applicazione:

L'applicazione è accessibile all'indirizzo http://localhost:8080/.

___

__Note__:

>Assicurati di avere installato Java 8+ e Maven sul tuo sistema. Se hai modificato il codice, dovrai riavviare l'applicazione per vedere le modifiche.

__Contributi__:

Sei il benvenuto per contribuire a questo progetto! Per maggiori informazioni su come contribuire, consulta il repository GitHub.