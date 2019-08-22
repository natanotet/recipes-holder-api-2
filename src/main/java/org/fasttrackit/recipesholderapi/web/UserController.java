package org.fasttrackit.recipesholderapi.web;

import org.fasttrackit.recipesholderapi.domanin.User;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.service.UserService;
import org.fasttrackit.recipesholderapi.transfer.user.CreateUserRequest;
import org.fasttrackit.recipesholderapi.transfer.user.GetUserRequest;
import org.fasttrackit.recipesholderapi.transfer.user.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(GetUserRequest request, Pageable pageable) {
        Page<User> response = userService.getUsers(request,pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserRequest request){
        User response = userService.createUser(request);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deteleUser(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) throws ResourceNotFoundException {
        User response = userService.getUser(id);

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @PostMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, UpdateUserRequest request) throws ResourceNotFoundException {
        User response = userService.updateUser(id, request);

       return new ResponseEntity(response,HttpStatus.NO_CONTENT);

    }
}
