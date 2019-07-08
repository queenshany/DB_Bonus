
package view;

import java.io.IOException;

import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

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

	//protected static User currentUser;
	//	protected static SysData sysData = control.SysData.getInstance();

	// ------------------------------ Methods ------------------------------
	/**
	 * this method starts the windows in the system
	 */
	public static void initUI() {
		//newLoginWindow();
		
		//newAdminCruisesSTWindow();
		//newAdminPortCountryWindow();
		//newAdminShipsRoomsWindow();
		newAdminCustomersWindow();
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
				// TODO Auto-generated method stub
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
	
	// ================================== Add & Update Country ==================================
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
				false);
	}
	
	// ================================== Add & Update Port ==================================
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
					false);
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
}
