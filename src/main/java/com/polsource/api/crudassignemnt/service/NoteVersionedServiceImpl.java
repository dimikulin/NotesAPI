package com.polsource.api.crudassignemnt.service;

import com.polsource.api.crudassignemnt.dao.NoteVersionedDAO;
import com.polsource.api.crudassignemnt.entity.Note;
import com.polsource.api.crudassignemnt.entity.NoteVersioned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteVersionedServiceImpl implements NoteVersionedService{

    NoteVersionedDAO noteVersionedDAO;

    @Autowired
    public NoteVersionedServiceImpl(NoteVersionedDAO theNoteVersionedDAO){
        this.noteVersionedDAO = theNoteVersionedDAO;
    }

    @Transactional
    @Override
    public List<NoteVersioned> findById(Integer theNoteId) {
        return noteVersionedDAO.findById(theNoteId);
    }

    @Transactional
    @Override
    public void save(Note theNote) {
        noteVersionedDAO.save(theNote);
    }
}
