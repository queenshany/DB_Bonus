package control;

import model.*;
import utils.Consts;

import java.sql.*;
import java.util.ArrayList;

public class ControllerLogic {

    public static ControllerLogic instance;
    protected DriverManager dm;
    public static Connection conn;
    public static Statement s;
    private String userName;
    private String userID;

    private ControllerLogic() {
        try {
            initConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ControllerLogic getInstance() {
        if (instance == null)
            instance = new ControllerLogic();
        return instance;
    }

    public void initConn() throws Exception {

        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        String serverName = "localhost";
        String dbName = "NemoBonus";
        String userName = "sasa";
        String password = "12345";
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=NemoBonus;", userName, password);
        s = conn.createStatement();
    }

    public void insertPerson(Person p) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.insertPerson);
            ps.setString(i++, p.getPersonID());
            ps.setString(i++, p.getFirstName());
            ps.setString(i++, p.getSurName());
            ps.setDate(i++,   p.getDateOfBirth());
            ps.setString(i++, p.getPhone());
            ps.setString(i++, p.getEmail());
            ps.setString(i++, p.getPass());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCountry(Country c) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.insertCountry);
            ps.setString(i++, c.getCountryName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCruise(CruiseSailing cs) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.insertCruise);
            ps.setInt(i++, Integer.parseInt(cs.getCruiseID()));
            ps.setInt(i++, Integer.parseInt(cs.getCruiseShipID()));
            ps.setDate(i++, cs.getLeavingTime());
            ps.setDate(i++, cs.getReturnTime());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> getAllCustomers(){
        ArrayList<Person> toReturn = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps;
            ps = conn.prepareStatement(Consts.getAllCustomers);
            rs = ps.executeQuery();

            while (rs.next()) {
                int i = 1;
                toReturn.add(new Person(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++),
                        rs.getString(i++), rs.getString(i++), rs.getString(i++)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public ArrayList<Country> getAllCountries(){
        ArrayList<Country> toReturn = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps;
            ps = conn.prepareStatement(Consts.getAllCountries);
            rs = ps.executeQuery();

            while (rs.next()) {
                int i = 1;
                toReturn.add(new Country(rs.getString(i++)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public ArrayList<CruiseSailing> getAllCruise(){
        ArrayList<CruiseSailing> toReturn = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps;
            ps = conn.prepareStatement(Consts.getAllCruise);
            rs = ps.executeQuery();

            while (rs.next()) {
                int i = 1;
                toReturn.add(new CruiseSailing(Integer.toString(rs.getInt(i++)), Integer.toString(rs.getInt(i++)), rs.getDate(i++), rs.getDate(i++)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public ArrayList<Port> getAllPorts(){
        ArrayList<Port> toReturn = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps;
            ps = conn.prepareStatement(Consts.getAllPorts);
            rs = ps.executeQuery();

            while (rs.next()) {
                int i = 1;
                toReturn.add(new Port(rs.getString(i++), rs.getString(i++)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public ArrayList<Room> getAllRooms(String cruiseShipID){
        ArrayList<Room> toReturn = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps;
            ps = conn.prepareStatement(Consts.getAllRooms);
            ps.setString(1, String.valueOf(cruiseShipID));
            rs = ps.executeQuery();

            while (rs.next()) {
                int i = 1;
                toReturn.add(new Room(rs.getString(i++), rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getInt(i++)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public ArrayList<SailTo> getAllSailTo(String sailingID){
        ArrayList<SailTo> toReturn = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps;
            ps = conn.prepareStatement(Consts.getAllSailTo);
            ps.setString(1, String.valueOf(sailingID));
            rs = ps.executeQuery();

            while (rs.next()) {
                int i = 1;
                toReturn.add(new SailTo(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++), rs.getDate(i++)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public ArrayList<CruiseShip> getAllShips(){
        ArrayList<CruiseShip> toReturn = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps;
            ps = conn.prepareStatement(Consts.getAllShips);
            rs = ps.executeQuery();

            while (rs.next()) {
                int i = 1;
                toReturn.add(new CruiseShip(rs.getString(i++), rs.getString(i++), rs.getDate(i++), rs.getInt(i++), rs.getInt(i++)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}
