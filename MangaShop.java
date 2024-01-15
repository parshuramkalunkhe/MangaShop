import java.util.Date;
import java.util.Scanner;

// Collections
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

// Exception
import java.util.InputMismatchException;

//============================================================//
//============================================================//
//this all is user creation and login and signup related stuff.
class User {
	private String name;
	private long contact;
	private String email;
	private String password;

	public User(String name, long contact, String email, String password) {
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public long getContact() {
		return contact;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}

interface Authentication {
	void login(Scanner scr) throws InvalidCredintialException;

	void signup(Scanner scr) throws InvalidCredintialException;
}

class AuthProcess implements Authentication {
	private static List<User> userCollection = new ArrayList<User>();
	private static Map<Long, String> userCredentials = new HashMap<Long, String>();

	public void login(Scanner scr) throws InvalidCredintialException {
		System.out.println("| ** ============================================================ ** |");
		System.out.println("| <<                       Login Process                          >> |");
		System.out.println("| ** ============================================================ ** |");

		try {
			long contact = userContact(scr);
			String password;

			if (userCredentials.containsKey(contact)) {
				System.out.print("  >> Enter password : ");
				password = scr.next();

				if (userCredentials.get(contact).equals(password)) {
					System.out.println("| ** ============================================================ ** |");
					System.out.println("| <<                    Logged In Successfully                    >> |");
					System.out.println("| ** ============================================================ ** |");
					HomePage.menu();
				} else {
					throw new InvalidCredintialException("Invalid Credintial.");
				}
			} else {
				throw new InvalidCredintialException("User already exists.");
			}
		} catch (InvalidCredintialException e) {
			System.out.println("  !! " + e);
		} catch (Exception e) {
			System.out.println("  !! User is not exits");
		}

	}

	public void signup(Scanner scr) throws InvalidCredintialException {
		System.out.println("| ** ============================================================ ** |");
		System.out.println("| <<                       Signup Process                         >> |");
		System.out.println("| ** ============================================================ ** |");

		String name = userName(scr);

		long contact = userContact(scr);

		String email = userEmail(scr);

		String password;

		try {
			if (userCredentials.containsKey(contact)) {
				throw new InvalidCredintialException("User already exists. Please choose a different one.");
			} else {
				password = userPassword(scr);

				userCredentials.put(contact, password);
				User user = new User(name, contact, email, password);
				userCollection.add(user);
				System.out.println("| ** ============================================================ ** |");
				System.out.println("| <<                       Signup Successfully                    >> |");
				System.out.println("| ** ============================================================ ** |");

			}
		} catch (InvalidCredintialException e) {
			System.out.println("  !! " + e);
		} catch (Exception e) {
			System.out.println("  !! " + e);
		}
		HomePage.menu();
	}

	private String userName(Scanner scr) {
		System.out.print("  >> Enter your name : ");
		String name = scr.next();

		if (name.isBlank()) {
			userName(scr);
		}

		if (!(name.length() >= 3)) {
			System.out.println("  !! UserName must be three characters.");
			userName(scr);
		} else if (name.charAt(0) >= '0' && name.charAt(0) <= '9') {
			System.out.println("  !! UserName must be start with a letter.");
			userName(scr);
		}

		return name;
	}

	private long userContact(Scanner scr) {
		System.out.print("  >> Enter contact no : ");
		long contact = scr.nextLong();

		try {
			if (!(contact > 999999999L && contact < 100000000000L)) {
				System.out.println("  !! Please enter 10 digits");
				userContact(scr);
			}
		} catch (InputMismatchException e) {
			System.out.println("  !! Enter Number Only.");
			userContact(scr);
		} catch (Exception e) {
			System.out.println("  !! Unknown Error.");
			userContact(scr);
		}
		return contact;
	}

	private String userEmail(Scanner scr) {
		System.out.print("  >> Enter your email : ");
		String email = scr.next();

		if (email.isBlank()) {
			userEmail(scr);
		}
		for (User user : userCollection) {
			if (user.getEmail().equals(email)) {
				System.out.println("  !! email already exists.");
				userEmail(scr);
			}
		}

		boolean isSymbol = false, isDOT = false;

		for (int i = 0; i < email.length(); i++) {
			char ch = email.charAt(i);
			if (ch == '@') {
				isSymbol = true;
			} else if (ch == '.') {
				isDOT = true;
			}
		}

		if (!(isSymbol || isDOT)) {
			System.out.println("  !! Invalid email.");
			userEmail(scr);
		}

		return email;
	}

	private String userPassword(Scanner scr) {

		System.out.print("  >> Enter your password : ");
		String password = scr.next();

		if (password.isBlank()) {
			userPassword(scr);
		}

		boolean isUppercase = false, isLowercase = false, isNumeric = false, isSpecial = false;

		for (int i = 0; i < password.length(); i++) {
			char ch = password.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				isUppercase = true;
			} else if (ch >= 'a' && ch <= 'z') {
				isLowercase = true;
			} else if (ch >= '0' && ch <= '9') {
				isNumeric = true;
			} else if (ch == ' ') {
				System.out.println(" !! Space is Not allowed.");
				userPassword(scr);
			} else {
				isSpecial = true;
			}
		}

		if (!(password.length() > 8 && isUppercase && isLowercase && isNumeric && isSpecial)) {
			System.out.println("  !! Weak Password.");
			userPassword(scr);
		}

		return password;
	}

	public void allUsersMap() {
		for (Long key : userCredentials.keySet()) {
			System.out.println("  -+| Contact : " + key + ", Password : " + userCredentials.get(key) + " |+- ");
		}
	}

	public void allUserCollection() {
		for (User user : userCollection) {
			System.out.println("  -+| " + user.getName() + " : " + user.getContact() + " : " + user.getEmail() + " : "
					+ user.getPassword() + " |+- ");
		}
	}

}
//User related stuff ended here.
//============================================================//
//============================================================//

// Custom exception for handling invalid credentials.
//============================================================//
//============================================================//

@SuppressWarnings("serial")
class InvalidCredintialException extends Exception {
	/**
	 * Constructs an InvalidCredentialException with the specified error message.
	 *
	 * @param errorMessage The descriptive error message.
	 */
	public InvalidCredintialException(String errorMessage) {
		super(errorMessage);
	}
}

/**
 * Custom exception for handling invalid input.
 */
@SuppressWarnings("serial")
class InvalidInputException extends InputMismatchException {
	/**
	 * Constructs an InvalidInputException with the specified error message.
	 *
	 * @param errorMessage The descriptive error message.
	 */
	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}
}

