/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_3_fx_application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author ektasharma
 */
public class CourseDB {

    private static String fileName = "Course.txt";
    public static final int RECORDSIZE = 72;
    Utilities ut = new Utilities();

    public static void writeToFileAsFixedLength(Course rec, int recordNo) {
        try {
            RandomAccessFile outFile = new RandomAccessFile(fileName, "rw");
            outFile.seek(RECORDSIZE * (recordNo - 1));

            Utilities.writeFixedLengthString(rec.getTitle(), 30, outFile);
            outFile.writeInt(rec.getCredits());
            outFile.writeDouble(rec.getFee());
            
            outFile.close();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Saving record - File Not Found");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Saving record - Error writing to file");
        }
    }

    public Course readFromFileAsFixedLength(int courseNumber) throws IOException {
        try {

            RandomAccessFile readFromFile = new RandomAccessFile(fileName, "r");
            int numberOfRecords = (int) readFromFile.length() / RECORDSIZE;
            if ((courseNumber - 1) <= numberOfRecords) {
                readFromFile.seek(RECORDSIZE * (courseNumber - 1));
                String title = Utilities.readFixedLengthString(30, readFromFile);
                int credits = readFromFile.readInt();
                double fee = readFromFile.readDouble();

                Course course = new Course(courseNumber, title, credits, fee);

                return course;

            } else {
                throw new IllegalArgumentException("Course number is not valid");
            }
        } catch (IllegalArgumentException Iex) {
//            JOptionPane.showMessageDialog(null, "Not Found");
                Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setContentText(Iex.getMessage());
                    msg.setHeaderText("Error Box");
                    msg.showAndWait();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return null;
        
    }


    public void updateToFileAsFixedLength(Course course, int numberOfRecords) {
        try {
            RandomAccessFile readWriteFile = new RandomAccessFile(fileName, "rw");
            if ((numberOfRecords) <= readWriteFile.length() / RECORDSIZE) {
                readWriteFile.seek(RECORDSIZE * (numberOfRecords - 1));
                Utilities.writeFixedLengthString(course.getTitle(), 30, readWriteFile);
                readWriteFile.writeInt(course.getCredits());
                readWriteFile.writeDouble(course.getFee());

                readWriteFile.close();
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File not Found");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in updating the file");
        }
    }

    public boolean contains(Course p) {
        Course courseTemp = new Course();
        return false;
    }
}
