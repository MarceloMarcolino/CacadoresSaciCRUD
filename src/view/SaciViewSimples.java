package view;

import model.*;
import service.SaciService;
import service.CacadorService;
import service.MovimentoService;
import java.util.List;
import java.util.Scanner;

/**
 * Classe View simplificada para demonstração educacional
 * Interface básica em console para o CRUD completo
 */
public class SaciViewSimples {
    
    private Scanner scanner;
    private SaciService saciService;
    private CacadorService cacadorService;
    private MovimentoService movimentoService;

    public SaciViewSimples() {
        this.scanner = new Scanner(System.in);
        this.saciService = new SaciService();
        this.cacadorService = new CacadorService();
        this.movimentoService = new MovimentoService();
    }

    /**
     * Método principal que exibe o menu e controla a aplicação
     */
    public void iniciar() {
        exibirBemVindo();
        
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    demonstrarSacis();
                    break;
                case 2:
                    demonstrarCacadores();
                    break;
                case 3:
                    demonstrarMovimentos();
                    break;
                case 4:
                    demonstrarEstatisticas();
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
        System.out.println("║                  Versão Educacional                     ║");
        System.out.println("║                                                          ║");
        System.out.println("║        Gerencie Sacis, Caçadores e Movimentos!          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Exibe o menu principal
     */
    private void exibirMenu() {
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                      📋 MENU PRINCIPAL                   │");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        System.out.println("│  1  - Ver e Gerenciar Sacis                             │");
        System.out.println("│  2  - Ver e Gerenciar Caçadores                         │");
        System.out.println("│  3  - Ver e Gerenciar Movimentos                        │");
        System.out.println("│  4  - Ver Estatísticas do Sistema                       │");
        System.out.println("│                                                          │");
        System.out.println("│  0  - Sair                                              │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Demonstra funcionalidades dos Sacis
     */
    private void demonstrarSacis() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("                    🌟 GESTÃO DE SACIS");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // Listar todos os Sacis
        System.out.println("\n📝 SACIS CADASTRADOS:");
        List<Saci> sacis = saciService.listarTodosSacis();
        
        if (sacis.isEmpty()) {
            System.out.println("Nenhum Saci cadastrado.");
        } else {
            for (Saci saci : sacis) {
                exibirSaciDetalhado(saci);
                System.out.println("─".repeat(60));
            }
        }

        // Demonstrar busca por tipo
        System.out.println("\n🔍 EXEMPLO DE BUSCA POR TIPO (VALENTE):");
        List<Saci> sacisValentes = saciService.buscarSacisPorTipo(TipoSaci.VALENTE);
        for (Saci saci : sacisValentes) {
            System.out.println("• " + saci.getNome() + " (" + saci.getTipo().getNome() + ")");
        }

        // Demonstrar estatísticas
        System.out.println("\n📊 ESTATÍSTICAS DOS SACIS:");
        System.out.println("Total de Sacis: " + sacis.size());
        System.out.println("Sacis Valentes: " + sacisValentes.size());
        
        long sacisRigidos = sacis.stream()
                .filter(s -> s.getTipo() == TipoSaci.RIGIDO)
                .count();
        System.out.println("Sacis Rígidos: " + sacisRigidos);
    }

    /**
     * Demonstra funcionalidades dos Caçadores
     */
    private void demonstrarCacadores() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("                  👤 GESTÃO DE CAÇADORES");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // Listar todos os Caçadores
        System.out.println("\n📝 CAÇADORES CADASTRADOS:");
        List<Cacador> cacadores = cacadorService.listarTodosCacadores();
        
        if (cacadores.isEmpty()) {
            System.out.println("Nenhum Caçador cadastrado.");
        } else {
            for (Cacador cacador : cacadores) {
                exibirCacadorDetalhado(cacador);
                System.out.println("─".repeat(60));
            }
        }

        // Demonstrar busca por classe
        System.out.println("\n🔍 EXEMPLO DE BUSCA POR CLASSE (DOMADOR):");
        List<Cacador> domadores = cacadorService.buscarCacadoresPorClasse(ClasseCacador.DOMADOR);
        for (Cacador cacador : domadores) {
            System.out.println("• " + cacador.getNome() + " (" + cacador.getClasse().getNome() + ")");
        }

        System.out.println("\n📊 ESTATÍSTICAS DOS CAÇADORES:");
        System.out.println("Total de Caçadores: " + cacadores.size());
        System.out.println("Domadores: " + domadores.size());
    }

    /**
     * Demonstra funcionalidades dos Movimentos
     */
    private void demonstrarMovimentos() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("                  ⚔️ GESTÃO DE MOVIMENTOS");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // Listar todos os Movimentos
        System.out.println("\n📝 MOVIMENTOS CADASTRADOS:");
        List<Movimento> movimentos = movimentoService.listarTodosMovimentos();
        
        if (movimentos.isEmpty()) {
            System.out.println("Nenhum Movimento cadastrado.");
        } else {
            for (Movimento movimento : movimentos) {
                exibirMovimentoDetalhado(movimento);
                System.out.println("─".repeat(60));
            }
        }

        // Demonstrar busca por categoria
        System.out.println("\n🔍 EXEMPLO DE BUSCA POR CATEGORIA (FÍSICO):");
        List<Movimento> movimentosFisicos = movimentoService.buscarMovimentosPorCategoria(CategoriaMovimento.FISICO);
        for (Movimento movimento : movimentosFisicos) {
            System.out.println("• " + movimento.getNome() + " (" + movimento.getCategoria().getNome() + ")");
        }

        System.out.println("\n📊 ESTATÍSTICAS DOS MOVIMENTOS:");
        System.out.println("Total de Movimentos: " + movimentos.size());
        System.out.println("Movimentos Físicos: " + movimentosFisicos.size());
    }

    /**
     * Exibe estatísticas gerais do sistema
     */
    private void demonstrarEstatisticas() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("                  📊 ESTATÍSTICAS GERAIS");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        List<Saci> sacis = saciService.listarTodosSacis();
        List<Cacador> cacadores = cacadorService.listarTodosCacadores();
        List<Movimento> movimentos = movimentoService.listarTodosMovimentos();
        
        System.out.println("📈 RESUMO DO SISTEMA:");
        System.out.println("• Total de Sacis: " + sacis.size());
        System.out.println("• Total de Caçadores: " + cacadores.size());
        System.out.println("• Total de Movimentos: " + movimentos.size());
        
        System.out.println("\n🎮 TIPOS DE SACI:");
        for (TipoSaci tipo : TipoSaci.values()) {
            long count = sacis.stream().filter(s -> s.getTipo() == tipo).count();
            System.out.println("• " + tipo.getNome() + ": " + count + " Sacis");
        }
        
        System.out.println("\n👥 CLASSES DE CAÇADOR:");
        for (ClasseCacador classe : ClasseCacador.values()) {
            long count = cacadores.stream().filter(c -> c.getClasse() == classe).count();
            System.out.println("• " + classe.getNome() + ": " + count + " Caçadores");
        }
    }

