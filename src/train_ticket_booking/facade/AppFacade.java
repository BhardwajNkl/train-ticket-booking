package train_ticket_booking.facade;

import java.util.List;
import java.util.Set;

import train_ticket_booking.factory.AppContext;
import train_ticket_booking.model.BookingConfirmationDto;
import train_ticket_booking.model.BookingRequestDto;
import train_ticket_booking.model.Train;
import train_ticket_booking.service.BookingService;
import train_ticket_booking.service.TrainService;

public class AppFacade {
	private BookingService bookingService;
	private TrainService trainService;

	public AppFacade() {
		this.bookingService = AppContext.getObject(BookingService.class);
		this.trainService = AppContext.getObject(TrainService.class);
	}

	public Train searchTrainByTrainNumber(String trainNumber) {
		return trainService.searchTrainByTrainNumber(trainNumber);
	}

	public List<Train> searchTrainByRoute(String source, String destination) {
		return trainService.searchTrainByRoute(source, destination);
	}

	public boolean isSeatAvailable(String trainNumber) {
		return trainService.isSeatAvailable(trainNumber);
	}

	public void getCoachDetails(String trainNumber, int coachNumber) {
		trainService.getCoachDetails(trainNumber, coachNumber);
	}

	public BookingConfirmationDto bookTicket(BookingRequestDto bookingRequestDto) {
		return bookingService.bookTicket(bookingRequestDto);
	}

	public Set<BookingConfirmationDto> getBookingRegister() {
		return bookingService.getBookingRegister();
	}

}
