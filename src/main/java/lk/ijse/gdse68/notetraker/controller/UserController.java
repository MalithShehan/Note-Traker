package lk.ijse.gdse68.notetraker.controller;

import lk.ijse.gdse68.notetraker.dao.UserDao;
import lk.ijse.gdse68.notetraker.dto.UserDTO;
import lk.ijse.gdse68.notetraker.entity.UserEntity;
import lk.ijse.gdse68.notetraker.service.UserService;
import lk.ijse.gdse68.notetraker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("health")
    public String healthCheck() {
        return "User controller is OK!";
    }
    @Autowired
    private final UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart ("lastName") String lastName,
            @RequestPart ("email") String email,
            @RequestPart ("password") String password,
            @RequestPart ("profilePic") String profilePic) {

        // Handle profile pic
        var base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);
        // build the user object
        UserDTO buldUserDTO = new UserDTO();
        buldUserDTO.setFirstName(firstName);
        buldUserDTO.setLastName(lastName);
        buldUserDTO.setEmail(email);
        buldUserDTO.setPassword(password);
        buldUserDTO.setProfilePic(base64ProfilePic);

        //send to the service layer
        return new ResponseEntity<>(userService.saveUser(buldUserDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable ("id") String userId) {
//        return userService.deleteUser(userId) ?
//                new ResponseEntity<>(HttpStatus.NO_CONTENT)     :
//                new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (userService.deleteUser(userId)) {
            return ResponseEntity.ok("User Deleted Successfully!");
        } else {
            return new ResponseEntity<>("Note Deleted Faild!", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getSelectedUser(@PathVariable ("id") String userId) {
        return userService.getSelectedUser(userId);
    }

    @GetMapping(value = "allUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable ("userId") String userId, @RequestBody UserDTO userDTO) {
        if(userService.updateUser(userId, userDTO)) {
            return ResponseEntity.ok("User Update Successfully!");
        } else {
            return new ResponseEntity<>("Note Update faild!", HttpStatus.BAD_REQUEST);
        }
    }
}