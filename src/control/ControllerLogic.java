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
            insertUpdatePerson(p, ps, i);
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
            insertUpdateCruise(cs, ps, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCruiseOrder(CruiseOrder co) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.insertCruiseOrder);
            ps.setInt(i++, Integer.parseInt(co.getCruiseID()));
            ps.setInt(i++, Integer.parseInt(co.getCruiseShipID()));
            ps.setInt(i++, Integer.parseInt(co.getRoomNumber()));
            ps.setString(i++, co.getPersonID());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPort(Port p) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.insertPort);
            ps.setString(i++, p.getCountryName());
            ps.setString(i++, p.getPortName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRoom(Room r) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.insertRoom);
            insertUpdateRoom(r, ps, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSailTo(SailTo st) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.insertSailTo);
            insertUpdateSailTo(st, ps, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertShip(CruiseShip csh) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.insertShip);
            insertUpdateShip(csh, ps, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCountry(Country c) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.removeCountry);
            ps.setString(i++, c.getCountryName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCruise(CruiseSailing cs) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.removeCruise);
            ps.setInt(i++, Integer.parseInt(cs.getCruiseID()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCruiseOrder(CruiseOrder co) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.removeCruiseOrder);
            ps.setInt(i++, Integer.parseInt(co.getCruiseID()));
            ps.setInt(i++, Integer.parseInt(co.getCruiseShipID()));
            ps.setInt(i++, Integer.parseInt(co.getRoomNumber()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePort(Port p) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.removePort);
            ps.setString(i++, p.getCountryName());
            ps.setString(i++, p.getPortName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRoom(Room r) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.removeRoom);
            ps.setInt(i++, Integer.parseInt(r.getCruiseShipID()));
            ps.setInt(i++, r.getRoomNumber());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSailTo(SailTo st) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.removeSailTo);
            ps.setString(i++, st.getCountryName());
            ps.setString(i++, st.getPortName());
            ps.setInt(i++, Integer.parseInt(st.getSailingID()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeShip(CruiseShip csh) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.removeShip);
            ps.setInt(i++, Integer.parseInt(csh.getCruiseShipID()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(Person p) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.updatePerson);
            insertUpdatePerson(p, ps, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertUpdatePerson(Person p, PreparedStatement ps, int i) throws SQLException {
        ps.setString(i++, p.getPersonID());
        ps.setString(i++, p.getFirstName());
        ps.setString(i++, p.getSurName());
        ps.setDate(i++,   p.getDateOfBirth());
        ps.setString(i++, p.getPhone());
        ps.setString(i++, p.getEmail());
        ps.setString(i++, p.getPass());
        ps.execute();
    }

    public void updateCruise(CruiseSailing cs) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.updateCruise);
            insertUpdateCruise(cs, ps, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertUpdateCruise(CruiseSailing cs, PreparedStatement ps, int i) throws SQLException {
        ps.setInt(i++, Integer.parseInt(cs.getCruiseID()));
        ps.setInt(i++, Integer.parseInt(cs.getCruiseShipID()));
        ps.setDate(i++, cs.getLeavingTime());
        ps.setDate(i++, cs.getReturnTime());
        ps.execute();
    }

    public void updateShip(CruiseShip csh) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.updateCruiseShip);
            insertUpdateShip(csh, ps, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertUpdateShip(CruiseShip csh, PreparedStatement ps, int i) throws SQLException {
        ps.setInt(i++, Integer.parseInt(csh.getCruiseShipID()));
        ps.setString(i++, csh.getShipName());
        ps.setDate(i++, csh.getManufacturingDate());
        ps.setInt(i++, csh.getMaxCapacity());
        ps.setInt(i++, csh.getMaxNumberOfPeople());
        ps.execute();
    }

    public void updateRoom(Room r) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.updateRoom);
            insertUpdateRoom(r, ps, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertUpdateRoom(Room r, PreparedStatement ps, int i) throws SQLException {
        ps.setInt(i++, Integer.parseInt(r.getCruiseShipID()));
        ps.setInt(i++, r.getRoomNumber());
        ps.setString(i++, Integer.toString(r.getBedsAmount()));
        ps.setString(i++, r.getRoomType());
        ps.setInt(i++, r.getPrice());
        ps.execute();
    }

    public void updateSailTo(SailTo st) {
        PreparedStatement ps;
        try {
            int i = 1;
            ps = conn.prepareStatement(Consts.updateSailTo);
            insertUpdateSailTo(st, ps, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertUpdateSailTo(SailTo st, PreparedStatement ps, int i) throws SQLException {
        ps.setString(i++, st.getCountryName());
        ps.setString(i++, st.getPortName());
        ps.setInt(i++, Integer.parseInt(st.getSailingID()));
        ps.setDate(i++, st.getArrivalTime());
        ps.setDate(i++, st.getLeavingTime());
        ps.execute();
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

    public ArrayList<CruiseOrder> getAllCruiseOrder(){
        ArrayList<CruiseOrder> toReturn = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps;
            ps = conn.prepareStatement(Consts.getAllCruiseOrders);
            rs = ps.executeQuery();

            while (rs.next()) {
                int i = 1;
                toReturn.add(new CruiseOrder(Integer.toString(rs.getInt(i++)), Integer.toString(rs.getInt(i++)), Integer.toString(rs.getInt(i++)), rs.getString(i++)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}
