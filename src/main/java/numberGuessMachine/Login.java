package numberGuessMachine;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Login {
	
	private long id;
	private String name;
	private String password;
	private int points;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
