package com.polsource.api.crudassignemnt.dao;

import com.polsource.api.crudassignemnt.entity.Note;
import com.polsource.api.crudassignemnt.entity.NoteVersioned;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class NoteVersionedHibernateImpl implements NoteVersionedDAO{

    private EntityManager entityManager;

    @Autowired
    public NoteVersionedHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<NoteVersioned> findById(Integer theNoteId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("from NoteVersioned where idNote="+theNoteId);
        List<NoteVersioned> notes = theQuery.getResultList();
        if(notes.isEmpty()){
                throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Note id="+theNoteId+" not found");
        }
        return notes;
    }

    @Override
    public void save(Note theNote) {
        Session currentSession = entityManager.unwrap(Session.class);
        NoteVersioned noteVersioned = new NoteVersioned(theNote.getId(),theNote.getTitle(),theNote.getContent(), theNote.getCreated(), theNote.getModified());
        currentSession.saveOrUpdate(noteVersioned);
    }
    }

