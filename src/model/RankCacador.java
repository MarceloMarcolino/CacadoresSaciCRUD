package model;

/**
 * Enum que representa os ranks (níveis de progressão) dos Caçadores.
 * Baseado na tabela de progressão da documentação.
 */
public enum RankCacador {
    INICIAL(0, "Inicial", 4, "+2 Atributos de Caçador + 5 Perícias"),
    UMA_ESTRELA(1, "☆", 4, "+1 Traço + 1 Perícias"),
    DUAS_ESTRELAS(2, "☆☆", 8, "+1 Dado em Testes de Respostas + 1 Atributo de Caçador"),
    TRES_ESTRELAS(3, "☆☆☆", 12, "+1 Traço + 1 Perícias"),
    QUATRO_ESTRELAS(4, "☆☆☆☆", 12, "+1 Dado em Testes de Respostas + 1 Atributo de Caçador"),
    MESTRE(5, "MESTRE", -1, "+1 Dado em Testes de Respostas + 1 Traço + 1 Perícias");

    private final int nivel;
    private final String simbolo;
    private final int ppNecessarios; // Pontos de Progressão necessários
    private final String beneficios;

    RankCacador(int nivel, String simbolo, int ppNecessarios, String beneficios) {
        this.nivel = nivel;
        this.simbolo = simbolo;
        this.ppNecessarios = ppNecessarios;
        this.beneficios = beneficios;
    }

    public int getNivel() {
        return nivel;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public int getPpNecessarios() {
        return ppNecessarios;
    }

    public String getBeneficios() {
        return beneficios;
    }

    /**
     * Retorna o próximo rank na progressão
     */
    public RankCacador getProximoRank() {
        switch (this) {
            case INICIAL: return UMA_ESTRELA;
            case UMA_ESTRELA: return DUAS_ESTRELAS;
            case DUAS_ESTRELAS: return TRES_ESTRELAS;
            case TRES_ESTRELAS: return QUATRO_ESTRELAS;
            case QUATRO_ESTRELAS: return MESTRE;
            case MESTRE: return null; // Não progride mais
            default: return null;
        }
    }

    @Override
    public String toString() {
        return simbolo + " - " + beneficios;
    }
}
