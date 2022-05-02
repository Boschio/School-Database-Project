import java.util.Arrays;

public class Faculty extends Employee {

	private Course[] coursesTaught;
	private int numCoursesTaught;
	private boolean isTenured;
	
	public Faculty() {
		super();
		numCoursesTaught = 0;
		coursesTaught = new Course[100];
		isTenured = false;
	}
	
	public Faculty(boolean isTenured) {
		super();
		numCoursesTaught = 0;
		coursesTaught = new Course[100];
		this.isTenured = isTenured;
	}
	
	public Faculty(String deptName, boolean isTenured) {
		super(deptName);
		//super.setDeptName(deptName);
		numCoursesTaught = 0;
		coursesTaught = new Course[100];
		this.isTenured = isTenured;
	}
	
	public Faculty(String name, int birthYear, String deptName, boolean isTenured) {
		super(name,birthYear,deptName);
		//super.setName(name);
		//super.setBirthYear(birthYear);
		//super.setDeptName(deptName);
		numCoursesTaught = 0;
		coursesTaught = new Course[100];
		this.isTenured = isTenured;
	}
	
	public boolean isTenured() {
		return isTenured;
	}
	
	public int getNumCoursesTaught() {
		return numCoursesTaught;
	}
	
	public void setIsTenured(boolean isTenured) {
		this.isTenured = isTenured;
	}
	
	public void addCourseTaught(Course course) {
		if (numCoursesTaught < 100 && course != null) {
			coursesTaught[numCoursesTaught++] = course;
		}
	}
	
	public void addCoursesTaught(Course[] course) {
		if (numCoursesTaught < 100 && course != null) {
			for (int i = 0; i < course.length; i++) {
				addCourseTaught(course[i]);
			}
		}
	}
	
	public Course getCourseTaught(int index) {
		if (index >= numCoursesTaught || index < 0) {
			return null;
		}
		return coursesTaught[index];
	}

	public String getCourseTaughtAsString(int index) {
		if (index >= coursesTaught.length || index < 0) {
			return "";
		}
		return coursesTaught[index].getCourseDept() + "-" + coursesTaught[index].getCourseNum();
	}
	
	public String getAllCoursesTaughtAsString() {
		String courses = "";
		for (int i=0;i<numCoursesTaught;i++) {
			courses += getCourseTaughtAsString(i) + ", ";
		}
		return courses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(coursesTaught);
		result = prime * result + (isTenured ? 1231 : 1237);
		result = prime * result + numCoursesTaught;
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
		Faculty other = (Faculty) obj;
		if (!Arrays.equals(coursesTaught, other.coursesTaught))
			return false;
		if (isTenured != other.isTenured)
			return false;
		if (numCoursesTaught != other.numCoursesTaught)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		
		String tenured;
		if (isTenured) {
			tenured = "Is Tenured";
		}
		else {
			tenured = "Not Tenured";
		}
		return String.format("%s Faculty: %11s | Number of Courses Taught: %3d | Courses Taught: %s",super.toString(), tenured, numCoursesTaught, getAllCoursesTaughtAsString());

	}
	
	@Override
	public int compareTo(Person p) {

		if (p instanceof Faculty) {
			Faculty f = (Faculty) p;
			if (this.numCoursesTaught == f.numCoursesTaught)
				return 0;
			else if (this.numCoursesTaught > f.numCoursesTaught) 
				return 1;
			else
				return -1;
		}
		return 0;
	}
	
}
