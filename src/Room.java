import java.util.*;

public class Room {
    private final String roomSchedule;
    private final int roomNumber;
    private final int bedCount;
    private boolean availability;
    private List<Customer> listOfRegisteredCustomers;
    private static final List<Room> roomList = new ArrayList<>();
    private static final String[][] rooms = {
        {"1st Floor", "101", "1", "true"},          //floor_number, room_number, bed_count, availability
        {"1st Floor", "102", "2", "true"}, 
        {"1st Floor", "103", "2", "true"}, 
        {"1st Floor", "104", "2", "true"}, 
        {"1st Floor", "105", "2", "true"}, 
        {"1st Floor", "106", "2", "true"}, 
        {"1st Floor", "107", "3", "true"}, 
        {"1st Floor", "108", "3", "true"}, 
        {"2nd Floor", "201", "1", "true"},
        {"2nd Floor", "202", "2", "true"}, 
        {"2nd Floor", "203", "2", "true"}, 
        {"2nd Floor", "204", "2", "true"}, 
        {"2nd Floor", "205", "2", "true"}, 
        {"2nd Floor", "206", "2", "true"}, 
        {"2nd Floor", "207", "3", "true"}, 
        {"2nd Floor", "208", "3", "true"}, 
        {"3rd Floor", "301", "1", "true"},
        {"3rd Floor", "302", "2", "true"}, 
        {"3rd Floor", "303", "2", "true"}, 
        {"3rd Floor", "304", "2", "true"}, 
        {"3rd Floor", "305", "2", "true"}, 
        {"3rd Floor", "306", "2", "true"}, 
        {"3rd Floor", "307", "3", "true"}, 
        {"3rd Floor", "308", "3", "true"}
};


    Room() {
        this.listOfRegisteredCustomers = new ArrayList<>();
        this.roomSchedule = null;
        this.roomNumber = 0;
        this.bedCount = 0;
        this.availability = false;
    }

    Room(String roomSchedule, int roomNumber, int bedCount, boolean availability) {
        this.listOfRegisteredCustomers = new ArrayList<>();
        this.roomSchedule = roomSchedule;
        this.roomNumber = roomNumber;
        this.bedCount = bedCount;
        this.availability = availability;
    }

    public void roomScheduler() {
        for (String[] roomInfo : rooms) {
            String floorNumber = roomInfo[0];
            int roomNumber = Integer.parseInt(roomInfo[1]);
            int bedCount = Integer.parseInt(roomInfo[2]);
            boolean availability = Boolean.parseBoolean(roomInfo[3]);
            roomList.add(new Room(floorNumber, roomNumber, bedCount, availability));
        }
    }

    void addNewCustomer(Customer customer) {
        this.listOfRegisteredCustomers.add(customer);
    }

    boolean isCustomerAlreadyAdded(List<Customer> customersList, Customer customer) {
        boolean isAdded = false;
        for (Customer customer1 : customersList) {
            if (customer1.getUserID() == customer.getUserID()) {
                isAdded = true;
                break;
            }
        }
        return isAdded;
    }

    public boolean checkRoomStatus(int roomNumber) {
        for (String[] room : rooms) {
            if (room[1].equals(String.valueOf(roomNumber))) {
                return Boolean.parseBoolean(room[3]); 
            }
        }
        return false; 
    }
    
    public void changeRoomStatus(int roomNumber, String status) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i][1].equals(String.valueOf(roomNumber))) {
                rooms[i][3] = status;
                roomList.get(i).setAvailability(Boolean.parseBoolean(status));
                break;
            }
        }
    }
    
    public void displayRoom() {
        Iterator<Room> roomIterator = roomList.iterator();
        System.out.println();
        System.out.print("+-----+-----------+---------+-----+-----------+\n");
        System.out.printf("| Num | FLOOR  NO | ROOM NO | BED | AVAILABLE |%n");
        System.out.print("+-----+-----------+---------+-----+-----------+\n");
        int i = 0;
        while (roomIterator.hasNext()) {
            i++;
            Room f1 = roomIterator.next();
            System.out.println(f1.toString(i));
             System.out.print("+-----+-----------+---------+-----+-----------+\n");
        }
    }

    public String toString(int i) {
        return String.format("| %-4d| %-9s | %-7d | %-3d | %-9b |", i, roomSchedule, roomNumber, bedCount, availability);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public List<Customer> getListOfRegisteredCustomers() {
        return listOfRegisteredCustomers;
    }

    public String getRoomSchedule() {
        return roomSchedule;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
