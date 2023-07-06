package com.company.onlineshop.controller;

import com.company.onlineshop.dto.AuthoritiesDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.AuthoritiesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class AuthoritiesController {
    private final AuthoritiesService authoritiesService;

    @PostMapping("/create")
    public ResponseDto<AuthoritiesDto> create(@Valid @RequestBody AuthoritiesDto dto) {
        return this.authoritiesService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<AuthoritiesDto> get(@PathVariable(value = "id") Integer id) {
        return this.authoritiesService.get(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<AuthoritiesDto> update(@PathVariable(value = "id") Integer id, @RequestBody AuthoritiesDto dto) {
        return this.authoritiesService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<AuthoritiesDto> delete(@PathVariable(value = "id") Integer id) {
        return this.authoritiesService.delete(id);
    }
//ADMIN
    @PatchMapping("/access")
    public ResponseDto<AuthoritiesDto> addAuth(@RequestParam(value = "authId") Integer authId, @RequestParam(value = "userId") Integer userId) {
        return this.authoritiesService.addAuth(authId, userId);
    }
}
