// Youtube video: https://youtu.be/S1V5xqh5F_k
// FIXME BE SURE TO UNCOMMENT THE CODE FOR MY MAIN MENU

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver_SchoolDB {

	public static void main(String[] args) throws IOException {
		File inFS;
		String fileContents;
		Scanner input = new Scanner(System.in);
		int index;
		Scanner readFile;
		Course[] courses = new Course[100];
		int numCourses = 0;
		GeneralStaff[] gs = new GeneralStaff[100];
		int numStaff = 0;
		Faculty[] f = new Faculty[100];
		int numFaculty = 0;
		Student[] s = new Student[100];
		int numStudents = 0;

		String dbHeader = "\n**************************************************************\nSCHOOL DATABASE INFO:\n\n************************************************\nCOURSES:";
		String dbHeader2 = "************************************************\n************************************************\nPERSONS:\n************************************************\n************************************************\nEMPLOYEES:\n************************************************\n************************************************\nGENERAL STAFF:";
		String dbHeader3 = "************************************************\n************************************************\nFACULTY:";
		String dbHeader4 = "************************************************\n************************************************\nSTUDENTS:";
		String dbHeader5 = "************************************************\n**************************************************************\n";
		String menuHeader = "**************************************************************\n**************************************************************\n";

		try {
			inFS = new File("SchoolDB_Initial.txt");
			readFile = new Scanner(inFS);

			while (readFile.hasNextLine()) {
				fileContents = readFile.nextLine();
				System.out.println(fileContents);

				if (fileContents.contains("Course:")) {
					fileContents = fileContents.substring(8);

					boolean isGradCourse;
					int courseNum;
					String subject;
					int credits;

					String[] arr = (fileContents.contains(", ")) ? fileContents.split(", ") : fileContents.split(",");
					isGradCourse = Boolean.parseBoolean(arr[0]);
					courseNum = Integer.parseInt(arr[1]);
					subject = arr[2];
					credits = Integer.parseInt(arr[3]);
					courses[numCourses] = new Course(isGradCourse, courseNum, subject, credits);
					numCourses++;
				}
				if (fileContents.contains("GeneralStaff:")) {
					fileContents = fileContents.substring(13);

					String duty;
					String dept;
					int birthYear;
					String name;

					String[] arr = fileContents.split(",");
					if (arr == null) {
						gs[numStaff] = new GeneralStaff();
						numStaff++;
					}
					if (arr.length == 1) {
						duty = arr[0].trim();
						gs[numStaff] = new GeneralStaff(duty);
						numStaff++;
					}
					if (arr.length == 2) {
						dept = arr[0].trim();
						duty = arr[1].trim();
						gs[numStaff] = new GeneralStaff(dept, duty);
						numStaff++;
					}
					if (arr.length == 4) {
						name = arr[0].trim();
						birthYear = Integer.parseInt(arr[1]);
						dept = arr[2].trim();
						duty = arr[3].trim();
						gs[numStaff] = new GeneralStaff(name, birthYear, dept, duty);
						numStaff++;
					}

				}
				if (fileContents.contains("Faculty:")) {
					fileContents = fileContents.substring(8);

					boolean tenured;
					String dept;
					int birthYear;
					String name;

					String[] arr = (fileContents.contains(",")) ? fileContents.split(",") : fileContents.split(" ");
					if (arr == null) {
						f[numFaculty] = new Faculty();
						numFaculty++;
					}
					if (arr.length == 1) {
						String t = arr[0];
						if (t.contains("true"))
							tenured = true;
						else
							tenured = false;

						f[numFaculty] = new Faculty(tenured);
						numFaculty++;
					}
					if (arr.length == 2) {
						dept = arr[0].trim();
						tenured = Boolean.parseBoolean(arr[1]);
						f[numFaculty] = new Faculty(dept, tenured);
						numFaculty++;
					}
					if (arr.length == 4) {
						name = arr[0].trim();
						birthYear = Integer.parseInt(arr[1]);
						dept = arr[2].trim();
						tenured = Boolean.parseBoolean(arr[3]);
						f[numFaculty] = new Faculty(name, birthYear, dept, tenured);
						numFaculty++;
					}
				}
				if (fileContents.contains("Student:")) {
					fileContents = fileContents.substring(8);

					boolean grad;
					String major;
					int birthYear;
					String name;
					String[] arr = fileContents.split(",");

					if (arr == null) {
						s[numStudents] = new Student();
						numStudents++;
					}
					if (arr.length == 1) {
						String t = arr[0];
						if (t.contains("true"))
							grad = true;
						else
							grad = false;

						s[numStudents] = new Student(grad);
						numStudents++;
					}
					if (arr.length == 2) {
						major = arr[0].trim();
						grad = Boolean.parseBoolean(arr[1]);
						s[numStudents] = new Student(major, grad);
						numStudents++;
					}
					if (arr.length == 4) {
						name = arr[0].trim();
						birthYear = Integer.parseInt(arr[1]);
						major = arr[2].trim();
						grad = Boolean.parseBoolean(arr[3]);
						s[numStudents] = new Student(name, birthYear, major, grad);
						numStudents++;
					}

				}

			}

			System.out.println(dbHeader);
			for (int i = 0; i < numCourses; i++) {
				System.out.println(courses[i].toString());
			}

			System.out.println(dbHeader2);
			for (int i = 0; i < numStaff; i++) {
				System.out.println(gs[i].toString());
			}
			System.out.println(dbHeader3);
			for (int i = 0; i < numFaculty; i++) {
				System.out.println(f[i].toString());
			}
			System.out.println(dbHeader4);
			for (int i = 0; i < numStudents; i++) {
				System.out.println(s[i].toString());
			}
			System.out.println(dbHeader5);
			readFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}

		// MENU STUFF
		boolean notFinished = true;
		while (notFinished) {
			try {

				System.out.println("\n" + menuHeader);
				System.out.println("School Database - Main Menu");
				System.out.println("\nPlease select from the following:\n");
				System.out.println(
						"[1 - Create 3 new Courses/Faculty/General Staff/Students]  [2 - Add 2 Courses to a Faculty/Student]  [3 - Get Course from a Faculty/Student or check if Faculty teaches specific course]");
				System.out.println(
						"[4 - Check which Faculty/Student has the most and least Courses/Credits or check for min/max Course Num]  [0 - EXIT]\n");
				System.out.print(menuHeader);
				System.out.print("Enter the number of your selection: ");
				int selection = input.nextInt();
				System.out.println();
				int choice, choice2;
				switch (selection) {
				case 1:
					boolean cont = true;
					while (cont) {
						System.out.println("Please select which you would like to create: ");
						System.out.println(
								"[1 - Create 3 new Courses]  [2 - Create 3 new Faculty member]  [3 - Create 3 new General Staff members]  [4 - Create 3 new Students]  [0 - EXIT]\n");
						System.out.print("Enter the number of your selection: ");
						selection = input.nextInt();
						System.out.println();
						switch (selection) {
						case 1:
							System.out.println("\nPlease create 3 new courses:");
							for (int i = 0; i < 3; i++) {
								System.out.println("Is this a graduate course? (y/n)");
								String answer = input.next();
								boolean isGradCourse;
								if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
									isGradCourse = true;
								} else {
									isGradCourse = false;
								}
								System.out.println("What subject is this class?");
								String subject = input.next();
								System.out.println("What is the course number for this class?");
								int courseNum = input.nextInt();
								System.out.println("How many credits is this class?");
								int credits = input.nextInt();
								System.out.println("");

								courses[numCourses] = new Course(isGradCourse, courseNum, subject, credits);
								numCourses++;
								System.out.println("Course created.");
							}
							break;
						case 2:
							System.out.println("\nPlease create 3 new Faculty members:");
							for (int i = 0; i < 3; i++) {
								System.out.println("What is your name?");
								String name = input.next();
								name += input.nextLine();
								System.out.println("What is your year of birth?");
								int birthYear = input.nextInt();
								System.out.println("What department?");
								String deptName = input.next();
								deptName += input.nextLine();
								System.out.println("Are you tenured? (y/n)");
								boolean isTenured;
								String answer = input.next();
								if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
									isTenured = true;
								} else {
									isTenured = false;
								}
								System.out.println("");

								f[numFaculty] = new Faculty(name, birthYear, deptName, isTenured);
								numFaculty++;
								System.out.println("Faculty created.");
							}
							break;

						case 3:
							System.out.println("\nPlease create 3 new General Staff:");
							for (int i = 0; i < 3; i++) {
								System.out.println("What is your name?");
								String name = input.next();
								name += input.nextLine();
								System.out.println("What is your year of birth?");
								int birthYear = input.nextInt();
								System.out.println("What department?");
								String deptName = input.next();
								deptName += input.nextLine();
								System.out.println("What is your duty?");
								String duty = input.next();
								duty += input.nextLine();
								System.out.println("");

								gs[numStaff] = new GeneralStaff(name, birthYear, deptName, duty);
								numStaff++;
								System.out.println("Staff created.");
							}
							break;

						case 4:
							System.out.println("\nPlease create 3 new Students:");
							for (int i = 0; i < 3; i++) {
								System.out.println("What is your name?");
								String name = input.next();
								name += input.nextLine();
								System.out.println("What is your year of birth?");
								int birthYear = input.nextInt();
								System.out.println("What is your major?");
								String major = input.next();
								major += input.nextLine();
								System.out.println("Are you a graduate? (y/n)");
								boolean isGrad;
								String answer = input.next();
								if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
									isGrad = true;
								} else {
									isGrad = false;
								}
								System.out.println("");

								s[numStudents] = new Student(name, birthYear, major, isGrad);
								numStudents++;
								System.out.println("Student created.");
							}
							break;

						case 0:
							cont = false;
							break;
						}
					}
					break;

				case 2:

					// Add 2 new Courses to a Faculty object
					cont = true;
					while (cont) {
						System.out.println("Please select which you would like to do: ");
						System.out.println(
								"[1 - Add 2 Courses to a Faculty member]  [2 - Add 2 Courses to a Student]  [3 - Add an array of 2 Courses to a Faculty member]  [4 - Add an array of 2 Courses to a Student]  [0 - EXIT]\n");
						System.out.print("Enter the number of your selection: ");
						selection = input.nextInt();
						System.out.println();
						switch (selection) {
						case 1:
							addCourseToFaculty(f, numFaculty, courses, numCourses, input);
							break;
						case 2:
							addCourseToStudent(s, numStudents, courses, numCourses, input);
							break;
						case 3:
							addCourseArrayToFaculty(f, numFaculty, courses, numCourses, input);
							break;
						case 4:
							addCourseArrayToStudent(s, numStudents, courses, numCourses, input);
							break;
						case 0:
							cont = false;
							break;
						}
					}
					break;
				case 3:
					cont = true;
					while (cont) {
						System.out.println("Please select which you would like do: ");
						System.out.println(
								"[1 - Get the Course at index from a Faculty]  [2 - Get the Course at index from a Student]  [3 - Check if Faculty teaches specified Course]  [0 - EXIT]\n");
						System.out.print("Enter the number of your selection: ");
						selection = input.nextInt();
						System.out.println();
						switch (selection) {
						case 1:
							getFacultyCourse(f, numFaculty, input);
							break;
						case 2:
							getStudentCourse(s, numStudents, input);
							break;
						case 3:
							doesFacultyTeachCourse(f, numFaculty, courses, numCourses, input);
							break;
						case 0:
							cont = false;
							break;
						}
					}
					break;

				case 4:
					cont = true;
					while (cont) {
						System.out.println("Please select which you would like do: ");
						System.out.println(
								"[1 - Check which Faculty teaches the most and least Courses]  [2 - Check which Student has the most and least credits]  [3 - Check which Course is the min of all Courses]  [4 - Check which Course is the max of all Courses]  [0 - EXIT]\n");
						System.out.print("Enter the number of your selection: ");
						selection = input.nextInt();
						System.out.println();
						switch (selection) {
						case 1:
							mostLeastCourseTaught(f, numFaculty);
							break;
						case 2:
							mostLeastCredits(s, numStudents);
							break;
						case 3:
							minCourse(courses, numCourses);
							break;
						case 4:
							maxCourse(courses, numCourses);
							break;
						case 0:
							cont = false;
							break;
						}
					}
					break;

				case 0:
					notFinished = false;
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Back to main menu.");
				input.next();
			}
		}

		// Display all the Objects using toString on the console (this includes existing
		// plus recently added)

		FileOutputStream outFS = new FileOutputStream("SchoolDB_Updated.txt", false);
		PrintWriter pw = new PrintWriter(outFS);

		System.out.println(dbHeader);
		for (int i = 0; i < numCourses; i++) {
			System.out.println(courses[i].toString());
			pw.printf("Course: %b,%d,%s,%d%n", courses[i].isGraduateCourse(), courses[i].getCourseNum(),
					courses[i].getCourseDept(), courses[i].getNumCredits());

		}
		pw.println();
		System.out.println(dbHeader2);

		for (int i = 0; i < numStaff; i++) {
			System.out.println(gs[i].toString());
			pw.printf("GeneralStaff: %s,%d,%s,%s%n", gs[i].getName(), gs[i].getBirthYear(), gs[i].getDeptName(),
					gs[i].getDuty());
		}
		pw.println();
		System.out.println(dbHeader3);

		for (int i = 0; i < numFaculty; i++) {
			System.out.println(f[i].toString());
			pw.printf("Faculty: %s,%d,%s,%b%n", f[i].getName(), f[i].getBirthYear(), f[i].getDeptName(),
					f[i].isTenured());
		}
		pw.println();
		System.out.println(dbHeader4);

		for (int i = 0; i < numStudents; i++) {
			if (s[i].getMajor().isEmpty()) {
				s[i].setMajor("undeclared");
			}
			System.out.println(s[i].toString());
			pw.printf("Student: %s,%d,%s,%b%n", s[i].getName(), s[i].getBirthYear(), s[i].getMajor(),
					s[i].isGraduate());
		}
		System.out.println(dbHeader5);
		System.out.println("\nSchoolDB_Updated.txt overwritten.");

		input.close();
		pw.flush();
		outFS.close();

	}

	public static void addCourseToFaculty(Faculty[] f, int numFaculty, Course[] courses, int numCourses,
			Scanner input) {
		// Add 2 Courses to a Faculty object
		for (int i = 0; i < numFaculty; i++) {
			System.out.println(i + " : " + f[i].toString());
		}
		System.out.print("\nSelect a faculty member: ");
		int choice = input.nextInt();

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < numCourses; j++) {
				System.out.println(j + " : " + courses[j].toString());
			}
			System.out.print("\nPlease add a course taught: ");
			int choice2 = input.nextInt();
			f[choice].addCourseTaught(courses[choice2]);
			System.out.println("Course added.");
		}
	}

	public static void addCourseToStudent(Student[] s, int numStudents, Course[] courses, int numCourses,
			Scanner input) {
		// Add 2 Courses to a Student object

		for (int i = 0; i < numStudents; i++) {
			System.out.println(i + " : " + s[i].toString());
		}
		System.out.print("\nSelect a student: ");
		int choice = input.nextInt();

		System.out.println("\nPlease add 2 new courses taken:");
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < numCourses; j++) {
				System.out.println(j + " : " + courses[j].toString());
			}
			System.out.print("\nSelect a course: ");
			int choice2 = input.nextInt();
			s[choice].addCourseTaken(courses[choice2]);
			System.out.println("Course added.");
		}
	}

	public static void addCourseArrayToFaculty(Faculty[] f, int numFaculty, Course[] courses, int numCourses,
			Scanner input) {
		// Add 2 courses in array to a Faculty object

		for (int i = 0; i < numFaculty; i++) {
			System.out.println(i + " : " + f[i].toString());
		}
		System.out.print("\nSelect a faculty member: ");
		int choice = input.nextInt();
		Course[] coursesTaughtTemp = new Course[2];
		System.out.println("\nPlease add 2 courses taught:");
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < numCourses; j++) {
				System.out.println(j + " : " + courses[j].toString());
			}
			System.out.print("\nSelect a Course: ");
			int choice2 = input.nextInt();
			coursesTaughtTemp[i] = courses[choice2];
		}
		f[choice].addCoursesTaught(coursesTaughtTemp);
		System.out.println("Courses added.");
	}

	public static void addCourseArrayToStudent(Student[] s, int numStudents, Course[] courses, int numCourses,
			Scanner input) {
		// Add 2 courses in array to a Student object
		for (int i = 0; i < numStudents; i++) {
			System.out.println(i + " : " + s[i].toString());
		}
		System.out.print("\nSelect a student: ");
		int choice = input.nextInt();
		Course[] coursesTakenTemp = new Course[2];
		System.out.println("\nPlease add 2 courses taken:");
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < numCourses; j++) {
				System.out.println(j + " : " + courses[j].toString());
			}
			System.out.print("\nSelect a course: ");
			int choice2 = input.nextInt();
			coursesTakenTemp[i] = courses[choice2];
		}
		s[choice].addCoursesTaken(coursesTakenTemp);
		System.out.println("Courses added.");
	}

	public static void getFacultyCourse(Faculty[] f, int numFaculty, Scanner input) {
		// Get the Course at index (valid and invalid indexes) from a Faculty object
		for (int i = 0; i < numFaculty; i++) {
			System.out.println(i + " : " + f[i].toString());
		}
		System.out.print("\nSelect a faculty member: ");
		int choice = input.nextInt();
		System.out.print("Please enter an index: ");
		int index = input.nextInt();
		System.out.println(f[choice].getCourseTaught(index));
		System.out.println();
	}

	public static void getStudentCourse(Student[] s, int numStudents, Scanner input) {
		// Get the Course at index (valid and invalid indexes) from a Student object
		for (int i = 0; i < numStudents; i++) {
			System.out.println(i + " : " + s[i].toString());
		}
		System.out.print("\nSelect a Student member: ");
		int choice = input.nextInt();
		System.out.print("Please enter an index: ");
		int index = input.nextInt();
		System.out.println(s[choice].getCourseTaken(index));
		System.out.println();
	}

	public static void doesFacultyTeachCourse(Faculty[] f, int numFaculty, Course[] courses, int numCourses,
			Scanner input) {
		// Allow the user to select a Faculty object and a Course object from
		// menus and query the Faculty object for the Course to determine whether the
		// Faculty object teaches it or not.

		for (int i = 0; i < numFaculty; i++) {
			System.out.println(i + " : " + f[i].toString());
		}
		System.out.print("\nSelect a faculty member: ");
		int choice = input.nextInt();

		for (int j = 0; j < numCourses; j++) {
			System.out.println(j + " : " + courses[j].toString());
		}
		System.out.print("\nPlease select a course: ");
		int choice2 = input.nextInt();

		for (int i = 0; i < numCourses; i++) {
			if (f[choice].getNumCoursesTaught() > 0) {
				if (f[choice].getCourseTaught(i) == courses[choice2]) {
					System.out.println(f[choice].getName() + " teaches " + courses[choice2] + ".");
					break;
				} else {
					System.out.println(f[choice].getName() + " does not teach " + courses[choice2] + ".");
					break;
				}
			}
		}
		if (f[choice].getNumCoursesTaught() == 0) {
			System.out.println(f[choice].getName() + " does not teach any course!");
		}
	}

	public static void mostLeastCourseTaught(Faculty[] f, int numFaculty) {
		// Determine which Faculty object teaches the most and the least courses.
		Arrays.sort(f, 0, numFaculty);
		if (f[0].getNumCoursesTaught() != f[numFaculty - 1].getNumCoursesTaught()) {
			System.out.println(f[0].getName() + " teaches the least amount of courses: " + f[0].getNumCoursesTaught());
			System.out.println(f[numFaculty - 1].getName() + " teaches the most courses: "
					+ f[numFaculty - 1].getNumCoursesTaught());
		} else {
			System.out.println("All Faculty teach the same amount of courses.");
		}
	}

	public static void mostLeastCredits(Student[] s, int numStudents) {
		// Determine which Student has the most and least credits.
		Arrays.sort(s, 0, numStudents);
		if (s[0].getTotalCredits() != s[numStudents - 1].getTotalCredits()) {
			System.out.println(s[0].getName() + " has the least amount of credits: " + s[0].getTotalCredits());
			System.out.println(s[numStudents - 1].getName() + " has the most amount of credits: "
					+ s[numStudents - 1].getTotalCredits());
		} else {
			System.out.println("All Students have the same amount of credits.");
		}
	}

	public static void minCourse(Course[] courses, int numCourses) {
		// Determine which Course is the minimum of all Course objects in the catalog.
		Arrays.sort(courses, 0, numCourses);
		System.out.println("Min of all Course objects: " + courses[0].toString());
	}

	public static void maxCourse(Course[] courses, int numCourses) {
		// Determine which Course is the minimum of all Course objects in the catalog.
		Arrays.sort(courses, 0, numCourses);
		System.out.println("Max of all Course objects: " + courses[numCourses - 1].toString());
	}

}
