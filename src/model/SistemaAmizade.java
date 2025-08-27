package model;

/**
 * Sistema de Amizade/Laços entre Caçador e Saci
 * Baseado na documentação: "os Sacis formam Laços: ligações profundas de amizade, 
 * respeito e companheirismo com os caçadores"
 */
public class SistemaAmizade {
    private int lacos;
    private int nivelAmizade;
    private boolean podeEvoluir;

    public SistemaAmizade() {
        this.lacos = 0;
        this.nivelAmizade = calcularNivelAmizade();
        this.podeEvoluir = false;
    }

    public SistemaAmizade(int lacos) {
        this.lacos = lacos;
        this.nivelAmizade = calcularNivelAmizade();
        this.podeEvoluir = verificarPodeEvoluir();
    }

    /**
     * Calcula o nível de amizade baseado nos laços
     * Tabela da documentação:
     * Nível 1: 4 Laços
     * Nível 2: 8 Laços  
     * Nível 3: 12 Laços
     * Nível 4: 16 Laços
     * Nível 5: 20 Laços
     * Nível 6: 24 Laços
     * Nível 7: Bônus
     */
    private int calcularNivelAmizade() {
        if (lacos >= 24) return 7;
        if (lacos >= 20) return 6;
        if (lacos >= 16) return 5;
        if (lacos >= 12) return 4;
        if (lacos >= 8) return 3;
        if (lacos >= 4) return 2;
        return 1;
    }

    /**
     * Verifica se o Saci pode evoluir baseado nos laços
     * ☆ evolui com 12+ laços
     * ☆☆ evolui com 16+ laços  
     * ☆☆☆ evolui com 20+ laços
     */
    private boolean verificarPodeEvoluir() {
        return lacos >= 12; // Será refinado com base no rank do Saci
    }

    /**
     * Adiciona laços de amizade
     */
    public void adicionarLacos(int quantidade) {
        this.lacos += quantidade;
        this.nivelAmizade = calcularNivelAmizade();
        this.podeEvoluir = verificarPodeEvoluir();
    }

    /**
     * Remove laços (usado quando Saci evolui - retrocede 2 níveis)
     */
    public void removerLacos(int quantidade) {
        this.lacos = Math.max(0, this.lacos - quantidade);
        this.nivelAmizade = calcularNivelAmizade();
        this.podeEvoluir = verificarPodeEvoluir();
    }

    /**
     * Retorna os bônus do nível atual
     */
    public String getBonusAtual() {
        switch (nivelAmizade) {
            case 1: return "Nenhum bônus";
            case 2: return "+1 Movimento";
            case 3: return "+1 Atributo";
            case 4: return "+1 Movimento";
            case 5: return "+1 Atributo";
            case 6: return "+1 Movimento";
            case 7: return "+1 Atributo";
            default: return "Nível inválido";
        }
    }

    /**
     * Verifica se está nos 3 primeiros níveis (período de desobediência)
     */
    public boolean estaPeriodoDesobediencia() {
        return nivelAmizade <= 3;
    }

    // Getters e Setters
    public int getLacos() {
        return lacos;
    }

    public void setLacos(int lacos) {
        this.lacos = lacos;
        this.nivelAmizade = calcularNivelAmizade();
        this.podeEvoluir = verificarPodeEvoluir();
    }

    public int getNivelAmizade() {
        return nivelAmizade;
    }

    public boolean isPodeEvoluir() {
        return podeEvoluir;
    }

    public void setPodeEvoluir(boolean podeEvoluir) {
        this.podeEvoluir = podeEvoluir;
    }

    @Override
    public String toString() {
        return String.format("Amizade - Nível: %d | Laços: %d | Bônus: %s | Pode Evoluir: %s",
                nivelAmizade, lacos, getBonusAtual(), podeEvoluir ? "Sim" : "Não");
    }
}
