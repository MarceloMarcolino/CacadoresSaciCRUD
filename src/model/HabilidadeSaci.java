package model;

/**
 * Enum que representa as Habilidades especiais dos Sacis
 * Baseado na documentação completa do sistema Caçadores de Saci
 */
public enum HabilidadeSaci {
    // Habilidades Gerais
    FOTOSSINTESE("Fotossíntese", "Enquanto o clima estiver Ensolarado, a agilidade aumenta em 1"),
    PIPOQUEIO("Pipoqueio", "Se um ataque for derrubar este Saci, o atacante recebe o dano restante"),
    FLUTUAR("Flutuar", "Recebe -1 dano físico, mas +1 dano de movimentos Caóticos"),
    INTIMIDACAO("Intimidação", "Quando entra em batalha, diminui Potência de todos os inimigos em 1"),
    NINGUEM_ME_SEGURA("Ninguém me Segura", "Este Saci não Cochila e nem pode ser Congelado"),
    DEFESA_RELUZENTE("Defesa Reluzente", "Se estiver com vida cheia, reduz 1 de dano recebido"),
    RESISTENCIA_ANORMAL("Resistência Anormal", "O Saci nega qualquer dano de condições de clima"),
    QUEBRARUGA("Quebraruga", "Ao sofrer +3 dano, perde parte do corpo e oponente fica Confuso"),
    BUSQUEM_CONHECIMENTO("Busquem Conhecimento", "Caçador recebe 1 sucesso automático em Investigações"),
    
    // Habilidades de Suporte
    ESCUDO_AMIGO("Escudo Amigo", "Pode rolar dado para tomar dano no lugar de Saci aliado"),
    UIVO_DE_GUERRA("Uivo de Guerra", "Saci Primário se torna imune a Confuso"),
    ATENÇÃO_EM_MIM("Atenção em Mim", "Se afetado por condição negativa, ganha +1 Foco"),
    EXPLOSAO_CONTIDA("Explosão Contida", "Ao usar 'Detonação Final', pode ficar com 1 PV ao invés de ser derrubado"),
    REFRESCAR("Refrescar", "Uma vez por combate, pode curar 1 PV de aliado com espiga"),
    
    // Habilidades Ofensivas  
    SUPORTE_OFENSIVO("Suporte Ofensivo", "Movimentos que não causam dano agora causam 2 dados de dano"),
    SURPRESA_AFIADA("Surpresa Afiada", "Adiciona 1 dado a movimentos que tenham 'Corte' no nome"),
    FORÇA_IMPARAVEL("Força Imparável", "Sua Potência não pode ser diminuída"),
    ABSORÇÃO_NEGATIVA("Absorção Negativa", "Quando atributo do oponente diminui, ganha +1 no mesmo atributo"),
    
    // Habilidades Específicas por Tipo
    FOGO_DA_BRAVURA("Fogo da Bravura", "Movimentos físicos Valentes causam Queimado 1 (Valente)"),
    CABECA_DURA("Cabeça Dura", "Uma vez por combate, nega dano fatal ficando com 1 PV (Rígido)"),
    TORCIDA_ORGANIZADA("Torcida Organizada", "Efeitos de aumento de atributo recebem +1 no turno (Cativante)"),
    INSTINTO_SOMBRIO("Instinto Sombrio", "Movimentos Sombrios ganham +1 dado contra alvos feridos (Sombrio)"),
    CAOS_CRESCENTE("Caos Crescente", "A cada turno, ganha +1 em atributo aleatório (Caótico)"),
    SINTONIA_MISTICA("Sintonia Mística", "Pode usar energia de aliados para amplificar movimentos (Neutro)");
    
    private final String nome;
    private final String descricao;
    
    HabilidadeSaci(String nome, String descricao) {
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
     * Verifica se é uma habilidade específica de tipo
     */
    public boolean isEspecificaDeTipo() {
        return this == FOGO_DA_BRAVURA || this == CABECA_DURA || 
               this == TORCIDA_ORGANIZADA || this == INSTINTO_SOMBRIO || 
               this == CAOS_CRESCENTE || this == SINTONIA_MISTICA;
    }
    
    /**
     * Verifica se é uma habilidade de suporte
     */
    public boolean isSuporte() {
        return this == ESCUDO_AMIGO || this == UIVO_DE_GUERRA || 
               this == ATENÇÃO_EM_MIM || this == EXPLOSAO_CONTIDA || 
               this == REFRESCAR || this == BUSQUEM_CONHECIMENTO;
    }
    
    /**
     * Verifica se é uma habilidade passiva
     */
    public boolean isPassiva() {
        return this == FOTOSSINTESE || this == FLUTUAR || 
               this == RESISTENCIA_ANORMAL || this == DEFESA_RELUZENTE;
    }
    
    /**
     * Obter tipo compatível (se aplicável)
     */
    public TipoSaci getTipoCompativel() {
        switch (this) {
            case FOGO_DA_BRAVURA:
                return TipoSaci.VALENTE; // Tipo Valente
            case CABECA_DURA:
                return TipoSaci.RIGIDO; // Tipo Rígido  
            case TORCIDA_ORGANIZADA:
                return TipoSaci.CATIVANTE; // Tipo Cativante
            case INSTINTO_SOMBRIO:
                return TipoSaci.SOMBRIO; // Tipo Sombrio
            case CAOS_CRESCENTE:
                return TipoSaci.CAOTICO;
            case SINTONIA_MISTICA:
                return TipoSaci.NEUTRO; // Tipo Neutro
            default:
                return null; // Habilidades gerais não têm tipo específico
        }
    }
}
