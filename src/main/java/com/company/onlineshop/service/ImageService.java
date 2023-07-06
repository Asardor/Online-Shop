package com.company.onlineshop.service;

import com.company.onlineshop.dto.ImageDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.model.Image;
import com.company.onlineshop.repository.ImageRepository;
import com.company.onlineshop.service.mapper.ImageMapper;
import com.company.onlineshop.service.valid.ImageValidation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public record ImageService(
        ImageMapper imageMapper,
        ImageValidation imageValidation,
        ImageRepository imageRepository) {
    public ResponseDto<ImageDto> upload(MultipartFile file) {
        try {
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .massage("Image successful create!")
                    .data(this.imageMapper.toDto(
                                    this.imageRepository.save(
                                            Image.builder()
                                                    .imageName(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0])
                                                    .type(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1])
                                                    .status(true)
                                                    .createdAt(LocalDateTime.now())
                                                    .path(saveFile(file))
                                                    .build())
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .massage("Image saving error massage :: " + e.getMessage())
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<ImageDto> download(Integer id) {
        return this.imageRepository.findByImageIdAndDeletedAtIsNull(id).map(image -> {
            try {
                ImageDto dto = this.imageMapper.toDto(image);
                dto.setData(Files.readAllBytes(Path.of(image.getPath())));
                return ResponseDto.<ImageDto>builder()
                        .massage("Ok")
                        .success(true)
                        .data(dto)
                        .build();
            } catch (IOException e) {
                return ResponseDto.<ImageDto>builder()
                        .massage("Error")
                        .success(false)
                        .build();
            }
        }).orElse(
                ResponseDto.<ImageDto>builder()
                        .massage("Not found")
                        .success(false)
                        .build()
        );
    }

    public ResponseDto<ImageDto> update(Integer id, MultipartFile file) {
        try {
            return this.imageRepository.findByImageIdAndDeletedAtIsNull(id)
                    .map(image -> {
                        File uFile = new File(image.getPath());
                        if (uFile.exists()) {
                            uFile.delete();
                        }
                        try {
                            return ResponseDto.<ImageDto>builder()
                                    .success(true)
                                    .massage("OK")
                                    .data(this.imageMapper.toDto(
                                            this.imageRepository.save(
                                                    Image.builder()
                                                            .imageId(id)
                                                            .imageName(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0])
                                                            .type(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1])
                                                            .status(true)
                                                            .createdAt(LocalDateTime.now())
                                                            .path(saveFile(file))
                                                            .build())))
                                    .build();
                        } catch (IOException e) {
                            return ResponseDto.<ImageDto>builder()
                                    .massage("Image updating error massage :: " + e.getMessage())
                                    .success(false)
                                    .build();
                        }
                    })
                    .orElse(ResponseDto.<ImageDto>builder()
                            .massage("Not found!")
                            .success(false)
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .massage("Image updating error massage :: " + e.getMessage())
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<ImageDto> delete(Integer id) {
        try {
            return this.imageRepository.findByImageIdAndDeletedAtIsNull(id)
                    .map(image -> {
                        File file = new File(image.getPath());
                        if (file.exists()) {
                            file.delete();
                        }
                        image.setDeletedAt(LocalDateTime.now());
                        this.imageRepository.save(image);
                        return ResponseDto.<ImageDto>builder()
                                .massage("Image successful deleted!")
                                .data(this.imageMapper.toDto(image))
                                .success(true)
                                .build();
                    })
                    .orElse(ResponseDto.<ImageDto>builder()
                            .massage("Not found!")
                            .success(false)
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .massage("Image deleting error massage :: " + e.getMessage())
                    .success(false)
                    .build();
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        String folders = String.format("%s/%s", "upload", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        File image = new File(folders);
        if (!image.exists()) {
            image.mkdirs();
        }
        String imageName = String.format("%s/%s", folders, UUID.randomUUID());
        Files.copy(file.getInputStream(), Path.of(imageName));
        return imageName;
    }
}
