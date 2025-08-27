package model;

/**
 * Classe que representa o sistema de Forma Primitiva dos Sacis
 * Baseado na documentação: páginas 75-77
 * 
 * "Nos cantos mais escondidos do mundo, onde a civilização deixa espaço para
 * a natureza bruta e o mistério, estão os cristais primitivos..."
 */
public class FormaPrimitiva {
    private boolean ativa;
    private int turnosRestantes;
    private int pvBonus;
    private boolean imuneVantagensTipo;
    private TipoSaci tipoOriginal;
    
    public FormaPrimitiva() {
        this.ativa = false;
        this.turnosRestantes = 0;
        this.pvBonus = 6; // Conforme documentação: "+6 PV"
        this.imuneVantagensTipo = false;
    }
    
    /**
     * Ativa a Forma Primitiva
     * Conforme documentação: "Ao usar a forma primitiva, seu Saci recebe os seguintes bônus:
     * +6 PV, Poder crescer o seu Saci, Se torna imune a movimentos super efetivos"
     * 
     * @param tipoOriginal O tipo original do Saci
     * @return Os PVs adicionais recebidos
     */
    public int ativar(TipoSaci tipoOriginal) {
        if (this.ativa) {
            return 0; // Já está ativo
        }
        
        this.ativa = true;
        this.tipoOriginal = tipoOriginal;
        this.imuneVantagensTipo = true;
        
        // Documentação: "o Narrador rolará um dado e manterá o resultado em segredo"
        this.turnosRestantes = (int)(Math.random() * 6) + 1; // 1d6
        
        return this.pvBonus;
    }
    
    /**
     * Desativa a Forma Primitiva
     * Conforme documentação: "após ele voltar a sua forma normal depois das rodadas
     * em que estava em sua forma primitiva, ele fica Confuso."
     * 
     * @return true se deve aplicar condição Confuso
     */
    public boolean desativar() {
        if (!this.ativa) {
            return false;
        }
        
        this.ativa = false;
        this.turnosRestantes = 0;
        this.imuneVantagensTipo = false;
        
        // Documentação: "ele fica Confuso" após reverter
        return true;
    }
    
    /**
     * Processa o fim de um turno
     * @return true se a forma primitiva deve ser desativada
     */
    public boolean processarTurno() {
        if (!this.ativa) {
            return false;
        }
        
        this.turnosRestantes--;
        
        if (this.turnosRestantes <= 0) {
            return true; // Deve desativar
        }
        
        return false;
    }
    
    /**
     * Verifica se pode crescer
     * Documentação: "Poder crescer o seu Saci"
     */
    public boolean podeUsar() {
        return this.ativa;
    }
    
    /**
     * Verifica se é imune a vantagens de tipo
     * Documentação: "Se torna imune a movimentos super efetivos"
     */
    public boolean isImuneVantagensTipo() {
        return this.ativa && this.imuneVantagensTipo;
    }
    
    // Getters e Setters
    public boolean isAtiva() {
        return ativa;
    }
    
    public int getTurnosRestantes() {
        return turnosRestantes;
    }
    
    public int getPvBonus() {
        return pvBonus;
    }
    
    public TipoSaci getTipoOriginal() {
        return tipoOriginal;
    }
    
    @Override
    public String toString() {
        if (ativa) {
            return String.format("Forma Primitiva ATIVA (%d turnos restantes, +%d PV, Imune a vantagens)", 
                turnosRestantes, pvBonus);
        } else {
            return "Forma Primitiva INATIVA";
        }
    }
    
    /**
     * Enum dos tipos de cristais primitivos
     * Baseado na documentação: "Pulseira Bate Enrola com o Cristal Primitivo do tipo correspondente"
     */
    public enum TipoCristal {
        VALENTE("Cristal Valente", TipoSaci.VALENTE),
        RIGIDO("Cristal Rígido", TipoSaci.RIGIDO), 
        NEUTRO("Cristal Neutro", TipoSaci.NEUTRO),
        SOMBRIO("Cristal Sombrio", TipoSaci.SOMBRIO),
        CATIVANTE("Cristal Cativante", TipoSaci.CATIVANTE),
        CAOTICO("Cristal Caótico", TipoSaci.CAOTICO);
        
        private final String nome;
        private final TipoSaci tipoCorrespondente;
        
        TipoCristal(String nome, TipoSaci tipoCorrespondente) {
            this.nome = nome;
            this.tipoCorrespondente = tipoCorrespondente;
        }
        
        public String getNome() {
            return nome;
        }
        
        public TipoSaci getTipoCorrespondente() {
            return tipoCorrespondente;
        }
        
        /**
         * Verifica se este cristal pode ser usado por um Saci de determinado tipo
         */
        public boolean podeUsar(TipoSaci tipoSaci) {
            return this.tipoCorrespondente == tipoSaci;
        }
    }
}
