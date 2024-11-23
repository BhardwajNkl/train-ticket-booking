package train_ticket_booking.model;

/***
 * seats-1,2,3,4....60 lowerseats-1,4,7,...58 : nth lowerseat= 1+(n-1)*3
 * middleseats-2,5,8,...59 : nth middleseat= 2+(n-1)*3 upperseats-3,6,9,....60 :
 * nth upperseat= 3+(n-1)*3 seats boolean: true means filled else available
 * 
 * @author nikhilbhardwaj01
 *
 */
public class Coach {
	private final int TOTAL_SEATS = 6;
	private final int LOWER_SEATS = 2;
	private final int MIDDLE_SEATS = 2;
	private final int UPPER_SEATS = 2;

	private int coachNumber;
	private boolean seats[];
	private int seatsAvailable;
	private int lowerSeatsAvailableCount;
	private int middleSeatsAvailableCount;
	private int upperSeatsAvailableCount;
	
	public Coach(int coachNumber) {
		this.coachNumber = coachNumber;
		seats = new boolean[TOTAL_SEATS];
		seatsAvailable = TOTAL_SEATS;
		lowerSeatsAvailableCount = LOWER_SEATS;
		middleSeatsAvailableCount = MIDDLE_SEATS;
		upperSeatsAvailableCount = UPPER_SEATS;
	}

	public boolean isSeatAvailable() {
		return seatsAvailable > 0;
	}
	
	public boolean isPreferredBerthAvailable(String berthPreference) {
		switch(berthPreference) {
		case "LOWER": return isLowerSeatAvailable();
		case "MIDDLE": return isMiddleSeatAvailable();
		case "UPPER": return isUpperSeatAvailable();
		default: return false;
		}
	}

	public boolean isLowerSeatAvailable() {
		return lowerSeatsAvailableCount > 0;
	}

	public boolean isMiddleSeatAvailable() {
		return middleSeatsAvailableCount > 0;
	}

	public boolean isUpperSeatAvailable() {
		return upperSeatsAvailableCount > 0;
	}

	public double seatsFilledPercentage() {
		int seatsFilled = TOTAL_SEATS - seatsAvailable;
		double seatsFilledPercentage = ((double)seatsFilled / TOTAL_SEATS) * 100; // type caste
		return seatsFilledPercentage;
	}
	
	// this is used for sorting the list of coaches based on seats percentage filled.
		// can we achive this same goal of sorting value with help of getPriceIncrement() method? in that case getPriceIncrement can be used for 2 purposes.
	public int seatsFilledPercentageEquivalance() {
		double seatsFilledPercentage = this.seatsFilledPercentage();
		if(seatsFilledPercentage<20) return 1;
		else if(seatsFilledPercentage<30) return 2;
		else if(seatsFilledPercentage<35) return 3;
		else if(seatsFilledPercentage<40) return 4;
		return 5;
	}
	
	private int bookLowerSeat() {
		// check if a lower seat is available. else book in other type.
					if(lowerSeatsAvailableCount==0) {
						throw new RuntimeException("LOWER BERTH IS NOT AVAILABLE IN THE COACH");
					}
					
					// get the last booked seat in the lower category
					int bookedLowerSeats = LOWER_SEATS - lowerSeatsAvailableCount;
					int nextLowerSeat = 1 + (bookedLowerSeats) * 3; // Nth term formula
					seats[nextLowerSeat - 1] = true;
					// change seats availability numbers
					lowerSeatsAvailableCount--;
					seatsAvailable--;
					// return the booked seat number
					return nextLowerSeat;
	}
	
	private int bookMiddleSeat() {
		// check if a lower seat is available. else book in other type.
		if(middleSeatsAvailableCount==0) {
			throw new RuntimeException("MIDDLE BERTH IS NOT AVAILABLE IN THE COACH");
		}
		
// get the last booked seat in the lower category
int bookedMiddleSeats = MIDDLE_SEATS - middleSeatsAvailableCount;
int nextMiddleSeat = 2 + (bookedMiddleSeats) * 3; // Nth term formula
seats[nextMiddleSeat - 1] = true;
// change seats availability numbers
middleSeatsAvailableCount--;
seatsAvailable--;
// return the booked seat number
return nextMiddleSeat;
	}
	
