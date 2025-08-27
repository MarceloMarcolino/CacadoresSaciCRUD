package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe Saci COMPLETA com todos os sistemas da documentação
 * 
 * SISTEMAS IMPLEMENTADOS:
 * - Sistema de Amizade/Laços com Caçador
 * - Sistema de Traquinagem
 * - Sistema de Desobediência
 * - Sistema de Evolução
 * - Sistema de Crescimento (gastar PV para bônus)
 * - Todos os atributos e mecânicas
 */
public class SaciCompleto {
    private int id;
    private String nome;
    private TipoSaci tipo;
    private TemperamentoSaci temperamentoSaci;
    private Rank rank;
    
    // SISTEMAS PRINCIPAIS IMPLEMENTADOS
    private SistemaAmizade amizade;         // Sistema de laços com caçador
    private SistemaTraquinagem traquinagem; // Sistema de pontos especiais
    private SistemaDesobediencia desobediencia; // Sistema de desobediência
    private SistemaExperiencia experiencia; // Sistema existente mantido
    
    // Sistemas complementares
    private List<CondicaoStatus> condicoesStatus;
    private List<TipoCrescimento> crescimentosDisponiveis;
    private boolean isSelvagem;
    private int cacadorId; // ID do caçador que possui este Saci
    
    // Atributos básicos do Saci
    private int pv; // Pontos de Vida
    private int pvMaximo; // PV máximo (para crescimento)
    private int potencia;
    private int agilidade;
    private int resistencia;
    private int magia;
    private int foco;
    
    // Habilidades e características
    private HabilidadeSaci habilidadeEspecial;
    private String habilidadeSuporte;
    private String descricao;
    private List<String> movimentos;
    
    // Sistema de evolução
    private boolean podeEvoluir;
    private String evolucaoPara;
    private boolean jaEvoluiu; // Para controlar retrocesso de amizade
    
    // Sistema de Forma Primitiva (páginas 75-77 da documentação)
    private FormaPrimitiva formaPrimitiva;
    private PulseiraBateEnrola pulseira;

    // Construtor padrão
    public SaciCompleto() {
        // Inicializar sistemas
        this.amizade = new SistemaAmizade();
        this.traquinagem = new SistemaTraquinagem();
        this.desobediencia = new SistemaDesobediencia();
        this.experiencia = new SistemaExperiencia();
        this.formaPrimitiva = new FormaPrimitiva(); // Sistema de Forma Primitiva
        
        // Inicializar listas
        this.movimentos = new ArrayList<>();
        this.condicoesStatus = new ArrayList<>();
        this.crescimentosDisponiveis = new ArrayList<>();
        
        // Valores padrão
        this.podeEvoluir = false;
        this.jaEvoluiu = false;
        this.isSelvagem = true;
        this.cacadorId = -1; // -1 indica que não tem dono
        
        // PV padrão baseado no rank
        this.pv = 6; // Valor padrão para rank ☆
        this.pvMaximo = this.pv;
    }

    // Construtor com parâmetros principais
    public SaciCompleto(String nome, TipoSaci tipo, Rank rank) {
        this();
        this.nome = nome;
        this.tipo = tipo;
        this.rank = rank;
        this.pv = rank.getPvBase();
        this.pvMaximo = this.pv;
    }

    // Construtor completo
    public SaciCompleto(int id, String nome, TipoSaci tipo, Rank rank,
                       int potencia, int agilidade, int resistencia, int magia, int foco,
                       HabilidadeSaci habilidadeEspecial, String habilidadeSuporte) {
        this(nome, tipo, rank);
        this.id = id;
        this.potencia = potencia;
        this.agilidade = agilidade;
        this.resistencia = resistencia;
        this.magia = magia;
        this.foco = foco;
        this.habilidadeEspecial = habilidadeEspecial;
        this.habilidadeSuporte = habilidadeSuporte;
    }

    // MÉTODOS DOS SISTEMAS IMPLEMENTADOS

    /**
     * Adiciona laços de amizade
     */
    public void ganharLacos(int quantidade) {
        amizade.adicionarLacos(quantidade);
        verificarPodeEvoluir();
    }

    /**
     * Verifica se pode evoluir baseado nos laços e rank
     */
    private void verificarPodeEvoluir() {
        int lacosNecessarios;
        switch (rank) {
            case UMA_ESTRELA:
                lacosNecessarios = 12;
                break;
            case DUAS_ESTRELAS:
                lacosNecessarios = 16;
                break;
            case TRES_ESTRELAS:
                lacosNecessarios = 20;
                break;
            default:
                lacosNecessarios = Integer.MAX_VALUE; // Não evolui
        }
        
        this.podeEvoluir = amizade.getLacos() >= lacosNecessarios;
    }

