import java.util.List;

public interface DisplayClass {

    void displayRegisteredUsersForAllRoom();

    void displayRegisteredUserForASpecificRoom(String flightNum);

    void displayHeaderForUsers(Room room, List<Customer> c);

    void displayRoomsRegisteredByOneUser(int userID);

    void displayArtWork(int options);
}