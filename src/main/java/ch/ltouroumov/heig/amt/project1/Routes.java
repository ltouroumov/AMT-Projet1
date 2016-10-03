package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.router.IRouterConfig;
import ch.ltouroumov.heig.amt.project1.router.Router;

/**
 * Created by ldavid on 9/28/16.
 */
public class Routes implements IRouterConfig {
    @Override
    public void configure(Router router) {
        router.map("/assets").prefix().to("default");

        router.map("/").to("HomeServlet");
        router.map("/register").to("RegisterServlet");
        router.map("/login").to("LoginServlet");
        router.map("/logout").to("LogoutServlet");
        router.map("/profile").to("ProfileServlet").filter(AuthenticatedFilter.class);
    }
}
