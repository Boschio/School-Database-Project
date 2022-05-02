
public class GeneralStaff extends Employee {
	
	private String duty;
	
	public GeneralStaff() {
		duty = "";
	}
	
	public GeneralStaff(String duty) {
		this.duty = duty;
	}
	
	public GeneralStaff(String deptName,String duty) {
		setDeptName(deptName);
		this.duty = duty;
	}
	
	public GeneralStaff(String name,int birthYear,String deptName,String duty) {
		setName(name);
		setBirthYear(birthYear);
		setDeptName(deptName);
		this.duty = duty;
	}
	
	public String getDuty() {
		return duty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((duty == null) ? 0 : duty.hashCode());
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
		GeneralStaff other = (GeneralStaff) obj;
		if (duty == null) {
			if (other.duty != null)
				return false;
		} else if (!duty.equals(other.duty))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s GeneralStaff: Duty: %10s",super.toString(), duty);
	}
	
	public int compareTo(Employee e) {
		return super.compareTo(e);
	}
	
}
