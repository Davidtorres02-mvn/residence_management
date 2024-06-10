package com.pm.parking_management.parking.service;

import com.pm.parking_management.parking.model.entity.UserEntity;
import com.pm.parking_management.parking.model.request.LoginRequest;
import com.pm.parking_management.parking.model.request.UserRequest;
import com.pm.parking_management.parking.repository.UserRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserRespository userRespository;


    public ResponseEntity<?> authenticate (LoginRequest request){
        log.info("Searching user with email {}",request.getEmail());
        long userId = userRespository.findUserIdByEmail(request.getEmail());
        Optional<UserEntity> user = userRespository.findById(userId);

        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }
        if (!request.getPassword().equals(user.get().getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contrasena incorrecta");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Inicio de sesion exitoso");



    }

}
