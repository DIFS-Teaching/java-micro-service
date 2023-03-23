package cz.vut.fit.pis.client.rest;

import cz.vut.fit.pis.client.service.QueueLogic;
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
    private QueueLogic queueLogic;
    
    @GET
    @Path("q4")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO queueFourItems() {
        return queueLogic.queueItems(4);
    }

}
