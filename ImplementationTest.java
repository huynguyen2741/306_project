package standard;

import static org.junit.Assert.assertEquals;

import java.util.*;
import org.junit.Test;
import javax.swing.JOptionPane;
public class ImplementationTest {

	@Test
	public void testCourseClass() {
		Course aCourse = new Course("JAVA1001");
		aCourse.setCourseID("MATH1006");
		aCourse.setTitle("Algebra");
		aCourse.setDateStart("Jan 10");
		aCourse.setDateEnd("Math 14");
		aCourse.setTimeStart("1:15pm");
		aCourse.setTimeEnd("2:20 pm");	
		assertEquals("MATH1006", aCourse.getCourseID());
		assertEquals("Algebra", aCourse.getTitle());
		assertEquals("Jan 10", aCourse.getDateStart());
		assertEquals("Math 14", aCourse.getDateEnd());
		assertEquals("1:15pm", aCourse.getTimeStart());
		assertEquals("2:20 pm", aCourse.getTimeEnd());
		//assertEquals("Course ID:MATH1006Title:AlgebraDatestart:Jan10Dateend:Math14Timestart:1:15pmTimeend:2:20pm", aCourse.toString());
		String course = "JAVA1006";
		Vector<Course> courses = new Vector<Course>();
		courses.add(new Course("JAVA1006","Java Basic", "Jan 07", "May 10","1:00 pm", "2:00 pm"));
		assertEquals(1, courses.size());
		Course one_course = (Course)courses.get(0);
		if(one_course.getCourseID().equalsIgnoreCase(course)){
			courses.remove(0);
		}
		assertEquals(0, courses.size());
		
		Person aPerson = new Person();
		aPerson.setFirstName("Kevin");
		aPerson.setLastName("Vo");
		aPerson.setPhoneNumber("111-222-3333");
		aPerson.setEmail("Kevin@gmail.com");
		aPerson.setUserName("S33333333");
		aPerson.setPassword("S3s-");
		aPerson.setIsMaintenance(false);
		assertEquals("Kevin", aPerson.getFirstName());
		assertEquals("Vo", aPerson.getLastName());
		assertEquals("1112223333", aPerson.getPhoneNumber());
		assertEquals("Kevin@gmail.com", aPerson.getEmail());
		assertEquals("S33333333", aPerson.getUserName());
		assertEquals("S3s-", aPerson.getPassword());
		assertEquals(false, aPerson.getIsMaintenance());
		
		Instructor anInstructor = new Instructor();
		anInstructor.setOfficeHour("Mon 4:00 pm - 5:00pm");
		//assertEquals("Mon 4:00 pm - 5:00pm", anInstructor.getOfficeHour());
		Vector<Course> course_list = new Vector<Course>();
		course_list.add(new Course("JAVA1006","Java Basic","Jan 07","May 10","1:00 pm","2:00 pm"));
		course_list.add(new Course("JAVA1007","Java Basic A","Jan 07","May 10","2:00 pm","4:00 pm"));
		
		
		anInstructor.setOfficeHour("Mon 2:00 pm");
		//assertEquals("Mon 2:00 pm",anInstructor.getOfficeHour());
		anInstructor.setCourse(course_list);
		anInstructor.getNumOfCouse();
		anInstructor.dropCourse("JAVA1007");
		
		Administrator newMaint = new Administrator("Bill","Burr","999-999-9999","123 clermont court","apple@tv.com","E44444444","E4e-",true);
		newMaint.setOfficeNumber("A123");
		assertEquals("A123", newMaint.getOfficeNumber());
		
		Graduate graduateStudent = new Graduate();
		graduateStudent.setProgram("Info Security");
		assertEquals("Info Security", graduateStudent.getProgram());
		
		UnderGraduate undergraduateStudent = new UnderGraduate();
		undergraduateStudent.setDegree("Computer Science");
		assertEquals("Computer Science", undergraduateStudent.getDegree());
		
//		Instructor
//		getNumOfCouse
//		getCourses
//		getCourseList
//		setCourse
//		dropCourse
//		
//		Student
//		getCourse
//		getCourses
//		getCourseList
//		setCourse
//		dropCourse
		
		
		
		
	}
}