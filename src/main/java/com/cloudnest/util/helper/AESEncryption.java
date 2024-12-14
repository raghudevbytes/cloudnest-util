package com.cloudnest.util.helper;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESEncryption {
    public static String encrypt(String data) {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[Constants.IV_LENGTH]);
        try {
            Cipher cipher = Cipher.getInstance(Constants.CIPHER_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, KeyLoaderUtil.loadSecretKey("aes"),ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e
                 ) {
         throw new RuntimeException(e);
        }
    }
}
