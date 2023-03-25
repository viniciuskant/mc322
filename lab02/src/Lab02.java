import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Scanner;

public class Lab02{
    public static void main(String [] args){ 
        while(true){
            String input, nome, data, idade, endereco, cpf, email, telefone, placa, marca, modelo;
            Scanner scanner = new Scanner(System.in);
            Cliente cliente;
            Seguradora seguradora;
            Sinistro sinistro;
            Veiculo veiculo;
            
            System.out.println("\n\n\t\tFaça um cadastro!\n\nQual tipo:\n(1)Cliente\n(2)Seguradora\n(3)Sinistro\n(4)Veiculo\n");
            
            
            input = scanner.nextLine();
            
            if (input.equals("1") || input.equals("Cliente") || input.equals("cliente")){
                System.out.print("Nome: ");
                nome = scanner.nextLine();
                System.out.print("Data de nascimento (ddmmaaaa): ");
                data = scanner.nextLine();
                System.out.print("Idade: ");
                idade = scanner.nextLine();
                System.out.print("Endereco: ");
                endereco = scanner.nextLine();
                System.out.print("CPF: ");
                cpf = scanner.nextLine();

                cliente = new Cliente(nome, data, idade, endereco, cpf);

                System.out.println("\nCadastro realizado com sucesso!\n\nDados inseridos:");
                System.out.println(cliente.toString());


            }

            else if (input.equals("2") || input.equals("Seguradora") || input.equals("seguradora")){
                System.out.print("Nome: ");
                nome = scanner.nextLine();
                System.out.print("Telefone: ");
                telefone = scanner.nextLine();
                System.out.print("Email: ");
                email = scanner.nextLine();
                System.out.print("Endereco: ");
                endereco = scanner.nextLine();


                seguradora = new Seguradora(nome, telefone, email, endereco);

                System.out.println("\nCadastro realizado com sucesso!\n\nDados inseridos:");
                System.out.println(seguradora.toString());

            }

            else if (input.equals("3") || input.equals("Sinistro") || input.equals("sinistro")){
                System.out.print("Data: ");
                data = scanner.nextLine();
                System.out.print("Endereco: ");
                endereco = scanner.nextLine();

                sinistro = new Sinistro(data, endereco);

                System.out.println("\nCadastro realizado com sucesso!\n\nDados inseridos:");
                System.out.println(sinistro.toString());
            }

            else if (input.equals("4") || input.equals("Veiculo") || input.equals("veiculo")){
                System.out.print("Placa: ");
                placa = scanner.nextLine();
                System.out.print("Marca: ");
                marca = scanner.nextLine();
                System.out.print("Modelo: ");
                modelo = scanner.nextLine();

                veiculo = new Veiculo(placa, marca, modelo);

                System.out.println("\nCadastro realizado com sucesso!\n\nDados inseridos:");
                System.out.println(veiculo.toString());
            }

        }
    }
}