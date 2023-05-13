public class Validacao {
    
    private static boolean numeroIguais(String cpfTeste){
        int i, primeiroNumero = cpfTeste.charAt(0);
        for (i = 0; i < 9  && primeiroNumero == cpfTeste.charAt(i) ; i++)

        //Testo se todos os numeros são iguais
        if (i == 9)
            return true;

        return false;
    }

    public static boolean validarCPF(String cpfTeste){
        int somatorio , resto, i, digito, primeiroDigito, segundoDigito;
        cpfTeste = cpfTeste.replace(".", "");
        cpfTeste = cpfTeste.replace("-", "");

        if (cpfTeste.length() != 11) //Verifica se o tamanho está correto
            return false;

        if(numeroIguais(cpfTeste)) //Verifica se os digitos são iguais
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

        if (cnpjTeste.length() != 14) //Verifica se o tamanho está correto
            return false;

        int[] digitos = new int[14];
        for (i = 0; i < 14; i++) {
            if (cnpjTeste.charAt(i) - 48 < 0 || cnpjTeste.charAt(i) - 48 > 9)
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
    
}
