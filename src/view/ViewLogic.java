
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
	
	// ================================== Admin ==================================
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


}
