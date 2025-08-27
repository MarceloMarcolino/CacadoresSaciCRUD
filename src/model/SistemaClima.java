package model;

/**
 * Sistema de Clima aprimorado baseado na documentação completa
 * Baseado na página 75 da documentação:
 * 
 * "O clima aqui não é apenas um detalhe de fundo, mas sim uma ferramenta
 * estratégica nas mãos habilidosas de um Saci e seu caçador!"
 */
public class SistemaClima {
    private CondicaoClimatica climaAtual;
    private int rodadasRestantes;
    private boolean permanente;
    
    public SistemaClima() {
        this.climaAtual = CondicaoClimatica.NORMAL;
        this.rodadasRestantes = 0;
        this.permanente = false;
    }
    
    /**
     * Muda o clima do campo de batalha
     * @param novoClima O novo clima a ser aplicado
     * @param duracao Duração em rodadas (0 = permanente)
     */
    public void mudarClima(CondicaoClimatica novoClima, int duracao) {
        this.climaAtual = novoClima;
        this.rodadasRestantes = duracao;
        this.permanente = (duracao == 0);
    }
    
    /**
     * Processa o fim de uma rodada
     * @return true se o clima mudou para Normal
     */
    public boolean processarRodada() {
        if (permanente || climaAtual == CondicaoClimatica.NORMAL) {
            return false;
        }
        
        rodadasRestantes--;
        if (rodadasRestantes <= 0) {
            climaAtual = CondicaoClimatica.NORMAL;
            return true;
        }
        
        return false;
    }
    
    /**
     * Verifica se um Saci pode ser afetado por uma condição específica
     * Baseado nos efeitos climáticos da documentação
     */
    public boolean podeSerAfetadoPorCondicao(String condicao) {
        switch (climaAtual) {
            case ENSOLARADO:
                // "Ninguém pode ser Congelado enquanto esse clima estiver em campo"
                return !condicao.equals("Congelado");
                
            case CHUVOSO:
                // "Ninguém pode ser Queimado enquanto esse clima estiver em campo"
                return !condicao.startsWith("Queimado");
                
            default:
                return true;
        }
    }
    
    /**
     * Calcula bônus de dados em Teste de Ataque baseado no tipo do Saci
     * Conforme documentação página 75
     */
    public int getBonusDadosAtaque(TipoSaci tipoSaci) {
        switch (climaAtual) {
            case ENSOLARADO:
                // "Todos os Sacis do tipo Valente recebem 1 dado a mais em seu Teste de Ataque"
                return tipoSaci == TipoSaci.VALENTE ? 1 : 0;
                
            case CHUVOSO:
                // "Todos os Sacis do tipo Cativante recebem 1 dado a mais em seu Teste de Ataque"
                return tipoSaci == TipoSaci.CATIVANTE ? 1 : 0;
                
            case NUBLADO:
                // "Todos os Sacis do tipo Sombrio e Caóticos recebem 1 dado a mais"
                return (tipoSaci == TipoSaci.SOMBRIO || tipoSaci == TipoSaci.CAOTICO) ? 1 : 0;
                
            default:
                return 0;
        }
    }
    
    /**
     * Calcula penalidade de dados para movimentos mágicos
     */
    public int getPenalidadeMovimentosMagicos() {
        switch (climaAtual) {
            case TEMPESTUOSO:
                // "Movimentos Mágicos tem menos 1 dado em Testes de Ataque"
                return -1;
            default:
                return 0;
        }
    }
    
    /**
     * Calcula bônus de atributo baseado no clima
     */
    public int getBonusAtributo(TipoSaci tipoSaci, String atributo) {
        switch (climaAtual) {
            case TEMPESTUOSO:
                // "Sacis do tipo Rígido recebem 1 ponto a mais em Resistência"
                if (tipoSaci == TipoSaci.RIGIDO && atributo.equals("Resistencia")) {
                    return 1;
                }
                break;
            case ENSOLARADO:
            case CHUVOSO: 
            case NUBLADO:
            case NORMAL:
            default:
                break;
        }
        return 0;
    }
    
    /**
     * Calcula dano por clima em Sacis não-Rígidos
     */
    public int getDanoPorClima(TipoSaci tipoSaci) {
        switch (climaAtual) {
            case TEMPESTUOSO:
                // "Qualquer Saci que não seja do tipo Rígido deve rolar um dado por rodada,
                // se não obtiver um sucesso, ele leva 1 ponto de dano"
                return tipoSaci != TipoSaci.RIGIDO ? 1 : 0;
            case ENSOLARADO:
            case CHUVOSO: 
            case NUBLADO:
            case NORMAL:
            default:
                return 0;
        }
    }
    