/**
 * Custom exception for handling payment failures.
 */
@SuppressWarnings("serial")
class PaymentFailedException extends Exception {
	/**
	 * Constructs a PaymentFailedException with the specified error message.
	 *
	 * @param errorMessage The descriptive error message.
	 */
	public PaymentFailedException(String errorMessage) {
		super(errorMessage);
	}
}
// Custom Exceptions end here.
//============================================================//
//============================================================//

//============================================================//
//============================================================//
//Billing System
interface BillingSystem {
	void totalBill(Object obj, Scanner scr);

	void payment(Object obj, String pay, Scanner scr);

	void payment(Object obj, double cash, Scanner scr);
}

class PaymentSystem implements BillingSystem {

	static private LinkedList<MangaObject> mangaCollection = new LinkedList<MangaObject>();
//	static private Map<Integer, String> yourOrders = new HashMap<Integer, String>();
	User user;

	static double bill;

	@Override
	public void totalBill(Object obj, Scanner scr) {
		MangaObject manga = (MangaObject) obj;
		String paymentId;
		// ** TypeCasting : Narrowing ( double >> int ).
		int discount = (int) (bill * 0.1);

		System.out.println("  >> Your total bill amount is : " + bill);

		bill = bill - discount;
		System.out.println("  >> After discount the bill is : " + bill);

		try {
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.println("                       ** Payment Process  **                         ");
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  >> Press 1 for UPI.");
			System.out.println("  >> Press 2 for Paypal.");
			System.out.println("  >> Press 3 for Cash.");
			System.out.println("| ** ============================================================ ** |");

			System.out.print("  >> Enter your choice : ");
			int choice = scr.nextInt();

			System.out.println("| ** ============================================================ ** |");

			switch (choice) {
			case 1:
				System.out.print("  >> Enter the UPI Id : ");
				paymentId = scr.next();
				payment(manga, paymentId, scr);
				break;
			case 2:
				System.out.print("  >> Enter the Paypal Id : ");
				paymentId = scr.next();
				payment(manga, paymentId, scr);
				break;
			case 3:
				payment(manga, bill, scr);
				break;
			default:
				bill = bill + discount;
				totalBill(manga, scr);
				break;
			}
			System.exit(0);
		} catch (InvalidInputException e) {
			System.out.println("  !! Invalid userInput.");
			totalBill(manga, scr);
		} catch (Exception e) {
			System.out.println("  !! There is someting wrong Please try again. !!  ");
			totalBill(manga, scr);
		}
	}

	public void payment(Object obj, String paymentId, Scanner scr) {

		try {
			System.out.print("  >> Enter the amount : ");
			double amount = scr.nextDouble();

			System.out.println("  >> Thank you for paying : " + amount + "  ₹ /-");
			System.out.println("| ** ============================================================ ** |");
			mangaCollection.add((MangaObject) obj);
			System.out.println("  >> Would you like to continue purchasing more mangas.");
			System.out.print("  >> Press Y or y for yes and N or n for No : ");

			char ch = scr.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				HomePage.menu();
			} else if (ch == 'n' || ch == 'N') {
				System.exit(0);
			} else {
				throw new PaymentFailedException("  !! Payment Failed.");
			}
		} catch (InvalidInputException e) {
			payment(obj, paymentId, scr);
		} catch (PaymentFailedException e) {
			System.out.println("  !! Unsuccessful : " + e);
			totalBill(obj, scr);
		} catch (Exception e) {
			System.out.println("  !! There is something wrong.");
			totalBill(obj, scr);
		}

	}

	public void payment(Object obj, double cash, Scanner scr) {

		System.out.println("  >> Thank you for paying : " + cash);
		System.out.println("| ** ============================================================ ** |");
		mangaCollection.add((MangaObject) obj);
		System.out.println("  >> Would you like to continue purchasing more manga, manhwa, and merchandise.");
		System.out.print("  >> Press Y or y for yes and N or n for No : ");
		try {
			char ch = scr.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				HomePage.menu();
			} else if (ch == 'n' || ch == 'N') {
				MangaShop.exit();
			} else {
				throw new PaymentFailedException("  >> You have selected wrong option.");
			}
		} catch (PaymentFailedException e) {
			System.out.println("Error : " + e);
			payment(obj, cash, scr);
		}
	}

	public static void yourOrders(Scanner scr) {
		System.out.println("  !! ## ** << Your Orders >> ** ## !! ");
		if (mangaCollection.isEmpty()) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  << No Orders Placed Yet. >>");
			System.out.println("| ** ============================================================ ** |");
			HomePage.menu();
		}
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  << Your Orders. >>");
		System.out.println("| ** ============================================================ ** |");

		for (MangaObject manga : mangaCollection) {
			System.out.println("  -+| " + manga.getId() + " : " + manga.getTitle() + " |+- ");
		}

		System.out.println("  >> Would you like to continue purchasing more manga, manhwa, and merchandise.");
		System.out.print("  >> Press Y or y for yes and N or n for No : ");
		try {
			char ch = scr.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				HomePage.menu();
			} else if (ch == 'n' || ch == 'N') {
				MangaShop.exit();
			} else {
				throw new PaymentFailedException("  >> You have selected wrong option.");
			}
		} catch (Exception e) {
			System.out.println("Error : " + e);
			yourOrders(new Scanner(System.in));
		}

	}
}

//============================================================//
//============================================================//

// All MangaShop here
//============================================================//
//============================================================//

abstract class Manga {
	// Manga information
	private int Id;
	private String title;
	private int volumes;
	private String description;
	private String status;
	private Date date;
	private String author;
	private String genre;
	private String type;
	private double price;

	public Manga(int Id, String title, int volumes, String description, String status, Date date, String author,
			String genre, String type, double price) {
		this.Id = Id;
		this.title = title;
		this.volumes = volumes;
		this.description = description;
		this.status = status;
		this.date = date;
		this.author = author;
		this.genre = genre;
		this.type = type;
		this.price = price;
	}

	public Manga() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public String getTitle() {
		return title;
	}

	public int getVolumes() {
		return volumes;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public Date getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public String getType() {
		return type;
	}

	abstract void mangaBill(Object obj, Scanner scr);
}

class MangaObject extends Manga {

	private static List<MangaObject> mangaList = new LinkedList<MangaObject>();

	public MangaObject(int Id, String title, int volumes, String description, String status, Date date, String author,
			String genre, String type, double price) {
		super(Id, title, volumes, description, status, date, author, genre, type, price);
	}

