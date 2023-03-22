package cz.vut.fit.pis.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/client")
public class ClientResource {

    @Inject
    private QueueClient queueClient;
    
    @GET
    @Path("q4")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO queueFourItems() {
        queueClient.queueUp();
        queueClient.queueUp();
        queueClient.queueUp();
        QueueDTO lastResult = queueClient.queueUp();
        return new ResultDTO(queueClient.queueLength(), lastResult.getMessage());
    }

}
