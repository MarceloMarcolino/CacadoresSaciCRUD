package view;

import java.util.List;
import java.util.Scanner;
import model.*;
import service.CacadorService;
import service.InventarioService;
import service.MovimentoService;
import service.SaciService;

/**
 * Classe View para interface com usuário do CRUD de Sacis
 * Interface simples em console para fins educacionais
 */
public class SaciView {
    
    private Scanner scanner;
    private SaciService saciService;
    private CacadorService cacadorService;
    private MovimentoService movimentoService;
    private InventarioService inventarioService;

    public SaciView() {
        this.scanner = new Scanner(System.in);
        this.saciService = new SaciService();
        this.cacadorService = new CacadorService();
        this.movimentoService = new MovimentoService();
        this.inventarioService = new InventarioService();
    }

    /**
     * Método principal que exibe o menu e controla a aplicação
     */
    public void iniciar() {
        exibirBemVindo();
        
        boolean continuar = true;
        while (continuar) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    menuSacis();
                    break;
                case 2:
                    menuCacadores();
                    break;
                case 3:
                    menuMovimentos();
                    break;
                case 4:
                    menuInventarios();
                    break;
                case 5:
                    exibirEstatisticasGerais();
                    break;
                case 0:
                    continuar = false;
                    exibirDespedida();
                    break;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
            }
            
