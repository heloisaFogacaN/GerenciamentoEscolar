import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String endereco;
    private int idade;
    private static ArrayList<Usuario> usuarios = new ArrayList<>();


    public Usuario(String nome, String endereco, int idade) {
        this.nome = nome;
        this.endereco = endereco;
        this.idade = idade;
    }

    public Usuario buscarUsuario(Usuario usuarioBusca){
        System.out.println(usuarios);
        for (Usuario usuario: usuarios){
            if (usuario.nome.equals(usuarioBusca.nome)){
                if (usuario.endereco.equals(usuarioBusca.endereco)){
                    if (usuario.idade == usuarioBusca.idade){
                        return usuario;
                    }
                }
            }
        }

      return null;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public String menu(){
        return "";
    }

    public void setUsuarios() {
        usuarios.add(this);
    }
}
