package com.spring3.oauth.basic.services;

import com.spring3.oauth.basic.dtos.UserRequest;
import com.spring3.oauth.basic.dtos.UserResponse;

import java.util.List;


public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUser();

    List<UserResponse> getAllUser();


}
