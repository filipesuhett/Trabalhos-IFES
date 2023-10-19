public class AcademicSys{

    // Private member variables to store the number of teachers, number of students,
    // an array of teachers, and an array of students.
    private int numTeachers;
    private int numStudents;
    private Teacher[] teachers;
    private Student[] students;

    // Constructor initializes the member variables and creates arrays with fixed sizes.
    public AcademicSys() {
        this.numTeachers = 0; // Initialize the number of teachers to 0.
        this.numTeachers = 0; // This looks like a typo, it should probably be numStudents.
        this.teachers = new Teacher[100]; // Create an array to hold up to 100 teachers.
        this.students = new Student[1000]; // Create an array to hold up to 1000 students.
    }

    // Method to add a new teacher to the system.
    public void newTeacher(Teacher t){
        this.getTeachers()[this.getNumTeachers()] = t; // Add the teacher to the array.
        this.setNumTeachers(this.getNumTeachers()+1); // Increment the number of teachers.
    }

    // Method to add a new student to the system.
    public void newStudents(Student s){
        this.getStudents()[this.getNumStudents()] = s; // Add the student to the array.
        this.setNumStudents(this.getNumStudents()+1); // Increment the number of students.
    }

    // Method to find a teacher by their CPF (presumably an identification number).
    public Teacher findTeacher(String cpf){
        Teacher[] teachers = this.getTeachers(); // Get the array of teachers.
        for (int i = 0; i < this.getNumTeachers(); i++){ // Loop through the teachers.
            String TeacherCPF = teachers[i].getCpf(); // Get the CPF of the current teacher.
            if (TeacherCPF.equals(cpf)){ // If it matches the input CPF...
                return teachers[i]; // Return this teacher.
            }
        }
        return null; // If no match is found, return null.
    }

    // Method to find a student by their matriculation number.
    public Student findStudent(String mat){
        Student[] students = this.getStudents(); // Get the array of students.
        for (int i = 0; i < this.getNumStudents(); i++){ // Loop through the students.
            String StdMat = students[i].getMat(); // Get the matriculation number of the current student.
            if (StdMat.equals(mat)){ // If it matches the input matriculation number...
                return students[i]; // Return this student.
            }
        }
        return null; // If no match is found, return null.
    }

    // Method to list all teachers in the system.
    public void listTeacher(){
        Teacher[] teachers = this.getTeachers(); // Get the array of teachers.
        System.out.println("Professores Cadastrados: ");
        for (int i = 0; i < this.getNumTeachers(); i++){ // Loop through the teachers.
            System.out.print("* "); // Print an asterisk.
            System.out.println(teachers[i]); // Print the details of the teacher.
        }
    }

    // Method to list all students in the system.
    public void listStudent(){
        Student[] students = this.getStudents(); // Get the array of students.
        System.out.println("Alunos Cadastrados: ");
        for (int i = 0; i < this.getNumStudents(); i++){ // Loop through the students.
            System.out.print("* "); // Print an asterisk.
            System.out.println(students[i]); // Print the details of the student.
        }
    }

    // Getters and setters for various member variables.

    public int getNumTeachers() {
        return numTeachers;
    }

    public void setNumTeachers(int numTeachers) {
        this.numTeachers = numTeachers;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }

    public void setTeachers(Teacher[] teachers) {
        this.teachers = teachers;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
