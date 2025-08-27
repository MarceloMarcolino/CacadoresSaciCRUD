package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Caçador no jogo Caçadores de Saci
 * Caçadores são os personagens jogadores que capturam e treinam Sacis
 * 
 * ATUALIZADO COM SISTEMAS COMPLETOS BASEADOS NA DOCUMENTAÇÃO:
 * - Atributos e Perícias
 * - Sistema de Progressão 
 * - Sistema de Amizade (será aplicado aos Sacis)
 * - Sistema de Itens
 * - Traços e Sonhos
 */
public class Cacador {
    private int id;
    private String nome;
    private int idade;
    private ClasseCacador classe;
    private TipoSonhador tipoSonhador; 
    private String sonho;
    private List<Integer> sacis; // IDs dos Sacis que o caçador possui
    
    // NOVOS SISTEMAS IMPLEMENTADOS
    private SistemaProgressao progressao; // Sistema completo de progressão
    private SistemaItens itens;           // Sistema de itens (garrafas, tampinhas, etc.)
    private Inventario inventario;        // Sistema de inventário existente
    private List<Traco> tracos;          // Sistema de traços existente
    
    // Informações adicionais
    private String biografia;
    private int nivel; // Manter compatibilidade com sistema antigo
    private int experiencia; // Manter compatibilidade com sistema antigo

    // Construtor padrão
    public Cacador() {
        // Todos começam com 1 ponto em cada atributo
        this.coracao = 1;
        this.musculo = 1;
        this.cerebro = 1;
        this.charme = 1;
        this.nivel = 1;
        this.experiencia = 0;
        this.sacis = new ArrayList<>();
        this.inventario = new Inventario();
        this.tracos = new ArrayList<>();
    }

    // Construtor com parâmetros principais
    public Cacador(String nome, ClasseCacador classe, String sonho) {
        this();
        this.nome = nome;
        this.classe = classe;
        this.sonho = sonho;
    }

    // Construtor completo
    public Cacador(int id, String nome, ClasseCacador classe, String sonho,
                   int coracao, int musculo, int cerebro, int charme,
                   int atencao, int tatica, int arte, int empatia, int encrenca,
                   int esportes, int mandar, int fingir, String biografia) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.sonho = sonho;
        this.coracao = coracao;
        this.musculo = musculo;
        this.cerebro = cerebro;
        this.charme = charme;
        this.atencao = atencao;
        this.tatica = tatica;
        this.arte = arte;
        this.empatia = empatia;
        this.encrenca = encrenca;
        this.esportes = esportes;
        this.mandar = mandar;
        this.fingir = fingir;
        this.biografia = biografia;
        this.nivel = 1;
        this.experiencia = 0;
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

    public ClasseCacador getClasse() {
        return classe;
    }

    public void setClasse(ClasseCacador classe) {
        this.classe = classe;
    }

    public String getSonho() {
        return sonho;
    }

    public void setSonho(String sonho) {
        this.sonho = sonho;
    }

    public int getCoracao() {
        return coracao;
    }

    public void setCoracao(int coracao) {
        this.coracao = coracao;
    }

    public int getMusculo() {
        return musculo;
    }

    public void setMusculo(int musculo) {
        this.musculo = musculo;
    }

    public int getCerebro() {
        return cerebro;
    }

    public void setCerebro(int cerebro) {
        this.cerebro = cerebro;
    }

    public int getCharme() {
        return charme;
    }

    public void setCharme(int charme) {
        this.charme = charme;
    }

    public int getAtencao() {
        return atencao;
    }

    public void setAtencao(int atencao) {
        this.atencao = atencao;
    }

    public int getTatica() {
        return tatica;
    }

    public void setTatica(int tatica) {
        this.tatica = tatica;
    }

    public int getArte() {
        return arte;
    }

    public void setArte(int arte) {
        this.arte = arte;
    }

    public int getEmpatia() {
        return empatia;
    }

    public void setEmpatia(int empatia) {
        this.empatia = empatia;
    }

    public int getEncrenca() {
        return encrenca;
    }

    public void setEncrenca(int encrenca) {
        this.encrenca = encrenca;
    }

    public int getEsportes() {
        return esportes;
    }

    public void setEsportes(int esportes) {
        this.esportes = esportes;
    }

    public int getMandar() {
        return mandar;
    }

    public void setMandar(int mandar) {
        this.mandar = mandar;
    }

    public int getFingir() {
        return fingir;
    }

    public void setFingir(int fingir) {
        this.fingir = fingir;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Integer> getSacis() {
        return sacis;
    }

    public void setSacis(List<Integer> sacis) {
        this.sacis = sacis;
    }

    public void adicionarSaci(int saciId) {
        if (sacis == null) {
            sacis = new ArrayList<>();
        }
        sacis.add(saciId);
    }

    public void removerSaci(int saciId) {
        if (sacis != null) {
            sacis.remove(Integer.valueOf(saciId));
        }
    }

    // Métodos utilitários
    public int calcularTotalAtributos() {
        return coracao + musculo + cerebro + charme;
    }

    public int calcularTotalPericias() {
        return atencao + tatica + arte + empatia + encrenca + esportes + mandar + fingir;
    }

    // Getters e Setters para os novos campos
    public TipoSonhador getTipoSonhador() {
        return tipoSonhador;
    }

    public void setTipoSonhador(TipoSonhador tipoSonhador) {
        this.tipoSonhador = tipoSonhador;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    // Métodos para facilitar uso do inventário
    public boolean usarGarrafa() {
        return inventario.usarGarrafa();
    }

    public boolean usarCurativo() {
        return inventario.usarCurativo();
    }

    public boolean gastarTampinhas(int quantidade) {
        return inventario.gastarTampinhas(quantidade);
    }

    public boolean usarItemEspecial() {
        return inventario.usarItemEspecial();
    }

    public void recarregarItemEspecial() {
        inventario.recarregarItemEspecial();
    }

    public String obterResumoInventario() {
        return inventario.obterResumo();
    }

    // Getters e Setters para Traços
    public List<Traco> getTracos() {
        return tracos;
    }

    public void setTracos(List<Traco> tracos) {
        this.tracos = tracos;
    }

    public void adicionarTraco(Traco traco) {
        if (tracos == null) {
            tracos = new ArrayList<>();
        }
        if (!tracos.contains(traco) && traco.isCompativelCom(this.classe)) {
            tracos.add(traco);
        }
    }

    public void removerTraco(Traco traco) {
        if (tracos != null) {
            tracos.remove(traco);
        }
    }

    public boolean temTraco(Traco traco) {
        return tracos != null && tracos.contains(traco);
    }

    public String listarTracos() {
        if (tracos == null || tracos.isEmpty()) {
            return "Nenhum traço especial";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Traços: ");
        for (int i = 0; i < tracos.size(); i++) {
            sb.append(tracos.get(i).getNome());
            if (i < tracos.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    // Método toString para exibir informações do Caçador
    @Override
    public String toString() {
        return String.format(
            "Caçador{id=%d, nome='%s', classe=%s, nível=%d, " +
            "atributos=(♥%d 💪%d 🧠%d ✨%d), " +
            "sonho='%s'}",
            id, nome, classe, nivel, coracao, musculo, cerebro, charme, sonho
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cacador cacador = (Cacador) obj;
        return id == cacador.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
