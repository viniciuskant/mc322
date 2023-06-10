import java.util.Scanner;

public class Validacao {
    
    private static boolean numeroIguais(String teste, int limite ){
        int i, primeiroNumero = teste.charAt(0), nIguais = 0;
        for (i = 0; i < limite; i++)
            if (primeiroNumero == teste.charAt(i))
                nIguais++;

        //Testo se todos os numeros são iguais
        if (nIguais == limite - 1)
            return true;

        return false;
    }

    public static boolean validarCPF(String cpfTeste){
        int somatorio , resto, i, digito, primeiroDigito, segundoDigito;
        cpfTeste = cpfTeste.replace(".", "");
        cpfTeste = cpfTeste.replace("-", "");

        if (cpfTeste.length() != 11) //Verifica se o tamanho está correto
            return false;

        if(numeroIguais(cpfTeste, 9)) //Verifica se os digitos são iguais
            return false;

        int[] digitos = new int[11];
        for (i = 0; i < 11; i++) {
            if (cpfTeste.charAt(i) < 48 || cpfTeste.charAt(i) > 57)
                return false;
            digitos[i] = cpfTeste.charAt(i) - 48;
        }
            

        primeiroDigito = digitos[9];
        segundoDigito = digitos[10];
        
        //Primeiro digito
        somatorio = 0;
        for (i = 0; i < 9; i++)
            somatorio += digitos[i] * (10 - i);

        resto = somatorio % 11;
        digito = resto < 2 ? 0 : 11 - resto;
        
        if (digito != primeiroDigito)
            return false;

        //Segundo Digito
        somatorio = 0;
        for (i = 0; i < 10; i++)
            somatorio += digitos[i] * (11 - i); 

        resto = somatorio % 11;
        digito = resto < 2 ? 0 : 11 - resto;

        if (digito != segundoDigito)
            return false;
       
        return true;
    }

    public static boolean validarCNPJ(String cnpjTeste){
        int somatorio, i, primeiroDigito, segundoDigito, resto, digito;
        cnpjTeste = cnpjTeste.replace(".", "");
        cnpjTeste = cnpjTeste.replace("-", "");       
        cnpjTeste = cnpjTeste.replace("/", "");


        if (cnpjTeste.length() != 14) //Verifica se o tamanho está correto
            return false;

        if(numeroIguais(cnpjTeste, 12))
            return false;

        int[] digitos = new int[14];
        for (i = 0; i < 14; i++) {
            if (cnpjTeste.charAt(i) < 48 || cnpjTeste.charAt(i) > 57)
                return false;
            digitos[i] = cnpjTeste.charAt(i) - 48;
        }
        

        primeiroDigito = cnpjTeste.charAt(12) - 48;
        segundoDigito = cnpjTeste.charAt(13) - 48;
        
        //Primeiro digito    
        somatorio = 0;
        for (i = 0; i < 4; i++) {
            somatorio += digitos[i] * (5 - i);
        }
        for (i = 4; i < 12; i++) {
            somatorio += digitos[i] * (13 - i);
        }

        resto = somatorio % 11;
        digito = resto < 2 ? 0 : 11 - resto;

        if (digito != primeiroDigito)
            return false;

        //Segundo Digito
        somatorio = 0;
        for (i = 0; i < 5; i++) {
            somatorio += digitos[i] * (6 - i);
        }
        for (i = 5; i < 13; i++) {
            somatorio += digitos[i] * (14- i);
        }

        resto = somatorio % 11;
        digito = resto < 2 ? 0 : 11 - resto;

        if (digito != segundoDigito)
            return false;

        else
            return true;
    }

    public static boolean validaNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return false;
        }

        for (char letra : nome.toCharArray()) {
            if (!Character.isLetter(letra) && letra != ' ') {
                return false;
            }
        }
        return true;
    }
    
    public static boolean validarData(String data){
        String[] dataSeparada = data.split("/");
        if(dataSeparada.length != 3)
            return false;

        for (String n: dataSeparada){
            if(!ehInteiro(n))
                return false;
        }
        
        return Date.dataValida(dataSeparada[0], dataSeparada[1], dataSeparada[2]);
    }

    public static boolean ehInteiro(String string){
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    // Metodo que le o Terminal
    private static String lerString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    // Metodo que le uma data válida do terminal
    public static Date lerData(String info) {
        String data;
        System.out.print(info + " (dd/MM/aaaa): ");
        data = lerString();
        while (!validarData(data)) { // Não sai do loop enquanto a entrada não for valida
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
        while (!validaNome(nome)) { // Não sai do loop enquanto a entrada não for valida
            System.out.print("Nome invalido!\n" + info + ": ");
            nome = lerString();
        }
        return nome;
    }

    // Metodo  que le um cpf valido do terminal
    public static String lerCPF(){
        System.out.print("CPF: ");
        String  cpf = lerString();
        while (!validarCPF(cpf)) { // Não sai do loop enquanto a entrada não for valida
            System.out.print("CPF inválido!\nCPF: ");
            cpf = lerString();
        }
        return cpf;
    }
    
    // Metodo  que le um cpf valido do terminal
    public static String lerCNPJ(){
        System.out.print("CNPJ: ");
        String  cnpj = lerString();
        while (!validarCNPJ(cnpj)) { // Não sai do loop enquanto a entrada não for valida
            System.out.print("CPF inválido!\nCNPJ: ");
            cnpj = lerString();
        }
        return cnpj;
    }
}
