package lk.ijse.gdse68.notetraker.service.impl;

import lk.ijse.gdse68.notetraker.dao.UserDao;
import lk.ijse.gdse68.notetraker.dto.UserDTO;
import lk.ijse.gdse68.notetraker.entity.UserEntity;
import lk.ijse.gdse68.notetraker.exseption.UserNotFoundException;
import lk.ijse.gdse68.notetraker.service.UserService;
import lk.ijse.gdse68.notetraker.util.AppUtil;
import lk.ijse.gdse68.notetraker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    @Autowired
    private final UserDao userDao;

    @Autowired
    private final Mapping mapping;

    @Override
    public String saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        UserEntity userEntity = userDao.save(mapping.convertToUserEntity(userDTO));
        if (userEntity != null && userEntity.getUserId() != null) {
            return "User saved successfully!";
        } else {
            return "Save Faild!";
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
            Optional<UserEntity> tmpUser = userDao.findById(userDTO.getUserId());
            if (!tmpUser.isPresent()) {
                throw new UserNotFoundException("User not found");
            } else {
                tmpUser.get().setFirstName(userDTO.getFirstName());
                tmpUser.get().setLastName(userDTO.getLastName());
                tmpUser.get().setEmail(userDTO.getEmail());
                tmpUser.get().setPassword(userDTO.getPassword());
                tmpUser.get().setProfilePic(userDTO.getProfilePic());
            }
    }


    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> selectedUserId = userDao.findById(userId);
        if(selectedUserId.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            userDao.deleteById(userId);
        }
    }


    @Override
    public UserDTO getSelectedUser(String userId) {
        UserEntity userEntityByUserId = userDao.getUserEntityByUserId(userId);
        return mapping.convertToUserDTOList(userEntityByUserId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapping.convertUserToDTO(userDao.findAll());
    }
}
