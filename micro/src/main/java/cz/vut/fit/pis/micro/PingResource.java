package cz.vut.fit.pis.micro;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * See
 * https://www.adam-bien.com/roller/abien/entry/microprofile_metered_vs_timed
 *
 * @author burgetr
 */
@RequestScoped
@Path("ping")
@Tag(name = "ping", description = "Simple ping resource")
public class PingResource {

    @Inject
    @ConfigProperty(name="name", defaultValue="Nobody")
    private String name;
    
    private int queue = 42;
    
    @GET
    @Path("m")
    @Produces(MediaType.APPLICATION_JSON)
    @Metered(name = "pingMeter")
    @Operation(operationId = "pingUp", summary = "Increments the queue length")
    @APIResponse(responseCode = "200", description = "Queue incremented",
            content = @Content(schema = @Schema(ref = "ResultMessage")))
    public ResultMessage ping_metered() {
        queue++;
        return new ResultMessage("ok", "Metered ping, hello " + name);
    }
    
    @GET
    @Path("t")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed(name = "pingTimer")
    @Operation(operationId = "pingDown", summary = "Decrements the queue length")
    @APIResponse(responseCode = "200", description = "Queue decremented",
            content = @Content(schema = @Schema(ref = "ResultMessage")))
    public ResultMessage ping_timed() {
        queue--;
        return new ResultMessage("ok", "Timed ping, hello " + name);
    }
    
    @GET
    @Path("q")
    @Produces(MediaType.APPLICATION_JSON)
    @Gauge(unit = "items")
    public ResultMessage queueSize() {
        return new ResultMessage("ok", "Queue is " + queue);
    }

}
