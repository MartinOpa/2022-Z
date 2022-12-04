package domain_layer;

import java.util.LinkedList;
import java.util.List;
import data_layer.ClientDB;
import data_layer.ReservationsDB;
import data_layer.VehicleDB;

public class Client extends User {
    private ClientDB ClientDB;
    private ReservationsDB ReservationDB;
    private VehicleDB VehicleDB;
    private List<Vehicle> VehicleList;
    private List<Reservation> ReservationList;
    
    public Client(int ID, String login, String firstName, String lastName, Address address, int phone) {
        this.ID = ID;
        this.accountType = 0;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.ClientDB = new ClientDB();
        this.ReservationDB = new ReservationsDB();
        this.VehicleDB = new VehicleDB();
    }
    
    public ClientDB getClientDB() {
        return this.ClientDB;
    }
    
    public ReservationsDB getReservationDB() {
        return this.ReservationDB;
    }
    
    public VehicleDB getVehicleDB() {
        return this.VehicleDB;
    }
    
    public void setVehicleList(List<Vehicle> list) {
        this.VehicleList = list;
    }
    
    public List<Vehicle> getVehicleList() {
        return this.VehicleList;
    }
    
    public void setReservationList(List<Reservation> list) {
        this.ReservationList = list;
    }
    
    public List<Reservation> getReservationList() {
        return this.ReservationList;
    }

    public List<Vehicle> filter(List<Vehicle> list, String parameter) {
        List<Vehicle> filteredList = new LinkedList<>();
        for (Vehicle vehicle : list) {
            if(vehicle.getLogin().matches(".*" + parameter + ".*") || vehicle.getMake().matches(".*" + parameter + ".*") || vehicle.getModel().matches(".*" + parameter + ".*")
                    || vehicle.getVin().matches(".*" + parameter + ".*") || vehicle.getYear().matches(".*" + parameter + ".*") || vehicle.getPlate().matches(".*" + parameter + ".*")) {
                filteredList.add(vehicle);
            }
        }
        
        return filteredList;
    }
    
    public List<Reservation> filter(String parameter, List<Reservation> list) {
        List<Reservation> filteredList = new LinkedList<>();
        for (Reservation reservation : list) {
            if(reservation.getLogin().matches(".*" + parameter + ".*") || reservation.getDateTime().matches(".*" + parameter + ".*")
                    || reservation.getVin().matches(".*" + parameter + ".*") || reservation.getIssue().matches(".*" + parameter + ".*")) {
                filteredList.add(reservation);
            }
        }
        
        return filteredList;
    }
}
