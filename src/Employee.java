
public class Employee extends Person {

	private String deptName;
	private static int numEmployees = 0;
	private int employeeID;
	
	public Employee() {
		this.deptName = "";
		numEmployees++;
		this.employeeID = numEmployees;
	}
	
	public Employee(String deptName) {
		this.deptName = deptName;
		numEmployees++;
		this.employeeID = numEmployees;
	}
	
	public Employee(String name, int birthYear, String deptName) {
		super.setName(name);
		super.setBirthYear(birthYear);		
		this.deptName = deptName;
		numEmployees++;
		this.employeeID = numEmployees;
	}


	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public static int getNumEmployees() {
		return numEmployees;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result + employeeID;
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
		Employee other = (Employee) obj;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		if (employeeID != other.employeeID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s Employee: Department: %20s | Employee Number: %3d", super.toString(), deptName, employeeID);
	}
	
	@Override
	public int compareTo(Person p) {
		int parent = super.compareTo(p);
		if (p instanceof Employee) {
			Employee e = (Employee) p;
			if (parent > 0) {
				if (this.getEmployeeID() > e.getEmployeeID()) {
					return 1;
				}
			} else if (parent < 0) {
				if (this.employeeID < e.employeeID) {
					return -1;
				}
			}
			return 0;
		}
		return parent;
	}
	
}
