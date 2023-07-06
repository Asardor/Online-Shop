package com.company.onlineshop.service;

import com.company.onlineshop.dto.AuthoritiesDto;
import com.company.onlineshop.dto.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public record AuthoritiesService() {
    public ResponseDto<AuthoritiesDto> create(AuthoritiesDto dto) {
        //userId=null
        return null;
    }

    public ResponseDto<AuthoritiesDto> get(Integer id) {
        return null;
    }

    public ResponseDto<AuthoritiesDto> update(Integer id, AuthoritiesDto dto) {
        return null;
    }

    public ResponseDto<AuthoritiesDto> delete(Integer id) {
        return null;
    }
    public ResponseDto<AuthoritiesDto> addAuth(Integer authId,Integer userId){
        return null;
    }


}

