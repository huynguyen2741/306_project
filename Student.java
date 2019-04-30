import javax.swing.JOptionPane;
import java.util.*;

public class Student extends Person{
   private String status;
   private double grade;
   
   private Vector<Course> courses = new Vector<Course>();
   
   public static final int MIN_NUM_COURSE = 1;
   public static final int MAX_NUM_COURSE = 5;
   
   public Student(){
      super();
   }   
   
   public Student(String firstName, String lastName, String phoneNumber, String address, String email,String userName,String password, boolean isMaintenance, String status, double grade){
      super(firstName,lastName,phoneNumber,address,email,userName,password,isMaintenance);
      this.status = status;
      this.grade = grade;
   }
   

   
   public String getStatus(){
      return status;
   }
   public double getGrade(){
      return grade;
   }
   public void setGrade(double grade){
      this.grade = grade;
   }
   
   public void setStatus(String status){
      this.status = status;
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
            output += oneCourse.toString()+"\n";
         }
         return output;
      }
      return "Student does not have courses.";
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
      //boolean hasCourse = false;
      int count = 0;
      int index = -1;
      if(courses != null){
         
         Iterator it  = courses.iterator();
         while(it.hasNext()){
            Course one_course = (Course)it.next();
            if(one_course.getCourseID().equalsIgnoreCase(course)){
               
               index = count;
            }
            count++;
         }      
         if(index != -1){
            courses.remove(index);
         }
      }
      
   }
      
   public String toString(){
      return super.toString() + "\nGrade: " + this.getGrade() + "\n" + getCourses();
   }
   
}

