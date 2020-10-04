package com.polsource.api.crudassignemnt.dao;

import com.polsource.api.crudassignemnt.entity.Note;
import com.polsource.api.crudassignemnt.entity.NoteVersioned;

import java.util.List;

public interface NoteVersionedDAO {
    public List<NoteVersioned> findById(Integer theNoteId);

    public void save(Note theNote);
}
