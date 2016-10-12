package ch.ltouroumov.heig.amt.project1.security;

import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.Stateless;

/**
 * Created by ldavid on 10/3/16.
 */
@Stateless
public class HashPasswordEncoder implements IPasswordEncoder {
    @Override
    public String encode(String password) {
        return DigestUtils.sha1Hex(password);
    }

    @Override
    public boolean check(String refPassword, String password) {
        return DigestUtils.sha1Hex(password).equalsIgnoreCase(refPassword);
    }
}
