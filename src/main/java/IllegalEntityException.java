/**
 * Created by kost on 2. 4. 2015.
 */
public class IllegalEntityException extends Exception{

    public IllegalEntityException(String message,Throwable cause)
    {
        super(message,cause);
    }

    public IllegalEntityException(String message)
    {
        super(message);
    }
}
