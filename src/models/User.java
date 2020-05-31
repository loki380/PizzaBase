package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String Login;
    private String password;
    public User(String login, String password){
        this.Login=login;
        this.password=password;
    }
    public User(){
    }
    public String getLogin(){
        return this.Login;
    }
    public String getPassword(){
        return this.password;
    }
    public void setLogin(String login){
        this.Login=login;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public static String generateHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.reset();
        byte[] hash = digest.digest(data.getBytes());
        return bytesToStringHex(hash);
    }
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String bytesToStringHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for(int j=0; j< bytes.length; j++){
            int v= bytes[j] & 0xFF;
            hexChars[j*2]=hexArray[v >>> 4];
            hexChars[j*2+1]= hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
