package com.pm.parking_management.parking.controller;

import com.pm.parking_management.parking.model.entity.UserEntity;
import com.pm.parking_management.parking.model.request.UserRequest;
import com.pm.parking_management.parking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pm.parking_management.common.Constants.BASE_PATH;

@RestController
@Slf4j
@RequestMapping(value = BASE_PATH)


public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/list")
    public ResponseEntity<?> getUsers(){
        try {
            log.info("Searching users");
            List<UserEntity> usersList = userService.getAllUsers();
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = {})
    @PostMapping("/userRegistration")
    public ResponseEntity<?> createUser(@RequestBody UserRequest request){

        if (userService.isRequestValid(request)){
            ResponseEntity<UserEntity> responseEntity = userService.createUser(request);
            if (responseEntity.getStatusCode().is2xxSuccessful()){
                log.info("New user registered successfully");
                return new ResponseEntity<>(responseEntity, HttpStatus.OK);
            } else {
                log.error("An error occurred while creating user");
                return new ResponseEntity<>("An error occurred while creating user", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            log.error("Invalid request");
            return new ResponseEntity<>("Invalid request for creating user", HttpStatus.BAD_REQUEST);
        }
    }


}
