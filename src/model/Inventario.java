package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa o inventário de um caçador
 * Gerencia itens como Garrafas de Captura, Curativos e Tampinhas
 */
public class Inventario {
    private Map<TipoItem, Integer> itens;
    
    public Inventario() {
        this.itens = new HashMap<>();
        // Todo caçador começa com 2 garrafas conforme documentação
        itens.put(TipoItem.GARRAFA_CAPTURA, 2);
        itens.put(TipoItem.CURATIVO, 0);
        itens.put(TipoItem.TAMPINHA, 0);
        itens.put(TipoItem.ITEM_ESPECIAL, 1); // O item especial da classe
    }
    
    /**
     * Adiciona itens ao inventário
     */
    public void adicionarItem(TipoItem tipo, int quantidade) {
        itens.put(tipo, itens.getOrDefault(tipo, 0) + quantidade);
    }
    
    /**
     * Remove itens do inventário (se possível)
     */
    public boolean removerItem(TipoItem tipo, int quantidade) {
        int quantidadeAtual = itens.getOrDefault(tipo, 0);
        if (quantidadeAtual >= quantidade) {
            itens.put(tipo, quantidadeAtual - quantidade);
            return true;
        }
        return false;
    }
    
    /**
     * Verifica quantidade de um item
     */
    public int obterQuantidade(TipoItem tipo) {
        return itens.getOrDefault(tipo, 0);
    }
    
    /**
     * Verifica se tem o item em quantidade suficiente
     */
    public boolean temItem(TipoItem tipo, int quantidade) {
        return obterQuantidade(tipo) >= quantidade;
    }
    
    /**
     * Usa uma garrafa de captura
     */
    public boolean usarGarrafa() {
        return removerItem(TipoItem.GARRAFA_CAPTURA, 1);
    }
    
    /**
     * Usa um curativo
     */
    public boolean usarCurativo() {
        return removerItem(TipoItem.CURATIVO, 1);
    }
    
    /**
     * Gasta tampinhas
     */
    public boolean gastarTampinhas(int quantidade) {
        return removerItem(TipoItem.TAMPINHA, quantidade);
    }
    
    /**
     * Usa o item especial (limitado a 1 por sessão)
     */
    public boolean usarItemEspecial() {
        if (temItem(TipoItem.ITEM_ESPECIAL, 1)) {
            removerItem(TipoItem.ITEM_ESPECIAL, 1);
            return true;
        }
        return false;
    }
    
    /**
     * Recarrega o item especial no início de uma nova sessão
     */
    public void recarregarItemEspecial() {
        itens.put(TipoItem.ITEM_ESPECIAL, 1);
    }
    
    /**
     * Obter resumo do inventário
     */
    public String obterResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTÁRIO ===\n");
        sb.append("Garrafas de Captura: ").append(obterQuantidade(TipoItem.GARRAFA_CAPTURA)).append("\n");
        sb.append("Curativos: ").append(obterQuantidade(TipoItem.CURATIVO)).append("\n");
        sb.append("Tampinhas: ").append(obterQuantidade(TipoItem.TAMPINHA)).append("\n");
        sb.append("Item Especial: ").append(obterQuantidade(TipoItem.ITEM_ESPECIAL) > 0 ? "Disponível" : "Usado").append("\n");
        return sb.toString();
    }
    
    /**
     * Enum para tipos de itens
     */
    public enum TipoItem {
        GARRAFA_CAPTURA("Garrafa de Captura", "Essencial para capturar Sacis selvagens"),
        CURATIVO("Curativo", "Restaura PV dos Sacis feridos"),
        TAMPINHA("Tampinha", "Moeda do mundo dos Sacis, usada com Mercorujas"),
        ITEM_ESPECIAL("Item Especial", "Item único da classe do caçador");
        
        private final String nome;
        private final String descricao;
        
        TipoItem(String nome, String descricao) {
            this.nome = nome;
            this.descricao = descricao;
        }
        
        public String getNome() {
            return nome;
        }
        
        public String getDescricao() {
            return descricao;
        }
    }
}
