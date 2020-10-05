package com.polsource.api.crudassignemnt;

import com.polsource.api.crudassignemnt.entity.Note;
import com.polsource.api.crudassignemnt.service.NoteService;
import com.polsource.api.crudassignemnt.service.NoteVersionedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CrudassignemntApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudassignemntApplication.class, args);
	}

	@Autowired
	private NoteService noteService;

	@Autowired
	private NoteVersionedService noteVersionedService;

	@EventListener(ApplicationReadyEvent.class)
	public void init(){
		Note note1 = new Note("Piłkarzyki","To jest notatka o piłkarzykach");
		Note note2 = new Note("W pustyni i w puszczy","Ksiązka napisana przez Juliusza Słowackiego");
		Note note3 = new Note("Plan lekcji","1. Matematyka 2. W-F 3. Historia");

		noteService.save(note1);
		noteVersionedService.save(noteService.findById(note1.getId()));

		noteService.save(note2);
		noteVersionedService.save(noteService.findById(note2.getId()));

		noteService.save(note3);
		noteVersionedService.save(noteService.findById(note3.getId()));
	}
}
