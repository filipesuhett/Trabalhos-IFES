import java.io.IOException;

/**
 * Classe principal
 * @authors Filipe Suhett, Giovanna Scalfoni, Hilario Seibel Junior
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Entrada io = new Entrada();
        AcademicSys s = new AcademicSys();
        io.readText(s);

        int op = io.menu1();

        while (op != 0) {
            if (op == 1) {
                io.cadProf(s);
            }
            if (op == 2) {
                io.cadAluno(s);
            }
            if (op == 3) {
                io.cadTurma(s);
            }
            if (op == 4) {
                io.medianAll(s);
            }

            op = io.menu1();
        }
    }
}