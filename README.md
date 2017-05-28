# JAVA: RESTful Web Services Application – Vanzare Bilete

### Servicii implementate conform cerintei

 * /list_events GET - Pentru obtinerea tuturor evenimentelor
 * /buy_ticket POST - Pentru cumpararea unui bilet
 * /ticket_details GET - Pentru obtinerea detaliilor despre un bilet existent
 * /cancel_ticket POST - Pentru anularea unui bilet

### Servicii suplimentare

* /events POST - Pentru adaugarea unui eveniment
* /tickets GET - Pentru obtinerea tuturor biletelor

### Conectarea la baza de date

Detaliile evenimentelor si ticketelor sunt salvate intr-o baza de date MongoDB in colectiile **events** si **tickets**.

Conectarea la baza de date se face prin intermediul clasei *MongoConnection*:
```java
private MongoClientURI connectionString = new MongoClientURI(
			"mongodb://" + user + ":" + password + "@ds151661.mlab.com:51661/ubisapp");
private MongoClient mongoClient = new MongoClient(connectionString);
private MongoDatabase database = mongoClient.getDatabase("ubisapp");
```

Metoda *getConnection* returneaza fie colectia **events**, fie colectia **tickets** in functie de valoarea parametrului *type*.

### Exemple utilizare servicii

#### /list_events

Returneaza in format XML evenimentele din baza de date.

![list_events](https://cloud.githubusercontent.com/assets/16063287/26532514/3988d31e-440b-11e7-9dd7-4e562b9dc393.PNG)

#### /buy_ticket

Introduce un ticket in baza de date.

1) Este returnat **failure** pentru ca nu exista niciun eveniment cu *eventId*=4 in colectie.

![buy_ticket_fail](https://cloud.githubusercontent.com/assets/16063287/26532512/39878b94-440b-11e7-9644-058a1b442bac.PNG)


2) Este returnat **success**, iar biletul este introdus in baza de date.

![buy_ticket_succes](https://cloud.githubusercontent.com/assets/16063287/26532517/39cf2fda-440b-11e7-9314-f639317d6c76.PNG)


#### /ticket_details

Returneaza in format XML detaliile biletului identificat de *ticketId*.

![ticket_details](https://cloud.githubusercontent.com/assets/16063287/26532513/3987fcdc-440b-11e7-8926-44a1000befd3.PNG)


#### /cancel_ticket

Sterge din baza de date biletul identificat de *ticketId*.

1) Este returnat **failure** pentru ca nu exista niciun bilet cu *ticketId*=4 in colectie.

![cancel_ticket](https://cloud.githubusercontent.com/assets/16063287/26532515/39a3e73a-440b-11e7-9ff6-5597c7df462f.PNG)


2) Este returnat **succes**, iar biletul este sters din baza de date.

![cancel_ticket](https://cloud.githubusercontent.com/assets/16063287/26532516/39a69638-440b-11e7-8c94-122f12d83324.PNG)


