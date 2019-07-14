package view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Country;
import model.CruiseSailing;
import model.CruiseShip;
import model.Port;
import model.Room;
import model.SailTo;
import utils.Consts;
public class RoomManagementScreenController {

	// ============================== Variables =============================

	@FXML
	private StackPane pane;

	@FXML
	private AnchorPane mainPane;

	@FXML
	private JFXTextField IDTextField;

	@FXML
	private JFXTextField roomNumTextField;

	@FXML
	private JFXRadioButton suiteBut;

	@FXML
	private ToggleGroup roomTypeGroup;

	@FXML
	private JFXRadioButton standardBut;

	@FXML
	private JFXRadioButton twoBut;

	@FXML
	private ToggleGroup bedAmountGroup;

	@FXML
	private JFXRadioButton fourBut;

	@FXML
	private JFXTextField priceTextField;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton saveRoomBut;

	private Room room;

	private boolean update;

	// =============================== Methods ==============================

	public void initialize() {
		room = ViewLogic.adminShipsRoomsScreenController.room;
		pane.setStyle("-fx-background-image: url(\"/rsc/room-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		errorLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

		// for update
		if (room.getCruiseShipID() != null)
			IDTextField.setText(room.getCruiseShipID());

		if (room.getRoomNumber() > 0 && room.getPrice() > 0 && room.getRoomType() != null && room.getBedsAmount() > 0) {
			roomNumTextField.setText(Integer.toString(room.getRoomNumber()));
			roomNumTextField.setEditable(false);
			if (room.getRoomType().equalsIgnoreCase(Consts.STANDARD))
				roomTypeGroup.selectToggle(standardBut);
			else if (room.getRoomType().equalsIgnoreCase(Consts.SUITE))
				roomTypeGroup.selectToggle(suiteBut);

			if (room.getBedsAmount() == Consts.TWO)
				bedAmountGroup.selectToggle(twoBut);
			else if (room.getBedsAmount() == Consts.FOUR)
				bedAmountGroup.selectToggle(fourBut);

			priceTextField.setText(Integer.toString(room.getPrice()));

			update = true;
		}
	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void saveRoom() {
		int rnum, rprice;
		try {
			rnum = Integer.parseInt(roomNumTextField.getText());
			if (rnum > 0) {
				try {
					rprice = Integer.parseInt(priceTextField.getText());
					if (rprice > 0) {
						if (roomTypeGroup.getSelectedToggle() != null) {
							String rtype = Consts.STANDARD;
							int rbeds = Consts.TWO;

							if (roomTypeGroup.getSelectedToggle().equals(standardBut)) {
								rtype = Consts.STANDARD; 
							}
							else if (roomTypeGroup.getSelectedToggle().equals(suiteBut)) {
								rtype = Consts.SUITE; 
							} else
								errorLabel.setText("hello.");

							if (bedAmountGroup.getSelectedToggle() != null) {
								if (bedAmountGroup.getSelectedToggle().equals(twoBut)) {
									rbeds = Consts.TWO; 
								}
								else if (bedAmountGroup.getSelectedToggle().equals(fourBut)) {
									rbeds = Consts.FOUR; 
								} else
									errorLabel.setText("hello2.");

								try {
									room.setCruiseShipID(IDTextField.getText());
									room.setRoomNumber(rnum);
									room.setBedsAmount(rbeds);
									room.setRoomType(rtype);
									room.setPrice(rprice);

									if (update) {
										ViewLogic.controller.updateRoom(room);
										errorLabel.setText("Room updated successfully.");
									}
									else if (!update && ViewLogic.controller.insertRoom(room))
										errorLabel.setText("Room added successfully. Add another?");
									else if (!update)
										errorLabel.setText("Room already exists.");
									ViewLogic.adminShipsRoomsScreenController.setRoomTable();
								} catch(Exception e) {
									errorLabel.setText("Error occured.");
								}
							} else
								errorLabel.setText("Please select a bed amount.");
						} else
							errorLabel.setText("Please select a room type.");
					} else
						errorLabel.setText("Price must be a positive number.");
				} catch (NumberFormatException e) {
					errorLabel.setText("Price must be an integer.");
				}
			} else
				errorLabel.setText("Room Number must be a positive number.");
		} catch (NumberFormatException e) {
			errorLabel.setText("Room Number must be an integer.");
		}
		//		Port p = portCombo.getValue();
		//		if (p != null) {
		//			if (arrivalDatePicker.getValue() != null) {
		//				if (arrivalDatePicker.getValue().isAfter(LocalDate.now())) {
		//					Date arr = Date.valueOf(arrivalDatePicker.getValue());
		//					if (leavingDatePicker.getValue() != null) {
		//						if (leavingDatePicker.getValue().isAfter(LocalDate.now())) {
		//							if (leavingDatePicker.getValue().isAfter(arrivalDatePicker.getValue())) {
		//								Date leave = Date.valueOf(leavingDatePicker.getValue());
		//								try {
		//									sailto.setSailingID(IDTextField.getText());
		//									sailto.setPortName(p.getPortName());
		//									sailto.setCountryName(p.getCountryName());
		//									sailto.setArrivalTime(arr);
		//									sailto.setLeavingTime(leave);
		//									if (update) {
		//										ViewLogic.controller.updateSailTo(sailto);
		//										errorLabel.setText("Sail to destination updated successfully.");
		//									}
		//									else if (!update && ViewLogic.controller.insertSailTo(sailto))
		//										errorLabel.setText("Sail to destination added successfully. Add another?");
		//									else if (!update)
		//										errorLabel.setText("Sail to destination already exists.");
		//									ViewLogic.adminCruisesScreenController.setSTtable();
		//								} catch(Exception e) {
		//									errorLabel.setText("Error occured.");
		//								}
		//							} else
		//								errorLabel.setText("Leaving date must be after arrival date.");
		//						} else 
		//							errorLabel.setText("Leaving date must be after today.");
		//					} else
		//						errorLabel.setText("Leaving date must be after today.");
		//				} else
		//					errorLabel.setText("Arrival date must be after today.");
		//			} else
		//				errorLabel.setText("Please select an arrival date.");
		//		} else
		//			errorLabel.setText("Please select a port.");
	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			saveRoom();
	}
}
