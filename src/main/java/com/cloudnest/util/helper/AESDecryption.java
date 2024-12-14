package com.cloudnest.util.helper;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESDecryption {
    public static String decrypt(String encData) {
        try {
            Cipher cipher = Cipher.getInstance(Constants.CIPHER_MODE);
            cipher.init(Cipher.DECRYPT_MODE, KeyLoaderUtil.loadSecretKey("aes"),new IvParameterSpec(new byte[Constants.IV_LENGTH]));
            byte[] decBytes = cipher.doFinal(Base64.getDecoder().decode(encData));
            return new String(decBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                 | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

}
