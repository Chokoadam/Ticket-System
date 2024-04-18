//Adam Vasquez
import java.util.ArrayList;
import java.util.List;

public class Course {
	//Initialize student attributes
	private int courseId;
	private String courseName;
	private String instructor;
	private int capacity;
	private List<Student> enrolledStudents;
	
	public Course(int courseId, String courseName, String instructor, int capacity) { //Creates constructor for Course class that creates Course object
		this.courseId = courseId;
		this.courseName = courseName;
		this.instructor = instructor;
		this.capacity = capacity;
		this.enrolledStudents = new ArrayList<>();
	}
	
	public int getCourseId() { //Getter method for course ID
		return courseId;
	}
	
	public void setCourseId(int courseID) { //Setter method for course ID
		this.courseId = courseID;
	}
	
	public String getCourseName() { //Getter method for course name
		return courseName;
	}
	
	public void setCourseName(String courseName) { //Setter method for course name
		this.courseName = courseName;
	}
	
	public String getInstructor() { //Getter method for instructor
		return instructor;
	}
	
	public void setInstructor(String instructor) { //Setter method for instructor
		this.instructor = instructor;
	}
	
	public int getCapacity() { //Getter method for capacity
		return capacity;
	}
	
	public void setCapacity(int capacity) { //Setter method for capacity
		this.capacity = capacity;
	}
	
	public List<Student> getEnrolledStudents() { //Getter method for enrolled students list
		return enrolledStudents;
	}
	
	public boolean enrollStudent(Student student) { //Method that enrolls students only if the size of enrolled students list is less than the capacity of the oourse
		if(enrolledStudents.size() < capacity) {
			enrolledStudents.add(student);
			return true;
		}
		else { //returns false if the amount of enrolled students surpass the course capacity
			return false;
		}
	}
	
	public boolean dropStudent(Student student) { //Method that drops students from course
		return enrolledStudents.remove(student);
	}
}
