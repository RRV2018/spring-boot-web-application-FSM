package org.omsoft;

import org.omsoft.util.LoginUtil;

import java.security.MessageDigest;

public class Test {
    public static void main3242(String[] args) throws Exception{
     String salt = LoginUtil.getBase64Encode("Admin:mbs");
      System.out.println(salt);
      String[] user = LoginUtil.getBase64Decode(salt);
      System.out.println(user[0]);
        System.out.println(user[1]);
    }
}
