/** IT 306 - 001 Project
  * Group: 
  *   Huy Nguyen G00982719
  *      Ben Dahlhauser G01104179
  *      Kevin Vo G00837111
  * 
  * Edit in real time
  */



import java.util.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class CodingSchoolImplementation{
   public static void main(String[] args){
      
      HashMap<String, Person> people = new HashMap<String, Person>();
      //Just to have something to test with
      Person newStu = new UnderGraduate("Ben","Dahlhauser","703-250-4088","123 clermont court","bendahl@live.com","S33333333","S3s-",false,"Status",4.0);
      people.put(newStu.getUserName(),newStu);
      Person student_2 = new Graduate("Jon","Smith","123-123-1234","1 Circle","random@live.com","S12345678","S1s-",false,"Status",2.0);
      people.put(student_2.getUserName(),student_2);
      Person newEmp = new Instructor("Tom","Smith","657-890-8888","123 clermont court","red@live.com","E33333333","E3e-",false);
      people.put(newEmp.getUserName(),newEmp);
      Person newMaint = new Administrator("Bill","Burr","999-999-9999","123 clermont court","apple@tv.com","E44444444","E4e-",true);
      people.put(newMaint.getUserName(),newMaint);
      //list of available courses.
      Vector<Course> course_list = new Vector<Course>();
      course_list.add(new Course("JAVA1006","Java Basic","Jan 07","May 10","1:00 pm","2:00 pm"));
      course_list.add(new Course("JAVA2006","Java Intermediate","Jan 07","May 10","3:00 pm","4:00 pm"));

      //The username to identify the user. Used as key to find the associating Person in the Hashmap.
      String username ="";

      //String login = "";
      boolean tryAgain = true;
      do{
            username = usernamePasswordInput(people,course_list);
            if(username.charAt(0)=='S'){
               //STUDENT MENU
               studentMenu(username,people,course_list);
            }else{
               if(username.charAt(0)=='E'){
                  //INSTRUCTOR MENU
                  instructorMenu(username,people,course_list);
               }else{
                  if(username.charAt(0)=='C'){
                     JOptionPane.showMessageDialog(null, "Created Account");
                  }else{
                	 if(username.charAt(0)=='I')
                     JOptionPane.showMessageDialog(null, "Incorrect username or password");
                  }
               }
            }

      }while(username.charAt(0)!='Q');
      
   }
   
   //get a list showing name and username based on the type: "S" for Student, "E" for Employee
   public static String get_list(HashMap<String, Person> people, char type){
      if (people.size() <= 0){
         return "No entry exists";
      }

      //list of person.
      //Vector<Person> list = new Vector<Person>;

      String list = "";
      Iterator it = people.values().iterator();
      while (it.hasNext()){         
         //get one entry from the list
         Person one_entry = (Person)(it.next());

         //get the user name of the entry.
         String username = one_entry.getUserName();
         if (username.charAt(0)==(type)){
            
            if(one_entry instanceof Student){
               Student stu = (Student)one_entry;
               list += stu.toString() + "\n\n";
            }
            if(one_entry instanceof Employee){
               Employee emp = (Employee)one_entry;
               if(emp instanceof Instructor){
                  Instructor inst = (Instructor)emp;
                  list += inst.toString() + "\n\n";
               }else{
                  list += emp.toString() + "\n\n";
               }
            }
            
            //list += "Name: "+ one_entry.getFirstName() + " ," + one_entry.getLastName() + "\nID: " + username +"\n\n";
         }
      }
      return list;
   }


   public static String usernamePasswordInput(HashMap<String,Person> people, Vector<Course> course_list){
      
      boolean login = false;
      String option = "";
      int STUDENT_LOGIN = 0;
      int EMPLOYEE_LOGIN = 1;
      int CREATED_ACCOUNT = 2;
      int INCORRECT_LOGIN = -1;
      
      JTextField username = new JTextField();
      JTextField password = new JPasswordField();
      Object[] message = {"Username:", username,"Password:", password};
      String[] options = {"Login", "Create Account", "Exit"};

      int choice = JOptionPane.showOptionDialog(null, message, "Login", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
      if(choice != -1){
         option = options[choice];
      }
      
      String username1 = username.getText();
      String password1 = password.getText();
      
      if(option == options[1]){
         createAccount(people, false, course_list);
         return "Created Account";
      }
      
      Student tempStu = null;
      Employee tempEmp = null;
      if(option.equals(options[0])){
         if(!people.isEmpty()){
            if(people.containsKey(username1)){
               if(people.get(username1) instanceof Student){
                  tempStu = (Student)people.get(username1);
                  if(password1.equals(tempStu.getPassword())){
                     login = true;
                  }else{
                     login = false;
                  }
               }else{
                  if(people.get(username1) instanceof Employee){
                     tempEmp = (Employee)people.get(username1);
                     if(password1.equals(tempEmp.getPassword())){
                        login = true;
                     }else{
                        login = false;
                     }
                  }
               
               }
            }
         }else{
            return "No people in system";
         }
      }
      
      
      if(login){
         if(username1.charAt(0) == ('S')){
            //LOGIN = STUDENT LOGIN
            return username1;
         }
         if(username1.charAt(0) == ('E')){
            //LOGIN = EMPLOYEE LOGIN
            return username1;
         }
      }
      
      if(option.equals(options[2])){
         return "Quit";
      }
      return "Incorrect Login";
   }
   
   public static void createAccount(HashMap<String,Person> people, boolean maintMade, Vector<Course> course_list){
      
      boolean notMaint = false;
      boolean isMaint = true;
      
      if(maintMade){
      
      
      
         String[] options = {"Student","Employee","Maintenance"};
         int option = -1;
         
         option = JOptionPane.showOptionDialog(null,"What kind of account would you like to create?","Account Creation",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
         switch(option){
               case 0:
                  //Create Student Account
                  Person stuPerson = new Student();
                  createAccounts(people, maintMade, stuPerson, notMaint, course_list);
                  break;
               case 1:
                  //Create Employee Account
                  Person empPerson = new Instructor();
                  createAccounts(people, maintMade, empPerson, notMaint, course_list);
                  break;
               case 2:
                  //Create Maintenance Account
                  Person empPerson2 = new Administrator();
                  createAccounts(people, maintMade, empPerson2, isMaint, course_list);
                  break;
         }
      }else{
         String[] options = {"Student"};
         int option = -1;
         Person stuPerson = null;
         option = JOptionPane.showOptionDialog(null,"Create a student account","Account Creation",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
         switch(option){
               case 0:
                  //Create Student Account
                  String[] student_types = {"Undergraduate", "Graduate"};
                  int type = JOptionPane.showOptionDialog(null,"Which type of student? ","Student Creation",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, student_types, student_types[0]);
                  switch(type){
                     case 0: 
                        stuPerson = new UnderGraduate();
                        break;
                     case 1:
                        stuPerson = new Graduate();
                        break;
                  }
                  
                  createAccounts(people, maintMade, stuPerson, notMaint, course_list);
                  break;
         }
      }
   }
   
   public static void createAccounts(HashMap<String,Person> people, boolean maintMade, Person person, boolean isMaint, Vector<Course> course_list){
      
      boolean set = false;

      
      if(person instanceof Student){
         do{
            try{
               String username = JOptionPane.showInputDialog("Enter username:\nExample: S######## or E########\n ");
               if(people.containsKey(username)){
                  JOptionPane.showMessageDialog(null, "--Username already taken--");
                  set = false;
               }else{
                  person.setUserName(username);
                  set = true;
               }
            }catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
         }while(!set);
         set = false;
         do{
            try{
               person.setPassword(JOptionPane.showInputDialog("Enter password:\nMust contain:\n1 Uppercase letter\n1 Lowercase letter\n1 Number\n1Special character\n "));
               set = true;
            }catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
         }while(!set);
         set = false;
         do{
            try{
               person.setFirstName(JOptionPane.showInputDialog("Enter first name: "));
               set = true;
            }catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
         }while(!set);
         set = false;
         do{
            try{
               person.setLastName(JOptionPane.showInputDialog("Enter last name: "));
               set = true;
            }catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
         }while(!set);
         set = false;
         do{
            try{
               person.setPhoneNumber(JOptionPane.showInputDialog("Enter phone number:\nExample: ###-###-####\n "));
               set = true;
            }catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
         }while(!set);
         set = false;
         do{
            try{
               person.setAddress(JOptionPane.showInputDialog("Enter address: "));
               set = true;
            }catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
         }while(!set);
         set = false;
         do{
            try{
               person.setEmail(JOptionPane.showInputDialog("Enter email:\nExample: example@email.com\n  "));
               set = true;
            }catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
         }while(!set);
         set = false;
         do{
            try{
               Student stu = (Student)person;
               stu.setGrade(Double.parseDouble(JOptionPane.showInputDialog("Enter grade of student: ")));
               set = true;
            }catch(NumberFormatException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
         }while(!set);
         set = false;
         do{
            try{
               if (person instanceof UnderGraduate){
                  ((UnderGraduate)person).setDegree(JOptionPane.showInputDialog("Enter the degee of the student: "));
               }
               if (person instanceof Graduate){
            	   ((Graduate)person).setProgram(JOptionPane.showInputDialog("Enter the program of the student: "));
               }
               set = true;
            }
            catch(IllegalArgumentException e) {JOptionPane.showMessageDialog(null, e.getMessage());}
         }while (!set);
         person.setIsMaintenance(isMaint);
         people.put(person.getUserName(),person);
         
         
      }else{
         if(person instanceof Employee){
            do{
               try{
                  person.setUserName(JOptionPane.showInputDialog("Enter username: "));
                  set = true;
               }catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
               }
            }while(!set);
            set = false;
            do{
               try{
                  person.setPassword(JOptionPane.showInputDialog("Enter password: "));
                  set = true;
               }catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
               }
            }while(!set);
            set = false;
            do{
               try{
                  person.setFirstName(JOptionPane.showInputDialog("Enter first name: "));
                  set = true;
               }catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
               }
            }while(!set);
            set = false;
            do{
               try{
                  person.setLastName(JOptionPane.showInputDialog("Enter last name: "));
                  set = true;
               }catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
               }
            }while(!set);
            set = false;
            do{
               try{
                  person.setPhoneNumber(JOptionPane.showInputDialog("Enter phone number:\nExample: ###-###-####\n "));
                  set = true;
               }catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
               }
            }while(!set);
            set = false;
            do{
               try{
                  person.setAddress(JOptionPane.showInputDialog("Enter address: "));
                  set = true;
               }catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
               }
            }while(!set);
            set = false;
            do{
               try{
                  person.setEmail(JOptionPane.showInputDialog("Enter email: "));
                  set = true;
               }catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
               }
            } while(!set);
            
            set = false;
            do{
               try{
                  if (person instanceof Instructor){
                     if (((Instructor)person).getOfficeHour() == null || ((Instructor)person).getOfficeHour().equals("")){
                    	   boolean keepGoing = false;
                        do{
                           keepGoing = false;
                           ((Instructor)person).setOfficeHour(JOptionPane.showInputDialog("Enter office hour in format (example: Mon 4:00 pm - 5:00pm): "));
                           if(JOptionPane.showConfirmDialog(null, "Would you like to enter another day for office hourse?","More", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                              keepGoing = true;
                           }
                        }while(keepGoing);   
                     }
                     if (((Instructor)person).getNumOfCouse() <= 0 ) {
                    	 ((Instructor)person).setCourse(course_list);
                     }
                  }
                  else if (person instanceof Administrator){
                     if (((Administrator)person).getOfficeNumber() == null || ((Administrator)person).getOfficeNumber().equals("")){
                      ((Administrator)person).setOfficeNumber(JOptionPane.showInputDialog("Enter office number: "));
                     }
                  }
                  
                  set = true;
               }catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
               }
            }while (!set); 
            set = false;
            person.setIsMaintenance(isMaint);
            people.put(person.getUserName(),person);
            

         }
      }
      
      
      
