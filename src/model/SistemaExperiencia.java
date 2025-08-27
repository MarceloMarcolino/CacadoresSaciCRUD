package model;

/**
 * Classe que gerencia a experiência e evolução dos Sacis
 * Baseado no sistema de crescimento descrito na documentação
 */
public class SistemaExperiencia {
    private int experiencia;
    private int nivel;
    private int proxNivel;
    
    public SistemaExperiencia() {
        this.experiencia = 0;
        this.nivel = 1;
        this.proxNivel = calcularExperienciaNivel(2);
    }
    
    public SistemaExperiencia(int nivel) {
        this.nivel = nivel;
        this.experiencia = calcularExperienciaNivel(nivel);
        this.proxNivel = calcularExperienciaNivel(nivel + 1);
    }
    
    /**
     * Adiciona experiência ao Saci
     * @param pontos Pontos de experiência ganhos
     * @return true se subiu de nível
     */
    public boolean ganharExperiencia(int pontos) {
        this.experiencia += pontos;
        
        boolean subiuNivel = false;
        while (experiencia >= proxNivel && nivel < 50) { // Nível máximo 50
            nivel++;
            proxNivel = calcularExperienciaNivel(nivel + 1);
            subiuNivel = true;
        }
        
        return subiuNivel;
    }
    
    /**
     * Calcula experiência necessária para um nível
     */
    private int calcularExperienciaNivel(int nivel) {
        // Fórmula exponencial para crescimento de experiência
        return (int) Math.pow(nivel - 1, 2.5) * 100;
    }
    
    /**
     * Calcula o bônus de atributo baseado no nível
     */
    public int calcularBonusAtributo() {
        return (nivel - 1) / 5; // +1 bônus a cada 5 níveis
    }
    
    /**
     * Verifica se pode aprender novo movimento
     */
    public boolean podeAprenderMovimento() {
        return nivel % 3 == 0; // Novo movimento a cada 3 níveis
    }
    
    /**
     * Calcula pontos de vida máximos baseados no nível
     */
    public int calcularPVMaximo(int pvBase) {
        return pvBase + (nivel - 1) * 2; // +2 PV por nível
    }
    
    /**
     * Calcula experiência necessária para próximo nível
     */
    public int experienciaParaProximoNivel() {
        return Math.max(0, proxNivel - experiencia);
    }
    
    /**
     * Verifica se está no nível máximo
     */
    public boolean isNivelMaximo() {
        return nivel >= 50;
    }
    
    // Getters
    public int getExperiencia() {
        return experiencia;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public int getProxNivel() {
        return proxNivel;
    }
    
    // Setters (para persistência)
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
        this.proxNivel = calcularExperienciaNivel(nivel + 1);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nível ").append(nivel);
        if (!isNivelMaximo()) {
            sb.append(" (").append(experienciaParaProximoNivel()).append(" XP para nível ").append(nivel + 1).append(")");
        } else {
            sb.append(" (NÍVEL MÁXIMO)");
        }
        return sb.toString();
    }
    
    /**
     * Obter informações detalhadas da experiência
     */
    public String obterDetalhes() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== EXPERIÊNCIA ===\n");
        sb.append("Nível: ").append(nivel).append("\n");
        sb.append("Experiência: ").append(experiencia).append("\n");
        if (!isNivelMaximo()) {
            sb.append("Próximo Nível: ").append(proxNivel).append(" XP\n");
            sb.append("Faltam: ").append(experienciaParaProximoNivel()).append(" XP\n");
        } else {
            sb.append("NÍVEL MÁXIMO ATINGIDO!\n");
        }
        sb.append("Bônus de Atributo: +").append(calcularBonusAtributo()).append("\n");
        return sb.toString();
    }
}
