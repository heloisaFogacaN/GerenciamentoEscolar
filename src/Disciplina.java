public class Disciplina {
    private String nome;
    private int codigo;
    private int cargaHoraria;

    public Disciplina(String nome,int codigo,int cargaHoraria){
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }
}
