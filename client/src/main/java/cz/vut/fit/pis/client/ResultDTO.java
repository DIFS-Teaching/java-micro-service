/**
 * ResultMessage.java
 *
 * Created on 24. 3. 2022, 11:05:26 by burgetr
 */
package cz.vut.fit.pis.client;


/**
 * A simple status message returned from REST API.
 * 
 * @author burgetr
 */
public class ResultDTO
{
    private Integer queueLength;
    private String message;
    
    public ResultDTO()
    {
    }

    public ResultDTO(Integer queueLength, String message)
    {
        super();
        this.queueLength = queueLength;
        this.message = message;
    }

    public Integer getQueueLength()
    {
        return queueLength;
    }

    public void setQueueLength(Integer queueLength)
    {
        this.queueLength = queueLength;
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
