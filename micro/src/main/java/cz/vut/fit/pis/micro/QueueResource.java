package cz.vut.fit.pis.micro;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
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
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

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
    @Counted(name = "queueCount")
    @Operation(operationId = "queueUp", summary = "Increments the queue length")
    @APIResponse(responseCode = "200", description = "Queue incremented",
            content = @Content(schema = @Schema(ref = "ResultMessage")))
    public Response queueUp_metered() {
        if (queue < 60)
        {
            queue++;
        	return Response.ok(new ResultMessage("ok", "Metered queue up, hello " + name)).build();
        }
        else
        	return Response.status(Status.INTERNAL_SERVER_ERROR)
        			.entity(new ResultMessage("error", "Queue overflow"))
        			.build();
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
