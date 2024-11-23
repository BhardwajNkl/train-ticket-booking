package train_ticket_booking.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import train_ticket_booking.model.BookingConfirmationDto;

public class BookingRepository {
	Set<BookingConfirmationDto> bookings;
	
	public BookingRepository() {
		bookings = new HashSet<>();
	}
	
	public void addBooking(BookingConfirmationDto booking) {
		bookings.add(booking);
	}
	
	public BookingConfirmationDto getBooking(String trainNumber, int coachNumber,
			int seatNumber) {
		Optional<BookingConfirmationDto> result = bookings.stream()
		.filter(
				booking -> booking.getTrainNumber().equalsIgnoreCase(trainNumber)
					&& booking.getCoachNumber()== coachNumber
					&& booking.getSeatNumber()==seatNumber
				)
		.findFirst();
		
		if(result.isPresent()) {
			return result.get();
		}
		
		throw new RuntimeException("Booking Not Found!");
	}
	
	public Set<BookingConfirmationDto> getAllBookings(){
		return bookings;
	}
}
