package dao;

import model.Cacador;
import model.ClasseCacador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO (Data Access Object) para gerenciar Caçadores
 */
public class CacadorDAO {
    private Map<Integer, Cacador> cacadores = new HashMap<>();
    private int proximoId = 1;

    public CacadorDAO() {
        carregarCacadoresExemplo();
    }

    /**
     * Cria um novo caçador
     */
    public Cacador criar(Cacador cacador) {
        cacador.setId(proximoId++);
        cacadores.put(cacador.getId(), cacador);
        return cacador;
    }

    /**
     * Busca um caçador por ID
     */
    public Cacador buscarPorId(int id) {
        return cacadores.get(id);
    }

    /**
     * Busca um caçador por nome
     */
    public Cacador buscarPorNome(String nome) {
        return cacadores.values().stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    /**
     * Lista todos os caçadores
     */
    public List<Cacador> listarTodos() {
        return new ArrayList<>(cacadores.values());
    }

    /**
     * Atualiza um caçador
     */
    public void atualizar(Cacador cacador) {
        if (cacadores.containsKey(cacador.getId())) {
            cacadores.put(cacador.getId(), cacador);
        }
    }

    /**
     * Deleta um caçador por ID
     */
    public boolean deletar(int id) {
        return cacadores.remove(id) != null;
    }

    /**
     * Verifica se existe um caçador com o ID
     */
    public boolean existe(int id) {
        return cacadores.containsKey(id);
    }

    /**
     * Lista caçadores por classe
     */
    public List<Cacador> buscarPorClasse(ClasseCacador classe) {
        return cacadores.values().stream()
                .filter(c -> c.getClasse() == classe)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * Carrega caçadores exemplo baseados na documentação do jogo
     */
    private void carregarCacadoresExemplo() {
        // Exemplo de Domador
        Cacador domador = new Cacador();
        domador.setNome("João Silva");
        domador.setIdade(25);
        domador.setClasse(ClasseCacador.DOMADOR);
        domador.setNivel(1);
        domador.setExperiencia(0);
        domador.adicionarSaci(1); // Saci Comum
        criar(domador);

        // Exemplo de Pesquisador
        Cacador pesquisador = new Cacador();
        pesquisador.setNome("Maria Santos");
        pesquisador.setIdade(28);
        pesquisador.setClasse(ClasseCacador.PESQUISADOR);
        pesquisador.setNivel(2);
        pesquisador.setExperiencia(150);
        criar(pesquisador);

        // Exemplo de Torcedor
        Cacador torcedor = new Cacador();
        torcedor.setNome("Carlos Lima");
        torcedor.setIdade(22);
        torcedor.setClasse(ClasseCacador.TORCEDOR);
        torcedor.setNivel(1);
        torcedor.setExperiencia(50);
        criar(torcedor);
    }
}
