package lk.ijse.gdse68.notetraker.controller;

import lk.ijse.gdse68.notetraker.dto.NoteDTO;
import lk.ijse.gdse68.notetraker.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    @Autowired
    private final NoteService noteService;
    //Todo: CRUD of the note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody NoteDTO note) {
        //Todo: Handle with Service
        var saveData = noteService.saveNote(note);
        return ResponseEntity.ok(saveData);
    }
    @GetMapping(value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes(){
        return noteService.getAllNotes();
    }

    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO getNote(@PathVariable ("noteId") String noteId)  {
        return noteService.getSelectedNote(noteId);
    }
    @PatchMapping(value = "/{noteId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO note) {
        noteService.updateNote(noteId, note);
    }
    @DeleteMapping(value ="/{noteId}" )
    public void deleteNote(@PathVariable ("noteId") String noteId) {
        noteService.deleteNote(noteId);
    }
}