
// Importing statements

import java.util.Scanner;

// Exception Importing Statements
import java.util.InputMismatchException;

// ** ============================================================ ** //
// ** Start : Registration. ** //
//** ============================================================ ** //

// ** Abstraction : WelcomePage Interface.
interface WelcomePage {
	// Abstract Methods
	void login();

	void signup();
}

// ** Inheritance : Registration Class is Inheriting WelcomePage Interface.
class Registration implements WelcomePage {

	// ** Encapsulation : Data Hiding
	private String name;
	private long contact = 1234567890L;
	private String email;
	private String address;
	private String password = "admin";

	// ** Encapsulation : Getter and Setter Methods.
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContact() {
		return contact;
	}

	// ** Exception Propogation : throws InputMismatchException.
	public void setContact() {

		Scanner input = new Scanner(System.in);

		try {
			System.out.print("  >> Enter contact no : ");
			this.contact = input.nextLong();

			if (!(this.contact > 999999999L) && !(this.contact < 100000000000L)) {
				System.out.println("  !! Please enter 10 digits");

				// Method Recursion : Recursive Calling Statement.
				setContact();
			}
		} catch (InputMismatchException ie) {
			System.out.println("  !! Enter Number Only.");

			// Method Recursion : Recursive Calling Statement.
			setContact();
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// ** Polymorphism :
	// ** Runtime Polymorphism : Method Overriding.
	public void login() {

		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("| <<                       Login Process                          >> |");
		System.out.println("| ** ============================================================ ** |");

		Scanner input = new Scanner(System.in);

		// ** Exception Handling Mechanism : try catch.
		try {
			System.out.print("  >> Enter contact No. : ");
			long contact = input.nextLong();
			input.nextLine();

			System.out.print("  >> Enter password : ");
			String password = input.nextLine();

			if (this.contact == contact && this.password.equals(password)) {
				System.out.println("| ** ============================================================ ** |");
				System.out.println("| <<                    Logged In Successfully                    >> |");
				System.out.println("| ** ============================================================ ** |");
				System.out.println();
				System.out.println("                                *****                                 ");
			} else {
				System.out.println("| ** ============================================================ ** |");
				System.out.println("| <<              Invalid Credential Please Try Again             >> |");
				System.out.println();
				System.out.println("                                *****                                 ");
				login();
			}
		} catch (InputMismatchException ie) {
			System.out.println("| <<               Invalid Input Please Try Again                >> |");
			// Method Recursion : Recursive Calling Statement.
			login();
		}

	}

	// ** Runtime Polymorphism : Method Overriding.
	public void signup() {

		System.out.println("| ** ============================================================ ** |");
		System.out.println("| <<                       Signup Process                         >> |");
		System.out.println("| ** ============================================================ ** |");

		Scanner input = new Scanner(System.in);

		// ** Exception Handling Mechanism : try cath with finally.
		try {
			System.out.print("  >> Enter your name : ");
			String name = input.nextLine();
			setName(name);

			// Method Calling Statement
			setContact();

			System.out.print("  >> Enter your email : ");
			String email = input.nextLine();
			setEmail(email);

			System.out.print("  >> Enter your address : ");
			String address = input.nextLine();
			setAddress(address);

			System.out.print("  >> Enter your password : ");
			String password = input.nextLine();
			setPassword(password);

			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       Signup Successfully                    >> |");
			System.out.println("| ** ============================================================ ** |");
			System.out.println();
			System.out.println("                                *****                                 ");

			// Method Calling Statement
			login();
		} catch (Exception e) {
			System.out.println("  !! Invalid input. ");

			// Method Recursion : Recursive Calling Statement.
			signup();
		}

	}

}
//** ============================================================ ** //
//** End : Registration. ** //
//** ============================================================ ** //

//** ============================================================ ** //
//** Start : Biiling System. ** //
//** ============================================================ ** //

// ** Abstraction : BillingSystem Interface
interface BillingSystem {

	// Abstract Methods.
	void totalBill();

	void payment(String pay);

	void payment(double cash);
}

class PaymentSystem implements BillingSystem {

	static double bill;

	public void totalBill() {

		String paymentId;
		// ** TypeCasting : Narrowing ( double >> int ).
		int discount = (int) (bill * 0.1);

		Scanner input = new Scanner(System.in);

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
			int choice = input.nextInt();

			System.out.println("| ** ============================================================ ** |");

			switch (choice) {
			case 1:
				System.out.print("  >> Enter the UPI Id : ");
				paymentId = input.nextLine();
				input.nextLine();

				// Method Calling System : Actual Arguments.
				payment(paymentId);
				break;
			case 2:
				System.out.print("  >> Enter the Paypal Id : ");
				paymentId = input.nextLine();
				input.nextLine();
				// Method Calling System : Actual Arguments.
				payment(paymentId);
				break;
			case 3:
				// Method Calling System : Actual Arguments.
				payment(bill);
				break;
			default:
				bill = bill + discount;
				// Method Recursion : Recursive Calling Statement.
				totalBill();
				break;
			}
			System.exit(0);
		} catch (Exception e) {
			System.out.println("  !! There is someting wrong Please try again. !!  ");
			totalBill();
		}
	}

	// ** Polymorphism :
	// ** Runtime Polymorphism - Method Overriding
	// ** Compile Time Polymorphism - Method Overloading
	public void payment(String paymentId) {

		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();

		System.out.print("  >> Enter the amount : ");
		double amount = input.nextDouble();

		System.out.println("  >> Thank you for paying : " + amount + "  ₹ /-");
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> Would you like to continue purchasing more manga, manhwa, and merchandise.");
		System.out.print("  >> Press Y or y for yes and N or n for No : ");
		char ch = input.next().charAt(0);
		if (ch == 'y' || ch == 'Y') {
			homepage.menu();
		} else if (ch == 'n' || ch == 'N') {
			System.exit(0);
		} else {
			System.out.println("  >> You have selected wrong option.");
			payment(null);
		}

	}

	// ** Runtime Polymorphism - Method Overriding
	// ** Compile Time Polymorphism - Method Overloading
	public void payment(double cash) {
		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();

		System.out.println("  >> Thank you for paying : " + cash);
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> Would you like to continue purchasing more manga, manhwa, and merchandise.");
		System.out.print("  >> Press Y or y for yes and N or n for No : ");
		char ch = input.next().charAt(0);
		if (ch == 'y' || ch == 'Y') {
			homepage.menu();
		} else if (ch == 'n' || ch == 'N') {
			System.exit(0);
		} else {
			System.out.println("  >> You have selected wrong option.");
			payment(null);
		}
	}
}
//** ============================================================ ** //
//** End : Biiling System. ** //
//** ============================================================ ** //

//** ============================================================ ** //
//** Start : HomePage Class. ** //
//** ============================================================ ** //

// Home Page
class HomePage {

	public void menu() {

		Scanner input = new Scanner(System.in);

		System.out.println();
		System.out.println("                       ** choose your type **                         ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> Press 1 for kodomomuke (childrens).");
		System.out.println("  >> Press 2 for shonen (young boys).");
		System.out.println("  >> Press 3 for shojo (young girls).");
		System.out.println("  >> Press 4 for Seinen (adult male).");
		System.out.println("  >> Press 5 for Josei (adult female).");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous.");
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice: ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		switch (choice) {

		case 1:
			kodomomuke();
			break;
		case 2:
			shonenManga();
			break;
		case 3:
			shojoManga();
			break;
		case 4:
			seinenManga();
			break;
		case 5:
			joseiManga();
			break;
		case 0:
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
			break;
		case 99:
			menu();
			break;
		default:
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| !!             Please enter the correct input.                  !! |");
			System.out.println("| ** ============================================================ ** |");
			menu();
			break;

		}

	}

