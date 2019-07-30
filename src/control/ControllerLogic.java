package control;

import model.*;
import utils.Consts;


import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Class ControllerLogic ~ the connects the logic between the UI and DB
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class ControllerLogic {
	// TODO Class Members
	// ------------------------------- Class Members ------------------------------
	public static ControllerLogic instance;
	protected DriverManager dm;
	public static Connection conn;
	public static Statement s;

	// TODO Initialize & Constructors
	// -------------------------- Initialize & Constructors -----------------------
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
		// String serverName = "localhost";
		// String dbName = "NemoBonus";
		String userName = "sasa";
		String password = "12345";
		conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=NemoBonus;", userName, password);
		s = conn.createStatement();
	}

	// TODO Insert Methods
	// ------------------------------ Insert Methods ------------------------------
	/**
	 * inserts a person to the db
	 * @param p
	 * @return true if succeeded
	 */
	public boolean insertPerson(Person p) {
		PreparedStatement ps;
		ArrayList<Person> customers = getAllCustomers();
		if (customers.contains(p)){
			return false;
		}
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.insertPerson);

			insertUpdatePerson(p, ps, i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * inserts a country to the db
	 * @param c
	 * @return true if succeeded
	 */
	public boolean insertCountry(Country c) {
		PreparedStatement ps;
		ArrayList<Country> countries = getAllCountries();
		if (countries.contains(c)){
			return false;
		}
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.insertCountry);
			ps.setString(i++, c.getCountryName());

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * inserts a cruise to the db
	 * @param cs
	 * @return true if succeeded
	 */
	public boolean insertCruise(CruiseSailing cs) {
		PreparedStatement ps;
		boolean isInsert = true;
		ArrayList<CruiseSailing> cruiseSailings = getAllCruise();
		if (cruiseSailings.contains(cs)){
			return false;
		}
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.insertCruise);
			insertUpdateCruise(cs, ps, i, isInsert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * inserts a cruise order to the db
	 * @param co
	 * @return true if succeeded
	 */
	public boolean insertCruiseOrder(CruiseOrder co) {
		PreparedStatement ps;
		ArrayList<CruiseOrder> cruiseOrders= getAllCruiseOrder();
		if (cruiseOrders.contains(co)){
			return false;
		}
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
		return true;
	}

	/**
	 * inserts a port to the db
	 * @param p
	 * @return true if succeeded
	 */
	public boolean insertPort(Port p) {
		PreparedStatement ps;
		ArrayList<Port> ports = getAllPorts();
		if (ports.contains(p)){
			return false;
		}
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.insertPort);
			ps.setString(i++, p.getCountryName());
			ps.setString(i++, p.getPortName());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * inserts a room to the db
	 * @param r
	 * @return true if succeeded
	 */
	public boolean insertRoom(Room r) {
		PreparedStatement ps;
		ArrayList<Room> rooms = getAllRooms(r.getCruiseShipID());
		if (rooms.contains(r)){
			return false;
		}
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.insertRoom);
			insertUpdateRoom(r, ps, i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * inserts a sail to destination to the db
	 * @param st
	 * @return true if succeeded
	 */
	public boolean insertSailTo(SailTo st) {
		PreparedStatement ps;
		ArrayList<SailTo> sailTos = getAllSailTo(st.getSailingID());
		if (sailTos.contains(st)){
			return false;
		}
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.insertSailTo);
			insertUpdateSailTo(st, ps, i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * inserts a cruise ship to the db
	 * @param csh
	 * @return true if succeeded
	 */
	public void insertShip(CruiseShip csh) {
		PreparedStatement ps;
		boolean isInsert = true;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.insertShip);
			insertUpdateShip(csh, ps, i, isInsert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// TODO Remove Methods
	// ------------------------------ Remove Methods ------------------------------
	/**
	 * removes a country from the db
	 * @param c
	 */
	public void removeCountry(Country c) throws CantRemoveException {
		PreparedStatement ps;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.removeCountry);
			ps.setString(i++, c.getCountryName());
			ps.execute();
		} catch (SQLException e) {
			throw new CantRemoveException(e.getMessage());
		}
	}

	/**
	 * removes a cruise from the db
	 * @param cs
	 */
	public void removeCruise(CruiseSailing cs) throws CantRemoveException {
		PreparedStatement ps;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.removeCruise);
			ps.setInt(i++, Integer.parseInt(cs.getCruiseID()));
			ps.execute();
		} catch (SQLException e) {
			throw new CantRemoveException(e.getMessage());
		}
	}

	/**
	 * removes a cruise order from the db
	 * @param co
	 */
	public void removeCruiseOrder(CruiseOrder co) throws CantRemoveException {
		PreparedStatement ps;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.removeCruiseOrder);
			ps.setInt(i++, Integer.parseInt(co.getCruiseID()));
			ps.setInt(i++, Integer.parseInt(co.getCruiseShipID()));
			ps.setInt(i++, Integer.parseInt(co.getRoomNumber()));
			ps.execute();
		} catch (SQLException e) {
			throw new CantRemoveException(e.getMessage());
		}
	}

	/**
	 * removes a port from the db
	 * @param p
	 */
	public void removePort(Port p) throws CantRemoveException {
		PreparedStatement ps;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.removePort);
			ps.setString(i++, p.getCountryName());
			ps.setString(i++, p.getPortName());
			ps.execute();
		} catch (SQLException e) {
			throw new CantRemoveException(e.getMessage());
		}
	}

	/**
	 * removes a room from the db
	 * @param r
	 */
	public void removeRoom(Room r) throws CantRemoveException {
		PreparedStatement ps;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.removeRoom);
			ps.setInt(i++, Integer.parseInt(r.getCruiseShipID()));
			ps.setInt(i++, r.getRoomNumber());
			ps.execute();
		} catch (SQLException e) {
			throw new CantRemoveException(e.getMessage());
		}
	}

	/**
	 * removes a sail to destination from the db
	 * @param st
	 */
	public void removeSailTo(SailTo st) throws CantRemoveException {
		PreparedStatement ps;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.removeSailTo);
			ps.setString(i++, st.getCountryName());
			ps.setString(i++, st.getPortName());
			ps.setInt(i++, Integer.parseInt(st.getSailingID()));
			ps.execute();
		} catch (SQLException e) {
			throw new CantRemoveException(e.getMessage());
		}
	}

	/**
	 * removes a ship from the db
	 * @param csh
	 */
	public void removeShip(CruiseShip csh) throws CantRemoveException {
		PreparedStatement ps;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.removeShip);
			ps.setInt(i++, Integer.parseInt(csh.getCruiseShipID()));
			ps.execute();
		} catch (SQLException e) {
			throw new CantRemoveException(e.getMessage());
		}
	}

	/**
	 * removes a person from the db
	 * @param p
	 */
	public void removePerson(Person p) throws CantRemoveException {
		PreparedStatement ps;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.removePerson);
			ps.setString(i++, p.getPersonID());
			ps.execute();
		} catch (SQLException e) {
			throw new CantRemoveException(e.getMessage());
		}
	}

	// TODO Update Methods
	// ------------------------------ Update Methods ------------------------------
	/**
	 * updates a person in the db
	 * @param p
	 */
	public void updatePerson(Person p) {
		PreparedStatement ps;
		// boolean isInsert = false;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.updatePerson);
			insertUpdatePerson(p, ps, i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * executes the person update in the db
	 */
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

	/**
	 * updates a cruise in the db
	 * @param cs
	 */
	public void updateCruise(CruiseSailing cs) {
		PreparedStatement ps;
		boolean isInsert = false;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.updateCruise);
			insertUpdateCruise(cs, ps, i, isInsert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * executes the cruise update in the db
	 */
	private void insertUpdateCruise(CruiseSailing cs, PreparedStatement ps, int i, boolean isInsert) throws SQLException {
		if (isInsert){
			ps.setInt(i++, autoIncrementCruiseID());
		}
		else {
			ps.setInt(i++, Integer.parseInt(cs.getCruiseID()));
		}
		ps.setInt(i++, Integer.parseInt(cs.getCruiseShipID()));
		ps.setTimestamp(i++, cs.getLeavingTime());
		ps.setTimestamp(i++, cs.getReturnTime());
		ps.execute();
	}

	/**
	 * updates a ship in the db
	 * @param csh
	 */
	public void updateShip(CruiseShip csh) {
		PreparedStatement ps;
		boolean isInsert = false;
		try {
			int i = 1;
			ps = conn.prepareStatement(Consts.updateCruiseShip);
			insertUpdateShip(csh, ps, i, isInsert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * executes the ship update in the db
	 */
	private void insertUpdateShip(CruiseShip csh, PreparedStatement ps, int i, boolean isInsert) throws SQLException {
		if (isInsert){
			ps.setInt(i++, autoIncrementCruiseShip());
		}
		else {
			ps.setInt(i++, Integer.parseInt(csh.getCruiseShipID()));
		}
		ps.setString(i++, csh.getShipName());
		ps.setDate(i++, csh.getManufacturingDate());
		ps.setInt(i++, csh.getMaxCapacity());
		ps.setInt(i++, csh.getMaxNumberOfPeople());
		ps.execute();
	}

	/**
	 * updates a room in the db
	 * @param r
	 */
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

	/**
	 * executes the room update in the db
	 */
	private void insertUpdateRoom(Room r, PreparedStatement ps, int i) throws SQLException {
		ps.setInt(i++, Integer.parseInt(r.getCruiseShipID()));
		ps.setInt(i++, r.getRoomNumber());
		ps.setString(i++, Integer.toString(r.getBedsAmount()));
		ps.setString(i++, r.getRoomType());
		ps.setInt(i++, r.getPrice());
		ps.execute();
	}

	/**
	 * updates a sail to destination in the db
	 * @param st
	 */
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
	/**
	 * executes the sail to destination update in the db
	 */
	private void insertUpdateSailTo(SailTo st, PreparedStatement ps, int i) throws SQLException {
		ps.setString(i++, st.getCountryName());
		ps.setString(i++, st.getPortName());
		ps.setInt(i++, Integer.parseInt(st.getSailingID()));
		ps.setDate(i++, st.getArrivalTime());
		ps.setDate(i++, st.getLeavingTime());
		ps.execute();
	}

	// TODO Get List Methods
	// ----------------------------- Get List Methods -----------------------------
	/**
	 * @return all the customers in the db
	 */
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

	/**
	 * @return all the countries in the db
	 */
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

	/**
	 * @return all the cruises in the db
	 */
	public ArrayList<CruiseSailing> getAllCruise(){
		ArrayList<CruiseSailing> toReturn = new ArrayList<>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getAllCruise);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn.add(new CruiseSailing(Integer.toString(rs.getInt(i++)), Integer.toString(rs.getInt(i++)), rs.getTimestamp(i++), rs.getTimestamp(i++)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * @return all the ports in the db
	 */
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

	/**
	 * @return all the rooms in a ship in the db
	 */
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

	/**
	 * @return all the sail to destinations of a cruise in the db
	 */
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

	/**
	 * @return all the ships in the db
	 */
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

	/**
	 * @return all the cruise orders in the db
	 */
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

	/**
	 * @return all the cruise orders of a customer in the db
	 */
	public ArrayList<CruiseOrder> getAllCruiseOrderByCustomerID(Person p){
		ArrayList<CruiseOrder> toReturn = new ArrayList<>();
		try {
			//ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getAllCruiseOrdersByCustomerID);
			getCoByCustomerID(p, toReturn, ps);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	private void getCoByCustomerID(Person co, ArrayList<CruiseOrder> toReturn, PreparedStatement ps) throws SQLException {
		ResultSet rs;
		ps.setString(1, co.getPersonID());

		rs = ps.executeQuery();

		while (rs.next()) {
			int i = 1;
			toReturn.add(new CruiseOrder(Integer.toString(rs.getInt(i++)), Integer.toString(rs.getInt(i++)), Integer.toString(rs.getInt(i++)), rs.getString(i++)));
		}
	}

	/**
	 * @return all the future cruise orders of a customer in the db
	 */
	public ArrayList<CruiseOrder> getFutureCruiseOrderByCustomerID(Person co){
		ArrayList<CruiseOrder> toReturn = new ArrayList<>();
		try {
			//	ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getAllFutureCruiseOrderByCustomerID);
			getCoByCustomerID(co, toReturn, ps);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * @return all the future cruise orders in the db
	 */
	public ArrayList<CruiseOrder> getFutureCO(){
		ArrayList<CruiseOrder> toReturn = new ArrayList<>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getAllFutureCO);
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

	/**
	 * @return all the past cruise orders in the db
	 */
	public ArrayList<CruiseOrder> getAllPastCO(){
		ArrayList<CruiseOrder> toReturn = new ArrayList<>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getAllPastCO);
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

	/**
	 * @return all the future cruise sailings in the db
	 */
	public ArrayList<CruiseSailing> getAllFutureCruiseSailing(){
		ArrayList<CruiseSailing> toReturn = new ArrayList<>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getAllFutureCruiseSailing);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn.add(new CruiseSailing(Integer.toString(rs.getInt(i++)), Integer.toString(rs.getInt(i++)), rs.getTimestamp(i++), rs.getTimestamp(i++)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * @return all the free rooms in a cruise in the db
	 */
	public ArrayList<Room> getVacantRoomsByCruiseID(int cruiseID){
		ArrayList<Room> toReturn = new ArrayList<>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getVacantRoomsByCruiseID);
			ps.setInt(1, cruiseID);
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

	// TODO Helper Methods
	// ------------------------------ Helper Methods ------------------------------
	/**
	 * auto increments the cruise id
	 * @return next cruise id
	 */
	public int autoIncrementCruiseID(){
		int toReturn = 0;
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.autoIncrementCruiseID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn = rs.getInt(i++);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (toReturn == 0){
			return 1;
		}
		return toReturn;
	}

	/**
	 * auto increments the ship id
	 * @return next ship id
	 */
	public int autoIncrementCruiseShip(){
		int toReturn = 0;
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.autoIncrementCruiseShip);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn = rs.getInt(i++);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (toReturn == 0){
			return 1;
		}

		return toReturn;
	}

	/**
	 * checks if a person exists in the db
	 * @param p
	 * @return true if he exists
	 */
	public boolean doesPersonExist(Person p){
		ArrayList<Person> persons = getAllCustomers();
		if (persons.contains(p)){
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * checks if a user exists in the db
	 * @param userID
	 * @param pass
	 * @return person who uses the system
	 */
	public Person doesUserExist(String userID, String pass){
		ArrayList<Person> persons = getAllCustomers();
		Person person = null;

		for (Person p: persons) {
			if (p.getPersonID().equals(userID) && p.getPass().equals(pass)){
				person = p;
			}
		}
		return person;
	}

	/**
	 * checks if 2 dates overlap in a cruise (sail to)
	 * @param startDate
	 * @param endDate
	 * @param cruiseID
	 * @return true if overlaps, false if not
	 */
	public boolean isOverlapDates(Date startDate, Date endDate, String cruiseID){
		ArrayList<SailTo> destinations = getAllSailTo(cruiseID);
		if (!areValidSailToDates(startDate, endDate, cruiseID)){
			return false;
		}

		if (destinations.isEmpty())
			return true;

		for (SailTo st: destinations) {
			if (!(st.getArrivalTime().before(endDate) && startDate.before(st.getLeavingTime()))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * checks if 2 dates overlap in a cruise (sail to and cruise dates itself)
	 * @param startDate
	 * @param endDate
	 * @param cruiseID
	 * @return true if overlaps, false if not
	 */
	private boolean areValidSailToDates(Date startDate, Date endDate, String cruiseID){
		ArrayList<CruiseSailing> cruises = getAllCruise();
		CruiseSailing cruise = null;
		for (CruiseSailing c : cruises) {
			if (c.getCruiseID().equals(cruiseID)){
				cruise = c;
				break;
			}
		}

		Date leaving = new Date(cruise.getLeavingTime().getTime());
		Date returning = new Date(cruise.getReturnTime().getTime());

		if (leaving.before(endDate) && startDate.before(returning)) {
			return true;
		}
		return false;
	}

	/**
	 * checks if 2 dates overlap in a cruise of the same ship
	 * @param sailing
	 * @return true if overlaps, false if not
	 */
	public boolean cruiseSailOverlappingDates(CruiseSailing sailing){
		ArrayList<CruiseSailing> cruises = getAllCruise();
		for (CruiseSailing cs : cruises) {
			if (cs.getCruiseShipID().equals(sailing.getCruiseShipID())) {
				if (cs.getLeavingTime().before(sailing.getReturnTime()) && sailing.getLeavingTime().before(cs.getReturnTime()))
					return true;
			}
		}
		return false;
	}

	/**
	 * checks if we can add a room to a ship, according to max number of people
	 * @param room
	 * @return true if possible
	 */
	public boolean canAddRoomToShip(Room room, boolean update){
		ArrayList<CruiseShip> ships = getAllShips();
		CruiseShip ship = null;

		for (CruiseShip cs : ships) {
			if (cs.getCruiseShipID().equals(room.getCruiseShipID())){
				ship = cs;
				break;
			}
		}

		int currentNumOfBeds = roomsAmountInShip(ship, room, update);

		if (room.getBedsAmount() + currentNumOfBeds > ship.getMaxNumberOfPeople()) {
			return false;
		}
		
		return true;
	}

	/**
	 * calculates the bed amount in a ship
	 * @param ship
	 * @return the bed amount
	 */
	public int roomsAmountInShip(CruiseShip ship, Room room, boolean update) {
		int currentNumOfBeds = 0;
		if (ship != null) {
			ArrayList<Room> rooms = getAllRooms(ship.getCruiseShipID());
			for (Room r : rooms) {
				if (!update || (update && !r.equals(room)))
					currentNumOfBeds += r.getBedsAmount();
			}
		}
		return currentNumOfBeds;
	}

	// TODO Queries
	// ------------------------------- Query Methods ------------------------------
	/** 
	 * @return all OneAQuery's in the db
	 */
	public ArrayList<OneAQuery> getOneAQuery(Country c, int year){
		ArrayList<OneAQuery> toReturn = new ArrayList<>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getOneAQuery);
			ps.setInt(1, year);
			ps.setString(2, c.getCountryName());
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn.add(new OneAQuery(rs.getInt(i++), rs.getInt(i++), rs.getString(i++)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * @return all FiveQuery's in the db
	 */
	public ArrayList<FiveQuery> getFiveQuery(Date startDate, Date endDate){
		ArrayList<FiveQuery> toReturn = new ArrayList<>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getFiveQuery);
			ps.setDate(1, startDate);
			ps.setDate(2, endDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn.add(new FiveQuery(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * @return all SixQuery's in the db
	 */
	public double getSixQuery(CruiseSailing c) {
		// -------- for percent calculation
		int allSuitesInShip = 0;
		if (c != null) {
			for (Room r : getAllRooms(c.getCruiseShipID())) {
				if (r.getRoomType().equalsIgnoreCase((Consts.SUITE)))
					allSuitesInShip++;
			}
		}
		// -----------------------------------

		ArrayList<SixQuery> toReturn = new ArrayList<>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getSixQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn.add(new SixQuery(rs.getInt(i++), rs.getInt(i++)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (c != null) {
			SixQuery sq = new SixQuery(c.getCruiseID());
			if (toReturn.contains(sq)) {
				sq = toReturn.get(toReturn.indexOf(sq));
				return allSuitesInShip == 0 ? 0 : 100.0*sq.getNumOfSuits()/allSuitesInShip;
			}
		}
		return 0;
	}

	/**
	 * @return the Cruise Profit of a certain Date Range in the db
	 */
	public double getCruiseProfitByDateRange(Date startDate, Date endDate){
		int toReturn = 0;
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getCruiseProfitByDateRange);
			ps.setDate(1, startDate);
			ps.setDate(2, endDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn = rs.getInt(i++);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * @return the free cheapest room in a cruise in the db
	 */
	public Room getFreeCheapestRoom(String cruiseID){
		Room toReturn = null;
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getFreeCheapestRoom);
			ps.setString(1, cruiseID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn = new Room(rs.getString(i++), rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getInt(i++));
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * @return the List Of All Time Ship Orders in the db - how many times a ship has been used in a cruise order
	 */
	public HashMap<Integer, Integer> getListOfAllTimeShipOrders(){
		// key is CruiseShipID, Value is the count of cruiseShipID
		HashMap<Integer, Integer> toReturn = new HashMap<Integer, Integer>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getListOfAllTimeShipOrders);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn.put(rs.getInt(i++), rs.getInt(i++));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * TODO
	 * @return the get amount Of Customers that ordered a cruise to a given Destination
	 */
	public int getNumberOfCustomersByDestination(String countryName, String portName){
		int toReturn = 0;
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getNumberOfCustomersByDestination);
			ps.setString(1, countryName);
			ps.setString(2, portName);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn = rs.getInt(i++);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * @return the Percentage Of Occupied Rooms in a cruise
	 */
	public double getPercentageOfOccupiedRooms(CruiseSailing c){

		// -------- for percent calculation
		int allRoomsInShip = c == null ? 0 : getAllRooms(c.getCruiseShipID()).size();
		// -----------------------------------

		//key is cruiseID, Value is the count of num of empty rooms
		HashMap<Integer, Integer> toReturn = new HashMap<Integer, Integer>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.getPercentageOfOccupiedRooms);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn.put(rs.getInt(i++), rs.getInt(i++));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (c != null) {
			if (toReturn.containsKey(new Integer(c.getCruiseID()))) {
				int roomNum = toReturn.get(new Integer(c.getCruiseID()));
				return allRoomsInShip == 0 ? 0 : 100.0*roomNum/allRoomsInShip;
			}
		}

		return 0;
	}

	/**
	 * @return is a person is a vip customer or not (Query 10)
	 */
	public String checkVIPcustomer(String personID){
		int toReturn = 0;
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.checkVIPcustomer);
			ps.setString(1, personID);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn = rs.getInt(i++);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (toReturn == 1 ? "" : "not ") + "a VIP customer";
	}

	/**
	 * @return the amount of orders a person made per year
	 */
	public HashMap<Integer, Integer> getCustomerOrderByYear(Person p){
		// key is year, Value is the count of orders
		HashMap<Integer, Integer> toReturn = new HashMap<Integer, Integer>();
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = conn.prepareStatement(Consts.customerOrderByYear);
			ps.setString(1, p.getPersonID());
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 1;
				toReturn.put(rs.getInt(i++), rs.getInt(i++));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
}
