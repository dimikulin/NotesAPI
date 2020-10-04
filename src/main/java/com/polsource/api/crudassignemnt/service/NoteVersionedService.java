package com.polsource.api.crudassignemnt.service;

import com.polsource.api.crudassignemnt.entity.Note;
import com.polsource.api.crudassignemnt.entity.NoteVersioned;

import java.util.List;

public interface NoteVersionedService {
    public List<NoteVersioned> findById(Integer theNoteId);

    public void save(Note theNote);
}
