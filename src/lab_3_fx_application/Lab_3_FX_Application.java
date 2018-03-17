/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_3_fx_application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ektasharma
 */
public class Lab_3_FX_Application extends Application {

    private TextField txtCourseNo, txtTitle, txtCredit, txtFee;
//    private Button btnAdd, btnFind, btnUpdate, btnExit;

    private BorderPane createMainGUI() {
        BorderPane pane = new BorderPane();

        HBox hbox = new HBox(25);
        Button addBtn = new Button("Add");
        Button findBtn = new Button("Find");
        Button updateBtn = new Button("Update");
        Button exitBtn = new Button("Exit");
        hbox.getChildren().addAll(addBtn, findBtn, updateBtn,exitBtn);
        hbox.setAlignment(Pos.CENTER);

        HBox coursePane = new HBox(20);
        txtCourseNo = new TextField();
        txtTitle = new TextField();
        txtCredit = new TextField();
        txtFee = new TextField();
        coursePane.getChildren().addAll(txtCourseNo, txtTitle, txtCredit, txtFee);

        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);

        mainPane.add(new Label("Course#"), 0, 0);
        mainPane.add(txtCourseNo, 1, 0);

        mainPane.add(new Label("Title"), 0, 1);
        mainPane.add(txtTitle, 1, 1);

        mainPane.add(new Label("Credit"), 0, 2);
        mainPane.add(txtCredit, 1, 2);

        mainPane.add(new Label("Fee"), 0, 3);
        mainPane.add(txtFee, 1, 3);

        mainPane.setHgap(5.5);
        mainPane.setVgap(5.5);

        pane.setBottom(hbox);
        pane.setCenter(mainPane);

        addBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    if (txtCourseNo.getText().equals("")) {
                        throw new Exception("Please enter Course Number");
                    }

                    if (txtTitle.getText().equals("")) {
                        throw new Exception("Please enter title");
                    }

                    if (txtCredit.getText().equals("")) {
                        throw new Exception("Please enter credits");
                    }

                    if (txtFee.getText().equals("")) {
                        throw new Exception("Please enter fee");
                    }

                    CourseDB courseDbObj = new CourseDB();

                    int positionOfCourseNo = Integer.parseInt(txtCourseNo.getText());
                    Course courseData = new Course(positionOfCourseNo, txtTitle.getText(),
                            Integer.parseInt(txtCredit.getText()), Double.parseDouble(txtFee.getText()));

                    courseDbObj.writeToFileAsFixedLength(courseData, positionOfCourseNo);

                    txtCourseNo.setText("");
                    txtTitle.setText("");
                    txtCredit.setText("");
                    txtFee.setText("");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Added Successfully");
                    alert.show();

                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
            }
        });

        findBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                CourseDB findCourse = new CourseDB();
                int positionOfCourseNo = Integer.parseInt(txtCourseNo.getText());
                Course course = findCourse.readFromFileAsFixedLength(positionOfCourseNo);

                txtTitle.setText(course.getTitle());
                txtCredit.setText(Integer.toString(course.getCredits()));
                txtFee.setText(Double.toString(course.getFee()));
            }   catch (IOException ioex) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText(ioex.getMessage());
                    alert.show();
                }
           }
        });

        updateBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    CourseDB courseDbObj = new CourseDB();

                    int positionOfCourseNo = Integer.parseInt(txtCourseNo.getText());
                    Course updatedCourseData = new Course(positionOfCourseNo, txtTitle.getText(),
                            Integer.parseInt(txtCredit.getText()), Double.parseDouble(txtFee.getText()));

                    courseDbObj.updateToFileAsFixedLength(updatedCourseData, positionOfCourseNo);

                    txtCourseNo.setText("");
                    txtTitle.setText("");
                    txtCredit.setText("");
                    txtFee.setText("");
                    
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Updated Successfully");
                    alert.show();

                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
            }
        });
        
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        return pane;
    }

    @Override
    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);

        Scene scene = new Scene(createMainGUI(), 300, 250);

        primaryStage.setTitle("Course Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
