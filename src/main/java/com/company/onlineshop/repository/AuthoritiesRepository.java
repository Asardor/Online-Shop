package com.company.onlineshop.repository;

import com.company.onlineshop.model.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities,Integer> {
}
