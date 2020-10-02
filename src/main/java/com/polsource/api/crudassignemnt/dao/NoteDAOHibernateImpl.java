package com.polsource.api.crudassignemnt.dao;

import com.polsource.api.crudassignemnt.entity.Note;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class NoteDAOHibernateImpl implements NoteDAO{
    private EntityManager entityManager;

    @Autowired
    public NoteDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Note> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("from Note");
        List<Note> notes = theQuery.getResultList();
        return notes;
    }

    @Override
    public Note findById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Note theNote = currentSession.get(Note.class, theId);
        return theNote;
    }

    @Override
    public void save(Note theNote) {
        Session currentSession = entityManager.unwrap(Session.class);
        int versionNumber = theNote.getVersionNumber();
        versionNumber++;
        theNote.setCreated(new Date(System.currentTimeMillis()));
        theNote.setModified(theNote.getCreated());
        theNote.setVersionNumber(versionNumber);
        currentSession.saveOrUpdate(theNote);
    }

    @Override
    public void deleteById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Note theNote = currentSession.get(Note.class, theId);
        currentSession.delete(theNote);
    }
}
