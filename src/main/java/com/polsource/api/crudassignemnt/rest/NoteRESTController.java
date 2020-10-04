package com.polsource.api.crudassignemnt.rest;

import com.polsource.api.crudassignemnt.dao.NoteDAO;
import com.polsource.api.crudassignemnt.entity.Note;
import com.polsource.api.crudassignemnt.entity.NoteVersioned;
import com.polsource.api.crudassignemnt.service.NoteService;
import com.polsource.api.crudassignemnt.service.NoteVersionedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    @GetMapping("/notesVersioned/{noteId}")
    public List<NoteVersioned> findAll(@PathVariable int noteId){
        return noteVersionedService.findById(noteId);
    }

    // display particular Note
    @GetMapping("/notes/{noteId}")
    public Note getNote(@PathVariable int noteId){
        Note theNote = noteService.findById(noteId);
        if(theNote == null){
            throw new RuntimeException("Note id=" + noteId + " not found");
        }
        return theNote;
    }

    // create Note
    @PostMapping("/notes")
    public Note addNote(@RequestBody Note theNote){
        //theNote.setId(0);
        noteService.save(theNote);
        noteVersionedService.save(noteService.findById(theNote.getId()));
        return theNote;
    }

    // update Note
    @PutMapping("/notes")
    public Note updateNote(@RequestBody Note theNote){
        Note thisNote = noteService.findById(theNote.getId());
        if(thisNote == null){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Note not found");
        }
        noteService.update(theNote);
        noteVersionedService.save(theNote);
        return  theNote;
    }

    // delete Note
    @DeleteMapping("/notes/{noteId}")
    public String deleteNote(@PathVariable int noteId){
        Note theNote = noteService.findById(noteId);
        if(theNote == null){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Note id="+noteId+" not found");
        }
        noteService.deleteById(noteId);
        return "Deleted note id="+noteId;
    }
}
