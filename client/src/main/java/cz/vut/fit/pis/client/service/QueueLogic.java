/**
 * 
 */
package cz.vut.fit.pis.client.service;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

import cz.vut.fit.pis.client.queue.QueueClient;
import cz.vut.fit.pis.client.queue.QueueDTO;
import cz.vut.fit.pis.client.rest.ResultDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 *
 * @author burgetr
 */
@ApplicationScoped
public class QueueLogic
{
    @Inject
    private QueueClient queueClient;

    @Transactional
    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "queueFallback")
    public ResultDTO queueItems(int count) {
    	QueueDTO lastResult = null;
    	for (int i = 0; i < count; i++)
    		lastResult = queueClient.queueUp();
        int finalLength = queueClient.queueLength();
        return new ResultDTO(finalLength, lastResult.getMessage());
    }
    
    public ResultDTO queueFallback(int count) {
    	return new ResultDTO(-1, "Queue service not available");
    }
    
}
