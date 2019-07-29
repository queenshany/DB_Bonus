package view;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Country;
import model.CruiseSailing;
import model.FiveQuery;
import model.OneAQuery;
import model.Person;
import progress_circle.ProgressThread;
import progress_circle.RingProgressIndicator;
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
	private BarChart<String, Integer> popularChart;

	@FXML
	CategoryAxis xAxisPopular;

	@FXML
	NumberAxis yAxisPopular;

	@FXML
	private PieChart shipUsageChart;

	@FXML
	Label IDLabelShip;

	@FXML
	Label usageLabelShip;

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
		emptySuitesSP.setStyle("-fx-fill: transparent");

		LocalDate ld = LocalDate.now();
		LocalDate ld5 = LocalDate.of(2020, 12, 31);

		// set combo
		customerCombo.getItems().setAll(ViewLogic.controller.getAllCustomers());
		countryCombo.getItems().setAll(ViewLogic.controller.getAllCountries());
		cruiseComboRooms.getItems().setAll(ViewLogic.controller.getAllFutureCruiseSailing());
		cruiseComboSuites.getItems().setAll(ViewLogic.controller.getAllFutureCruiseSailing());

		// set table
		cruiseIDColumn.setCellValueFactory(new PropertyValueFactory<>("cruiseID")); // According to variable name
		shipIDColumn.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // Same here
		shipNameColumn.setCellValueFactory(new PropertyValueFactory<>("shipName")); // Same here

		// set pie chart
		setPieChart();

		// set bar chart
		startDatePickerPopular.setValue(ld);
		endDatePickerPopular.setValue(ld5);
		setPopularChart();

		// set profit
		startDatePickerProfit.setValue(ld);
		endDatePickerProfit.setValue(ld5);
		setProfitText();

		// set progress circles
		setEmptySuitesProgress();
		setRoomsProgress();
	}

	@FXML
	private void setPieChart() {

		ObservableList<PieChart.Data> shipDetails = FXCollections.observableArrayList();
		for (Entry<Integer, Integer> e : ViewLogic.controller.getListOfAllTimeShipOrders().entrySet()) {
			shipDetails.addAll(new PieChart.Data(e.getKey().toString(), e.getValue()));
		}

		shipUsageChart.setData(shipDetails);
		shipUsageChart.setClockwise(false);
		shipUsageChart.setStartAngle(90);
		shipUsageChart.setLabelsVisible(false);

		int allShipsAmount = ViewLogic.controller.getAllCruise().size();//shipDetails.size();
		shipUsageChart.getData().stream().forEach(data -> {
			data.getNode().addEventHandler(MouseEvent.ANY, e -> {
				IDLabelShip.setText(data.getName());
				usageLabelShip.setText(String.format("%.2f", data.getPieValue()/allShipsAmount*100) + " %");
			});
		});
	}

	@FXML
	private void setEmptySuitesProgress() {
		emptySuitesSP.getChildren().clear();
		RingProgressIndicator rpi = new RingProgressIndicator();
		rpi.setRingWidth(123);
		emptySuitesSP.getChildren().add(rpi);
		CruiseSailing c = cruiseComboSuites.getSelectionModel().getSelectedItem();
		if (c != null) {
			ProgressThread pt = new ProgressThread(rpi, ViewLogic.controller.getSixQuery(c));
			pt.start();
		}
	}

	@FXML
	private void setRoomsProgress() {
		occupiedRoomsSP.getChildren().clear();
		RingProgressIndicator rpi = new RingProgressIndicator();
		rpi.setRingWidth(123);
		occupiedRoomsSP.getChildren().add(rpi);
		CruiseSailing c = cruiseComboRooms.getSelectionModel().getSelectedItem();

		if (c != null) {
			ProgressThread pt = new ProgressThread(rpi, ViewLogic.controller.getPercentageOfOccupiedRooms(c));
			pt.start();
		}
	}

	@FXML
	private void setPopularChart() {
		if (startDatePickerPopular.getValue() != null) {
			if (endDatePickerPopular.getValue() != null) {
				Date start = Date.valueOf(startDatePickerPopular.getValue());
				Date end = Date.valueOf(endDatePickerPopular.getValue());
				if (start.before(end) || start.equals(end)) {
					ObservableList<XYChart.Series<String, Integer>> xyFQ = FXCollections.observableArrayList();

					for (FiveQuery fq : ViewLogic.controller.getFiveQuery(start, end)) {
						Series <String, Integer> destination = new Series<>();
						destination.setName(fq.getYear() + " | " + fq.getPortName() + ", " + fq.getCountryName());
						destination.getData().add(new XYChart.Data<String, Integer>((fq.getPortName() + ", " + fq.getCountryName() + "\n" + fq.getYear()), fq.getNumOfPersons()));
						xyFQ.add(destination);
					}
					popularChart.getXAxis().setTickLabelGap(0);
					popularChart.getYAxis().setTickLabelGap(0);
					popularChart.getData().setAll(xyFQ);
					// System.out.println(xyFQ);
					popularChart.setLegendVisible(false);
					popularChart.setAnimated(false);
				}
			}
		}
	}

	@FXML
	private void setProfitText() {
		if (startDatePickerProfit.getValue() != null) {
			if (endDatePickerProfit.getValue() != null) {
				Date start = Date.valueOf(startDatePickerProfit.getValue());
				Date end = Date.valueOf(endDatePickerProfit.getValue());
				if (start.before(end) || start.equals(end)) {
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
				// System.out.println(year);
				if (year >= 1000 && year <= 3000) {
					ArrayList<OneAQuery> oneArr = ViewLogic.controller.getOneAQuery(c, year);
					ObservableList<OneAQuery> oneList = FXCollections.observableArrayList(oneArr);
					query1ATable.setItems(oneList);
					query1ATable.refresh();
				}
			} catch (NumberFormatException e) {
				System.out.println("A non year was captured");
			}
		}
	}

	@FXML
	private void setVIPLabel() {
		Person p = customerCombo.getSelectionModel().getSelectedItem();
		if (p != null) {
			vipLabel.setText("is " + ViewLogic.controller.checkVIPcustomer(p.getPersonID()) + ".");
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
