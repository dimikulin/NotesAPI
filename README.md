# Notes API

RESTful API webservice that manages and stores in database simple notes.
In this assignment, I haven't created additinal webservice. I have created additional endpoint, that return whole history of changes for particular note.

## Description

The main functions of the webservice are:
* adding notes (title, content),
* editing notes,
* deleting notes,
* displaying all notes and particular note,
* displaying whole history of changes of particular notes.

## Technologies
Project is created with:
* Java (version 14.0.2),
* Spring Boot,
* Hibernate,
* Maven,
* H2 Database.

## Setup
You can launch the program in two ways:
* by downloading the project via e.g. IntellIJ and starting the application from there,
* by launching crudassignemnt.jar (/out/artifacts/crudassignemnt_jar)

You don't need to configure the database because the interface uses H2 Database.

## Endpoints

The most common HTTP status codes are returned when there is an error.

SR. No. |	API Name | HTTP Method	| Path	| Status Code	| Description 
--- | --- | --- | --- |--- |--- |
(1)	| GET Notes	| GET	| http://localhost:8080/api/notes	| 200 (OK)	| Get all notes
(2) |	POST Note	| POST	| http://localhost:8080/api/notes	| 201 (Created)	| Add a note
(3) |	GET Note	| GET	| http://localhost:8080/api/notes/{idNote}	| 200 (OK)	| Get a particular note
(4) |	PUT Note	| PUT | http://localhost:8080/api/notes	| 200 (OK)	| Update a note
(5) |	DELETE Note	| DELETE	|http://localhost:8080/api/notes/{idNote}	| 200 (OK)	| Delete a note
(6) |	GET Note Versioned	| GET	|http://localhost:8080/api/notesVersioned/{idNote}	| 200 (OK)	| Get a version history note



### Add note

Minimum title length is 3 and maximum length is 30. Minimum content length is 10 and maximum length is 255.

```
/api/notes [POST]

Content-Type: application/json

{
  "title" : "Football",
  "content" : "PSG are expected to announce the €4m loan with €16m obligation to buy signing of 29-year-old defensive midfielder Danilo Pereira from Porto."
}
```


Example of use:

![zdjecie](https://user-images.githubusercontent.com/45290627/95105729-df8baa00-0737-11eb-8234-b8ae423a8a54.JPG)

### Get a particular note

```
/api/notes/{idNote} [GET]
```

Example of use:

![image](https://user-images.githubusercontent.com/45290627/95105849-05b14a00-0738-11eb-9795-ae9ddfbaa388.png)

### Get all notes

```
/api/notes [GET]
```

Example of use:

![image](https://user-images.githubusercontent.com/45290627/95106062-4f9a3000-0738-11eb-81c1-ab3bee9cf1f3.png)

### Update a note

You cannot update a note without making changes.

```
/api/notes [PUT]

Content-Type: application/json

{
  "id" : 1,
  "title" : "Champions League",
  "content" : "Real Madrid won the UEFA Champions League trophy in the 2016/2017 season."
}
```

Example of use:

![image](https://user-images.githubusercontent.com/45290627/95106256-9556f880-0738-11eb-9f8e-ce4c5a150343.png)

### Delete a note

```
/api/notes/{idNote} [DELETE]
```
Example of use:

![image](https://user-images.githubusercontent.com/45290627/95106343-b15a9a00-0738-11eb-8a2d-52477970afc4.png)

### Get version history of particular note

```
/api/notesVersioned/{idNote} [GET]
```
Example of use:

![image](https://user-images.githubusercontent.com/45290627/95106476-dcdd8480-0738-11eb-9ff4-c3682df4b967.png)
