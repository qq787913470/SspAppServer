package com.yada.ssp.appServer.view;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Valid
public class UpdatePwd {

    @NotEmpty
    private String oldPwd;

    @NotEmpty
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
