package fi.oispakaljaa.karhu.APItemplates;

/**
 * Created by hugoh on 11/12/2016.
 */
public class OispakaljaaTemplate {
    public String status;
    public String message;
    public Object data;

    public OispakaljaaTemplate(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;

    }
}
