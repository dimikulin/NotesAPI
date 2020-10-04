package com.polsource.api.crudassignemnt.service;

import com.polsource.api.crudassignemnt.entity.Note;

import java.util.List;

public interface NoteService {
    public List<Note> findAll();

    public Note findById(int theId);

    public void save(Note theNote);

    public void deleteById(int theId);

    public void update(Note theNote);
}
