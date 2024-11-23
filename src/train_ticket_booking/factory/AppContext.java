package train_ticket_booking.factory;

import java.util.HashMap;
import java.util.Map;

import train_ticket_booking.facade.AppFacade;
import train_ticket_booking.repository.BookingRepository;
import train_ticket_booking.repository.TrainRepository;
import train_ticket_booking.service.BookingService;
import train_ticket_booking.service.TrainService;

public class AppContext {
	private static Map<Class, Object> beans = new HashMap<>();
		
	public static void initialize() {
		getObject(BookingRepository.class);
		getObject(TrainRepository.class);
		getObject(BookingService.class);
		getObject(TrainService.class);
		getObject(AppFacade.class);
	}
	
	public static <T> T getObject(Class<T> clazz) {
		if(!beans.containsKey(clazz)) {
			if(clazz==BookingRepository.class) {
				beans.put(clazz, new BookingRepository());
			}
			if(clazz==TrainRepository.class) {
				beans.put(clazz, new TrainRepository());
			}
			if(clazz==BookingService.class) {
				beans.put(clazz, new BookingService());
			}
			if(clazz==TrainService.class) {
				beans.put(clazz, new TrainService());
			}
			if(clazz==AppFacade.class) {
				beans.put(clazz, new AppFacade());
			}
		}
		
		return (T) beans.get(clazz);
	}
	
}
