import java.time.ZonedDateTime;

public class Date {
    private int ano;
    private int mes;
    private int dia;
    
    // Construtor
    public Date(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Date(String dia, String mes, String ano){
        this.dia = Integer.parseInt(dia);
        this.mes = Integer.parseInt(mes);
        this.ano = Integer.parseInt(ano);
    }

    // Getters and Setters
    public int getDia() {
        return dia;
    }
    
    public void setDia(int dia) {
        this.dia = dia;
    }
    
    public int getMes() {
        return mes;
    }
    
    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public String toString() {
        String str = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(ano);
        return str;
    }

    public static boolean mes31(int mes){
        if(mes == 1|| mes == 3|| mes == 5|| mes == 7|| mes == 8|| mes == 10|| mes == 12){ // Meses com 31 dias
            return true;
        }
        return false;
    }

    public static boolean mes30(int mes){
        if(mes31(mes) || mes != 2){
            return false;
        }

        return true;
    }

    public static boolean dataValida(int dia, int mes, int ano){
        // Verifica se o ano é válido
        if (ano < 1 || ano > 3000){
            return false;
        }

        // Verifica se o mes é valido
        if (mes < 1 || mes > 12){
            return false;
        }
        
        // Verifica se o dia é vadido de acordo com o mes
        if(dia < 1){
            return false;
        }
        if(mes31(mes) && (dia > 31)){
                return false;
        }

        
        else if (mes30(mes) && (dia > 30)){
                return false;
        }

        else if (mes == 2){ // Fevereiro
            if (ano % 4 == 0 && dia > 29){ //Ano bissexto
                return false;
            }

            if (dia > 28){
                return false;
            }
        }

        return true;
    }

    public static boolean dataValida(String dia, String mes, String ano){
        return dataValida(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano));
    }

    public static int diasTotais(int dia, int mes, int ano){
        int soma ;

        // Anos
        int anosBissextos = ano/4;
        soma = ano * 365 + anosBissextos;

        // Meses
        for(int i = 1; i < mes; i++){
            if(mes31(i)){ // Meses com 31 dias
                soma += 31;
            }
    
            else if (mes30(i)){ // Mesess com 30 dias
                soma += 30;
            }
    
            else if (i == 2){ // Fevereiro
                if (ano % 4 == 0){ //Ano bissexto
                    soma += 29;
                }
    
                else{
                    soma += 28;
                }
            }
        }

        // Dias
        soma += dia;
        return soma;
    }

    public static Date toDate(int diasTotais){
        // Não leva em conta a diferença de meses
        int ano = diasTotais/365;
        diasTotais = diasTotais%365 + ano/4; // Aproximado
        int mes = diasTotais/30;
        int dia = diasTotais%30;

        return new Date(ano, mes, dia);
    }

    public Date idade(){
        ZonedDateTime dataAtual = ZonedDateTime.now();

        int anoAtual = dataAtual.getYear();
        int mesAtual = dataAtual.getMonthValue();
        int diaAtual = dataAtual.getDayOfMonth();


        int totalDiasAtuais = diasTotais(diaAtual, mesAtual, anoAtual);
        int totalDiasData = diasTotais(dia, mes, ano);

        int diferenca = totalDiasAtuais - totalDiasData;
        
        return toDate(diferenca);
    }
}
