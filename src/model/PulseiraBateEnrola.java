package model;

/**
 * Classe que representa a Pulseira Bate Enrola
 * Baseado na documentação página 77:
 * 
 * "para poder usar a Forma Primitiva de seu Saci, você precisará de uma 
 * Pulseira Bate Enrola com o Cristal Primitivo do tipo correspondente ao seu Saci.
 * Esse item único carrega uma energia ancestral que, ao ser ativada, 
 * desperta o potencial máximo do seu companheiro."
 */
public class PulseiraBateEnrola {
    private int id;
    private String nome;
    private FormaPrimitiva.TipoCristal cristalInstalado;
    private boolean ativa;
    private int usosRestantes;
    private final int usosMaximos;
    
    public PulseiraBateEnrola() {
        this.nome = "Pulseira Bate Enrola";
        this.ativa = false;
        this.usosMaximos = 3; // Limitado para balanceamento
        this.usosRestantes = usosMaximos;
    }
    
    public PulseiraBateEnrola(FormaPrimitiva.TipoCristal cristal) {
        this();
        this.cristalInstalado = cristal;
        this.nome = "Pulseira Bate Enrola (" + cristal.getNome() + ")";
    }
    
    /**
     * Verifica se a pulseira pode ser usada por um Saci
     * Conforme documentação: "do tipo correspondente ao seu Saci"
     */
    public boolean podeUsar(TipoSaci tipoSaci) {
        if (cristalInstalado == null) {
            return false;
        }
        
        if (usosRestantes <= 0) {
            return false;
        }
        
        return cristalInstalado.podeUsar(tipoSaci);
    }
    
    /**
     * Usa a pulseira para ativar Forma Primitiva
     * @param tipoSaci O tipo do Saci que vai usar
     * @return true se foi usado com sucesso
     */
    public boolean usar(TipoSaci tipoSaci) {
        if (!podeUsar(tipoSaci)) {
            return false;
        }
        
        this.usosRestantes--;
        this.ativa = true;
        
        return true;
    }
    
    /**
     * Instala um cristal na pulseira
     */
    public boolean instalarCristal(FormaPrimitiva.TipoCristal cristal) {
        if (this.cristalInstalado != null) {
            return false; // Já tem cristal instalado
        }
        
        this.cristalInstalado = cristal;
        this.nome = "Pulseira Bate Enrola (" + cristal.getNome() + ")";
        return true;
    }
    
    /**
     * Remove o cristal da pulseira
     */
    public FormaPrimitiva.TipoCristal removerCristal() {
        FormaPrimitiva.TipoCristal cristalRemovido = this.cristalInstalado;
        this.cristalInstalado = null;
        this.nome = "Pulseira Bate Enrola (Vazia)";
        return cristalRemovido;
    }
    
    /**
     * Recarrega a pulseira (para balanceamento)
     */
    public void recarregar() {
        this.usosRestantes = usosMaximos;
        this.ativa = false;
    }
    
    /**
     * Desativa a pulseira após uso
     */
    public void desativar() {
        this.ativa = false;
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
    
    public FormaPrimitiva.TipoCristal getCristalInstalado() {
        return cristalInstalado;
    }
    
    public void setCristalInstalado(FormaPrimitiva.TipoCristal cristalInstalado) {
        this.cristalInstalado = cristalInstalado;
    }
    
    public boolean isAtiva() {
        return ativa;
    }
    
    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    
    public int getUsosRestantes() {
        return usosRestantes;
    }
    
    public void setUsosRestantes(int usosRestantes) {
        this.usosRestantes = usosRestantes;
    }
    
    public int getUsosMaximos() {
        return usosMaximos;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome);
        sb.append(" - Usos: ").append(usosRestantes).append("/").append(usosMaximos);
        
        if (cristalInstalado != null) {
            sb.append(" - Cristal: ").append(cristalInstalado.getNome());
            sb.append(" (Para Sacis ").append(cristalInstalado.getTipoCorrespondente()).append(")");
        } else {
            sb.append(" - SEM CRISTAL");
        }
        
        if (ativa) {
            sb.append(" [ATIVA]");
        }
        
        return sb.toString();
    }
    
    /**
     * Cria uma descrição detalhada conforme a documentação
     */
    public String getDescricaoCompleta() {
        return String.format(
            "%s\n" +
            "\"Esse item único carrega uma energia ancestral que, ao ser ativada, " +
            "desperta o potencial máximo do seu companheiro.\"\n" +
            "Usos restantes: %d/%d\n" +
            "Cristal instalado: %s\n" +
            "Estado: %s",
            nome,
            usosRestantes, usosMaximos,
            cristalInstalado != null ? cristalInstalado.getNome() : "Nenhum",
            ativa ? "ATIVA" : "INATIVA"
        );
    }
}