	public void kodomomuke() {

		Scanner input = new Scanner(System.in);
//		BillingSystem payment = new PaymentSystem();

		System.out.println("| ** ================ ** Manga for childrens. ** ================ ** |");
		System.out.println();
		System.out.println("                       ** choose your Manga **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");

		KodomomukeManga doraemon = new KodomomukeManga(1, "Doraemon", "Manga", 45,
				"Doraemon is cat shaped robot from the future traveling all the way back to the 20th century in order to help Nobi Nobita, a lazy kid with nothing really going for him. Nobi is bad at sports, his grades are terrible, he can't even win a match of rock-paper-scissors. Doraemon has a hard task ahead of him but he is well prepared with a huge arsenal of the most inventive and funny gadgets available. The problem is he's not the most competent robot cat helper out there either and the pair gets in constant trouble because of it. Thus begins one of the most interesting friendships ever to appear in a manga series.",
				"Finished", "Dec 1969 to 1996", "Fujiko F. Fujio",
				"Adventure, Award Winning, Comedy, Sci-Fi, Slice of Life", "Kids", 7412.07);

		System.out.println("  1. " + doraemon.title);

		KodomomukeManga pokémonAdventures = new KodomomukeManga(2, "Pokémon Adventures", "Manga", 14,
				"Red is just a normal kid living in the rural Pallet Town, when he decides to go out on his own adventure, along with his rival, Green. In this world of Pokémon, he makes many friends, humans and Pokémon alike. However, all is not well. Team Rocket is trying to capture Mew, a very rare Pokémon, and is performing experiments on other Pokémon, trying to increase their power. Red and his friends must battle against Team Rocket to stop their cruel experiments and unlock the secrets of Pokémon.",
				"Finished", "Nov 1996 to Apr 28, 2003", "Hidenori Kusaka", "Action, Adventure", "Kids", 3498.26);

		System.out.println("  2. " + pokémonAdventures.title);

		KodomomukeManga megamanGigamix = new KodomomukeManga(3, "Megaman Gigamix", "Manga", 3,
				"Mega Man Gigamix is a manga drawn by Hitoshi Ariga and connected with Mega Man Megamix, being that some chapters are prequels and others are direct follow-ups. The manga was published by BN in three volumes between 2009-2010 in Japan and by Udon Entertainment between 2011-2012 in the United States.",
				"Finished", "Nov 30, 2009 to Nov 27, 2010", "Ariga, Hitoshi", "Action, Adventure, Sci-Fi", "Kids",
				999.50);

		System.out.println("  3. " + megamanGigamix.title);

		KodomomukeManga astroBoy = new KodomomukeManga(4, "Astro Boy", "Manga", 3,
				"A more modern version of Tetsuwan Atom.", "Finished", "Apr 2003 to Aug 2003", "Tezuka, Osamu",
				"Action, Adventure, Sci-Fi", "Kids", 1999.01);

		System.out.println("  4. " + astroBoy.title);

		KodomomukeManga metalFightBeyblade = new KodomomukeManga(5, "Metal Fight Beyblade", "Manga", 11,
				"No synopsis information has been added to this title. Help improve our database by adding a synopsis here.",
				"Finished", "Sep 13, 2008 to Feb 15, 2012", "Adachi, Takafumi", "Adventure, Sports", "Kids", 3165.10);

		System.out.println("  5. " + metalFightBeyblade.title);

		System.out.println("| ** ============================================================ ** |");
		System.out.println();
		System.out.println("  >> Press Number from above for your fav. Manga. ");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			menu();
		} else {

			switch (choice) {
			case 1:
				doraemon.kodomomuke(doraemon);
				break;

			case 2:
				pokémonAdventures.kodomomuke(pokémonAdventures);
				break;

			case 3:
				megamanGigamix.kodomomuke(megamanGigamix);
				break;

			case 4:
				astroBoy.kodomomuke(astroBoy);
				break;

			case 5:
				metalFightBeyblade.kodomomuke(metalFightBeyblade);
				break;

			default:
				System.out.println("| ** ============================================================ ** |");
				System.out.println("| !!             Please enter the correct input.                  !! |");
				System.out.println("| ** ============================================================ ** |");
				kodomomuke();
			}
		}
	}

