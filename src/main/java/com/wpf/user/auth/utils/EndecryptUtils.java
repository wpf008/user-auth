package com.wpf.user.auth.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:22
 * @ClassName: user-auth
 * @Description:
 */
public class EndecryptUtils {

    /**
     * base64进制加密
     *
     * @param password
     * @return
     */
    public static String encrytBase64(String password) {
        byte[] bytes = password.getBytes();
        return Base64.encodeToString(bytes);
    }
    /**
     * base64进制解密
     * @param cipherText
     * @return
     */
    public static String decryptBase64(String cipherText) {
        return Base64.decodeToString(cipherText);
    }
    /**
     * 16进制加密
     *
     * @param password
     * @return
     */
    public static String encrytHex(String password) {
        byte[] bytes = password.getBytes();
        return Hex.encodeToString(bytes);
    }
    /**
     * 16进制解密
     * @param cipherText
     * @return
     */
    public static String decryptHex(String cipherText) {
        return new String(Hex.decode(cipherText));
    }


    public static String generateToken(String userName){
        String random=new SecureRandomNumberGenerator().nextBytes().toHex();
        String key = new Md5Hash(userName,
                ByteSource.Util.bytes(random),3).toString();
        return key;
    }

    public static Boolean checkPwd(String loginPwd,String pwd,String salt){
        String result = new Md5Hash(loginPwd,
                ByteSource.Util.bytes(salt),3).toString();
        return result.equals(pwd);
    }

    public static String generatePwd(String password,String salt){
        String result = new Md5Hash(password,
                ByteSource.Util.bytes(salt),3).toString();
        return result;
    }
}
