package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe que representa um Saci no jogo Caçadores de Saci
 * Esta é uma classe modelo básica para fins educacionais
 */
public class Saci {
    private int id;
    private String nome;
    private TipoSaci tipo;
    private TemperamentoSaci temperamentoSaci; // Novo sistema de temperamento específico
    private Temperamento temperamento; // Mantém compatibilidade
    private Rank rank;
    
    // Novos sistemas do jogo
    private SistemaExperiencia experiencia;
    private List<CondicaoStatus> condicoesStatus;
    private boolean isSelvagem;
    private int cacadorId; // ID do caçador que possui este Saci
    private List<TipoCrescimento> crescimentosDisponiveis; // Crescimentos que pode usar
    
    // Atributos básicos do Saci
    private int pv; // Pontos de Vida
    private int potencia;
    private int agilidade;
    private int resistencia;
    private int magia;
    private int foco;
    
    private HabilidadeSaci habilidadeEspecial; // Mudou de String para enum
    private String habilidadeSuporte;
    private String descricao;
    private List<String> movimentos; // Lista de movimentos que o Saci conhece
    private boolean podeEvoluir;
    private String evolucaoPara; // Nome do Saci para qual este evolui

    // Construtor padrão
    public Saci() {
        this.movimentos = new ArrayList<>();
        this.podeEvoluir = false;
        this.experiencia = new SistemaExperiencia();
        this.condicoesStatus = new ArrayList<>();
        this.isSelvagem = true;
        this.cacadorId = -1; // -1 indica que não tem dono
    }

    // Construtor com parâmetros principais
    public Saci(String nome, TipoSaci tipo, Temperamento temperamento, Rank rank) {
        this();
        this.nome = nome;
        this.tipo = tipo;
        this.temperamento = temperamento;
        this.rank = rank;
    }

