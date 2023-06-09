import java.util.Scanner;

public class Leitura {

    // Metodo que le o Terminal
    public static String lerString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    // Metodo que le uma data válida do terminal
    public static Date lerData(String info) {
        String data;
        System.out.print(info + " (dd/MM/aaaa): ");
        data = lerString();
        while (!Validacao.validarData(data)) { // Não sai do loop enquanto a entrada não for valida
            System.out.print("Data invalida\n" + info + " (dd/MM/aaaa): ");
            data = lerString();
        }

        String[] dataSeparada = data.split("/");
        return new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);
    }

    // Metodo que le um nome válido do terminal
    public static String lerNome(String info){
        String nome;
        System.out.print(info + ": ");
        nome = lerString();
        while (!Validacao.validaNome(nome)) { // Não sai do loop enquanto a entrada não for valida
            System.out.print("Nome invalido!\n" + info + ": ");
            nome = lerString();
        }
        return nome;
    }

    // Metodo  que le um cpf valido do terminal
    public static String lerCPF(){
        System.out.print("CPF: ");
        String  cpf = lerString();
        while (!Validacao.validarCPF(cpf)) { // Não sai do loop enquanto a entrada não for valida
            System.out.print("CPF inválido!\nCPF: ");
            cpf = lerString();
        }
        return cpf;
    }
    
    // Metodo  que le um cpf valido do terminal
    public static String lerCNPJ(){
        System.out.print("CNPJ: ");
        String  cnpj = lerString();
        while (!Validacao.validarCNPJ(cnpj)) { // Não sai do loop enquanto a entrada não for valida
            System.out.print("CPF inválido!\nCNPJ: ");
            cnpj = lerString();
        }
        return cnpj;
    }
    
    // Metodo que converte String em inteiro
    public static int INT(String string) {
        return Integer.parseInt(string);
    }

    // Metodo que interage com o usuário e retorna um ClientePF
    public static ClientePF lerPF() {
        String nome, endereco, educacao, genero, cpf, telefone, email;
        Date dataNascimento;

        nome = lerNome("Nome");

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Telefone: ");
        telefone = lerString();

        System.out.print("Email: ");
        email = lerString();

        dataNascimento = lerData("Data de Nascimento");

        System.out.print("Escolaridade: ");
        educacao = lerString();

        System.out.print("Gênero: ");
        genero = lerString();

        cpf = lerCPF();

        return new ClientePF(nome, endereco, telefone, email, educacao, genero, cpf, dataNascimento);
    }

    // Metodo que interage com o usuário e retorna um ClientePJ
    public static ClientePJ lerPJ() {
        String nome, endereco, cnpj, telefone, email;
        Date dataFundacao;

        nome = lerNome("Nome da Empresa");

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Telefone: ");
        telefone = lerString();

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Email: ");
        email = lerString();

        dataFundacao = lerData("Data de lincenca");

        cnpj = lerCNPJ();

        return new ClientePJ(nome, endereco, telefone, email, cnpj, dataFundacao);
    }

    // Metodo que interage com o usuário e retorna um Cliente
    public static Cliente lerCliente() {

        System.out.print("Tipo de cliente:\n0 - Pessoa Fisica\n1 - Pessoa Juridica\nDigite uma opcao:\n");
        String tipoCadastro = lerString();
        while (true) { // Não sai do loop enquanto a entrada não for uma das opcoes
            if (tipoCadastro.equals("0")) {
                return lerPF();
            } else if (tipoCadastro.equals("1")) {
                return lerPJ();
            } else {
                System.out.println("Opcao invalida.\nEscolha uma opcao:\n");
                tipoCadastro = lerString();
            }
        }
    }

    // Metodo que interage com o usuário e retorna uma Seguradora
    public static Seguradora lerSeguradora() {
        String nome, telefone, email, endereco, cnpj;

        nome = lerNome("Nome da Seguradora");

        cnpj = lerCNPJ();

        System.out.print("Telefone da Seguradora: ");
        telefone = lerString();

        System.out.print("Email da Seguradora: ");
        email = lerString();

        System.out.print("Endereço da Seguradora: ");
        endereco = lerString();

        return new Seguradora(nome, cnpj, telefone, endereco, email); // Cadastra a Seguradora
    }

}
