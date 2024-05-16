import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String[][] adminUserNameAndPassword = new String[10][2];
    private static List<Customer> customersCollection = new ArrayList<>();

    public static void main(String[] args) {
        int countNumOfUsers = 1;
        RolesAndPermissions r1 = new RolesAndPermissions();
        Room room = new Room();
        RoomReservation bookingAndReserving = new RoomReservation();
        Customer c1 = new Customer();
        room.roomScheduler();


        welcomeScreen(1);
        System.out.println("\n\t\t\t\t\t+++++++++++++ Welcome +++++++++++++\n\nTo Further Proceed, Please enter a value.");
        System.out.println("\n***** Default Username && Password is root-root ***** Using Default Credentials will restrict you to just view the list of Customers....\n");
        displayMainMenu();
        Scanner read = new Scanner(System.in);
        int desiredOption = read.nextInt();
        while (desiredOption < 0 || desiredOption > 8) {
            System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
            desiredOption = read.nextInt();
        }

        do {
            Scanner read1 = new Scanner(System.in);
            if (desiredOption == 1) {

                adminUserNameAndPassword[0][0] = "root";
                adminUserNameAndPassword[0][1] = "root";
                printArtWork(1);
                System.out.print("\nEnter the UserName to login to the Management System :     ");
                String username = read1.nextLine();
                System.out.print("Enter the Password to login to the Management System :    ");
                String password = read1.nextLine();
                System.out.println();

                if (r1.isPrivilegedUserOrNot(username, password) == -1) {
                    System.out.printf("\n%20sERROR!!! Unable to login Cannot find user with the entered credentials.... Try Creating New Credentials or get yourself register by pressing 4....\n", "");
                } else if (r1.isPrivilegedUserOrNot(username, password) == 0) {
                    System.out.println("You've standard/default privileges to access the data... You can just view customers data..." + "Can't perform any actions on them....");
                    c1.displayCustomersData(true);
                } else {
                    System.out.printf("%-20sLogged in Successfully as \"%s\"..... For further Proceedings, enter a value from below....", "", username);

                    do {
                        System.out.printf("\n\n%-60s+++++++++ 2nd Layer Menu +++++++++%50sLogged in as \"%s\"\n", "", "", username);
                        System.out.printf("%-30s (a) Enter 1 to add new Customer....\n", "");
                        System.out.printf("%-30s (b) Enter 2 to search a Customer (with User ID)....\n", "");
                        System.out.printf("%-30s (b) Enter 3 to search a Customer (with User Name)....\n", "");
                        System.out.printf("%-30s (c) Enter 4 to update the Data of the Customer....\n", "");
                        System.out.printf("%-30s (d) Enter 5 to delete a Customer....\n", "");
                        System.out.printf("%-30s (e) Enter 6 to Display all Customer....\n", "");
                        System.out.printf("%-30s (f) Enter 7 to Display all rooms registered by a Customer...\n", "");//
                        System.out.printf("%-30s (g) Enter 0 to Go back to the Main Menu/Logout....\n", "");
                        System.out.print("Enter the desired Choice :   ");
                        Scanner r = new Scanner(System.in);
                        desiredOption = r.nextInt();
                        if (desiredOption == 1) {
                            c1.displayArtWork(1);
                            c1.addNewCustomer();
                        } else if (desiredOption == 2) {
                            c1.displayArtWork(2);
                            System.out.print("Enter the CustomerID to Search :\t");
                            int customerID = read1.nextInt();
                            System.out.println();
                            c1.searchUser(customerID);
                        } else if (desiredOption == 3) {
                            c1.displayArtWork(2);
                            System.out.print("Enter the Customer Name to Search :\t");
                            String customerID = read1.nextLine();
                            System.out.println();
                            c1.searchUser(customerID);
                        } else if (desiredOption == 4) {
                            bookingAndReserving.displayArtWork(2);
                            c1.displayCustomersData(false);
                            System.out.print("Enter the CustomerID to Update its Data :\t");
                            int customerID = read1.nextInt();
                            if (customersCollection.size() > 0) {
                                c1.editUserInfo(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }
                        } else if (desiredOption == 5) {
                            bookingAndReserving.displayArtWork(3);
                            c1.displayCustomersData(false);
                            System.out.print("Enter the CustomerID to Delete its Data :\t");
                            int customerID = read1.nextInt();
                            if (customersCollection.size() > 0) {
                                c1.deleteUser(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }
                        } else if (desiredOption == 6) {
                            c1.displayArtWork(3);
                            c1.displayCustomersData(false);
                        } else if (desiredOption == 7) {
                            bookingAndReserving.displayArtWork(5);
                            System.out.print("\n\nEnter the ID of the user to display all rooms registered by that user...");
                            int id = read1.nextInt();
                            bookingAndReserving.displayRoomsRegisteredByOneUser(id);
                        } else if (desiredOption == 0) {
                            bookingAndReserving.displayArtWork(22);
                            System.out.println("Thanks for Using...!!!");

                        } else {
                            System.out.println("Invalid Choice...Looks like you're Robot...Entering values randomly...You've Have to login again...");
                            bookingAndReserving.displayArtWork(22);
                            desiredOption = 0;
                        }

                    } while (desiredOption != 0);
                }
            } else if (desiredOption == 2) {
                printArtWork(2);
                System.out.print("\nEnter the UserName to Register :    ");
                String username = read1.nextLine();
                System.out.print("Enter the Password to Register :     ");
                String password = read1.nextLine();
                while (r1.isPrivilegedUserOrNot(username, password) != -1) {
                    System.out.print("ERROR!!! Admin with same UserName already exist. Enter new UserName:   ");
                    username = read1.nextLine();
                    System.out.print("Enter the Password Again:   ");
                    password = read1.nextLine();
                }

                adminUserNameAndPassword[countNumOfUsers][0] = username;
                adminUserNameAndPassword[countNumOfUsers][1] = password;

                countNumOfUsers++;
            } else if (desiredOption == 3) {
                printArtWork(3);
                System.out.print("\n\nEnter the Email to Login : \t");
                String userName = read1.nextLine();
                System.out.print("Enter the Password : \t");
                String password = read1.nextLine();
                String[] result = r1.isCustomerRegistered(userName, password).split("-");

                if (Integer.parseInt(result[0]) == 1) {
                    int desiredChoice;
                    System.out.printf("\n\n%-20sLogged in Successfully as \"%s\"..... For further Proceedings, enter a value from below....", "", userName);
                    do {
                        System.out.printf("\n\n%-60s+++++++++ 3rd Layer Menu +++++++++%50sLogged in as \"%s\"\n", "", "", userName);
                        System.out.printf("%-40s (a) Enter 1 to Book a room....\n", "");
                        System.out.printf("%-40s (b) Enter 2 to update your Data....\n", "");
                        System.out.printf("%-40s (c) Enter 3 to delete your account....\n", "");
                        System.out.printf("%-40s (d) Enter 4 to Cancel a Room....\n", "");//
                        System.out.printf("%-40s (e) Enter 5 to Display all room registered by you....\n", "");//
                        System.out.printf("%-40s (f) Enter 0 to Go back to the Main Menu/Logout....\n", "");
                        System.out.print("Enter the desired Choice :   ");
                        desiredChoice = read.nextInt();
                        if (desiredChoice == 1) {
                            bookingAndReserving.displayArtWork(1);
                            room.displayRoom();
                            System.out.print("\nEnter the desired room number to book :\t ");
                            int roomToBeBooked = read1.nextInt();
                            bookingAndReserving.bookRoom(roomToBeBooked, Integer.parseInt(result[1]));
                        } else if (desiredChoice == 2) {
                            bookingAndReserving.displayArtWork(2);
                            c1.editUserInfo(Integer.parseInt(result[1]));
                        } else if (desiredChoice == 3) {
                            bookingAndReserving.displayArtWork(3);
                            System.out.print("Are you sure to delete your account...It's an irreversible action...Enter Y/y to confirm...\t\t");
                            char confirmationChar = read1.nextLine().charAt(0);
                            if (confirmationChar == 'Y' || confirmationChar == 'y') {
                                c1.deleteUser(Integer.parseInt(result[1]));
                                System.out.printf("User %s's account deleted Successfully...!!!", userName);
                                desiredChoice = 0;
                            } else {
                                System.out.println("Action has been cancelled...");
                            }
                        } else if (desiredChoice == 4) {
                            bookingAndReserving.displayArtWork(5);
                            bookingAndReserving.cancelRoom(Integer.parseInt(result[1]));
                        } else if (desiredChoice == 5) {
                            bookingAndReserving.displayArtWork(6);
                            bookingAndReserving.displayRoomsRegisteredByOneUser(Integer.parseInt(result[1]));
                        } else {
                            bookingAndReserving.displayArtWork(7);
                            if (desiredChoice != 0) {
                                System.out.println("Invalid Choice...Looks like you're Robot...Entering values randomly...You've Have to login again...");
                            }
                            desiredChoice = 0;
                        }
                    } while (desiredChoice != 0);

                } else {
                    System.out.printf("\n%20sERROR!!! Unable to login Cannot find user with the entered credentials.... Try Creating New Credentials or get yourself register by pressing 4....\n", "");
                }
            } else if (desiredOption == 4) {
                printArtWork(4);
                c1.addNewCustomer();
            } else if (desiredOption == 5) {
                printArtWork(5);
                manualInstructions();
            }
            
            displayMainMenu();
            desiredOption = read1.nextInt();
            while (desiredOption < 0 || desiredOption > 8) {
            System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
            desiredOption = read1.nextInt();
            }
        } while (desiredOption != 0);
        welcomeScreen(-1);
    }

    static void displayMainMenu() {
        System.out.println("\n\n\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login as admin.");
        System.out.println("\t\t(c) Press 2 to Register as admin.");
        System.out.println("\t\t(e) Press 3 to Login as customer");       
        System.out.println("\t\t(d) Press 4 to Register as customer");
        System.out.println("\t\t(d) Press 5 to Display the User Manual.");
        System.out.print("\t\tEnter the desired option:    ");
    }

    static void manualInstructions() {
        Scanner read = new Scanner(System.in);
        System.out.printf("%n%n%50s %s User Manual %s", "", "+++++++++++++++++", "+++++++++++++++++");
        System.out.println("\n\n\t\t(a) Press 1 to display Admin Manual.");
        System.out.println("\t\t(b) Press 2 to display User Manual.");
        System.out.print("\nEnter the desired option :    ");
        int choice = read.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.print("ERROR!!! Invalid entry...Please enter a value either 1 or 2....Enter again....");
            choice = read.nextInt();
        }
        if (choice == 1) {
            System.out.println("\n\n(1) Admin have the access to all users data...Admin can delete, update, add and can perform search for any customer...\n");
            System.out.println("(2) In order to access the admin module, you've to get yourself register by pressing 2, when the main menu gets displayed...\n");
            System.out.println("(3) Provide the required details...Once you've registered yourself, press 1 to login as an admin... \n");
            System.out.println("(4) Once you've logged in, 2nd layer menu will be displayed on the screen...From here on, you can select from variety of options...\n");
            System.out.println("(5) Pressing \"1\" will add a new Customer, provide the program with required details to add the customer...\n");
            System.out.println("(6) Pressing \"2\" will search for any Customer with USER ID, given the admin(you) provides the ID from the table printing above....  \n");
            System.out.println("(6) Pressing \"3\" will search for any Customer with USER NAME, given the admin(you) provides the ID from the table printing above....  \n");
            System.out.println("(7) Pressing \"4\" will let you update any customer data given the user ID provided to program...\n");
            System.out.println("(8) Pressing \"5\" will let you delete any customer given its ID provided...\n");
            System.out.println("(9) Pressing \"6\" will let you display all registered customer...\n");
            System.out.println("(10) Pressing \"7\" will let you display all registered rooms under a customer...\n");
            System.out.println("(11) Pressing \"0\" will make you logged out of the program...You can login again any time you want during the program execution....\n");
        } else {
            System.out.println("\n\n(1) Local user has the access to its data only...He/She won't be able to change/update other users data...\n");
            System.out.println("(2) In order to access local users benefits, you've to get yourself register by pressing 3, when the main menu gets displayed...\n");
            System.out.println("(3) Provide the details asked by the program to add you to the users list...Once you've registered yourself, press \"4\" to login as a customer...\n");
            System.out.println("(4) Once you've logged in, 3rd layer menu will be displayed...\n");
            System.out.println("(5) Pressing \"1\" will let you book a room from the available room list...\n");
            System.out.println("(7) Pressing \"2\" will let you update your own data...You won't be able to update other's data... \n");
            System.out.println("(8) Pressing \"3\" will delete your account... \n");
            System.out.println("(10) Pressing \"4\" will let you cancel any room registered by you...\n");
            System.out.println("(11) Pressing \"5\" will display all rooms registered by you...\n");
            System.out.println("(12) Pressing \"0\" will make you logout of the program...You can login back at anytime with your credentials...for this particular run-time... \n");
        }
    }
    
    static void welcomeScreen(int option) {
        String artWork = "";

        if (option == 1) {
            artWork = """

                    888       888          888                                               \s
                    888   o   888          888                                               \s
                    888  d8b  888          888                                               \s
                    888 d888b 888  .d88b.  888  .d8888b  .d88b.  88888b.d88b.   .d88b.       \s
                    888d88888b888 d8P  Y8b 888 d88P"    d88""88b 888 "888 "88b d8P  Y8b      \s
                    88888P Y88888 88888888 888 888      888  888 888  888  888 88888888      \s
                    8888P   Y8888 Y8b.     888 Y88b.    Y88..88P 888  888  888 Y8b.          \s
                    888P     Y888  "Y8888  888  "Y8888P  "Y88P"  888  888  888  "Y8888       \s
                                                                                             \s
                                                                                             \s
                                                                                             \s
                    """;
        }
        System.out.println(artWork);
    }

    static void printArtWork(int option) {
        String artWork;
        if (option == 4) {
            artWork = """

                     .o88b. db    db .d8888. d888888b  .d88b.  .88b  d88. d88888b d8888b.      .d8888. d888888b  d888b  d8b   db db    db d8888b.\s
                    d8P  Y8 88    88 88'  YP `~~88~~' .8P  Y8. 88'YbdP`88 88'     88  `8D      88'  YP   `88'   88' Y8b 888o  88 88    88 88  `8D\s
                    8P      88    88 `8bo.      88    88    88 88  88  88 88ooooo 88oobY'      `8bo.      88    88      88V8o 88 88    88 88oodD'\s
                    8b      88    88   `Y8b.    88    88    88 88  88  88 88~~~~~ 88`8b          `Y8b.    88    88  ooo 88 V8o88 88    88 88~~~  \s
                    Y8b  d8 88b  d88 db   8D    88    `8b  d8' 88  88  88 88.     88 `88.      db   8D   .88.   88. ~8~ 88  V888 88b  d88 88     \s
                     `Y88P' ~Y8888P' `8888Y'    YP     `Y88P'  YP  YP  YP Y88888P 88   YD      `8888Y' Y888888P  Y888P  VP   V8P ~Y8888P' 88     \s
                                                                                                                                                 \s
                                                                                                                                                 \s
                    """;
            } else if (option == 5) {
                artWork = """

                    db    db .d8888. d88888b d8888b.      .88b  d88.  .d8b.  d8b   db db    db  .d8b.  db     \s
                    88    88 88'  YP 88'     88  `8D      88'YbdP'88 d8' `8b 888o  88 88    88 d8' `8b 88     \s
                    88    88 `8bo.   88ooooo 88oobY'      88  88  88 88ooo88 88V8o 88 88    88 88ooo88 88     \s
                    88    88   `Y8b. 88~~~~~ 88`8b        88  88  88 88~~~88 88 V8o88 88    88 88~~~88 88     \s
                    88b  d88 db   8D 88.     88 `88.      88  88  88 88   88 88  V888 88b  d88 88   88 88booo.\s
                    ~Y8888P' `8888Y' Y88888P 88   YD      YP  YP  YP YP   YP VP   V8P ~Y8888P' YP   YP Y88888P\s
                                                                                                                                                \s
                                                                                                                                                \s
                   """;
            } else if (option == 3) {
            artWork = """

                     .o88b. db    db .d8888. d888888b  .d88b.  .88b  d88. d88888b d8888b.      db       .d88b.   d888b  d888888b d8b   db\s
                    d8P  Y8 88    88 88'  YP `~~88~~' .8P  Y8. 88'YbdP`88 88'     88  `8D      88      .8P  Y8. 88' Y8b   `88'   888o  88\s
                    8P      88    88 `8bo.      88    88    88 88  88  88 88ooooo 88oobY'      88      88    88 88         88    88V8o 88\s
                    8b      88    88   `Y8b.    88    88    88 88  88  88 88~~~~~ 88`8b        88      88    88 88  ooo    88    88 V8o88\s
                    Y8b  d8 88b  d88 db   8D    88    `8b  d8' 88  88  88 88.     88 `88.      88booo. `8b  d8' 88. ~8~   .88.   88  V888\s
                     `Y88P' ~Y8888P' `8888Y'    YP     `Y88P'  YP  YP  YP Y88888P 88   YD      Y88888P  `Y88P'   Y888P  Y888888P VP   V8P\s
                                                                                                                                         \s
                                                                                                                                         \s
                    """;
        } else if (option == 2) {
            artWork = """

                     .d8b.  d8888b. .88b  d88. d888888b d8b   db      .d8888. d888888b  d888b  d8b   db db    db d8888b.\s
                    d8' `8b 88  `8D 88'YbdP`88   `88'   888o  88      88'  YP   `88'   88' Y8b 888o  88 88    88 88  `8D\s
                    88ooo88 88   88 88  88  88    88    88V8o 88      `8bo.      88    88      88V8o 88 88    88 88oodD'\s
                    88~~~88 88   88 88  88  88    88    88 V8o88        `Y8b.    88    88  ooo 88 V8o88 88    88 88~~~  \s
                    88   88 88  .8D 88  88  88   .88.   88  V888      db   8D   .88.   88. ~8~ 88  V888 88b  d88 88     \s
                    YP   YP Y8888D' YP  YP  YP Y888888P VP   V8P      `8888Y' Y888888P  Y888P  VP   V8P ~Y8888P' 88     \s
                                                                                                                        \s
                                                                                                                        \s
                        \s""";
        } else {
            artWork = """

                     .d8b.  d8888b. .88b  d88. d888888b d8b   db      db       .d88b.   d888b  d888888b d8b   db\s
                    d8' `8b 88  `8D 88'YbdP`88   `88'   888o  88      88      .8P  Y8. 88' Y8b   `88'   888o  88\s
                    88ooo88 88   88 88  88  88    88    88V8o 88      88      88    88 88         88    88V8o 88\s
                    88~~~88 88   88 88  88  88    88    88 V8o88      88      88    88 88  ooo    88    88 V8o88\s
                    88   88 88  .8D 88  88  88   .88.   88  V888      88booo. `8b  d8' 88. ~8~   .88.   88  V888\s
                    YP   YP Y8888D' YP  YP  YP Y888888P VP   V8P      Y88888P  `Y88P'   Y888P  Y888888P VP   V8P\s
                                                                                                                \s
                                                                                                                \s
                    """;
        }

        System.out.println(artWork);
    }

    public static List<Customer> getCustomersCollection() {
        return customersCollection;
    }
}