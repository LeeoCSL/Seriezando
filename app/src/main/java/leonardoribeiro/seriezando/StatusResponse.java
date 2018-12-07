package leonardoribeiro.seriezando;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusResponse {

    private boolean error;
    private String message;

    private String id;

    public StatusResponse(){}

    public StatusResponse(boolean error, String message, String id) {
        this.error = error;
        this.message = message;
        this.id = id;
    }

    public boolean hasError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "StatusResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +

                '}';
    }
}
