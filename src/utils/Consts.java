package utils;

public final class Consts {

    public static final String SUITE = "Suite";
    public static final String STANDARD = "Standard";
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int SEVEN = 7;
    public static final int NINE = 9;
    
    public static final String getAllCustomers = "exec GetAllCustomers";
    public static final String getAllCountries = "exec GetAllCountries";
    public static final String getAllCruise = "exec GetAllCruise";
    public static final String getAllPorts = "exec GetAllPorts";
    public static final String getAllRooms = "exec GetAllRooms ?";
    public static final String getAllSailTo = "exec GetAllSailTo ?";
    public static final String getAllShips = "exec GetAllShips";
    public static final String getAllCruiseOrders = "exec GetAllCruiseOrder";
    public static final String getAllCruiseOrdersByCustomerID = "exec GetCruiseOrderByCustomerID ?";
    public static final String getAllFutureCruiseOrderByCustomerID = "exec GetFutureCruiseOrderByCustomerID ?";
    public static final String getOneAQuery = "exec OneAQuery ?,?";
    public static final String getFiveQuery = "exec FiveQuery ?,?";
    public static final String getSixQuery = "exec SixQuery";
    public static final String insertPerson = "exec InsertPerson ?,?,?,?,?,?,?";
    public static final String insertCountry = "exec InsertCountry ?";
    public static final String insertCruise = "exec InsertCruise ?,?,?,?";
    public static final String insertCruiseOrder = "exec InsertCruiseOrder ?,?,?,?";
    public static final String insertPort = "exec InsertPort ?,?";
    public static final String insertRoom = "exec InsertRoom ?,?,?,?,?";
    public static final String insertSailTo = "exec InsertSailTo ?,?,?,?,?";
    public static final String insertShip = "exec InsertShip ?,?,?,?,?";
    public static final String updateCruise = "exec UpdateCruise ?";
    public static final String updateCruiseShip = "exec UpdateCruiseShip ?";
    public static final String updatePerson = "exec UpdatePerson ?";
    public static final String updateRoom = "exec UpdateRoom ?";
    public static final String updateSailTo = "exec UpdateSailTo ?";
    public static final String removeCountry = "exec RemoveCountry ?";
    public static final String removeCruise = "exec RemoveCruise ?";
    public static final String removeCruiseOrder = "exec RemoveCruiseOrder ?,?,?";
    public static final String removePort = "exec RemovePort ?,?";
    public static final String removeRoom = "exec RemoveRoom ?,?";
    public static final String removeSailTo = "exec RemoveSailTo ?,?,?";
    public static final String removeShip = "exec RemoveShip ?";
}
