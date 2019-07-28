package view;

import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CruiseSailing;
import model.FiveQuery;
import model.Room;
public class CustomerDashboardScreenController {

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
	private JFXTextField vipTextField;

	@FXML
	private JFXComboBox<CruiseSailing> cruiseComboFree;

	@FXML
	private JFXTextField roomTextField;

	@FXML
	private JFXDatePicker startDatePickerPopular;

	@FXML
	private JFXDatePicker endDatePickerPopular;

	@FXML
	private BarChart<String, Integer> popularChart;

	@FXML
	private CategoryAxis xAxisPopular;

	@FXML
	private NumberAxis yAxisPopular;

	@FXML
	private BarChart<String, Integer> ordersChart;

	@FXML
	private CategoryAxis xAxisOrders;

	@FXML
	private NumberAxis yAxisOrders;

	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/dashboard-bg1.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		vipTextField.setText("You are " + ViewLogic.controller.checkVIPcustomer(ViewLogic.currentUser.getPersonID()) + ".");


		// set cruise combo
		cruiseComboFree.getItems().setAll(ViewLogic.controller.getAllFutureCruiseSailing());

		// set bar chart
		LocalDate ld = LocalDate.now();
		LocalDate ld5 = (LocalDate.now()).plusYears(5);
		startDatePickerPopular.setValue(ld);
		endDatePickerPopular.setValue(ld5);
		setPopularChart();

		setOrdersChart();

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
					System.out.println(xyFQ);
					popularChart.setLegendVisible(false);
					popularChart.setAnimated(false);
				}
			}
		}
	}

	private void setOrdersChart() {
		ObservableList<XYChart.Series<String, Integer>> xyFQ = FXCollections.observableArrayList();

		for (Entry<Integer, Integer> e : ViewLogic.controller.getCustomerOrderByYear(ViewLogic.currentUser).entrySet()) {
			Series <String, Integer> s = new Series<>();
			s.getData().add(new XYChart.Data<String, Integer>((e.getKey().toString()), e.getValue()));
			xyFQ.add(s);
		}
		ordersChart.getXAxis().setTickLabelGap(0);
		ordersChart.getYAxis().setTickLabelGap(0);
		ordersChart.getData().setAll(xyFQ);
		System.out.println(xyFQ);
		ordersChart.setLegendVisible(false);
		ordersChart.setAnimated(false);
	}

	@FXML
	private void setRoomTextField() {
		CruiseSailing c = cruiseComboFree.getSelectionModel().getSelectedItem();
		if (c != null) {
			Room r = ViewLogic.controller.getFreeCheapestRoom(c.getCruiseID());
			roomTextField.setText(r == null ? "There are no free rooms" : r.toString());
		} else
			roomTextField.setText("");

	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
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
		ViewLogic.newCustomerManagementWindow();
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