	public MangaObject() {
	}

	public void mangaBill(Object obj, Scanner scr) {
		MangaObject manga = (MangaObject) obj;
		PaymentSystem paymentSystem = new PaymentSystem();
		try {
			System.out.print("  >> Enter your choice : ");
			int choice = scr.nextInt();
			System.out.println("| ** ============================================================ ** |");

			if (choice == 1) {
				PaymentSystem.bill = PaymentSystem.bill + manga.getPrice();
				paymentSystem.totalBill(manga, scr);
			} else if (choice == 99) {
				HomePage.menu();
			} else {
				throw new InvalidInputException("  >> You have selected wrong option.");
			}

			System.out.println("  >> Do you want to choose another Manga?");
			System.out.print("  >> If yes then press Y and for No press N : ");

			char ch = scr.next().charAt(0);

			if (ch == 'y' || ch == 'Y') {
				HomePage.menu();
			} else if (ch == 'n' || ch == 'N') {
				MangaShop.exit();
			} else {
				throw new InvalidInputException("  >> You have selected wrong option.");
			}
		} catch (InvalidInputException e) {
			System.out.println("Error : " + e);
			mangaBill(manga, scr);
		} catch (Exception e) {
			mangaBill(manga, scr);
		}
	}

	@SuppressWarnings("resource")
	public void mangaObject(Object obj) {
		MangaObject manga = (MangaObject) obj;
		Scanner scr = new Scanner(System.in);

		System.out.println("| ** ============================================================ ** |");
		System.out.println("                       ** Order Process **                        ");
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + manga.getTitle());
		System.out.println("  >> Volumes : " + manga.getVolumes());
		System.out.println("  >> Pirce : " + manga.getPrice() + " ₹ /-");
		System.out.println("  >> Press 1 for purchase.");
		System.out.println("  >> Press 2 for add into cart.");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 9 for Your Cart.");
		System.out.println("  >> Press 99 for main menu.");
		System.out.println("| ** ============================================================ ** |");

		try {

			System.out.print("  >> Enter your choice : ");
			int choice = scr.nextInt();
			System.out.println("| ** ============================================================ ** |");

			if (1 == choice) {
				System.out.println("| ** ============================================================ ** |");
				System.out.println("                        ** Billing Process **                         ");
				System.out.println("| ** ============================================================ ** |");
				System.out.println("  >> You have selected " + manga.getTitle() + " Manga.");
				System.out.println("  >> The price is " + manga.getPrice() + "  ₹ /-");
				System.out.println("  >> Press 0 to exit.");
				System.out.println("  >> Press 1 to place order.");
				System.out.println("  >> Press 99 to go back.");
				System.out.println();
				System.out.println("| ** ============================================================ ** |");
				mangaBill(manga, scr);
			} else if (2 == choice) {
				if (mangaList.contains(manga)) {
					System.out.println("  !! Manga is alreay added to your cart.");
					mangaObject(manga);
				}
				mangaList.add(manga);
				System.out.println("  >> Added to cart: " + manga.getTitle());
				mangaObject(manga);
			} else if (0 == choice) {
				MangaShop.exit();
			} else if (9 == choice) {
				cart(scr);
			} else if (99 == choice) {
				HomePage.menu();
			} else {
				throw new InvalidInputException("Please enter the correct input.");
			}
		} catch (InvalidInputException e) {
			System.out.println("  !! Error : " + e);
			mangaObject(manga);
		}
	}

	public static void cart(Scanner scr) {
		System.out.println("  -- ++ << !! ## Your Cart ## !! >> ++ --");

		if (mangaList.isEmpty()) {
			System.out.println("  !! Your Cart is empty.");
			
		}
		for (Manga manga : mangaList) {
			System.out.println("  -+| " + manga.getId() + ". " + manga.getTitle() + " |+- ");
		}
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> Press 0 to exit.");
		System.out.println("  >> Press 1 to place order.");
		System.out.println("  >> Press 2 for clear cart.");
		System.out.println("  >> Press 99 to go Main menu.");
		try {
			System.out.println("| ** ============================================================ ** |");
			System.out.print("  >> Enter Your Choice : ");
			int choice = scr.nextInt();
			System.out.println("| ** ============================================================ ** |");
			if (choice == 0) {
				MangaShop.exit();
			} else if (1 == choice) {
				checkOut(scr); // Process the checkout
			} else if (2 == choice) {
				mangaList.clear();
				System.out.println("  >> Cart cleared.");
				HomePage.menu();
			} else if (99 == choice) {
				HomePage.menu();
			}
		} catch (Exception e) {
			cart(new Scanner(System.in));
		}
	}

	public static void checkOut(Scanner scr) {
		// Perform checkout logic here
		if (mangaList.isEmpty()) {
			System.out.println("  !! Your cart is empty. Cannot proceed to checkout.");
		} else {
			// Calculate the total price and perform any necessary payment-related actions
			double totalPrice = 0;
			for (Manga manga : mangaList) {
				totalPrice += manga.getPrice();
			}

			System.out.println("  >> Total price for your items: " + totalPrice + " ₹ /- ");
			// Perform any further actions related to finalizing the purchase (e.g., payment
			// gateway integration)

			// After successful checkout, clear the cart
			mangaList.clear();
			System.out.println("  >> Thank you for your purchase!");
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  >> Press 0 to exit.");
			System.out.println("  >> Press 99 to go Main menu.");
			try {
				System.out.println("| ** ============================================================ ** |");
				System.out.print("  >> Enter Your Choice : ");
				int choice = scr.nextInt();
				System.out.println("| ** ============================================================ ** |");
				if (choice == 0) {
					MangaShop.exit();
				} else if (99 == choice) {
					HomePage.menu();
				}
			} catch (Exception e) {
				checkOut(new Scanner(System.in));
			}
		}
	}
}
// Manga Endes here
//============================================================//
//============================================================//

//============================================================//
//============================================================//
//HomePage

class HomePage {

	private static Map<Integer, Manga> mangaCollection = new HashMap<Integer, Manga>();

	static Scanner scr = new Scanner(System.in);