	public void shonenManga() {
		Scanner input = new Scanner(System.in);
//		BillingSystem payment = new PaymentSystem();

		System.out.println("| ** ================ ** Manga for young boys. ** ================ ** |");
		System.out.println();
		System.out.println("                       ** choose your Manga **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		ShonenManga naruto = new ShonenManga(1, "Naruto", "Manga", 72,
				"Whenever Naruto Uzumaki proclaims that he will someday become the Hokage—a title bestowed upon the best ninja in the Village Hidden in the Leaves—no one takes him seriously. Since birth, Naruto has been shunned and ridiculed by his fellow villagers. But their contempt isn't because Naruto is loud-mouthed, mischievous, or because of his ineptitude in the ninja arts, but because there is a demon inside him. Prior to Naruto's birth, the powerful and deadly Nine-Tailed Fox attacked the village. In order to stop the rampage, the Fourth Hokage sacrificed his life to seal the demon inside the body of the newborn Naruto.And so when he is assigned to Team 7—along with his new teammates Sasuke Uchiha and Sakura Haruno, under the mentorship of veteran ninja Kakashi Hatake—Naruto is forced to work together with other people for the first time in his life. Through undergoing vigorous training and taking on challenging missions, Naruto must learn what it means to work in a team and carve his own route toward becoming a full-fledged ninja recognized by his village.",
				"Finished", "Sep 21, 1999 to Nov 10, 2014", "Masashi Kishimoto", "Action, Adventure, Fantasy",
				"Young Boys (Shonen)", 10078.20);
		System.out.println("  1. " + naruto.title);

		ShonenManga bleach = new ShonenManga(2, "Bleach", "Manga", 74,
				"For as long as he can remember, high school student Ichigo Kurosaki has been able to see the spirits of the dead, but that has not stopped him from leading an ordinary life. One day, Ichigo returns home to find an intruder in his room who introduces herself as Rukia Kuchiki, a Soul Reaper tasked with helping souls pass over. Suddenly, the two are jolted from their conversation when a Hollow—an evil spirit known for consuming souls—attacks. As Ichigo makes a brash attempt to stop the Hollow, Rukia steps in and shields him from a counterattack. Injured and unable to keep fighting, Rukia suggests a risky plan—transfer half of her Soul Reaper powers to Ichigo. He accepts and, to Rukia's surprise, ends up absorbing her powers entirely, allowing him to easily dispatch the Hollow.\r\n"
						+ "\r\n"
						+ "Now a Soul Reaper himself, Ichigo must take up Rukia's duties of exterminating Hollows and protecting spirits, both living and dead. Along with his friends Orihime Inoue and Yasutora Sado—who later discover spiritual abilities of their own—Ichigo soon learns that the consequences of becoming a Soul Reaper and dealing with the world of spirits are far greater than he ever imagined.",
				"Finished", "Aug 7, 2001 to Aug 22, 2016", "Kubo, Tite", "Action, Adventure, Award Winning, Fantasy",
				"Young Boys (Shonen)", 9828.39);
		System.out.println("  2. " + bleach.title);

		ShonenManga onepiece = new ShonenManga(3, "onepiece", "Manga", 106,
				"Gol D. Roger, a man referred to as the \"King of the Pirates,\" is set to be executed by the World Government. But just before his demise, he confirms the existence of a great treasure, One Piece, located somewhere within the vast ocean known as the Grand Line. Announcing that One Piece can be claimed by anyone worthy enough to reach it, the King of the Pirates is executed and the Great Age of Pirates begins.\r\n"
						+ "\r\n"
						+ "Twenty-two years later, a young man by the name of Monkey D. Luffy is ready to embark on his own adventure, searching for One Piece and striving to become the new King of the Pirates. Armed with just a straw hat, a small boat, and an elastic body, he sets out on a fantastic journey to gather his own crew and a worthy ship that will take them across the Grand Line to claim the greatest status on the high seas.",
				"Publishing", "Jul 22, 1997 to ?", "Oda, Eiichiro", "Action, Adventure, Fantasy", "Young Boys (Shonen)",
				11660.80);
		System.out.println("  3. " + onepiece.title);

		ShonenManga chainsawMan = new ShonenManga(4, "chainsawMan", "Manga", 15,
				"Denji has a simple dream—to live a happy and peaceful life, spending time with a girl he likes. This is a far cry from reality, however, as Denji is forced by the yakuza into killing devils in order to pay off his crushing debts. Using his pet devil Pochita as a weapon, he is ready to do anything for a bit of cash.\r\n"
						+ "\r\n"
						+ "Unfortunately, he has outlived his usefulness and is murdered by a devil in contract with the yakuza. However, in an unexpected turn of events, Pochita merges with Denji's dead body and grants him the powers of a chainsaw devil. Now able to transform parts of his body into chainsaws, a revived Denji uses his new abilities to quickly and brutally dispatch his enemies. Catching the eye of the official devil hunters who arrive at the scene, he is offered work at the Public Safety Bureau as one of them. Now with the means to face even the toughest of enemies, Denji will stop at nothing to achieve his simple teenage dreams.",
				"Publishing", "Dec 3, 2018 to ?", "Fujimoto, Tatsuki", "Action, Award Winning, Supernatural",
				"Young Boys (Shonen)", 8329.15);
		System.out.println("  4. " + chainsawMan.title);

		ShonenManga demonSlayer = new ShonenManga(5, "demonSlayer", "Manga", 23,
				"Tanjirou Kamado lives with his impoverished family on a remote mountain. As the oldest sibling, he took upon the responsibility of ensuring his family's livelihood after the death of his father. On a cold winter day, he goes down to the local village in order to sell some charcoal. As dusk falls, he is forced to spend the night in the house of a curious man who cautions him of strange creatures that roam the night: malevolent demons who crave human flesh.\r\n"
						+ "\r\n"
						+ "When he finally makes his way home, Tanjirou's worst nightmare comes true. His entire family has been brutally slaughtered with the sole exception of his sister Nezuko, who has turned into a flesh-eating demon. Engulfed in hatred and despair, Tanjirou desperately tries to stop Nezuko from attacking other people, setting out on a journey to avenge his family and find a way to turn his beloved sister back into a human.",
				"Finished", "Feb 15, 2016 to May 18, 2020", "Gotouge, Koyoharu", "Action, Fantasy",
				"Young Boys (Shonen)", 9162.06);
		System.out.println("  5. " + demonSlayer.title);

		System.out.println("| ** ============================================================ ** |");
		System.out.println();
		System.out.println("  >> Press Number from above for your fav. Manga. ");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			menu();
		} else {

			switch (choice) {
			case 1:
				naruto.shonen(naruto);
				break;

			case 2:
				bleach.shonen(bleach);
				break;

			case 3:
				onepiece.shonen(onepiece);
				break;

			case 4:
				chainsawMan.shonen(chainsawMan);
				break;

			case 5:
				demonSlayer.shonen(demonSlayer);
				break;

			default:
				System.out.println("| ** ============================================================ ** |");
				System.out.println("| !!             Please enter the correct input.                  !! |");
				System.out.println("| ** ============================================================ ** |");
				shonenManga();
			}
		}
	}

	public void shojoManga() {
		Scanner input = new Scanner(System.in);
//		BillingSystem payment = new PaymentSystem();

		System.out.println("| ** ================ ** Manga for young girls. ** ================ ** |");
		System.out.println();
		System.out.println("                       ** choose your Manga **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		ShojoManga aoHaruRide = new ShojoManga(1, "Ao Haru Ride", "Manga", 13,
				"While most girls desire popularity among boys, Futaba Yoshioka wants the exact opposite. After attracting many admirers back in middle school which resulted in her being shunned by her female classmates, she decided that high school will be her chance to revamp her image. Therefore, she starts acting unfeminine and indifferent to boys, allowing her to make some friends along the way. Little does Futaba know, her life will take another drastic turn when her first love, Kou Mabuchi, returns after his sudden disappearance years ago in middle school. Despite his extended absence, the fond memories they shared together still linger in her mind, and she wishes to return to those days. But she realizes that the sweet, gentle boy of the past has completely vanished, and in his place stands someone cold and pessimistic. While he admits that her feelings for him back then were mutual, he warns that they can never go back to the past, as everything, including him, has changed. Ao Haru Ride follows Futaba as she searches for true love and friendship, all while trying her best to stay true to herself.",
				"Finished", " Jan 13, 2011 to Feb 13, 2015", "Sakisaka, Io", "Romance", "Young girls", 60);
		System.out.println("  1. " + aoHaruRide.title);

		ShojoManga lastGame = new ShojoManga(2, "Last Game", "Manga", 11,
				"Nothing is beyond Naoto Yanagi, heir to the Yanagi business conglomerate. Idolized for his athletic and intellectual competence, looks and wealth, Naoto lived like a king during his elementary school days—then entered Mikoto Kujou, a plain, gloomy-looking transfer student.  Due to her low financial status, Naoto was initially apathetic towards Mikoto. But despite having just arrived at his school, she completely eclipsed him in everything by consistently scoring top marks in exams and placing first in athletic events. After a brief confrontation with her that left him shocked, Naoto vowed to outdo her no matter the cost.  Ten years later, they are now students attending the same college. Having failed to defeat Mikoto throughout middle and high school, Naoto decides they will have one last game: if he can make Mikoto fall in love with him and then break her heart, it will be his victory. However, he finds himself falling in love with her instead…",
				"Finished", "Aug 24, 2011 to Jun 24, 2016", "Amano, Shinobu", "Comedy, Romance", "Young girls", 77);
		System.out.println("  2. " + lastGame.title);

		ShojoManga orange = new ShojoManga(3, "Orange", "Manga", 7,
				"One morning, Naho Takamiya receives a letter in the mail claiming to be from herself 10 years in the future. The letter reveals a series of events that are supposed to take place that day. At first, Naho thinks it's just a prank; but when the letter mentions a transfer student named Kakeru Naruse, who really ends up transferring into her class later that day, she is forced to believe in its contents.  As Naho continues to read the letter, her future self mentions several regrets, urging Naho to take the right decisions now. Somehow these regrets all seem to be connected to Kakeru, and with the burden of the knowledge that the boy wouldn't be with her and her friends in the future, will Naho be able to make the perfect choices that will alter what seems to be fate?",
				"Finished", "Mar 13, 2012 to Apr 8, 2022", "Takano, Ichigo", "Drama, Romance, Sci-Fi", "Young Girls",
				80);
		System.out.println("  3. " + orange.title);

		ShojoManga tommie = new ShojoManga(4, "Tomie", "Manga", 3,
				"In a high school classroom, students mourn the loss of one of their own: Tomie Kawakami, who has been murdered and dismembered. Shocked by the announcement of her death, the class is puzzled by the cruel fate that has befallen someone so dear to them; such a radiant and beautiful girl did not deserve such a hideous demise. However, a strikingly familiar student suddenly appears at the classroom's entrance. Gorgeous, slender, and with a beauty mark under her left eye, Tomie smiles and apologizes for being late.  But this is just the beginning of the mysteries surrounding this seemingly inhuman woman. The men unlucky enough to catch her eye become smitten with her instantly, but also become driven by a dark impulse to dismember her, one they often succumb to. And each time, Tomie returns from the dead to continue her favorite pastime: toying with men. ",
				"Finished", "1987 to 2000", "Itou, Junji", "Drama, Horror, Supernatural", "Young girls", 90);
		System.out.println("  4. " + tommie.title);

		ShojoManga highSchoolDebut = new ShojoManga(5, "High School Debut", "Manga", 15,
				"While in middle school, Haruna Nagashima threw herself into playing softball while secretly dreaming of finding romance, but without any luck. High school has just started for her, and she now has the perfect chance to fall in love with a potential boyfriend. But there's one problem—she has no idea where to begin!  On her first day of school, she accidentally bumps into You Komiyama, the most popular boy in the year. With his stunning looks, Haruna is positive he can help. The handsome boy reluctantly agrees to coach her about love under one condition: she must not fall in love with him. Under You's guidance, will Haruna be able to kick off her high school debut and find herself a boyfriend?",
				"Finished", "Aug 13, 2003 to May 13, 2013", "Kawahara, Kazune", "Comedy, Romance", "Young girls", 50);
		System.out.println("  5. " + highSchoolDebut.title);

		System.out.println("| ** ============================================================ ** |");
		System.out.println();
		System.out.println("  >> Press Number from above for your fav. Manga. ");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			menu();
		} else {

			switch (choice) {
			case 1:
				aoHaruRide.shojo(aoHaruRide);
				break;

			case 2:
				lastGame.shojo(lastGame);
				break;

			case 3:
				orange.shojo(orange);
				break;

			case 4:
				tommie.shojo(tommie);
				break;

			case 5:
				highSchoolDebut.shojo(highSchoolDebut);
				break;

			default:
				System.out.println("| ** ============================================================ ** |");
				System.out.println("| !!             Please enter the correct input.                  !! |");
				System.out.println("| ** ============================================================ ** |");
				shojoManga();
			}
		}
	}

	public void seinenManga() {

		Scanner input = new Scanner(System.in);
//		BillingSystem payment = new PaymentSystem();

		System.out.println("| ** ================ ** Manga for Adult Male. ** ================ ** |");
		System.out.println();
		System.out.println("                       ** choose your Manga **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> Press Number for your fav. Manga. ");
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		SeinenManga berserk = new SeinenManga(1, "Berserk", "Manga", 42, "Guts, a former mercenary now known as the Black Swordsman, is out for revenge. After a tumultuous childhood, he finally finds someone he respects and believes he can trust, only to have everything fall apart when this person takes away everything important to Guts for the purpose of fulfilling his own desires. Now marked for death, Guts becomes condemned to a fate in which he is relentlessly pursued by demonic beings.  Setting out on a dreadful quest riddled with misfortune, Guts, armed with a massive sword and monstrous strength, will let nothing stop him, not even death itself, until he is finally able to take the head of the one who stripped him—and his loved one—of their humanity.", "Publishing", " Aug 25, 1989 to ?", "Miura, Kentarou", " Action, Adventure, Award Winning, Drama, Fantasy, Horror, Supernatural", "Adult Male", 160);
		System.out.println("  1. " + berserk.title);

		SeinenManga monster = new SeinenManga(2, "Monster", "Manga", 18, "Kenzou Tenma, a renowned Japanese neurosurgeon working in post-war Germany, faces a difficult choice: to operate on Johan Liebert, an orphan boy on the verge of death, or on the mayor of Düsseldorf. In the end, Tenma decides to gamble his reputation by saving Johan, effectively leaving the mayor for dead.  As a consequence of his actions, hospital director Heinemann strips Tenma of his position, and Heinemann's daughter Eva breaks off their engagement. Disgraced and shunned by his colleagues, Tenma loses all hope of a successful career—that is, until the mysterious killing of Heinemann gives him another chance.  Nine years later, Tenma is the head of the surgical department and close to becoming the director himself. Although all seems well for him at first, he soon becomes entangled in a chain of gruesome murders that have taken place throughout Germany. The culprit is a monster—the same one that Tenma saved on that fateful day nine years ago. ", "Finished", "Dec 5, 1994 to Dec 20, 2001", "Urasawa, Naoki", "Award Winning, Drama, Mystery", "Adult Male", 67);
		System.out.println("  2. " + monster.title);

		SeinenManga onePunchMan = new SeinenManga(3, "One-Punch Man", "Manga", 29, "After rigorously training for three years, the ordinary Saitama has gained immense strength which allows him to take out anyone and anything with just one punch. He decides to put his new skill to good use by becoming a hero. However, he quickly becomes bored with easily defeating monsters, and wants someone to give him a challenge to bring back the spark of being a hero.  Upon bearing witness to Saitama's amazing power, Genos, a cyborg, is determined to become Saitama's apprentice. During this time, Saitama realizes he is neither getting the recognition that he deserves nor known by the people due to him not being a part of the Hero Association. Wanting to boost his reputation, Saitama decides to have Genos register with him, in exchange for taking him in as a pupil. Together, the two begin working their way up toward becoming true heroes, hoping to find strong enemies and earn respect in the process.", "Publishing", "Jun 14, 2012 to ?", "Murata, Yusuke, ONE", "Action, Comedy", "Adult Male", 120);
		System.out.println("  3. " + onePunchMan.title);

		SeinenManga vinlandSaga = new SeinenManga(4, "Vinland Saga", "Manga", 27, "Thorfinn, son of one of the Vikings' greatest warriors, is among the finest fighters in the merry band of mercenaries run by the cunning Askeladd, an impressive feat for a person his age. However, Thorfinn is not part of the group for the plunder it entails—instead, for having caused his family great tragedy, the boy has vowed to kill Askeladd in a fair duel. Not yet skilled enough to defeat him, but unable to abandon his vengeance, Thorfinn spends his boyhood with the mercenary crew, honing his skills on the battlefield among the war-loving Danes, where killing is just another pleasure of life.  One day, when Askeladd receives word that Danish prince Canute has been taken hostage, he hatches an ambitious plot—one that will decide the next King of England and drastically alter the lives of Thorfinn, Canute, and himself. Set in 11th-century Europe, Vinland Saga tells a bloody epic in an era where violence, madness, and injustice are inescapable, providing a paradise for the battle-crazed and utter hell for the rest who live in it. ", "Publishing", "Apr 13, 2005 to ?", "Yukimura, Makoto", "Action, Adventure, Award Winning, Drama", "Adult Male", 99);
		System.out.println("  4. " + vinlandSaga.title);

		SeinenManga vagabond = new SeinenManga(5, "Vagabond", "Manga", 37, "In 16th-century Japan, Shinmen Takezou is a wild, rough young man, in both his appearance and his actions. His aggressive nature has won him the collective reproach and fear of his village, leading him and his best friend, Matahachi Honiden, to run away in search of something grander than provincial life. The pair enlist in the Toyotomi army, yearning for glory—but when the Toyotomi suffer a crushing defeat at the hands of the Tokugawa Clan at the Battle of Sekigahara, the friends barely make it out alive.  After the two are separated, Shinmen returns home on a self-appointed mission to notify the Hon'iden family of Matahachi's survival. He instead finds himself a wanted criminal, framed for his friend's supposed murder based on his history of violence. Upon being captured, he is strung up on a tree and left to die. An itinerant monk, the distinguished Takuan Soho, takes pity on the devil child, secretly freeing Shinmen and christening him with a new name to avoid pursuit by the authorities: Musashi Miyamoto.  Vagabond is the fictitious retelling of the life of one of Japan's most renowned swordsmen, the Sword Saint Musashi Miyamoto—his rise from a swordsman with no desire other than to become Invincible Under the Heavens to an enlightened warrior who slowly learns of the importance of close friends, self-reflection, and life itself.", "On Hiatus", "Sep 3, 1998 to May 21, 2015", "Inoue, Takehiko, Yoshikawa, Eiji", "Action, Adventure, Award Winning", "Adult Male", 120);
		System.out.println("  5. " + vagabond.title);

		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			menu();
		} else {

			switch (choice) {
			case 1:
				berserk.seinen(berserk);
				break;

			case 2:
				monster.seinen(monster);
				break;

			case 3:
				onePunchMan.seinen(onePunchMan);
				break;

			case 4:
				vinlandSaga.seinen(vinlandSaga);
				break;

			case 5:
				vagabond.seinen(vagabond);
				break;

			default:
				System.out.println("| ** ============================================================ ** |");
				System.out.println("| !!             Please enter the correct input.                  !! |");
				System.out.println("| ** ============================================================ ** |");
				shonenManga();
			}
		}
	}

	public void joseiManga() {

		Scanner input = new Scanner(System.in);
//		BillingSystem payment = new PaymentSystem();

		System.out.println("| ** ================ ** Manga for Adult Female. ** ================ ** |");
		System.out.println();
		System.out.println("                       ** choose your Manga **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> Press Number for your fav. Manga. ");
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		JoseiManga usagiDrop = new JoseiManga(1, "Usagi Drop", "Manga", 10, "When bachelor Daikichi Kawachi attends his grandfather's funeral, he is surprised to find a mysterious young girl alone in the garden. To his astonishment, the shy and reserved girl, named Rin Kaga, is believed to be the illegitimate child of his late grandfather.  Due to the shameful circumstances related to the little girl, no one in the family is willing to take her in following her father's death. Infuriated by the coldness extended to an innocent child, Daikichi announces that he will take care of Rin himself, despite his young age, single status, and lack of parental experience.  Usagi Drop follows the story of Daikichi and Rin as they try to find happiness and purpose to their fateful meeting. ", "Finished", "Oct 8, 2005 to Dec 8, 2011", "Unita, Yumi", "Comedy, Drama, Slice of Life", "Adult Female", 66);
		System.out.println("  1. " + usagiDrop.title);

		SeinenManga chihayafuru = new SeinenManga(2, "Chihayafuru", "Manga", 50, "Always deemed inferior to her elder sister, the strong-willed yet aimless Chihaya Ayase has no dream of her own. In contrast to her, Taichi Mashima, the son of a surgeon, is gifted yet insecure as he is burdened by the heavy expectations of his strict mother, who wants him to be perfect in everything. However, the lives of Chihaya and Taichi soon change as they encounter Arata Wataya, the new transfer student in their class.  Inspired by Arata's dream to become the best at competitive karuta—a card game based on the classic anthology of one hundred Japanese poets—Chihaya quickly falls in love with the game. Refusing to lose to the talented Arata, the prideful Taichi joins along and immerses himself in the game, aiming to one day surpass his fated rival. The three friends spend their childhood practicing karuta everyday, until certain circumstances force them to part with each other.  A few years later, now in high school, the trio finds themselves reunited through the world of competitive karuta. Alongside their newfound comrades and rivals, they embark on a journey of self-discovery, friendship, and romance.", "Finished", "Dec 28, 2007 to Aug 1, 2022", "Suetsugu, Yuki", "Award Winning, Drama, Mystery", "Adult Female", 88);
		System.out.println("  2. " + chihayafuru.title);

		SeinenManga kuragehime = new SeinenManga(3, "Kuragehime", "Manga", 17, "Tsukimi Kurashita has wanted to be a princess ever since her youth. However, at 18 years old, she finds herself far from that dream. Instead, she has become a plain and shy girl who spends most of her time and money on her biggest obsession: jellyfish.  Tsukimi lives with her fellow niche hobbyists in the all-female Amamizukan apartment complex, where the two most important rules are to avoid stylish people and men. Tsukimi has always abided by these rules until one evening—while rescuing a spotted jellyfish—she encounters a classy, princess-like woman. After allowing the girl to stay the night, Tsukimi is shocked to discover that the person is not a woman at all.", "Finished", "Oct 25, 2008 to Aug 25, 2017", "Higashimura, Akiko", "Award Winning, Comedy", "Adult Female", 77);
		System.out.println("  3. " + kuragehime.title);

		SeinenManga karneval = new SeinenManga(4, "Karneval", "Manga", 28, "Naive boy Nai has been looking for someone important to him, with only an old bracelet and the name Karoku as his clues to their whereabouts. Along the way, he becomes ensnared by a monstrous woman hellbent on keeping him as a pet. But soon, he is saved by a clever and crafty thief named Gareki, who has broken into the woman's house to loot it. The two find themselves labeled as wanted criminals and end up entangled in the affairs of Circus—the country's most powerful defense organization. Circus is responsible for protecting the nation's citizens from entities known as varuga—monsters who consume human flesh—as well as uncovering the operations of a mysterious organization called Kafka.  Circus has taken an interest in Nai upon learning that the bracelet he possessed turns out to be an old Circus ID. While Nai decides to stay in Circus' care, Gareki struggles to find his place aboard the ship. As their search for Karoku deepens, the pair encounter more dangerous varuga who all seem to gravitate toward Nai.", "Finished", "Aug 28, 2007 to Oct 28, 2021", "Mikanagi, Touya", "Action, Fantasy, Mystery, Sci-Fi", "Adult Female", 99);
		System.out.println("  4. " + karneval.title);

		SeinenManga _07Ghost = new SeinenManga(5, "07-Ghost", "Manga", 17, "The students of the Barsburg Empire's Military Academy are trained to become capable fighters who will serve as the empire's newest soldiers. Every year, five hundred students take a graduation exam, where only 20 can pass to become honorable begleiters, a position equivalent to assistant executive officers. Students fight their enemies by using zaiphon, God-given abilities that take different forms depending on the individual.  Teito Klein, the academy chairman's favorite student, is belittled by his peers due to his previous status as a slave. Despite being unable to fully remember his past, Teito's cheerful best friend, Mikage Celestine, stays by his side. After the two undergo the exam, Teito recalls small pieces of a long-forgotten memory, causing him to instinctively attack the chief-of-staff, Ayanami.  Subdued for his insubordination, Teito is imprisoned for further questioning. But with the help of Mikage, Teito escapes to the Barsburg Church in the empire's Seventh District. As he struggles to escape the clutches of the Barsburg Empire's military, he unveils the memories that lie within him and learns about a compelling power that roams in the shadows of the empire. ", "Finished", "Apr 28, 2005 to Aug 28, 2013", "Amemiya, Yuki, Ichihara, Yukino", "Action, Fantasy, Mystery, Sci-Fi", "Adult Female", 100);
		System.out.println("  5. " + _07Ghost.title);

		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			menu();
		} else {

			switch (choice) {
			case 1:
				usagiDrop.josei(usagiDrop);
				break;

			case 2:
				chihayafuru.seinen(chihayafuru);
				break;

			case 3:
				kuragehime.seinen(kuragehime);
				break;

			case 4:
				karneval.seinen(karneval);
				break;

			case 5:
				_07Ghost.seinen(_07Ghost);
				break;

			default:
				System.out.println("| ** ============================================================ ** |");
				System.out.println("| !!             Please enter the correct input.                  !! |");
				System.out.println("| ** ============================================================ ** |");
				shonenManga();
			}
		}
	}
}

