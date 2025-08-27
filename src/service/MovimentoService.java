package service;

import dao.MovimentoDAO;
import model.Movimento;
import model.TipoSaci;
import model.CategoriaMovimento;
import java.util.List;

/**
 * Classe de Service para Movimento
 * Contém a lógica de negócio e validações para movimentos
 */
public class MovimentoService {
    
    private MovimentoDAO movimentoDAO;

    public MovimentoService() {
        this.movimentoDAO = new MovimentoDAO();
    }

    /**
     * Cria um novo Movimento com validações de negócio
     */
    public Movimento criarMovimento(String nome, TipoSaci tipo, CategoriaMovimento categoria,
                                   String testeAtaque, String efeito, String descricao) {
        // Validações de negócio
        String erroValidacao = validarDadosMovimento(nome, tipo, categoria, testeAtaque, efeito);
        
        if (erroValidacao != null) {
            System.out.println("❌ Erro de validação: " + erroValidacao);
            return null;
        }

        Movimento movimento = new Movimento();
        movimento.setNome(nome);
        movimento.setTipo(tipo);
        movimento.setCategoria(categoria);
        movimento.setTesteAtaque(testeAtaque);
        movimento.setEfeito(efeito);
        movimento.setDescricao(descricao);
        movimento.setTemPrioridade(false);
        movimento.setEhArtimanha(false);
        movimento.setEhArea(false);
        movimento.setRankMinimo(1);
        
        return movimentoDAO.criar(movimento);
    }

    /**
     * Busca um movimento por ID
     */
    public Movimento buscarMovimentoPorId(int id) {
        if (id <= 0) {
            System.out.println("❌ ID deve ser maior que zero");
            return null;
        }
        return movimentoDAO.buscarPorId(id);
    }

    /**
     * Busca um movimento por nome
     */
    public Movimento buscarMovimentoPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("❌ Nome não pode ser vazio");
            return null;
        }
        return movimentoDAO.buscarPorNome(nome);
    }

    /**
     * Lista todos os movimentos
     */
    public List<Movimento> listarTodosMovimentos() {
        return movimentoDAO.listarTodos();
    }

    /**
     * Lista movimentos por tipo de Saci
     */
    public List<Movimento> buscarMovimentosPorTipo(TipoSaci tipo) {
        if (tipo == null) {
            System.out.println("❌ Tipo não pode ser nulo");
            return List.of();
        }
        return movimentoDAO.buscarPorTipo(tipo);
    }

    /**
     * Lista movimentos por categoria
     */
    public List<Movimento> buscarMovimentosPorCategoria(CategoriaMovimento categoria) {
        if (categoria == null) {
            System.out.println("❌ Categoria não pode ser nula");
            return List.of();
        }
        return movimentoDAO.buscarPorCategoria(categoria);
    }

    /**
     * Atualiza dados do movimento
     */
    public boolean atualizarMovimento(Movimento movimento) {
        if (movimento == null || movimento.getId() <= 0) {
            System.out.println("❌ Movimento inválido para atualização");
            return false;
        }

        if (!movimentoDAO.existe(movimento.getId())) {
            System.out.println("❌ Movimento não encontrado");
            return false;
        }

        movimentoDAO.atualizar(movimento);
        System.out.println("✅ Movimento atualizado com sucesso!");
        return true;
    }

    /**
     * Deleta um movimento
     */
    public boolean deletarMovimento(int id) {
        if (id <= 0) {
            System.out.println("❌ ID deve ser maior que zero");
            return false;
        }

        if (!movimentoDAO.existe(id)) {
            System.out.println("❌ Movimento não encontrado");
            return false;
        }

        boolean deletado = movimentoDAO.deletar(id);
        if (deletado) {
            System.out.println("✅ Movimento deletado com sucesso!");
        }
        return deletado;
    }

    /**
     * Lista movimentos por compatibilidade com um Saci
     */
    public List<Movimento> buscarMovimentosCompativeis(TipoSaci tipoSaci) {
        List<Movimento> movimentosDoTipo = movimentoDAO.buscarPorTipo(tipoSaci);
        
        // Adicionar movimentos universais (tipo NEUTRO)
        List<Movimento> movimentosUniversais = movimentoDAO.buscarPorTipo(TipoSaci.NEUTRO);
        movimentosDoTipo.addAll(movimentosUniversais);
        
        return movimentosDoTipo;
    }

    /**
     * Verifica se um movimento pode ser usado por um Saci de determinado rank
     */
    public boolean movimentoDisponivelParaRank(int movimentoId, int rankSaci) {
        Movimento movimento = movimentoDAO.buscarPorId(movimentoId);
        if (movimento == null) {
            return false;
        }
        
        // Converter rank de Saci para número (assumindo NOVATO=1, VETERANO=2, etc.)
        return movimento.getRankMinimo() <= rankSaci;
    }

    /**
     * Valida os dados do movimento
     */
    private String validarDadosMovimento(String nome, TipoSaci tipo, CategoriaMovimento categoria,
                                        String testeAtaque, String efeito) {
        if (nome == null || nome.trim().isEmpty()) {
            return "Nome é obrigatório";
        }

        if (nome.trim().length() > 50) {
            return "Nome deve ter no máximo 50 caracteres";
        }

        if (tipo == null) {
            return "Tipo é obrigatório";
        }

        if (categoria == null) {
            return "Categoria é obrigatória";
        }

        if (testeAtaque != null && testeAtaque.length() > 100) {
            return "Teste de ataque deve ter no máximo 100 caracteres";
        }

        if (efeito != null && efeito.length() > 200) {
            return "Efeito deve ter no máximo 200 caracteres";
        }

        // Verificar se já existe movimento com esse nome
        if (movimentoDAO.buscarPorNome(nome) != null) {
            return "Já existe um movimento com esse nome";
        }

        return null; // Sem erros
    }

    /**
     * Calcula efetividade de um movimento contra um tipo específico
     */
    public double calcularEfetividade(TipoSaci tipoAtacante, TipoSaci tipoDefensor) {
        // Lógica simplificada de efetividade baseada nos tipos
        // Em um jogo real, isso seria uma tabela de tipos mais complexa
        
        if (tipoAtacante == tipoDefensor) {
            return 1.0; // Efetividade normal
        }
        
        // Algumas interações básicas
        switch (tipoAtacante) {
            case VALENTE:
                return (tipoDefensor == TipoSaci.SOMBRIO) ? 1.5 : 1.0;
            case SOMBRIO:
                return (tipoDefensor == TipoSaci.CATIVANTE) ? 1.5 : 1.0;
            case CATIVANTE:
                return (tipoDefensor == TipoSaci.CAOTICO) ? 1.5 : 1.0;
            case CAOTICO:
                return (tipoDefensor == TipoSaci.RIGIDO) ? 1.5 : 1.0;
            case RIGIDO:
                return (tipoDefensor == TipoSaci.VALENTE) ? 1.5 : 1.0;
            default:
                return 1.0;
        }
    }

    /**
     * Verifica se o movimento existe
     */
    public boolean movimentoExiste(int id) {
        return movimentoDAO.existe(id);
    }
}
