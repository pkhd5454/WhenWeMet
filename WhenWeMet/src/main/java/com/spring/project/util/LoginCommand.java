package com.spring.project.util;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class LoginCommand {
	
    @NotEmpty(message="??΄?λ₯? ?? ₯?΄μ£ΌμΈ?.")
    private String id;
 
    @NotEmpty(message="λΉλ?λ²νΈλ₯? ?? ₯?΄μ£ΌμΈ?.")
    private String password;
    
    private boolean rememberId;
    
    public boolean isRememberId() {
		return rememberId;
	}
	public void setRememberId(boolean rememberId) {
		this.rememberId = rememberId;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
