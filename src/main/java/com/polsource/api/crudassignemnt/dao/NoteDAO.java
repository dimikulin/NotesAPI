package com.polsource.api.crudassignemnt.dao;

import com.polsource.api.crudassignemnt.entity.Note;

import java.util.List;

public interface NoteDAO {
    public List<Note> findAll();

    public Note findById(int theId);

    public void save(Note theNote);

    public void deleteById(int theId);
}
