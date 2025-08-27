package dao;

import model.Inventario;
import model.Inventario.TipoItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO para gerenciar inventários dos caçadores
 * Sistema simples para fins educacionais
 */
public class InventarioDAO {
    // Simulação de banco de dados usando Map
    private static Map<Integer, Inventario> inventarios = new HashMap<>();
    private static int proximoId = 1;

    /**
     * Criar inventário para um caçador
     */
    public Inventario criarInventario(int cacadorId) {
        Inventario inventario = new Inventario();
        inventarios.put(cacadorId, inventario);
        return inventario;
    }

    /**
     * Buscar inventário por ID do caçador
     */
    public Inventario buscarPorCacadorId(int cacadorId) {
        return inventarios.get(cacadorId);
    }

    /**
     * Atualizar inventário
     */
    public boolean atualizarInventario(int cacadorId, Inventario inventario) {
        if (inventarios.containsKey(cacadorId)) {
            inventarios.put(cacadorId, inventario);
            return true;
        }
        return false;
    }

    /**
     * Adicionar item ao inventário de um caçador
     */
    public boolean adicionarItem(int cacadorId, TipoItem tipo, int quantidade) {
        Inventario inventario = inventarios.get(cacadorId);
        if (inventario != null) {
            inventario.adicionarItem(tipo, quantidade);
            return true;
        }
        return false;
    }

    /**
     * Remover item do inventário de um caçador
     */
    public boolean removerItem(int cacadorId, TipoItem tipo, int quantidade) {
        Inventario inventario = inventarios.get(cacadorId);
        if (inventario != null) {
            return inventario.removerItem(tipo, quantidade);
        }
        return false;
    }

    /**
     * Verificar quantidade de um item no inventário
     */
    public int obterQuantidadeItem(int cacadorId, TipoItem tipo) {
        Inventario inventario = inventarios.get(cacadorId);
        if (inventario != null) {
            return inventario.obterQuantidade(tipo);
        }
        return 0;
    }

    /**
     * Listar todos os inventários (para fins administrativos)
     */
    public List<Map.Entry<Integer, Inventario>> listarTodos() {
        return new ArrayList<>(inventarios.entrySet());
    }

    /**
     * Deletar inventário de um caçador
     */
    public boolean deletarInventario(int cacadorId) {
        return inventarios.remove(cacadorId) != null;
    }

    /**
     * Recarregar item especial para todos os caçadores (início de nova sessão)
     */
    public void recarregarItensEspeciais() {
        for (Inventario inventario : inventarios.values()) {
            inventario.recarregarItemEspecial();
        }
        System.out.println("Todos os itens especiais foram recarregados para nova sessão!");
    }

    /**
     * Obter estatísticas de uso de itens
     */
    public String obterEstatisticas() {
        if (inventarios.isEmpty()) {
            return "Nenhum inventário encontrado.";
        }

        int totalGarrafas = 0;
        int totalCurativos = 0;
        int totalTampinhas = 0;
        int cacadoresComItemDisponivel = 0;

        for (Inventario inv : inventarios.values()) {
            totalGarrafas += inv.obterQuantidade(TipoItem.GARRAFA_CAPTURA);
            totalCurativos += inv.obterQuantidade(TipoItem.CURATIVO);
            totalTampinhas += inv.obterQuantidade(TipoItem.TAMPINHA);
            if (inv.obterQuantidade(TipoItem.ITEM_ESPECIAL) > 0) {
                cacadoresComItemDisponivel++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTATÍSTICAS DE INVENTÁRIO ===\n");
        sb.append("Total de Caçadores: ").append(inventarios.size()).append("\n");
        sb.append("Total de Garrafas: ").append(totalGarrafas).append("\n");
        sb.append("Total de Curativos: ").append(totalCurativos).append("\n");
        sb.append("Total de Tampinhas: ").append(totalTampinhas).append("\n");
        sb.append("Caçadores com Item Especial Disponível: ").append(cacadoresComItemDisponivel).append("\n");

        return sb.toString();
    }

    /**
     * Limpar todos os dados (para testes)
     */
    public static void limparDados() {
        inventarios.clear();
        proximoId = 1;
    }
}
