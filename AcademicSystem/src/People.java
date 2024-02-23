public abstract class People {
    protected String name;
    protected String cpf;

    public People(String name, String cpf) {
        this.setName(name);
        this.setCpf(cpf);
    }

    public abstract String toString();

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