    /**
     * Calcula bônus para condições específicas
     */
    public int getBonusCondicao(String tipoMovimento) {
        switch (climaAtual) {
            case ENSOLARADO:
                // "Adicione 1 dado a mais em testes que deixem o Saci Queimado"
                if (tipoMovimento.contains("Queimado")) {
                    return 1;
                }
                break;
                
            case NUBLADO:
                // "Adicione 1 dado a mais em Testes de Ataque que deixem o Saci Envenenado"
                if (tipoMovimento.contains("Envenenado")) {
                    return 1;
                }
                break;
        }
        return 0;
    }
    
    /**
     * Verifica efeitos especiais do clima
     */
    public String getEfeitoEspecial() {
        switch (climaAtual) {
            case CHUVOSO:
                return "Sacis Congelados sofrem -1 de Resistência além dos -2 de Agilidade";
                
            case NUBLADO:
                return "Cochilando dura uma rodada a mais";
                
            case ENSOLARADO:
            case TEMPESTUOSO:
            case NORMAL:
            default:
                return null;
        }
    }
    
    /**
     * Verifica se um movimento pode ser usado instantaneamente devido ao clima
     * Conforme documentação: movimentos que precisam carregamento podem ser usados
     * instantaneamente em climas específicos
     */
    public boolean podeUsarInstantaneamente(String nomeMovimento) {
        switch (climaAtual) {
            case TEMPESTUOSO:
                // "Cabeçada Massiva" pode ser usada instantaneamente em clima Tempestuoso
                return nomeMovimento.equals("Cabeçada Massiva");
            case ENSOLARADO:
            case CHUVOSO: 
            case NUBLADO:
            case NORMAL:
            default:
                return false;
        }
    }
    
    // Getters e Setters
    public CondicaoClimatica getClimaAtual() {
        return climaAtual;
    }
    
    public void setClimaAtual(CondicaoClimatica climaAtual) {
        this.climaAtual = climaAtual;
    }
    
    public int getRodadasRestantes() {
        return rodadasRestantes;
    }
    
    public void setRodadasRestantes(int rodadasRestantes) {
        this.rodadasRestantes = rodadasRestantes;
    }
    
    public boolean isPermanente() {
        return permanente;
    }
    
    public void setPermanente(boolean permanente) {
        this.permanente = permanente;
    }
    
    @Override
    public String toString() {
        if (climaAtual == CondicaoClimatica.NORMAL) {
            return "Clima: Normal";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Clima: ").append(climaAtual.getNome());
        
        if (permanente) {
            sb.append(" (Permanente)");
        } else if (rodadasRestantes > 0) {
            sb.append(" (").append(rodadasRestantes).append(" rodadas restantes)");
        }
        
        return sb.toString();
    }
    
    /**
     * Retorna descrição completa dos efeitos do clima atual
     */
    public String getDescricaoCompleta() {
        if (climaAtual == CondicaoClimatica.NORMAL) {
            return "Clima normal - nenhum efeito especial";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("🌤️ Clima: ").append(climaAtual.getNome()).append("\n");
        sb.append(climaAtual.getDescricao()).append("\n");
        
        // Adicionar efeitos específicos baseados na documentação
        switch (climaAtual) {
            case ENSOLARADO:
                sb.append("• Sacis Valentes: +1 dado em ataques\n");
                sb.append("• Imunidade a Congelado\n");
                sb.append("• +1 dado em ataques que causam Queimado\n");
                break;
                
            case CHUVOSO:
                sb.append("• Sacis Cativantes: +1 dado em ataques\n");
                sb.append("• Imunidade a Queimado\n");
                sb.append("• Congelados: -1 Resistência adicional\n");
                break;
                
            case TEMPESTUOSO:
                sb.append("• Sacis Rígidos: +1 Resistência\n");
                sb.append("• Não-Rígidos: risco de 1 dano por turno\n");
                sb.append("• Movimentos Mágicos: -1 dado\n");
                sb.append("• Cabeçada Massiva pode ser usada instantaneamente\n");
                break;
                
            case NUBLADO:
                sb.append("• Sacis Sombrios e Caóticos: +1 dado em ataques\n");
                sb.append("• Cochilando dura +1 rodada\n");
                sb.append("• +1 dado em ataques que causam Envenenado\n");
                break;
                
            case NORMAL:
            default:
                sb.append("• Nenhum efeito especial\n");
                break;
        }
        
        if (permanente) {
            sb.append("Duração: Permanente");
        } else if (rodadasRestantes > 0) {
            sb.append("Rodadas restantes: ").append(rodadasRestantes);
        }
        
        return sb.toString();
    }
}
