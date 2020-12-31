package com.zsk.common.network.config;

import com.blankj.utilcode.util.EncodeUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @创建日期: 2020/12/25 16:13
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
public class AESTool {
    private static String ivParameter="J#y9sJesy*5HmqLq";//密码默认偏移，可更改
    private static AESTool instance=null;
    private final String CIPHERMODEPADDING="AES/CBC/PKCS7Padding";//AES/CBCPKCS5Padding
    private String sKey="J#y9sJesy*5HmqLqEVlyUPYfpH$pHx$!";
    private SecretKeySpec skforAES=null;
    private byte[] iv=ivParameter.getBytes();
    private IvParameterSpec IV;

    public AESTool(){
        byte[] skAsByteArray;
        try{
            skAsByteArray=sKey.getBytes("ASCII");
            skforAES=new SecretKeySpec(skAsByteArray,"AES");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        IV=new IvParameterSpec(iv);
    }

    public static AESTool getInstance(){
        if (instance==null){
            synchronized (AESTool.class){
                if (instance==null){
                    instance=new AESTool();
                }
            }
        }
        return instance;
    }

    public String encrypt(byte[] plaintext){
        byte[] ciphertext=encrypt(CIPHERMODEPADDING,skforAES,IV,plaintext);
        String base64_ciphertext= EncodeUtils.base64Encode2String(ciphertext);
        return base64_ciphertext;
    }
    public String decrypt(String ciphertext_base64){
        byte[] s=EncodeUtils.base64Decode(ciphertext_base64);
        String decrypted=new String(decrypt(CIPHERMODEPADDING,skforAES,IV,s));
        return decrypted;
    }
    private byte[] encrypt(String cmp, SecretKey sk,IvParameterSpec IV,byte[] msg){
        try {
            Cipher c=Cipher.getInstance(cmp);
            c.init(Cipher.ENCRYPT_MODE,sk,IV);
            return c.doFinal(msg);
        } catch (Exception nsae) {

        }
        return null;
    }
    private byte[] decrypt(String cmp, SecretKey sk,IvParameterSpec IV,byte[] ciphertext){
        try {
            Cipher c=Cipher.getInstance(cmp);
            c.init(Cipher.ENCRYPT_MODE,sk,IV);
            return c.doFinal(ciphertext);
        } catch (Exception nsae) {

        }
        return null;
    }
}
