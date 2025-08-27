package model;

/**
 * Enum que representa os tipos de Crescimento disponíveis para Sacis em combate
 * Baseado na documentação do sistema Caçadores de Saci - página 78
 */
public enum TipoCrescimento {
    CRESCIMENTO_PODEROSO("Crescimento Poderoso", 1, "+2 dados no próximo teste de ataque"),
    CRESCIMENTO_RELAMPAGO("Crescimento Relâmpago", 2, "+1 dado em testes de respostas"),
    CRESCIMENTO_FEROZ("Crescimento Feroz", 2, "+1 em um atributo à escolha"),
    CRESCIMENTO_INSTINTIVO("Crescimento Instintivo", 3, "Force oponente a rolar novamente teste de ataque"),
    CRESCIMENTO_AMEACADOR("Crescimento Ameaçador", 3, "Inimigos sofrem -1 dado por 2 rodadas"),
    CRESCIMENTO_EXPLOSIVO("Crescimento Explosivo", 4, "Receba um ataque adicional neste turno");
    
    private final String nome;
    private final int custoPV;
    private final String efeito;
    
    TipoCrescimento(String nome, int custoPV, String efeito) {
        this.nome = nome;
        this.custoPV = custoPV;
        this.efeito = efeito;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getCustoPV() {
        return custoPV;
    }
    
    public String getEfeito() {
        return efeito;
    }
    
    @Override
    public String toString() {
        return String.format("%s (Custo: %d PV) - %s", nome, custoPV, efeito);
    }
    
    /**
     * Verifica se o Saci tem PV suficiente para usar este crescimento
     */
    public boolean podeUsar(int pvAtual) {
        return pvAtual > custoPV; // Deve ter mais PV que o custo para não morrer
    }
    
    /**
     * Aplica o custo de PV do crescimento
     */
    public int aplicarCusto(int pvAtual) {
        return Math.max(1, pvAtual - custoPV); // Nunca deixa com 0 PV
    }
}