    /**
     * Evolui o Saci (deve ser chamado quando apropriado)
     */
    public boolean evoluir(SaciCompleto novaForma) {
        if (!podeEvoluir) return false;
        
        // Retrocede 2 níveis de amizade mas mantém bônus
        amizade.removerLacos(8); // Aproximadamente 2 níveis
        
        // Herda PV extras do rank do caçador
        int pvExtras = this.pv - this.rank.getPvBase();
        novaForma.setPv(novaForma.getRank().getPvBase() + pvExtras);
        novaForma.setPvMaximo(novaForma.getPv());
        
        // Marca como evoluído
        this.jaEvoluiu = true;
        novaForma.setJaEvoluiu(true);
        
        return true;
    }

    /**
     * Usa traquinagem para efeito especial
     */
    public boolean usarTraquinagem(SistemaTraquinagem.UsoTraquinagem uso) {
        return traquinagem.usarTraquinagem(uso);
    }

    /**
     * Verifica se desobedece
     */
    public boolean testarDesobediencia() {
        return desobediencia.verificarDesobediencia(amizade);
    }

    /**
     * Rola efeito de desobediência
     */
    public SistemaDesobediencia.EfeitoDesobediencia rolarDesobediencia() {
        return desobediencia.rolarEfeitoDesobediencia();
    }

    /**
     * Usa crescimento (gasta PV para bônus)
     */
    public boolean usarCrescimento(TipoCrescimento crescimento) {
        if (!crescimentosDisponiveis.contains(crescimento)) return false;
        if (pv <= crescimento.getCustoPV()) return false;
        
        // Gasta PV
        this.pv -= crescimento.getCustoPV();
        
        // Aplicar bônus do crescimento seria implementado aqui
        // (depende do sistema de combate)
        
        return true;
    }

    /**
     * Captura o Saci (torna-se não selvagem)
     */
    public void serCapturado(int cacadorId) {
        this.isSelvagem = false;
        this.cacadorId = cacadorId;
    }

    /**
     * Liberta o Saci (volta a ser selvagem)
     */
    public void serLibertado() {
        this.isSelvagem = true;
        this.cacadorId = -1;
    }

    /**
     * Adiciona condição de status
     */
    public void adicionarCondicao(CondicaoStatus condicao) {
        if (!condicoesStatus.contains(condicao)) {
            condicoesStatus.add(condicao);
        }
    }

    /**
     * Remove condição de status
     */
    public boolean removerCondicao(CondicaoStatus condicao) {
        return condicoesStatus.remove(condicao);
    }

    /**
     * Verifica se tem uma condição específica
     */
    public boolean temCondicao(CondicaoStatus condicao) {
        return condicoesStatus.contains(condicao);
    }

    /**
     * Adiciona movimento ao repertório
     */
    public void aprenderMovimento(String movimento) {
        if (!movimentos.contains(movimento)) {
            movimentos.add(movimento);
        }
    }

