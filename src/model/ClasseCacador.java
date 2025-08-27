package model;

/**
 * Enum que representa as Classes de Caçador no jogo Caçadores de Saci
 * Cada classe possui características únicas e estilos de jogo específicos
 */
public enum ClasseCacador {
    DOMADOR("Domador", "Aventureiros destemidos com laços especiais com os Sacis", 
            "Empatia e Mandar", "Rede do Caçador", "Treinador Nato"),
    
    PESQUISADOR("Pesquisador", "Sabichões apaixonados pelo estudo dos Sacis", 
                "Atenção e Tática", "Caderno de Anotações", "Tenho uma ideia"),
    
    TORCEDOR("Torcedor", "Caçadores apaixonados por times e ídolos", 
             "Encrenca e Fingir", "Camisa de Time", "Cai dentro"),
    
    COZINHEIRO("Cozinheiro", "Mestres da culinária que nutrem e fortalecem os Sacis", 
               "Arte e Empatia", "Marmita", "Ingrediente Secreto"),
    
    COLECIONADOR("Colecionador", "Entusiastas que buscam Sacis raros pelo mundo", 
                 "Atenção e Fingir", "Mochila", "Sortudo"),
    
    MISTICO("Místico", "Exploradores dos mistérios e energias espirituais", 
            "Empatia e Mandar", "Amuleto Místico", "Cristal Elemental"),
    
    LUTADOR("Lutador", "Mestres das artes marciais que lutam junto aos Sacis", 
            "Esportes e Encrenca", "Pesos de Treinamento", "Lutador Sobrenatural");

    private final String nome;
    private final String descricao;
    private final String periciasTreinadas;
    private final String itemEspecial;
    private final String habilidade;

    ClasseCacador(String nome, String descricao, String periciasTreinadas, 
                  String itemEspecial, String habilidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.periciasTreinadas = periciasTreinadas;
        this.itemEspecial = itemEspecial;
        this.habilidade = habilidade;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPericiasTreinadas() {
        return periciasTreinadas;
    }

    public String getItemEspecial() {
        return itemEspecial;
    }

    public String getHabilidade() {
        return habilidade;
    }

    @Override
    public String toString() {
        return nome;
    }
}
