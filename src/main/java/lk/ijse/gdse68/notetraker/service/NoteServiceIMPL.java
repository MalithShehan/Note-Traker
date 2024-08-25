package lk.ijse.gdse68.notetraker.service;

import lk.ijse.gdse68.notetraker.dto.NoteDTO;
import lk.ijse.gdse68.notetraker.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public final class NoteServiceIMPL implements NoteService {
    List<NoteDTO> saveNoteTmp = new ArrayList<>();
    public NoteServiceIMPL() {
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of SE","SE Desc","P1","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a68-3ccc-41b2-9de6-4e9bcdba65bb","Priciples of CS","CS Desc","P2","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a069-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of NW","NW Desc","P1","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a70-2ebc-41b2-9de6-4e9ddbbba65b","Priciples of UI","UI Desc","P2","20240825"));
        System.out.println(saveNoteTmp);
    }
    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNoteId());
        saveNoteTmp.add(noteDTO);
        return "Saved successfully in Service layer";
    }

    @Override
    public boolean updateNote(String noteId, NoteDTO noteDTO) {
        ListIterator<NoteDTO> updateList = saveNoteTmp.listIterator();
        while (updateList.hasNext()){
            NoteDTO noteDto = updateList.next();
            if (noteDto.getNoteId().equals(noteId)) {
                updateList.set(noteDTO);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteNote(String noteId) {
        ListIterator<NoteDTO> tmpList = saveNoteTmp.listIterator();
        while (tmpList.hasNext()) {
            NoteDTO noteDTO = tmpList.next();
            if (noteId.equals(noteDTO.getNoteId())) {
                tmpList.remove();
                return true;
            }
        }
        return false;
    }


    @Override
    public NoteDTO getSelectedNote(String noteId) {
        for (NoteDTO noteDTO : saveNoteTmp) {
            if (noteDTO.getNoteId().equals(noteId)){
                return noteDTO;
            }
        }
        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return saveNoteTmp;
    }
}
