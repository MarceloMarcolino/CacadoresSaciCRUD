package model;

/**
 * Enum que representa os diferentes tipos de movimento de combate no jogo
 * Baseado na documentação do sistema Caçadores de Saci
 */
public enum TipoMovimento {
    FISICO("Físico", "Movimentos baseados em força e agilidade"),
    MAGICO("Mágico", "Movimentos baseados em magia e foco"),
    SOMBRIO("Sombrio", "Movimentos das trevas e mistério"),
    RÍGIDO("Rígido", "Movimentos de resistência e defesa"),
    CAOTICO("Caótico", "Movimentos imprevisíveis e destruitivos"),
    SUPORTE("Suporte", "Movimentos de apoio e cura"),
    AREA("Área", "Movimentos que afetam múltiplos alvos"),
    ARTIMANHA("Artimanha", "Movimentos especiais sem dano direto"),
    CORTE("Corte", "Movimentos de corte e precisão"),
    PRIORIDADE("Prioridade", "Movimentos que atacam primeiro");
    
    private final String nome;
    private final String descricao;
    
    TipoMovimento(String nome, String descricao) {
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
     * Verifica se é um movimento ofensivo
     */
    public boolean isOfensivo() {
        return this != SUPORTE && this != ARTIMANHA;
    }
    
    /**
     * Verifica se é um movimento de suporte
     */
    public boolean isSuporte() {
        return this == SUPORTE || this == ARTIMANHA;
    }
    
    /**
     * Verifica se afeta múltiplos alvos
     */
    public boolean isArea() {
        return this == AREA;
    }
    
    /**
     * Verifica se tem prioridade de ataque
     */
    public boolean temPrioridade() {
        return this == PRIORIDADE;
    }
}
