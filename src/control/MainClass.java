package control;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Person;
import view.ViewLogic;

import java.sql.Date;

/**
 * Class MainClass ~ represents the program runner
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class MainClass extends Application{
	public static void main(String[] args) {
		try {
			ControllerLogic.getInstance().initConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);

	}
	public void start(Stage primaryStage) {
		ViewLogic.initUI();
	}

}
