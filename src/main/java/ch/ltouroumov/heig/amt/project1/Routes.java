package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.router.*;

/**
 * Route handlers for servlets
 *
 * @author ldavid
 * Created: 9/28/16
 */
public class Routes implements IRouterConfig {
    @Override
    public void configure(RouteCollection router) {
        router.map("/assets").prefix().to(Route.DEFAULT_HANDLER);

        router.map("/").to(new ForwardHandler("/WEB-INF/pages/index.jsp"));
        router.map("/pokemons").to(new ServletHandler("/WEB-INF/pages/pokemons.jsp"));
        router.map("/users").to(new ForwardHandler("/WEB-INF/pages/users.jsp"));

        router.map("/register").to(new ServletHandler("RegisterServlet"));
        router.map("/login").to(new ServletHandler("LoginServlet"));
        router.map("/logout").to(new ServletHandler("LogoutServlet"));
        router.map("/profile").to(new ServletHandler("ProfileServlet")).filter(AuthenticatedFilter.class);
    }
}
