# Notes API

RESTful API webservice that manages and stores in database simple notes.

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

## Endpoints

The most common HTTP status codes are returned when there is an error.

### Add note

```
/api/notes [POST]

Content-Type: application/json

{
  "title" : "Football",
  "content" : "PSG are expected to announce the €4m loan with €16m obligation to buy signing of 29-year-old defensive midfielder Danilo Pereira from Porto."
}
```

### Get a particular note

```
/api/notes/{idNote} [GET]
```

### Get all notes

```
/api/notes [GET]
```

### Update a note

```
/api/notes [PUT]

{
  "id" : 1,
  "title" : "Champions League",
  "content" : "Real Madrid won the UEFA Champions League trophy in the 2016/2017 season."
}
```

### Delete a note

```
/api/notes/{idNote} [DELETE]
```

### Get version history of particular note

```
/api/notesVersioned/{idNote} [GET]
```
