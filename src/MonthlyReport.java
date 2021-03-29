/**
 * AnnualReport created by Weihang Guo on MacBook in p4
 *
 * Author: Ni Pinzhi(pni4@wisc.edu)
 * Date:   @4.21
 *
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 002
 *
 * IDE: IntelliJ
 * Version: 2019
 *
 *
 * List Collaborators: None
 *
 * Other Credits: None
 *
 * Known Bugs: None
 */

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The Class MonthlyReport.
 */

public class MonthlyReport extends Application {
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Start.
	 *
	 * @param stage the stage
	 */
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Monthly Report");
		stage.setWidth(550);
		stage.setHeight(600);
		//create a label for title
		Label title = new Label("Monthly Report");
		title.setFont(new Font("Arial", 20));

		//create a table to show the monthly report
		TableView<Milk> table = new TableView<>();
		ObservableList<Milk> data =
				FXCollections.observableArrayList(
						new Milk("0", 100, 10.0),
						new Milk("1", 260, 26.0),
						new Milk("2", 640, 64.0)
				);

		//build the farmID column
		TableColumn farmID = new TableColumn("FarmID");
		farmID.setMinWidth(100);
		farmID.setCellValueFactory(new PropertyValueFactory<>("farmID"));

		//build the weight column
		TableColumn weight = new TableColumn("Total Weight");
		weight.setMinWidth(100);
		weight.setCellValueFactory(new PropertyValueFactory<>("weight"));

		//build the percentage column
		TableColumn percentage = new TableColumn("Percent of Total Weight of All Farms");
		percentage.setMinWidth(300);
		percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
		table.getColumns().addAll(farmID, weight, percentage);

		//create a horizontal box
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		Label prompt = new Label("Month:");
		TextField month = new TextField();
		Button c = new Button("Confirm");
		c.addEventHandler(ActionEvent.ACTION, (e) -> table.setItems(data));
		hbox.getChildren().addAll(prompt, month, c);

		//create a horizontal box for the orders
		HBox hbox2 = new HBox();
		hbox2.setSpacing(10);
		ToggleGroup group = new ToggleGroup();
		RadioButton order1 = new RadioButton("Ascending Order");
		order1.setToggleGroup(group);
		order1.addEventHandler(ActionEvent.ACTION,
				(e) -> {weight.setSortType(TableColumn.SortType.ASCENDING);
					table.getSortOrder().add(weight);
					table.sort();});
		RadioButton order2 = new RadioButton("Descending Order");
		order2.setToggleGroup(group);
		order2.addEventHandler(ActionEvent.ACTION,
				(e) -> {weight.setSortType(TableColumn.SortType.DESCENDING);
					table.getSortOrder().add(weight);
					table.sort();});
		hbox2.getChildren().addAll(order1, order2);



		//create a vbox to contain all the elements
		VBox vbox = new VBox();
		vbox.setSpacing(20);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(title, hbox, hbox2, table);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * The Class Milk.
	 */
	public static class Milk {

		/** The farm ID. */
		private final SimpleStringProperty farmID;

		/** The weight. */
		private final SimpleIntegerProperty weight;

		/** The percentage. */
		private final SimpleDoubleProperty percentage;

		/**
		 * Instantiates.
		 *
		 * @param farmID the farm ID
		 * @param weight the weight
		 * @param percentage the percentage
		 */
		private Milk(String farmID, Integer weight, Double percentage) {
			this.farmID = new SimpleStringProperty(farmID);
			this.weight = new SimpleIntegerProperty(weight);
			this.percentage = new SimpleDoubleProperty(percentage);
		}

		/**
		 * Gets the farm ID.
		 *
		 * @return the farm ID
		 */
		public String getFarmID() {
			return farmID.get();
		}


		/**
		 * Gets the weight.
		 *
		 * @return the weight
		 */
		public Integer getWeight() {
			return weight.get();
		}

		/**
		 * Gets the percentage.
		 *
		 * @return the percentage
		 */
		public Double getPercentage() {
			return percentage.get();
		}

	}

}
