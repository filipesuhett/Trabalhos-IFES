import java.io.BufferedWriter;
import java.io.IOException;

public class Teacher extends People implements Save{
    private double wage;

    public Teacher(String name, String cpf, double wage) {
        super(name, cpf); // Chama o construtor da superclasse (People) com o nome e CPF fornecidos.
        this.setWage(wage);
    }

    public String toString() {
        return this.getName() + " (CPF: " + this.getCpf() + ")";
    }

    public void saveArc(BufferedWriter buff) {
        try {
            buff.write(this.name + ";" + this.cpf + ";" + this.wage + "\n");
        } catch (IOException e) {
            System.out.println("Error while saving teacher.");
        }
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }
}
