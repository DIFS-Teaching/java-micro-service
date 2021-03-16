package cz.vut.fit.pis.micro;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 *
 * @author airhacks.com
 */
@ApplicationScoped
@Path("ping")
public class PingResource {

    @Inject
    @ConfigProperty(name="name", defaultValue="Nobody")
    private String name;
    
    private int queue = 42;
    
    @GET
    @Path("m")
    @Metered(name = "pingMeter")
    public String ping_metered() {
        queue++;
        return "Metered ping, hello " + name;
    }
    
    @GET
    @Path("t")
    @Timed(name = "pingTimer")
    public String ping_timed() {
        queue--;
        return "Timed ping, hello " + name;
    }
    
    @GET
    @Path("q")
    @Gauge(unit = "items")
    public int queueSize() {
        return queue;
    }

}
