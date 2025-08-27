package main;

import view.SaciViewSimples;

/**
 * Classe principal do sistema CRUD Caçadores de Saci
 * 
 * Este projeto foi desenvolvido para fins educacionais e demonstra
 * os conceitos básicos de CRUD (Create, Read, Update, Delete) em Java.
 * 
 * Baseado no jogo "Caçadores de Saci" - um RPG brasileiro que apresenta
 * criaturas místicas do folclore nacional.
 * 
 * Estrutura do projeto:
 * - model: Classes que representam os dados (Saci, TipoSaci, Temperamento, Rank)
 * - dao: Classe de acesso aos dados (Data Access Object)
 * - service: Lógica de negócio e validações
 * - view: Interface com o usuário
 * - main: Classe principal
 * 
 * @author Sistema CRUD Educacional
 * @version 1.0
 */
public class Main {
    
    /**
     * Método principal da aplicação
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        try {
            // Configurar encoding para caracteres especiais (emojis)
            System.setProperty("file.encoding", "UTF-8");
            
            // Inicializar e executar a interface
            SaciViewSimples view = new SaciViewSimples();
            view.iniciar();
            
        } catch (Exception e) {
            System.err.println("❌ Erro fatal na aplicação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