//       //CREATE ACCOUNT METHOD with VALIDATION OF PASSWORD AND USERNAME
//       String username = JOptionPane.showInputDialog("Enter username: ");
//       String fName = JOptionPane.showInputDialog("Enter first name: ");
//       String lName = JOptionPane.showInputDialog("Enter last name: ");
//       String phoneNumber = JOptionPane.showInputDialog("Enter phone number: ");
//       String address = JOptionPane.showInputDialog("Enter address: ");
//       String email = JOptionPane.showInputDialog("Enter email: ");
//       String password = JOptionPane.showInputDialog("Enter password: ");
//       
//       String isMaintenance = JOptionPane.showInputDialog("Enter whether maintenance (Y/N): ");
//       boolean realIsMaint = false;
//       if(isMaintenance.equalsIgnoreCase("Y")){
//          realIsMaint = true;
//       }
//       
// //       Person person = null;
//       if(username.charAt(0)=='S'){
//          person = new Student(fName,lName,phoneNumber,address,email,username,password,realIsMaint,"Status",4.0);
//       }else{
//          person = new Employee(fName,lName,phoneNumber,address,email,username,password,realIsMaint,"Faculty");
//       }
//       people.put(username,person);
      
   }
   
   
   public static void studentMenu(String username, HashMap<String, Person> people, Vector<Course> course_list){

      //retrieve the Student object from the list based on the username (ID).
      Student student = (Student)(people.get(username));

      Object[] menu = {"View Self","Modify Self","View Courses","View Employees","Drop Courses","Add One Course","Exit"};
      int option = -1;
      while(option != 6){
         option = JOptionPane.showOptionDialog(null,"Choose one option","Student Menu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);
         
         switch(option){
            case 0:
               //DISPLAY STUDENT INFORMATION TO USER
               //stuDisplayInfo(student);
               JOptionPane.showMessageDialog(null, student.toString());
               break;
            case 1:
               //MODIFY STUDENT INFORMATION
               student = stuModifyInfo(student,course_list);
               people.put(username,student);
               break;
            case 2:
               //DISPLAY STUDENTS COURSES
               //stuDisplayCourses(student);               
               JOptionPane.showMessageDialog(null,student.getCourses());
               break;
            case 3:
               //DISPLAY EMPLOYEES IN SYSTEM
               JOptionPane.showMessageDialog(null,get_list(people,'E'));
               break;
            case 4:
               //DELETE COURSE FROM STUDENT
               //stuDeleteCourse(student);
               //student.dropCourse(JOptionPane.showInputDialog("Enter course ID to drop"));
               people.put(username,student);
               break;
            case 5:
               //ADD ONE COURSE.              
               student.setCourse(course_list);
               break;
            case 6:
               //EXIT               
               writeToFile(people);
               return;
         }
      }
   }
   
   
   
   public static Student stuModifyInfo(Student student,Vector<Course> course_list){
      String menu = "What value would you like to change?\n1- ID\n2- Name\n3- Phone Number\n4- Email\n5- Address\n";
      String[] options = {"Name","Phone Number","Email","Address","Exit"};
      int option = -1;
      while(option != 4){
         option = JOptionPane.showOptionDialog(null,"What would you like to modify?","Student Menu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
         //option = Integer.parseInt(JOptionPane.showInputDialog(menu));
         switch (option){
            case 0:
               //CHANGE Name
               //stuChangeName();
               student.setFirstName(JOptionPane.showInputDialog("Enter new first name for the student"));
               student.setLastName(JOptionPane.showInputDialog("Enter new last name for the student"));
               break;
            case 1:
               //CHANGE PHONE NUMBER
               //stuChangePhoneNumber();
               student.setPhoneNumber(JOptionPane.showInputDialog("Enter new phone number for the student"));
               break;
            case 2:
               //CHANGE EMAIL
               //stuChangeEmail();
               student.setEmail(JOptionPane.showInputDialog("Enter new email for the student"));
               break;
            case 3:
               //CHANGE ADDRESS
               //stuChangeAddress();
               student.setAddress(JOptionPane.showInputDialog("Enter new address for the student"));
               break;
            case 4:
               return student;
            
         }
      }
      return student;
   }    
   
   //INSTRUCTOR MENU
   public static void instructorMenu(String username, HashMap<String, Person> people, Vector<Course> course_list){
      
      Person newPerson = (Employee)people.get(username);
      
      if(newPerson.getIsMaintenance() == true){
         maintenanceMenu(username, people, course_list);
      }else{
      

         Object[] menu = {"Modify Student Grade","Modify Course","Drop Student","Exit"};
         int option = -1;
         while(option != 3){
            option = JOptionPane.showOptionDialog(null,"Choose one option","Instructor Menu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);
//          String menu = "1- Modify Student Grade\n2- Modify Course\n3- Drop Student\n4- Exit";
//          int option = -1;
//          while(option != 4){
//             option = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(option){
               case 0:
                  //MODIFY STUDENT GRADE
                  instModifyStuGrade(people);
                  break;
               case 1:
                  //MODIFY COURSE
                  instModCourse(course_list);
                  break;
               case 2:
                  //DROP STUDENT
                  instDropStu(people);
                  break;
               case 3:
                  //EXIT
                  return;
            }
         }
      }
   }  
   
   public static void instModifyStuGrade(HashMap<String,Person> people){
      boolean gradeSet = false;
      String stuName = "";
      do{
         stuName = JOptionPane.showInputDialog("Enter the username of the student you would like to modify: ");
         //VALIDATE STUDENT NAME
         if(people.containsKey(stuName)){
            Student tempStu = (Student)people.get(stuName);
            tempStu.setGrade(Double.parseDouble(JOptionPane.showInputDialog("What new grade would you like to set for " + tempStu.getFirstName() + "?")));
            
         }else{
            JOptionPane.showMessageDialog(null, "No student with that username");
         }
         gradeSet = true;
      }while(!gradeSet);
      //ENTER NEW GRADE FOR STUDENT
      
   }
   
   public static void instModCourse(Vector<Course> course_list){
      boolean realCourse = false;
      String whichCourse = "";
      int size = course_list.size();
      Course[] arr = new Course[size];
      course_list.copyInto(arr);
      
      do{
         whichCourse = JOptionPane.showInputDialog("Enter course ID of course you would like to modify: ");
         if(!course_list.isEmpty()){
            for(int i = 0; i < arr.length; i++){
               if(arr[i].getCourseID().equals(whichCourse)){
                  modCourse(i, course_list);
               }else{
                  JOptionPane.showMessageDialog(null, "No courses with that ID");
               }
            }
         }else{
            JOptionPane.showMessageDialog(null, "No courses in list");
         }
         
         realCourse = true;
      }while(!realCourse);
      
      
      
   }
   
   public static void modCourse(int index, Vector<Course> course_list){
      Course course = course_list.get(index);
      

      
      Object[] menu = {"Course","Start Date","End Date","Start Time","End Time","Exit"};
      int option = -1;
      while(option != 5){
         option = JOptionPane.showOptionDialog(null,"Which would you like to modify","Modify Course Menu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);
      
//       String menu = "Modify:\n1- Course ID\n2- Start Date\n3- End Date\n4- Start Time\n5- End Time\n6- Exit";
//       int option = -1;
//       while(true){
//          option = Integer.parseInt(JOptionPane.showInputDialog(menu));
         switch(option){
            case 0:
               //MODIFY COURSE ID
               //modCourseID();
               String newID = JOptionPane.showInputDialog("Enter new ID for course: ");
               course.setCourseID(newID);
               JOptionPane.showMessageDialog(null, "New ID has been set");
               break;
            case 1:
               //MODIFY START DATE
               //modCourseStartDate();
               String newStartD = JOptionPane.showInputDialog("Enter new Start Date for course: ");
               course.setDateStart(newStartD);
               JOptionPane.showMessageDialog(null, "New Start Date has been set");
               break;
            case 2:
               //MODIFY END DATE
               //modCourseEndDate();
               String newEndD = JOptionPane.showInputDialog("Enter new End Date for course: ");
               course.setDateEnd(newEndD);
               JOptionPane.showMessageDialog(null, "New Start Date has been set");
               break;
            case 3:
               //MODIFY START TIME
               //modCourseStartTime();
               String newStartT = JOptionPane.showInputDialog("Enter new Start Time for course: ");
               course.setDateEnd(newStartT);
               JOptionPane.showMessageDialog(null, "New Start Date has been set");
               break;
            case 4:
               //MODIFY END TIME
               //modCourseEndTime();
               String newEndT = JOptionPane.showInputDialog("Enter new End Time for course: ");
               course.setDateEnd(newEndT);
               JOptionPane.showMessageDialog(null, "New Start Date has been set");
               break;
            case 5:
               return;
         }
      }
   }
   
   
   public static void instDropStu(HashMap<String,Person> people){
      String stuID = JOptionPane.showInputDialog("Enter username of student you want to drop: ");
      String courseID = JOptionPane.showInputDialog("Enter course ID for course you want to drop: ");
      if(!people.isEmpty()){
         if(people.containsKey(stuID)){
            Student student = (Student)people.get(stuID);
            if(student.getCourse(courseID)){
               student.dropCourse(courseID);
               JOptionPane.showMessageDialog(null, "Course successfully dropped");
            }else{
               JOptionPane.showMessageDialog(null, "Student is not registered for that course");
            }
         }else{
            JOptionPane.showMessageDialog(null, "No student matching that username");
         }
      }else{
         JOptionPane.showMessageDialog(null, "No students in list");
      }
      
      
   }
   
   //MAINTENANCE MENU
   public static void maintenanceMenu(String username, HashMap<String, Person> people, Vector<Course> course_list){
      
      Employee emp = (Employee)people.get(username);
      
      
      Object[] menu = {"View Students","Add Students","Modify Students","View Employees","Add Employees","Modify Employees","View Courses","Add Courses","Modify Courses","Exit"};
      int option = -1;
      while(option != 9){
         option = JOptionPane.showOptionDialog(null,"Choose one option","Maintenance Menu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);
         switch(option){
            case 0:
               //VIEW STUDENTS
               //maintViewStus();
               JOptionPane.showMessageDialog(null,get_list(people,'S'));
               break;
            case 1:
               //ADD STUDENTS = call createAccount() 
               //maintAddStu();
               createAccount(people, false, course_list);
               break;
            case 2:
               //MODIFY STUDENTS
               //maintModStu();
               String student_username = JOptionPane.showInputDialog("Enter student ID: ");
               if(people.containsKey(student_username)){
                  Student tempStu = (Student)people.get(student_username);
                  tempStu = stuModifyInfo(tempStu,course_list);
               }else{
                  JOptionPane.showMessageDialog(null, "No student with that username");
               }
               break;
            case 3:
               //VIEW EMPLOYEES
               //maintViewEmps();
               JOptionPane.showMessageDialog(null,get_list(people,'E'));
               break;
            case 4:
               //ADD EMPLOYEES = call createAccount()
               //maintAddEmp();
               createAccount(people, true, course_list);
               break;
            case 5:
               //MODIFY EMPLOYEES
               //maintModEmp();
               String emp_username = JOptionPane.showInputDialog("Enter employee ID: ");
               if(people.containsKey(emp_username)){
                  Employee tempEmp = (Employee)people.get(emp_username);
                  tempEmp = empModifyInfo(tempEmp,course_list);
               }else{
                  JOptionPane.showMessageDialog(null, "No employee with that username");
               } 
               break;
            case 6:
               //VIEW COURSES
               //maintViewCourses();
               String output = "List of course\n\n";
               if (course_list.size() > 0){                  
                  for (Course oneCourse : course_list){
                     output += oneCourse.toString() + "\n";
                  }
               }
               else { 
                  output += "Student does not have courses.";
               }
               JOptionPane.showMessageDialog(null,output) ;
               //JOptionPane.showMessageDialog(null, course_list.toString());
               break;
            case 7:
               //ADD COURSES
               //maintAddCourse();
               boolean isset = false;
               do{
                  try {
                     String courseID = JOptionPane.showInputDialog("Enter ID:");
                     String title = JOptionPane.showInputDialog("Enter title:");
                     String dateStart = JOptionPane.showInputDialog("Enter starting date:");
                     String dateEnd = JOptionPane.showInputDialog("Enter ending date:");
                     String timeStart = JOptionPane.showInputDialog("Enter starting time:");
                     String timeEnd = JOptionPane.showInputDialog("Enter ending time:");
                     course_list.add(new Course( courseID,  title,  dateStart,  dateEnd,  timeStart,  timeEnd));
                     isset = true;
                  }
                  catch(IllegalArgumentException e) { JOptionPane.showMessageDialog(null, e.getMessage());}
               }while(!isset);
               writeToFile(course_list);
               break;
            case 8:
               //MODIFY COURSES
               //need to complete
               //maintModCourse();
               instModCourse(course_list);
               break;
            case 9:
               //EXIT
               writeToFile(people);
               writeToFile(course_list);
               return;
            
         }
      }
   }
   
   public static Employee empModifyInfo(Employee employee,Vector<Course> course_list){
      String menu = "What value would you like to change?\n1- ID\n2- Name\n3- Phone Number\n4- Email\n5- Address\n";
      String[] options = {"Name","Phone Number","Email","Address","Exit"};
      int option = -1;
      while(option != 4){
         option = JOptionPane.showOptionDialog(null,"What would you like to modify?","Employee Menu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
         //option = Integer.parseInt(JOptionPane.showInputDialog(menu));
         switch (option){
            case 0:
               //CHANGE Name
               //stuChangeName();
               employee.setFirstName(JOptionPane.showInputDialog("Enter new first name for the employee"));
               employee.setLastName(JOptionPane.showInputDialog("Enter new last name for the employee"));
               break;
            case 1:
               //CHANGE PHONE NUMBER
               //stuChangePhoneNumber();
               employee.setPhoneNumber(JOptionPane.showInputDialog("Enter new phone number for the employee"));
               break;
            case 2:
               //CHANGE EMAIL
               //stuChangeEmail();
               employee.setEmail(JOptionPane.showInputDialog("Enter new email for the employee"));
               break;
            case 3:
               //CHANGE ADDRESS
               //stuChangeAddress();
               employee.setAddress(JOptionPane.showInputDialog("Enter new address for the employee"));
               break;
            case 4:
               return employee;
            
         }
      }
      return employee;
   }
   
      
   public static void writeToFile(HashMap<String,Person> people){
      String out_path  = "./user.txt";
      boolean isset = false;
      String output_student = "List of student\n************************************************\n";
      String output_emp = "List of employees\n************************************************\n";
      String output = "List of user in format (Name - username : password)\n************************************************\n";
      do{
         try{      
            
            Iterator it = people.values().iterator();
            while (it.hasNext()){         
               
               Person one_entry = (Person)(it.next());
               output += one_entry.getFirstName() +" "+ one_entry.getLastName() +" - " + one_entry.getUserName() + " : " + one_entry.getPassword() +"\n\n";
               if (one_entry instanceof UnderGraduate){
                  output_student += one_entry.toString() + "\n\n--------------------------\n";
                  
               }
               else if (one_entry instanceof Graduate){
                  output_student += one_entry.toString() + "\n\n--------------------------\n";
                  
               } 
               else if (one_entry instanceof Instructor){
                  output_emp += one_entry.toString() + "\n\n--------------------------\n";
                  
               }
               else if (one_entry instanceof Administrator){
                  output_emp += one_entry.toString() + "\n\n--------------------------\n";
                  
               }
               
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(out_path))); 
            bw.write(output); 
            
            bw.close();

            out_path = "./student.txt";
            bw = new BufferedWriter(new FileWriter(new File(out_path))); 
            bw.write(output_student); 
            
            bw.close();

            out_path = "./employee.txt";
            bw = new BufferedWriter(new FileWriter(new File(out_path))); 
            bw.write(output_emp); 
            
            bw.close();
            
            isset = true;
              
         }
         catch(IllegalArgumentException e) {}
         
         catch(FileNotFoundException e){
            e.printStackTrace(); 
         }
         catch( IOException e){
            e.printStackTrace(); 
         }
         
      }while (!isset);      
   }
   
   public static void writeToFile(Vector<Course> course_list){
      String out_path  = "./course.txt";
      boolean isset = false;
      do{
         try{      
            String output = "List of Course\n************************************************\n";   
            for (Course c: course_list){
               output += c.toString() + "\n--------------------------\n";
            }         
            
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(out_path))); 
            bw.write(output); 
            isset = true;
            bw.close();
                
         }
         catch(FileNotFoundException e){
            e.printStackTrace(); 
         }
         catch( IOException e){
            e.printStackTrace(); 
         }
      }while (!isset);
   }
   
   
}
