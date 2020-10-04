package com.polsource.api.crudassignemnt.service;

import com.polsource.api.crudassignemnt.dao.NoteDAO;
import com.polsource.api.crudassignemnt.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    private NoteDAO noteDAO;

    @Autowired
    public NoteServiceImpl(NoteDAO theNoteDAO) {
        this.noteDAO = theNoteDAO;
    }

    @Override
    @Transactional
    public List<Note> findAll() {
        return noteDAO.findAll();
    }

    @Override
    @Transactional
    public Note findById(int theId) {
        return noteDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Note theNote) {
        noteDAO.save(theNote);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        noteDAO.deleteById(theId);
    }

    @Override
    @Transactional
    public void update(Note theNote) {
        noteDAO.update(theNote);
    }
}
