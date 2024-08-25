package lk.ijse.gdse68.notetraker.service;

import lk.ijse.gdse68.notetraker.dao.NoteDao;
import lk.ijse.gdse68.notetraker.dto.NoteDTO;

import lk.ijse.gdse68.notetraker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public  class NoteServiceIMPL implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private Mapping mapping;

    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDao.save(mapping.convertToEntity(noteDTO));
        var noteEntity = mapping.convertToEntity(noteDTO);
        noteDao.save(noteEntity);
        return "Saved Successfully in Service layer";
    }

    @Override
    public boolean updateNote(String noteId, NoteDTO incomeNoteDTO) {


        return false;
    }

    @Override
    public boolean deleteNote(String noteId) {


        return false;
    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {
        return null;
    }


    @Override
    public List<NoteDTO> getAllNotes() {
        return null;
    }
}
