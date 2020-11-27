package com.ivan.blog.dao;

import com.ivan.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
