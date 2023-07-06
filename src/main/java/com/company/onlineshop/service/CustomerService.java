package com.company.onlineshop.service;

import com.company.onlineshop.dto.BasketDto;
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
                    .code(0)
                    .message("Ok")
                    .date(this.customerMapper.toDtoNotBasketAndOrders(customer))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CustomerDto>builder()
                    .code(-3)
                    .message(e.getMessage())
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
                        .code(-1)
                        .build();
            }
            return ResponseDto.<CustomerDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .date(this.customerMapper.toDtoNotBasketAndOrders(optional.get()))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CustomerDto>builder()
                    .code(-3)
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }*/

        return this.customerRepository.findByCustomerIdAndDeletedAtIsNull(customerId)
                .map(customer -> ResponseDto.<CustomerDto>builder()
                        .success(true)
                        .message("Ok")
                        .code(0)
                        .date(this.customerMapper.toDto(customer))
                        .build())
                        .orElse(ResponseDto.<CustomerDto>builder()
                                .message("Not found")
                                .code(-1)
                                .build());

    }

    public ResponseDto<CustomerDto> update(CustomerDto dto, Integer customerId) {
        return null;
    }

    public ResponseDto<CustomerDto> delete(Integer customerId) {
        return null;
    }
}
