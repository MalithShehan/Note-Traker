package lk.ijse.gdse68.notetraker.service;

import lk.ijse.gdse68.notetraker.dao.NoteDao;
import lk.ijse.gdse68.notetraker.dto.NoteDTO;

import lk.ijse.gdse68.notetraker.entity.NoteEntity;
import lk.ijse.gdse68.notetraker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<NoteEntity> tmpNoteEntity = noteDao.findById(noteId);
        if (!tmpNoteEntity.isPresent()) {
            return false;
        } else {
            tmpNoteEntity.get().setNoteTitle(incomeNoteDTO.getNoteTitle());
            tmpNoteEntity.get().setNoteDesc(incomeNoteDTO.getNoteDesc());
            tmpNoteEntity.get().setPriorityLevel(incomeNoteDTO.getPriorityLevel());
            tmpNoteEntity.get().setCreateDate(incomeNoteDTO.getCreateDate());
        }
        return true;
    }

    @Override
    public boolean deleteNote(String noteId) {
//        noteDao.deleteById(noteId);
        if (noteDao.existsById(noteId)) {
            noteDao.deleteById(noteId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {
        return mapping.convertToDTO( noteDao.getReferenceById(noteId));

    }


    @Override
    public List<NoteDTO> getAllNotes() {
//        List<NoteEntity> getAllNotes = noteDao.findAll();
//        List<NoteDTO> noteDTOS = mapping.convertToDTO(getAllNotes);
//        return noteDTOS;

        return mapping.convertToDTO(noteDao.findAll());
    }
}
