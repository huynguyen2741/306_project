import java.util.*;
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
   
   //used to add one course to the courses list.
   public void setCourse(Vector<Course> course_list) {
      Iterator it = course_list.iterator();
      JOptionPane.showMessageDialog(null,"A list of course will be display\n\nPlease note down the course ID of the course to be added.");
      String list = "List of available course:\n\n ";

      Object[] options = new Object[course_list.size()];

      boolean isset = false;
      do{
         int index = 0;
         while( it.hasNext()){
         Course one_course = (Course)it.next();
            list+= one_course.toString() + "\n\n";
            options[index++] = one_course.getCourseID();
         }
         JOptionPane.showMessageDialog(null, list);
         int course_index = JOptionPane.showOptionDialog(null,"Please choose the course ID","Course Menu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
         if (course_index >= 0){
            if(courses.contains(course_list.get(course_index))){
               JOptionPane.showMessageDialog(null, "Student already signed up for that course");
               isset = false;
            }else{
               courses.add(course_list.get(course_index));
               isset = true;
            }
         }
         else {JOptionPane.showMessageDialog(null,"Please choose a course");}
      }while (!isset);      
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


