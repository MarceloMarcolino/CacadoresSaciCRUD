package model;

/**
 * Enum que representa os temperamentos dos Sacis.
 * Cada temperamento fornece bônus específicos em testes de perícia.
 */
public enum Temperamento {
    CALMO("Calmo", "Observação cuidadosa antes de agir. Bônus em Empatia."),
    AGRESSIVO("Agressivo", "Prontos para confronto imediato. Bônus em Encrenca."),
    QUIETO("Quieto", "Agem com discrição e travessuras. Bônus em Fingir."),
    RESISTENTE("Resistente", "Durões que suportam qualquer desafio. Bônus em Tática."),
    IMPLACAVEL("Implacável", "Persistentes e determinados. Bônus em Atenção."),
    AGITADO("Agitado", "Sempre em movimento e aventura. Bônus em Esportes.");

    private final String nome;
    private final String descricao;

    Temperamento(String nome, String descricao) {
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