    // Construtor completo
    public Saci(int id, String nome, TipoSaci tipo, TemperamentoSaci temperamentoSaci, Rank rank,
               int pv, int potencia, int agilidade, int resistencia, int magia, int foco,
               HabilidadeSaci habilidadeEspecial, String habilidadeSuporte, String descricao) {
        this();
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.temperamentoSaci = temperamentoSaci;
        this.rank = rank;
        this.pv = pv;
        this.potencia = potencia;
        this.agilidade = agilidade;
        this.resistencia = resistencia;
        this.magia = magia;
        this.foco = foco;
        this.habilidadeEspecial = habilidadeEspecial;
        this.habilidadeSuporte = habilidadeSuporte;
        this.descricao = descricao;
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

    public Temperamento getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(Temperamento temperamento) {
        this.temperamento = temperamento;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public int getFoco() {
        return foco;
    }

    public void setFoco(int foco) {
        this.foco = foco;
    }

    public HabilidadeSaci getHabilidadeEspecial() {
        return habilidadeEspecial;
    }

    public void setHabilidadeEspecial(HabilidadeSaci habilidadeEspecial) {
        this.habilidadeEspecial = habilidadeEspecial;
    }

    public String getHabilidadeSuporte() {
        return habilidadeSuporte;
    }

    public void setHabilidadeSuporte(String habilidadeSuporte) {
        this.habilidadeSuporte = habilidadeSuporte;
    }

    public List<String> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<String> movimentos) {
        this.movimentos = movimentos;
    }

    public void adicionarMovimento(String movimento) {
        if (movimentos == null) {
            movimentos = new ArrayList<>();
        }
        movimentos.add(movimento);
    }

    public void removerMovimento(String movimento) {
        if (movimentos != null) {
            movimentos.remove(movimento);
        }
    }

    public boolean isPodeEvoluir() {
        return podeEvoluir;
    }

    public void setPodeEvoluir(boolean podeEvoluir) {
        this.podeEvoluir = podeEvoluir;
    }

    public String getEvolucaoPara() {
        return evolucaoPara;
    }

    public void setEvolucaoPara(String evolucaoPara) {
        this.evolucaoPara = evolucaoPara;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getters e Setters para os novos campos
    public TemperamentoSaci getTemperamentoSaci() {
        return temperamentoSaci;
    }

    public void setTemperamentoSaci(TemperamentoSaci temperamentoSaci) {
        this.temperamentoSaci = temperamentoSaci;
    }

    public SistemaExperiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(SistemaExperiencia experiencia) {
        this.experiencia = experiencia;
    }

    public List<CondicaoStatus> getCondicoesStatus() {
        return condicoesStatus;
    }

    public void setCondicoesStatus(List<CondicaoStatus> condicoesStatus) {
        this.condicoesStatus = condicoesStatus;
    }

    public boolean isSelvagem() {
        return isSelvagem;
    }

    public void setSelvagem(boolean selvagem) {
        isSelvagem = selvagem;
    }

    public int getCacadorId() {
        return cacadorId;
    }

    public void setCacadorId(int cacadorId) {
        this.cacadorId = cacadorId;
        this.isSelvagem = (cacadorId == -1); // Atualiza status selvagem
    }

    // Métodos para gerenciar condições de status
    public void adicionarCondicaoStatus(CondicaoStatus condicao) {
        if (!condicoesStatus.contains(condicao)) {
            condicoesStatus.add(condicao);
        }
    }

    public void removerCondicaoStatus(CondicaoStatus condicao) {
        condicoesStatus.remove(condicao);
    }

    public boolean temCondicaoStatus(CondicaoStatus condicao) {
        return condicoesStatus.contains(condicao);
    }

    public void limparCondicoesStatus() {
        condicoesStatus.clear();
    }

    // Método para capturar o Saci (muda de selvagem para domesticado)
    public void capturar(int cacadorId) {
        this.cacadorId = cacadorId;
        this.isSelvagem = false;
    }

    // Método para libertar o Saci (volta a ser selvagem)
    public void libertar() {
        this.cacadorId = -1;
        this.isSelvagem = true;
    }

    // Getters e Setters para os novos sistemas
    public List<TipoCrescimento> getCrescimentosDisponiveis() {
        return crescimentosDisponiveis;
    }

    public void setCrescimentosDisponiveis(List<TipoCrescimento> crescimentosDisponiveis) {
        this.crescimentosDisponiveis = crescimentosDisponiveis;
    }

    public void adicionarCrescimento(TipoCrescimento crescimento) {
        if (crescimentosDisponiveis == null) {
            crescimentosDisponiveis = new ArrayList<>();
        }
        crescimentosDisponiveis.add(crescimento);
    }

    public boolean podeUsarCrescimento(TipoCrescimento crescimento) {
        return crescimento.podeUsar(this.pv);
    }

    public void usarCrescimento(TipoCrescimento crescimento) {
        if (podeUsarCrescimento(crescimento)) {
            this.pv = crescimento.aplicarCusto(this.pv);
        }
    }

    // Método para verificar se tem habilidade específica
    public boolean temHabilidade(HabilidadeSaci habilidade) {
        return this.habilidadeEspecial == habilidade;
    }

    // Método para calcular o poder total do Saci
    public int calcularPoderTotal() {
        return potencia + agilidade + resistencia + magia + foco;
    }

    // Método toString para exibir informações do Saci
    @Override
    public String toString() {
        return String.format(
            "Saci{id=%d, nome='%s', tipo=%s, temperamento=%s, rank=%s, " +
            "pv=%d, potência=%d, agilidade=%d, resistência=%d, magia=%d, foco=%d, " +
            "poder total=%d, habilidade='%s'}",
            id, nome, tipo, temperamento, rank, pv, potencia, agilidade, 
            resistencia, magia, foco, calcularPoderTotal(), habilidadeEspecial
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Saci saci = (Saci) obj;
        return id == saci.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
