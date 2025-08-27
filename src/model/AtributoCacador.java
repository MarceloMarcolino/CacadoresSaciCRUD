package model;

/**
 * Representa os quatro atributos principais de um Caçador
 * Baseado na documentação: "Os caçadores possuem quatro atributos que definem 
 * o quão bem você age diante das adversidades: Coração, Músculo, Cérebro e Charme"
 */
public enum AtributoCacador {
    CORACAO("Coração", "A essência da sua empatia, paixão e determinação. " +
           "Quando é hora de demonstrar coragem, entender os outros ou resistir " +
           "a desafios emocionais, Coração é o que vai guiar suas ações."),
    
    MUSCULO("Músculo", "A força bruta, o vigor físico. Se precisa quebrar uma barreira, " +
           "levantar um peso ou se defender em uma luta, Músculo é o que mede o quanto " +
           "seu corpo pode suportar e realizar."),
    
    CEREBRO("Cérebro", "A sede por conhecimento e raciocínio lógico. Quando as situações " +
           "exigem resolver um enigma, planejar uma estratégia ou entender algo complicado, " +
           "Cérebro é o atributo que te ajuda a encontrar a solução."),
    
    CHARME("Charme", "A arte de influenciar e cativar. Seja através de palavras ou presença, " +
           "o Charme dita o quanto você pode persuadir, enganar, ou simplesmente brilhar em um grupo.");

    private final String nome;
    private final String descricao;

    AtributoCacador(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return nome;
    }
}
