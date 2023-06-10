public enum SubSubmenuOpcoes {
    CADASTRA_VEICULO("Cadastar Veiculo"),    
    REMOVER_VEICULO("Remover Veiculo"),
	LISTAR_VEICULOS("Listar veiculos"),
    CADASTRA_FROTA("Cadastrar Frota"),
    ADICIONAR_VEICULO_FROTA("Adicionar veiculo a frota"),
    REMOVER_VEICULO_FROTA("Remover veiculo da frota"),
    SUBSTITUIR_FROTA("Substituir frota"),
	LISTAR_FROTAS("Listar frotas"),
	VOLTAR("Voltar");

	//atributo
	private final String descricao;
	
	//Construtor
	SubSubmenuOpcoes(String descricao){
		this.descricao = descricao;
	}
	
	//getter
	public String getDescricao() {
		return descricao;
	}
}
