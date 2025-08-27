package model;

/**
 * Sistema de Traquinagem dos Sacis
 * Baseado nas referências na documentação: sistema de recursos especiais
 * que permite aos Sacis gastos pontos para efeitos especiais
 */
public class SistemaTraquinagem {
    private int pontosTraquinagem;
    private int pontosMaximos;

    public SistemaTraquinagem() {
        this.pontosTraquinagem = 3; // Valor padrão inicial
        this.pontosMaximos = 3;
    }

    public SistemaTraquinagem(int pontos) {
        this.pontosTraquinagem = pontos;
        this.pontosMaximos = pontos;
    }

    /**
     * Gasta pontos de traquinagem
     */
    public boolean gastarTraquinagem(int quantidade) {
        if (pontosTraquinagem >= quantidade) {
            pontosTraquinagem -= quantidade;
            return true;
        }
        return false;
    }

    /**
     * Recupera pontos de traquinagem
     */
    public void recuperarTraquinagem(int quantidade) {
        this.pontosTraquinagem = Math.min(pontosMaximos, pontosTraquinagem + quantidade);
    }

    /**
     * Reset completo dos pontos
     */
    public void resetarTraquinagem() {
        this.pontosTraquinagem = pontosMaximos;
    }

    /**
     * Aumenta o máximo de pontos de traquinagem
     */
    public void aumentarMaximo(int quantidade) {
        this.pontosMaximos += quantidade;
        this.pontosTraquinagem += quantidade; // Também aumenta os atuais
    }

    /**
     * Verifica se tem pontos suficientes
     */
    public boolean temPontosSuficientes(int quantidade) {
        return pontosTraquinagem >= quantidade;
    }

    /**
     * Enum dos tipos de uso de traquinagem baseados na documentação
     */
    public enum UsoTraquinagem {
        AUMENTAR_AGILIDADE("Receber +2 pontos em Agilidade até o fim da rodada", 1),
        CURA("Se curar durante combate", 1),
        RECUPERAR_PV("Recuperar pontos de vida", 1),
        EFEITO_ESPECIAL("Ativar efeito especial de movimento", 1),
        REMOVER_CONDICAO("Retirar condição negativa", 1);

        private final String descricao;
        private final int custo;

        UsoTraquinagem(String descricao, int custo) {
            this.descricao = descricao;
            this.custo = custo;
        }

        public String getDescricao() {
            return descricao;
        }

        public int getCusto() {
            return custo;
        }
    }

    /**
     * Usa traquinagem para um efeito específico
     */
    public boolean usarTraquinagem(UsoTraquinagem uso) {
        return gastarTraquinagem(uso.getCusto());
    }

    // Getters e Setters
    public int getPontosTraquinagem() {
        return pontosTraquinagem;
    }

    public void setPontosTraquinagem(int pontosTraquinagem) {
        this.pontosTraquinagem = Math.max(0, Math.min(pontosMaximos, pontosTraquinagem));
    }

    public int getPontosMaximos() {
        return pontosMaximos;
    }

    public void setPontosMaximos(int pontosMaximos) {
        this.pontosMaximos = Math.max(1, pontosMaximos);
        // Ajusta os pontos atuais se excederem o novo máximo
        this.pontosTraquinagem = Math.min(this.pontosTraquinagem, this.pontosMaximos);
    }

    @Override
    public String toString() {
        return String.format("Traquinagem: %d/%d", pontosTraquinagem, pontosMaximos);
    }
}
