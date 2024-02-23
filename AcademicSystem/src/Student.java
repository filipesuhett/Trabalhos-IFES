import java.io.BufferedWriter;
import java.io.IOException;

public class Student extends People implements Save, java.lang.Comparable<Student> {
    private String mat;

    public Student(String name, String cpf, String mat) {
        super(name, cpf); // Chama o construtor da superclasse (People) com o nome e CPF fornecidos.
        this.setMat(mat);
    }

    public String toString() {
        return this.getName() + " (Matrícula: " + this.getMat() + ")";
    }

    public void saveArc(BufferedWriter buff) {
        try {
            buff.write(this.name + ";" + this.cpf + ";" + this.mat + "\n");
        } catch (IOException e) {
            System.out.println("Error while saving student.");
        }
    }
    
    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public int compareTo(Student o) {
        return 0;
    }
}
