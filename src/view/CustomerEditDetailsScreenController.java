package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.E_Phone;
public class CustomerEditDetailsScreenController {

	// ============================== Variables =============================

	@FXML
    private AnchorPane mainPane;

    @FXML
    private JFXButton homeBut;

    @FXML
    private JFXButton logoutBut;

    @FXML
    private JFXButton editDetailsBut;

    @FXML
    private JFXButton cruiseOrderBut;

    @FXML
    private JFXButton viewOrdersBut;

    @FXML
    private JFXButton dashboardBut;

    @FXML
    private VBox pane;

    @FXML
    private JFXTextField IDTextField;

    @FXML
    private JFXPasswordField pwTextField;

    @FXML
    private JFXTextField firstNameTextField;

    @FXML
    private JFXTextField surnameTextField;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXDatePicker birthDatePicker;

    @FXML
    private JFXComboBox<E_Phone> phoneCombo;

    @FXML
    private JFXTextField phoneTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXButton saveCustomerBut;

	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/edit-details-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

    @FXML
    void saveCustomer() {

    }
    
	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			saveCustomer();
	}

	// ========================== Menu Action Listeners ==========================

	@FXML
	private void homeMainOnAction() {
		closeWindow();
		ViewLogic.newCustomerMainWindow();
	}

	@FXML
	private void logoutOnAction() {
		closeWindow();
		ViewLogic.currentUser = null;
		ViewLogic.newLoginWindow();
	}

	@FXML
	private void editDetailsOnAction() {
		closeWindow();
		ViewLogic.newCustomerEditDetailsWindow();
	}
	
	@FXML
	private void cruiseOrderOnAction() {
		closeWindow();
		ViewLogic.newCustomerCruiseOrderWindow();
	}
	
	@FXML
	private void viewOrdersOnAction() {
		closeWindow();
		ViewLogic.newCustomerViewOrdersWindow();
	}
	
	@FXML
	private void dashboardOnAction() {
		closeWindow();
		ViewLogic.newCustomerDashWindow();
	}
}
