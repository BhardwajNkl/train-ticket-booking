package train_ticket_booking.service;

import java.util.Arrays;
import java.util.List;

import train_ticket_booking.factory.AppContext;
import train_ticket_booking.model.Coach;
import train_ticket_booking.model.Train;
import train_ticket_booking.repository.TrainRepository;

public class TrainService {
	private TrainRepository trainRepository;
	
	public TrainService() {
		this.trainRepository = AppContext.getObject(TrainRepository.class);
	}
	
	public Train searchTrainByTrainNumber(String trainNumber) {
		return trainRepository.findByTrainNumber(trainNumber);
	}

	public List<Train> searchTrainByRoute(String source, String destination) {
		return trainRepository.findByRoute(source, destination);
	}
	
	public boolean isSeatAvailable(String trainNumber) {
		Train train = trainRepository.findByTrainNumber(trainNumber);
		// check if any coach has at least 1 seat available
		return train.getCoaches().stream()
		.anyMatch(coach->coach.isSeatAvailable());
	}
	
	public void getCoachDetails(String trainNumber, int coachNumber) {
		Train train = trainRepository.findByTrainNumber(trainNumber);
		Coach coach = train.getCoaches().get(coachNumber-1);
		// print coach array. seats filled. seats available. current seat price.
		System.out.println(Arrays.toString(coach.getSeats()));
		System.out.println("Seats Available: "+coach.getSeatsAvailable());
		
	}

}
