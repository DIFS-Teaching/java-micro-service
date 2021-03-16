/**
 * 
 */
package cz.vut.fit.pis.micro;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

/**
 * @author burgetr
 *
 */
@Readiness
@Liveness
public class HealthChecker implements HealthCheck
{

    @Override
    public HealthCheckResponse call()
    {
        return HealthCheckResponse.up("Ready and alive!");
    }
    
}
