package edu.phoenix.dao;

import edu.phoenix.NullArgumentException;
import edu.phoenix.model.User;

import java.util.*;

public class Database {
    public static List<User> dataBase = new ArrayList<>();

    public static void addUser(String name, String login, String password)
    {
        // Будем отлавливать исключение, если список == null, или логин или пароль == null
        try
        {
            if(login == null || password == null)
            {
                throw new NullArgumentException("login or password was null");
            }
            dataBase.add(new User(name, login, password));
        }
        catch(NullArgumentException | NullPointerException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public static User getUser(String login, String password)
    {
        try
        {
            if(login == null || password == null)
            {
                throw new NullArgumentException("login or password was null");
            }
            for (User u : dataBase) {
                if (Objects.equals(u.getLogin(), login)) {
                    return u;
                }
            }
        }
        catch(NullArgumentException | NullPointerException e)
        {
            System.err.println(e.getMessage());
        }


        // Если пользователь не найден, будем возвращать пользователя с пустыми полями:
        return new User(null, null, null);
    }

    public static void deleteUser(String login)
    {
        // удаляем пользователя с логином == login
        try
        {
            if(login == null)
            {
                throw new NullArgumentException("login was null");
            }
            for (User u : dataBase)
            {
                if (u.getLogin() == login) {
                    dataBase.remove(u);
                    return;
                }
            }
        }
        catch (NullArgumentException | NullPointerException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public static void updateUser(User user)
    {
        User u = getUser(user.getLogin(), user.getPassword());
        int index = dataBase.indexOf(u);
        dataBase.set(index, user);
    }

}
// Добавить исключение, если при поиске одно из полей == null
// Исключение, когда пользователь не найден