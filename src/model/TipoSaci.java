package model;

/**
 * Enum que representa os tipos de Saci no jogo Caçadores de Saci.
 * Cada tipo tem características únicas que influenciam o combate.
 */
public enum TipoSaci {
    VALENTE("Valente", "Encaram desafios sem recuar! Ataques poderosos como chamas ardentes."),
    RIGIDO("Rígido", "Verdadeiras rochas. Estrategistas que preveem cada movimento do adversário."),
    NEUTRO("Neutro", "Camaleões do combate! Se adaptam a qualquer situação ou desafio."),
    SOMBRIO("Sombrio", "Mestres em ilusões e ataques assustadores. Dominam a escuridão."),
    CAOTICO("Caótico", "Cheios de energia e imprevisíveis! Movimentos inventados na hora."),
    CATIVANTE("Cativante", "Gurus zen! Especialistas em cura e proteção de aliados.");

    private final String nome;
    private final String descricao;

    TipoSaci(String nome, String descricao) {
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
