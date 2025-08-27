package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Caçador no jogo Caçadores de Saci
 * Caçadores são os personagens jogadores que capturam e treinam Sacis
 * 
 * ATUALIZADO COM SISTEMAS COMPLETOS BASEADOS NA DOCUMENTAÇÃO:
 * - Sistema de Progressão (Atributos e Perícias gerenciados internamente)
 * - Sistema de Itens (Garrafas, Tampinhas, Curativos)
 * - Traços e Sonhos
 * - Compatibilidade com sistemas existentes
 */
public class CacadorCompleto {
    private int id;
    private String nome;
    private int idade;
    private ClasseCacador classe;
    private TipoSonhador tipoSonhador; 
    private String sonho;
    private List<Integer> sacis; // IDs dos Sacis que o caçador possui
    
    // SISTEMAS PRINCIPAIS IMPLEMENTADOS
    private SistemaProgressao progressao; // Gerencia atributos, perícias, ranks, PP
    private SistemaItens itens;           // Gerencia garrafas, tampinhas, curativos
    private Inventario inventario;        // Sistema de inventário existente
    private List<Traco> tracos;          // Sistema de traços existente
    
    // Informações adicionais
    private String biografia;
    private int nivel; // Manter compatibilidade
    private int experiencia; // Manter compatibilidade

    // Construtor padrão
    public CacadorCompleto() {
        // Inicializar sistemas novos
        this.progressao = new SistemaProgressao();
        this.itens = new SistemaItens();
        this.inventario = new Inventario();
        this.tracos = new ArrayList<>();
        this.sacis = new ArrayList<>();
        
        // Valores padrão
        this.nivel = 1;
        this.experiencia = 0;
    }

    // Construtor com parâmetros principais
    public CacadorCompleto(String nome, ClasseCacador classe, String sonho) {
        this();
        this.nome = nome;
        this.classe = classe;
        this.sonho = sonho;
    }

    // Construtor completo
    public CacadorCompleto(int id, String nome, ClasseCacador classe, String sonho, String biografia) {
        this();
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.sonho = sonho;
        this.biografia = biografia;
    }

    // MÉTODOS DE ACESSO AOS SISTEMAS

    /**
     * Distribui pontos de atributo
     */
    public boolean distribuirAtributo(AtributoCacador atributo, int pontos) {
        return progressao.distribuirAtributo(atributo, pontos);
    }

    /**
     * Distribui pontos de perícia
     */
    public boolean distribuirPericia(PericiaCacador pericia, int pontos) {
        return progressao.distribuirPericia(pericia, pontos);
    }

    /**
     * Adiciona pontos de progressão
     */
    public void ganharPP(int pontos) {
        progressao.adicionarPP(pontos);
    }

    /**
     * Usa garrafa de captura
     */
    public boolean usarGarrafaCaptura() {
        return itens.usarGarrafaCaptura();
    }

    /**
     * Compra garrafa com tampinha
     */
    public boolean comprarGarrafa() {
        return itens.comprarGarrafa();
    }

    /**
     * Recebe tampinhas por capturar Saci
     */
    public void receberTampinhas(Rank rankSaci, boolean sucessoMaximo) {
        itens.receberTampinhas(rankSaci, sucessoMaximo);
    }

    /**
     * Adiciona um traço
     */
    public void adicionarTraco(String nomeTraco) {
        progressao.adicionarTraco(nomeTraco);
    }

    /**
     * Adiciona um Saci ao time
     */
    public void adicionarSaci(int idSaci) {
        if (!sacis.contains(idSaci)) {
            sacis.add(idSaci);
        }
    }

    /**
     * Remove um Saci do time
     */
    public boolean removerSaci(int idSaci) {
        return sacis.remove(Integer.valueOf(idSaci));
    }

    // GETTERS PARA ATRIBUTOS (via sistema de progressão)
    public int getCoracao() {
        return progressao.getValorAtributo(AtributoCacador.CORACAO);
    }

    public int getMusculo() {
        return progressao.getValorAtributo(AtributoCacador.MUSCULO);
    }

    public int getCerebro() {
        return progressao.getValorAtributo(AtributoCacador.CEREBRO);
    }

    public int getCharme() {
        return progressao.getValorAtributo(AtributoCacador.CHARME);
    }

    // GETTERS PARA PERÍCIAS (via sistema de progressão)
    public int getAtencao() {
        return progressao.getValorPericia(PericiaCacador.ATENCAO);
    }

    public int getTatica() {
        return progressao.getValorPericia(PericiaCacador.TATICA);
    }

    public int getArte() {
        return progressao.getValorPericia(PericiaCacador.ARTE);
    }

    public int getEmpatia() {
        return progressao.getValorPericia(PericiaCacador.EMPATIA);
    }

    public int getEncrenca() {
        return progressao.getValorPericia(PericiaCacador.ENCRENCA);
    }

    public int getEsportes() {
        return progressao.getValorPericia(PericiaCacador.ESPORTES);
    }

    public int getMandar() {
        return progressao.getValorPericia(PericiaCacador.MANDAR);
    }

    public int getFingir() {
        return progressao.getValorPericia(PericiaCacador.FINGIR);
    }

    // GETTERS E SETTERS BÁSICOS
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public ClasseCacador getClasse() {
        return classe;
    }

    public void setClasse(ClasseCacador classe) {
        this.classe = classe;
    }

    public TipoSonhador getTipoSonhador() {
        return tipoSonhador;
    }

    public void setTipoSonhador(TipoSonhador tipoSonhador) {
        this.tipoSonhador = tipoSonhador;
    }

    public String getSonho() {
        return sonho;
    }

    public void setSonho(String sonho) {
        this.sonho = sonho;
    }

    public List<Integer> getSacis() {
        return new ArrayList<>(sacis);
    }

    public void setSacis(List<Integer> sacis) {
        this.sacis = new ArrayList<>(sacis);
    }

    public SistemaProgressao getProgressao() {
        return progressao;
    }

    public SistemaItens getItens() {
        return itens;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<Traco> getTracos() {
        return new ArrayList<>(tracos);
    }

    public void setTracos(List<Traco> tracos) {
        this.tracos = new ArrayList<>(tracos);
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return String.format(
            "Caçador: %s | Classe: %s | Rank: %s | Sonho: %s\n" +
            "Atributos - Coração: %d, Músculo: %d, Cérebro: %d, Charme: %d\n" +
            "%s\n%s",
            nome != null ? nome : "Sem nome",
            classe != null ? classe.getNome() : "Sem classe",
            progressao.getRankAtual().getSimbolo(),
            sonho != null ? sonho : "Sem sonho",
            getCoracao(), getMusculo(), getCerebro(), getCharme(),
            progressao.toString(),
            itens.toString()
        );
    }
}
