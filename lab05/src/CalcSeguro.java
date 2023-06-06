public enum CalcSeguro {
    VALOR_BASE(10.0),
    FATOR_18_30(1.25),
    FATOR_30_60(1.0),
    FATOR_60_90(1.5);

    private final double constante;

    private CalcSeguro(Double constante){
        this.constante = constante;
    }

    public double getConstante(){
        return constante;
    }
}
