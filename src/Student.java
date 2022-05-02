import java.util.Arrays;

public class Student extends Person {
	
	private static int numStudents = 0;
	private int studentID;
	private Course[] coursesTaken;
	private int numCoursesTaken;
	private boolean isGraduate;
	private String major = "undeclared";
	
	public Student() {
		coursesTaken = new Course[50];
		numCoursesTaken = 0;
		isGraduate = false;
		numStudents++;
		studentID = numStudents;
	}
	
	public Student(boolean isGraduate) {
		coursesTaken = new Course[50];
		numCoursesTaken = 0;
		this.isGraduate = isGraduate;
		numStudents++;
		studentID = numStudents;
	}
	
	public Student(String major,boolean isGraduate) {
		coursesTaken = new Course[50];
		numCoursesTaken = 0;
		this.major = major;
		this.isGraduate = isGraduate;
		numStudents++;
		studentID = numStudents;
	}
	
	public Student(String name,int birthYear,String major,boolean isGraduate) {
		super(name,birthYear);
		coursesTaken = new Course[50];
		numCoursesTaken = 0;
		this.major = major;
		this.isGraduate = isGraduate;
		numStudents++;
		studentID = numStudents;
	}
	
	public boolean isGraduate() {
		return isGraduate;
	}
	
	public int getNumCoursesTaken() {
		return numCoursesTaken;
	}
	
	public static int getNumStudents() {
		return numStudents;
	}
	
	public int getStudentID() {
		return studentID;
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setIsGraduate(boolean isGraduate) {
		this.isGraduate = isGraduate;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public void addCourseTaken(Course course) {
		if (numCoursesTaken < 50 && course != null) {
			coursesTaken[numCoursesTaken++] = course;
		}
	}
	
	public void addCoursesTaken(Course[] course) {
		if (numCoursesTaken < 50 && course != null) {
			for (int i = 0; i < course.length; i++) {
				addCourseTaken(course[i]);
			}
		}
	}
	
	public Course getCourseTaken(int index) {
		if (index >= coursesTaken.length || index < 0) {
			return null;
		}
		return coursesTaken[index];
	}
	
	public String getCourseTakenAsString(int index) {
		if (index >= coursesTaken.length || index < 0) {
			return "";
		}
		return coursesTaken[index].getCourseDept() + "-" + coursesTaken[index].getCourseNum();
	}
	
	public String getAllCoursesTakenAsString() {
		String courses = "";
		for (int i=0;i<numCoursesTaken;i++) {
			courses += getCourseTakenAsString(i) + ", ";
		}
		return courses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(coursesTaken);
		result = prime * result + (isGraduate ? 1231 : 1237);
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + numCoursesTaken;
		result = prime * result + studentID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (!Arrays.equals(coursesTaken, other.coursesTaken))
			return false;
		if (isGraduate != other.isGraduate)
			return false;
		if (major == null) {
			if (other.major != null)
				return false;
		} else if (!major.equals(other.major))
			return false;
		if (numCoursesTaken != other.numCoursesTaken)
			return false;
		if (studentID != other.studentID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String isGrad;
		if (isGraduate) {
			isGrad = "Graduate";
		}
		else {
			isGrad = "Undergraduate";
		}
		return String.format("%s Student: studentID: %04d | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s", super.toString(), studentID, getMajor(), isGrad, numCoursesTaken, getAllCoursesTakenAsString());
	}
	
	public int getTotalCredits() {
        int credits = 0;
        for(int i = 0; i < numCoursesTaken; i++) {
            credits+=coursesTaken[i].getNumCredits();
        }
        return credits;
    }
	
	@Override
	public int compareTo(Person p) {

		if (p instanceof Student) {
			Student s = (Student) p;
			if (this.getTotalCredits() == s.getTotalCredits())
				return 0;
			else if (this.getTotalCredits() > s.getTotalCredits()) 
				return 1;
			else
				return -1;
		}
		return 0;
	}
	
}
