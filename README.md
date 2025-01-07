# Biblioteca Virtuale API

Benvenuto nella Biblioteca Virtuale API! Questo progetto è un'implementazione di un'API per una biblioteca virtuale, costruita con **Java Spring Boot**. L'applicazione consente la gestione di utenti e libri, con funzionalità avanzate per la ricerca e il filtraggio.

## Funzionalità principali

- **Gestione degli utenti**: Creazione, aggiornamento e visualizzazione degli utenti. Ogni utente ha una lista di libri presi in prestito.
- **Gestione dei libri**: Creazione, aggiornamento e visualizzazione dei libri, con la possibilità di filtrare per:
  - **Autore**
  - **Categoria**
  - **Disponibilità**
- **Paginazione**: Tutti gli endpoint supportano la paginazione per una migliore esperienza con dataset di grandi dimensioni.
- **Filtri dinamici**: Ricerca avanzata con parametri flessibili per soddisfare le esigenze dell'utente.

## Tecnologie utilizzate

- **Java**: Linguaggio di programmazione principale.
- **Spring Boot**: Framework per la costruzione dell'API REST.
- **H2 Database**: Database relazionale in memoria per lo sviluppo e il testing (configurabile con altri database).

## Struttura del progetto

### Entità principali

- **User**: Ogni utente ha un ID univoco, un nome e una lista di libri attualmente presi in prestito.
- **Book**: Ogni libro ha un ID univoco, titolo, autore, categoria, stato di disponibilità e numero di libri disponibili.

### Endpoint principali

#### User
- `GET /users`: Restituisce una lista di utenti (paginata).
- `POST /users/register`: Crea un nuovo utente.
- `GET /users/{username}`: Restituisce i dettagli di un utente specifico.
- `PUT /users/{id}`: Aggiorna le informazioni di un utente.
- `DELETE /users/{id}`: Elimina un utente dal database.

#### Book
- `GET /books`: Restituisce una lista di tutti i libri (paginata).
- `GET /books/search`: Restituisce una lista di libri coerente con i filtri dinamici specificati dai parametri query (paginata).
- `POST /books/addBook`: Aggiunge un nuovo libro al catalogo.
- `GET /books/{id}`: Restituisce i dettagli di un libro specifico.
- `PUT /books/{id}`: Aggiorna le informazioni di un libro.
- `DELETE /books/{id}`: Elimina un libro dal database.

#### Library
- `GET /library/{userId}/assignBook`: Assegna all'utente con l'userId specificato il libro con il bookId da specificare nei parametri query.
- `GET /library/{userId}/returnBook`: Toglie all'utente con l'userId specificato il libro con il bookId da specificare nei parametri query.

### Filtraggio dinamico per i libri
Gli endpoint dei libri supportano i seguenti parametri di query:
- `author`: Filtra i libri per autore.
- `genre`: Filtra i libri per categoria.
- `isAvailable`: Filtra per disponibilità (per visualizzare solo quelli disponibili)

Esempio: GET /books?author=Umberto+Eco&isAvailable=true


## Setup e avvio del progetto

1. Clona il repository:
   ```bash
   git clone https://github.com/tuo-utente/BibliotecaVirtuale-API.git

2. Accedi alla directory del progetto:
   ```bash
   cd BibliotecaVirtuale-API

3. Assicurati di avere Java e Spring installati correttamente sul PC.
   ```bash
   java -version
   javac -version
   spring --version

3. Compila ed esegui il progetto:
   ```bash
   ./mvnw spring-boot:run

4. L'Applicazione sara disponibile su `http://localhost:8080`

5. Per configurare un database diverso da H2, modifica il file `application.properties` situato in `src/main/resources` con i dettagli del tuo database.

### Testing
Puoi utilizzare strumenti come Postman o cURL per testare gli endpoint. Sono inclusi anche test unitari per verificare il corretto funzionamento delle principali funzionalità.

### Contributi
Contributi, segnalazioni di bug e suggerimenti sono i benvenuti! Sentiti libero di aprire una issue o inviare una pull request.

### Licenza
Questo progetto è rilasciato sotto la licenza MIT. Per ulteriori dettagli, consulta il file LICENSE.

🎉 Grazie per aver scelto Biblioteca Virtuale API!
