package com.example.autoPujcovna.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.autoPujcovna.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findStudentbyEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id=?1")
    Optional<User> findStudentbyId(long id);

    
   
}
