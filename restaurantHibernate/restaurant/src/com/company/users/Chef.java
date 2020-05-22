package com.company.users;

import java.io.Serializable;

public class Chef extends User implements Serializable {

    public Chef(String name, String password, int id) {
        super(name, password, id);
    }
}
