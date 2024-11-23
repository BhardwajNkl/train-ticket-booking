package train_ticket_booking.model;

public class BookingRequestDto {
	private String trainNumber;
	private String passangerName;
	private int age;
	private String date;
	private BerthPreference berthPreference;
	private FoodPreference foodPreference;
	public BookingRequestDto(String trainNumber, String passangerName, int age, String date, String berthPreference,
			String foodPreference) {
		super();
		this.trainNumber = trainNumber;
		this.passangerName = passangerName;
		this.age = age;
		this.date = date;
		this.berthPreference = BerthPreference.valueOf(berthPreference);
		this.foodPreference = FoodPreference.valueOf(foodPreference);
	}
	public String getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
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
	public BerthPreference getBerthPreference() {
		return berthPreference;
	}
	public void setBerthPreference(String berthPreference) {
		this.berthPreference = BerthPreference.valueOf(berthPreference);
	}
	public FoodPreference getFoodPreference() {
		return foodPreference;
	}
	public void setFoodPreference(String foodPreference) {
		this.foodPreference = FoodPreference.valueOf(foodPreference);
	}
	
}
