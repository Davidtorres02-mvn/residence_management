package com.pm.parking_management.parking.service;

import com.pm.parking_management.parking.model.entity.UserEntity;
import com.pm.parking_management.parking.model.request.UserRequest;
import com.pm.parking_management.parking.repository.UserRespository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service

public class UserService {

    private final UserRespository userRespository;

    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public Boolean isRequestValid(UserRequest request){
        return request.getName() != null;

    }

    @Transactional
    public ResponseEntity<UserEntity> createUser(UserRequest request){

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(request.getName());
            userEntity.setPassword(request.getPassword());
            userEntity.setEmail(request.getEmail());
            userEntity.setIdRole(request.getIdRole());
            userEntity.setCreatedDtm(LocalDateTime.now());

            userRespository.save(userEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);

        } catch (Exception e){
            log.error("An error occurred while creating user " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<UserEntity> getAllUsers(){
        List<UserEntity> userList = userRespository.findAll();
        if (userList.isEmpty()){
            log.info("There is any user registered");
        }
        return userList;
    }


}
