package com.ivan.blog.service;

import com.ivan.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    void deleteType(Long id);

    Type getTypeByName(String name);

}
