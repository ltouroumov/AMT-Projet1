package ch.ltouroumov.heig.amt.project1.user;

/**
 * Created by ldavid on 10/3/16.
 */
public interface IPasswordEncoder {

    String encode(String password);

    boolean check(String refPassword, String password);

}
