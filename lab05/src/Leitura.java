import java.util.Scanner;

public class Leitura {

    //Metodo que le o Terminal
	public static String lerString(){
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}

    //Metodo que converte String em inteiro
	public static int INT(String string){
		return Integer.parseInt(string);
	} 

	//Metodo que interage com o usuário e retorna um ClientePF
	public static ClientePF lerPF(){
        String nome, endereco, data, educacao, genero, cpf, telefone, email;
        Date dataNascimento;  

        System.out.print("Nome da Pessoa: ");
        nome = lerString();
        while(!Validacao.validaNome(nome)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Nome invalido!\nNome da Pessoa: ");
            nome = lerString();
        }

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Telefone: ");
        telefone = lerString();

        System.out.print("Email: ");
        email = lerString();

        System.out.print("Data de Nascimento(dd/MM/aaaa): ");
        data = lerString();
        while(!Validacao.validarData(data)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Data invalida\nData de licença(dd/MM/aaaa): ");
            data = lerString();
        }
        
        String[] dataSeparada = data.split("/");
        dataNascimento = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("Escolaridade: ");
        educacao = lerString();

        System.out.print("Gênero: ");
        genero = lerString();

        System.out.print("CPF: ");
        cpf = lerString();
        while(!Validacao.validarCPF(cpf)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("CPF inválido!\nCPF: ");
            cpf = lerString();
        }

        return new ClientePF(nome, endereco, telefone, email, educacao, genero, cpf, dataNascimento);
    }

	//Metodo que interage com o usuário e retorna um ClientePJ
    public static ClientePJ lerPJ(){
        String nome, endereco, data, cnpj, telefone, email;
        Date dataFundacao;

        System.out.print("Nome da Empresa: "); //Não sai do loop enquanto a entrada não for valida
        nome = lerString();
        while(!Validacao.validaNome(nome)){
            System.out.print("Nome invalido!\nNome da Empresa: ");
            nome = lerString();
        }

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Telefone: ");
        telefone = lerString();

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Email: ");
        email = lerString();

		System.out.print("Data de licença(dd/MM/aaaa): ");
        data = lerString();
        while(!Validacao.validarData(data)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Data invalida\nData de licença(dd/MM/aaaa): ");
            data = lerString();
        }

        String[] dataSeparada = data.split("/");
        dataFundacao = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("CNPJ: ");
        cnpj = lerString();
		while(!Validacao.validarCNPJ(cnpj)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("CNPJ inválido!\n CNPJ: ");
            cnpj = lerString();
        }

        // System.out.print("Número de Funcionarios: ");
        // nFuncionarios = lerString();
		// while(!Validacao.ehInteiro(nFuncionarios) && INT(nFuncionarios) < 0){ //Não sai do loop enquanto a entrada não for valida
        //     System.out.print("Número de Funcinários invalido!\nNúmero de Funcionarios: ");
        //     nFuncionarios = lerString();
        // }

        return new ClientePJ(nome, endereco, telefone, email, cnpj, dataFundacao);
    }

	//Metodo que interage com o usuário e retorna um Cliente
    public static Cliente lerCliente(){
        
        System.out.print("Tipo de cliente:\n0 - Pessoa Fisica\n1 - Pessoa Juridica\nDigite uma opcao:\n");
        String tipoCadastro = lerString();
        while(true){ //Não sai do loop enquanto a entrada não for uma das opcoes
            if(tipoCadastro.equals("0")){
                return lerPF();  
            }
            else if(tipoCadastro.equals("1")){
                return lerPJ();
            }
            else{
                System.out.println("Opcao invalida.\nEscolha uma opcao:\n");
				tipoCadastro = lerString();
            }
        }
    }

	//Metodo que interage com o usuário e retorna um Sinistro
    public static Sinistro lerSinistro(){
        Date dataSinistro;
        String endereco, data, dataSeparada[];

        System.out.print("Data do Sinistro(dd/MM/aaaa): ");
        data = lerString();
        while(!Validacao.validarData(data)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Data invalida!\nData do Sinistro(dd/MM/aaaa): ");
            data = lerString();
        }
        
        dataSeparada = data.split("/");
        dataSinistro = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("Endereco: ");
        endereco = lerString();

        return new Sinistro(dataSinistro, endereco);

    }

	//Metodo que interage com o usuário e retorna um Veiculo
    public static Veiculo lerVeiculo(){
        String placa, marca, modelo;
        String anoFabricacao;

        System.out.print("Placa do veículo: ");
        placa = lerString();

        System.out.print("Marca: ");
        marca = lerString();

        System.out.print("Modelo: ");
        modelo = lerString();

        System.out.print("Ano de fabricação do veículo: ");
        anoFabricacao = lerString();
		while(!Validacao.ehInteiro(anoFabricacao)){ //Não sai do loop enquanto a entrada não for valida
			System.out.print("Ano de fabricação invalido!\nAno de fabricação:");
			anoFabricacao = lerString();
		}

        return new Veiculo(placa, marca, modelo, INT(anoFabricacao));
    }

	//Metodo que interage com o usuário e retorna uma Seguradora
    public static Seguradora lerSeguradora(){
        String nome, telefone, email, endereco, cnpj;

        System.out.print("Nome da Seguradora: ");
        nome = lerString();
        while(!Validacao.validaNome(nome)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Nome invalido!\nNome da Seguradora: ");
            nome = lerString();
        }

        System.out.print("CNPJ: ");
        cnpj = lerString();
		while(!Validacao.validarCNPJ(cnpj)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("CNPJ inválido!\n CNPJ: ");
            cnpj = lerString();
        }
		
        System.out.print("Telefone da Seguradora: ");
        telefone = lerString();

        System.out.print("Email da Seguradora: ");
        email = lerString();

        System.out.print("Endereço da Seguradora: ");
        endereco = lerString();

        return new Seguradora(nome, cnpj, telefone, endereco, email); //Cadastra a Seguradora
    }
	
}
