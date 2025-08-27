package service;

import dao.SaciDAO;
import model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Classe de Service para Saci
 * Contém a lógica de negócio e validações
 * Atua como intermediário entre a interface de usuário e o DAO
 */
public class SaciService {
    
    private SaciDAO saciDAO;

    public SaciService() {
        this.saciDAO = new SaciDAO();
    }

    /**
     * Cria um novo Saci com validações de negócio
     * @param nome Nome do Saci
     * @param tipo Tipo do Saci
     * @param temperamento Temperamento do Saci
     * @param rank Rank do Saci
     * @param pv Pontos de Vida
     * @param potencia Atributo Potência
     * @param agilidade Atributo Agilidade
     * @param resistencia Atributo Resistência
     * @param magia Atributo Magia
     * @param foco Atributo Foco
     * @param habilidadeEspecial Habilidade especial do Saci
     * @param descricao Descrição do Saci
     * @return Saci criado ou null se inválido
     */
    public Saci criarSaci(String nome, TipoSaci tipo, Temperamento temperamento, Rank rank,
                         int pv, int potencia, int agilidade, int resistencia, int magia, int foco,
                         String habilidadeEspecial, String descricao) {
        
        // Validações de negócio
        String erroValidacao = validarDadosSaci(nome, tipo, temperamento, rank, pv, 
                                               potencia, agilidade, resistencia, magia, foco);
        
        if (erroValidacao != null) {
            System.out.println("❌ Erro de validação: " + erroValidacao);
            return null;
        }

        // Verificar se já existe um Saci com o mesmo nome
        List<Saci> sacisExistentes = saciDAO.buscarPorNome(nome);
        if (!sacisExistentes.isEmpty()) {
            System.out.println("⚠️ Aviso: Já existe um Saci com nome similar: " + nome);
        }

        // Criar o Saci
        Saci novoSaci = new Saci(0, nome, tipo, temperamento, rank, pv, potencia, 
                                agilidade, resistencia, magia, foco, habilidadeEspecial, descricao);
        
        return saciDAO.criar(novoSaci);
    }

    /**
     * Busca um Saci por ID
     * @param id ID do Saci
     * @return Saci encontrado ou null
     */
    public Saci buscarSaciPorId(int id) {
        if (id <= 0) {
            System.out.println("❌ ID deve ser maior que zero");
            return null;
        }
        
        Saci saci = saciDAO.buscarPorId(id);
        if (saci == null) {
            System.out.println("❌ Saci não encontrado com ID: " + id);
        }
        
        return saci;
    }

