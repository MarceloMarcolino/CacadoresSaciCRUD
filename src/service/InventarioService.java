package service;

import dao.InventarioDAO;
import model.Inventario;
import model.Inventario.TipoItem;
import java.util.List;
import java.util.Map;

/**
 * Service para gerenciar operações de inventário
 */
public class InventarioService {
    private InventarioDAO inventarioDAO;

    public InventarioService() {
        this.inventarioDAO = new InventarioDAO();
    }

    /**
     * Criar inventário para um novo caçador
     */
    public boolean criarInventarioCacador(int cacadorId) {
        try {
            Inventario inventario = inventarioDAO.criarInventario(cacadorId);
            System.out.println("Inventário criado para o caçador ID: " + cacadorId);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao criar inventário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Adicionar item ao inventário
     */
    public boolean adicionarItem(int cacadorId, TipoItem tipo, int quantidade) {
        try {
            if (quantidade <= 0) {
                System.out.println("Quantidade deve ser maior que zero!");
                return false;
            }

            boolean sucesso = inventarioDAO.adicionarItem(cacadorId, tipo, quantidade);
            if (sucesso) {
                System.out.printf("Adicionado %d %s(s) ao inventário do caçador %d\n", 
                    quantidade, tipo.getNome(), cacadorId);
            } else {
                System.out.println("Erro ao adicionar item - caçador não encontrado!");
            }
            return sucesso;
        } catch (Exception e) {
            System.out.println("Erro ao adicionar item: " + e.getMessage());
            return false;
        }
    }

    /**
     * Usar item do inventário
     */
    public boolean usarItem(int cacadorId, TipoItem tipo, int quantidade) {
        try {
            Inventario inventario = inventarioDAO.buscarPorCacadorId(cacadorId);
            if (inventario == null) {
                System.out.println("Inventário não encontrado!");
                return false;
            }

            if (!inventario.temItem(tipo, quantidade)) {
                System.out.printf("Quantidade insuficiente de %s! Tem: %d, Precisa: %d\n",
                    tipo.getNome(), inventario.obterQuantidade(tipo), quantidade);
                return false;
            }

            boolean sucesso = inventario.removerItem(tipo, quantidade);
            if (sucesso) {
                System.out.printf("Usado %d %s(s) do inventário do caçador %d\n", 
                    quantidade, tipo.getNome(), cacadorId);
            }
            return sucesso;
        } catch (Exception e) {
            System.out.println("Erro ao usar item: " + e.getMessage());
            return false;
        }
    }

    /**
     * Capturar Saci (usa garrafa de captura)
     */
    public boolean tentarCaptura(int cacadorId) {
        System.out.println("=== TENTATIVA DE CAPTURA ===");
        
        Inventario inventario = inventarioDAO.buscarPorCacadorId(cacadorId);
        if (inventario == null) {
            System.out.println("Inventário não encontrado!");
            return false;
        }

        if (!inventario.temItem(TipoItem.GARRAFA_CAPTURA, 1)) {
            System.out.println("Você não tem garrafas de captura!");
            return false;
        }

        // Simula chance de captura (70% de sucesso)
        boolean capturou = Math.random() < 0.7;
        
        if (capturou) {
            inventario.usarGarrafa();
            System.out.println("🎉 Captura bem-sucedida! Garrafa utilizada.");
            return true;
        } else {
            inventario.usarGarrafa();
            System.out.println("💔 O Saci escapou! Garrafa perdida.");
            return false;
        }
    }

    /**
     * Curar Saci
     */
    public boolean curarSaci(int cacadorId, String nomeSaci) {
        System.out.println("=== CURANDO SACI ===");
        
        boolean sucesso = usarItem(cacadorId, TipoItem.CURATIVO, 1);
        if (sucesso) {
            System.out.printf("Saci %s foi curado com sucesso!\n", nomeSaci);
        }
        return sucesso;
    }

    /**
     * Usar item especial
     */
    public boolean usarItemEspecial(int cacadorId, String nomeItem) {
        System.out.println("=== USANDO ITEM ESPECIAL ===");
        
        Inventario inventario = inventarioDAO.buscarPorCacadorId(cacadorId);
        if (inventario == null) {
            System.out.println("Inventário não encontrado!");
            return false;
        }

        boolean sucesso = inventario.usarItemEspecial();
        if (sucesso) {
            System.out.printf("Item Especial '%s' usado com sucesso!\n", nomeItem);
        } else {
            System.out.println("Item especial já foi usado nesta sessão!");
        }
        return sucesso;
    }

    /**
     * Ver inventário completo
     */
    public void mostrarInventario(int cacadorId) {
        Inventario inventario = inventarioDAO.buscarPorCacadorId(cacadorId);
        if (inventario == null) {
            System.out.println("Inventário não encontrado!");
            return;
        }

        System.out.println(inventario.obterResumo());
    }

    /**
     * Comprar item com tampinhas (simulação de Mercoruja)
     */
    public boolean comprarItem(int cacadorId, TipoItem item, int preco) {
        System.out.printf("=== COMPRANDO %s ===\n", item.getNome());
        
        Inventario inventario = inventarioDAO.buscarPorCacadorId(cacadorId);
        if (inventario == null) {
            System.out.println("Inventário não encontrado!");
            return false;
        }

        if (!inventario.temItem(TipoItem.TAMPINHA, preco)) {
            System.out.printf("Tampinhas insuficientes! Tem: %d, Precisa: %d\n",
                inventario.obterQuantidade(TipoItem.TAMPINHA), preco);
            return false;
        }

        if (inventario.gastarTampinhas(preco)) {
            inventario.adicionarItem(item, 1);
            System.out.printf("Comprou %s por %d tampinhas!\n", item.getNome(), preco);
            return true;
        }

        return false;
    }

    /**
     * Recarregar itens especiais (nova sessão)
     */
    public void novaSessione() {
        inventarioDAO.recarregarItensEspeciais();
    }

    /**
     * Listar inventários de todos os caçadores
     */
    public void listarTodosInventarios() {
        List<Map.Entry<Integer, Inventario>> inventarios = inventarioDAO.listarTodos();
        
        if (inventarios.isEmpty()) {
            System.out.println("Nenhum inventário encontrado.");
            return;
        }

        System.out.println("=== TODOS OS INVENTÁRIOS ===");
        for (Map.Entry<Integer, Inventario> entry : inventarios) {
            System.out.println("Caçador ID: " + entry.getKey());
            System.out.println(entry.getValue().obterResumo());
            System.out.println("---");
        }
    }

    /**
     * Obter estatísticas de inventário
     */
    public void mostrarEstatisticas() {
        System.out.println(inventarioDAO.obterEstatisticas());
    }
}
