package com.polsource.api.crudassignemnt;

import com.polsource.api.crudassignemnt.entity.Note;
import com.polsource.api.crudassignemnt.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;

import java.net.URI;
import java.net.URISyntaxException;


@SpringBootTest(classes = CrudassignemntApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteRESTControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // TESTS STATUS ADD NOTES
    @Test
    public void testAddEmployeeStatusOK() {
        Note note = new Note("Lokesh", "Guptdsadsadsadsada");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/notes", note, String.class);
        Assertions.assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddEmployeeTitleTooShort() {
        Note note = new Note("k", "Guptdsadsadsadsada");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/notes", note, String.class);
        Assertions.assertEquals(422, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddEmployeeTitleTooLong() {
        Note note = new Note("SSASASASASSASSASASASASASASASASASASSASASASASA", "Guptdsadsadsadsada");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/notes", note, String.class);
        Assertions.assertEquals(422, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddEmployeeContentTooShort() {
        Note note = new Note("wwww", "Gup");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/notes", note, String.class);
        Assertions.assertEquals(422, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddEmployeeContentTooLong() {
        Note note = new Note("wwww", "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff" +
                "Gupsssssssawertrewffffff");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/notes", note, String.class);
        Assertions.assertEquals(422, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddEmployeeTitleAndContentEmpty() {
        Note note = new Note("", "");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/notes", note, String.class);
        Assertions.assertEquals(422, responseEntity.getStatusCodeValue());
    }

    // TESTS STATUS GET NOTES

    @Test
    public void testGetNotesListSuccess() throws URISyntaxException
    {
        final String baseUrl = "http://localhost:" + port + "/api/notes";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testGetParticularNoteSuccess(){
        Note note = new Note("wwww", "Gupdsadsadsadsadadsdas");
        ResponseEntity<Note> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/api/notes/1", Note.class);
        Assertions.assertEquals("Piłkarzyki", responseEntity.getBody().getTitle());
    }

    @Test
    public void testGetParticularNoteNotFound(){
        Note note = new Note("wwww", "Gupdsadsadsadsadadsdas");
        ResponseEntity<Note> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/api/notes/12345", Note.class);
        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
    }

}
