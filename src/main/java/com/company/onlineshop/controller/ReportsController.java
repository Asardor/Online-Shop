package com.company.onlineshop.controller;

import com.company.onlineshop.dto.ProductDto;
import com.company.onlineshop.dto.ReportsDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportsService reportsService;
    @PostMapping("/create")
    public ResponseDto<ReportsDto> create(@RequestBody ReportsDto dto) {
        return this.reportsService.create(dto);
    }


    @GetMapping("/get/{id}")
    public ResponseDto<ReportsDto> get(@PathVariable("id") Integer reportsId) {
        return this.reportsService.get(reportsId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<ReportsDto> update(@PathVariable("id") Integer reportsId,
                                          @RequestBody ReportsDto dto) {
        return this.reportsService.update(reportsId, dto);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseDto<ReportsDto> delete(@PathVariable("id") Integer reportsId) {
        return this.reportsService.delete(reportsId);
    }


}
