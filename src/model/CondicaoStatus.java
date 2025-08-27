package model;

/**
 * Enum que representa as condições de status que podem afetar Sacis em combate
 * Baseado na documentação do jogo
 */
public enum CondicaoStatus {
    QUEIMADO("Queimado", "Recebe 1 de dano por turno e perde 1 ponto em Agilidade"),
    CONFUSO("Confuso", "Movimentos podem falhar ou atingir alvos aleatórios"),
    PARALISADO("Paralisado", "Não pode atacar por algumas rodadas"),
    ENVENENADO("Envenenado", "Recebe dano contínuo baseado na potência do veneno"),
    CEGO("Cego", "Reduz drasticamente a precisão dos ataques"),
    ADORMECIDO("Adormecido", "Não pode agir até ser acordado ou receber dano");

    private final String nome;
    private final String descricao;

    CondicaoStatus(String nome, String descricao) {
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
        return nome + " - " + descricao;
    }
}
