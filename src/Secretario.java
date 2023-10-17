public class Secretario extends Usuario {

    private int quantidadeDeBoletins;

    public Secretario(String nome, String endereco, int idade){
        super(nome, endereco, idade);
        this.quantidadeDeBoletins = 0;
    }

    @Override
    public String menu() {
        return """
                1 - Gerar boletim
                2 - Alocar usuarios à turmas
                3 - Atribuir disciplina
                4 - Criar Turma.
                0 - Logout
                """;
    }
}
