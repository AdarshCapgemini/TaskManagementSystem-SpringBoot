package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.UserUserRoleDto;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.InvalidUserException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.exception.UserListIsEmptyException;
import com.example.demo.exception.UserRoleListIsEmptyException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserServiceImpl;

class UserServiceImplTest {

	@InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserRoleRepository userRoleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateANewUser() throws UserAlreadyExistsException {
        User user = new User();
        user.setUserId(1);
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createANewUser(user);
        assertEquals(user, createdUser);
    }

    @Test
    void testGetListOfAllUsers() throws UserListIsEmptyException {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getListOfAllUsers();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetDetailsOfSpecificUserById() throws UserDoesNotExistException {
        User user = new User();
        user.setUserId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.getDetailsOfSpecificUserById(1)).thenReturn(user);

        User result = userService.getDetailsOfSpecificUserById(1);
        assertEquals(user, result);
    }

    @Test
    void testAuthenticateUser() throws UserDoesNotExistException, InvalidUserException {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("password");
        when(userRepository.findByUserName("testUser")).thenReturn(user);

        boolean isAuthenticated = userService.authenticateUser("testUser", "password");
        assertTrue(isAuthenticated);
    }
    
    @Test
    void testGetUsersWithSpecificEmailDomain() throws UserDoesNotExistException {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.getUsersWithSpecificEmailDomain("example.com")).thenReturn(users);

        List<User> result = userService.getUsersWithSpecificEmailDomain("example.com");
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetUsersByFullName() throws UserDoesNotExistException {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.getUsersByFullName("John Doe")).thenReturn(users);

        List<User> result = userService.getUsersByFullName("John Doe");
        assertFalse(result.isEmpty());
    }

    
    @Test
    void testGetUsersWithMostTasks() throws UserListIsEmptyException {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users); // Mocking findAll to return a non-empty list
        when(userRepository.getUsersWithMostTasks()).thenReturn(users);

        List<User> result = userService.getUsersWithMostTasks();
        assertFalse(result.isEmpty());
    }


    @Test
    void testUpdateUserDetailsByUserId() throws UserDoesNotExistException {
        User user = new User();
        user.setUserId(1);
        User updatedUser = new User();
        updatedUser.setUserId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User result = userService.updateUserDetailsByUserId(1, updatedUser);
        assertEquals(updatedUser, result);
    }

    @Test
    void testDeleteUserByUserId() throws UserDoesNotExistException {
        User user = new User();
        user.setUserId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        userService.deleteUserByUserId(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    
    @Test
    void testGetUserWithCompletedTasks() throws UserListIsEmptyException {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users); // Mocking findAll to return a non-empty list
        when(userRepository.getUserWithCompletedTasks()).thenReturn(users);

        List<User> result = userService.getUserWithCompletedTasks();
        assertFalse(result.isEmpty());
    }


    @Test
    void testGetUserIdByUserName() throws UserDoesNotExistException {
        when(userRepository.findByUserName("testUser")).thenReturn(new User());
        when(userRepository.getUserIdByUserName("testUser")).thenReturn(1);

        int userId = userService.getUserIdByUserName("testUser");
        assertEquals(1, userId);
    }

    @Test
    void testGetAllUserRoleIdsByUserName() throws UserDoesNotExistException {
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add(1);
        when(userRepository.findByUserName("testUser")).thenReturn(new User());
        when(userRepository.getAllUserRoleIdsByUserName("testUser")).thenReturn(roleIds);

        List<Integer> result = userService.getAllUserRoleIdsByUserName("testUser");
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetAllUserRolesWithUserId() throws UserDoesNotExistException {
        List<UserRole> roles = new ArrayList<>();
        roles.add(new UserRole());
        when(userRepository.findById(1)).thenReturn(Optional.of(new User()));
        when(userRepository.getAllUserRolesWithUserId(1)).thenReturn(roles);

        List<UserRole> result = userService.getAllUserRolesWithUserId(1);
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetAllUserWithUserRole() throws UserListIsEmptyException, UserRoleListIsEmptyException {
        List<User> users = new ArrayList<>();
        users.add(new User());
        List<UserRole> roles = new ArrayList<>();
        roles.add(new UserRole());
        when(userRepository.findAll()).thenReturn(users);
        when(userRoleRepository.findAll()).thenReturn(roles);
        when(userRepository.getAllUserWithUserRole()).thenReturn(new ArrayList<>());

        List<UserUserRoleDto> result = userService.getAllUserWithUserRole();
        assertNotNull(result);
    }

    @Test
    void testGetAllUserIds()  {
        int[] userIds = {1, 2, 3};
        when(userRepository.getAllUserIds()).thenReturn(userIds);

        int[] result = userService.getAllUserIds();
        assertArrayEquals(userIds, result);
    }

}
