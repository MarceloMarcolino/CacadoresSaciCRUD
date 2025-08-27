package model;

/**
 * Representa as 8 perícias de um Caçador
 * Baseado na documentação: "eles contam com 8 Perícias, que são habilidades 
 * essenciais para superar desafios e se destacar em qualquer situação"
 */
public enum PericiaCacador {
    ATENCAO("Atenção", AtributoCacador.CEREBRO, 
           "A perícia Atenção serve para medir o quão atento você está aos seus arredores " +
           "e a capacidade de pegar detalhes que outras pessoas deixariam passar despercebido."),
    
    TATICA("Tática", AtributoCacador.CEREBRO,
          "Tática é a habilidade de bolar estratégias, planos e tramoias. " +
          "Com essa perícia, você sempre tem uma carta na manga."),
    
    ARTE("Arte", AtributoCacador.CORACAO,
        "Fazer arte é um termo amplo: Ela mede o quão bem você é capaz de desenhar, " +
        "pintar, fazer esculturas e deixar todo mundo de queixo caído no karaokê."),
    
    EMPATIA("Empatia", AtributoCacador.CORACAO,
           "Sua Empatia mede sua capacidade de auxiliar os outros, seja para tirar um " +
           "band-aid de uma ferida sem machucar ou usar palavras de conforto naquele " +
           "amigo que acabou de perder uma batalha importante."),
    
    ENCRENCA("Encrenca", AtributoCacador.MUSCULO,
            "Quando nada mais resolve e você é obrigado a usar outra abordagem, é bom " +
            "recorrer para a Encrenca. Essa perícia mede sua capacidade de…bem, arrumar " +
            "confusão por aí, seja brigando ou provocando os outros."),
    
    ESPORTES("Esportes", AtributoCacador.MUSCULO,
            "Correr, saltar, nadar e escalar. Esportes medem sua aptidão atlética " +
            "e sua capacidade de superar obstáculos usando seu treinamento em " +
            "determinadas atividades físicas."),
    
    MANDAR("Mandar", AtributoCacador.CHARME,
          "Essa perícia é ligada à sua capacidade de liderança e de conseguir com que " +
          "façam o que você quer. Liderar seu grupo de amigos ou mandar um grupo de " +
          "valentões ir pastar são todas feitas com seu Mandar."),
    
    FINGIR("Fingir", AtributoCacador.CHARME,
          "Se fazer de desentendido, enganar aquele adulto chato ou fazer birra para " +
          "causar uma distração e chamar a atenção de todo mundo para você. Em outras " +
          "palavras, Fingir engloba a arte da cara de pau.");

    private final String nome;
    private final AtributoCacador atributoAssociado;
    private final String descricao;

    PericiaCacador(String nome, AtributoCacador atributoAssociado, String descricao) {
        this.nome = nome;
        this.atributoAssociado = atributoAssociado;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public AtributoCacador getAtributoAssociado() {
        return atributoAssociado;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return nome + " (" + atributoAssociado.getNome() + ")";
    }
}
