package com.fxs.oauth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptTest {
    @Test
    public void bcryptTest() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("Za123456");
        System.out.println(encode);
        System.out.println(encode.length());
        System.out.println( bCryptPasswordEncoder.matches("Za123456", encode));

    }
}
