package com.company.onlineshop.service;

import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public record UserService() {
    public ResponseDto<UserDto> create(UserDto dto) {
        return null;
    }

    public ResponseDto<UserDto> get(Integer id) {
        return null;
    }

    public ResponseDto<UserDto> update(Integer id, UserDto dto) {
        return null;
    }

    public ResponseDto<UserDto> delete(Integer id) {
        return null;
    }
}