class PrivacyProtected {

	private String author;
	private String producers;
	private String licensors;
	private String studios;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public String getLicensors() {
		return licensors;
	}

	public void setLicensors(String licensors) {
		this.licensors = licensors;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

}

//Statistics
class Statistics {

	// anime statistics
	double rating;
	double score;
	double ranked;
	long popularity;
	long members;
	long favorites;

	// anime social sites and fandom
	String official_site;
	String fandom;

	Statistics(double rating, double score, double ranked, long popularity, long members, long favorites,
			String official_site, String watch_online, String fandom) {
		this.rating = rating;
		this.score = score;
		this.ranked = ranked;
		this.popularity = popularity;
		this.members = members;
		this.favorites = favorites;
		this.official_site = official_site;
		this.fandom = fandom;
	}

}

// Manga
class Manga {
	// anime information
	int Id;
	String title;
	String type;
	int volumes;
	String description;
	String status;
	String published;
	double price;
	String source;
	String genre;
	String demographic;
}

//Kodomomuke (children's) --> Sub class
class KodomomukeManga extends Manga {
	// Constructor
	KodomomukeManga(int Id, String title, String type, int volumes, String description, String status, String published,
			String source, String genre, String demographic, double price) {

		// anime information
		this.Id = Id;
		this.title = title;
		this.type = type;
		this.volumes = volumes;
		this.description = description;
		this.status = status;
		this.published = published;
		this.source = source;
		this.genre = genre;
		this.demographic = demographic;
		this.price = price;

	}

