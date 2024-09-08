package lk.ijse.gdse68.notetraker.service.impl;

import lk.ijse.gdse68.notetraker.dao.UserDao;
import lk.ijse.gdse68.notetraker.dto.UserDTO;
import lk.ijse.gdse68.notetraker.entity.UserEntity;
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
        userDao.save(mapping.convertToUserEntity(userDTO));
        return "User saved successfully";
    }

    @Override
    public boolean updateUser( UserDTO userDTO)  {
        Optional<UserEntity> tmpUserEntity = userDao.findById(userDTO.getUserId());
        if (!tmpUserEntity.isPresent()) {
            return false;
        } else {
            tmpUserEntity.get().setFirstName(userDTO.getFirstName());
            tmpUserEntity.get().setLastName(userDTO.getLastName());
            tmpUserEntity.get().setEmail(userDTO.getEmail());
            tmpUserEntity.get().setPassword(userDTO.getPassword());
            tmpUserEntity.get().setProfilePic(userDTO.getProfilePic());
        }
        return true;
    }

    @Override
    public boolean deleteUser(String userId) {
        if (userDao.existsById(userId)) {
            userDao.deleteById(userId);
            return true;
        } else {
            return false;
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
