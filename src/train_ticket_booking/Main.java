package train_ticket_booking;

import static train_ticket_booking.util.ConsoleColors.BLUE_BOLD;
import static train_ticket_booking.util.ConsoleColors.DEFAULT;
import static train_ticket_booking.util.ConsoleColors.PURPLE_BOLD;
import static train_ticket_booking.util.ConsoleColors.RED_BOLD;
import static train_ticket_booking.util.ConsoleColors.RESET;
import static train_ticket_booking.util.ConsoleColors.YELLOW_BOLD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import train_ticket_booking.facade.AppFacade;
import train_ticket_booking.factory.AppContext;
import train_ticket_booking.model.BookingConfirmationDto;
import train_ticket_booking.model.BookingRequestDto;
import train_ticket_booking.model.Train;

public class Main {
	public static void main(String args[]) throws IOException {
			AppContext.initialize();

			AppFacade appFacade = AppContext.getObject(AppFacade.class);

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = "";
			String command = "";
			System.out.print(DEFAULT); // Console color

			showMenu();

			do {
				System.out.print(PURPLE_BOLD + "command " + YELLOW_BOLD+"$ ");
				input = br.readLine();
				System.out.print(DEFAULT);
				
				String[] inputTokens = sanitizeInputTokens(input); // Break the input string into individual strings so that the command and it's arguments can be used for processing.

				command = inputTokens[0];

				switch (command) {
				case "search_train_by_number": {
					if (inputTokens.length < 2) {
						System.out.println(
								RED_BOLD + "Incorrect command syntax: Please pass the name of the train number." + DEFAULT);
						showMenu();
					} else {
						String trainNumber = inputTokens[1];
						Train train = appFacade.searchTrainByTrainNumber(trainNumber);
						System.out.println(train);
					}
					break;
				}

				case "search_trains_by_route": {
					if(inputTokens.length<3) {
						System.out.println(
								RED_BOLD + "Incorrect command syntax: Please pass all arguments." + DEFAULT);
						showMenu();
					} else {
						String source = inputTokens[1];
						String destination = inputTokens[2];
						List<Train> trains = appFacade.searchTrainByRoute(source, destination);
						trains.forEach(System.out::println);
					}
					break;
				}

				case "check_train_for_seat": {
					if (inputTokens.length < 2) {
						System.out.println(
								RED_BOLD + "Incorrect command syntax: Please pass the name of the train number." + DEFAULT);
						showMenu();
					} else {
						String trainNumber = inputTokens[1];
						System.out.println(appFacade.isSeatAvailable(trainNumber));
					}
					break;
				}

				case "book_ticket": {
					if (inputTokens.length < 2) {
						System.out.println(
								RED_BOLD + "Incorrect command syntax: Please pass the name of the train number." + DEFAULT);
						showMenu();
					} else {
						String trainNumber = inputTokens[1];
						// take other required inputs: passanger name, age, travel date, berth preference, food preference
						String passangerName = "raju";
						int age = 20;
						String travelDate = "2024-11-22";
						String berthPreference = "LOWER";
						String foodPreference = "VEG";
						
						BookingRequestDto bookingRequestDto = new BookingRequestDto(trainNumber, passangerName, age, travelDate, berthPreference, foodPreference);
						
						// call the method
						BookingConfirmationDto booking = appFacade.bookTicket(bookingRequestDto);
						System.out.println(booking);
					}
					break;
				}

				case "get_coach_details": {
					if(inputTokens.length<3) {
						System.out.println(
								RED_BOLD + "Incorrect command syntax: Please pass all arguments." + DEFAULT);
						showMenu();
					} else {
						String trainNumber = inputTokens[1];
						int coachNumber = Integer.parseInt(inputTokens[2]);
						appFacade.getCoachDetails(trainNumber, coachNumber);
					}
					break;
				}

				case "get_booking_register": {
					Set<BookingConfirmationDto> bookings = appFacade.getBookingRegister();
					System.out.println(bookings);
					break;
				}

				case "exit": {
					System.out.println(BLUE_BOLD + "Okay, Bye!" + RESET);
					break;
				}

				case "menu": {
					showMenu();
					break;
				}

				default:
					System.out.println(RED_BOLD + "Invalid command: " + command + DEFAULT);
					showMenu();
				
				}

			} while (!command.equalsIgnoreCase("exit"));
			
			System.out.print(RESET);
		}
		
		private static void showMenu() {
			System.out.println("|===========================================================================================|");
			System.out.println("| Enter your command. PLEASE PASS ARGUMENTS IN DOUBLE QUOTES.                               |");
			System.out.println("| 1. Search Train By Number: "+YELLOW_BOLD+"search_train_by_number <trainNumber>"+DEFAULT+"                           |");
			System.out.println("| 2. Search Trains By Route: "+YELLOW_BOLD+"search_trains_by_route <source> <destiation>"+DEFAULT+"                   |");
			System.out.println("| 3. To check if seat is available: "+YELLOW_BOLD+"check_train_for_seat <trainNumber>"+DEFAULT+"                      |");
			System.out.println("| 4. To Book Ticket: "+YELLOW_BOLD+"book_ticket <trainNumber>"+DEFAULT+"                                              |");
			System.out.println("| 5. To Check Coach Details: "+YELLOW_BOLD+"get_coach_details <trainNumber> <coachNumber>"+DEFAULT+"                  |");
			System.out.println("| 6. To Get Bookings Register: "+YELLOW_BOLD+"get_booking_register"+DEFAULT+"                                         |");
			System.out.println("| 7. To Exit: "+YELLOW_BOLD+"exit"+DEFAULT+"                                                                          |");
			System.out.println("| 8. To get this menu: "+YELLOW_BOLD+"menu"+DEFAULT+"                                                                 |");
			System.out.println("|===========================================================================================|");
		}
		
		/**
		 * 
		 * @param input Command input string from user.
		 * @return An array of strings extracted from the input string.
		 */
		private static String[] sanitizeInputTokens(String input) {
			
			input = input.trim().toLowerCase();
			String[] inputTokens = input.split("\"");
			
			List<String> updatedTokens = new ArrayList<>();
			
			// the first word is command so no need to do anything on that. The arguments are inside double quotes so we need to remove that.
			updatedTokens.add(inputTokens[0].trim());
			
			if(inputTokens.length>1) {
				for(int i=1; i<inputTokens.length;i++) {
					String token = inputTokens[i].trim();
					if(token.length()==0) continue;
					updatedTokens.add(token);
				}
			}
			
			return updatedTokens.toArray(new String[0]);
		}
		
}
