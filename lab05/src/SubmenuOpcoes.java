/*
 * Define as constantes dos submenus
 */
public enum SubmenuOpcoes {
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES_PF("Listar cliente PF"),
	LISTAR_CLIENTES_PJ("Listar cliente PJ"),
	LISTAR_SINISTROS_SEGURADORA("Listar sinistros por Seguradora"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros por Cliente"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculo por Cliente"),
	LISTAR_VEICULOS_SEGURADORA("Listar veiculo por Seguradora"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
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
