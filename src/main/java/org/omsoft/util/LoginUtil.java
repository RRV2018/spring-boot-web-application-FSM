package org.omsoft.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LoginUtil {
    public static String encryptPassword(char[] password){
    return null;
    }

    public static void logout(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.removeAttribute("message");
        session.removeAttribute("user");
        currentUser.logout();
    }

    public static boolean isValidUser(){
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.isAuthenticated();
    }
    public static String getSecurePassword(String password,String salt){
        String generatedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt.getBytes());
            byte[] bytes = messageDigest.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100 ,16).substring(1));
            }
            generatedPassword = sb.toString();
        }catch (NoSuchAlgorithmException e ){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt.toString();
    }
    public static String getBase64Encode(String key){
        return Base64.encode(key.getBytes()).toString();
    }
    public static String[] getBase64Decode(String authHeader){
        return new String(Base64.decode(authHeader.getBytes())).split(":");
    }

}
