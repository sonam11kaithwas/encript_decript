package com.advantal.myapplication;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    //CaesarCypher cCypher = new CaesarCypher(10);
    String plainText = "Android Developer";
    String key16Byte = "advantaladvantal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Running java program");


        try {
            System.out.println("Plain Text  : " + plainText);


            String enCodedString = AESCipher.aesEncryptString(plainText, key16Byte);
            System.out.println("Encoded String  : " + enCodedString + " || length = " + enCodedString.length());
// dDxQOs46xOh5lTf3dwHZ/AR89b62pr31S1CXmBNhrts=


            String deCodedString = AESCipher.aesDecryptString(enCodedString, key16Byte);
            System.out.println("Decoded String : " + deCodedString + " || length = " + deCodedString.length());


        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // Set up secret key spec f     double i= Math.random();
        //       Log.e("","");     double i= Math.random();
        //       Log.e("","");or 128-bit AES encryption and decryption
        SecretKeySpec sks = null;
        try {
//            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//            sr.setSeed("any data used as random seed".getBytes());
//            KeyGenerator kg = KeyGenerator.getInstance("AES");
//            kg.init(128, sr);
//            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
            sks = new SecretKeySpec(key16Byte.getBytes(StandardCharsets.UTF_8), "AES");
        } catch (Exception e) {
            Log.e("MainActivity:", "AES secret key spec error");
        }

        // Encode the original data with AES
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(plainText.getBytes());
        } catch (Exception e) {
            Log.e("TAG", "AES encryption error");
        }

        System.out.println("Encoded String  : [ENCODED]:-----------" +
                Base64.encodeToString(encodedBytes, Base64.DEFAULT));


        // Decode the encoded data with AES
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, sks);
            decodedBytes = c.doFinal(encodedBytes);
        } catch (Exception e) {
            Log.e("TAG", "AES decryption error");
        }

        System.out.println("[DECODED]:----------------" + new String(decodedBytes));
        System.out.println("My Code");

    }
}