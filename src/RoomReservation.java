import java.util.List;
import java.util.Scanner;

public class RoomReservation implements DisplayClass{
    Room room = new Room();
    int roomIndexInRoomList;

    void bookRoom(int roomNumber, int userID) {
        boolean isFound = false;
        for (Room r1 : room.getRoomList()) {
            if (roomNumber == r1.getRoomNumber()) {
                for (Customer customer : Customer.customerCollection) {
                    if (userID == customer.getUserID()) {
                        isFound = true;
                        if (room.checkRoomStatus(roomNumber)) {
                            if(!room.isCustomerAlreadyAdded(room.getListOfRegisteredCustomers(), customer)) {
                                room.addNewCustomer(customer);
                                customer.addNewRoomToCustomerList(r1);
                                room.changeRoomStatus(roomNumber, "false");
                                System.out.printf("\n %50s You've booked %d room", "", roomNumber);

                            }
                        }
                        else {
                            System.out.println("Room is not available now...!");
                        }
                    break;
                    }
                }
            }
        }
        if (!isFound) {
            System.out.println("Invalid Room Number...! No room with the  ID \"" + roomNumber + "\" was found...");
        }
    }

    int roomIndex(List<Room> roomList, Room room) {
        int i = -1;
        for (Room r1 : roomList) {
            if (r1.equals(room)) {
                i = roomList.indexOf(r1);
            }
        }
        return i;
    }

    boolean isRoomAlreadyAddedToCustomerList(List<Room> roomList, Room room) {
        boolean addedOrNot = false;
        for (Room r1 : roomList) {
            if (r1.getRoomNumber() == room.getRoomNumber()) {
                this.roomIndexInRoomList = roomList.indexOf(r1);
                addedOrNot = true;
                break;
            }
        }
        return addedOrNot;
    }

    void cancelRoom(int userID) {
        int roomNum = 0;
        Scanner read = new Scanner(System.in);
        boolean isFound = false;
    
        for (Customer customer : Customer.customerCollection) {
            if (userID == customer.getUserID()) {
                if (customer.getRoomsRegisteredByUser().size() != 0) {
                    System.out.printf("%50s %s Here is the list of all the Rooms registered by you %s", " ", "++++++++++++++", "++++++++++++++");
                    displayRoomsRegisteredByOneUser(userID);
                    System.out.print("Enter the Room Number of the Room you want to cancel : ");
                    roomNum = read.nextInt();
                    for (Room room : customer.getRoomsRegisteredByUser()) {
                        if (room.getRoomNumber() == roomNum) {
                            isFound = true;
                            customer.removeRoom(room);
                            System.out.println("Room with Room Number \"" + roomNum + "\" has been cancelled successfully.");
                            break;
                        }
                        else {
                            System.out.println("ERROR!!! Couldn't find Room with Room Number \"" + roomNum + "\".....");
                        }
                    }
                } else {
                    System.out.println("No Room has been registered by you with ID \"" + userID + "\".....");
                }
            }
        }    
    }
    
    public String toString(int index, Room room, Customer customer) {
        return String.format("| %-4d | %-11d | %-7s |", index, customer.getUserID(), room.getRoomNumber());
    }
    
    
    @Override
    public void displayRegisteredUsersForAllRoom() {

    }

    @Override
    public void displayRegisteredUserForASpecificRoom(String flightNum) {

    }

    @Override
    public void displayHeaderForUsers(Room room, List<Customer> c) {

    }

    @Override
    public void displayRoomsRegisteredByOneUser(int userID) {
        System.out.println();
        System.out.print("+------+-------------+---------+\n");
        System.out.printf("| Num  | CUSTOMER ID | ROOM NO |%n");
        System.out.print("+------+-------------+---------+\n");
        for (Customer customer : Customer.customerCollection) {
            List<Room> r = customer.getRoomsRegisteredByUser();
            int size = customer.getRoomsRegisteredByUser().size();
            if (userID == customer.getUserID()) {
                for (int i = 0; i < size; i++) {
                    System.out.println(toString((i + 1), r.get(i), customer));
                    System.out.print("+------+-------------+---------+\n");
                }
            }
        }
    }

