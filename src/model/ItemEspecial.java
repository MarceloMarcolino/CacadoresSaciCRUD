package model;

/**
 * Enum que representa os itens especiais dos caçadores
 * Cada classe tem seu próprio item especial conforme a documentação
 */
public enum ItemEspecial {
    REDE_DO_CACADOR("Rede do Caçador", "Uma vez por sessão, o alvo perde 1 ponto em Agilidade", ClasseCacador.DOMADOR),
    CADERNO_ANOTACOES("Caderno de Anotações", "Uma vez por sessão, o alvo recebe menos um ponto em Foco", ClasseCacador.PESQUISADOR),
    CAMISA_DE_TIME("Camisa de Time", "Uma vez por sessão, nega por completo um dano que seu Saci receberia", ClasseCacador.TORCEDOR),
    MARMITA("Marmita", "Uma vez por sessão, um Saci recebe +1 Resistência e Foco até o final do combate", ClasseCacador.COZINHEIRO),
    MOCHILA("Mochila", "Uma vez por sessão, role 3 dados para achar Garrafas, Curativos ou Tampinhas", ClasseCacador.COLECIONADOR),
    AMULETO_MISTICO("Amuleto Místico", "Uma vez por sessão, estende qualquer efeito por +1 Rodada", ClasseCacador.MISTICO),
    PESOS_TREINAMENTO("Pesos de Treinamento", "Uma vez por sessão, recebe +1 Potência e Agilidade até o final do combate", ClasseCacador.LUTADOR);

    private final String nome;
    private final String descricao;
    private final ClasseCacador classe;

    ItemEspecial(String nome, String descricao, ClasseCacador classe) {
        this.nome = nome;
        this.descricao = descricao;
        this.classe = classe;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public ClasseCacador getClasse() {
        return classe;
    }

    /**
     * Obtém o item especial para uma determinada classe
     */
    public static ItemEspecial obterPorClasse(ClasseCacador classe) {
        for (ItemEspecial item : values()) {
            if (item.classe == classe) {
                return item;
            }
        }
        return REDE_DO_CACADOR; // fallback
    }

    @Override
    public String toString() {
        return nome + " - " + descricao;
    }
}
