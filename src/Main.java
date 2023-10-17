
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    //    static Turma turma = new Turma("MI72");
    static Disciplina programacaoJava = new Disciplina("Programação Java", 3, 340);
    static Disciplina programacaoOrientadaAObjetos = new Disciplina("Programação Orientada a Objetos", 2, 100);
    static Disciplina logicaDeProgramacao = new Disciplina("Lógica de Programação", 1, 160);

//    static Professor professor = new Professor("A", "A", 1, turma, programacaoJava);
//    static Aluno aluno = new Aluno("B", "B", 1, turma);
//    static Secretario secretario = new Secretario("C", "C", 1);

    public static void main(String[] args) {
//        professor.setUsuarios();
//        aluno.setUsuarios();
//        secretario.setUsuarios();
//        turma.adicionarTurma();
//        turma.adicionarProfessor(professor);
//        turma.adicionarAluno(aluno);

        do {
            telaInicial();
        } while (true);


    }

    private static void telaInicial() {
        System.out.println("""
                SEJA BEM-VINDO!
                                
                O que deseja fazer?
                1 - Se cadastrar
                2 - Entrar na conta
                3 - Encerrar Aplicação
                """);
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> fazerLogin();
            case 3 -> System.exit(0);
            default -> System.out.println("Insira um número válido.");
        }

    }

    private static void fazerLogin() {
        System.out.println("Informe seu nome:");
        String nome = sc.next();
        System.out.println("Informe seu endereço:");
        String endereco = sc.next();
        System.out.println("Informe sua idade:");
        int idade = sc.nextInt();
        Usuario usuarioBusca = new Usuario(nome, endereco, idade);
        Usuario usuarioLogado = usuarioBusca.buscarUsuario(usuarioBusca);
        if (usuarioLogado != null) {
            menu(usuarioLogado);
        } else {
            System.out.println("As informações concedidas não coferem com nenhum usuário deste sistema, por favor revise os dados e tente novamente.");
        }
    }

    private static void menu(Usuario usuarioLogado) {
        do {
            System.out.println(usuarioLogado.menu());
            int opcao = sc.nextInt();

            switch (opcao) {
                case 0 -> usuarioLogado = null;
                case 1 -> {
                    if (usuarioLogado instanceof Professor) {
                        verMediaDeAlunos((Professor) usuarioLogado);
                    } else if (usuarioLogado instanceof Aluno) {
                        verMedia((Aluno) usuarioLogado);
                    } else {
                        gerarBoletim();
                    }
                }
                case 2 -> {
                    if (usuarioLogado instanceof Professor) {
                        gerarNota((Professor) usuarioLogado);
                    } else if (usuarioLogado instanceof Secretario) {
                        alocarUsuarios();
                    } else {
                        System.out.println("Número inserido é inválido, tente novamente");
                    }
                }
                case 3 -> {
                    if (usuarioLogado instanceof Secretario) {
                        atribuirDisciplina();
                    } else {
                        System.out.println("Número inserido é inválido, tente novamente");

                    }
                }
                case 4 -> {
                    if (usuarioLogado instanceof Secretario) {
                        criarTurma();
                    } else {
                        System.out.println("Número inserido é inválido, tente novamente");
                    }
                }
                default -> System.out.println("Número inserido é inválido, tente novamente");
            }
        } while (usuarioLogado != null);

    }

    private static void verMedia(Aluno usuarioLogado) {
        for (Professor professor : usuarioLogado.getTurma().getProfessores()) {
            System.out.println("Matéria de " + professor.getDisciplina());
            int i = 0;
            double media = 0;
            for (Prova prova : usuarioLogado.getProvas()) {
                if (prova.getDisciplina() == professor.getDisciplina()) {
                    i++;
                    media += prova.getNota();
                }
            }
            System.out.println("Média: " + media / i);
        }
    }


    private static void gerarBoletim() {
        System.out.println("Informe o nome da turma em que deseja gerar boletim");
        String nome = sc.next();
        Turma turmaBusca = new Turma(nome);
        Turma turmaEncontrada = turmaBusca.buscarTurma();

        if (turmaEncontrada != null) {
            for (Professor professor : turmaEncontrada.getProfessores()) {
                System.out.println("Matéria de " + professor.getDisciplina().getNome());
                if (turmaEncontrada.getAlunos().size() > 0) {
                    for (Aluno aluno : turmaEncontrada.getAlunos()) {
                        System.out.println("Aluno " + aluno.getNome());
                        int i = 0;
                        double media = 0;
                        for (Prova prova : aluno.getProvas()) {
                            if (prova.getDisciplina() == professor.getDisciplina()) {
                                i++;
                                System.out.println("Nota " + i + ": " + prova.getNota());
                                media += prova.getNota();
                            }
                        }
                        System.out.println("Média: " + media / i + "\n\n");
                    }
                } else {
                    System.out.println("Essa turma não tem alunos.");
                }

            }
        } else {
            System.out.println("Essa turma não existe.");
        }
    }

    private static void criarTurma() {
        System.out.println("Informe o nome da nova turma:");
        String nome = sc.next();
        Turma turmaBusca = new Turma(nome);
        Turma turmaEncontrada = turmaBusca.buscarTurma();

        if (turmaEncontrada == null) {
            turmaBusca.adicionarTurma();
            System.out.println("Turma adicionada com sucesso.");
        } else {
            System.out.println("Já existe uma turma com esse nome.");
        }
    }

    private static void atribuirDisciplina() {
        System.out.println("Informe o nome do professor a ter uma nova disciplina:");
        String nomeUsuario = sc.next();
        System.out.println("Informe o endereço do professor a ter uma nova disciplina:");
        String enderecoUsuario = sc.next();
        System.out.println("Informe a idade do professor a ter uma nova disciplina: ");
        int idadeUsuario = sc.nextInt();
        Usuario usuarioBusca = new Usuario(nomeUsuario, enderecoUsuario, idadeUsuario);
        Usuario usuarioAAdicionar = usuarioBusca.buscarUsuario(usuarioBusca);


        if (usuarioAAdicionar != null && usuarioAAdicionar instanceof Professor) {
            do {
                System.out.println("""
                        Informe a matéria que esse professor lecionará:
                        1 - Lógica de Programação
                        2 - Programação Orientada A Objetos
                        3 - Programação Java
                        """);
                int opcao = sc.nextInt();
                switch (opcao) {
                    case 1 -> ((Professor) usuarioAAdicionar).setDisciplina(logicaDeProgramacao);
                    case 2 -> ((Professor) usuarioAAdicionar).setDisciplina(programacaoOrientadaAObjetos);
                    case 3 -> ((Professor) usuarioAAdicionar).setDisciplina(programacaoJava);
                    default -> System.out.println("Insira um número válido para a disciplina");
                }
            } while (((Professor) usuarioAAdicionar).getDisciplina() == null);

        } else {
            System.out.println("Esse usuario ou não existe ou não é um professor.");
        }
    }

    private static void alocarUsuarios() {
        System.out.println("Informe o nome da turma em que deseja alocar usuários:");
        String nome = sc.next();
        Turma turmaBusca = new Turma(nome);
        Turma turmaEncontrada = turmaBusca.buscarTurma();

        if (turmaEncontrada != null) {
            System.out.println("Informe o nome do usuário a ser incluído na turma:");
            String nomeUsuario = sc.next();
            System.out.println("Informe o endereço do usuário a ser incluído na turma:");
            String enderecoUsuario = sc.next();
            System.out.println("Informe a idade do usuário a ser incluído na turma: ");
            int idadeUsuario = sc.nextInt();
            Usuario usuarioBusca = new Usuario(nomeUsuario, enderecoUsuario, idadeUsuario);
            Usuario usuarioAAdicionar = usuarioBusca.buscarUsuario(usuarioBusca);
            if (usuarioAAdicionar != null) {
                if (!(usuarioAAdicionar instanceof Secretario)) {
                    if (usuarioAAdicionar instanceof Professor) {
                        ((Professor) usuarioAAdicionar).setTurma(turmaEncontrada);
                        turmaEncontrada.adicionarProfessor((Professor) usuarioAAdicionar);
                        System.out.println("Professor adicionado a turma com sucesso.");
                    } else {
                        ((Aluno) usuarioAAdicionar).setTurma(turmaEncontrada);
                        turmaEncontrada.adicionarAluno((Aluno) usuarioAAdicionar);
                        System.out.println("Aluno adicionado a turma com sucesso.");
                    }
                } else {
                    System.out.println("Você não pode adicionar um secretário a uma turma");
                }
            } else {
                System.out.println("As informações concedidas não coferem com nenhum usuário deste sistema, por favor revise os dados e tente novamente.");
            }
        } else {
            System.out.println("Essa turma não existe");
        }

    }

    private static void verMediaDeAlunos(Professor usuarioLogado) {
        ArrayList<Aluno> alunos = usuarioLogado.getTurma().getAlunos();
        for (Aluno aluno : alunos) {
            double media = 0;
            if (aluno.getProvas().size() > 0) {
                for (Prova prova : aluno.getProvas()) {
                    if (prova.getDisciplina().equals(usuarioLogado.getDisciplina())) {
                        media += prova.getNota();
                    }
                }
                media = media / aluno.getProvas().size();
                System.out.println("O aluno " + aluno.getNome() + " tem a média de " + media + " na sua disciplina de " + usuarioLogado.getDisciplina().getNome());
            } else {
                System.out.println("O aluno " + aluno.getNome() + " não realizou nenhuma prova ainda.");
            }

        }
    }

    private static void gerarNota(Professor usuarioLogado) {
        ArrayList<Aluno> alunos = usuarioLogado.getTurma().getAlunos();
        if (alunos.size() > 0) {
            for (Aluno aluno : alunos) {
                double nota = -10;
                do {
                    System.out.println("Informe a nota do aluno " + aluno.getNome());
                    nota = sc.nextDouble();
                    if (nota >= 0 && nota <= 10) {
                        System.out.println("Entrei");
                        System.out.println(usuarioLogado.getDisciplina());
                        Prova prova = new Prova(usuarioLogado.getDisciplina(), nota);
                        aluno.adicionarProva(prova);
                    } else {
                        System.out.println("Insira uma nota de 0 a 10 por favor.");
                    }
                } while (nota < 0 || nota > 10);

            }
            System.out.println("Notas geradas com sucesso");
        } else {
            System.out.println("Essa turma ainda não tem alunos professor.");
        }


    }

    private static void cadastrar() {
        System.out.println("Nos informe seu nome:");
        String nome = sc.next();
        System.out.println("Agora nos informe seu endereço:");
        String endereco = sc.next();
        System.out.println("Nos informe sua idade:");
        int idade = sc.nextInt();
        Usuario usuarioBusca = new Usuario(nome, endereco, idade);
        Usuario usuarioLogado = usuarioBusca.buscarUsuario(usuarioBusca);

        if (usuarioLogado == null) {
            if (idade > 0) {
                System.out.println("""
                        Agora nos informe que tipo de usuário você é:
                        1 - Aluno
                        2 - Professor
                        3 - Secretário
                        """);
                int opcao = sc.nextInt();

                switch (opcao) {
                    case 1 -> {
                        Usuario novoUsuario = new Aluno(nome, endereco, idade, null);
                        System.out.println("Cadastro realizado com sucesso.");
                        novoUsuario.setUsuarios();
                    }
                    case 2 -> {
                        Usuario novoUsuario = new Professor(nome, endereco, idade, null, null);
                        System.out.println("Cadastro realizado com sucesso.");
                        novoUsuario.setUsuarios();
                    }
                    case 3 -> {
                        Usuario novoUsuario = new Secretario(nome, endereco, idade);
                        System.out.println("Cadastro realizado com sucesso.");
                        novoUsuario.setUsuarios();
                    }
                    default -> System.out.println("Informe um número válido.");


                }
            } else {
                System.out.println("Idade inválida.");
            }
        } else {
            System.out.println("Já existe um usuário com exatamene essas informações.");
        }


    }

}
