package train_ticket_booking.service;

import java.util.List;
import java.util.Set;

import train_ticket_booking.factory.AppContext;
import train_ticket_booking.model.BookingConfirmationDto;
import train_ticket_booking.model.BookingRequestDto;
import train_ticket_booking.model.Coach;
import train_ticket_booking.model.Train;
import train_ticket_booking.repository.BookingRepository;
import train_ticket_booking.repository.TrainRepository;

public class BookingService {
	private TrainService trainService;
	private TrainRepository trainRepository;
	private BookingRepository bookingRepository;
	
	public BookingService() {
		this.trainRepository = AppContext.getObject(TrainRepository.class);
		this.bookingRepository = AppContext.getObject(BookingRepository.class);
		this.trainService = AppContext.getObject(TrainService.class);
	}
	
	public BookingConfirmationDto bookTicket(BookingRequestDto bookingRequestDto) {
		
		// check if seat is available
		if(!trainService.isSeatAvailable(bookingRequestDto.getTrainNumber())) {
			throw new RuntimeException("SEAT IS NOT AVAILABLE IN THIS TRAIN!");
		}
		
		
		// proceed with booking logic: successful booking should return {coachNumber, seatNumber}
		// 1. filter coaches based on seat available and berth available
		Train train = trainRepository.findByTrainNumber(bookingRequestDto.getTrainNumber());
		List<Coach> coachesHavingSeat = train.getCoaches()
				.stream()
				.filter(
						coach->coach.isSeatAvailable()
						) // currently not considering the berth
				.toList();
		
		List<Coach> coachesHavingBerthPreferenceAvailable = coachesHavingSeat.stream()
				.filter(coach->
				coach.isPreferredBerthAvailable(bookingRequestDto.getBerthPreference()
						.toString())
				).toList();
		List<Coach> finalCoachList = coachesHavingBerthPreferenceAvailable.size()>0 ?
				coachesHavingBerthPreferenceAvailable : coachesHavingSeat;
		
		// 2. choose the best coach(least percentage filled) : sort in increasing order of seats filled percentage and then pick first.
		Coach coach = finalCoachList.stream()
			.sorted(
					(coach1, coach2)->
//					Double.compare(coach1.seatsFilledPercentage(),
//							coach2.seatsFilledPercentage())
					Integer.compare(coach1.seatsFilledPercentageEquivalance(),
							coach2.seatsFilledPercentageEquivalance())
					)
			.findFirst().get();
		
		// 3. book ticket in this coach
		// 3.1. LOGIC: for current seat price of this coach.
		float currentSeatPrice = coach.getCurrentSeatPrice();
		int seatNumberBooked = coach.bookSeat(bookingRequestDto.getBerthPreference().toString());
		
		// register this booking
		BookingConfirmationDto booking = new BookingConfirmationDto();
		booking.setPassangerName(bookingRequestDto.getPassangerName());
		booking.setAge(bookingRequestDto.getAge());
		booking.setTrainNumber(bookingRequestDto.getTrainNumber());
		booking.setBerthPreference(bookingRequestDto.getBerthPreference().toString());
		booking.setFoodPreference(bookingRequestDto.getFoodPreference().toString());
		booking.setDate(bookingRequestDto.getDate());
		booking.setCoachNumber(coach.getCoachNumber());
		booking.setSeatNumber(seatNumberBooked);
		booking.setPrice(currentSeatPrice);
		
		bookingRepository.addBooking(booking);
		
		// RETURN OR PRINT. currently just printing
//		System.out.println(booking);
		return booking;
		
	}
	
	public Set<BookingConfirmationDto> getBookingRegister() {
		return bookingRepository.getAllBookings();
	}
}
