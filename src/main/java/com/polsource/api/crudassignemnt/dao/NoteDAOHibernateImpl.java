package com.polsource.api.crudassignemnt.dao;

import com.polsource.api.crudassignemnt.entity.Note;
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

        if(theNote.getTitle() == null || theNote.getTitle().isEmpty() || theNote.getContent() == null || theNote.getContent().isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Required fields aren't filled");
        }

        if(theNote.getCreated()!=null || theNote.getModified() != null || theNote.getId()!=null || theNote.getVersionNumber()!=null){
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "This field is read-only");
        }

        if(theNote.getTitle().length()<3){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The minimum title length is 3");
        }else if(theNote.getTitle().length()>30){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The maximum title length is 30");
        }

        if(theNote.getContent().length()<10){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The minimum content length is 10");
        }else if(theNote.getContent().length()>255){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The maximum content length is 255");
        }

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);
        theNote.setCreated(strDate);
        theNote.setModified(strDate);
        theNote.setVersionNumber(1);
        currentSession.saveOrUpdate(theNote);
    }

    @Override
    public void deleteById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Note theNote = currentSession.get(Note.class, theId);
        currentSession.delete(theNote);
    }

    @Override
    public void update(Note theNote) {
        Session currentSession = entityManager.unwrap(Session.class);

        if(theNote.getTitle() == null || theNote.getTitle().isEmpty() || theNote.getContent() == null || theNote.getContent().isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Required fields aren't filled");
        }

        if(theNote.getCreated()!=null || theNote.getModified() != null || theNote.getVersionNumber()!=null){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "This field is read-only");
        }

        if(theNote.getTitle().length()<3){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The minimum title length is 3");
        }else if(theNote.getTitle().length()>30){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The maximum title length is 30");
        }

        if(theNote.getContent().length()<10){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The minimum content length is 10");
        }else if(theNote.getContent().length()>255){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The maximum content length is 255");
        }

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);
        theNote.setCreated(findById(theNote.getId()).getCreated());
        theNote.setModified(strDate);
        theNote.setVersionNumber(findById(theNote.getId()).getVersionNumber()+1);
        currentSession.merge(theNote);
    }
}
