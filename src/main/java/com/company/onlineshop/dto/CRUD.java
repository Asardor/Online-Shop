package com.company.onlineshop.dto;

public interface CRUD<K,V> {
    ResponseDto<V> create(V dto);
    ResponseDto<V> get(K id);
    ResponseDto<V> update(K id,V dto);
    ResponseDto<V> delete(K id);
}