	public void details() {
		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println();
		System.out.println("                             ** Details **                            ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  ## Title : " + title);
		System.out.println("  ## Type : " + type);
		System.out.println("  ## volumes : " + volumes);
		System.out.println("  ## Status : " + status);
		System.out.println("  ## Pubished Date : " + published);
		System.out.println("  ## Source : " + source);
		System.out.println("  ## Genre : " + genre);
		System.out.println("  ## Demographic : " + demographic);
		System.out.println("  ## Description : " + description);
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + title + " Manga.");
		System.out.println("  >> The price is " + price + "  ₹ /-");
		System.out.println("  >> Press 0 to exit.");
		System.out.println("  >> Press 1 to place order.");
		System.out.println("  >> Press 99 to go back.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (choice == 1) {
			PaymentSystem.bill = PaymentSystem.bill + price;
		} else if (choice == 99) {
			kodomomuke(null);
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.menu();
		}

		System.out.println("  >> Do you want to choose another Manga?");
		System.out.print("  >> If yes then press Y and for No press N : ");
		char ch = input.next().charAt(0);
		if (ch == 'y' || ch == 'Y') {
			homepage.kodomomuke();
		} else if (ch == 'n' || ch == 'N') {
			paymentSystem.totalBill();
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.kodomomuke();
		}

	}

