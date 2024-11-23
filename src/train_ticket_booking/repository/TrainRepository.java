package train_ticket_booking.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import train_ticket_booking.model.Train;

public class TrainRepository {
	List<Train> trains;
	
	public TrainRepository() {
		trains = new ArrayList<>();
		
		// initializing with 2 trains
		Train train1 = new Train("t101", "patna", "delhi");
		Train train2 = new Train("t201", "patna", "kashi");
		Train train3 = new Train("t301", "patna", "delhi");
		Train train4 = new Train("t401", "patna", "mumbai");
		
		trains.addAll(Arrays.asList(train1, train2, train3, train4));
	}
	
	// find by train number
	public Train findByTrainNumber(String trainNumber) {
		Optional<Train> result = trains.stream()
				.filter(train->train.getTrainNumber().equalsIgnoreCase(trainNumber))
				.findFirst();
		if(result.isPresent()) return result.get();
		throw new RuntimeException("Train with given number does not exist.");
	}
	
	// find by train number: CNSIDER RETURNING LIST SO THAT USER CAN CHOOSE WHICH TRAIN TO PICK
		public List<Train> findByRoute(String source, String destination) {
			List<Train> result = trains.stream()
					.filter(
							train->train.getSource().equalsIgnoreCase(source)
							&&
							train.getDestination().equalsIgnoreCase(destination)
							)
					.toList();
			
			if(result.size()>0) return result;
			
			throw new RuntimeException("No trains available for the given route.");
		}
}
