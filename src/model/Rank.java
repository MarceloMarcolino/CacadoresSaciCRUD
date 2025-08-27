package model;

/**
 * Enum que representa os ranks (níveis de dificuldade) dos Sacis.
 * Cada rank determina a quantidade de pontos de atributo e PV base.
 */
public enum Rank {
    UMA_ESTRELA(1, "☆", 2, 6, "Sacis iniciantes"),
    DUAS_ESTRELAS(2, "☆☆", 3, 10, "Sacis intermediários"),
    TRES_ESTRELAS(3, "☆☆☆", 4, 14, "Sacis avançados"),
    QUATRO_ESTRELAS(4, "☆☆☆☆", 5, 18, "Sacis lendários");

    private final int nivel;
    private final String simbolo;
    private final int pontosAtributo;
    private final int pvBase;
    private final String descricao;

    Rank(int nivel, String simbolo, int pontosAtributo, int pvBase, String descricao) {
        this.nivel = nivel;
        this.simbolo = simbolo;
        this.pontosAtributo = pontosAtributo;
        this.pvBase = pvBase;
        this.descricao = descricao;
    }

    public int getNivel() {
        return nivel;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public int getPontosAtributo() {
        return pontosAtributo;
    }

    public int getPvBase() {
        return pvBase;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return simbolo + " - " + descricao;
    }
}
