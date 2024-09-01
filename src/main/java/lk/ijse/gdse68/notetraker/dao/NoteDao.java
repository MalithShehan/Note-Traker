package lk.ijse.gdse68.notetraker.dao;

import lk.ijse.gdse68.notetraker.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends JpaRepository<NoteEntity, String> {
    
}