    /**
     * Busca Sacis por nome
     * @param nome Nome do Saci
     * @return Lista de Sacis encontrados
     */
    public List<Saci> buscarSacisPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("❌ Nome não pode estar vazio");
            return new ArrayList<>();
        }
        
        List<Saci> sacis = saciDAO.buscarPorNome(nome);
        System.out.println("🔍 Encontrados " + sacis.size() + " Saci(s) com nome contendo: " + nome);
        
        return sacis;
    }

    /**
     * Busca Sacis por tipo
     * @param tipo Tipo do Saci
     * @return Lista de Sacis do tipo especificado
     */
    public List<Saci> buscarSacisPorTipo(TipoSaci tipo) {
        if (tipo == null) {
            System.out.println("❌ Tipo não pode ser nulo");
            return new ArrayList<>();
        }
        
        List<Saci> sacis = saciDAO.buscarPorTipo(tipo);
        System.out.println("🔍 Encontrados " + sacis.size() + " Saci(s) do tipo: " + tipo);
        
        return sacis;
    }

    /**
     * Busca Sacis por rank
     * @param rank Rank do Saci
     * @return Lista de Sacis do rank especificado
     */
    public List<Saci> buscarSacisPorRank(Rank rank) {
        if (rank == null) {
            System.out.println("❌ Rank não pode ser nulo");
            return new ArrayList<>();
        }
        
        List<Saci> sacis = saciDAO.buscarPorRank(rank);
        System.out.println("🔍 Encontrados " + sacis.size() + " Saci(s) do rank: " + rank);
        
        return sacis;
    }

    /**
     * Lista todos os Sacis
     * @return Lista de todos os Sacis
     */
    public List<Saci> listarTodosSacis() {
        List<Saci> sacis = saciDAO.listarTodos();
        System.out.println("📋 Total de Sacis cadastrados: " + sacis.size());
        return sacis;
    }

    /**
     * Atualiza um Saci existente
     * @param saci Saci com dados atualizados
     * @return true se atualizado com sucesso
     */
    public boolean atualizarSaci(Saci saci) {
        if (saci == null || saci.getId() <= 0) {
            System.out.println("❌ Saci inválido para atualização");
            return false;
        }

        // Verificar se o Saci existe
        Saci saciExistente = saciDAO.buscarPorId(saci.getId());
        if (saciExistente == null) {
            System.out.println("❌ Saci não encontrado para atualização. ID: " + saci.getId());
            return false;
        }

        // Validar os novos dados
        String erroValidacao = validarDadosSaci(saci.getNome(), saci.getTipo(), 
                                               saci.getTemperamento(), saci.getRank(),
                                               saci.getPv(), saci.getPotencia(), 
                                               saci.getAgilidade(), saci.getResistencia(),
                                               saci.getMagia(), saci.getFoco());
        
        if (erroValidacao != null) {
            System.out.println("❌ Erro de validação na atualização: " + erroValidacao);
            return false;
        }

        return saciDAO.atualizar(saci);
    }

    /**
     * Remove um Saci por ID
     * @param id ID do Saci a ser removido
     * @return true se removido com sucesso
     */
    public boolean removerSaci(int id) {
        if (id <= 0) {
            System.out.println("❌ ID deve ser maior que zero");
            return false;
        }

        // Verificar se o Saci existe antes de tentar remover
        Saci saci = saciDAO.buscarPorId(id);
        if (saci == null) {
            System.out.println("❌ Saci não encontrado para remoção. ID: " + id);
            return false;
        }

        System.out.println("⚠️ Removendo Saci: " + saci.getNome() + " (ID: " + id + ")");
        return saciDAO.deletar(id);
    }

    /**
     * Obtém estatísticas dos Sacis
     * @return String com estatísticas
     */
    public String obterEstatisticas() {
        return saciDAO.obterEstatisticas();
    }

    /**
     * Encontra os Sacis mais poderosos
     * @param limite Número máximo de Sacis a retornar
     * @return Lista dos Sacis mais poderosos
     */
    public List<Saci> obterSacisMaisPoderosos(int limite) {
        if (limite <= 0) {
            System.out.println("❌ Limite deve ser maior que zero");
            return new ArrayList<>();
        }

        return saciDAO.listarTodos().stream()
                     .sorted((s1, s2) -> Integer.compare(s2.calcularPoderTotal(), s1.calcularPoderTotal()))
                     .limit(limite)
                     .collect(Collectors.toList());
    }

    /**
     * Valida os dados básicos de um Saci
     * @return String com erro ou null se válido
     */
    private String validarDadosSaci(String nome, TipoSaci tipo, Temperamento temperamento, 
                                   Rank rank, int pv, int potencia, int agilidade, 
                                   int resistencia, int magia, int foco) {
        
        // Validar nome
        if (nome == null || nome.trim().isEmpty()) {
            return "Nome não pode estar vazio";
        }
        
        if (nome.length() > 50) {
            return "Nome não pode ter mais de 50 caracteres";
        }

        // Validar enums
        if (tipo == null) {
            return "Tipo não pode ser nulo";
        }
        
        if (temperamento == null) {
            return "Temperamento não pode ser nulo";
        }
        
        if (rank == null) {
            return "Rank não pode ser nulo";
        }

        // Validar atributos (devem ser positivos e dentro de limites razoáveis)
        if (pv <= 0 || pv > 200) {
            return "PV deve estar entre 1 e 200";
        }
        
        if (potencia < 0 || potencia > 50) {
            return "Potência deve estar entre 0 e 50";
        }
        
        if (agilidade < 0 || agilidade > 50) {
            return "Agilidade deve estar entre 0 e 50";
        }
        
        if (resistencia < 0 || resistencia > 50) {
            return "Resistência deve estar entre 0 e 50";
        }
        
        if (magia < 0 || magia > 50) {
            return "Magia deve estar entre 0 e 50";
        }
        
        if (foco < 0 || foco > 50) {
            return "Foco deve estar entre 0 e 50";
        }

        return null; // Tudo válido
    }
}
