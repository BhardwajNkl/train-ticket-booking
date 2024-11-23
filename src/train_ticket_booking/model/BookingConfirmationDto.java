package train_ticket_booking.model;

public class BookingConfirmationDto {
	private String trainNumber;
	private int coachNumber;
	private int seatNumber;
	private String passangerName;
	private int age;
	private String date;
	private String berthPreference;
	private String foodPreference;
	private float price;
	
	public BookingConfirmationDto() {
	}

	public BookingConfirmationDto(String trainNumber, int coachNumber, int seatNumber, String passangerName, int age,
			String date, String berthPreference, String foodPreference, float price) {
		super();
		this.trainNumber = trainNumber;
		this.coachNumber = coachNumber;
		this.seatNumber = seatNumber;
		this.passangerName = passangerName;
		this.age = age;
		this.date = date;
		this.berthPreference = berthPreference;
		this.foodPreference = foodPreference;
		this.price = price;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public int getCoachNumber() {
		return coachNumber;
	}

	public void setCoachNumber(int coachNumber) {
		this.coachNumber = coachNumber;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getPassangerName() {
		return passangerName;
	}

	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBerthPreference() {
		return berthPreference;
	}

	public void setBerthPreference(String berthPreference) {
		this.berthPreference = berthPreference;
	}

	public String getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(String foodPreference) {
		this.foodPreference = foodPreference;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BookingConfirmationDto [trainNumber=" + trainNumber + ", coachNumber=" + coachNumber + ", seatNumber="
				+ seatNumber + ", passangerName=" + passangerName + ", age=" + age + ", date=" + date
				+ ", berthPreference=" + berthPreference + ", foodPreference=" + foodPreference + ", price=" + price
				+ "]";
	}
	
	
	
	
}
