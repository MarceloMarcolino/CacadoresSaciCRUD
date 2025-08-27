package model;

/**
 * Enum que representa os diferentes tipos de interação no mundo do jogo
 * Baseado na documentação do sistema Caçadores de Saci
 */
public enum TipoInteracao {
    ENCONTRAR_SACI("Encontrar Saci", "Buscar e encontrar Sacis selvagens para capturar"),
    CONVERSAR_NPC("Conversar com NPC", "Dialogar com NPCs e obter informações"),
    USAR_MERCORUJA("Usar Mercoruja", "Interagir com máquinas Mercoruja usando tampinhas"),
    TREINAR_SACI("Treinar Saci", "Treinar seus Sacis para aprender novos movimentos"),
    CURAR_SACI("Curar Saci", "Usar curativos para restaurar PV dos Sacis"),
    CAPTURAR_SACI("Capturar Saci", "Usar garrafa para capturar um Saci selvagem"),
    COMBATE_SACI("Combate de Sacis", "Batalhar com outros caçadores ou Sacis selvagens"),
    EXPLORAR_AREA("Explorar Área", "Investigar e explorar novas áreas do mundo"),
    COLETAR_ITEM("Coletar Item", "Encontrar e coletar itens pelo mundo"),
    DESCANSAR("Descansar", "Recuperar energia e curar Sacis em locais seguros");
    
    private final String nome;
    private final String descricao;
    
    TipoInteracao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    /**
     * Obter descrição completa da interação
     */
    public String obterDescricaoCompleta() {
        return nome + ": " + descricao;
    }
}
