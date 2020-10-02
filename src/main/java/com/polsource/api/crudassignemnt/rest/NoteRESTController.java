package com.polsource.api.crudassignemnt.rest;

import com.polsource.api.crudassignemnt.dao.NoteDAO;
import com.polsource.api.crudassignemnt.entity.Note;
import com.polsource.api.crudassignemnt.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteRESTController {
    private NoteService noteService;

    @Autowired
    public NoteRESTController(NoteService theNoteService){
        this.noteService = theNoteService;
    }

    @GetMapping("/notes")
    public List<Note> findAll(){
        return noteService.findAll();
    }

    @GetMapping("/notes/{noteId}")
    public Note getNote(@PathVariable int noteId){
        Note theNote = noteService.findById(noteId);
        if(theNote == null){
            throw new RuntimeException("Note id=" + noteId + " not found");
        }
        return theNote;
    }

    @PostMapping("/notes")
    public Note addNote(@RequestBody Note theNote){
        theNote.setId(0);
        noteService.save(theNote);
        return theNote;
    }
}
