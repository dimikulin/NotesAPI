package com.polsource.api.crudassignemnt.entity;

import javax.persistence.*;

@Entity
@Table(name="noteversioned")
public class NoteVersioned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idNote;

    private String title;

    private String content;

    private String created;

    private String modified;

    public NoteVersioned(){}

    public NoteVersioned(Integer idNote, String title, String content, String created, String modified) {
        this.idNote = idNote;
        this.title = title;
        this.content = content;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "NoteVersioned{" +
                ", idNote=" + idNote +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                '}';
    }
}
