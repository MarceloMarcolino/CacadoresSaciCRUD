package model;

import java.util.*;

/**
 * Sistema de Progressão do Caçador
 * Baseado na documentação: "é a partir desses pontos que você poderá fazer 
 * com que seu Caçador e Saci se tornem mais fortes"
 */
public class SistemaProgressao {
    private int pontosProgressao; // PP
    private RankCacador rankAtual;
    private Map<AtributoCacador, Integer> atributos;
    private Map<PericiaCacador, Integer> pericias;
    private List<String> tracos;

    public SistemaProgressao() {
        this.pontosProgressao = 0;
        this.rankAtual = RankCacador.INICIAL;
        this.atributos = new HashMap<>();
        this.pericias = new HashMap<>();
        this.tracos = new ArrayList<>();
        
        // Inicializar com valores base
        inicializarAtributos();
        inicializarPericias();
    }

    /**
     * Inicializa todos os atributos com 1 ponto (base)
     * + 2 pontos extras para distribuir
     */
    private void inicializarAtributos() {
        for (AtributoCacador attr : AtributoCacador.values()) {
            atributos.put(attr, 1);
        }
    }

    /**
     * Inicializa todas as perícias com 0 pontos
     * + 5 pontos para distribuir
     */
    private void inicializarPericias() {
        for (PericiaCacador pericia : PericiaCacador.values()) {
            pericias.put(pericia, 0);
        }
    }

    /**
     * Adiciona pontos de progressão
     */
    public void adicionarPP(int pontos) {
        this.pontosProgressao += pontos;
        verificarProgressao();
    }

    /**
     * Verifica se pode progredir de rank
     */
    private void verificarProgressao() {
        int ppNecessarios = getPPNecessarios();
        if (ppNecessarios > 0 && pontosProgressao >= ppNecessarios) {
            subirRank();
        }
    }

    /**
     * Retorna PP necessários para o próximo rank
     */
    public int getPPNecessarios() {
        return rankAtual.getPpNecessarios();
    }

    /**
     * Sobe de rank e aplica benefícios
     */
    private void subirRank() {
        RankCacador novoRank = rankAtual.getProximoRank();
        if (novoRank != null) {
            this.rankAtual = novoRank;
            this.pontosProgressao = 0; // Reset PP
            aplicarBeneficios();
        }
    }

    /**
     * Aplica benefícios do novo rank
     */
    private void aplicarBeneficios() {
        switch (rankAtual) {
            case INICIAL:
                // +2 Atributos de Caçador + 5 Perícias (já aplicado na criação)
                break;
            case UMA_ESTRELA:
                // +1 Traço + 1 Perícias (aplicado manualmente)
                break;
            case DUAS_ESTRELAS:
                // +1 Dado em Testes de Respostas + 1 Atributo de Caçador
                break;
            case TRES_ESTRELAS:
                // +1 Traço + 1 Perícias
                break;
            case QUATRO_ESTRELAS:
                // +1 Dado em Testes de Respostas + 1 Atributo de Caçador
                break;
            case MESTRE:
                // +1 Dado em Testes de Respostas + 1 Traço + 1 Perícias
                break;
        }
    }

    /**
     * Distribui pontos de atributo
     */
    public boolean distribuirAtributo(AtributoCacador atributo, int pontos) {
        if (pontos < 0) return false;
        
        int valorAtual = atributos.getOrDefault(atributo, 1);
        atributos.put(atributo, valorAtual + pontos);
        return true;
    }

    /**
     * Distribui pontos de perícia
     */
    public boolean distribuirPericia(PericiaCacador pericia, int pontos) {
        if (pontos < 0) return false;
        
        int valorAtual = pericias.getOrDefault(pericia, 0);
        pericias.put(pericia, valorAtual + pontos);
        return true;
    }

    /**
     * Adiciona um traço
     */
    public void adicionarTraco(String traco) {
        if (!tracos.contains(traco)) {
            tracos.add(traco);
        }
    }

    /**
     * Remove um traço
     */
    public boolean removerTraco(String traco) {
        return tracos.remove(traco);
    }

    /**
     * Perguntas de progressão para ganhar PP
     */
    public enum PerguntaProgressao {
        DERROTOU_INIMIGO("Vocês derrotaram um inimigo poderoso?"),
        DESCOBRIU_MUNDO("Vocês descobriram mais sobre o mundo?"),
        CAPTUROU_SACI("Vocês capturaram um novo Saci?"),
        CRIOU_LACOS_PNJ("Vocês criaram laços com algum PNJ?"),
        APROXIMOU_SONHO("Vocês fizeram algo que fez com que chegassem mais perto de seus sonhos?");

        private final String pergunta;

        PerguntaProgressao(String pergunta) {
            this.pergunta = pergunta;
        }

        public String getPergunta() {
            return pergunta;
        }
    }

    // Getters e Setters
    public int getPontosProgressao() {
        return pontosProgressao;
    }

    public void setPontosProgressao(int pontosProgressao) {
        this.pontosProgressao = pontosProgressao;
    }

    public RankCacador getRankAtual() {
        return rankAtual;
    }

    public void setRankAtual(RankCacador rankAtual) {
        this.rankAtual = rankAtual;
    }

    public Map<AtributoCacador, Integer> getAtributos() {
        return atributos;
    }

    public Map<PericiaCacador, Integer> getPericias() {
        return pericias;
    }

    public List<String> getTracos() {
        return tracos;
    }

    public int getValorAtributo(AtributoCacador atributo) {
        return atributos.getOrDefault(atributo, 1);
    }

    public int getValorPericia(PericiaCacador pericia) {
        return pericias.getOrDefault(pericia, 0);
    }

    @Override
    public String toString() {
        return String.format("Progressão - Rank: %s | PP: %d/%d | Traços: %d",
                rankAtual, pontosProgressao, getPPNecessarios(), tracos.size());
    }
}