	public void kodomomuke(KodomomukeManga kodomomuke) {

		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println("                       ** " + kodomomuke.title + " **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + kodomomuke.title);
		System.out.println("  >> Volumes : " + kodomomuke.volumes);
		System.out.println("  >> Pirce : " + kodomomuke.price + " ₹ /-");
		System.out.println("  >> Description : " + kodomomuke.description);
		System.out.println("  >> Press 1 for more details.");
		System.out.println("  >> Press 2 for purchase.");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (1 == choice) {
			System.out.println("                       ** " + kodomomuke.title + " **                        ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			kodomomuke.details();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();
		} else if (2 == choice) {
			System.out.println();
			System.out.println("                        ** Billing Process **                         ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  >> You have selected " + kodomomuke.title + " Manga.");
			System.out.println("  >> The price is " + kodomomuke.price + "  ₹ /-");
			System.out.println("  >> Press 0 to exit.");
			System.out.println("  >> Press 1 to place order.");
			System.out.println("  >> Press 99 to go back.");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.print("  >> Enter your choice : ");
			choice = input.nextInt();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();

			if (choice == 1) {
				PaymentSystem.bill = PaymentSystem.bill + kodomomuke.price;
			} else if (choice == 99) {
				homepage.kodomomuke();
			} else {
				System.out.println("  >> You have selected wrong option.");
				homepage.menu();
			}

			System.out.println("  >> Do you want to choose another Manga?");
			System.out.print("  >> If yes then press Y and for No press N : ");
			char ch = input.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				homepage.kodomomuke();
			} else if (ch == 'n' || ch == 'N') {
				paymentSystem.totalBill();
			} else {
				System.out.println("  >> You have selected wrong option.");
				System.out.println();
				homepage.kodomomuke();
			}
		} else if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			homepage.kodomomuke();
		} else {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| !!             Please enter the correct input.                  !! |");
			System.out.println("| ** ============================================================ ** |");
			kodomomuke(null);
		}
	}

}

//Shonen (Young boys) --> Sub class
class ShonenManga extends Manga {
	// Constructor
	ShonenManga(int Id, String title, String type, int volumes, String description, String status, String published,
			String source, String genre, String demographic, double price) {

		// anime information
		this.Id = Id;
		this.title = title;
		this.type = type;
		this.volumes = volumes;
		this.description = description;
		this.status = status;
		this.published = published;
		this.source = source;
		this.genre = genre;
		this.demographic = demographic;
		this.price = price;

	}

	public void details() {
		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println();
		System.out.println("                             ** Details **                            ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  ## Title : " + title);
		System.out.println("  ## Type : " + type);
		System.out.println("  ## volumes : " + volumes);
		System.out.println("  ## Status : " + status);
		System.out.println("  ## Pubished Date : " + published);
		System.out.println("  ## Source : " + source);
		System.out.println("  ## Genre : " + genre);
		System.out.println("  ## Demographic : " + demographic);
		System.out.println("  ## Description : " + description);
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + title + " Manga.");
		System.out.println("  >> The price is " + price + "  ₹ /-");
		System.out.println("  >> Press 0 to exit.");
		System.out.println("  >> Press 1 to place order.");
		System.out.println("  >> Press 99 to go back.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (choice == 1) {
			PaymentSystem.bill = PaymentSystem.bill + price;
		} else if (choice == 99) {
			shonen(null);
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.menu();
		}

