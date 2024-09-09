package lk.ijse.gdse68.notetraker.service;

import lk.ijse.gdse68.notetraker.customerObject.UserResponse;
import lk.ijse.gdse68.notetraker.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
}