	private int bookUpperSeat() {
		// check if a lower seat is available. else book in other type.
		if(upperSeatsAvailableCount==0) {
			throw new RuntimeException("UPPER BERTH IS NOT AVAILABLE IN THE COACH");
		}
		
// get the last booked seat in the lower category
int bookedUpperSeats = UPPER_SEATS - upperSeatsAvailableCount;
int nextUpperSeat = 3 + (bookedUpperSeats) * 3; // Nth term formula
seats[nextUpperSeat - 1] = true;
// change seats availability numbers
upperSeatsAvailableCount--;
seatsAvailable--;
// return the booked seat number
return nextUpperSeat;
	}

	public int bookSeat(String seatPreference) {
		switch (seatPreference) {
		case "LOWER": {
			
//			// check if a lower seat is available. else book in other type.
//			if(lowerSeatsAvailableCount==0) {
//				throw new RuntimeException("LOWER BERTH IS NOT AVAILABLE IN THE COACH");
//			}
//			
//			// get the last booked seat in the lower category
//			int bookedLowerSeats = LOWER_SEATS - lowerSeatsAvailableCount;
//			int nextLowerSeat = 1 + (bookedLowerSeats) * 3; // Nth term formula
//			seats[nextLowerSeat - 1] = true;
//			// change seats availability numbers
//			lowerSeatsAvailableCount--;
//			seatsAvailable--;
//			// return the booked seat number
//			return nextLowerSeat;
			
			int bookedSeat;
			try {
				bookedSeat = bookLowerSeat();
			} catch (Exception e) {
				// since lower seat could not be booked. book in middle or upper.
				if(middleSeatsAvailableCount>0) {
					bookedSeat = bookMiddleSeat();
				} else {
					bookedSeat = bookUpperSeat();
				}
			}
			
			return bookedSeat;
		}

		case "MIDDLE": {
//			// check if a lower seat is available. else book in other type.
//						if(middleSeatsAvailableCount==0) {
//							throw new RuntimeException("MIDDLE BERTH IS NOT AVAILABLE IN THE COACH");
//						}
//						
//			// get the last booked seat in the lower category
//			int bookedMiddleSeats = MIDDLE_SEATS - middleSeatsAvailableCount;
//			int nextMiddleSeat = 2 + (bookedMiddleSeats) * 3; // Nth term formula
//			seats[nextMiddleSeat - 1] = true;
//			// change seats availability numbers
//			middleSeatsAvailableCount--;
//			seatsAvailable--;
//			// return the booked seat number
//			return nextMiddleSeat;
			
			int bookedSeat;
			try {
				bookedSeat = bookMiddleSeat();
			} catch (Exception e) {
				// since middle seat could not be booked. book in lower or upper.
				if(lowerSeatsAvailableCount>0) {
					bookedSeat = bookLowerSeat();
				} else {
					bookedSeat = bookUpperSeat();
				}
			}
			
			return bookedSeat;
		}

		case "UPPER": {
//			// check if a lower seat is available. else book in other type.
//						if(upperSeatsAvailableCount==0) {
//							throw new RuntimeException("UPPER BERTH IS NOT AVAILABLE IN THE COACH");
//						}
//						
//			// get the last booked seat in the lower category
//			int bookedUpperSeats = UPPER_SEATS - upperSeatsAvailableCount;
//			int nextUpperSeat = 3 + (bookedUpperSeats) * 3; // Nth term formula
//			seats[nextUpperSeat - 1] = true;
//			// change seats availability numbers
//			upperSeatsAvailableCount--;
//			seatsAvailable--;
//			// return the booked seat number
//			return nextUpperSeat;
			
			int bookedSeat;
			try {
				bookedSeat = bookUpperSeat();
			} catch (Exception e) {
				// since upper seat could not be booked. book in lower or middle.
				if(lowerSeatsAvailableCount>0) {
					bookedSeat = bookLowerSeat();
				} else {
					bookedSeat = bookUpperSeat();
				}
			}
			
			return bookedSeat;
		}

		default: {
//			throw new RuntimeException("Invald berth preference!");
			// book in any berth
			if(lowerSeatsAvailableCount>0) return bookLowerSeat();
			if(middleSeatsAvailableCount>0) return bookMiddleSeat();
			return bookUpperSeat();
		}

		}
	}
	
	public int getCoachNumber() {
		return coachNumber;
	}
	
	public boolean[] getSeats() {
		return seats;
	}
	
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	
	public float getCurrentSeatPrice() {
		// PROVIDE LOGIC
		return 2000;
	}

}
