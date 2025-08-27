package model;

/**
 * Classe que representa um Movimento de Saci
 * Movimentos são os ataques e habilidades que os Sacis podem usar em combate
 */
public class Movimento {
    private int id;
    private String nome;
    private TipoSaci tipo;
    private TipoMovimento tipoMovimento; // Novo campo para tipo de movimento
    private CategoriaMovimento categoria;
    private String testeAtaque; // Ex: "Agilidade + Magia + 1"
    private String efeito;
    private String descricao;
    private boolean temPrioridade;
    private boolean ehArtimanha;
    private boolean ehArea;
    private int rankMinimo; // Rank mínimo do caçador para aprender
    private int dadosBase; // Quantidade base de dados para o teste
    private CondicaoStatus condicaoAplicada; // Condição que pode ser aplicada

    // Construtor padrão
    public Movimento() {
    }

    // Construtor simplificado para exemplos básicos
    public Movimento(String nome, TipoSaci tipo, CategoriaMovimento categoria, 
                    int dano, int precisao, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.efeito = "Dano: " + dano;
        this.testeAtaque = "Precisão: " + precisao + "%";
        this.descricao = descricao;
        this.temPrioridade = false;
        this.ehArtimanha = false;
        this.ehArea = false;
        this.rankMinimo = 1;
    }

    // Construtor completo
    public Movimento(int id, String nome, TipoSaci tipo, CategoriaMovimento categoria,
                    String testeAtaque, String efeito, String descricao,
                    boolean temPrioridade, boolean ehArtimanha, boolean ehArea, int rankMinimo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.testeAtaque = testeAtaque;
        this.efeito = efeito;
        this.descricao = descricao;
        this.temPrioridade = temPrioridade;
        this.ehArtimanha = ehArtimanha;
        this.ehArea = ehArea;
        this.rankMinimo = rankMinimo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoSaci getTipo() {
        return tipo;
    }

    public void setTipo(TipoSaci tipo) {
        this.tipo = tipo;
    }

    public CategoriaMovimento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaMovimento categoria) {
        this.categoria = categoria;
    }

    public String getTesteAtaque() {
        return testeAtaque;
    }

    public void setTesteAtaque(String testeAtaque) {
        this.testeAtaque = testeAtaque;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isTemPrioridade() {
        return temPrioridade;
    }

    public void setTemPrioridade(boolean temPrioridade) {
        this.temPrioridade = temPrioridade;
    }

    public boolean isEhArtimanha() {
        return ehArtimanha;
    }

    public void setEhArtimanha(boolean ehArtimanha) {
        this.ehArtimanha = ehArtimanha;
    }

    public boolean isEhArea() {
        return ehArea;
    }

    public void setEhArea(boolean ehArea) {
        this.ehArea = ehArea;
    }

    public int getRankMinimo() {
        return rankMinimo;
    }

    public void setRankMinimo(int rankMinimo) {
        this.rankMinimo = rankMinimo;
    }

    // Getters e Setters para os novos campos
    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public int getDadosBase() {
        return dadosBase;
    }

    public void setDadosBase(int dadosBase) {
        this.dadosBase = dadosBase;
    }

    public CondicaoStatus getCondicaoAplicada() {
        return condicaoAplicada;
    }

    public void setCondicaoAplicada(CondicaoStatus condicaoAplicada) {
        this.condicaoAplicada = condicaoAplicada;
    }

    /**
     * Verifica se o movimento pode aplicar uma condição de status
     */
    public boolean podeAplicarCondicao() {
        return condicaoAplicada != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append(" (").append(tipo).append(" - ").append(categoria).append(")");
        
        if (temPrioridade) sb.append(" [PRIORIDADE]");
        if (ehArtimanha) sb.append(" [ARTIMANHA]");
        if (ehArea) sb.append(" [ÁREA]");
        
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movimento movimento = (Movimento) obj;
        return id == movimento.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
