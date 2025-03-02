import java.util.*;

class Car1{
	private String carId, brand, model;
	private double basePricePerDay;
	private	boolean isAvailable;
	public Car1(String carId, String brand, String model, double basePricePerDay) {
		this.carId = carId;
		this.brand = brand;
		this.model =model;
		this.basePricePerDay = basePricePerDay;
		this.isAvailable = true;
	}
	public String getCarId() {
		return carId;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public double calculatePrice(int rentalDays) {
		return basePricePerDay * rentalDays;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void rent() {
		isAvailable = false;
	}
	public void returnCar() {
		isAvailable = true;
	}
}

class Customer{
	private String name, customerId;
	public Customer(String name, String customerId) {
		this.customerId = customerId;
		this.name = name;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getName() {
		return name;
	}
}

class Rental{
	private Car1 car;
	private Customer customer;
	private int days;
	
	public Rental(Car1 car, Customer customer, int days) {
		this.car = car;
		this.customer = customer;
		this.days = days;
	}
	public Car1 getCar() {
		return car;
	}
	public Customer getCustomer() {
		return customer;
	}
	public int getDays() {
		return days;
	}
}

class CarRentalSystem{
	private List<Car1> cars;
	private List<Customer> customers;
	private List<Rental> rentals;
	
	public CarRentalSystem() {
		cars = new ArrayList<>();
		customers = new ArrayList<>();
		rentals = new ArrayList<>();
	}
	public void addCar(Car1 car) {
		cars.add(car);
	}
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	public void rentCar(Car1 car, Customer customer, int days) {
		if (car.isAvailable()) {
			car.rent();
			rentals.add(new Rental(car,customer,days));
		}
		else System.out.println("Car is not available for rent!!!");
	}
	public void returnCar(Car1 car) {
		car.returnCar();
		Rental rentalToRemove = null;
		for(Rental rental:rentals) {
			if(rental.getCar()==car) {
				rentalToRemove = rental;
				break;
			}
		}
		if(rentalToRemove != null) {
			rentals.remove(rentalToRemove);
			System.out.println("Car returned successfully!");
		}
		else System.out.println("Car was not rented");
	}
	public void menu() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("=== Car Rental System ===");
			System.out.println("1. Rent a Car ");
			System.out.println("2. Return a Car ");
			System.out.println("3. Exit ");
			System.out.println("   Enter your Choice ");
			
			int ch = scan.nextInt();
			scan.nextLine();
			
			if(ch == 1) {
				System.out.println("Rent a Car ");
				System.out.println("Enter your Name: ");
				String customerName = scan.nextLine();
				System.out.println("Available Cars: ");
				for(Car1 car: cars) {
					if(car.isAvailable()) {
						System.out.println(car.getCarId()+" - "+car.getBrand()+" "+car.getModel());
					}
				}
				System.out.println("Enter the Car ID you want to rent: ");
				String carId = scan.nextLine();
				
				System.out.println("Enter the number of days for rental: ");
				int rentalDays = scan.nextInt();
				scan.nextLine();
				
				Customer newCustomer = new Customer(customerName, "CUS"+(customers.size()+1));
				addCustomer(newCustomer);
				
				Car1 selectedCar = null;
				for(Car1 car: cars) {
					if(car.getCarId().equals(carId) && car.isAvailable()) {
						selectedCar = car;
						break;
					}
				}
				if(selectedCar!=null) {
					double totalPrice = selectedCar.calculatePrice(rentalDays);
					System.out.println("Rental Information");
					System.out.println("Customer Id: "+newCustomer.getCustomerId());
					System.out.println("Customer Name: "+newCustomer.getName());
					System.out.println("Car: "+selectedCar.getBrand()+" "+selectedCar.getModel());
					System.out.println("Rental Days: "+rentalDays);
					System.out.printf("Total Price: $%.2f%n",totalPrice);
					
					System.out.println("Confirm Rental (Y/N): ");
					String confirm = scan.nextLine();
					
					if(confirm.equalsIgnoreCase("Y")) {
						rentCar(selectedCar, newCustomer, rentalDays);
						System.out.println("Car Rented Successfully! ");
					}
					else System.out.println("Rental cancelled!!!");
				}
				else System.out.println("Invalid Car Selection or Car not available for rent!");
			}
			else if(ch==2) {
				System.out.println("Return a Car");
				System.out.println("Enter the Car Id you want to return: ");
				String carId = scan.nextLine();
				
				Car1 carToReturn = null;
				for(Car1 car:cars) {
					if(car.getCarId().equals(carId) && !car.isAvailable()) {
						carToReturn = car;
						break;
					}
				}
				if(carToReturn != null) {
					Customer customer = null;
					for(Rental rental:rentals) {
						if(rental.getCar() == carToReturn) {
							customer = rental.getCustomer();
							break;
						}
					}
					if(customer!=null) {
						returnCar(carToReturn);
						System.out.println("Car returned successfully by "+customer.getName());
					}
					else System.out.println("Car was not returned or some information is missing!!");
				}
				else System.out.println("Invalid Car Id or car is not rented.");
			}
			else if(ch==3) break;
			else System.out.println("Invalid Choice");
		}
		System.out.println("Thank You for choosing Car Rental System!!!");
	}
}

public class Main {

	public static void main(String[] args) {
		CarRentalSystem crs = new CarRentalSystem();
		
		Car1 car1 = new Car1("C001","Toyota","Camry",60.0);
		Car1 car2 = new Car1("C002","Honda","Accord",75.5);
		Car1 car3 = new Car1("C003","Mahindra","Thar",150.0);
		
		crs.addCar(car1);
		crs.addCar(car2);
		crs.addCar(car3);
		
		crs.menu();
	}
}
