/*
 * Define as constantes dos submenus
 */
public enum SubmenuOpcoes {
	CADASTRAR_SEGURADORA("Cadastrar Seguradoras", new SubSubmenuOpcoes[]{}),
	LISTAR_SEGURADORAS("Listar Seguradoras", new SubSubmenuOpcoes[]{}),
	CADASTAR_SEGURO("Cadastar Seguro", new SubSubmenuOpcoes[]{}),
	CANCELAR_SEGURO("Cancelar Seguro", new SubSubmenuOpcoes[]{}),
	LISTAR_SEGUROS_CLIENTE("Listar Seguros por Cliente", new SubSubmenuOpcoes[]{}),
	CADASTAR_CLIENTE("Cadastar Cliente", new SubSubmenuOpcoes[]{}),
	REMOVER_CLIENTE("Remover Cliente", new SubSubmenuOpcoes[]{}),
	LISTAR_CLIENTES("Listar Clientes", new SubSubmenuOpcoes[]{}),
	SINISTROS_POR_CLIENTE("Listar Sinistros por Cliente", new SubSubmenuOpcoes[]{}),
	CONFIGURACOES_CLIENTES_PF("Configurações Clientes PF", new SubSubmenuOpcoes[]{
		SubSubmenuOpcoes.CADASTRA_VEICULO,
		SubSubmenuOpcoes.REMOVER_VEICULO,
		SubSubmenuOpcoes.VOLTAR
	}),
	CONFIGURACOES_CLIENTES_PJ("Configuracoes Clientes PJ", new SubSubmenuOpcoes[]{
		SubSubmenuOpcoes.CADASTRA_FROTA,
		SubSubmenuOpcoes.REMOVER_VEICULO_FROTA,
		SubSubmenuOpcoes.REMOVER_VEICULO_FROTA,
		SubSubmenuOpcoes.VOLTAR
	}),
	VOLTAR("Voltar", new SubSubmenuOpcoes[]{});
	
	//atributo
	private final String descricao;
	private final SubSubmenuOpcoes[] subsubmenu;
	
	//Construtor
	SubmenuOpcoes(String descricao, SubSubmenuOpcoes[] subsubmenu){
		this.descricao = descricao;
		this.subsubmenu = subsubmenu;
	}
	
	//getter
	public String getDescricao() {
		return descricao;
	}

	public SubSubmenuOpcoes[] getSubSubmenu() {
		return subsubmenu;
	}
}
