import java.util.*;

import javax.swing.*;
import javax.swing.JOptionPane;

public class Instructor extends Employee{
   private String officeHour;
   private Vector<Course> courses = new Vector<Course>();
   public Instructor() {}
   
  public Instructor (String firstName, String lastName, String phoneNumber, String address, String email,String userName,String password, boolean isMaintenance){
      super(firstName,lastName,phoneNumber,address,email,userName,password,isMaintenance);
      
   }
   public String getOfficeHour(){
      return officeHour;
   }
   public void setOfficeHour(String officeHour){
      this.officeHour = "\n            "  + getOfficeHour();
   }
   public String toString(){
      return super.toString() + "\nOffice hour: " + this.getOfficeHour() + "\n" + getCourses();
   }
   public int getNumOfCouse(){ return courses.size();}
    public boolean getCourse(String course){
      boolean hasCourse = false;
      
      if(courses != null){
         
         Iterator it  = courses.iterator();
         while(it.hasNext()){
            Course one_course = (Course)it.next();
            if(one_course.getCourseID().equalsIgnoreCase(course)){
               hasCourse = true;
               return hasCourse;
            }
         }         
      }
      return hasCourse;
   }
   public String getCourses(){
      if (courses.size() > 0){
         String output = "List of course\n\n";
         for (Course oneCourse : courses){
            output += oneCourse.toString();
         }
         return output;
      }
      return "Instructor does not have courses.";
   }

   public Vector<String> getCourseList(){
      Vector<String> out = new Vector<String>();
      if (courses.size() > 0){
         //String output = "List of course\n\n";
         for (Course c : courses){
            //output += oneCourse.toString()+"\n";
            out.add("<html>ID: " + c.getCourseID() + "<br>Title: " + c.getTitle() + "<br>" + c.getDateStart() + "--" + c.getDateEnd()
                + "<br>" + c.getTimeStart() + " - " + c.getTimeEnd() + "<br><br></html>");
         }
         //return out;
      }
      else {
         out.add("Instructor does not have courses.");
      }
      
      return out;
      
   }
   
   //used to add one course to the courses list.
   public void setCourse(Vector<Course> course_list) {
      //Iterator it = course_list.iterator();
      boolean isset = false;
      //JOptionPane.showMessageDialog(null,"A list of course will be display\n\nPlease note down the course ID of the course to be added.");
      //String list = "List of available course:\n\n ";

      Vector<String> out = new Vector<String>();
      //list of course indexes to be selected by the user.
      Object[] options = new Object[course_list.size()];
      int index = 0;

      //fill the options with all the courses.
      for (Course c : course_list){
            //output += c.toString()+"\n";
            options[index++] = c.getCourseID();
            out.add("<html>ID: " + c.getCourseID() + "<br>Title: " + c.getTitle() + "<br>" + c.getDateStart() + "--" + c.getDateEnd()
                + "<br>" + c.getTimeStart() + " - " + c.getTimeEnd() + "<br><br></html>");
      }
      JList list = new JList (out);
      JScrollPane scroll  = new JScrollPane(list);
      scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
      

      //get user input.
      do{
         int course_index = JOptionPane.showOptionDialog(null,scroll,"Course Menu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
         if (course_index >= 0){
            if(courses.contains(course_list.get(course_index))){
               JOptionPane.showMessageDialog(null, "Instructor already signed up for that course");
               if (JOptionPane.showConfirmDialog(null,"Try again?") != JOptionPane.YES_OPTION){isset = true;}
            }else{
               courses.add(course_list.get(course_index));
               isset = true;
            }
         }
         else {JOptionPane.showMessageDialog(null,"Please choose a course");}
      } while (!isset);
           
   }

   public void dropCourse(String course){
      
      
      if(courses != null){
         
         Iterator it  = courses.iterator();
         while(it.hasNext()){
            Course one_course = (Course)it.next();
            if(one_course.getCourseID().equalsIgnoreCase(course)){
               //hasCourse = true;
               courses.remove(one_course);
            }
         }         
      }
      
   }
}


