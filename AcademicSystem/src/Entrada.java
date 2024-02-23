import java.io.*;
import java.sql.SQLOutput;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Classe com as rotinas de entrada e saída do projeto
 * @authors Filipe Suhett, Giovanna Scalfoni e Hilario Seibel Junior
 */
public class Entrada {
    public Scanner input;
    /**
     * Construtor da classe InputOutput
     * Se houver um arquivo input.txt, define que o Scanner vai ler deste arquivo.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     */
    public Entrada() {
        try {
            // Se houver um arquivo input.txt, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input.txt"));
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in);
        }
    }

    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    private String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        String linha = "";
        linha = this.input.nextLine();

        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#')
            linha = this.input.nextLine();

        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    private int lerInteiro(String msg) throws NumberFormatException, StringIndexOutOfBoundsException {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = this.lerLinha(msg);
        int numero = Integer.parseInt(linha);

        return numero;
    }

    /**
     * Faz a leitura de um double
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    private double lerDouble(String msg) throws NumberFormatException, StringIndexOutOfBoundsException {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um double e retorna este double
        String linha = this.lerLinha(msg);
        double numero = Double.parseDouble(linha);

        return numero;
    }

    /**
    * Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.
    * @return Inteiro contendo a opção escolhida pelo usuário
    */
    public int menu1() {
        // Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.

        int op = -1;

        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar professor:\n" +
                "2) Cadastrar aluno:\n" +
                "3) Cadastrar turma:\n" +
                "4) Visualizar notas de todas as turmas:\n" +
                "0) Sair\n";

        try {
            op = this.lerInteiro(msg);

            while (op < 0 || op > 4) {
                System.out.println("Opção inválida. Tente novamente: ");
                op = this.lerInteiro(msg);
            }
        }
        catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Tente novamente: ");
        }

        return op;
    }

    /***************************************************/

    /**
     * Lê os dados de um novo Teacher e cadastra-o no sistema.
     * @param s: Um objeto da classe AcademicSys
     */
    public void cadProf(AcademicSys s) throws IOException {
        s.listTeacher();

        String nome = "";
        String cpf = "";
        double salario = 0.0;

        try {
            nome = this.lerLinha("Digite o nome do professor: ");
            cpf = this.lerLinha("Digite o cpf do professor: ");
            salario = this.lerDouble("Digite o salário do professor: R$");

            FileWriter f = new FileWriter("teachers.txt", true);
            BufferedWriter buff = new BufferedWriter(f);

            if (s.findTeacher(cpf) == null) { // Garantindo que o não CPF esteja duplicado.
                Teacher p = new Teacher(nome, cpf, salario);
                p.saveArc(buff);
                buff.close();
                s.newTeacher(p);
            }
            else {
                System.out.println("Erro: CPF duplicado. Professor não adicionado.");
            }
        }
        catch (StringIndexOutOfBoundsException | NumberFormatException | NoSuchElementException e) {
            System.out.println("Erro: Entrada inválida. Professor não adicionado.");
        }
    }

    /**
     * Lê os dados de um novo Student e cadastra-o no sistema.
     * @param s: Um objeto da classe AcademicSys
     */
    public void cadAluno(AcademicSys s) throws IOException {
        s.listStudent();

        String nome = "";
        String cpf = "";
        String mat = "";

        try {
            nome = this.lerLinha("Digite o nome do aluno: ");
            cpf = this.lerLinha("Digite o cpf do aluno: ");
            mat = this.lerLinha("Digite a matrícula do aluno: ");

            FileWriter f = new FileWriter("students.txt", true);
            BufferedWriter buff = new BufferedWriter(f);

            if (s.findStudent(mat) == null) {
                Student a = new Student(nome, cpf, mat);
                a.saveArc(buff);
                buff.close();
                s.newStudents(a);
            }
            else {
                System.out.println("Erro: Matrícula duplicada. Aluno não adicionado.");
            }

        }
        catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Aluno não adicionado.");
        }
    }

    /***************************************************/

    /**
     * Lê o CPF de um Teacher e localiza-o no sistema.
     * @param s: Um objeto da classe AcademicSys
     * @return Um objeto da classe Teacher.
     */
    private Teacher lerProf(AcademicSys s) {
        s.listTeacher();

        String cpf = this.lerLinha("Digite o CPF do professor: ");
        Teacher p = s.findTeacher(cpf);

        while (p == null) {
            cpf = this.lerLinha("CPF inválido. Digite outro: ");
            p = s.findTeacher(cpf);
        }

        return p;
    }

    /**
     * Lê um número de Students, depois a matrícula de cada Student e localiza-os no sistema.
     * @param s: Um objeto da classe AcademicSys
     * @return Um array contendo todos os objetos da classe Student cujas matrículas foram digitadas.
     */
    private Student[] lerAluno(AcademicSys s) {
        int nStudents = this.lerInteiro("Digite a quantidade de alunos na disciplina: ");

        if (nStudents <= 0 || nStudents > s.lengthStudents()) {
            Student[] Students = new Student[0];
            System.out.println("Erro: Tamanho da Turma é superior ao numero de alunos cadastrados ou o Tamanho da Turma é 0.");
            return Students;
        }

        Student[] Students = new Student[nStudents];

        for(int i=0; i<nStudents; i++) {
            s.listStudent();

            String mat = this.lerLinha("Digite a matrícula do alunos: ");
            Student a = s.findStudent(mat);

            while (a == null) {
                mat = this.lerLinha("Matrícula inválida. Digite outra: ");
                a = s.findStudent(mat);
            }

            for(int j=0; j<i; j++) {
                if (Students[j].getMat().equals(mat)) {
                    Student[] Students1 = new Student[0];
                    System.out.println("Erro: Matrícula duplicada. Aluno não adicionado.");
                    return Students1;
                }
            }

            Students[i] = a;
        }

        return Students;
    }

    /**
     * Lê as notas de um Student em uma Test.
     * @param s: Um objeto da classe AcademicSys
     * @param a: Um objeto da classe Student
     * @param nQuestoes: Inteiro com o número de questões nesta Test
     * @return Um objeto da classe StudentTest, com as respectivas as notas do Student em cada questão da Test.
     */
    private StudentTest lerAlunoProva(AcademicSys s, Student a, int nQuestoes) {
        double[] notas = new double[nQuestoes];

        for (int i=0; i<nQuestoes; i++) {
            notas[i] = lerDouble("Nota de " + a.getName() + " na questão " + (i+1) + ": ");
        }

        return new StudentTest(a, notas);
    }

    /**
     * Lê os dados de uma Test.
     * @param s: Um objeto da classe AcademicSys
     * @param Students: Um array com todos os Students que fizeram esta Test.
     * @return Um novo objeto da classe Test com todos os dados que foram lidos.
     */
    private Test lerProva(AcademicSys s, Student[] Students) {
        String nome = this.lerLinha("Informe o nome desta Prova: ");

        int dia = this.lerInteiro("Digite o dia da prova: ");
        int mes = this.lerInteiro("Digite o mês da prova: ");
        int ano = this.lerInteiro("Digite o ano da prova: ");
        Date aplic = new Date(dia, mes, ano);

        double valor = this.lerDouble("Digite o valor máximo desta avaliação: ");

        int nQuestoes = this.lerInteiro("Digite o número de questões: ");

        StudentTest[] notas = new StudentTest[Students.length];
        for (int i = 0; i< Students.length; i++) {
            notas[i] = this.lerAlunoProva(s, Students[i], nQuestoes);
        }

        return new Test(nome, aplic, valor, nQuestoes, notas);
    }

    /**
     * Lê a avaliação que o Teacher fez de um Student em um trabalho.
     * @param s: Um objeto da classe AcademicSys
     * @param a: Um objeto da classe Student
     * @return Um objeto da classe StudentAssignment com as informações lidas sobre o Student neste trabalho.
     */
    private StudentAssignment lerAlunoTrab(AcademicSys s, Student a) {
        double nota = this.lerDouble("Nota do " + a.getName() + " no trabalho: ");

        int dia = this.lerInteiro("Digite o dia de entrega do trabalho: ");
        int mes = this.lerInteiro("Digite o mês de entrega do trabalho: ");
        int ano = this.lerInteiro("Digite o ano de entrega do trabalho: ");
        Date entrega = new Date(dia, mes, ano);

        int tempoExec = this.lerInteiro("Informe o tempo de processamento: ");

        return new StudentAssignment(a, nota, entrega, tempoExec);
    }

    /**
     * Lê os dados de um Assignment.
     * @param s: Um objeto da classe AcademicSys
     * @param Students: Um array com todos os Students que fizeram esta Test.
     * @return Um novo objeto da classe Assignment com todos os dados que foram lidos.
     */
    private Assignment lerTrabalho(AcademicSys s, Student[] Students) {
        String nome = this.lerLinha("Informe o nome desta avaliação: ");

        int dia = this.lerInteiro("Digite o dia do trabalho: ");
        int mes = this.lerInteiro("Digite o mês do trabalho: ");
        int ano = this.lerInteiro("Digite o ano do trabalho: ");
        Date aplic = new Date(dia, mes, ano);

        double valor = this.lerDouble("Digite o valor máximo desta avaliação: ");
        int tempoEsp = this.lerInteiro("Digite o tempo esperado pelo professor: ");

        StudentAssignment[] notas = new StudentAssignment[Students.length];
        for (int i=0; i<Students.length; i++) {
            notas[i] = this.lerAlunoTrab(s, Students[i]);
        }

        return new Assignment(nome, aplic, valor, tempoEsp, notas);
    }

    /**
     * Lê as avaliações de uma certa disciplina.
     * @param s: Um objeto da classe AcademicSys
     * @param Students: Um array com todos os Students que fizeram esta Test.
     * @return Um array com todas as avaliações da disciplina.
     */
    private Exam[] lerAvaliacoes(AcademicSys s, Student[] Students) {
        int nAvaliacoes = this.lerInteiro("Digite a quantidade de avaliações na disciplina: ");

        if (nAvaliacoes <= 0) {
            Exam[] avs = new Exam[0];
            System.out.println("Erro: Número de avaliações inválido. Tente novamente: ");
            return avs;
        }

        Exam[] avs = new Exam[nAvaliacoes];

        for (int i=0; i<nAvaliacoes; i++) {
            int op = this.lerInteiro("Escolha um tipo de avaliação:\n1) Prova\n2) Trabalho");

            while (op != 1 && op != 2) {
                op = this.lerInteiro("Tipo de avaliação inválida. Tente novamente: ");
            }

            if (op == 1) avs[i] = this.lerProva(s, Students);
            else avs[i] = this.lerTrabalho(s, Students);
        }

        return avs;
    }

    /**
     * Lê os dados de uma Classroom.
     * @param s: Um objeto da classe AcademicSys
     * @return Um novo objeto da classe Classroom com todos os dados desta Classroom.
     */
    public void cadTurma(AcademicSys s) {
        if (s.lengthStudents() == 0) {
            System.out.println("Erro: Não há alunos cadastrados. Turma não adicionada.");
            return;
        }

        if (s.lengthTeachers() == 0) {
            System.out.println("Erro: Não há professores cadastrados. Turma não adicionada.");
            return;
        }

        try {
            String disciplina = this.lerLinha("Digite o nome da disciplina: ");

            int ano = this.lerInteiro("Digite o ano da disciplina: ");
            int sem = this.lerInteiro("Digite o semestre da disciplina: ");

            Teacher p = this.lerProf(s);
            Student[] Students = this.lerAluno(s);

            if (Students.length == 0) {
                return;
            }

            Exam[] avs = this.lerAvaliacoes(s, Students);

            if (avs.length == 0) {
                return;
            }

            s.newClassroom(new Classroom(disciplina, ano, sem, p, Students, avs));
        }
        catch (StringIndexOutOfBoundsException | NumberFormatException | NoSuchElementException e) {
            System.out.println("Erro: Entrada inválida. Turma não adicionada.");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Erro: Entrada inválida. Turma não adicionada.");
        }
    }

    public void medianAll(AcademicSys s) {
        s.listClassroom();
    }

    public void readText(AcademicSys s) {
        try {
            FileReader f = new FileReader("students.txt");
            BufferedReader buff = new BufferedReader(f);

            while (true) {
                if (!buff.ready()) break;
                String line = buff.readLine();
                String[] data = line.split(";");
                s.newStudents(new Student(data[0], data[1], data[2]));
            }

            buff.close();
        } 
        catch (IOException e) {
            System.out.println("Erro: Arquivo não encontrado.");
        }
        
        try {
            FileReader f = new FileReader("teachers.txt");
            BufferedReader buff = new BufferedReader(f);

            while (true) {
                if (!buff.ready()) break;
                String line = buff.readLine();
                String[] data = line.split(";");
                s.newTeacher(new Teacher(data[0], data[1], Double.parseDouble(data[2])));
            }

            buff.close();
        } 
        catch (IOException e) {
            System.out.println("Erro: Arquivo não encontrado.");
        }
    }
}