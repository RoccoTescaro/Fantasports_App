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

    public UserController add(User user)
    {
        dao.addUser(user);
        return this;
    }

    public User get(String username, char[] password)
    {
        return null;
        //return dao.getUser(username, password);
    }
}