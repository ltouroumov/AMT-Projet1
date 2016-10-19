package ch.ltouroumov.heig.amt.project1.security;

/**
 * Allows password encoding before storage
 *
 * @author ldavid
 * Created: 10/3/16
 */
public interface IPasswordEncoder {

    /**
     * Encodes the password from plain to encoded.
     * @param password Password to encode
     * @return Encoded password
     */
    String encode(String password);

    /**
     * Check a plain password against an encoded reference
     * @param refPassword Encoded reference
     * @param password Plain password
     * @return Match status
     */
    boolean check(String refPassword, String password);

}
