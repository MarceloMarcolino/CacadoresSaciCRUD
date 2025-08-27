package dao;

import model.Movimento;
import model.TipoSaci;
import model.CategoriaMovimento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO (Data Access Object) para gerenciar Movimentos
 */
public class MovimentoDAO {
    private Map<Integer, Movimento> movimentos = new HashMap<>();
    private int proximoId = 1;

    public MovimentoDAO() {
        carregarMovimentosExemplo();
    }

    /**
     * Cria um novo movimento
     */
    public Movimento criar(Movimento movimento) {
        movimento.setId(proximoId++);
        movimentos.put(movimento.getId(), movimento);
        return movimento;
    }

    /**
     * Busca um movimento por ID
     */
    public Movimento buscarPorId(int id) {
        return movimentos.get(id);
    }

    /**
     * Busca um movimento por nome
     */
    public Movimento buscarPorNome(String nome) {
        return movimentos.values().stream()
                .filter(m -> m.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    /**
     * Lista todos os movimentos
     */
    public List<Movimento> listarTodos() {
        return new ArrayList<>(movimentos.values());
    }

    /**
     * Atualiza um movimento
     */
    public void atualizar(Movimento movimento) {
        if (movimentos.containsKey(movimento.getId())) {
            movimentos.put(movimento.getId(), movimento);
        }
    }

    /**
     * Deleta um movimento por ID
     */
    public boolean deletar(int id) {
        return movimentos.remove(id) != null;
    }

    /**
     * Verifica se existe um movimento com o ID
     */
    public boolean existe(int id) {
        return movimentos.containsKey(id);
    }

    /**
     * Lista movimentos por tipo de Saci
     */
    public List<Movimento> buscarPorTipo(TipoSaci tipo) {
        return movimentos.values().stream()
                .filter(m -> m.getTipo() == tipo)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * Lista movimentos por categoria
     */
    public List<Movimento> buscarPorCategoria(CategoriaMovimento categoria) {
        return movimentos.values().stream()
                .filter(m -> m.getCategoria() == categoria)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * Carrega movimentos exemplo baseados na documentação completa do jogo
     * Baseado nas páginas 84-92 da documentação oficial
     */
    private void carregarMovimentosExemplo() {
        // ========== MOVIMENTOS DO TIPO VALENTE (páginas 84-85) ==========
        // Chamas
        criar(new Movimento("Chamas", TipoSaci.VALENTE, CategoriaMovimento.MAGICO, 
                3, 30, "Agilidade + Magia + 1. Efeito: Role 1 dado, se cair 1 o oponente fica Queimado 1"));
                
        // Esquentar
        criar(new Movimento("Esquentar", TipoSaci.VALENTE, CategoriaMovimento.UTILITARIO,
                0, 30, "Foco + Magia + 1. Artimanha. O alvo fica Queimado 1-3 dependendo do rank"));
                
        // Vendaval Ardente
        criar(new Movimento("Vendaval Ardente", TipoSaci.VALENTE, CategoriaMovimento.MAGICO,
                4, 25, "Foco + Magia + 2. Role 2 dados, resultado 1 = Queimado 2"));
                
        // Bravura Certeira
        criar(new Movimento("Bravura Certeira", TipoSaci.VALENTE, CategoriaMovimento.FISICO,
                4, 30, "Agilidade + Potência + 2. Reduz Agilidade do alvo em 1"));
                
        // Socos Furiosos
        criar(new Movimento("Socos Furiosos", TipoSaci.VALENTE, CategoriaMovimento.FISICO,
                3, 35, "Agilidade + Potência + 1. Prioridade. Resultado 6 = Paralisado"));
                
        // Mudança de Clima
        criar(new Movimento("Mudança de Clima", TipoSaci.VALENTE, CategoriaMovimento.MAGICO,
                2, 20, "Magia + Foco + 1. Resultado 1 = +1 Pot/Agi/Mag por 1 rodada"));
                
        // Explosão Destemida
        criar(new Movimento("Explosão Destemida", TipoSaci.VALENTE, CategoriaMovimento.MAGICO,
                3, 25, "Magia + Potência + 1. Área. Perde 1 PV ao usar"));
                
        // Ensolarar
        criar(new Movimento("Ensolarar", TipoSaci.VALENTE, CategoriaMovimento.UTILITARIO,
                0, 30, "Foco + Magia + 2. Artimanha. O clima fica Ensolarado"));
                
        // Corte em Chamas
        criar(new Movimento("Corte em Chamas", TipoSaci.VALENTE, CategoriaMovimento.FISICO,
                4, 30, "Potência + Agilidade + 2. 2 dados, resultado 1 = Queimado 2"));
                
        // Dança Estridente
        criar(new Movimento("Dança Estridente", TipoSaci.VALENTE, CategoriaMovimento.FISICO,
                3, 30, "Agilidade + Resistência + 2. 2 dados, resultado 5 = +1 Agilidade"));

        // ========== MOVIMENTOS DO TIPO SOMBRIO (páginas 85-87) ==========
        // Dentada
        criar(new Movimento("Dentada", TipoSaci.SOMBRIO, CategoriaMovimento.FISICO,
                3, 30, "Potência + Agilidade + 1. Resultado 4 = Envenenado 1"));
                
        // Cair no Sono
        criar(new Movimento("Cair no Sono", TipoSaci.SOMBRIO, CategoriaMovimento.UTILITARIO,
                0, 25, "Foco + Magia. Artimanha. Alvo fica Cochilando"));
                
        // Adrenalina Vingativa
        criar(new Movimento("Adrenalina Vingativa", TipoSaci.SOMBRIO, CategoriaMovimento.FISICO,
                4, 30, "Potência + Agilidade + 1. +1 dado se já recebeu dano do alvo"));
                
        // Baderna
        criar(new Movimento("Baderna", TipoSaci.SOMBRIO, CategoriaMovimento.UTILITARIO,
                2, 25, "Potência + Foco + 1. Alvo recebe -1 em Magia"));
                
        // Cara Feia
        criar(new Movimento("Cara Feia", TipoSaci.SOMBRIO, CategoriaMovimento.UTILITARIO,
                0, 30, "Magia + Foco + 2. -1 dano físico por 3 rodadas"));
                
        // Brincar de Coitado
        criar(new Movimento("Brincar de Coitado", TipoSaci.SOMBRIO, CategoriaMovimento.UTILITARIO,
                0, 25, "Foco + Resistência + 1. Artimanha. Reduz 1 Resistência do alvo"));
                
        // Te Peguei
        criar(new Movimento("Te Peguei", TipoSaci.SOMBRIO, CategoriaMovimento.UTILITARIO,
                0, 30, "Resistência + Foco + 1. Alvo só pode usar movimentos de dano por 3 turnos"));
                
        // Sombra Venenosa
        criar(new Movimento("Sombra Venenosa", TipoSaci.SOMBRIO, CategoriaMovimento.UTILITARIO,
                0, 25, "Foco + Magia + 1. Artimanha. Envenenado 1-2 conforme rank"));
                
        // Rajada Sombria
        criar(new Movimento("Rajada Sombria", TipoSaci.SOMBRIO, CategoriaMovimento.MAGICO,
                4, 25, "Magia + Foco + 2. 2 dados, resultado 4 = Confuso"));
                
        // Toque Venenoso
        criar(new Movimento("Toque Venenoso", TipoSaci.SOMBRIO, CategoriaMovimento.FISICO,
                3, 30, "Potência + Agilidade + 2. Resultado 4 = Envenenado 1"));

        // ========== MOVIMENTOS DO TIPO NEUTRO (páginas 87-88) ==========
        // Socão
        criar(new Movimento("Socão", TipoSaci.NEUTRO, CategoriaMovimento.FISICO,
                3, 30, "Potência + Agilidade + 1. Resultado 4 = Confuso"));
                
        // Agarrão
        criar(new Movimento("Agarrão", TipoSaci.NEUTRO, CategoriaMovimento.FISICO,
                2, 25, "Potência + Resistência + 1. Preso por 3 rodadas, -1 Agi/Res"));
                
        // Esquiva Precisa
        criar(new Movimento("Esquiva Precisa", TipoSaci.NEUTRO, CategoriaMovimento.UTILITARIO,
                0, 30, "Agilidade + Foco + 1. Artimanha. +1 Agilidade por 3 rodadas"));
                
        // Ataque Repentino
        criar(new Movimento("Ataque Repentino", TipoSaci.NEUTRO, CategoriaMovimento.FISICO,
                3, 35, "Potência + Agilidade + 2. Prioridade"));
                
        // Invejar
        criar(new Movimento("Invejar", TipoSaci.NEUTRO, CategoriaMovimento.UTILITARIO,
                0, 20, "Copia o último movimento usado pelo alvo"));
                
        // Nana Neném
        criar(new Movimento("Nana Neném", TipoSaci.NEUTRO, CategoriaMovimento.UTILITARIO,
                0, 25, "Foco + Magia. Artimanha. Alvo fica Cochilando"));
                
        // Investida Suave
        criar(new Movimento("Investida Suave", TipoSaci.NEUTRO, CategoriaMovimento.FISICO,
                2, 25, "Potência + Foco + 2. Não reduz alvo a menos de 1 PV"));
                
        // Tudo ou Nada
        criar(new Movimento("Tudo ou Nada", TipoSaci.NEUTRO, CategoriaMovimento.FISICO,
                5, 20, "+1 dado de dano para cada 3 PV perdidos. Cooldown 2 rodadas"));
                
        // Esqueça Tudo
        criar(new Movimento("Esqueça Tudo", TipoSaci.NEUTRO, CategoriaMovimento.UTILITARIO,
                0, 25, "Foco + Resistência + 2. Alvo não pode usar último movimento por 3 rodadas"));
                
        // Detonação Final
        criar(new Movimento("Detonação Final", TipoSaci.NEUTRO, CategoriaMovimento.MAGICO,
                8, 10, "Foco + Resistência + 6. Área. Usuário é derrubado (4x resultado 6 = sobrevive)"));

        // ========== MOVIMENTOS DO TIPO RÍGIDO (páginas 88-90) ==========
        // Racha de Terra
        criar(new Movimento("Racha de Terra", TipoSaci.RIGIDO, CategoriaMovimento.FISICO,
                3, 30, "Potência + Resistência. Área. Alvos recebem -1 Agilidade"));
                
        // Espinhos
        criar(new Movimento("Espinhos", TipoSaci.RIGIDO, CategoriaMovimento.UTILITARIO,
                0, 30, "Magia + Foco + 1. Por 3 rodadas, Evasão causa 1 dano no oponente"));
                
        // Pedregulhos
        criar(new Movimento("Pedregulhos", TipoSaci.RIGIDO, CategoriaMovimento.FISICO,
                3, 30, "Potência + Resistência + 1. Metade do resultado adiciona ao teste"));
                
        // Defesa de Aço
        criar(new Movimento("Defesa de Aço", TipoSaci.RIGIDO, CategoriaMovimento.UTILITARIO,
                0, 35, "Foco + Resistência + 1. Reduz 1 dano nessa rodada"));
                
        // Pancada de Terra
        criar(new Movimento("Pancada de Terra", TipoSaci.RIGIDO, CategoriaMovimento.FISICO,
                3, 30, "Potência + Resistência + 2. Resultado 4 = Confuso"));
                
        // Golpe de Oportunidade
        criar(new Movimento("Golpe de Oportunidade", TipoSaci.RIGIDO, CategoriaMovimento.MAGICO,
                4, 25, "Magia + Agilidade + 2. +1 dado se oponente falhou resposta"));
                
        // Absorver
        criar(new Movimento("Absorver", TipoSaci.RIGIDO, CategoriaMovimento.MAGICO,
                3, 30, "Resistência + Magia + 2. +1 dado se recebeu dano físico antes"));
                
        // Sem Arrependimentos
        criar(new Movimento("Sem Arrependimentos", TipoSaci.RIGIDO, CategoriaMovimento.UTILITARIO,
                0, 30, "+2 Foco/Resistência, não pode esquivar mais. Uma vez por combate"));
                
        // Tormenta de Areia
        criar(new Movimento("Tormenta de Areia", TipoSaci.RIGIDO, CategoriaMovimento.UTILITARIO,
                0, 30, "Magia + Foco + 2. Artimanha. Clima fica Tempestuoso"));
                
        // Salto Terrestre
        criar(new Movimento("Salto Terrestre", TipoSaci.RIGIDO, CategoriaMovimento.FISICO,
                4, 30, "Potência + Agilidade + 3. +1 Agi/Pot por 3 rodadas"));

        // ========== MOVIMENTOS DO TIPO CATIVANTE (páginas 90-91) ==========
        // Sopro de Cura
        criar(new Movimento("Sopro de Cura", TipoSaci.CATIVANTE, CategoriaMovimento.MAGICO,
                0, 35, "Magia + Foco + 1. Artimanha. Cure 1 PV"));
                
        // Investida
        criar(new Movimento("Investida", TipoSaci.CATIVANTE, CategoriaMovimento.FISICO,
                3, 30, "Potência + Agilidade + 1. Resultado 4 = Confuso"));
                
        // Refrescar
        criar(new Movimento("Refrescar", TipoSaci.CATIVANTE, CategoriaMovimento.MAGICO,
                2, 30, "Magia + Resistência + 2. Recupera PV = metade do dano"));
                
        // Impulso Tropical
        criar(new Movimento("Impulso Tropical", TipoSaci.CATIVANTE, CategoriaMovimento.FISICO,
                2, 30, "Magia + Agilidade + 1. Diminui 1 Potência do alvo"));
                
        // Barreira Mágica
        criar(new Movimento("Barreira Mágica", TipoSaci.CATIVANTE, CategoriaMovimento.UTILITARIO,
                0, 35, "Magia + Foco + 2. Artimanha. -1 dano mágico por 1 rodada"));
                
        // Contra Encanto
        criar(new Movimento("Contra Encanto", TipoSaci.CATIVANTE, CategoriaMovimento.MAGICO,
                4, 25, "Reflete último movimento mágico do oponente com +1 dado"));
                
        // Troca de Peças
        criar(new Movimento("Troca de Peças", TipoSaci.CATIVANTE, CategoriaMovimento.UTILITARIO,
                0, 25, "Magia + Foco + 2. Artimanha. Troca Potência e Magia com alvo"));
                
        // Chuvarada
        criar(new Movimento("Chuvarada", TipoSaci.CATIVANTE, CategoriaMovimento.UTILITARIO,
                0, 30, "Magia + Foco + 2. Artimanha. Clima fica Chuvoso"));
                
        // Ritmo Verdejante
        criar(new Movimento("Ritmo Verdejante", TipoSaci.CATIVANTE, CategoriaMovimento.UTILITARIO,
                0, 30, "Foco + Magia + 2. +1 em dois atributos entre Agi/Mag/Foc"));
                
        // Canção de Ninar
        criar(new Movimento("Canção de Ninar", TipoSaci.CATIVANTE, CategoriaMovimento.UTILITARIO,
                0, 25, "Magia + Foco. Artimanha. Alvo fica Cochilando"));

        // ========== MOVIMENTOS DO TIPO CAÓTICO (páginas 91-92) ==========
        // Redemoinho
        criar(new Movimento("Redemoinho", TipoSaci.CAOTICO, CategoriaMovimento.MAGICO,
                2, 30, "Magia + Agilidade + 1. Resultado 4 = Confuso"));
                
        // Afugentar
        criar(new Movimento("Afugentar", TipoSaci.CAOTICO, CategoriaMovimento.FISICO,
                3, 30, "Agilidade + Potência + 1. 2 dados, resultado 4 = Confuso"));
                
        // Iluminar
        criar(new Movimento("Iluminar", TipoSaci.CAOTICO, CategoriaMovimento.UTILITARIO,
                0, 25, "Magia + Foco + 2. -1 Agilidade, reduz dados de ataque do alvo"));
                
        // Bafo Gelado
        criar(new Movimento("Bafo Gelado", TipoSaci.CAOTICO, CategoriaMovimento.MAGICO,
                3, 25, "Magia + Foco + 2. Resultado 3 = Congelado"));
                
        // Golpe Errático
        criar(new Movimento("Golpe Errático", TipoSaci.CAOTICO, CategoriaMovimento.FISICO,
                3, 25, "Potência + Agilidade + 2. Alvo fica Confuso"));
                
        // Você Vem Junto
        criar(new Movimento("Você Vem Junto", TipoSaci.CAOTICO, CategoriaMovimento.UTILITARIO,
                0, 20, "Foco + Magia + 2. Se usuário for derrubado, pode derrubar atacante"));
                
        // Vórtice de Incerteza
        criar(new Movimento("Vórtice de Incerteza", TipoSaci.CAOTICO, CategoriaMovimento.MAGICO,
                4, 20, "Magia + Foco + 1. +1 dado para cada Saci em campo"));
                
        // Congelar
        criar(new Movimento("Congelar", TipoSaci.CAOTICO, CategoriaMovimento.UTILITARIO,
                0, 25, "Foco + Magia. Alvo fica Congelado"));
                
        // Nebulosidade
        criar(new Movimento("Nebulosidade", TipoSaci.CAOTICO, CategoriaMovimento.UTILITARIO,
                0, 30, "Foco + Magia + 2. Clima fica Nebuloso"));
                
        // Paralisia Forçada
        criar(new Movimento("Paralisia Forçada", TipoSaci.CAOTICO, CategoriaMovimento.UTILITARIO,
                0, 30, "Foco + Magia + 2. Alvo fica Paralisado"));
    }
}