		System.out.println("  >> Do you want to choose another Manga?");
		System.out.print("  >> If yes then press Y and for No press N : ");
		char ch = input.next().charAt(0);
		if (ch == 'y' || ch == 'Y') {
			homepage.shonenManga();
		} else if (ch == 'n' || ch == 'N') {
			paymentSystem.totalBill();
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.shonenManga();
		}

	}

	public void shonen(ShonenManga shonen) {

		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println("                       ** " + shonen.title + " **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + shonen.title);
		System.out.println("  >> Volumes : " + shonen.volumes);
		System.out.println("  >> Pirce : " + shonen.price + " ₹ /-");
		System.out.println("  >> Description : " + shonen.description);
		System.out.println("  >> Press 1 for more details.");
		System.out.println("  >> Press 2 for purchase.");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (1 == choice) {
			System.out.println("                       ** " + shonen.title + " **                        ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			shonen.details();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();
		} else if (2 == choice) {
			System.out.println();
			System.out.println("                        ** Billing Process **                         ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  >> You have selected " + shonen.title + " Manga.");
			System.out.println("  >> The price is " + shonen.price + "  ₹ /-");
			System.out.println("  >> Press 0 to exit.");
			System.out.println("  >> Press 1 to place order.");
			System.out.println("  >> Press 99 to go back.");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.print("  >> Enter your choice : ");
			choice = input.nextInt();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();

			if (choice == 1) {
				PaymentSystem.bill = PaymentSystem.bill + shonen.price;
			} else if (choice == 99) {
				homepage.shonenManga();
			} else {
				System.out.println("  >> You have selected wrong option.");
				homepage.menu();
			}

			System.out.println("  >> Do you want to choose another Manga?");
			System.out.print("  >> If yes then press Y and for No press N : ");
			char ch = input.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				homepage.shonenManga();
			} else if (ch == 'n' || ch == 'N') {
				paymentSystem.totalBill();
			} else {
				System.out.println("  >> You have selected wrong option.");
				homepage.shonenManga();
			}
		} else if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			homepage.shonenManga();
		} else {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| !!             Please enter the correct input.                  !! |");
			System.out.println("| ** ============================================================ ** |");
			shonen(null);
		}
	}

}

//Shojo (Young girls) --> Sub class
class ShojoManga extends Manga {
	// Constructor
	ShojoManga(int Id, String title, String type, int volumes, String description, String status, String published,
			String source, String genre, String demographic, double price) {

		// anime information
		this.Id = Id;
		this.title = title;
		this.type = type;
		this.volumes = volumes;
		this.description = description;
		this.status = status;
		this.published = published;
		this.source = source;
		this.genre = genre;
		this.demographic = demographic;
		this.price = price;

	}

	public void details() {
		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println();
		System.out.println("                             ** Details **                            ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  ## Title : " + title);
		System.out.println("  ## Type : " + type);
		System.out.println("  ## volumes : " + volumes);
		System.out.println("  ## Status : " + status);
		System.out.println("  ## Pubished Date : " + published);
		System.out.println("  ## Source : " + source);
		System.out.println("  ## Genre : " + genre);
		System.out.println("  ## Demographic : " + demographic);
		System.out.println("  ## Description : " + description);
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + title + " Manga.");
		System.out.println("  >> The price is " + price + "  ₹ /-");
		System.out.println("  >> Press 0 to exit.");
		System.out.println("  >> Press 1 to place order.");
		System.out.println("  >> Press 99 to go back.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (choice == 1) {
			PaymentSystem.bill = PaymentSystem.bill + price;
		} else if (choice == 99) {
			shojo(null);
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.menu();
		}

		System.out.println("  >> Do you want to choose another Manga?");
		System.out.print("  >> If yes then press Y and for No press N : ");
		char ch = input.next().charAt(0);
		if (ch == 'y' || ch == 'Y') {
			homepage.shojoManga();
		} else if (ch == 'n' || ch == 'N') {
			paymentSystem.totalBill();
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.shojoManga();
		}

	}

	public void shojo(ShojoManga shojo) {

		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println("                       ** " + shojo.title + " **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + shojo.title);
		System.out.println("  >> Volumes : " + shojo.volumes);
		System.out.println("  >> Pirce : " + shojo.price + " ₹ /-");
		System.out.println("  >> Description : " + shojo.description);
		System.out.println("  >> Press 1 for more details.");
		System.out.println("  >> Press 2 for purchase.");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (1 == choice) {
			System.out.println("                       ** " + shojo.title + " **                        ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			shojo.details();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();
		} else if (2 == choice) {
			System.out.println();
			System.out.println("                        ** Billing Process **                         ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  >> You have selected " + shojo.title + " Manga.");
			System.out.println("  >> The price is " + shojo.price + "  ₹ /-");
			System.out.println("  >> Press 0 to exit.");
			System.out.println("  >> Press 1 to place order.");
			System.out.println("  >> Press 99 to go back.");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.print("  >> Enter your choice : ");
			choice = input.nextInt();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();

			if (choice == 1) {
				PaymentSystem.bill = PaymentSystem.bill + shojo.price;
			} else if (choice == 99) {
				homepage.shojoManga();
			} else {
				System.out.println("  >> You have selected wrong option.");
				homepage.menu();
			}

			System.out.println("  >> Do you want to choose another Manga?");
			System.out.print("  >> If yes then press Y and for No press N : ");
			char ch = input.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				homepage.shojoManga();
			} else if (ch == 'n' || ch == 'N') {
				paymentSystem.totalBill();
			} else {
				System.out.println("  >> You have selected wrong option.");
				homepage.shojoManga();
			}
		} else if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			homepage.shojoManga();
		} else {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| !!             Please enter the correct input.                  !! |");
			System.out.println("| ** ============================================================ ** |");
			shojo(null);
		}
	}

}

//Seinen (adult male) --> Sub class
class SeinenManga extends Manga {
	// Constructor
	SeinenManga(int Id, String title, String type, int volumes, String description, String status, String published,
			String source, String genre, String demographic, double price) {

		// anime information
		this.Id = Id;
		this.title = title;
		this.type = type;
		this.volumes = volumes;
		this.description = description;
		this.status = status;
		this.published = published;
		this.source = source;
		this.genre = genre;
		this.demographic = demographic;
		this.price = price;
	}

	public void details() {
		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println();
		System.out.println("                             ** Details **                            ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  ## Title : " + title);
		System.out.println("  ## Type : " + type);
		System.out.println("  ## volumes : " + volumes);
		System.out.println("  ## Status : " + status);
		System.out.println("  ## Pubished Date : " + published);
		System.out.println("  ## Source : " + source);
		System.out.println("  ## Genre : " + genre);
		System.out.println("  ## Demographic : " + demographic);
		System.out.println("  ## Description : " + description);
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + title + " Manga.");
		System.out.println("  >> The price is " + price + "  ₹ /-");
		System.out.println("  >> Press 0 to exit.");
		System.out.println("  >> Press 1 to place order.");
		System.out.println("  >> Press 99 to go back.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (choice == 1) {
			PaymentSystem.bill = PaymentSystem.bill + price;
		} else if (choice == 99) {
			seinen(null);
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.menu();
		}

