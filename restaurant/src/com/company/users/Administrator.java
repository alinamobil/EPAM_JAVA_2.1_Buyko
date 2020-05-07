package com.company.users;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

    public Administrator(String name, String password, int id) {
        super(name, password, id);
    }
}
