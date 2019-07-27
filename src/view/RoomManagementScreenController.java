package view;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Room;
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
	private JFXTextField priceTextField;

	@FXML
	private JFXRadioButton standardBut;

	@FXML
	private ToggleGroup roomTypeGroup;

	@FXML
	private JFXRadioButton suiteBut;

	@FXML
	private JFXSlider bedsSlider;

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
			bedsSlider.setValue(room.getBedsAmount());

			if (room.getRoomType().equalsIgnoreCase(Consts.STANDARD))
				roomTypeGroup.selectToggle(standardBut);
			else if (room.getRoomType().equalsIgnoreCase(Consts.SUITE))
				roomTypeGroup.selectToggle(suiteBut);

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

							if (roomTypeGroup.getSelectedToggle().equals(standardBut)) {
								rtype = Consts.STANDARD; 
							}
							else if (roomTypeGroup.getSelectedToggle().equals(suiteBut)) {
								rtype = Consts.SUITE; 
							} else
								errorLabel.setText("hello.");

							if (bedsSlider.getValue() >= 0) {

								try {
									room.setCruiseShipID(IDTextField.getText());
									room.setRoomNumber(rnum);
									Double d = bedsSlider.getValue();
									room.setBedsAmount(d.intValue());
									room.setRoomType(rtype);
									room.setPrice(rprice);
									if (ViewLogic.controller.canAddRoomToShip(room)) {
									if (update) {
										ViewLogic.controller.updateRoom(room);
										errorLabel.setText("Room updated successfully.");
									}

									else if (!update && ViewLogic.controller.insertRoom(room))
										errorLabel.setText("Room added successfully. Add another?");
									else if (!update)
										errorLabel.setText("Room already exists.");
									ViewLogic.adminShipsRoomsScreenController.setRoomTable();
									} else
										errorLabel.setText("The room's beds amount exceeds the allowed number of people.");
								} catch(Exception e) {
									e.printStackTrace();
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
	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			saveRoom();
	}
}
