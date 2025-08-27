package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Sistema de Combate completo
 * Baseado na documentação do sistema Caçadores de Saci
 */
public class SistemaCombate {
    private List<Saci> sacisCombate;
    private CondicaoClimatica climaAtual;
    private int turnoAtual;
    private List<String> logCombate;
    
    public SistemaCombate() {
        this.sacisCombate = new ArrayList<>();
        this.climaAtual = CondicaoClimatica.NORMAL;
        this.turnoAtual = 1;
        this.logCombate = new ArrayList<>();
    }
    
    /**
     * Inicia um novo combate
     */
    public void iniciarCombate(List<Saci> participantes) {
        this.sacisCombate = new ArrayList<>(participantes);
        this.turnoAtual = 1;
        this.logCombate.clear();
        adicionarLog("=== COMBATE INICIADO ===");
        adicionarLog("Clima atual: " + climaAtual.toString());
        adicionarLog("Participantes: " + participantes.size() + " Sacis");
    }
    
    /**
     * Executa um movimento em combate
     */
    public String executarMovimento(Saci atacante, Saci defensor, Movimento movimento) {
        StringBuilder resultado = new StringBuilder();
        
        // Verifica se o Saci pode usar o movimento
        if (!podeUsarMovimento(atacante, movimento)) {
            return "❌ " + atacante.getNome() + " não pode usar " + movimento.getNome();
        }
        
        // Aplica efeitos do clima
        int dadosBonus = calcularBonusClima(movimento);
        
        // Simula teste de ataque (simplificado)
        boolean acertou = simularTesteAtaque(movimento, dadosBonus);
        
        resultado.append("🎯 ").append(atacante.getNome())
                .append(" usa ").append(movimento.getNome())
                .append(" em ").append(defensor.getNome());
        
        if (acertou) {
            // Calcula dano
            int dano = calcularDano(movimento, atacante);
            
            // Aplica dano
            int pvAnterior = defensor.getPv();
            defensor.setPv(Math.max(0, pvAnterior - dano));
            
            resultado.append("\n✅ Acertou! Dano: ").append(dano);
            resultado.append("\n❤️ ").append(defensor.getNome())
                    .append(": ").append(pvAnterior).append(" → ").append(defensor.getPv()).append(" PV");
            
            // Verifica se aplicou condição de status
            if (movimento.podeAplicarCondicao()) {
                if (Math.random() < 0.3) { // 30% de chance
                    defensor.adicionarCondicaoStatus(movimento.getCondicaoAplicada());
                    resultado.append("\n🔥 ").append(defensor.getNome())
                            .append(" ficou ").append(movimento.getCondicaoAplicada().getNome());
                }
            }
            
            // Verifica se o defensor foi derrubado
            if (defensor.getPv() <= 0) {
                resultado.append("\n💀 ").append(defensor.getNome()).append(" foi derrubado!");
            }
        } else {
            resultado.append("\n❌ Errou!");
        }
        
        String log = resultado.toString();
        adicionarLog(log);
        return log;
    }
    
    /**
     * Muda o clima do combate
     */
    public void mudarClima(CondicaoClimatica novoClima) {
        CondicaoClimatica climaAnterior = this.climaAtual;
        this.climaAtual = novoClima;
        
        String log = "🌤️ Clima mudou de " + climaAnterior.getNome() + 
                     " para " + novoClima.getNome();
        adicionarLog(log);
    }
    
