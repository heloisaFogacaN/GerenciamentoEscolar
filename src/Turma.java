import java.util.ArrayList;

public class Turma {

    private String nome;
    private ArrayList<Professor> professores = new ArrayList<>();
    private ArrayList<Aluno> alunos = new ArrayList<>();
    private static ArrayList<Turma> turmas = new ArrayList<>();

    public Turma(String nome) {
        this.nome = nome;
    }

    public void adicionarProfessor(Professor usuario) {
        professores.add(usuario);
    }

    public void adicionarAluno(Aluno usuario) {
        alunos.add(usuario);
    }

    public ArrayList<Turma> getTurmas() {
        return turmas;
    }

    public Turma buscarTurma() {
        for (Turma turma : getTurmas()) {
            if (turma.nome.equals(this.nome)) {
                return turma;
            }
        }
        return null;
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void adicionarTurma() {
        turmas.add(this);
    }
}
