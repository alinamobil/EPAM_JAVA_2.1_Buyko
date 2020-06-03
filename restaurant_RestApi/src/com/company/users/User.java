package com.company.users;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String password;
    private int id;

    public User(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickName='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof User)) {
            return false;
        }

        User user = (User) o;
        return (this.name == user.name &&
                this.password == user.password
                &&
                this.id == user.id);
    }
}
