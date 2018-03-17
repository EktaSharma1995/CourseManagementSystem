/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_3_fx_application;

/**
 *
 * @author ektasharma
 */
public class Course {
    private int courseNumber;
    private String title;
    private int credits;
    private double fee;
    
    public Course(){
        
    }
    
    public Course(int courseNumber,String title,int credits,double fee) {
          
        setCourseNumber(courseNumber);
        setTitle(title);
        setCredits(credits);
        setFee(fee);
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) throws ArithmeticException{
        if (courseNumber <= 0) {
            throw new ArithmeticException("Course number can't be zero or negative ");
        } else {
            this.courseNumber = courseNumber;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws NullPointerException{
        if (title==null || title.equals("")){
            throw new NullPointerException("Title can't be null");
        } else {
            this.title = title;
        }
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) throws ArithmeticException{
        if (credits <= 0) {
            throw new ArithmeticException("Credits can't be zero or negative ");
        } else {
            this.credits = credits;
        }
    }   

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) throws ArithmeticException{
        if (fee <= 0) {
            throw new ArithmeticException("Fee can't be zero or negative ");
        } else {
            this.fee = fee;
        }
    }

    @Override
    public String toString() {
        return "Course{" + "courseNumber=" + courseNumber + ", title=" + title + ", credits=" + credits + ", fee=" + fee + '}';
    }
    
    
    
}