		System.out.println("  >> Do you want to choose another Manga?");
		System.out.print("  >> If yes then press Y and for No press N : ");
		char ch = input.next().charAt(0);
		if (ch == 'y' || ch == 'Y') {
			homepage.seinenManga();
		} else if (ch == 'n' || ch == 'N') {
			paymentSystem.totalBill();
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.seinenManga();
		}

	}

	public void seinen(SeinenManga seinen) {

		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println("                       ** " + seinen.title + " **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + seinen.title);
		System.out.println("  >> Volumes : " + seinen.volumes);
		System.out.println("  >> Pirce : " + seinen.price + " ₹ /-");
		System.out.println("  >> Description : " + seinen.description);
		System.out.println("  >> Press 1 for more details.");
		System.out.println("  >> Press 2 for purchase.");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (1 == choice) {
			System.out.println("                       ** " + seinen.title + " **                        ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			seinen.details();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();
		} else if (2 == choice) {
			System.out.println();
			System.out.println("                        ** Billing Process **                         ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  >> You have selected " + seinen.title + " Manga.");
			System.out.println("  >> The price is " + seinen.price + "  ₹ /-");
			System.out.println("  >> Press 0 to exit.");
			System.out.println("  >> Press 1 to place order.");
			System.out.println("  >> Press 99 to go back.");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.print("  >> Enter your choice : ");
			choice = input.nextInt();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();

			if (choice == 1) {
				PaymentSystem.bill = PaymentSystem.bill + seinen.price;
			} else if (choice == 99) {
				homepage.seinenManga();
			} else {
				System.out.println("  >> You have selected wrong option.");
				homepage.menu();
			}

			System.out.println("  >> Do you want to choose another Manga?");
			System.out.print("  >> If yes then press Y and for No press N : ");
			char ch = input.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				homepage.seinenManga();
			} else if (ch == 'n' || ch == 'N') {
				paymentSystem.totalBill();
			} else {
				System.out.println("  >> You have selected wrong option.");
				homepage.seinenManga();
			}
		} else if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			homepage.seinenManga();
		} else {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| !!             Please enter the correct input.                  !! |");
			System.out.println("| ** ============================================================ ** |");
			seinen(null);
		}
	}

}

//Josei (adult female) --> Sub class
class JoseiManga extends Manga {
	// Constructor
	JoseiManga(int Id, String title, String type, int volumes, String description, String status, String published,
			String source, String genre, String demographic, double price) {

		// anime information
		this.Id = Id;
		this.title = title;
		this.type = type;
		this.volumes = volumes;
		this.description = description;
		this.status = status;
		this.published = published;
		this.source = source;
		this.genre = genre;
		this.demographic = demographic;
		this.price = price;

	}

	public void details() {
		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println();
		System.out.println("                             ** Details **                            ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  ## Title : " + title);
		System.out.println("  ## Type : " + type);
		System.out.println("  ## volumes : " + volumes);
		System.out.println("  ## Status : " + status);
		System.out.println("  ## Pubished Date : " + published);
		System.out.println("  ## Source : " + source);
		System.out.println("  ## Genre : " + genre);
		System.out.println("  ## Demographic : " + demographic);
		System.out.println("  ## Description : " + description);
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + title + " Manga.");
		System.out.println("  >> The price is " + price + "  ₹ /-");
		System.out.println("  >> Press 0 to exit.");
		System.out.println("  >> Press 1 to place order.");
		System.out.println("  >> Press 99 to go back.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (choice == 1) {
			PaymentSystem.bill = PaymentSystem.bill + price;
		} else if (choice == 99) {
			josei(null);
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.menu();
		}

		System.out.println("  >> Do you want to choose another Manga?");
		System.out.print("  >> If yes then press Y and for No press N : ");
		char ch = input.next().charAt(0);
		if (ch == 'y' || ch == 'Y') {
			homepage.joseiManga();
		} else if (ch == 'n' || ch == 'N') {
			paymentSystem.totalBill();
		} else {
			System.out.println("  >> You have selected wrong option.");
			homepage.joseiManga();
		}

	}

	public void josei(JoseiManga josei) {

		Scanner input = new Scanner(System.in);
		HomePage homepage = new HomePage();
		BillingSystem paymentSystem = new PaymentSystem();

		System.out.println("                       ** " + josei.title + " **                        ");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> You have selected " + josei.title);
		System.out.println("  >> Volumes : " + josei.volumes);
		System.out.println("  >> Pirce : " + josei.price + " ₹ /-");
		System.out.println("  >> Description : " + josei.description);
		System.out.println("  >> Press 1 for more details.");
		System.out.println("  >> Press 2 to buy.");
		System.out.println("  >> Press 0 for exit.");
		System.out.println("  >> Press 99 for previous menu.");
		System.out.println();
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice : ");
		int choice = input.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		if (1 == choice) {
			System.out.println("                       ** " + josei.title + " **                        ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			josei.details();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();
		} else if (2 == choice) {
			System.out.println();
			System.out.println("                        ** Billing Process **                         ");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.println("  >> You have selected " + josei.title + " Manga.");
			System.out.println("  >> The price is " + josei.price + "  ₹ /-");
			System.out.println("  >> Press 0 to exit.");
			System.out.println("  >> Press 1 to place order.");
			System.out.println("  >> Press 99 to go back.");
			System.out.println();
			System.out.println("| ** ============================================================ ** |");
			System.out.print("  >> Enter your choice : ");
			choice = input.nextInt();
			System.out.println("| ** ============================================================ ** |");
			System.out.println();

			if (choice == 1) {
				PaymentSystem.bill = PaymentSystem.bill + josei.price;
			} else if (choice == 99) {
				homepage.joseiManga();
			} else {
				System.out.println("  >> You have selected wrong option.");
				homepage.menu();
			}

			System.out.println("  >> Do you want to choose another Manga?");
			System.out.print("  >> If yes then press Y and for No press N : ");
			char ch = input.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				homepage.joseiManga();
			} else if (ch == 'n' || ch == 'N') {
				paymentSystem.totalBill();
			} else {
				System.out.println("  >> You have selected wrong option.");
				homepage.joseiManga();
			}

		} else if (0 == choice) {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
		} else if (99 == choice) {
			homepage.joseiManga();
		} else {
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| !!             Please enter the correct input.                  !! |");
			System.out.println("| ** ============================================================ ** |");
			josei(null);
		}
	}
}

public class MangaShop {

	// SIB - Static Initializer Block
	static {
		System.out.println("| ** ============================================================ ** |");
		System.out.println("| <<                     Welcome to AniShop                       >> |");
		System.out.println("| ** ============================================================ ** |");
	}

	// registration method
	public static void registration(Scanner scr) {

		System.out.println("  **                    Registration Process                      **  ");
		System.out.println("| ** ============================================================ ** |");
		System.out.println("  >> Press 1 for Signup.");
		System.out.println("  >> Press 2 for Login.");
		System.out.println("  >> Press 0 for Exit.");
		System.out.println("| ** ============================================================ ** |");
		System.out.print("  >> Enter your choice: ");
		int choice = scr.nextInt();
		System.out.println("| ** ============================================================ ** |");
		System.out.println();

		WelcomePage registration = new Registration(); // UpCasting.

		switch (choice) {
		case 1:
			registration.signup();
			break;
		case 2:
			registration.login();
			break;
		case 0:
			System.out.println("| ** ============================================================ ** |");
			System.out.println("| <<                       See you again.                         >> |");
			System.out.println("| ** ============================================================ ** |");
			System.exit(0);
			break;
		default:
			System.out.println("                 !! Please enter the correct input. !!                ");
			registration(new Scanner(System.in));
		}

	}

	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);

		registration(scr);

		HomePage home = new HomePage();
		home.menu();

	}

}
