package com.example.swagger.api.dao;

import com.example.swagger.api.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDAO {
    void insertUser(UserDTO userDTO);
}
