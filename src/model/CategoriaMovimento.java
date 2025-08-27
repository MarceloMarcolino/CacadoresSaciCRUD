package model;

/**
 * Enum que representa as categorias de movimentos dos Sacis
 * Baseado nas três categorias principais do jogo
 */
public enum CategoriaMovimento {
    FISICO("Físico", "Movimentos baseados no ataque físico do Saci"),
    MAGICO("Mágico", "Movimentos baseados no ataque especial do Saci"),
    UTILITARIO("Utilitário", "Movimentos que afetam o estado sem causar dano direto");

    private final String nome;
    private final String descricao;

    CategoriaMovimento(String nome, String descricao) {
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
}
