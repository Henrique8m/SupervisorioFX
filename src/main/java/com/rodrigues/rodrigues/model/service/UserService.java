package com.rodrigues.rodrigues.model.service;

import com.rodrigues.rodrigues.model.dao.UserDao;
import com.rodrigues.rodrigues.model.dto.UserRequest;
import com.rodrigues.rodrigues.model.dto.UserResponse;
import com.rodrigues.rodrigues.model.entities.User;
import com.rodrigues.rodrigues.model.exceptions.DatabaseException;
import com.rodrigues.rodrigues.model.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserService {

    @Autowired
    private UserDao dao;
    @Autowired
    private UserRequest userRequest;
    @Autowired
    private UserResponse userResponse;
    @Autowired
    private User user;


    public void saveUser(UserRequest newUser) throws Exception {
        user = user.requestToUser(newUser);
        Boolean exists = dao.findByEmail(user.getEmail()).isPresent();
        if(exists){
            throw new DatabaseException("Conflict.");
        }
        dao.save(user);

        //passando um exemplo!
/*
        User obj = new User();
        obj.setEmail(dto.getEmail());
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues();
        org.springframework.data.domain.Example<User> example = org.springframework.data.domain.Example.of(obj, matcher);
        boolean one = dao.exists(example);
        obj.setName(dto.getName());
        obj.setSenha(dto.getSenha());
        if (one) {
            throw new Exception("Ja cadastrado");
        } else {
            dao.saveAll(Arrays.asList(obj));
        }
*/
    }

    public UserResponse findbyId(Long id) {
        User user = dao.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        UserResponse response = userResponse.userToUserResponse(user);
        return response;
    }
}
