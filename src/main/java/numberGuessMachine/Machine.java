package numberGuessMachine;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Machine {
	
	private long id;
	private int gameCount;
	private int low = 1;
	private int high = 100;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getGameCount() {
		return gameCount;
	}
	
	public void guess() {
		int guess = findNumber(this.low, this.high);
		System.out.println("Guess: " + guess);
		String guessUrl = "http://localhost:8080/guess"
				+ "?guessedNum=" + guess
				+ "&gameCount=" + this.gameCount
				+ "&userID=" + this.id;
		JsonNode response = get(guessUrl);
		this.gameCount = response.path("gameCount").asInt();
		String tooLow = "Higher than " + guess + ", try again";
		String tooHigh = "Lower than " + guess + ", try again";
		String message = response.path("content").asText();
		System.out.println(message);
		if(message.equals(tooHigh))
			this.high = guess;
		else if (message.equals(tooLow))
			this.low = guess;
		else {
			this.high = 100;
			this.low = 1;
		}
	}

	public void setGameCount() {
		JsonNode response = get("http://localhost:8080/game/count");
		JsonNode gameCount = response.path("message");
		System.out.println("Current game count is: " + gameCount.asText());
		this.gameCount = gameCount.asInt();
	}
	        
	public int findNumber(int lowest, int highest) {
		int difference = highest - lowest;
		return (lowest + (difference / 2)); 
	}
	
	public JsonNode get(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
		try {
			root = mapper.readTree(response.getBody());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return root;
	}
	
	public void checkForGame() {
		boolean gameNotReady = true;
		while (gameNotReady) {
			try {
				System.out.println("Attempting connection to NumberGuess program.");
				this.get("http://localhost:8080/game/count");
				gameNotReady = false;
			}
			catch (Exception e){
				gameNotReady = true;
			}
			System.out.println("Waiting...\n");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
