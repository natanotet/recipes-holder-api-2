package org.fasttrackit.recipesholderapi;

import org.fasttrackit.recipesholderapi.domanin.User;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.service.UserService;

import org.fasttrackit.recipesholderapi.transfer.user.CreateUserRequest;
import org.fasttrackit.recipesholderapi.transfer.user.UpdateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;


import javax.validation.ConstraintViolationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;



@RunWith(SpringRunner.class)
@SpringBootTest

public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;



    @Test
    public void testCreateUser_whenValidRequest_thenReturnCreatedUser(){
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("Vasile Bucatarul");

        User createUser = userService.createUser(request);

        assertThat(createUser, notNullValue());
        assertThat(createUser.getUserName(), is(request.getUserName()));
    }

//    @Test(expected = ConstraintViolationException.class)
//    public void testCreateUser_whenMissingMandatoryProperties_thenTrowException(){
//       CreateUserRequest request = new CreateUserRequest();
//
//        userService.createUser(request);
//
//    }

    @Test
    public void testGetUserbyId_whenValidRequest_thenReturnAskedUser() throws ResourceNotFoundException {
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("Costin Brazilianul");

        User createdUser = userService.createUser(request);

        assertThat(createdUser, notNullValue());
        assertThat(createdUser.getUserName(), is(request.getUserName()));


        User user = userService.getUser(createdUser.getId());
        assertThat(user, notNullValue());
        assertThat(user.getId(), is(createdUser.getId()));
        assertThat(user.getUserName(), is(createdUser.getUserName()));
    }

    @Test
    public void testUpdateUserName_whenvalidrequest_thenReturnUpdatedUserName() throws ResourceNotFoundException {

    }
}
