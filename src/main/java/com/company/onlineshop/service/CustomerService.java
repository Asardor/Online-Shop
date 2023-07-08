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
import java.util.Optional;

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
                    .message("Ok")
                    .data(this.customerMapper.toDtoNotBasketAndOrders(customer))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CustomerDto>builder()
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<CustomerDto> get(Integer customerId) {
        return this.customerRepository.findByCustomerIdAndDeletedAtIsNull(customerId)
                .map(customer -> ResponseDto.<CustomerDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.customerMapper.toDto(customer))
                        .build())
                .orElse(ResponseDto.<CustomerDto>builder()
                        .message("Not found")
                        .build());

    }

    public ResponseDto<CustomerDto> update(CustomerDto dto, Integer customerId) {
        Optional<Customer> optional = this.customerRepository.findByCustomerIdAndDeletedAtIsNull(customerId);
        if (optional.isEmpty()) {
            return ResponseDto.<CustomerDto>builder()
                    .message("Customer is not found!")
                    .build();
        }
        Customer customer = optional.get();
        this.customerMapper.update(dto, customer);
        this.customerRepository.save(customer);
        return ResponseDto.<CustomerDto>builder()
                .success(true)
                .message("Customer successful updated and save database")
                .data(this.customerMapper.toDtoNotBasketAndOrders(customer))
                .build();
    }

    public ResponseDto<CustomerDto> delete(Integer customerId) {
        try {
            return customerRepository.findByCustomerIdAndDeletedAtIsNull(customerId)
                    .map(customer -> {
                                customer.setDeletedAt(LocalDateTime.now());
                                customer.setStatus(false);
                                customerRepository.save(customer);
                                return ResponseDto.<CustomerDto>builder()
                                        .success(true)
                                        .message("Successful updated")
                                        .data(this.customerMapper.toDtoNotBasketAndOrders(customer))
                                        .build();
                            }
                    ).orElse(ResponseDto.<CustomerDto>builder()
                            .success(false)
                            .message(String.format("This %s id customer is not found", customerId))
                            .build());

        } catch (Exception e) {
            return ResponseDto.<CustomerDto>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }
}
