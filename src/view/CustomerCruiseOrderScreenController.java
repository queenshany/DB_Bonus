package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
public class CustomerCruiseOrderScreenController {

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
	private JFXComboBox<?> cruiseCombo;

	@FXML
	private JFXComboBox<?> roomCombo;

	@FXML
	private Label errorAddOrderLabel;

	@FXML
	private JFXButton addOrderBut;

	@FXML
	private TableView<?> ordersTable;

	@FXML
	private TableColumn<?, ?> cruiseColumn;

	@FXML
	private TableColumn<?, ?> shipColumn;

	@FXML
	private TableColumn<?, ?> roomNumColumn;

	@FXML
	private Label errorDelOrderLabel;

	@FXML
	private JFXButton delOrderBut;

	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/cruise-order-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		errorDelOrderLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , white , 5 , 1.5 , 0 , 0 )");
		errorAddOrderLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , white , 5 , 1.5 , 0 , 0 )");

		//		Label l = new Label("Login");
		//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
		//		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	//TODO
	@FXML
	private void addOrder() {

	}

	//TODO
	@FXML
	private void deleteOrder() {

	}

	// ========================== Menu Action Listeners ==========================

	//TODO
	@FXML
	private void homeMainOnAction() {

	}

	//TODO
	@FXML
	private void logoutOnAction() {

	}

	//TODO
	@FXML
	private void editDetailsOnAction() {

	}

	//TODO
	@FXML
	private void cruiseOrderOnAction() {

	}

	//TODO
	@FXML
	private void viewOrdersOnAction() {

	}

	//TODO
	@FXML
	private void dashboardOnAction() {

	}

	/*
	 */
}
