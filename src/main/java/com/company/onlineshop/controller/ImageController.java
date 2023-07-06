package com.company.onlineshop.controller;

import com.company.onlineshop.dto.ImageDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
@PostMapping("/create")
    public ResponseDto<ImageDto> upload(@RequestBody MultipartFile file) {
        return this.imageService.upload(file);
    }
@GetMapping("/get/{id}")
    public ResponseDto<ImageDto> download(@PathVariable(value = "id") Integer id) {
        return this.imageService.download(id);
    }
@PutMapping("/update/{id}")
    public ResponseDto<ImageDto> update(@PathVariable(value = "id") Integer id, @RequestBody MultipartFile file) {
        return this.imageService.update(id,file);
    }
@DeleteMapping("/delete/{id}")
    public ResponseDto<ImageDto> delete(@PathVariable(value = "id") Integer id) {
        return this.imageService.delete(id);
    }


}
