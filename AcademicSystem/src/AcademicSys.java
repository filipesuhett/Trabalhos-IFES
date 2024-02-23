import java.util.ArrayList;
import java.util.List;

public class AcademicSys implements Comparable<Classroom>, java.lang.Comparable<Classroom> {
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Classroom> classrooms;

    public AcademicSys() {
        this.setTeachers(new ArrayList<>());
        this.setStudents(new ArrayList<>());
        this.setClassrooms(new ArrayList<>());
    }

    public void newTeacher(Teacher t) {
        this.teachers.add(t);
    }

    public void newStudents(Student s) {
        this.students.add(s);
    }

    public void newClassroom(Classroom c) {
        this.classrooms.add(c);
    }

    public Teacher findTeacher(String cpf) {
        List<Teacher> teachers = this.getTeachers();
        for (Teacher teacher : teachers) {
            if (teacher.getCpf().equals(cpf)) {
                return teacher;
            }
        }
        return null;
    }

    public Student findStudent(String mat) {
        List<Student> students = this.getStudents();
        for (Student student : students) {
            if (student.getMat().equals(mat)) {
                return student;
            }
        }
        return null;
    }

    public Classroom findClassroom(String name) {
        List<Classroom> classrooms = this.getClassrooms();
        for (Classroom classroom : classrooms) {
            if (classroom.getName().equals(name)) {
                return classroom;
            }
        }
        return null;
    }

    // Method to list all teachers in the system.
    public void listTeacher(){
        List<Teacher> teachers = this.getTeachers(); // Get the array of teachers.
        System.out.println("Professores Cadastrados: ");
        for (int i = 0; i < teachers.size(); i++){ // Loop through the teachers.
            System.out.print("* "); // Print an asterisk.
            System.out.println(teachers.get(i)); // Print the details of the teacher.
        }
    }

    // Method to list all students in the system.
    public void listStudent(){
        List<Student> students = this.getStudents();
        System.out.println("Alunos Cadastrados: ");
        for (int i = 0; i < students.size(); i++){ // Loop through the students.
            System.out.print("* "); // Print an asterisk.
            System.out.println(students.get(i)); // Print the details of the student.
        }
    }

    // Method to list all classrooms in the system.
    public void listClassroom(){
        List<Classroom> classrooms = this.getClassrooms();
        ord(classrooms);
        System.out.println("Turmas Cadastradas: ");
        for (int i = 0; i < classrooms.size(); i++){ // Loop through the classrooms.
            System.out.println("------------------------------------------------------------");
            classrooms.get(i).median();// Print the details of the classroom.
        }
    }

    public int compareTo(Classroom first ,Classroom other) {
        int yearComparison = Integer.compare(first.getYear(), other.getYear());
        if (yearComparison != 0) {
            return yearComparison;
        }
    
        int semesterComparison = Integer.compare(first.getPeriod(), other.getPeriod());
        if (semesterComparison != 0) {
            return semesterComparison;
        }
    
        int nameComparison = first.getName().compareTo(other.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }

        if (first.getTeacher().cpf.equals(other.getTeacher().cpf)) {
            return 0;
        }

        return 1;
    }
    
    public void ord(List<Classroom> list) {
        mergeSort(list);
    }

    public void mergeSort(List<Classroom> list) {
        if (list.size() <= 1) {
            return;
        }

        int mid = list.size() / 2;
        List<Classroom> left = new ArrayList<>(list.subList(0, mid));
        List<Classroom> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    public void merge(List<Classroom> list, List<Classroom> left, List<Classroom> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (compareTo(right.get(j), left.get(i)) == 1) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }

    public int lengthStudents(){
        return this.getStudents().size();
    }

    public int lengthTeachers(){
        return this.getTeachers().size();
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public int compareTo(Classroom o) {
        return 0;
    }
}