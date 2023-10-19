public class Teacher extends People {
    // Private member variable to store the wage of the teacher.
    private double wage;

    // Constructor to initialize the Teacher object with provided name, CPF, and wage.
    public Teacher(String name, String cpf, double wage) {
        // Call the constructor of the superclass (People) with provided name and CPF.
        super(name, cpf);

        // Set the wage for this teacher.
        this.setWage(wage);
    }

    // Getter and setter method for the wage of the teacher.
    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }
}
