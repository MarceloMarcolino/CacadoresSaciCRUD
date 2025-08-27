package model;

/**
 * Enum que representa as condições climáticas no mundo do jogo
 * Baseado na documentação do sistema Caçadores de Saci
 */
public enum CondicaoClimatica {
    ENSOLARADO("Ensolarado", "Clima ensolarado que beneficia alguns movimentos", "☀️"),
    TEMPESTUOSO("Tempestuoso", "Tempestade que acelera alguns movimentos", "⛈️"),
    CHUVOSO("Chuvoso", "Chuva que afeta o campo de batalha", "🌧️"),
    NUBLADO("Nublado", "Clima nebuloso que favorece tipos sombrios", "☁️"),
    NORMAL("Normal", "Clima neutro sem efeitos especiais", "🌤️");
    
    private final String nome;
    private final String descricao;
    private final String simbolo;
    
    CondicaoClimatica(String nome, String descricao, String simbolo) {
        this.nome = nome;
        this.descricao = descricao;
        this.simbolo = simbolo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public String getSimbolo() {
        return simbolo;
    }
    
    @Override
    public String toString() {
        return simbolo + " " + nome;
    }
    
    /**
     * Verifica se é um clima especial que afeta batalhas
     */
    public boolean isClimaEspecial() {
        return this != NORMAL;
    }
    
    /**
     * Obter efeitos do clima na batalha
     */
    public String obterEfeitos() {
        switch (this) {
            case ENSOLARADO:
                return "Movimentos de fogo ganham +1 dado. Movimentos como 'Rajada Solardente' causam dano extra.";
            case TEMPESTUOSO:
                return "Movimentos que normalmente precisam carregar podem ser usados instantaneamente.";
            case CHUVOSO:
                return "Movimentos de água ganham +1 dado. Movimentos de fogo causam -1 de dano.";
            default:
                return "Nenhum efeito especial.";
        }
    }
}
