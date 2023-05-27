package org.omsoft;

import org.omsoft.util.LoginUtil;

import java.security.MessageDigest;

public class Test {
    public static void main232(String[] args) throws Exception{
     String salt = LoginUtil.getSalt();
      System.out.println(salt);
      String s = LoginUtil.getSecurePassword("raj","[B@25f38edc");
      System.out.println(s);


    }
}