	@SuppressWarnings("deprecation")
	public static void menu() {

		System.out.println("| ** ============================================================ ** |");
		System.out.println("                       ** Welcome to MangaShop **                         ");
		System.out.println("| ** ============================================================ ** |");

		// kodomomuke
		Manga doraemon = new MangaObject(1, "Doraemon", 45,
				"Doraemon is cat shaped robot from the future traveling all the way back to the 20th century in order to help Nobi Nobita, a lazy kid with nothing really going for him. Nobi is bad at sports, his grades are terrible, he can't even win a match of rock-paper-scissors. Doraemon has a hard task ahead of him but he is well prepared with a huge arsenal of the most inventive and funny gadgets available. The problem is he's not the most competent robot cat helper out there either and the pair gets in constant trouble because of it. Thus begins one of the most interesting friendships ever to appear in a manga series.",
				"Finished", new Date(1969, 12, 1, 0, 0), "Fujiko F. Fujio",
				"Adventure, Award Winning, Comedy, Sci-Fi, Slice of Life", "kodomomuke", 7412.07);

		Manga pokémonAdventures = new MangaObject(2, "Pokémon Adventures", 14,
				"Red is just a normal kid living in the rural Pallet Town, when he decides to go out on his own adventure, along with his rival, Green. In this world of Pokémon, he makes many friends, humans and Pokémon alike. However, all is not well. Team Rocket is trying to capture Mew, a very rare Pokémon, and is performing experiments on other Pokémon, trying to increase their power. Red and his friends must battle against Team Rocket to stop their cruel experiments and unlock the secrets of Pokémon.",
				"Finished", new Date(1996, 11, 28, 0, 0), "Hidenori Kusaka", "Action, Adventure", "kodomomuke",
				3498.26);

		Manga megamanGigamix = new MangaObject(3, "Megaman Gigamix", 3,
				"Mega Man Gigamix is a manga drawn by Hitoshi Ariga and connected with Mega Man Megamix, being that some chapters are prequels and others are direct follow-ups. The manga was published by BN in three volumes between 2009-2010 in Japan and by Udon Entertainment between 2011-2012 in the United States.",
				"Finished", new Date(2009, 11, 20, 0, 0), "Ariga, Hitoshi", "Action, Adventure, Sci-Fi", "kodomomuke",
				999.50);

		Manga astroBoy = new MangaObject(4, "Astro Boy", 3, "A more modern version of Tetsuwan Atom.", "Finished",
				new Date(2003, 4, 10, 0, 0), "Tezuka, Osamu", "Action, Adventure, Sci-Fi", "kodomomuke", 1999.01);

		Manga metalFightBeyblade = new MangaObject(5, "Metal Fight Beyblade", 11,
				"No synopsis information has been added to this title. Help improve our database by adding a synopsis here.",
				"Finished", new Date(2008, 9, 13, 0, 0), "Adachi, Takafumi", "Adventure, Sports", "kodomomuke",
				3165.10);

		// shonen
		Manga naruto = new MangaObject(1, "Naruto", 72,
				"Whenever Naruto Uzumaki proclaims that he will someday become the Hokage—a title bestowed upon the best ninja in the Village Hidden in the Leaves—no one takes him seriously. Since birth, Naruto has been shunned and ridiculed by his fellow villagers. But their contempt isn't because Naruto is loud-mouthed, mischievous, or because of his ineptitude in the ninja arts, but because there is a demon inside him. Prior to Naruto's birth, the powerful and deadly Nine-Tailed Fox attacked the village. In order to stop the rampage, the Fourth Hokage sacrificed his life to seal the demon inside the body of the newborn Naruto.And so when he is assigned to Team 7—along with his new teammates Sasuke Uchiha and Sakura Haruno, under the mentorship of veteran ninja Kakashi Hatake—Naruto is forced to work together with other people for the first time in his life. Through undergoing vigorous training and taking on challenging missions, Naruto must learn what it means to work in a team and carve his own route toward becoming a full-fledged ninja recognized by his village.",
				"Finished", new Date(1999, 9, 21, 0, 0), "Masashi Kishimoto", "Action, Adventure, Fantasy", "shonen",
				10078.20);

		Manga bleach = new MangaObject(2, "Bleach", 74,
				"For as long as he can remember, high school student Ichigo Kurosaki has been able to see the spirits of the dead, but that has not stopped him from leading an ordinary life. One day, Ichigo returns home to find an intruder in his room who introduces herself as Rukia Kuchiki, a Soul Reaper tasked with helping souls pass over. Suddenly, the two are jolted from their conversation when a Hollow—an evil spirit known for consuming souls—attacks. As Ichigo makes a brash attempt to stop the Hollow, Rukia steps in and shields him from a counterattack. Injured and unable to keep fighting, Rukia suggests a risky plan—transfer half of her Soul Reaper powers to Ichigo. He accepts and, to Rukia's surprise, ends up absorbing her powers entirely, allowing him to easily dispatch the Hollow. Now a Soul Reaper himself, Ichigo must take up Rukia's duties of exterminating Hollows and protecting spirits, both living and dead. Along with his friends Orihime Inoue and Yasutora Sado—who later discover spiritual abilities of their own—Ichigo soon learns that the consequences of becoming a Soul Reaper and dealing with the world of spirits are far greater than he ever imagined.",
				"Finished", new Date(2001, 8, 7, 0, 0), "Kubo, Tite", "Action, Adventure, Award Winning, Fantasy",
				"shonen", 9828.39);

		Manga onepiece = new MangaObject(3, "onepiece", 106,
				"Gol D. Roger, a man referred to as the \"King of the Pirates,\" is set to be executed by the World Government. But just before his demise, he confirms the existence of a great treasure, One Piece, located somewhere within the vast ocean known as the Grand Line. Announcing that One Piece can be claimed by anyone worthy enough to reach it, the King of the Pirates is executed and the Great Age of Pirates begins. Twenty-two years later, a young man by the name of Monkey D. Luffy is ready to embark on his own adventure, searching for One Piece and striving to become the new King of the Pirates. Armed with just a straw hat, a small boat, and an elastic body, he sets out on a fantastic journey to gather his own crew and a worthy ship that will take them across the Grand Line to claim the greatest status on the high seas.",
				"Publishing", new Date(1997, 7, 22, 0, 0), "Oda, Eiichiro", "Action, Adventure, Fantasy", "shonen",
				11660.80);

		Manga chainsawMan = new MangaObject(4, "chainsawMan", 15,
				"Denji has a simple dream—to live a happy and peaceful life, spending time with a girl he likes. This is a far cry from reality, however, as Denji is forced by the yakuza into killing devils in order to pay off his crushing debts. Using his pet devil Pochita as a weapon, he is ready to do anything for a bit of cash. Unfortunately, he has outlived his usefulness and is murdered by a devil in contract with the yakuza. However, in an unexpected turn of events, Pochita merges with Denji's dead body and grants him the powers of a chainsaw devil. Now able to transform parts of his body into chainsaws, a revived Denji uses his new abilities to quickly and brutally dispatch his enemies. Catching the eye of the official devil hunters who arrive at the scene, he is offered work at the Public Safety Bureau as one of them. Now with the means to face even the toughest of enemies, Denji will stop at nothing to achieve his simple teenage dreams.",
				"Publishing", new Date(2018, 12, 3, 0, 0), "Fujimoto, Tatsuki", "Action, Award Winning, Supernatural",
				"shonen", 8329.15);

		Manga demonSlayer = new MangaObject(5, "demonSlayer", 23,
				"Tanjirou Kamado lives with his impoverished family on a remote mountain. As the oldest sibling, he took upon the responsibility of ensuring his family's livelihood after the death of his father. On a cold winter day, he goes down to the local village in order to sell some charcoal. As dusk falls, he is forced to spend the night in the house of a curious man who cautions him of strange creatures that roam the night: malevolent demons who crave human flesh. When he finally makes his way home, Tanjirou's worst nightmare comes true. His entire family has been brutally slaughtered with the sole exception of his sister Nezuko, who has turned into a flesh-eating demon. Engulfed in hatred and despair, Tanjirou desperately tries to stop Nezuko from attacking other people, setting out on a journey to avenge his family and find a way to turn his beloved sister back into a human.",
				"Finished", new Date(2016, 2, 15, 0, 0), "Gotouge, Koyoharu", "Action, Fantasy", "shonen", 9162.06);

		// shojo
		Manga aoHaruRide = new MangaObject(1, "Ao Haru Ride", 13,
				"While most girls desire popularity among boys, Futaba Yoshioka wants the exact opposite. After attracting many admirers back in middle school which resulted in her being shunned by her female classmates, she decided that high school will be her chance to revamp her image. Therefore, she starts acting unfeminine and indifferent to boys, allowing her to make some friends along the way. Little does Futaba know, her life will take another drastic turn when her first love, Kou Mabuchi, returns after his sudden disappearance years ago in middle school. Despite his extended absence, the fond memories they shared together still linger in her mind, and she wishes to return to those days. But she realizes that the sweet, gentle boy of the past has completely vanished, and in his place stands someone cold and pessimistic. While he admits that her feelings for him back then were mutual, he warns that they can never go back to the past, as everything, including him, has changed. Ao Haru Ride follows Futaba as she searches for true love and friendship, all while trying her best to stay true to herself.",
				"Finished", new Date(2011, 1, 13, 0, 0), "Sakisaka, Io", "Romance", "shojo", 6000);

		Manga lastGame = new MangaObject(2, "Last Game", 11,
				"Nothing is beyond Naoto Yanagi, heir to the Yanagi business conglomerate. Idolized for his athletic and intellectual competence, looks and wealth, Naoto lived like a king during his elementary school days—then entered Mikoto Kujou, a plain, gloomy-looking transfer student.  Due to her low financial status, Naoto was initially apathetic towards Mikoto. But despite having just arrived at his school, she completely eclipsed him in everything by consistently scoring top marks in exams and placing first in athletic events. After a brief confrontation with her that left him shocked, Naoto vowed to outdo her no matter the cost.  Ten years later, they are now students attending the same college. Having failed to defeat Mikoto throughout middle and high school, Naoto decides they will have one last game: if he can make Mikoto fall in love with him and then break her heart, it will be his victory. However, he finds himself falling in love with her instead…",
				"Finished", new Date(2011, 8, 24, 0, 0), "Amano, Shinobu", "Comedy, Romance", "shojo", 7700);

		Manga orange = new MangaObject(3, "Orange", 7,
				"One morning, Naho Takamiya receives a letter in the mail claiming to be from herself 10 years in the future. The letter reveals a series of events that are supposed to take place that day. At first, Naho thinks it's just a prank; but when the letter mentions a transfer student named Kakeru Naruse, who really ends up transferring into her class later that day, she is forced to believe in its contents.  As Naho continues to read the letter, her future self mentions several regrets, urging Naho to take the right decisions now. Somehow these regrets all seem to be connected to Kakeru, and with the burden of the knowledge that the boy wouldn't be with her and her friends in the future, will Naho be able to make the perfect choices that will alter what seems to be fate?",
				"Finished", new Date(2012, 3, 13, 0, 0), "Takano, Ichigo", "Drama, Romance, Sci-Fi", "shojo", 8090);

		Manga tommie = new MangaObject(4, "Tomie", 3,
				"In a high school classroom, students mourn the loss of one of their own: Tomie Kawakami, who has been murdered and dismembered. Shocked by the announcement of her death, the class is puzzled by the cruel fate that has befallen someone so dear to them; such a radiant and beautiful girl did not deserve such a hideous demise. However, a strikingly familiar student suddenly appears at the classroom's entrance. Gorgeous, slender, and with a beauty mark under her left eye, Tomie smiles and apologizes for being late.  But this is just the beginning of the mysteries surrounding this seemingly inhuman woman. The men unlucky enough to catch her eye become smitten with her instantly, but also become driven by a dark impulse to dismember her, one they often succumb to. And each time, Tomie returns from the dead to continue her favorite pastime: toying with men. ",
				"Finished", new Date(1987, 1, 1, 0, 0), "Itou, Junji", "Drama, Horror, Supernatural", "shojo", 8990);

		Manga highSchoolDebut = new MangaObject(5, "High School Debut", 15,
				"While in middle school, Haruna Nagashima threw herself into playing softball while secretly dreaming of finding romance, but without any luck. High school has just started for her, and she now has the perfect chance to fall in love with a potential boyfriend. But there's one problem—she has no idea where to begin!  On her first day of school, she accidentally bumps into You Komiyama, the most popular boy in the year. With his stunning looks, Haruna is positive he can help. The handsome boy reluctantly agrees to coach her about love under one condition: she must not fall in love with him. Under You's guidance, will Haruna be able to kick off her high school debut and find herself a boyfriend?",
				"Finished", new Date(2003, 8, 13, 0, 0), "Kawahara, Kazune", "Comedy, Romance", "shojo", 5000);

		// seinen
		Manga berserk = new MangaObject(1, "Berserk", 42,
				"Guts, a former mercenary now known as the Black Swordsman, is out for revenge. After a tumultuous childhood, he finally finds someone he respects and believes he can trust, only to have everything fall apart when this person takes away everything important to Guts for the purpose of fulfilling his own desires. Now marked for death, Guts becomes condemned to a fate in which he is relentlessly pursued by demonic beings.  Setting out on a dreadful quest riddled with misfortune, Guts, armed with a massive sword and monstrous strength, will let nothing stop him, not even death itself, until he is finally able to take the head of the one who stripped him—and his loved one—of their humanity.",
				"Publishing", new Date(1989, 8, 25, 0, 0), "Miura, Kentarou",
				" Action, Adventure, Award Winning, Drama, Fantasy, Horror, Supernatural", "seinen", 6000);

		Manga monster = new MangaObject(2, "Monster", 18,
				"Kenzou Tenma, a renowned Japanese neurosurgeon working in post-war Germany, faces a difficult choice: to operate on Johan Liebert, an orphan boy on the verge of death, or on the mayor of Düsseldorf. In the end, Tenma decides to gamble his reputation by saving Johan, effectively leaving the mayor for dead.  As a consequence of his actions, hospital director Heinemann strips Tenma of his position, and Heinemann's daughter Eva breaks off their engagement. Disgraced and shunned by his colleagues, Tenma loses all hope of a successful career—that is, until the mysterious killing of Heinemann gives him another chance.  Nine years later, Tenma is the head of the surgical department and close to becoming the director himself. Although all seems well for him at first, he soon becomes entangled in a chain of gruesome murders that have taken place throughout Germany. The culprit is a monster—the same one that Tenma saved on that fateful day nine years ago. ",
				"Finished", new Date(1994, 12, 5, 0, 0), "Urasawa, Naoki", "Award Winning, Drama, Mystery", "seinen",
				6700);

		Manga onePunchMan = new MangaObject(3, "One-Punch Man", 29,
				"After rigorously training for three years, the ordinary Saitama has gained immense strength which allows him to take out anyone and anything with just one punch. He decides to put his new skill to good use by becoming a hero. However, he quickly becomes bored with easily defeating monsters, and wants someone to give him a challenge to bring back the spark of being a hero.  Upon bearing witness to Saitama's amazing power, Genos, a cyborg, is determined to become Saitama's apprentice. During this time, Saitama realizes he is neither getting the recognition that he deserves nor known by the people due to him not being a part of the Hero Association. Wanting to boost his reputation, Saitama decides to have Genos register with him, in exchange for taking him in as a pupil. Together, the two begin working their way up toward becoming true heroes, hoping to find strong enemies and earn respect in the process.",
				"Publishing", new Date(2012, 6, 14, 0, 0), "Murata, Yusuke, ONE", "Action, Comedy", "seinen", 1200);

		Manga vinlandSaga = new MangaObject(4, "Vinland Saga", 27,
				"Thorfinn, son of one of the Vikings' greatest warriors, is among the finest fighters in the merry band of mercenaries run by the cunning Askeladd, an impressive feat for a person his age. However, Thorfinn is not part of the group for the plunder it entails—instead, for having caused his family great tragedy, the boy has vowed to kill Askeladd in a fair duel. Not yet skilled enough to defeat him, but unable to abandon his vengeance, Thorfinn spends his boyhood with the mercenary crew, honing his skills on the battlefield among the war-loving Danes, where killing is just another pleasure of life.  One day, when Askeladd receives word that Danish prince Canute has been taken hostage, he hatches an ambitious plot—one that will decide the next King of England and drastically alter the lives of Thorfinn, Canute, and himself. Set in 11th-century Europe, Vinland Saga tells a bloody epic in an era where violence, madness, and injustice are inescapable, providing a paradise for the battle-crazed and utter hell for the rest who live in it. ",
				"Publishing", new Date(2005, 4, 13, 0, 0), "Yukimura, Makoto",
				"Action, Adventure, Award Winning, Drama", "seinen", 9900);

		Manga vagabond = new MangaObject(5, "Vagabond", 37,
				"In 16th-century Japan, Shinmen Takezou is a wild, rough young man, in both his appearance and his actions. His aggressive nature has won him the collective reproach and fear of his village, leading him and his best friend, Matahachi Honiden, to run away in search of something grander than provincial life. The pair enlist in the Toyotomi army, yearning for glory—but when the Toyotomi suffer a crushing defeat at the hands of the Tokugawa Clan at the Battle of Sekigahara, the friends barely make it out alive.  After the two are separated, Shinmen returns home on a self-appointed mission to notify the Hon'iden family of Matahachi's survival. He instead finds himself a wanted criminal, framed for his friend's supposed murder based on his history of violence. Upon being captured, he is strung up on a tree and left to die. An itinerant monk, the distinguished Takuan Soho, takes pity on the devil child, secretly freeing Shinmen and christening him with a new name to avoid pursuit by the authorities: Musashi Miyamoto.  Vagabond is the fictitious retelling of the life of one of Japan's most renowned swordsmen, the Sword Saint Musashi Miyamoto—his rise from a swordsman with no desire other than to become Invincible Under the Heavens to an enlightened warrior who slowly learns of the importance of close friends, self-reflection, and life itself.",
				"On Hiatus", new Date(1998, 9, 3, 0, 0), "Inoue, Takehiko, Yoshikawa, Eiji",
				"Action, Adventure, Award Winning", "seinen", 12000);

		// josei
		Manga usagiDrop = new MangaObject(1, "Usagi Drop", 10,
				"When bachelor Daikichi Kawachi attends his grandfather's funeral, he is surprised to find a mysterious young girl alone in the garden. To his astonishment, the shy and reserved girl, named Rin Kaga, is believed to be the illegitimate child of his late grandfather.  Due to the shameful circumstances related to the little girl, no one in the family is willing to take her in following her father's death. Infuriated by the coldness extended to an innocent child, Daikichi announces that he will take care of Rin himself, despite his young age, single status, and lack of parental experience.  Usagi Drop follows the story of Daikichi and Rin as they try to find happiness and purpose to their fateful meeting. ",
				"Finished", new Date(2005, 10, 8, 0, 0), "Unita, Yumi", "Comedy, Drama, Slice of Life", "josei", 6600);

		Manga chihayafuru = new MangaObject(2, "Chihayafuru", 50,
				"Always deemed inferior to her elder sister, the strong-willed yet aimless Chihaya Ayase has no dream of her own. In contrast to her, Taichi Mashima, the son of a surgeon, is gifted yet insecure as he is burdened by the heavy expectations of his strict mother, who wants him to be perfect in everything. However, the lives of Chihaya and Taichi soon change as they encounter Arata Wataya, the new transfer student in their class.  Inspired by Arata's dream to become the best at competitive karuta—a card game based on the classic anthology of one hundred Japanese poets—Chihaya quickly falls in love with the game. Refusing to lose to the talented Arata, the prideful Taichi joins along and immerses himself in the game, aiming to one day surpass his fated rival. The three friends spend their childhood practicing karuta everyday, until certain circumstances force them to part with each other.  A few years later, now in high school, the trio finds themselves reunited through the world of competitive karuta. Alongside their newfound comrades and rivals, they embark on a journey of self-discovery, friendship, and romance.",
				"Finished", new Date(2007, 12, 8, 0, 0), "Suetsugu, Yuki", "Award Winning, Drama, Mystery", "josei",
				8800);

		Manga kuragehime = new MangaObject(3, "Kuragehime", 17,
				"Tsukimi Kurashita has wanted to be a princess ever since her youth. However, at 18 years old, she finds herself far from that dream. Instead, she has become a plain and shy girl who spends most of her time and money on her biggest obsession: jellyfish.  Tsukimi lives with her fellow niche hobbyists in the all-female Amamizukan apartment complex, where the two most important rules are to avoid stylish people and men. Tsukimi has always abided by these rules until one evening—while rescuing a spotted jellyfish—she encounters a classy, princess-like woman. After allowing the girl to stay the night, Tsukimi is shocked to discover that the person is not a woman at all.",
				"Finished", new Date(2008, 10, 25, 0, 0), "Higashimura, Akiko", "Award Winning, Comedy", "josei", 7700);

		Manga karneval = new MangaObject(4, "Karneval", 28,
				"Naive boy Nai has been looking for someone important to him, with only an old bracelet and the name Karoku as his clues to their whereabouts. Along the way, he becomes ensnared by a monstrous woman hellbent on keeping him as a pet. But soon, he is saved by a clever and crafty thief named Gareki, who has broken into the woman's house to loot it. The two find themselves labeled as wanted criminals and end up entangled in the affairs of Circus—the country's most powerful defense organization. Circus is responsible for protecting the nation's citizens from entities known as varuga—monsters who consume human flesh—as well as uncovering the operations of a mysterious organization called Kafka.  Circus has taken an interest in Nai upon learning that the bracelet he possessed turns out to be an old Circus ID. While Nai decides to stay in Circus' care, Gareki struggles to find his place aboard the ship. As their search for Karoku deepens, the pair encounter more dangerous varuga who all seem to gravitate toward Nai.",
				"Finished", new Date(2007, 8, 28, 0, 0), "Mikanagi, Touya", "Action, Fantasy, Mystery, Sci-Fi", "josei",
				9900);

		Manga _07Ghost = new MangaObject(5, "07-Ghost", 17,
				"The students of the Barsburg Empire's Military Academy are trained to become capable fighters who will serve as the empire's newest soldiers. Every year, five hundred students take a graduation exam, where only 20 can pass to become honorable begleiters, a position equivalent to assistant executive officers. Students fight their enemies by using zaiphon, God-given abilities that take different forms depending on the individual.  Teito Klein, the academy chairman's favorite student, is belittled by his peers due to his previous status as a slave. Despite being unable to fully remember his past, Teito's cheerful best friend, Mikage Celestine, stays by his side. After the two undergo the exam, Teito recalls small pieces of a long-forgotten memory, causing him to instinctively attack the chief-of-staff, Ayanami.  Subdued for his insubordination, Teito is imprisoned for further questioning. But with the help of Mikage, Teito escapes to the Barsburg Church in the empire's Seventh District. As he struggles to escape the clutches of the Barsburg Empire's military, he unveils the memories that lie within him and learns about a compelling power that roams in the shadows of the empire. ",
				"Finished", new Date(2005, 4, 28, 0, 0), "Amemiya, Yuki, Ichihara, Yukino",
				"Action, Fantasy, Mystery, Sci-Fi", "josei", 11000);

		mangaCollection.put(1, doraemon);
		mangaCollection.put(2, pokémonAdventures);
		mangaCollection.put(3, megamanGigamix);
		mangaCollection.put(4, astroBoy);
		mangaCollection.put(5, metalFightBeyblade);
		mangaCollection.put(6, naruto);
		mangaCollection.put(7, bleach);
		mangaCollection.put(8, onepiece);
		mangaCollection.put(9, chainsawMan);
		mangaCollection.put(10, demonSlayer);
		mangaCollection.put(11, aoHaruRide);
		mangaCollection.put(12, lastGame);
		mangaCollection.put(13, orange);
		mangaCollection.put(14, tommie);
		mangaCollection.put(15, highSchoolDebut);
		mangaCollection.put(16, berserk);
		mangaCollection.put(17, monster);
		mangaCollection.put(18, onePunchMan);
		mangaCollection.put(19, vinlandSaga);
		mangaCollection.put(20, vagabond);
		mangaCollection.put(21, usagiDrop);
		mangaCollection.put(22, chihayafuru);
		mangaCollection.put(23, kuragehime);
		mangaCollection.put(24, karneval);
		mangaCollection.put(25, _07Ghost);

		Set<Map.Entry<Integer, Manga>> mangSet = mangaCollection.entrySet();

		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> Press 1 for kodomomuke (childrens).");
		System.out.println("  >> Press 2 for shonen (young boys).");
		System.out.println("  >> Press 3 for shojo (young girls).");
		System.out.println("  >> Press 4 for Seinen (adult male).");
		System.out.println("  >> Press 5 for Josei (adult female).");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 9 for your Orders.");
		System.out.println("  >> Press 99 for login menu.");
		System.out.println("| ** ============================================================ ** |");

		try {
			System.out.print("  >> Enter your choice : ");
			int choice = scr.nextInt();
			System.out.println("| ** ============================================================ ** |");

			Manga manga = new MangaObject();
			switch (choice) {
			case 1:
				for (Map.Entry<Integer, Manga> entry : mangSet) {
//					int id = entry.getKey();
					Manga mangaObj = entry.getValue();
					if (mangaObj.getType().equals("kodomomuke")) {
						System.out.println("  " + mangaObj.getId() + ". " + mangaObj.getTitle());
					}
				}
				System.out.println("| ** ============================================================ ** |");
				System.out.print("  >> Enter your choice : ");
				choice = scr.nextInt();
				System.out.println("| ** ============================================================ ** |");

				switch (choice) {
				case 1:
					((MangaObject) manga).mangaObject(doraemon);
					break;
				case 2:
					((MangaObject) manga).mangaObject(pokémonAdventures);
					break;
				case 3:
					((MangaObject) manga).mangaObject(megamanGigamix);
					break;
				case 4:
					((MangaObject) manga).mangaObject(astroBoy);
					break;
				case 5:
					((MangaObject) manga).mangaObject(metalFightBeyblade);
					break;

				default:
					System.out.println("Invalid stuff.");
				}
				break;

			case 2:
				for (Map.Entry<Integer, Manga> entry : mangSet) {
//					int id = entry.getKey();
					Manga mangaObj = entry.getValue();
					if (mangaObj.getType().equals("shonen")) {
						System.out.println("  " + mangaObj.getId() + ". " + mangaObj.getTitle());
					}
				}
				System.out.println("| ** ============================================================ ** |");
				System.out.print("  >> Enter your choice : ");
				choice = scr.nextInt();
				System.out.println("| ** ============================================================ ** |");

				switch (choice) {
				case 1:
					((MangaObject) manga).mangaObject(naruto);
					break;
				case 2:
					((MangaObject) manga).mangaObject(bleach);
					break;
				case 3:
					((MangaObject) manga).mangaObject(onepiece);
					break;
				case 4:
					((MangaObject) manga).mangaObject(chainsawMan);
					break;
				case 5:
					((MangaObject) manga).mangaObject(demonSlayer);
					break;

				default:
					System.out.println("Invalid stuff.");
				}
				break;

			case 3:
				for (Map.Entry<Integer, Manga> entry : mangSet) {
//					int id = entry.getKey();
					Manga mangaObj = entry.getValue();
					if (mangaObj.getType().equals("shojo")) {
						System.out.println("  " + mangaObj.getId() + ". " + mangaObj.getTitle());
					}
				}

				System.out.println("| ** ============================================================ ** |");
				System.out.print("  >> Enter your choice : ");
				choice = scr.nextInt();
				System.out.println("| ** ============================================================ ** |");

				switch (choice) {
				case 1:
					((MangaObject) manga).mangaObject(aoHaruRide);
					break;
				case 2:
					((MangaObject) manga).mangaObject(lastGame);
					break;
				case 3:
					((MangaObject) manga).mangaObject(orange);
					break;
				case 4:
					((MangaObject) manga).mangaObject(tommie);
					break;
				case 5:
					((MangaObject) manga).mangaObject(highSchoolDebut);
					break;

				default:
					System.out.println("Invalid stuff.");
				}
				break;

			case 4:
				for (Map.Entry<Integer, Manga> entry : mangSet) {
//					int id = entry.getKey();
					Manga mangaObj = entry.getValue();
					if (mangaObj.getType().equals("seinen")) {
						System.out.println("  " + mangaObj.getId() + ". " + mangaObj.getTitle());
					}
				}

				System.out.println("| ** ============================================================ ** |");
				System.out.print("  >> Enter your choice : ");
				choice = scr.nextInt();
				System.out.println("| ** ============================================================ ** |");

				switch (choice) {
				case 1:
					((MangaObject) manga).mangaObject(berserk);
					break;
				case 2:
					((MangaObject) manga).mangaObject(monster);
					break;
				case 3:
					((MangaObject) manga).mangaObject(onePunchMan);
					break;
				case 4:
					((MangaObject) manga).mangaObject(vinlandSaga);
					break;
				case 5:
					((MangaObject) manga).mangaObject(vagabond);
					break;

				default:
					System.out.println("Invalid stuff.");
				}
				break;

			case 5:
				for (Map.Entry<Integer, Manga> entry : mangSet) {
//					int id = entry.getKey();
					Manga mangaObj = entry.getValue();
					if (mangaObj.getType().equals("josei")) {
						System.out.println("  " + mangaObj.getId() + ". " + mangaObj.getTitle());
					}
				}

				System.out.println("| ** ============================================================ ** |");
				System.out.print("  >> Enter your choice : ");
				choice = scr.nextInt();
				System.out.println("| ** ============================================================ ** |");

				switch (choice) {
				case 1:
					((MangaObject) manga).mangaObject(usagiDrop);
					break;
				case 2:
					((MangaObject) manga).mangaObject(chihayafuru);
					break;
				case 3:
					((MangaObject) manga).mangaObject(kuragehime);
					break;
				case 4:
					((MangaObject) manga).mangaObject(karneval);
					break;
				case 5:
					((MangaObject) manga).mangaObject(_07Ghost);
					break;

				default:
					System.out.println("Invalid stuff.");
				}

				break;

			case 0:
				MangaShop.exit();
				break;
			case 9:
				PaymentSystem.yourOrders(scr);
			case 99:
				menu();
				break;

			default:
				throw new InvalidInputException("Invalid input.");
			}

		} catch (Exception e) {
			System.out.println(e);
			menu();
		}
	}
}
// Home Page ends here
//============================================================//
//============================================================//

