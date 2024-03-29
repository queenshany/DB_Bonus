

package view;

import java.io.IOException;

import java.net.URL;

import control.ControllerLogic;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Person;

/**
 * Class ViewLogic ~ manages the windows in the system
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class ViewLogic {
	// ------------------------------ Variables ------------------------------
	protected static final Rectangle2D FULL_SCREEN = Screen.getPrimary().getBounds();
	protected static final Rectangle2D VISIBLE_SCREEN = Screen.getPrimary().getVisualBounds();

	protected static Person currentUser;
	protected static ControllerLogic controller = ControllerLogic.getInstance();

	protected static AdminCountriesPortsScreenController adminCountriesPortsScreenController;
	protected static AdminCruisesScreenController adminCruisesScreenController;
	protected static AdminShipsRoomsScreenController adminShipsRoomsScreenController;
	protected static AdminCustomersScreenController adminCustomersScreenController;
	protected static AdminCruiseOrdersScreenController adminCruiseOrdersScreenController;

	// ------------------------------ Methods ------------------------------
	/**
	 * this method starts the windows in the system
	 */
	public static void initUI() {
		newLoginWindow();
	}

	/**
	 * this method manages the window properties
	 * @param fxmlLocation
	 * @param stage
	 * @param prefWidth
	 * @param prefHeight
	 * @param minWidth
	 * @param minHeight
	 * @param maxWidth
	 * @param maxHeight
	 * @param resizable
	 * @param title
	 * @param waitFor
	 */
	protected static void newWindow(URL fxmlLocation, Stage stage, Double prefWidth,
			Double prefHeight, Double minWidth, Double minHeight, Double maxWidth,
			Double maxHeight, boolean resizable, String title, boolean waitFor) {
		//
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(fxmlLocation);
					Parent root = loader.load();
					Scene scene;

					if (prefWidth == null || prefHeight == null)
						scene = new Scene(root);
					else
						scene = new Scene(root, prefWidth, prefHeight);

					Image image = new Image("rsc/nemo-logo.png");
					stage.getIcons().setAll(image);

					stage.setScene(scene);

					if (minWidth != null)
						stage.setMinWidth(minWidth);
					if (minHeight != null)
						stage.setMinHeight(minHeight);
					if (maxWidth != null)
						stage.setMaxWidth(maxWidth);
					if (maxHeight != null)
						stage.setMaxHeight(maxHeight);

					stage.setResizable(resizable);

					if (title != null && !title.isEmpty() && !title.trim().isEmpty())
						stage.setTitle(title);

					if (waitFor)
						stage.initModality(Modality.APPLICATION_MODAL);

					stage.showAndWait();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ================================== Login ==================================
	/**
	 * Open Login Window
	 */
	protected static void newLoginWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("Login.fxml"),
				stage,
				null, null, null, null, null, null,
				false,
				"Login",
				false);
	}
	// ============================= Create Customer =============================
	/**
	 * Open Create Customer Window
	 */
	protected static void createAccountWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CreateCustomer.fxml"),
				stage,
				null, null, null, null, null, null,
				false,
				"Create an Account",
				false);
	}

	// ================================== Admin Main ==================================
	/**
	 * Open Admin Main Window
	 */
	protected static void newAdminMainWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AdminMain.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Admin",
				false);
	}

	// ================================== Admin Dashboard ==================================
	/**
	 * Open Admin Dashboard Window
	 */
	protected static void newAdminDashWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AdminDashboard.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Dashboard",
				false);
	}

	// ================================== Admin Ports & Countries ==================================
	/**
	 * Open Admin Ports & Countries Window
	 */
	protected static void newAdminPortCountryWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AdminCountriesPorts.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Ports & Countries Management",
				false);
	}

	// ================================== Add Country ==================================
	/**
	 * Open Country Management Window
	 */
	protected static void newCountryManagementWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CountryManagement.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Country Management",
				true);
	}

	// ================================== Add Port ==================================
	/**
	 * Open Port Management Window
	 */
	protected static void newPortManagementWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("PortManagement.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Port Management",
				true);
	}

	// ================================== Admin Cruises & SailTos ==================================
	/**
	 * Open Admin Cruises & SailTos Window
	 */
	protected static void newAdminCruisesSTWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AdminCruises.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Cruises & Destinations Management",
				false);
	}

	// ================================== Add & Update Cruise ==================================
	/**
	 * Open Cruise Management Window
	 */
	protected static void newCruiseManagementWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CruiseManagement.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Cruise Management",
				true);
	}

	// ================================== Add & Update Sail To ==================================
	/**
	 * Open Sail To Management Window
	 */
	protected static void newSailToManagementWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("SailToManagement.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Sail To Management",
				true);
	}

	// ================================== Admin Ships & Rooms ==================================
	/**
	 * Open Admin Ships & Rooms Window
	 */
	protected static void newAdminShipsRoomsWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AdminShipsRooms.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Ships & Rooms Management",
				false);
	}

	// ================================== Add & Update Ship ==================================
	/**
	 * Open Ship Management Window
	 */
	protected static void newShipManagementWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("ShipManagement.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Ship Management",
				true);
	}

	// ================================== Add & Update Room ==================================
	/**
	 * Open Room Management Window
	 */
	protected static void newRoomManagementWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("RoomManagement.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Room Management",
				true);
	}

	// ================================== Admin Customers ==================================
	/**
	 * Open Admin Customers Window
	 */
	protected static void newAdminCustomersWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AdminCustomers.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Customers Management",
				false);
	}

	// ================================== Add & Update Customer ==================================
	/**
	 * Open Customer Management Window
	 */
	protected static void newCustomerManagementWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CustomerManagement.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Customer Management",
				true);
	}

	// ================================== Admin Cruise Orders ==================================
	/**
	 * Open Admin Cruise Order Window
	 */
	protected static void newAdminCruiseOrdersWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AdminCruiseOrders.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Cruise Order Management",
				false);
	}

	// ================================== Add Cruise Order ==================================
	/**
	 * Open Cruise Order Management Window
	 */
	protected static void newCOManagementWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CruiseOrderManagement.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Cruise Order Management",
				true);
	}
	// ================================== Customer Main ==================================
	/**
	 * Open Customer Main Window
	 */
	protected static void newCustomerMainWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CustomerMain.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Customer",
				false);
	}

	// ================================== Customer Dashboard ==================================
	/**
	 * Open Customer Dashboard Window
	 */
	protected static void newCustomerDashWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CustomerDashboard.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Dashboard",
				false);
	}

	// ================================== Customer View Orders ==================================
	/**
	 * Open Customer View Orders Window
	 */
	protected static void newCustomerViewOrdersWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CustomerViewOrders.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"My Orders",
				false);
	}

	// ================================== Customer Cruise Order ==================================
	/**
	 * Open Customer View Orders Window
	 */
	protected static void newCustomerCruiseOrderWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CustomerCruiseOrder.fxml"),
				stage,
				null, null,	null, null,	null, null,
				false,
				"Cruise Order",
				false);
	}
}
