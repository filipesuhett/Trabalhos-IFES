public class Student extends People {
    // Private member variable to store the matriculation number.
    private String mat;

    // Constructor to initialize the Student object with provided name, CPF, and matriculation number.
    public Student(String name, String cpf, String mat) {
        // Call the constructor of the superclass (People) with provided name and CPF.
        super(name, cpf);

        // Set the matriculation number for this student.
        this.setMat(mat);
    }

    // Method to return a string representation of the student's name and matriculation number.
    public String toString() {
        return this.getName() + " (Matrícula: " + this.getMat() + ")";
    }

    // Getter and setter method for matriculation number.
    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }
}
