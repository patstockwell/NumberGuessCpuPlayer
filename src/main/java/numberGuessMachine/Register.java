package numberGuessMachine;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"categories"})
public class Register {
	
	private String message;
	
	public Register () {
	}

	public String getResponse() {
		return message;
	}

	public void setResponse(String response) {
		this.message = response;
	}
	
	@Override
    public String toString() {
        return "Register{" +
                "message='" + message +
                "'}";
    }

}
