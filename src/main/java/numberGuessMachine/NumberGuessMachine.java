package numberGuessMachine;


import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;

public class NumberGuessMachine {

	public static void main(String[] args) {
		
		Machine theMachine = new Machine();
		theMachine.checkForGame();
		
		String registerUrl = "http://localhost:8080/player/register?name=TheMachine&password=1234";
		String loginUrl = "http://localhost:8080/player/login?name=TheMachine&password=1234";

		theMachine.get(registerUrl);
		JsonNode login = theMachine.get(loginUrl);
		System.out.println("Name--------------------- " + login.path("name").asText());
		System.out.println("Id--------------------- " + login.path("id").asText());
		if (login.path("name").equals(null))
			System.exit(0);
		
		theMachine.setId(login.path("id").asLong());
		theMachine.setGameCount();
		while (true) {
			theMachine.guess();
			System.out.println("Pausing for ten seconds\n");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
