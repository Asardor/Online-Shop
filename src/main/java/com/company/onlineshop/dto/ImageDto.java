package com.company.onlineshop.dto;
import lombok.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private Integer imageId;
    private String imageName;
    private String path;
    private String type;
    private byte[] data;
    private Integer userId;
    private Integer accountId;
    private Integer cartId;
    private Integer productId;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}