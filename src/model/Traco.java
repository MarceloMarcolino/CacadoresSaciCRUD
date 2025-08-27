package model;

/**
 * Enum que representa os Traços únicos dos Caçadores
 * Baseado na documentação completa do sistema Caçadores de Saci
 */
public enum Traco {
    // Traços Gerais
    AMBICAO_EXCESSIVA("Ambição Excessiva", 
        "Saci Primário recebe +1 atributo, mas inimigos têm +1 dado em Contra Ataques"),
    
    BOLSO_SORTUDO("Bolso Sortudo", 
        "Sempre ganha quantidade máxima de tampinhas +1 ao capturar Saci"),
    
    CORACAO_DE_MAE("Coração de Mãe", 
        "Uma vez por sessão, pode rolar novamente dado de captura que caiu em 1 ou 2"),
    
    CURIOSIDADE_INSACIAVEL("Curiosidade Insaciável", 
        "Recebe +1 dado em testes de Atenção ao investigar (Requer: Pesquisador)"),
    
    ESCUDEIRO_LEAL("Escudeiro Leal", 
        "Uma vez por combate, pode receber dano no lugar de Saci aliado"),
    
    ESPIRITO_DE_EQUIPE("Espírito de Equipe", 
        "Aliados recebem +1 dado em Movimentos Combinados"),
    
    IDOLO_LOCAL("Ídolo Local", 
        "Pode pedir ao Narrador para rolar novamente teste falhado (requer fã-clube)"),
    
    IMA_DE_DESASTRE("Imã de Desastre", 
        "Ganha +1 Traquinagem extra ao aceitar falhas propostas pelo Narrador"),
    
    INVISIVEL_NA_MULTIDAO("Invisível na Multidão", 
        "Recebe +1 dado em testes de se esconder ou infiltrar"),
    
    LELE_DA_CUCA("Lelé da Cuca", 
        "Fora de combate, pode rolar novamente quando sair 1 nos dados"),
    
    LOBO_SOLITARIO("Lobo Solitário", 
        "Recebe +1 dado em todos os testes quando separado do grupo"),
    
    MAO_NA_MASSA("Mão na Massa", 
        "Recebe +1 dado em testes de iniciativa"),
    
    MAU_PERDEDOR("Mau Perdedor", 
        "Recebe 4 dados bônus por derrota para usar em combates futuros"),
    
    MESTRE_DO_OFICIO("Mestre do Ofício", 
        "Recebe +1 dado em perícias relacionadas ao trabalho do caçador"),
    
    NARRADOR_NATO("Narrador Nato", 
        "Aliados recebem +1 dado em Ações de Caçador"),
    
    REI_DA_DIPLOMACIA("Rei da Diplomacia", 
        "Recebe +1 dado em testes de persuasão e negociação"),
    
    REI_DA_GAMBIARRA("Rei da Gambiarra", 
        "Uma vez por sessão, pode 'encontrar' item necessário"),
    
    SORRISO_INQUEBRANTAVEL("Sorriso Inquebrável", 
        "Uma vez por sessão, permite aliado rolar novamente teste falhado"),
    
    // Traços Específicos por Classe
    BOM_DE_GARFO("Bom de Garfo", 
        "Pode procurar ingredientes e ganhar +1 dado em cura (Requer: Cozinheiro)"),
    
    CACADOR_DE_RARIDADES("Caçador de Raridades", 
        "Sacis fugitivos são sempre raros (Requer: Colecionador)"),
    
    LUTADOR_COPIAO("Lutador Copião", 
        "Pode aprender movimentos Neutros/Rígidos de oponentes (Requer: Lutador)"),
    
    TRANQUILO_POR_NATUREZA("Tranquilo por Natureza", 
        "Sacis fugitivos não fogem enquanto estiver presente (Requer: Domador)"),
    
    VISAO_ESPIRITUAL("Visão Espiritual", 
        "Uma vez por sessão, pode pedir 'conselhos espirituais' (Requer: Místico)");
    
    private final String nome;
    private final String descricao;
    
    Traco(String nome, String descricao) {
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
    
    /**
     * Verifica se é um traço específico de classe
     */
    public boolean isEspecificoDeClasse() {
        return this == BOM_DE_GARFO || this == CACADOR_DE_RARIDADES || 
               this == LUTADOR_COPIAO || this == TRANQUILO_POR_NATUREZA || 
               this == VISAO_ESPIRITUAL || this == CURIOSIDADE_INSACIAVEL;
    }
    
    /**
     * Verifica compatibilidade com classe
     */
    public boolean isCompativelCom(ClasseCacador classe) {
        switch (this) {
            case BOM_DE_GARFO:
                return classe == ClasseCacador.COZINHEIRO;
            case CACADOR_DE_RARIDADES:
                return classe == ClasseCacador.COLECIONADOR;
            case LUTADOR_COPIAO:
                return classe == ClasseCacador.LUTADOR; // Lutador classe
            case TRANQUILO_POR_NATUREZA:
                return classe == ClasseCacador.DOMADOR; // Domador classe
            case VISAO_ESPIRITUAL:
                return classe == ClasseCacador.MISTICO;
            case CURIOSIDADE_INSACIAVEL:
                return classe == ClasseCacador.PESQUISADOR;
            default:
                return true; // Traços gerais são compatíveis com todas as classes
        }
    }
}
