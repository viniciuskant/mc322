import java.util.Date;
import java.util.Scanner;

public class Lab03{
    public static void main(String [] args){ 
        String nome, telefone, email, endereco, visualizar;
        Scanner input = new Scanner(System.in);    
        Seguradora seguradora;
        
        
        System.out.println("\n\n\tBem-vindo ao seu novo sistema de cadastros!");
        //Recebo os bados base da seguradora
        System.out.print("Nome da Seguradora: ");
        nome = input.nextLine();
        System.out.print("Telefone da Seguradora: ");
        telefone = input.nextLine();
        System.out.print("Email da Seguradora: ");
        email = input.nextLine();
        System.out.print("Endereço da Seguradora: ");
        endereco = input.nextLine();

        seguradora = new Seguradora(nome, telefone, email, endereco); //Cadastro a Seguradora

        System.out.println("\nSeu cadastro foi realizado com sucesso!\n");


        // Cadastranto uma PF
        String cpf = "71826141030";
        ClientePF clientepf = new ClientePF();
        if (ClientePF.validarCPF(cpf)){
            clientepf = new ClientePF("João", "Campinas", new Date(), "Ensino médio completo", "Masculino", "c", cpf, new Date());
            if(seguradora.cadastrarCliente(clientepf))
            System.out.print("Cliente cadastrado com sucesso!\n");
            else    
                System.out.print("Cliente não cadastrado!\n");
        }

        else{
            System.out.print("CPF inválido!\n");
        }

        // Cadastrando uma PJ
        String cnpj = "47896954000198";
        ClientePJ clientePJ1 = new ClientePJ(), clientePJ2;
        if (ClientePJ.validarCNPJ(cnpj)){
            clientePJ1 = new ClientePJ("J&M Construtora", "Valinhos", cnpj, new Date());
            if(seguradora.cadastrarCliente(clientePJ1))
                System.out.print("Cliente cadastrado com sucesso!\n");
            else    
                System.out.print("Cliente não cadastrado!\n");
        }

        else{
            System.out.print("CNPJ inválido!\n");
        }

        // Cadastrando uma PJ
        cnpj = "93134217000175";
        if (ClientePJ.validarCNPJ(cnpj)){
            clientePJ2 = new ClientePJ("Padaria PJ", "Cachoeira de Minas", cnpj, new Date());
            if (seguradora.cadastrarCliente(clientePJ2))
                System.out.print("Cliente cadastrado com sucesso!\n");
            else    
                System.out.print("Cliente não cadastradoesso!\n");
        }

        else{
            System.out.print("CNPJ inválido!\n");
        }


        // Removendo um Cliente
        for (int i = 0; i < 2; i ++){
            if(seguradora.removeCliente("Padaria PJ"))
                System.out.print("Padaria PJ" + " removido com sucesso\n");

            else
                System.out.print("Padaria PJ" + " não encontrado\n");

        }

        // Instanciando um veiculo
        Veiculo gol = new Veiculo("aaa-0000", "Volkswagen", "Gol", 1992);
        Veiculo chevette = new Veiculo("bbb-1111", "Chevrolet", "Chevette", 1978);


        // Atribuindo o veiculo a um cliente
        if (seguradora.addVeiculo("João", chevette))
            System.out.print("Veículo adicionado com sucesso!\n");
        else
            System.out.print("Não foi possível adicionar o veículo!\n");    

        if (seguradora.addVeiculo("J&M Construtora", gol))
            System.out.print("Veículo adicionado com sucesso!\n");
        else
            System.out.print("Não foi possível adicionar o veículo!\n"); 

        
        if (seguradora.addVeiculo("Padaria PJ", gol)) //Esse cliente foi removido
            System.out.print("Veículo adicionado com sucesso!\n");
        else
            System.out.print("Não foi possível adicionar o veículo!\n"); 

        // Gerando um sinistro
        Sinistro sinistro1 = new Sinistro("20180306", "Santa Rita do Sapucai", chevette, clientepf, seguradora);
        Sinistro sinistro2 = new Sinistro("20190407", "Borda da Mata", gol, clientePJ1, seguradora);

        seguradora.gerarSinistro(sinistro1);
        seguradora.gerarSinistro(sinistro2);
        
        //Chamando o metodo toString para cada objeto instanciado
        System.out.print("___________________________________________________\nDados da Seguradora:\n" + seguradora.toString() + "\n");
        System.out.print("___________________________________________________\nDados do cliente PF:\n" + clientepf.toString() + "\n");
        System.out.print("___________________________________________________\nDados do cliente PJ:\n" + clientePJ1.toString() + "\n");
        System.out.print("___________________________________________________\nDados do GOL:\n" + gol.toString() + "\n");
        System.out.print("___________________________________________________\nDados do Chevette:\n" + chevette.toString() + "\n");
        System.out.print("___________________________________________________\nDados do Sinistro (\"João\"):\n" + sinistro1.toString() + "\n");
        System.out.print("___________________________________________________\nDados do Sinistro (\"J&M COKNSTRUTORA\"):\n" + sinistro2.toString() + "\n___________________________________________________\n\n");


        // Listando clientes
        System.out.print("\n*Clientes do tipo PF:\n" + seguradora.listarClientes("PF") + "\n");
        System.out.print("\n*Clientes do tipo Pj:\n" + seguradora.listarClientes("PJ") + "\n");

        //  Visualizando sinistro
        System.out.print("\n*Visualizando sinistro de João:\n");
        seguradora.visualizarSinistro("João");
        System.out.print("\n*Visualizando sinistro de J&M Construtora:\n");
        seguradora.visualizarSinistro("J&M Construtora");

        // Listando Sinistro
        System.out.print("\n*Listando Sinistros:\n" + seguradora.listarSinistros() + "\n");


    }
}