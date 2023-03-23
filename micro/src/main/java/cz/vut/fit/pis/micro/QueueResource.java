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

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author burgetr
 */
@ApplicationScoped
@Path("queue")
@Tag(name = "ping", description = "Simple queue resource")
public class QueueResource {

    @Inject
    @ConfigProperty(name="name", defaultValue="Nobody")
    private String name;
    
    private int queue = 42;
    
    @GET
    @Path("m")
    @Produces(MediaType.APPLICATION_JSON)
    @Metered(name = "queueMeter")
    @Operation(operationId = "queueUp", summary = "Increments the queue length")
    @APIResponse(responseCode = "200", description = "Queue incremented",
            content = @Content(schema = @Schema(ref = "ResultMessage")))
    public ResultMessage queueUp_metered() {
        queue++;
        return new ResultMessage("ok", "Metered queue up, hello " + name);
    }
    
    @GET
    @Path("t")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed(name = "queueTimer")
    @Operation(operationId = "queueDown", summary = "Decrements the queue length")
    @APIResponse(responseCode = "200", description = "Queue decremented",
            content = @Content(schema = @Schema(ref = "ResultMessage")))
    public ResultMessage queueDown_timed() {
        queue--;
        return new ResultMessage("ok", "Timed queue down, hello " + name);
    }
    
    @GET
    @Path("q")
    @Produces(MediaType.APPLICATION_JSON)
    @Gauge(unit = "items")
    public int queueSize() {
        return queue;
    }

}