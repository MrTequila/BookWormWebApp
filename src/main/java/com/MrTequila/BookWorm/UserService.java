package com.MrTequila.BookWorm;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserService {


    @Autowired
    SessionFactory sessionFactory;

    public User validateUser(Login login) {

        List<User> users = new ArrayList<>();
        users = sessionFactory.getCurrentSession()
                .createQuery("from user where username=? and password=?")
                .setParameter(0, login.getUsername())
                .setParameter(1, login.getPassword())
                .list();

        if (users.size()>0) {
            return users.get(0);
        }
        else return null;
    }
}
