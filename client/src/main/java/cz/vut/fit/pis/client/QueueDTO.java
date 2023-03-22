/**
 * ResultMessage.java
 *
 * Created on 24. 3. 2022, 11:05:26 by burgetr
 */
package cz.vut.fit.pis.client;


/**
 * The response from the Queue service.
 * 
 * @author burgetr
 */
public class QueueDTO
{
    private String status;
    private String message;
    
    public QueueDTO()
    {
    }
    
    public QueueDTO(String status, String message)
    {
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
