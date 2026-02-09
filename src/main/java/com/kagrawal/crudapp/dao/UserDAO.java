package com.kagrawal.crudapp.dao;

import com.kagrawal.crudapp.model.User;

public interface UserDAO {
    void register(User user);
    User insert(String Username,String password);
    Boolean exists(String Username);
}
