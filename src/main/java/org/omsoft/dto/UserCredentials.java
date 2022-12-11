package org.omsoft.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class UserCredentials {
    private String userName;
    private String password;
    private String confirmPassword;
    private String userRole;
    private List<String> permission;

}
