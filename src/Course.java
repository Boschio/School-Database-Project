
public class Course implements Comparable<Course> {
	
	private boolean isGraduateCourse;
	private int courseNum;
	private String courseDept;
	private int numCredits;

	public Course(boolean isGraduateCourse,int courseNum,String courseDept,int numCredits) {
		this.isGraduateCourse = isGraduateCourse;
		this.courseNum = courseNum;
		this.courseDept = courseDept;
		this.numCredits = numCredits;
	}
	
	public boolean isGraduateCourse() {
		return isGraduateCourse;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public String getCourseDept() {
		return courseDept;
	}

	public int getNumCredits() {
		return numCredits;
	}
	
	public String getCourseName() {
		if (isGraduateCourse()) {
			return String.format("G%s%d", getCourseDept(),getCourseNum());
		}
		else {
			return String.format("U%s%d", getCourseDept(),getCourseNum());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseDept == null) ? 0 : courseDept.hashCode());
		result = prime * result + courseNum;
		result = prime * result + (isGraduateCourse ? 1231 : 1237);
		result = prime * result + numCredits;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseDept == null) {
			if (other.courseDept != null)
				return false;
		} else if (!courseDept.equals(other.courseDept))
			return false;
		if (courseNum != other.courseNum)
			return false;
		if (isGraduateCourse != other.isGraduateCourse)
			return false;
		if (numCredits != other.numCredits)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String isGrad = "";
		if (isGraduateCourse) {
			isGrad = "Graduate";
		}
		else {
			isGrad = "Undergraduate";
		}
		return String.format("Course: %3s-%3d | Number of Credits: %02d | %s", courseDept, courseNum, numCredits, isGrad);
	}
	
	@Override
	public int compareTo(Course c) {
		return Integer.compare(courseNum, c.courseNum);
	}

	
}
