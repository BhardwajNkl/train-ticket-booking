package train_ticket_booking.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
	private final int NUMBER_OF_COACH=2;
	private String trainNumber;
	private String source;
	private String destination;
	
	private List<Coach> coaches;
	
	public Train(String trainNumber, String source, String destination) {
		this.trainNumber = trainNumber;
		this.source = source;
		this.destination = destination;
		coaches = new ArrayList<>(NUMBER_OF_COACH);
		// initialize the coaches
		for(int i=1;i<=NUMBER_OF_COACH;i++) {
			coaches.add(new Coach(i));
		}
	}
	
	public String getTrainNumber() {
		return trainNumber;
	}
	
	public String getSource() {
		return source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public List<Coach> getCoaches(){
		return coaches;
	}
	
	
	@Override
	public String toString() {
		return "[ trainNumber: "+trainNumber+", source: "+source+", Destination: "
				+destination+" ]";
	}
}
