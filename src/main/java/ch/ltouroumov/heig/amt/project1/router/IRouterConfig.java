package ch.ltouroumov.heig.amt.project1.router;

/**
 * Configures the provided route collection with routes for the application.
 *
 * @author ldavid
 * Created: 9/28/16
 */
public interface IRouterConfig {

    /**
     * Configures the provided route collection with routes for the application.
     *
     * @param router Router to configure
     */
    void configure(RouteCollection router);

}
