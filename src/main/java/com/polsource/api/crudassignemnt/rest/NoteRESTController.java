package com.polsource.api.crudassignemnt.rest;

import com.polsource.api.crudassignemnt.entity.Note;
import com.polsource.api.crudassignemnt.entity.NoteVersioned;
import com.polsource.api.crudassignemnt.service.NoteService;
import com.polsource.api.crudassignemnt.service.NoteVersionedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteRESTController {

    private NoteService noteService;
    private NoteVersionedService noteVersionedService;

    @Autowired
    public NoteRESTController(NoteService theNoteService, NoteVersionedService theNoteVersionedService){
        this.noteService = theNoteService;
        this.noteVersionedService = theNoteVersionedService;
    }

    // display all Notes
    @GetMapping("/notes")
    public List<Note> findAll(){
        return noteService.findAll();
    }

    // display version history of particular note
    @GetMapping("/notesVersioned/{noteId}")
    public List<NoteVersioned> findAll(@PathVariable int noteId){
        return noteVersionedService.findById(noteId);
    }

    // display particular Note
    @GetMapping("/notes/{noteId}")
    public Note getNote(@PathVariable int noteId){
        Note theNote = noteService.findById(noteId);
        if(theNote == null){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Note id ="+noteId+" not found");
        }
        return theNote;
    }

    // create Note
    @PostMapping("/notes")
    public ResponseEntity<String> addNote(@RequestBody Note theNote){
        noteService.save(theNote);
        noteVersionedService.save(noteService.findById(theNote.getId()));
        return new ResponseEntity<>("Note was added!", HttpStatus.CREATED);
    }

    // update Note
    @PutMapping("/notes")
    public ResponseEntity<String> updateNote(@RequestBody Note theNote){
        Note thisNote = noteService.findById(theNote.getId());
        if(thisNote == null){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Note not found");
        }
        if(theNote.getContent().equals(thisNote.getContent()) && theNote.getTitle().equals(thisNote.getTitle())){
            throw new ResponseStatusException( HttpStatus.UNPROCESSABLE_ENTITY, "No changes have been made");
        }
        noteService.update(theNote); // update Note
        noteVersionedService.save(theNote); // save note versioned to version history
        return new ResponseEntity<>("Note was modified!", HttpStatus.OK);
    }

    // delete Note
    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable int noteId){
        Note theNote = noteService.findById(noteId);
        if(theNote == null){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Note id="+noteId+" not found");
        }
        noteService.deleteById(noteId);
        return new ResponseEntity<>("Deleted note id = "+noteId, HttpStatus.OK);
    }
}
