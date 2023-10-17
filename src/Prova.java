public class Prova {
    private Disciplina disciplina;
    private double nota;

    public Prova(Disciplina disciplina, double nota) {
        this.disciplina = disciplina;
        this.nota = nota;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public double getNota() {
        return nota;
    }
}
