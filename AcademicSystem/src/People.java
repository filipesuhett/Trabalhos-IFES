public class People {
    // Protected member variables to store the name and CPF.
    protected String name;
    protected String cpf;

    // Constructor to initialize the People object with provided name and CPF.
    public People(String name, String cpf) {
        this.setName(name);
        this.setCpf(cpf);
    }

    // Method to return a string representation of the person's name and CPF.
    public String toString() {
        return this.getName() + " (CPF: " + this.getCpf() + ")";
    }

    // Getter and setter methods for name and CPF.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
