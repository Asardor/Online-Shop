package com.company.onlineshop.service;

import com.company.onlineshop.dto.CustomerDto;
import com.company.onlineshop.dto.ResponseDto;
import com.company.onlineshop.model.Customer;
import com.company.onlineshop.repository.CustomerRepository;
import com.company.onlineshop.service.mapper.CustomerMapper;
import com.company.onlineshop.service.validate.CustomerValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerValidate customerValidate;
    private final CustomerRepository customerRepository;

    public ResponseDto<CustomerDto> create(CustomerDto dto) {
        try {
            Customer customer = this.customerMapper.toEntity(dto);
            customer.setCreatedAt(LocalDateTime.now());
            this.customerRepository.save(customer);
            return ResponseDto.<CustomerDto>builder()
                    .success(true)
                    .massage("Ok")
                    .data(this.customerMapper.toDtoNotBasketAndOrders(customer))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CustomerDto>builder()
                    .massage(e.getMessage())
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<CustomerDto> get(Integer customerId) {
        /*try {
            Optional<Customer> optional = this.customerRepository.findByCustomerIdAndDeletedAtIsNull(customerId);
            if (optional.isEmpty()) {
                return ResponseDto.<CustomerDto>builder()
                        .message("Customer is not found!")
                        .build();
            }
            return ResponseDto.<CustomerDto>builder()
                    .success(true)
                    .message("Ok")
                    .date(this.customerMapper.toDtoNotBasketAndOrders(optional.get()))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CustomerDto>builder()
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }*/

        return this.customerRepository.findByCustomerIdAndDeletedAtIsNull(customerId)
                .map(customer -> ResponseDto.<CustomerDto>builder()
                        .success(true)
                        .massage("Ok")
                        .data(this.customerMapper.toDto(customer))
                        .build())
                        .orElse(ResponseDto.<CustomerDto>builder()
                                .massage("Not found")
                                .build());

    }

    public ResponseDto<CustomerDto> update(CustomerDto dto, Integer customerId) {
        return null;
    }

    public ResponseDto<CustomerDto> delete(Integer customerId) {
        return null;
    }
}
