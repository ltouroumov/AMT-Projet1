package ch.ltouroumov.heig.amt.project1.security;

import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.Stateless;

/**
 * Encodes passwords through a simple hash
 *
 * @author ldavid
 * Created: 10/3/16
 */
@Stateless
public class HashPasswordEncoder implements IPasswordEncoder {

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode(String password) {
        return DigestUtils.sha1Hex(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(String refPassword, String password) {
        return DigestUtils.sha1Hex(password).equalsIgnoreCase(refPassword);
    }
}