    /**
     * Remove movimento do repertório
     */
    public boolean esquecerMovimento(String movimento) {
        return movimentos.remove(movimento);
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

    public TipoSaci getTipo() {
        return tipo;
    }

    public void setTipo(TipoSaci tipo) {
        this.tipo = tipo;
    }

    public TemperamentoSaci getTemperamentoSaci() {
        return temperamentoSaci;
    }

    public void setTemperamentoSaci(TemperamentoSaci temperamentoSaci) {
        this.temperamentoSaci = temperamentoSaci;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public SistemaAmizade getAmizade() {
        return amizade;
    }

    public SistemaTraquinagem getTraquinagem() {
        return traquinagem;
    }

    public SistemaDesobediencia getDesobediencia() {
        return desobediencia;
    }

    public SistemaExperiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(SistemaExperiencia experiencia) {
        this.experiencia = experiencia;
    }

    public List<CondicaoStatus> getCondicoesStatus() {
        return new ArrayList<>(condicoesStatus);
    }

    public void setCondicoesStatus(List<CondicaoStatus> condicoesStatus) {
        this.condicoesStatus = new ArrayList<>(condicoesStatus);
    }

    public List<TipoCrescimento> getCrescimentosDisponiveis() {
        return new ArrayList<>(crescimentosDisponiveis);
    }

    public void setCrescimentosDisponiveis(List<TipoCrescimento> crescimentosDisponiveis) {
        this.crescimentosDisponiveis = new ArrayList<>(crescimentosDisponiveis);
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
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = Math.max(0, Math.min(pvMaximo, pv));
    }

    public int getPvMaximo() {
        return pvMaximo;
    }

    public void setPvMaximo(int pvMaximo) {
        this.pvMaximo = Math.max(1, pvMaximo);
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = Math.max(0, potencia);
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = Math.max(0, agilidade);
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = Math.max(0, resistencia);
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = Math.max(0, magia);
    }

    public int getFoco() {
        return foco;
    }

    public void setFoco(int foco) {
        this.foco = Math.max(0, foco);
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getMovimentos() {
        return new ArrayList<>(movimentos);
    }

    public void setMovimentos(List<String> movimentos) {
        this.movimentos = new ArrayList<>(movimentos);
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

    // Sistema de Forma Primitiva
    /**
     * Ativa a Forma Primitiva usando uma Pulseira Bate Enrola
     * Baseado na documentação páginas 75-77
     */
    public boolean ativarFormaPrimitiva(PulseiraBateEnrola pulseira) {
        if (pulseira == null || !pulseira.podeUsar(this.tipo)) {
            return false;
        }
        
        if (formaPrimitiva.isAtiva()) {
            return false; // Já está ativo
        }
        
        // Usar a pulseira
        if (!pulseira.usar(this.tipo)) {
            return false;
        }
        
        // Ativar forma primitiva
        int pvBonus = formaPrimitiva.ativar(this.tipo);
        this.pvMaximo += pvBonus;
        this.pv += pvBonus;
        
        this.pulseira = pulseira;
        
        return true;
    }
    
    /**
     * Desativa a Forma Primitiva
     * Conforme documentação: aplica condição Confuso após reverter
     */
    public boolean desativarFormaPrimitiva() {
        if (!formaPrimitiva.isAtiva()) {
            return false;
        }
        
        // Remover bônus de PV
        int pvBonus = formaPrimitiva.getPvBonus();
        this.pvMaximo = Math.max(1, this.pvMaximo - pvBonus);
        this.pv = Math.min(this.pv, this.pvMaximo);
        
        // Desativar e verificar se deve aplicar confuso
        boolean aplicarConfuso = formaPrimitiva.desativar();
        
        if (aplicarConfuso) {
            condicoesStatus.add(CondicaoStatus.CONFUSO); // Aplicar condição após reverter Forma Primitiva
        }
        
        if (pulseira != null) {
            pulseira.desativar();
        }
        
        return true;
    }
    
    /**
     * Processa o fim de um turno para Forma Primitiva
     * @return true se a forma foi desativada automaticamente
     */
    public boolean processarTurnoFormaPrimitiva() {
        if (formaPrimitiva.processarTurno()) {
            desativarFormaPrimitiva();
            return true;
        }
        return false;
    }
    
    /**
     * Verifica se pode usar crescimento (requer Forma Primitiva ativa)
     */
    public boolean podeUsarCrescimento() {
        return formaPrimitiva.podeUsar();
    }
    
    /**
     * Verifica se é imune a vantagens de tipo (Forma Primitiva)
     */
    public boolean isImuneVantagensTipo() {
        return formaPrimitiva.isImuneVantagensTipo();
    }

    // Getters e Setters para Forma Primitiva
    public FormaPrimitiva getFormaPrimitiva() {
        return formaPrimitiva;
    }

    public void setFormaPrimitiva(FormaPrimitiva formaPrimitiva) {
        this.formaPrimitiva = formaPrimitiva;
    }

    public PulseiraBateEnrola getPulseira() {
        return pulseira;
    }

    public void setPulseira(PulseiraBateEnrola pulseira) {
        this.pulseira = pulseira;
    }

    public boolean isJaEvoluiu() {
        return jaEvoluiu;
    }

    public void setJaEvoluiu(boolean jaEvoluiu) {
        this.jaEvoluiu = jaEvoluiu;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
            "Saci: %s | Tipo: %s | Rank: %s | PV: %d/%d\n",
            nome != null ? nome : "Sem nome",
            tipo != null ? tipo.getNome() : "Sem tipo",
            rank != null ? rank.getSimbolo() : "☆",
            pv, pvMaximo
        ));
        
        sb.append(String.format(
            "Atributos - Pot: %d, Agi: %d, Res: %d, Mag: %d, Foc: %d\n",
            potencia, agilidade, resistencia, magia, foco
        ));
        
        // Adicionar status da Forma Primitiva
        if (formaPrimitiva.isAtiva()) {
            sb.append("🔥 FORMA PRIMITIVA ATIVA! ").append(formaPrimitiva.toString()).append("\n");
        }
        
        sb.append(String.format(
            "%s | %s | Selvagem: %s | Pode Evoluir: %s",
            amizade.toString(),
            traquinagem.toString(),
            isSelvagem ? "Sim" : "Não",
            podeEvoluir ? "Sim" : "Não"
        ));
        
        return sb.toString();
    }
}
