package HotelResevation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;


class Room {
    private int roomNumber;
    private String type;
    private boolean available;
    private double price;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.available = true; 
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }
}


class Reservation {
    private Room room;
    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Room room, String guestName, Date checkInDate, Date checkOutDate) {
        this.room = room;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
}


class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        // Initialize rooms
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Double", 80.0));
        rooms.add(new Room(103, "Suite", 150.0));
    }

    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(Room room, String guestName, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(room, guestName, checkInDate, checkOutDate);
        room.setAvailable(false); 
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}


public class HotelReservation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
           
            System.out.println("Enter check-in date (yyyy-MM-dd): ");
            Date checkInDate = sdf.parse(scanner.nextLine());
            System.out.println("Enter check-out date (yyyy-MM-dd): ");
            Date checkOutDate = sdf.parse(scanner.nextLine());

            
            List<Room> availableRooms = hotel.getAvailableRooms(checkInDate, checkOutDate);

            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for the selected dates.");
            } else {
                System.out.println("Available rooms:");
                for (Room room : availableRooms) {
                    System.out.println("Room Number: " + room.getRoomNumber() + ", Type: " + room.getType() +
                            ", Price: $" + room.getPrice());
                }

                
                System.out.println("Enter the room number you want to book: ");
                int roomNumber = Integer.parseInt(scanner.nextLine());
                Room selectedRoom = null;
                for (Room room : availableRooms) {
                    if (room.getRoomNumber() == roomNumber) {
                        selectedRoom = room;
                        break;
                    }
                }

                if (selectedRoom != null) {
                    
                    System.out.println("Enter your name: ");
                    String guestName = scanner.nextLine();

                    
                    Reservation reservation = hotel.makeReservation(selectedRoom, guestName, checkInDate, checkOutDate);
                    System.out.println("Reservation successful. Confirmation Number: " + reservation.hashCode());
                } else {
                    System.out.println("Invalid room number.");
                }
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        } finally {
            scanner.close();
        }
    }
}

