//Adam Vasquez
import java.util.*;
import java.lang.Integer;

public class EnrollmentSystem {
	//Initialize maps to store student and course information and priority queue to manage course scheduling
	private Map<Integer, Student> studentInfo;
	private Map<Integer, Course> courseInfo;
	private PriorityQueue<Course> courseScheduling;
	
	public EnrollmentSystem() {	//Creates a constructor for EnrollmentSystem class and creates EnrollmentSystem object
		//Attributes of EnrolllmentSystem object, studentInfo and courseInfo hash map, and courseScheduling priority queue
		this.studentInfo = new HashMap<>();
		this.courseInfo = new HashMap<>();	
		this.courseScheduling = new PriorityQueue<>(Comparator.comparingInt(c -> c.getEnrolledStudents().size()));
	}
	
	public void addStudent(Student student) { //Method that adds student to map containing student information
		studentInfo.put(student.getStudentId(), student);
	}
	
	public void removeStudent(int studentId) { //Method that removes student from map that contains student information
		studentInfo.remove(studentId);
	}
	
	public void addCourse(Course course) { //Method that adds course to map that contains course information and to priority queue that handles course scheduling
		courseInfo.put(course.getCourseId(), course);
		courseScheduling.offer(course);
	}
	
	public void removeCourse(int courseId) { //Method that removes course from map that contains course information
		courseInfo.remove(courseId);
	}
	
	public void modifyCourse(int courseId, String courseName, String instructor, int capacity) { //Method that allows the administrator to modify a course
        Course course = courseInfo.get(courseId);
        //Check if course ID is valid
        if (course != null) { 
            course.setCourseName(courseName);
            course.setInstructor(instructor);
            course.setCapacity(capacity);
            courseScheduling.remove(course);
            courseScheduling.offer(course);
        } else {
            System.out.println("Error: Course Not Found"); //When course ID entered was not valid
        }
    }
	
	public boolean enrollStudentCourse(int studentId, int courseId) { 
		//Method that allows the student to enroll in a course if the course is not full, if they haven't previously enrolled in the course, and if the student ID/course ID is valid
		if (!studentInfo.containsKey(studentId)) {
            System.out.println("Student with ID " + studentId + " does not exist.");
            return false;
        }
        // Check if course exists
        if (!courseInfo.containsKey(courseId)) {
            System.out.println("Course with ID " + courseId + " does not exist.");
            return false;
        }
        Student student = studentInfo.get(studentId);
        Course course = courseInfo.get(courseId);
        // Check if student is already enrolled in the course
        if (course.getEnrolledStudents().contains(student)) {
            System.out.println("Error: Cannot enroll in the same course twice.");
            return false;
        }
        // Check course capacity
        if (course.getEnrolledStudents().size() >= course.getCapacity()) {
            System.out.println("Course is already full. Cannot enroll student " + student.getName() + ".");
            return false;
        }
        // Enroll student in the course
        course.getEnrolledStudents().add(student);
        System.out.println("Successfully enrolled in " + course.getCourseName());
        return true;
    }

	
	
	public boolean dropStudentCourse (int studentId, int courseId) { //Method that allows students to drop from a course if course isn't empty, the student can be found in the course
		Student student = studentInfo.get(studentId);
		Course course = courseInfo.get(courseId);
		
		if(course != null && student != null && course.dropStudent(student)) {
			courseScheduling.remove(course);
			courseScheduling.offer(course);
			System.out.println("Successfully dropped from " + course.getCourseName());
			return true;
		}
		else {
			System.out.println("Error: Course or Student Not Found");
			return false;
		}
	}
	
	public void displayStudentDetails(int studentId) { //Method that displays student details
		Student student = studentInfo.get(studentId);
		//Checks if the student is valid by checking studentInfo hash map
		if(student != null) {
			//Displays student details
			System.out.println("Student ID: " + student.getStudentId());
			System.out.println("Name:       " + student.getName());
			System.out.println("Age:        " + student.getAge());
			System.out.println("Major:      " + student.getMajor());
		}
		else {
			System.out.println("Error: Student Not Found"); //If the student was not in the system
		}
	}
	
	public void displayCourseDetails(int courseID) {
		Course course = courseInfo.get(courseID);
		//Checks if the course is valid by checking couseInfo hash map
		if(course != null) {
			//Displays course details
			System.out.println("Course ID:         " + course.getCourseId());
			System.out.println("Course Name:       " + course.getCourseName());
			System.out.println("Instructor:        " + course.getInstructor());
			System.out.println("Capacity:          " + course.getCapacity());
			System.out.print("Enrolled Students: [");
	        for (Student student : course.getEnrolledStudents()) { //Goes through enrolledStudents list and prints out the students enrolled in the course
	            System.out.print(student.toString() + " "); 
	        }
	        
	        System.out.println("]");
		}
		else {
			System.out.println("Error: Course Not Found"); //If the course was not in the system
		}
	}
	
	public void displayEnrollmentStatus(int courseId) { //Method that displays enrollment status
        Course course = courseInfo.get(courseId);
        //Checks if the course is valid by checking couseInfo hash map
        if (course != null) {
        	//Displays enrollment status for specified course
            System.out.println("Enrollment status for " + course.getCourseName() + ":");
            System.out.println("Enrolled Students: " + course.getEnrolledStudents().size() + "/" + course.getCapacity());
        } else {
            System.out.println("Error: Course Not Found."); //If the course was not found in courseInfo hash map
        }
    }

    
    public void displayCourseScheduling() { //Method that displays course scheduling
        System.out.println("Course scheduling based on the number of enrolled students:");
        while (!courseScheduling.isEmpty()) { 
            Course course = courseScheduling.poll();
            System.out.println(course.getCourseName() + ": " + course.getEnrolledStudents().size() + " enrolled students");
        }
    }
}