    /**
     * Exibe detalhes de um Saci
     */
    private void exibirSaciDetalhado(Saci saci) {
        System.out.println("🌟 " + saci.getNome());
        System.out.println("   Tipo: " + saci.getTipo().getNome());
        System.out.println("   Temperamento: " + saci.getTemperamento().getNome());
        System.out.println("   Rank: " + saci.getRank().getSimbolo() + " (" + saci.getRank().getDescricao() + ")");
        System.out.println("   PV: " + saci.getPv() + " | Atributos: " + 
                          saci.getPotencia() + "/" + saci.getAgilidade() + "/" + 
                          saci.getResistencia() + "/" + saci.getMagia() + "/" + saci.getFoco());
        if (saci.getHabilidadeEspecial() != null) {
            System.out.println("   Habilidade: " + saci.getHabilidadeEspecial());
        }
    }

    /**
     * Exibe detalhes de um Caçador
     */
    private void exibirCacadorDetalhado(Cacador cacador) {
        System.out.println("👤 " + cacador.getNome());
        System.out.println("   Idade: " + cacador.getIdade() + " anos");
        System.out.println("   Classe: " + cacador.getClasse().getNome());
        System.out.println("   Nível: " + cacador.getNivel() + " | XP: " + cacador.getExperiencia());
        System.out.println("   Atributos: ♥" + cacador.getCoracao() + " 💪" + cacador.getMusculo() + 
                          " 🧠" + cacador.getCerebro() + " ✨" + cacador.getCharme());
        if (cacador.getSonho() != null) {
            System.out.println("   Sonho: " + cacador.getSonho());
        }
        if (cacador.getSacis() != null && !cacador.getSacis().isEmpty()) {
            System.out.println("   Sacis: " + cacador.getSacis().size() + " capturados");
        }
    }

    /**
     * Exibe detalhes de um Movimento
     */
    private void exibirMovimentoDetalhado(Movimento movimento) {
        System.out.println("⚔️ " + movimento.getNome());
        System.out.println("   Tipo: " + movimento.getTipo().getNome());
        System.out.println("   Categoria: " + movimento.getCategoria().getNome());
        if (movimento.getTesteAtaque() != null) {
            System.out.println("   Teste: " + movimento.getTesteAtaque());
        }
        if (movimento.getEfeito() != null) {
            System.out.println("   Efeito: " + movimento.getEfeito());
        }
        if (movimento.getDescricao() != null) {
            System.out.println("   Descrição: " + movimento.getDescricao());
        }
    }

    /**
     * Lê uma opção do usuário
     */
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Pausa e aguarda Enter
     */
    private void pausar() {
        System.out.println("\n💡 Pressione Enter para continuar...");
        scanner.nextLine();
        System.out.println();
    }

    /**
     * Exibe mensagem de despedida
     */
    private void exibirDespedida() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                          ║");
        System.out.println("║            Obrigado por usar o sistema!                 ║");
        System.out.println("║          Caçadores de Saci - Versão Educacional         ║");
        System.out.println("║                                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}
