import java.util.Scanner;

public class Gerar {
    public static ClientePF PF(){
        String nome, endereco, data, educacao, genero, classeEconomica, cpf;
        Date dataLicenca;
        Scanner input = new Scanner(System.in);    

        System.out.print("Nome da Pessoa: ");
        nome = input.nextLine();

        while(!Validacao.validaNome(nome)){
            System.out.println("NOME INVALIDO!");
            System.out.print("Nome da Pessoa: ");
            nome = input.nextLine();
        }
        System.out.print("Endereço: ");
        endereco = input.nextLine();

        System.out.print("Data de licença(dd/MM/aaaa): ");
        data = input.nextLine();
        String[] dataSeparada = data.split("/");
        dataLicenca = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("Escolaridade: ");
        educacao = input.nextLine();

        System.out.print("Gênero: ");
        genero = input.nextLine();

        System.out.print("Classe economica: ");
        classeEconomica = input.nextLine();

        System.out.print("CPF: ");
        cpf = input.nextLine();
        while(Validacao.validarCPF(cpf)){
            System.out.print("CPF inválido! Favor inserir novamente.\n CPF: ");
            cpf = input.nextLine();
        }

        return new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, cpf, dataLicenca);
    }

    public static ClientePJ PJ(){
        String nome, endereco, data, cnpj;
        int nFuncionarios;
        Date dataLicenca;
        Scanner input = new Scanner(System.in);  

        System.out.print("Nome da Empresa: ");
        nome = input.nextLine();

        while(!Validacao.validaNome(nome)){
            System.out.println("NOME INVALIDO!");
            System.out.print("Nome da Empresa: ");
            nome = input.nextLine();
        }

        System.out.print("Endereço: ");
        endereco = input.nextLine();

        System.out.print("Data de licença(dd/MM/aaaa): ");
        data = input.nextLine();
        String[] dataSeparada = data.split("/");
        dataLicenca = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("CNPJ: ");
        cnpj = input.nextLine();

        System.out.println("Número de Funcionarios");
        nFuncionarios = Integer.parseInt(input.nextLine());

        while(!Validacao.validarCNPJ(cnpj)){
            System.out.print("CNPJ inválido! Favor inserir novamente.\n CNPJ: ");
            cnpj = input.nextLine();
        }

        return new ClientePJ(nome, endereco, cnpj, dataLicenca, nFuncionarios);
    }

    public static Cliente cliente(){
        Scanner input = new Scanner(System.in);
        String tipoCadastro = input.nextLine();
        boolean opcaoIncorreta = false;

        System.out.print("Agora vamos cadastrar o cliente que sofreu o sinistro!\n (1)Pessoa Fisica\n (2)Pessoa Juridica\n");

        while(opcaoIncorreta){
            if(tipoCadastro.equals("1")){
                return Gerar.PF();  
            }
            else if(tipoCadastro.equals("2")){
                return Gerar.PJ();
            }
            else{
                System.out.println("OPÇÃO INVALIDA! TENTE NOVAMENTE.");
            }
        }
        return new Cliente();
    }

    public static Sinistro sinistro(){
        Date dataSinistro;
        Veiculo veiculo;
        String endereco, data, dataSeparada[];
        Scanner input = new Scanner(System.in);

        System.out.print("\n\nVamos cadastrar os dados do sinistro!\nData do sinistro(aaaa/mm/dd): ");
                data = input.nextLine();
                dataSeparada = data.split("/");
                dataSinistro = new Date(dataSeparada[1], dataSeparada[1], dataSeparada[2]);

                System.out.print("Endereco: ");
                endereco = input.nextLine();

                veiculo = veiculo();

                Cliente cliente = cliente();
                // Pergintar se deseja add veiculo
                // pessoa.addVeiculo(veiculo);

                return new Sinistro(dataSinistro, endereco, veiculo, cliente);

    }

    public static Veiculo veiculo(){
        String placa, marca, modelo;
        int anoFabricacao;
        Scanner input = new Scanner(System.in);

        System.out.print("\nAgora vamos cadastrar o veiculo!\nPlaca do veículo:");
        placa = input.nextLine();
        System.out.print("Marca do veículo: ");
        marca = input.nextLine();
        System.out.print("Modelo do veículo: ");
        modelo = input.nextLine();
        System.out.print("Ano de fabricação do veículo: ");
        anoFabricacao = Integer.parseInt(input.nextLine());
        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }

    public static Seguradora seguradora(){
        String nome, telefone, email, endereco;
        Scanner input = new Scanner(System.in);

        System.out.print("Nome da Seguradora: ");
        nome = input.nextLine();
        while(!Validacao.validaNome(nome)){
            System.out.println("NOME INVALIDO!");
            System.out.print("Nome da Seguradora: ");
            nome = input.nextLine();
        }
        System.out.print("Telefone da Seguradora: ");
        telefone = input.nextLine();
        System.out.print("Email da Seguradora: ");
        email = input.nextLine();
        System.out.print("Endereço da Seguradora: ");
        endereco = input.nextLine();

        return new Seguradora(nome, telefone, email, endereco); //Cadastro a Seguradora
    }
}
