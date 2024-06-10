package com.pm.parking_management.parking.repository;

import com.pm.parking_management.parking.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRespository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u.id FROM User u WHERE u.email = :email", nativeQuery = true)
    Long findUserIdByEmail(@Param("email") String email);

}
