package lk.ijse.gdse68.notetraker.service;

import lk.ijse.gdse68.notetraker.dto.UserDTO;

import java.util.List;

public interface UserService {
    String saveUser(UserDTO userDTO);
    boolean updateUser(String userId,UserDTO userDTO);
    boolean deleteUser(String userId);
    UserDTO getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
}
