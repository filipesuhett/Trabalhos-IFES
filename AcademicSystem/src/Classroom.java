import java.util.ArrayList;
import java.util.List;

public class Classroom implements Comparable<Student> ,java.lang.Comparable<Classroom> {
    private String name;
    private int year;
    private int period;
    private Teacher teacher;
    private Student[] students;
    private Exam[] exams;

    public Classroom(String name, int year, int period, Teacher teacher, Student[] students, Exam[] exams) {
        this.setName(name);
        this.setYear(year);
        this.setPeriod(period);
        this.setTeacher(teacher);
        this.setStudents(students);
        this.setExams(exams);
    }

    public void median() {
        System.out.println("Médias da Turma " + this.getName() + " (" + this.getYear() + "/" + this.getPeriod() + "):");
        Exam[] exams = this.getExams();
        double classroomMedian = 0;

        List<Student> list = new ArrayList<Student>();

        for (int z = 0; z < this.getStudents().length; z++) {
            list.add(this.getStudents()[z]);
        }

        this.ord(list);

        for (int i = 0; i < this.getStudents().length; i++) {
            double studentTotal = 0;
            int ind = findStudent(list.get(i).getMat());
            System.out.print(this.getStudents()[ind]);
            double totalGrade = 0;

            for(int j = 0; j < this.getExams().length; j++){
                Exam exam = exams[j];
                double grade = exam.grade(ind);
                studentTotal += grade;
                System.out.print(" " + grade);
                totalGrade += exam.getGrade();
            }

            if (studentTotal <= totalGrade) {
                System.out.println(" = " + studentTotal);
                classroomMedian += studentTotal;
            } else {
                System.out.println(" = " + totalGrade);
                classroomMedian += totalGrade;
            }
        }
        System.out.println("Média da turma: " + (classroomMedian / this.getStudents().length));
    }

    public int compareTo(Student first ,Student other) {
        Exam[] exams = this.getExams();

        double studentTotal1 = 0;
        double studentTotal2 = 0;

        int ind1 = findStudent(first.getMat());
        int ind2 = findStudent(other.getMat());

        for(int j = 0; j < this.getExams().length; j++){
            Exam exam = exams[j];
            double grade = exam.grade(ind1);
            studentTotal1 += grade;
        }
        
        for(int j = 0; j < this.getExams().length; j++){
            Exam exam = exams[j];
            double grade = exam.grade(ind2);
            studentTotal2 += grade;
        }


        int gradeComparison = Double.compare(studentTotal2, studentTotal1);
        if (gradeComparison != 0) {
            return gradeComparison;
        }

        // If total grades are equal, compare by registration in ascending order
        return first.getMat().compareTo(other.getMat());
    }
    public void ord(List<Student> list) {
        this.mergeSort(list);
    }

    public void mergeSort(List<Student> list) {
        if (list.size() <= 1) {
            return;
        }

        int mid = list.size() / 2;
        List<Student> left = new ArrayList<>(list.subList(0, mid));
        List<Student> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    public void merge(List<Student> list, List<Student> left, List<Student> right) {
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

    public int findStudent(String mat) {
        for (int i = 0; i < this.getStudents().length; i++) {
            if (this.getStudents()[i].getMat().equals(mat)) {
                return i;
            }
        }
        return -1;
    }
    // Getter and setter methods for the private member variables.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public Exam[] getExams() {
        return exams;
    }

    public void setExams(Exam[] exams) {
        this.exams = exams;
    }

    public int compareTo(Classroom o) {
        return 0;
    }
}
