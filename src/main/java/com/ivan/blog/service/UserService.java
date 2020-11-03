package com.ivan.blog.service;

import com.ivan.blog.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
