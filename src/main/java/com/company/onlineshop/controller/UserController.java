package com.company.onlineshop.controller;

import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.dto.UserDto;
import com.company.onlineshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseDto<UserDto> create(@Valid @RequestBody UserDto dto) {
        return this.userService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<UserDto> get(@PathVariable(value = "id") Integer id) {
        return this.userService.get(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<UserDto> update(@PathVariable(value = "id") Integer id,@RequestBody UserDto dto) {
        return this.userService.update(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<UserDto> delete(@PathVariable(value = "id") Integer id) {
        return this.userService.delete(id);
    }
}
