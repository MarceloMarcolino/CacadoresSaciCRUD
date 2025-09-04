package dao;

import java.util.*;
import model.*;

/**
 * DAO (Data Access Object) para operações CRUD de Saci
 * Esta classe simula um banco de dados usando uma lista em memória
 * É ideal para fins educacionais e demonstração de conceitos CRUD
 */
public class SaciDAO {
    
    // Lista que simula nossa "tabela" de Sacis no banco de dados
    private List<Saci> sacis;
    private int proximoId;

    public SaciDAO() {
        this.sacis = new ArrayList<>();
        this.proximoId = 1;
        // Inicializar com alguns Sacis de exemplo
        inicializarDadosExemplo();
    }

    /**
     * CREATE - Adiciona um novo Saci
     * @param saci O Saci a ser adicionado
     * @return O Saci adicionado com ID gerado
     */
    public Saci criar(Saci saci) {
        if (saci == null) {
            throw new IllegalArgumentException("Saci não pode ser nulo");
        }
        
        saci.setId(proximoId++);
        sacis.add(saci);
        System.out.println("✅ Saci criado com sucesso! ID: " + saci.getId());
        return saci;
    }

    /**
     * READ - Busca um Saci por ID
     * @param id ID do Saci
     * @return Saci encontrado ou null se não existir
     */
    public Saci buscarPorId(int id) {
        return sacis.stream()
                   .filter(saci -> saci.getId() == id)
                   .findFirst()
                   .orElse(null);
    }

    /**
     * READ - Busca Sacis por nome (busca parcial)
     * @param nome Nome ou parte do nome do Saci
     * @return Lista de Sacis que contêm o nome especificado
     */
    public List<Saci> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return sacis.stream()
                   .filter(saci -> saci.getNome().toLowerCase()
                                      .contains(nome.toLowerCase()))
                   .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * READ - Busca Sacis por tipo
     * @param tipo Tipo do Saci
     * @return Lista de Sacis do tipo especificado
     */
    public List<Saci> buscarPorTipo(TipoSaci tipo) {
        if (tipo == null) {
            return new ArrayList<>();
        }
        
        return sacis.stream()
                   .filter(saci -> saci.getTipo() == tipo)
                   .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * READ - Busca Sacis por rank
     * @param rank Rank do Saci
     * @return Lista de Sacis do rank especificado
     */
    public List<Saci> buscarPorRank(Rank rank) {
        if (rank == null) {
            return new ArrayList<>();
        }
        
        return sacis.stream()
                   .filter(saci -> saci.getRank() == rank)
                   .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * READ - Lista todos os Sacis
     * @return Lista com todos os Sacis
     */
    public List<Saci> listarTodos() {
        return new ArrayList<>(sacis); // Retorna uma cópia para evitar modificações externas
    }

    /**
     * UPDATE - Atualiza um Saci existente
     * @param saci Saci com dados atualizados
     * @return true se atualizado com sucesso, false caso contrário
     */
    public boolean atualizar(Saci saci) {
        if (saci == null || saci.getId() <= 0) {
            return false;
        }

        for (int i = 0; i < sacis.size(); i++) {
            if (sacis.get(i).getId() == saci.getId()) {
                sacis.set(i, saci);
                System.out.println("✅ Saci atualizado com sucesso! ID: " + saci.getId());
                return true;
            }
        }
        
        System.out.println("❌ Saci não encontrado para atualização. ID: " + saci.getId());
        return false;
    }

    /**
     * DELETE - Remove um Saci por ID
     * @param id ID do Saci a ser removido
     * @return true se removido com sucesso, false caso contrário
     */
    public boolean deletar(int id) {
        boolean removido = sacis.removeIf(saci -> saci.getId() == id);
        
        if (removido) {
            System.out.println("✅ Saci removido com sucesso! ID: " + id);
        } else {
            System.out.println("❌ Saci não encontrado para remoção. ID: " + id);
        }
        
        return removido;
    }

    /**
     * Método utilitário para contar total de Sacis
     * @return Número total de Sacis cadastrados
     */
    public int contarTotal() {
        return sacis.size();
    }

    /**
     * Método para obter estatísticas básicas
     * @return String com estatísticas dos Sacis
     */
    public String obterEstatisticas() {
        if (sacis.isEmpty()) {
            return "📊 Nenhum Saci cadastrado.";
        }

        Map<TipoSaci, Long> contagemPorTipo = new HashMap<>();
        Map<Rank, Long> contagemPorRank = new HashMap<>();
        
        for (Saci saci : sacis) {
            contagemPorTipo.merge(saci.getTipo(), 1L, Long::sum);
            contagemPorRank.merge(saci.getRank(), 1L, Long::sum);
        }

        StringBuilder stats = new StringBuilder();
        stats.append("📊 ESTATÍSTICAS DOS SACIS:\n");
        stats.append("Total de Sacis: ").append(sacis.size()).append("\n\n");
        
        stats.append("Por Tipo:\n");
        contagemPorTipo.forEach((tipo, qtd) -> 
            stats.append("  ").append(tipo).append(": ").append(qtd).append("\n"));
        
        stats.append("\nPor Rank:\n");
        contagemPorRank.forEach((rank, qtd) -> 
            stats.append("  ").append(rank).append(": ").append(qtd).append("\n"));

        return stats.toString();
    }

    /**
     * Inicializa alguns Sacis de exemplo baseados no jogo
     */
    private void inicializarDadosExemplo() {
        // Curupira - Saci Valente
        Saci curupira = new Saci("Curupira", TipoSaci.VALENTE, Temperamento.AGRESSIVO, Rank.DUAS_ESTRELAS);
        curupira.setPv(45);
        curupira.setPotencia(18);
        curupira.setAgilidade(15);
        curupira.setResistencia(16);
        curupira.setMagia(8);
        curupira.setFoco(12);
        curupira.setHabilidadeEspecial(HabilidadeSaci.INTIMIDACAO);
        curupira.setDescricao("Guardian feroz das florestas brasileiras");
        criar(curupira);

        // Boto - Saci Cativante
        Saci boto = new Saci("Boto", TipoSaci.CATIVANTE, Temperamento.CALMO, Rank.TRES_ESTRELAS);
        boto.setPv(50);
        boto.setPotencia(12);
        boto.setAgilidade(14);
        boto.setResistencia(13);
        boto.setMagia(20);
        boto.setFoco(16);
        boto.setHabilidadeEspecial(HabilidadeSaci.ESCUDO_AMIGO);
        boto.setDescricao("Saci encantador dos rios amazônicos");
        criar(boto);

        // Caipora - Saci Neutro
        Saci caipora = new Saci("Caipora", TipoSaci.NEUTRO, Temperamento.QUIETO, Rank.UMA_ESTRELA);
        caipora.setPv(35);
        caipora.setPotencia(14);
        caipora.setAgilidade(16);
        caipora.setResistencia(12);
        caipora.setMagia(10);
        caipora.setFoco(11);
        caipora.setHabilidadeEspecial(HabilidadeSaci.FLUTUAR);
        caipora.setDescricao("Protetor silencioso dos animais");
        criar(caipora);

        // Resetar o contador para começar do 1 novamente
        proximoId = 1;
        // Reajustar IDs dos sacis de exemplo
        for (int i = 0; i < sacis.size(); i++) {
            sacis.get(i).setId(i + 1);
        }
        proximoId = sacis.size() + 1;
    }
}
