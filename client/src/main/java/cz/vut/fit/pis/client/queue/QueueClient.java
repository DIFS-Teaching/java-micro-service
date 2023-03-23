/**
 * QueueClient.java
 *
 * Created on 22. 3. 2023, 14:28:23 by burgetr
 */
package cz.vut.fit.pis.client.queue;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * 
 * @author burgetr
 */
@RegisterRestClient(configKey = "QueueService", baseUri = "http://localhost:9080/micro/")
@Path("/api/queue")
public interface QueueClient
{

    @GET
    @Path("/m")
    @Produces(MediaType.APPLICATION_JSON)
    public QueueDTO queueUp();
    
    @GET
    @Path("/t")
    @Produces(MediaType.APPLICATION_JSON)
    public QueueDTO queueDown();
    
    @GET
    @Path("/q")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer queueLength();
    
}
