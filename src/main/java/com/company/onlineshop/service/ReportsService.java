package com.company.onlineshop.service;

import com.company.onlineshop.dto.ReportsDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.model.Reports;
import com.company.onlineshop.repository.ReportsRepository;
import com.company.onlineshop.service.mapper.ReportsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportsService {
      private final ReportsMapper reportsMapper;
      private final ReportsRepository reportsRepository;

    public ResponseDto<ReportsDto> create(ReportsDto dto) {
        try {
            Reports reports=reportsMapper.toEntity(dto);
            reports.setCreatedAt(LocalDateTime.now());
            reports.setStatus(true);
            this.reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .success(true)
                    .massage("Reports successful create!")
                    .data(reportsMapper.toDto(reports))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ReportsDto>builder()
                    .massage(String.format("Reports while saving error"+e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ReportsDto> get(Integer reportsId) {
        return this.reportsRepository.findByReportsIdAndDeletedAtIsNull(reportsId)
                .map(reports -> ResponseDto.<ReportsDto>builder()
                        .success(true)
                        .massage("Reports successful get!")
                        .data(reportsMapper.toDto(reports))
                        .build())
                .orElse(ResponseDto.<ReportsDto>builder()
                        .massage(String.format("Reports with %s id is not found", reportsId))
                        .build());
    }

    public ResponseDto<ReportsDto> update(Integer reportsId, ReportsDto dto) {
        try {
            return this.reportsRepository.findByReportsIdAndDeletedAtIsNull(reportsId)
                    .map(reports -> {
                        reports.setUpdatedAt(LocalDateTime.now());
                        reportsMapper.update(reports, dto);
                        reportsRepository.save(reports);
                        return ResponseDto.<ReportsDto>builder()
                                .success(true)
                                .massage("Reports successful update!")
                                .data(reportsMapper.toDto(reports))
                                .build();
                    })
                    .orElse(ResponseDto.<ReportsDto>builder()
                    .massage(String.format("Reports with %s id is not found", reportsId))
                    .build());
        }catch (Exception e){
            return ResponseDto.<ReportsDto>builder()
                    .massage(String.format("Reports while saving error"+e.getMessage()))
                    .build();
        }
    }
    public ResponseDto<ReportsDto> delete(Integer reportsId) {
        try {
            return this.reportsRepository.findByReportsIdAndDeletedAtIsNull(reportsId)
                    .map(reports -> {
                        reports.setUpdatedAt(LocalDateTime.now());
                        reports.setStatus(false);
                        reportsRepository.save(reports);
                        return ResponseDto.<ReportsDto>builder()
                                .success(true)
                                .massage("Reports successful delete!")
                                .data(reportsMapper.toDto(reports))
                                .build();
                    })
                    .orElse(ResponseDto.<ReportsDto>builder()
                            .massage(String.format("Reports with %s id is not found", reportsId))
                            .build());
        }catch (Exception e){
            return ResponseDto.<ReportsDto>builder()
                    .massage(String.format("Reports while saving error"+e.getMessage()))
                    .build();
        }
    }
}
