/*
 * Define as constantes dos submenus
 */
public enum SubmenuOpcoes {
	CADASTRAR_SEGURADORA("Cadastrar Seguradoras"),
	LISTAR_SEGURADORAS("Listar Seguradoras"),
	CADASTAR_SEGURO("Cadastar Seguro"),
	REMOVER_SEGURO("Remover Seguro"),
	LISTAR_SEGUROS_CLIENTE("Listar Seguros por Cliente"),
	CADASTAR_CLIENTE("Cadastar Cliente"),
	REMOVER_CLIENTE("Remover Cliente"),
	LISTAR_CLIENTES("Listar Clientes"),
	SINISTROS_POR_CLIENTE("Listar Sinistros por Cliente"),
	VOLTAR("Voltar");
	
	//atributo
	private final String descricao;
	
	//Construtor
	SubmenuOpcoes(String descricao){
		this.descricao = descricao;
	}
	
	//getter
	public String getDescricao() {
		return descricao;
	}
}
