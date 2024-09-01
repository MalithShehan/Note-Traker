package lk.ijse.gdse68.notetraker.util;

import lk.ijse.gdse68.notetraker.dto.NoteDTO;
import lk.ijse.gdse68.notetraker.entity.NoteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired  
    private ModelMapper modelMapper;

    //mapping
    //Note Details
    public NoteDTO convertToDTO(NoteEntity note) {
        return modelMapper.map(note, NoteDTO.class);
    }

    public NoteEntity convertToEntity(NoteDTO dto) {
        return modelMapper.map(dto, NoteEntity.class);
    }

    public List<NoteDTO> convertToDTO(List<NoteEntity> notes) {
        return modelMapper.map(notes, List.class);
    }
}
