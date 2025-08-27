package model;

/**
 * Enum que representa os temperamentos dos Sacis
 * Baseado na documentação do jogo - página 39-40
 */
public enum TemperamentoSaci {
    CALMO(1, "Calmo", "Sacis serenos que preferem a observação cuidadosa antes de agir", "Empatia"),
    AGRESSIVO(2, "Agressivo", "Prontos para a ação imediata, esses Sacis não hesitam em partir para o confronto", "Encrenca"),
    QUIETO(3, "Quieto", "Agem com discrição, realizando suas travessuras sem serem notados", "Fingir"),
    RESISTENTE(4, "Resistente", "São durões, capazes de suportar qualquer desafio sem perder o ânimo", "Tática"),
    IMPLACAVEL(5, "Implacável", "Persistentes e determinados, esses Sacis não desistem até alcançarem seus objetivos", "Atenção"),
    AGITADO(6, "Agitado", "Constantemente em movimento, sempre prontos para a próxima aventura", "Esportes");

    private final int codigo;
    private final String nome;
    private final String descricao;
    private final String periciaBonus;

    TemperamentoSaci(int codigo, String nome, String descricao, String periciaBonus) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.periciaBonus = periciaBonus;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPericiaBonus() {
        return periciaBonus;
    }

    /**
     * Obtém um temperamento aleatório (simulando rolagem de dado)
     */
    public static TemperamentoSaci obterAleatorio() {
        int roll = (int)(Math.random() * 6) + 1;
        for (TemperamentoSaci temp : values()) {
            if (temp.codigo == roll) {
                return temp;
            }
        }
        return CALMO; // fallback
    }

    @Override
    public String toString() {
        return nome + " - " + descricao + " (Bônus: +" + periciaBonus + ")";
    }
}
