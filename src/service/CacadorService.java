package service;

import dao.CacadorDAO;
import model.Cacador;
import model.ClasseCacador;
import java.util.List;

/**
 * Classe de Service para Caçador
 * Contém a lógica de negócio e validações para caçadores
 */
public class CacadorService {
    
    private CacadorDAO cacadorDAO;

    public CacadorService() {
        this.cacadorDAO = new CacadorDAO();
    }

    /**
     * Cria um novo Caçador com validações de negócio
     */
    public Cacador criarCacador(String nome, int idade, ClasseCacador classe, String sonho) {
        // Validações de negócio
        String erroValidacao = validarDadosCacador(nome, idade, classe, sonho);
        
        if (erroValidacao != null) {
            System.out.println("❌ Erro de validação: " + erroValidacao);
            return null;
        }

        Cacador cacador = new Cacador(nome, classe, sonho);
        cacador.setIdade(idade);
        
        return cacadorDAO.criar(cacador);
    }

    /**
     * Busca um caçador por ID
     */
    public Cacador buscarCacadorPorId(int id) {
        if (id <= 0) {
            System.out.println("❌ ID deve ser maior que zero");
            return null;
        }
        return cacadorDAO.buscarPorId(id);
    }

    /**
     * Busca um caçador por nome
     */
    public Cacador buscarCacadorPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("❌ Nome não pode ser vazio");
            return null;
        }
        return cacadorDAO.buscarPorNome(nome);
    }

    /**
     * Lista todos os caçadores
     */
    public List<Cacador> listarTodosCacadores() {
        return cacadorDAO.listarTodos();
    }

    /**
     * Lista caçadores por classe
     */
    public List<Cacador> buscarCacadoresPorClasse(ClasseCacador classe) {
        if (classe == null) {
            System.out.println("❌ Classe não pode ser nula");
            return List.of();
        }
        return cacadorDAO.buscarPorClasse(classe);
    }

    /**
     * Atualiza dados do caçador
     */
    public boolean atualizarCacador(Cacador cacador) {
        if (cacador == null || cacador.getId() <= 0) {
            System.out.println("❌ Caçador inválido para atualização");
            return false;
        }

        if (!cacadorDAO.existe(cacador.getId())) {
            System.out.println("❌ Caçador não encontrado");
            return false;
        }

        cacadorDAO.atualizar(cacador);
        System.out.println("✅ Caçador atualizado com sucesso!");
        return true;
    }

    /**
     * Deleta um caçador
     */
    public boolean deletarCacador(int id) {
        if (id <= 0) {
            System.out.println("❌ ID deve ser maior que zero");
            return false;
        }

        if (!cacadorDAO.existe(id)) {
            System.out.println("❌ Caçador não encontrado");
            return false;
        }

        boolean deletado = cacadorDAO.deletar(id);
        if (deletado) {
            System.out.println("✅ Caçador deletado com sucesso!");
        }
        return deletado;
    }

    /**
     * Adiciona um Saci ao caçador
     */
    public boolean adicionarSaciAoCacador(int cacadorId, int saciId) {
        Cacador cacador = cacadorDAO.buscarPorId(cacadorId);
        if (cacador == null) {
            System.out.println("❌ Caçador não encontrado");
            return false;
        }

        // Verificar se o caçador já tem o máximo de Sacis (6)
        if (cacador.getSacis() != null && cacador.getSacis().size() >= 6) {
            System.out.println("❌ Caçador já possui o máximo de 6 Sacis");
            return false;
        }

        cacador.adicionarSaci(saciId);
        cacadorDAO.atualizar(cacador);
        System.out.println("✅ Saci adicionado ao caçador com sucesso!");
        return true;
    }

    /**
     * Remove um Saci do caçador
     */
    public boolean removerSaciDoCacador(int cacadorId, int saciId) {
        Cacador cacador = cacadorDAO.buscarPorId(cacadorId);
        if (cacador == null) {
            System.out.println("❌ Caçador não encontrado");
            return false;
        }

        cacador.removerSaci(saciId);
        cacadorDAO.atualizar(cacador);
        System.out.println("✅ Saci removido do caçador com sucesso!");
        return true;
    }

    /**
     * Valida os dados do caçador
     */
    private String validarDadosCacador(String nome, int idade, ClasseCacador classe, String sonho) {
        if (nome == null || nome.trim().isEmpty()) {
            return "Nome é obrigatório";
        }

        if (nome.trim().length() > 100) {
            return "Nome deve ter no máximo 100 caracteres";
        }

        if (idade < 10 || idade > 100) {
            return "Idade deve estar entre 10 e 100 anos";
        }

        if (classe == null) {
            return "Classe é obrigatória";
        }

        if (sonho != null && sonho.length() > 500) {
            return "Sonho deve ter no máximo 500 caracteres";
        }

        // Verificar se já existe caçador com esse nome
        if (cacadorDAO.buscarPorNome(nome) != null) {
            return "Já existe um caçador com esse nome";
        }

        return null; // Sem erros
    }

    /**
     * Calcula nível baseado na experiência
     */
    public int calcularNivel(int experiencia) {
        // Cada 100 pontos de experiência = 1 nível
        return Math.max(1, (experiencia / 100) + 1);
    }

    /**
     * Adiciona experiência ao caçador
     */
    public boolean adicionarExperiencia(int cacadorId, int experiencia) {
        Cacador cacador = cacadorDAO.buscarPorId(cacadorId);
        if (cacador == null) {
            System.out.println("❌ Caçador não encontrado");
            return false;
        }

        int experienciaAtual = cacador.getExperiencia();
        int novaExperiencia = experienciaAtual + experiencia;
        
        int nivelAnterior = cacador.getNivel();
        int novoNivel = calcularNivel(novaExperiencia);

        cacador.setExperiencia(novaExperiencia);
        cacador.setNivel(novoNivel);

        cacadorDAO.atualizar(cacador);

        if (novoNivel > nivelAnterior) {
            System.out.println("🎉 " + cacador.getNome() + " subiu para o nível " + novoNivel + "!");
        }

        System.out.println("✅ Experiência adicionada com sucesso!");
        return true;
    }

    /**
     * Verifica se o caçador existe
     */
    public boolean cacadorExiste(int id) {
        return cacadorDAO.existe(id);
    }
}
