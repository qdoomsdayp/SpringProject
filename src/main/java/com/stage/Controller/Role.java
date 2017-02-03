package com.stage.Controller;

import java.io.Serializable;

/**
 * Created by wital on 31.01.2017.
 */
public class Role implements Serializable {
    private String idR;
    private String nameR;

    public Role() {
    }

    public Role(String idR, String nameR) {
        this.idR = idR;
        this.nameR = nameR;
    }

    public void setNameR(String nameR) {
        this.nameR = nameR;
    }

    public void setIdR(String idR) {
        this.idR = idR;
    }

    public String getNameR() {
        return nameR;
    }

    public String getIdR() {
        return idR;
    }
}
