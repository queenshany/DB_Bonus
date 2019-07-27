package view;


import java.sql.Date;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Country;
import model.CruiseOrder;
import model.CruiseSailing;
import model.OneAQuery;
import model.Person;
public class AdminDashboardScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane mainPane;

	@FXML
	private JFXButton homeBut;

	@FXML
	private JFXButton logoutBut;

	@FXML
	private JFXButton counPortsBut;

	@FXML
	private JFXButton shipRoomBut;

	@FXML
	private JFXButton sailCruiseBut;

	@FXML
	private JFXButton customersBut;

	@FXML
	private JFXButton cruiseOrderBut;

	@FXML
	private JFXButton dashboardBut;

	@FXML
	private VBox pane;

	@FXML
	private JFXComboBox<Person> customerCombo;

	@FXML
	private Label vipLabel;

	@FXML
	private JFXDatePicker startDatePickerProfit;

	@FXML
	private JFXDatePicker endDatePickerProfit;

	@FXML
	private JFXTextField profitTextField;

	@FXML
	private JFXDatePicker startDatePickerPopular;

	@FXML
	private JFXDatePicker endDatePickerPopular;

	@FXML
	private BarChart<?, ?> popularChart; //TODO

	@FXML
	private PieChart shipUsageChart;

	@FXML
	private JFXComboBox<Country> countryCombo;

	@FXML
	private JFXTextField yearTextFieldQuery1A;

	@FXML
	private TableView<OneAQuery> query1ATable;

	@FXML
	private TableColumn<OneAQuery, Integer> cruiseIDColumn;

	@FXML
	private TableColumn<OneAQuery, Integer> shipIDColumn;

	@FXML
	private TableColumn<OneAQuery, String> shipNameColumn;

	@FXML
	private JFXComboBox<CruiseSailing> cruiseComboSuites;

	@FXML
	private StackPane emptySuitesSP;

	@FXML
	private JFXComboBox<CruiseSailing> cruiseComboRooms;

	@FXML
	private StackPane occupiedRoomsSP;

	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/dashboard-bg1.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		//		Label l = new Label("Login");
		//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
		//		loginBut.setGraphic(l);

		// set combo
		customerCombo.getItems().setAll(ViewLogic.controller.getAllCustomers());
		countryCombo.getItems().setAll(ViewLogic.controller.getAllCountries());
		cruiseComboRooms.getItems().setAll(ViewLogic.controller.getAllCruise());
		cruiseComboSuites.getItems().setAll(ViewLogic.controller.getAllCruise());

		// set table
		cruiseIDColumn.setCellValueFactory(new PropertyValueFactory<>("cruiseID")); // According to variable name
		shipIDColumn.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // Same here
		shipNameColumn.setCellValueFactory(new PropertyValueFactory<>("shipName")); // Same here
	}

	@FXML
	private void setEmptySuitesProgress() {

	}

	@FXML
	private void setRoomsProgress() {

	}

	@FXML
	private void setPopularChart() {

	}

	@FXML
	private void setProfitText() {
		if (startDatePickerProfit.getValue() != null) {
			if (endDatePickerProfit.getValue() != null) {
				Date start = Date.valueOf(startDatePickerProfit.getValue());
				Date end = Date.valueOf(endDatePickerProfit.getValue());
				if (start.before(end)) {
					profitTextField.setText(ViewLogic.controller.getCruiseProfitByDateRange(start, end)+"");
				}
			}
		}
	}

	@FXML
	private void setQuery1ATable() {
		Country c = countryCombo.getSelectionModel().getSelectedItem();
		if (c != null) {
			try {
				int year = Integer.parseInt(yearTextFieldQuery1A.getText());
				if (year >= 1000 && year < 3000) {
					ArrayList<OneAQuery> oneArr = ViewLogic.controller.getOneAQuery(c, year);
					ObservableList<OneAQuery> oneList = FXCollections.observableArrayList(oneArr);
					query1ATable.setItems(oneList);
					query1ATable.refresh();
				}
			} catch (NumberFormatException e) {
				System.out.println("a non year was captured");
			}
		}
	}

	@FXML
	private void setVIPLabel() {
		Person p = customerCombo.getSelectionModel().getSelectedItem();
		if (p != null) {
			vipLabel.setText(ViewLogic.controller.checkVIPcustomer(p.getPersonID()));
		}
		else
			vipLabel.setText("");
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	// ========================== Menu Action Listeners ==========================

	@FXML
	private void homeMainOnAction() {
		closeWindow();
		ViewLogic.newAdminMainWindow();
	}

	@FXML
	private void logoutOnAction() {
		closeWindow();
		ViewLogic.newLoginWindow();
	}

	@FXML
	private void countriesPortsOnAction() {
		closeWindow();
		ViewLogic.newAdminPortCountryWindow();
	}

	@FXML
	private void shipsRoomsOnAction() {
		closeWindow();
		ViewLogic.newAdminShipsRoomsWindow();
	}

	@FXML
	private void cruisesOnAction() {
		closeWindow();
		ViewLogic.newAdminCruisesSTWindow();
	}

	@FXML
	private void customersOnAction() {
		closeWindow();
		ViewLogic.newAdminCustomersWindow();
	}

	@FXML
	private void cruiseOrdersOnAction() {
		closeWindow();
		ViewLogic.newAdminCruiseOrdersWindow();
	}

	@FXML
	private void dashboardOnAction() {
		closeWindow();
		ViewLogic.newAdminDashWindow();
	}

}