            if (continuar) {
                pausar();
            }
        }
        
        scanner.close();
    }

    /**
     * Exibe mensagem de boas-vindas
     */
    private void exibirBemVindo() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                 🌟 CAÇADORES DE SACI 🌟                  ║");
        System.out.println("║                    Sistema CRUD                          ║");
        System.out.println("║                                                          ║");
        System.out.println("║        Gerencie sua coleção de Sacis místicos!          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Exibe o menu principal atualizado
     */
    private void exibirMenuPrincipal() {
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                      📋 MENU PRINCIPAL                   │");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        System.out.println("│  1  - Gerenciar Sacis                                   │");
        System.out.println("│  2  - Gerenciar Caçadores                               │");
        System.out.println("│  3  - Gerenciar Movimentos                              │");
        System.out.println("│  4  - Gerenciar Inventários                             │");
        System.out.println("│  5  - Estatísticas Gerais                               │");
        System.out.println("│                                                          │");
        System.out.println("│  0  - Sair                                              │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Menu de gerenciamento de Sacis
     */
    private void menuSacis() {
        boolean voltarMenu = false;
        while (!voltarMenu) {
            System.out.println("┌──────────────────────────────────────────────────────────┐");
            System.out.println("│                    🌟 MENU DOS SACIS                     │");
            System.out.println("├──────────────────────────────────────────────────────────┤");
            System.out.println("│  1  - Criar novo Saci                                   │");
            System.out.println("│  2  - Buscar Saci por ID                                │");
            System.out.println("│  3  - Buscar Saci por Nome                              │");
            System.out.println("│  4  - Buscar Sacis por Tipo                             │");
            System.out.println("│  5  - Buscar Sacis por Rank                             │");
            System.out.println("│  6  - Listar todos os Sacis                             │");
            System.out.println("│  7  - Atualizar Saci                                    │");
            System.out.println("│  8  - Remover Saci                                      │");
            System.out.println("│  9  - Estatísticas dos Sacis                            │");
            System.out.println("│  10 - Sacis mais poderosos                              │");
            System.out.println("│                                                          │");
            System.out.println("│  0  - Voltar ao menu principal                          │");
            System.out.println("└──────────────────────────────────────────────────────────┘");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1: criarNovoSaci(); break;
                case 2: buscarSaciPorId(); break;
                case 3: buscarSaciPorNome(); break;
                case 4: buscarSaciPorTipo(); break;
                case 5: buscarSaciPorRank(); break;
                case 6: listarTodosSacis(); break;
                case 7: atualizarSaci(); break;
                case 8: removerSaci(); break;
                case 9: exibirEstatisticas(); break;
                case 10: exibirSacisMaisPoderosos(); break;
                case 0: voltarMenu = true; break;
                default: System.out.println("❌ Opção inválida!");
            }
            if (!voltarMenu) pausar();
        }
    }

    /**
     * Menu de gerenciamento de Caçadores
     */
    private void menuCacadores() {
        boolean voltarMenu = false;
        while (!voltarMenu) {
            System.out.println("┌──────────────────────────────────────────────────────────┐");
            System.out.println("│                  👤 MENU DOS CAÇADORES                   │");
            System.out.println("├──────────────────────────────────────────────────────────┤");
            System.out.println("│  1  - Criar novo Caçador                                │");
            System.out.println("│  2  - Buscar Caçador por ID                             │");
            System.out.println("│  3  - Buscar Caçador por Nome                           │");
            System.out.println("│  4  - Buscar Caçadores por Classe                       │");
            System.out.println("│  5  - Listar todos os Caçadores                         │");
            System.out.println("│  6  - Atualizar Caçador                                 │");
            System.out.println("│  7  - Remover Caçador                                   │");
            System.out.println("│  8  - Adicionar Saci ao Caçador                         │");
            System.out.println("│  9  - Remover Saci do Caçador                           │");
            System.out.println("│  10 - Adicionar Experiência                             │");
            System.out.println("│                                                          │");
            System.out.println("│  0  - Voltar ao menu principal                          │");
            System.out.println("└──────────────────────────────────────────────────────────┘");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1: criarNovoCacador(); break;
                case 2: buscarCacadorPorId(); break;
                case 3: buscarCacadorPorNome(); break;
                case 4: buscarCacadoresPorClasse(); break;
                case 5: listarTodosCacadores(); break;
                case 6: atualizarCacador(); break;
                case 7: removerCacador(); break;
                case 8: adicionarSaciAoCacador(); break;
                case 9: removerSaciDoCacador(); break;
                case 10: adicionarExperienciaAoCacador(); break;
                case 0: voltarMenu = true; break;
                default: System.out.println("❌ Opção inválida!");
            }
            if (!voltarMenu) pausar();
        }
    }

    /**
     * Menu de gerenciamento de Movimentos
     */
    private void menuMovimentos() {
        boolean voltarMenu = false;
        while (!voltarMenu) {
            System.out.println("┌──────────────────────────────────────────────────────────┐");
            System.out.println("│                   ⚔️ MENU DOS MOVIMENTOS                 │");
            System.out.println("├──────────────────────────────────────────────────────────┤");
            System.out.println("│  1  - Criar novo Movimento                              │");
            System.out.println("│  2  - Buscar Movimento por ID                           │");
            System.out.println("│  3  - Buscar Movimento por Nome                         │");
            System.out.println("│  4  - Buscar Movimentos por Tipo                        │");
            System.out.println("│  5  - Buscar Movimentos por Categoria                   │");
            System.out.println("│  6  - Listar todos os Movimentos                        │");
            System.out.println("│  7  - Atualizar Movimento                               │");
            System.out.println("│  8  - Remover Movimento                                 │");
            System.out.println("│  9  - Movimentos compatíveis com Tipo                   │");
            System.out.println("│                                                          │");
            System.out.println("│  0  - Voltar ao menu principal                          │");
            System.out.println("└──────────────────────────────────────────────────────────┘");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1: criarNovoMovimento(); break;
                case 2: buscarMovimentoPorId(); break;
                case 3: buscarMovimentoPorNome(); break;
                case 4: buscarMovimentosPorTipo(); break;
                case 5: buscarMovimentosPorCategoria(); break;
                case 6: listarTodosMovimentos(); break;
                case 7: atualizarMovimento(); break;
                case 8: removerMovimento(); break;
                case 9: buscarMovimentosCompativeis(); break;
                case 0: voltarMenu = true; break;
                default: System.out.println("❌ Opção inválida!");
            }
            if (!voltarMenu) pausar();
        }
    }

    /**
     * Exibe estatísticas gerais do sistema
     */
    private void exibirEstatisticasGerais() {
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                   📊 ESTATÍSTICAS GERAIS                 │");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        
        // Estatísticas dos Sacis
        List<Saci> sacis = saciService.listarTodosSacis();
        System.out.println("│ Total de Sacis: " + String.format("%-40d", sacis.size()) + " │");
        
        // Estatísticas dos Caçadores
        List<Cacador> cacadores = cacadorService.listarTodosCacadores();
        System.out.println("│ Total de Caçadores: " + String.format("%-36d", cacadores.size()) + " │");
        
        // Estatísticas dos Movimentos
        List<Movimento> movimentos = movimentoService.listarTodosMovimentos();
        System.out.println("│ Total de Movimentos: " + String.format("%-35d", movimentos.size()) + " │");
        
        System.out.println("└──────────────────────────────────────────────────────────┘");
    }

    /**
     * Lê a opção do usuário com tratamento de erro
     */
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Opção inválida
        }
    }

    /**
     * Cria um novo Saci
     */
    private void criarNovoSaci() {
        System.out.println("\n🆕 === CRIAR NOVO SACI ===");
        
        try {
            System.out.print("📝 Nome do Saci: ");
            String nome = scanner.nextLine().trim();
            
            TipoSaci tipo = selecionarTipo();
            if (tipo == null) return;
            
            Temperamento temperamento = selecionarTemperamento();
            if (temperamento == null) return;
            
            Rank rank = selecionarRank();
            if (rank == null) return;
            
            System.out.println("\n⚡ === ATRIBUTOS DO SACI ===");
            
            System.out.print("❤️  PV (Pontos de Vida): ");
            int pv = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("💪 Potência: ");
            int potencia = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("🏃 Agilidade: ");
            int agilidade = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("🛡️  Resistência: ");
            int resistencia = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("✨ Magia: ");
            int magia = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("🎯 Foco: ");
            int foco = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("🌟 Habilidade Especial (usando padrão FOTOSSINTESE): ");
            String habilidadeTexto = scanner.nextLine().trim();
            HabilidadeSaci habilidade = HabilidadeSaci.FOTOSSINTESE; // Padrão por enquanto
            
            System.out.print("📖 Descrição: ");
            String descricao = scanner.nextLine().trim();
            
            Saci novoSaci = saciService.criarSaci(nome, tipo, temperamento, rank, pv, 
                                                 potencia, agilidade, resistencia, magia, foco,
                                                 habilidade, descricao);
            
            if (novoSaci != null) {
                System.out.println("\n🎉 Saci criado com sucesso!");
                exibirDetalheSaci(novoSaci);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: Digite apenas números para os atributos!");
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
    }

    /**
     * Permite ao usuário selecionar um tipo de Saci
     */
    private TipoSaci selecionarTipo() {
        System.out.println("\n🎭 === TIPOS DE SACI ===");
        TipoSaci[] tipos = TipoSaci.values();
        
        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + " - " + tipos[i]);
        }
        
        System.out.print("> Escolha o tipo (1-" + tipos.length + "): ");
        
        try {
            int escolha = Integer.parseInt(scanner.nextLine().trim());
            if (escolha >= 1 && escolha <= tipos.length) {
                return tipos[escolha - 1];
            } else {
                System.out.println("❌ Opção inválida!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite apenas números!");
            return null;
        }
    }

    /**
     * Permite ao usuário selecionar um temperamento
     */
    private Temperamento selecionarTemperamento() {
        System.out.println("\n😤 === TEMPERAMENTOS ===");
        Temperamento[] temperamentos = Temperamento.values();
        
        for (int i = 0; i < temperamentos.length; i++) {
            System.out.println((i + 1) + " - " + temperamentos[i]);
        }
        
        System.out.print("> Escolha o temperamento (1-" + temperamentos.length + "): ");
        
        try {
            int escolha = Integer.parseInt(scanner.nextLine().trim());
            if (escolha >= 1 && escolha <= temperamentos.length) {
                return temperamentos[escolha - 1];
            } else {
                System.out.println("❌ Opção inválida!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite apenas números!");
            return null;
        }
    }

    /**
     * Permite ao usuário selecionar um rank
     */
    private Rank selecionarRank() {
        System.out.println("\n⭐ === RANKS ===");
        Rank[] ranks = Rank.values();
        
        for (int i = 0; i < ranks.length; i++) {
            System.out.println((i + 1) + " - " + ranks[i]);
        }
        
        System.out.print("> Escolha o rank (1-" + ranks.length + "): ");
        
        try {
            int escolha = Integer.parseInt(scanner.nextLine().trim());
            if (escolha >= 1 && escolha <= ranks.length) {
                return ranks[escolha - 1];
            } else {
                System.out.println("❌ Opção inválida!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite apenas números!");
            return null;
        }
    }

    /**
     * Busca Saci por ID
     */
    private void buscarSaciPorId() {
        System.out.println("\n🔍 === BUSCAR SACI POR ID ===");
        
        try {
            System.out.print("🔢 Digite o ID do Saci: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Saci saci = saciService.buscarSaciPorId(id);
            if (saci != null) {
                System.out.println("\n✅ Saci encontrado:");
                exibirDetalheSaci(saci);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite apenas números para o ID!");
        }
    }

    /**
     * Busca Saci por nome
     */
    private void buscarSaciPorNome() {
        System.out.println("\n🔍 === BUSCAR SACI POR NOME ===");
        
        System.out.print("📝 Digite o nome (ou parte do nome): ");
        String nome = scanner.nextLine().trim();
        
        List<Saci> sacis = saciService.buscarSacisPorNome(nome);
        exibirListaSacis(sacis);
    }

    /**
     * Busca Sacis por tipo
     */
    private void buscarSaciPorTipo() {
        System.out.println("\n🔍 === BUSCAR SACIS POR TIPO ===");
        
        TipoSaci tipo = selecionarTipo();
        if (tipo != null) {
            List<Saci> sacis = saciService.buscarSacisPorTipo(tipo);
            exibirListaSacis(sacis);
        }
    }

    /**
     * Busca Sacis por rank
     */
    private void buscarSaciPorRank() {
        System.out.println("\n🔍 === BUSCAR SACIS POR RANK ===");
        
        Rank rank = selecionarRank();
        if (rank != null) {
            List<Saci> sacis = saciService.buscarSacisPorRank(rank);
            exibirListaSacis(sacis);
        }
    }

    /**
     * Lista todos os Sacis
     */
    private void listarTodosSacis() {
        System.out.println("\n📋 === TODOS OS SACIS ===");
        
        List<Saci> sacis = saciService.listarTodosSacis();
        exibirListaSacis(sacis);
    }

    /**
     * Atualiza um Saci existente
     */
    private void atualizarSaci() {
        System.out.println("\n✏️ === ATUALIZAR SACI ===");
        
        try {
            System.out.print("🔢 Digite o ID do Saci a atualizar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Saci saciExistente = saciService.buscarSaciPorId(id);
            if (saciExistente == null) {
                return; // Mensagem já exibida no service
            }
            
            System.out.println("\n📄 Dados atuais:");
            exibirDetalheSaci(saciExistente);
            
            System.out.println("\n📝 Digite os novos dados (deixe vazio para manter o atual):");
            
            // Nome
            System.out.print("📝 Nome [" + saciExistente.getNome() + "]: ");
            String novoNome = scanner.nextLine().trim();
            if (!novoNome.isEmpty()) {
                saciExistente.setNome(novoNome);
            }
            
            // Habilidade Especial (comentado temporariamente - requer seleção de enum)
            // System.out.print("🌟 Habilidade Especial [" + saciExistente.getHabilidadeEspecial() + "]: ");
            // String novaHabilidade = scanner.nextLine().trim();
            // if (!novaHabilidade.isEmpty()) {
            //     saciExistente.setHabilidadeEspecial(novaHabilidade);
            // }
            
            // Descrição
            System.out.print("📖 Descrição [" + saciExistente.getDescricao() + "]: ");
            String novaDescricao = scanner.nextLine().trim();
            if (!novaDescricao.isEmpty()) {
                saciExistente.setDescricao(novaDescricao);
            }
            
            boolean sucesso = saciService.atualizarSaci(saciExistente);
            if (sucesso) {
                System.out.println("\n🎉 Saci atualizado com sucesso!");
                exibirDetalheSaci(saciExistente);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite apenas números para o ID!");
        }
    }

    /**
     * Remove um Saci
     */
    private void removerSaci() {
        System.out.println("\n🗑️ === REMOVER SACI ===");
        
        try {
            System.out.print("🔢 Digite o ID do Saci a remover: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Saci saci = saciService.buscarSaciPorId(id);
            if (saci == null) {
                return; // Mensagem já exibida no service
            }
            
            System.out.println("\n📄 Saci a ser removido:");
            exibirDetalheSaci(saci);
            
            System.out.print("\n⚠️ Confirma a remoção? (S/N): ");
            String confirmacao = scanner.nextLine().trim().toUpperCase();
            
            if ("S".equals(confirmacao) || "SIM".equals(confirmacao)) {
                boolean sucesso = saciService.removerSaci(id);
                if (sucesso) {
                    System.out.println("\n🎉 Saci removido com sucesso!");
                }
            } else {
                System.out.println("❌ Remoção cancelada.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite apenas números para o ID!");
        }
    }

    /**
     * Exibe estatísticas
     */
    private void exibirEstatisticas() {
        System.out.println("\n" + saciService.obterEstatisticas());
    }

    /**
     * Exibe os Sacis mais poderosos
     */
    private void exibirSacisMaisPoderosos() {
        System.out.println("\n🏆 === SACIS MAIS PODEROSOS ===");
        
        try {
            System.out.print("🔢 Quantos Sacis exibir (máximo): ");
            int limite = Integer.parseInt(scanner.nextLine().trim());
            
            List<Saci> sacisPoderosos = saciService.obterSacisMaisPoderosos(limite);
            
            if (sacisPoderosos.isEmpty()) {
                System.out.println("❌ Nenhum Saci encontrado.");
                return;
            }
            
            System.out.println("\n🏆 TOP " + sacisPoderosos.size() + " SACIS MAIS PODEROSOS:");
            System.out.println("═══════════════════════════════════════════════════════");
            
            for (int i = 0; i < sacisPoderosos.size(); i++) {
                Saci saci = sacisPoderosos.get(i);
                System.out.printf("🥇 %d° - %s (Poder Total: %d)%n", 
                    (i + 1), saci.getNome(), saci.calcularPoderTotal());
                System.out.printf("    Tipo: %s | Rank: %s%n", saci.getTipo(), saci.getRank());
                System.out.println("    ───────────────────────────────────────────────");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite apenas números!");
        }
    }

    /**
     * Exibe detalhes de um Saci
     */
    private void exibirDetalheSaci(Saci saci) {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.printf("│ 🆔 ID: %-4d  📝 Nome: %-35s │%n", saci.getId(), saci.getNome());
        System.out.printf("│ 🎭 Tipo: %-15s  😤 Temperamento: %-15s │%n", saci.getTipo(), saci.getTemperamento());
        System.out.printf("│ ⭐ Rank: %-15s  💪 Poder Total: %-10d │%n", saci.getRank(), saci.calcularPoderTotal());
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│                        ⚡ ATRIBUTOS ⚡                       │");
        System.out.printf("│ ❤️  PV: %-3d   💪 Potência: %-3d   🏃 Agilidade: %-3d │%n", 
                         saci.getPv(), saci.getPotencia(), saci.getAgilidade());
        System.out.printf("│ 🛡️  Resistência: %-3d   ✨ Magia: %-3d   🎯 Foco: %-3d     │%n", 
                         saci.getResistencia(), saci.getMagia(), saci.getFoco());
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.printf("│ 🌟 Habilidade: %-47s │%n", saci.getHabilidadeEspecial());
        System.out.printf("│ 📖 Descrição: %-48s │%n", saci.getDescricao());
        System.out.println("└─────────────────────────────────────────────────────────────┘");
    }

    /**
     * Exibe uma lista de Sacis
     */
    private void exibirListaSacis(List<Saci> sacis) {
        if (sacis.isEmpty()) {
            System.out.println("❌ Nenhum Saci encontrado.");
            return;
        }
        
        System.out.println("\n📋 Sacis encontrados: " + sacis.size());
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        for (Saci saci : sacis) {
            System.out.printf("🆔 %-2d | 📝 %-20s | 🎭 %-10s | ⭐ %-15s | 💪 %3d%n",
                saci.getId(), saci.getNome(), saci.getTipo(), saci.getRank(), saci.calcularPoderTotal());
        }
        
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        if (sacis.size() == 1) {
            System.out.println("\n📄 Detalhes completos:");
            exibirDetalheSaci(sacis.get(0));
        }
    }

    /**
     * Pausa a execução aguardando Enter
     */
    private void pausar() {
        System.out.println("\n⏸️ Pressione ENTER para continuar...");
        scanner.nextLine();
        limparTela();
    }

    /**
     * Simula limpeza da tela
     */
    private void limparTela() {
        // Em um ambiente real, poderia usar comandos específicos do sistema
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }

    /**
     * Menu de gerenciamento de inventários
     */
    private void menuInventarios() {
        boolean voltarMenu = false;
        while (!voltarMenu) {
            System.out.println("┌──────────────────────────────────────────────────────────┐");
            System.out.println("│                🎒 MENU DE INVENTÁRIOS                    │");
            System.out.println("├──────────────────────────────────────────────────────────┤");
            System.out.println("│  1  - Ver inventário de caçador                         │");
            System.out.println("│  2  - Adicionar item ao inventário                      │");
            System.out.println("│  3  - Usar item do inventário                           │");
            System.out.println("│  4  - Tentar capturar Saci                              │");
            System.out.println("│  5  - Curar Saci                                        │");
            System.out.println("│  6  - Usar item especial                                │");
            System.out.println("│  7  - Comprar item com tampinhas                        │");
            System.out.println("│  8  - Recarregar itens especiais (nova sessão)          │");
            System.out.println("│  9  - Listar todos os inventários                       │");
            System.out.println("│  10 - Estatísticas de inventários                       │");
            System.out.println("│                                                          │");
            System.out.println("│  0  - Voltar ao menu principal                          │");
            System.out.println("└──────────────────────────────────────────────────────────┘");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1: verInventarioCacador(); break;
                case 2: adicionarItemInventario(); break;
                case 3: usarItemInventario(); break;
                case 4: tentarCapturarSaci(); break;
                case 5: curarSaci(); break;
                case 6: usarItemEspecial(); break;
                case 7: comprarItemComTampinhas(); break;
                case 8: recarregarItensEspeciais(); break;
                case 9: listarTodosInventarios(); break;
                case 10: estatisticasInventarios(); break;
                case 0: voltarMenu = true; break;
                default: System.out.println("❌ Opção inválida!");
            }
            
            if (!voltarMenu) pausar();
        }
    }

    // Placeholder methods for inventory management
    private void verInventarioCacador() {
        System.out.print("Digite o ID do caçador: ");
        int id = scanner.nextInt();
        inventarioService.mostrarInventario(id);
    }

    private void adicionarItemInventario() {
        System.out.print("Digite o ID do caçador: ");
        int id = scanner.nextInt();
        
        System.out.println("Tipos de item disponíveis:");
        System.out.println("1 - Garrafa de Captura");
        System.out.println("2 - Curativo");
        System.out.println("3 - Tampinha");
        System.out.print("Escolha o tipo: ");
        int tipo = scanner.nextInt();
        
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        
        Inventario.TipoItem tipoItem = null;
        switch (tipo) {
            case 1: tipoItem = Inventario.TipoItem.GARRAFA_CAPTURA; break;
            case 2: tipoItem = Inventario.TipoItem.CURATIVO; break;
            case 3: tipoItem = Inventario.TipoItem.TAMPINHA; break;
        }
        
        if (tipoItem != null) {
            inventarioService.adicionarItem(id, tipoItem, quantidade);
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private void usarItemInventario() {
        System.out.print("Digite o ID do caçador: ");
        int id = scanner.nextInt();
        
        System.out.println("Tipos de item disponíveis:");
        System.out.println("1 - Garrafa de Captura");
        System.out.println("2 - Curativo");
        System.out.println("3 - Tampinha");
        System.out.print("Escolha o tipo: ");
        int tipo = scanner.nextInt();
        
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        
        Inventario.TipoItem tipoItem = null;
        switch (tipo) {
            case 1: tipoItem = Inventario.TipoItem.GARRAFA_CAPTURA; break;
            case 2: tipoItem = Inventario.TipoItem.CURATIVO; break;
            case 3: tipoItem = Inventario.TipoItem.TAMPINHA; break;
        }
        
        if (tipoItem != null) {
            inventarioService.usarItem(id, tipoItem, quantidade);
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private void tentarCapturarSaci() {
        System.out.print("Digite o ID do caçador: ");
        int id = scanner.nextInt();
        inventarioService.tentarCaptura(id);
    }

    private void curarSaci() {
        System.out.print("Digite o ID do caçador: ");
        int id = scanner.nextInt();
        System.out.print("Digite o nome do Saci: ");
        scanner.nextLine(); // consume newline
        String nome = scanner.nextLine();
        inventarioService.curarSaci(id, nome);
    }

    private void usarItemEspecial() {
        System.out.print("Digite o ID do caçador: ");
        int id = scanner.nextInt();
        System.out.print("Digite o nome do item especial: ");
        scanner.nextLine(); // consume newline
        String nome = scanner.nextLine();
        inventarioService.usarItemEspecial(id, nome);
    }

    private void comprarItemComTampinhas() {
        System.out.print("Digite o ID do caçador: ");
        int id = scanner.nextInt();
        
        System.out.println("Itens disponíveis na Mercoruja:");
        System.out.println("1 - Garrafa de Captura (5 tampinhas)");
        System.out.println("2 - Curativo (3 tampinhas)");
        System.out.print("Escolha o item: ");
        int item = scanner.nextInt();
        
        switch (item) {
            case 1:
                inventarioService.comprarItem(id, Inventario.TipoItem.GARRAFA_CAPTURA, 5);
                break;
            case 2:
                inventarioService.comprarItem(id, Inventario.TipoItem.CURATIVO, 3);
                break;
            default:
                System.out.println("Item inválido!");
        }
    }

    private void recarregarItensEspeciais() {
        inventarioService.novaSessione();
    }

    private void listarTodosInventarios() {
        inventarioService.listarTodosInventarios();
    }

    private void estatisticasInventarios() {
        inventarioService.mostrarEstatisticas();
    }

    /**
     * Exibe mensagem de despedida
     */
    private void exibirDespedida() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                     👋 ATÉ LOGO! 👋                      ║");
        System.out.println("║                                                          ║");
        System.out.println("║         Obrigado por usar o Caçadores de Saci!          ║");
        System.out.println("║              Que os Sacis estejam com você! 🌟          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    // ========== MÉTODOS TEMPORÁRIOS PARA CORRIGIR COMPILAÇÃO ==========
    
    private void criarNovoCacador() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void buscarCacadorPorId() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void buscarCacadorPorNome() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void buscarCacadoresPorClasse() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void listarTodosCacadores() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void atualizarCacador() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void removerCacador() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void adicionarSaciAoCacador() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void removerSaciDoCacador() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void adicionarExperienciaAoCacador() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void criarNovoMovimento() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void buscarMovimentoPorId() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void buscarMovimentoPorNome() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void buscarMovimentosPorTipo() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void buscarMovimentosPorCategoria() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void listarTodosMovimentos() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void atualizarMovimento() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void removerMovimento() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
    
    private void buscarMovimentosCompativeis() {
        System.out.println("🚧 Funcionalidade em desenvolvimento");
    }
}
