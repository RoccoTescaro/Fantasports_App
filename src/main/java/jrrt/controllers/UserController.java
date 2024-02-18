package jrrt.controllers;

import jrrt.daosystem.DataAccessObject;
import jrrt.entities.User;

public class UserController
{
    private DataAccessObject dao;

    public UserController()
    {
        dao = new DataAccessObject(); 
    }

    UserController add(User user)
    {
        dao.addUser(user);
        return this;
    }

    User get(String username, String password)
    {
        return dao.getUser(username, password);
    }
}