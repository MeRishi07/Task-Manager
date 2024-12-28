package com.banquemisr.challenge05.taskMangager.services;

import com.banquemisr.challenge05.taskMangager.Exeption.UserException;
import com.banquemisr.challenge05.taskMangager.entity.User;
import com.banquemisr.challenge05.taskMangager.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;



    public Optional<User> getUserByID(Long id) {
        return userRepo.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        try {
            Optional<User> existingUserOpt = userRepo.findById(id);
            if (existingUserOpt.isPresent()) {
                User existingUser = existingUserOpt.get();
                existingUser.setName(updatedUser.getName());
                existingUser.setUsername(updatedUser.getUsername());
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                existingUser.setRole(updatedUser.getRole());
                existingUser.setEmail(updatedUser.getEmail());
                return userRepo.save(existingUser);
            } else {
                throw new UserException("User not found with ID: " + id);
            }
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return null;
        }
    }

    public String deleteUser(Long id) {
        try {
            if (userRepo.existsById(id)) {
                userRepo.deleteById(id);
            } else {
                throw new UserException("User not found with ID: " + id);
            }
        }catch (Exception e){
            log.error(e.getStackTrace());
        }
        return "User deleted successfully with ID: " + id;
    }

}