//============================================================//
//============================================================//
// Main class
public class MangaShop {

	static {
		System.out.println("| ** ============================================================ ** |");
		System.out.println("| <<                     Welcome to MangaShop                     >> |");
		System.out.println("| ** ============================================================ ** |");
	}

	public static void exit() {
		System.exit(0);
	}

	public static void main(String[] args) throws InvalidCredintialException {
		Scanner scr = new Scanner(System.in);
		Authentication authProcess = new AuthProcess();
		while (true) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  **                    Registration Process                      **  ");
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  >> Press 1 for Signup.");
			System.out.println("  >> Press 2 for Login.");
			System.out.println("  >> Press 0 for Exit.");
			System.out.println("| ** ============================================================ ** |");
			try {
				System.out.print("  >> Enter your choice: ");
				int choice = scr.nextInt();
				System.out.println("| ** ============================================================ ** |");

				switch (choice) {
				case 1:
					authProcess.signup(scr);
					;
					break;
				case 2:
					authProcess.login(scr);
					break;
				case 3:
					System.out.println("  ** --++ All Users ++-- **");
					((AuthProcess) authProcess).allUserCollection();
					break;
				case 4:
					System.out.println("  ** All users ** ");
					((AuthProcess) authProcess).allUsersMap();
					break;
				case 0:
					System.exit(0);
					break;
				default:
					System.out.println("!! Please enter the correct input. !!");
					main(null);
				}
			} catch (InvalidCredintialException e) {
				System.out.println("Exception Occured" + e);
				main(null);
			}

		}
	}
}
//============================================================//
//============================================================//
