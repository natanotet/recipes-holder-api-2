package org.fasttrackit.recipesholderapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.recipesholderapi.domanin.User;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.persistence.UserRepository;
import org.fasttrackit.recipesholderapi.transfer.user.CreateUserRequest;
import org.fasttrackit.recipesholderapi.transfer.user.GetUserRequest;
import org.fasttrackit.recipesholderapi.transfer.user.UpdateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public User createUser (CreateUserRequest request){
        LOGGER.info("Creating user {}", request);

        User user = objectMapper.convertValue(request, User.class);

//        User user = new User();
//        user.setUserName(request.getUserName());

        return userRepository.save(user);
    }

    public User getUser (long id) throws ResourceNotFoundException{
        LOGGER.info("Retrieving user {}", id);
         return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "User" + id + " does not exist"
                ));
    }

    public User updateUser (long id, UpdateUserRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updateing user {} with {}",id,request );

        User user = getUser(id);
        BeanUtils.copyProperties(request, user);

       return userRepository.save(user);
    }

    public void deteleUser(long id) {
        LOGGER.info("deleting recipe {} ", id);

        userRepository.deleteById(id);

        LOGGER.info("deleted recipe {} ", id);
    }

    public Page<User> getUsers(GetUserRequest request, Pageable pageable){
        LOGGER.info("Retriving Users {} ", request);

        if (request.getPartialUserName() != null){
            return userRepository.findByUserNameContaining(request.getPartialUserName(), pageable);
        }

        return userRepository.findAll(pageable);
    }

}


