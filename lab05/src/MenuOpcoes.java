/* enum para menu externo
Cada constante é vista como uma descrição e uma lista de outras constantes (que são as constantes do submenu)

new SubmenuOpcoes[]{} cria uma lista de constantes do submenu.
*/
public enum MenuOpcoes {
	SEGURADORAS("Seguradoras", new SubmenuOpcoes[] {
		SubmenuOpcoes.CADASTAR_SEGURO,
		SubmenuOpcoes.CANCELAR_SEGURO,
		SubmenuOpcoes.LISTAR_SEGUROS_CLIENTE,
		SubmenuOpcoes.CADASTAR_CLIENTE,
		SubmenuOpcoes.REMOVER_CLIENTE,
		SubmenuOpcoes.LISTAR_CLIENTES,
		SubmenuOpcoes.SINISTROS_POR_CLIENTE,
		SubmenuOpcoes.VOLTAR
	}),	
	CONFIGURACOES("Configuracoes", new SubmenuOpcoes[] {
		SubmenuOpcoes.CADASTRAR_SEGURADORA,		
		SubmenuOpcoes.LISTAR_SEGURADORAS,
		SubmenuOpcoes.VOLTAR
	}),
	SAIR("Sair", new SubmenuOpcoes[] {});
	
	//atributos
	private final String descricao;
	private final SubmenuOpcoes[] submenu;
	
	//Construtor
	MenuOpcoes(String descricao, SubmenuOpcoes[] submenu){
		this.descricao = descricao;
		this.submenu = submenu;
	}

	
	//getters
	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOpcoes[] getSubmenu() {
		return submenu;
	}
}
