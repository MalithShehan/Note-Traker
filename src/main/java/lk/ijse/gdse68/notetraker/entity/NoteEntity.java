package lk.ijse.gdse68.notetraker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "notes")
public class NoteEntity implements SuperEntity{
    @Id
    private String noteId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
}