    @Override
    public void displayArtWork(int option) {
        String artWork;
        if (option == 1) {
            artWork = """
                                        
                    d8888b.  .d88b.   .d88b.  db   dD      d8888b.  .d88b.   .d88b.  .88b  d88.\s
                    88  `8D .8P  Y8. .8P  Y8. 88 ,8P'      88  `8D .8P  Y8. .8P  Y8. 88'YbdP`88\s
                    88oooY' 88    88 88    88 88,8P        88oobY' 88    88 88    88 88  88  88\s
                    88~~~b. 88    88 88    88 88`8b        88`8b   88    88 88    88 88  88  88\s
                    88   8D `8b  d8' `8b  d8' 88 `88.      88 `88. `8b  d8' `8b  d8' 88  88  88\s
                    Y8888P'  `Y88P'   `Y88P'  YP   YD      88   YD  `Y88P'   `Y88P'  YP  YP  YP\s
                                                                                                            \s
                                                                                                            \s
                    """;
        } else if (option == 2) {
            artWork = """
                                        
                    d88888b d8888b. d888888b d888888b      d888888b d8b   db d88888b  .d88b. \s
                    88'     88  `8D   `88'   `~~88~~'        `88'   888o  88 88'     .8P  Y8.\s
                    88ooooo 88   88    88       88            88    88V8o 88 88ooo   88    88\s
                    88~~~~~ 88   88    88       88            88    88 V8o88 88~~~   88    88\s
                    88.     88  .8D   .88.      88           .88.   88  V888 88      `8b  d8'\s
                    Y88888P Y8888D' Y888888P    YP         Y888888P VP   V8P YP       `Y88P' \s
                                                                                             \s
                                                                                             \s
                    """;
        } else if (option == 3) {
            artWork = """
                                        
                    d8888b. d88888b db      d88888b d888888b d88888b       .d8b.   .o88b.  .o88b.  .d88b.  db    db d8b   db d888888b\s
                    88  `8D 88'     88      88'     `~~88~~' 88'          d8' `8b d8P  Y8 d8P  Y8 .8P  Y8. 88    88 888o  88 `~~88~~'\s
                    88   88 88ooooo 88      88ooooo    88    88ooooo      88ooo88 8P      8P      88    88 88    88 88V8o 88    88   \s
                    88   88 88~~~~~ 88      88~~~~~    88    88~~~~~      88~~~88 8b      8b      88    88 88    88 88 V8o88    88   \s
                    88  .8D 88.     88booo. 88.        88    88.          88   88 Y8b  d8 Y8b  d8 `8b  d8' 88b  d88 88  V888    88   \s
                    Y8888D' Y88888P Y88888P Y88888P    YP    Y88888P      YP   YP  `Y88P'  `Y88P'  `Y88P'  ~Y8888P' VP   V8P    YP   \s
                                                                                                                                     \s
                                                                                                                                     \s
                    """;
        } else if (option == 4) {
            artWork = """
                                        
                     .o88b.  .d8b.  d8b   db  .o88b. d88888b db           d88888b db      d888888b  d888b  db   db d888888b\s
                    d8P  Y8 d8' `8b 888o  88 d8P  Y8 88'     88           88'     88        `88'   88' Y8b 88   88 `~~88~~'\s
                    8P      88ooo88 88V8o 88 8P      88ooooo 88           88ooo   88         88    88      88ooo88    88   \s
                    8b      88~~~88 88 V8o88 8b      88~~~~~ 88           88~~~   88         88    88  ooo 88~~~88    88   \s
                    Y8b  d8 88   88 88  V888 Y8b  d8 88.     88booo.      88      88booo.   .88.   88. ~8~ 88   88    88   \s
                     `Y88P' YP   YP VP   V8P  `Y88P' Y88888P Y88888P      YP      Y88888P Y888888P  Y888P  YP   YP    YP   \s
                                                                                                                           \s
                                                                                                                           \s
                    """;
        } else if (option == 5) {
            artWork = """
                                        
                    d8888b. d88888b  d888b  d888888b .d8888. d888888b d88888b d8888b. d88888b d8888b.      d8888b.  .d88b.   .d88b.  .88b  d88. .d8888.     \s
                    88  `8D 88'     88' Y8b   `88'   88'  YP `~~88~~' 88'     88  `8D 88'     88  `8D      88  `8D .8P  Y8. .8P  Y8. 88'YbdP`88 88'  YP     \s
                    88oobY' 88ooooo 88         88    `8bo.      88    88ooooo 88oobY' 88ooooo 88   88      88oobY' 88    88 88    88 88  88  88 `8bo.       \s
                    88`8b   88~~~~~ 88  ooo    88      `Y8b.    88    88~~~~~ 88`8b   88~~~~~ 88   88      88`8b   88    88 88    88 88  88  88   `Y8b.     \s
                    88 `88. 88.     88. ~8~   .88.   db   8D    88    88.     88 `88. 88.     88  .8D      88 `88. `8b  d8' `8b  d8' 88  88  88 db   8D     \s
                    88   YD Y88888P  Y888P  Y888888P `8888Y'    YP    Y88888P 88   YD Y88888P Y8888D'      88   YD  `Y88P'   `Y88P'  YP  YP  YP `8888Y'     \s
                                                                                                                                                                         \s
                                                                                                                                                                         \s
                    """;
        } else {
            artWork = """

                    db       .d88b.   d888b   d888b  d88888b d8888b.       .d88b.  db    db d888888b\s
                    88      .8P  Y8. 88' Y8b 88' Y8b 88'     88  `8D      .8P  Y8. 88    88 `~~88~~'\s
                    88      88    88 88      88      88ooooo 88   88      88    88 88    88    88   \s
                    88      88    88 88  ooo 88  ooo 88~~~~~ 88   88      88    88 88    88    88   \s
                    88booo. `8b  d8' 88. ~8~ 88. ~8~ 88.     88  .8D      `8b  d8' 88b  d88    88   \s
                    Y88888P  `Y88P'   Y888P   Y888P  Y88888P Y8888D'       `Y88P'  ~Y8888P'    YP   \s
                                                                                                    \s
                                                                                                    \s
                    """;
        }

        System.out.println(artWork);
    }
}
