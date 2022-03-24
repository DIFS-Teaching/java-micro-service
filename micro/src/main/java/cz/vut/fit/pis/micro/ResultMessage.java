/**
 * ResultMessage.java
 *
 * Created on 24. 3. 2022, 11:05:26 by burgetr
 */
package cz.vut.fit.pis.micro;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * A simple status message returned from REST API.
 * 
 * @author burgetr
 */
@Schema(name = "ResultMessage", description = "Result of an operation")
public class ResultMessage
{
    private String status;
    private String message;
    
    public ResultMessage(String status, String message)
    {
        super();
        this.status = status;
        this.message = message;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
