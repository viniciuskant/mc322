// public class Cpf {
//     private String cpf;
//     private int numero;
//     private int digito;
//     private boolean valido;

//     public boolean isValido() {
//         return valido;
//     }

//     //construtor
//     public Cpf(String cpf){
//         this.cpf = cpf;
//         this.numero = extrai_numero(cpf);
//         this.digito = extrai_digito(cpf);
//         this.valido = verifica();
//     }

//     private int extrai_numero(String cpf){
//         cpf = cpf.replaceAll(".", "");
//         cpf = cpf.replaceAll("-", "");
//         int numero = 0;

//         for (int i = 8; i >= 0; i--){
//             numero += (cpf.charAt(i) -  48)*10^(8-i);
//         }
//         return numero;
//     }

//     private int extrai_digito(String cpf){
//         int digito = 0;
//         digito += cpf.charAt(10) -  48;
//         digito += (cpf.charAt(9) -  48) * 10;
//         return digito;
//     }

//     private boolean verifica(){
        
//     }

// }