    /**
     * Verifica se Saci pode usar movimento
     */
    private boolean podeUsarMovimento(Saci saci, Movimento movimento) {
        // Verifica condições de status que impedem movimentos
        for (CondicaoStatus condicao : saci.getCondicoesStatus()) {
            if (condicao == CondicaoStatus.ADORMECIDO || condicao == CondicaoStatus.PARALISADO) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Calcula bônus do clima para movimento
     */
    private int calcularBonusClima(Movimento movimento) {
        if (climaAtual == CondicaoClimatica.NORMAL) return 0;
        
        // Movimentos de fogo ganham bônus com sol
        if (climaAtual == CondicaoClimatica.ENSOLARADO && 
            (movimento.getNome().contains("Fogo") || movimento.getNome().contains("Solar"))) {
            return 1;
        }
        
        // Movimentos de água ganham bônus com chuva
        if (climaAtual == CondicaoClimatica.CHUVOSO && 
            movimento.getNome().contains("Água")) {
            return 1;
        }
        
        return 0;
    }
    
    /**
     * Simula teste de ataque (simplificado)
     */
    private boolean simularTesteAtaque(Movimento movimento, int dadosBonus) {
        // Simula rolagem de dados
        int sucessos = 0;
        int totalDados = movimento.getDadosBase() + dadosBonus;
        
        for (int i = 0; i < totalDados; i++) {
            int dado = (int) (Math.random() * 6) + 1;
            if (dado >= 4) { // 4, 5, 6 são sucessos
                sucessos++;
            }
        }
        
        return sucessos > 0;
    }
    
    /**
     * Calcula dano do movimento
     */
    private int calcularDano(Movimento movimento, Saci atacante) {
        // Dano base baseado no tipo de movimento
        int danoBase = 2;
        
        // Bônus baseado nos atributos do atacante
        if (movimento.getTipoMovimento() == TipoMovimento.FISICO) {
            danoBase += atacante.getPotencia() / 3;
        } else if (movimento.getTipoMovimento() == TipoMovimento.MAGICO) {
            danoBase += atacante.getMagia() / 3;
        }
        
        return Math.max(1, danoBase);
    }
    
    /**
     * Adiciona log ao combate
     */
    private void adicionarLog(String mensagem) {
        logCombate.add("Turno " + turnoAtual + ": " + mensagem);
    }
    
    /**
     * Avança para próximo turno
     */
    public void proximoTurno() {
        turnoAtual++;
        adicionarLog("=== TURNO " + turnoAtual + " ===");
        
        // Processa efeitos de condições de status
        for (Saci saci : sacisCombate) {
            processarCondicoesStatus(saci);
        }
    }
    
    /**
     * Processa efeitos de condições de status
     */
    private void processarCondicoesStatus(Saci saci) {
        List<CondicaoStatus> condicoesParaRemover = new ArrayList<>();
        
        for (CondicaoStatus condicao : saci.getCondicoesStatus()) {
            switch (condicao) {
                case QUEIMADO:
                    saci.setPv(Math.max(0, saci.getPv() - 1));
                    adicionarLog("🔥 " + saci.getNome() + " sofre 1 de dano de queimadura");
                    break;
                case ENVENENADO:
                    saci.setPv(Math.max(0, saci.getPv() - 2));
                    adicionarLog("☠️ " + saci.getNome() + " sofre 2 de dano de veneno");
                    break;
                case ADORMECIDO:
                    adicionarLog("😴 " + saci.getNome() + " está adormecido");
                    // 50% chance de acordar
                    if (Math.random() < 0.5) {
                        condicoesParaRemover.add(condicao);
                        adicionarLog("😊 " + saci.getNome() + " acordou!");
                    }
                    break;
                case CONFUSO:
                    adicionarLog("😵 " + saci.getNome() + " está confuso");
                    break;
                case PARALISADO:
                    adicionarLog("⚡ " + saci.getNome() + " está paralisado");
                    // Paralisia dura só 1 turno
                    condicoesParaRemover.add(condicao);
                    break;
                case CEGO:
                    adicionarLog("👁️ " + saci.getNome() + " está cego");
                    break;
            }
        }
        
        // Remove condições que expiraram
        for (CondicaoStatus condicao : condicoesParaRemover) {
            saci.removerCondicaoStatus(condicao);
        }
    }
    
    /**
     * Verifica se combate terminou
     */
    public boolean combateTerminou() {
        long sacisAtivos = sacisCombate.stream().filter(s -> s.getPv() > 0).count();
        return sacisAtivos <= 1;
    }
    
    // Getters
    public CondicaoClimatica getClimaAtual() {
        return climaAtual;
    }
    
    public int getTurnoAtual() {
        return turnoAtual;
    }
    
    public List<String> getLogCombate() {
        return new ArrayList<>(logCombate);
    }
    
    public List<Saci> getSacisCombate() {
        return new ArrayList<>(sacisCombate);
    }
}
