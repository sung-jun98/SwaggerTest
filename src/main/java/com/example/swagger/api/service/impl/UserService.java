package com.example.swagger.api.service.impl;

import com.example.swagger.api.dto.request.CreateUserRequestDTO;
import com.example.swagger.api.dto.response.CreateUserResponseDTO;

public interface UserService {

    CreateUserResponseDTO signUp(CreateUserRequestDTO request);

}
