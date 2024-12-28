//package com.banquemisr.challenge05.taskMangager;
//
//import com.banquemisr.challenge05.taskMangager.Exeption.UserException;
//import com.banquemisr.challenge05.taskMangager.entity.User;
//import com.banquemisr.challenge05.taskMangager.repository.UserRepo;
//import com.banquemisr.challenge05.taskMangager.services.UserService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    private UserRepo userRepo;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Test
//    void testCreateUser_Success() {
//        // Arrange
//        User user = new User(1L, "John Doe", "johndoe", "password", "USER", "john@example.com");
//
//        Mockito.when(userRepo.save(user)).thenReturn(user);
//
//        // Act
//        User result = userService.createUser(user);
//
//        // Assert
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals("John Doe", result.getName());
//    }
//
//    @Test
//    void testUpdateUser_Success() {
//        // Arrange
//        Long userId = 1L;
//        User existingUser = new User(userId, "John Doe", "johndoe", "password", "USER", "john@example.com");
//        User updatedUser = new User(userId, "John Updated", "johnupdated", "newpassword", "ADMIN", "johnupdated@example.com");
//
//        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(existingUser));
//        Mockito.when(userRepo.save(existingUser)).thenReturn(updatedUser);
//
//        // Act
//        User result = userService.updateUser(userId, updatedUser);
//
//        // Assert
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals("John Updated", result.getName());
//        Assertions.assertEquals("ADMIN", result.getRole());
//    }
//
//    @Test
//    void testUpdateUser_NotFound() {
//        // Arrange
//        Long userId = 1L;
//        User updatedUser = new User(userId, "John Updated", "johnupdated", "newpassword", "ADMIN", "johnupdated@example.com");
//
//        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        UserException exception = Assertions.assertThrows(UserException.class, () -> userService.updateUser(userId, updatedUser));
//        Assertions.assertEquals("User not found with ID: " + userId, exception.getMessage());
//    }
//
//    @Test
//    void testDeleteUser_Success() {
//        // Arrange
//        Long userId = 1L;
//        Mockito.when(userRepo.existsById(userId)).thenReturn(true);
//
//        // Act
//        userService.deleteUser(userId);
//
//        // Assert
//        Mockito.verify(userRepo, Mockito.times(1)).deleteById(userId);
//    }
//
//    @Test
//    void testDeleteUser_NotFound() {
//        // Arrange
//        Long userId = 1L;
//        Mockito.when(userRepo.existsById(userId)).thenReturn(false);
//
//        // Act & Assert
//        UserException exception = Assertions.assertThrows(UserException.class, () -> userService.deleteUser(userId));
//        Assertions.assertEquals("User not found with ID: " + userId, exception.getMessage());
//    }
//}