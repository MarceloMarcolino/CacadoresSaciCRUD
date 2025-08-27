package model;

/**
 * Enum que representa os tipos de sonhadores conforme a documentação
 * Cada tipo define a personalidade e objetivos do caçador
 */
public enum TipoSonhador {
    AUDACIOSO("Audacioso", 
             "Seus sonhos só poderão ser conquistados se você ousar!", 
             "Inconsequente - Não pensa nas consequências das ações"),
    
    CALCULISTA("Calculista", 
              "Você sabe exatamente os passos que tem que seguir para conquistar seu sonho", 
              "Cabeça Fechada - Muito preso aos planos, com dificuldade para improvisar"),
    
    CONVICTO("Convicto", 
            "Para você, o destino de seus sonhos é uma certeza, não uma possibilidade", 
            "Inflexível - Ignora conselhos ou avisos de outros"),
    
    EGOISTA("Egoísta", 
           "O objetivo final é absoluto e você está preparado para ignorar os outros", 
           "Solitário - Afasta aliados e amigos em busca de ambições pessoais"),
    
    OBSTINADO("Obstinado", 
             "A meta é simples e grandiosa, você não se limita a buscar apenas alguns", 
             "Teimoso - Insiste em seguir adiante mesmo quando deveria refletir"),
    
    SINGELO("Singelo", 
           "Sonhos sobre criar impacto positivo na vida das pessoas", 
           "Subestimado - Outros podem não levá-lo a sério");

    private final String nome;
    private final String descricao;
    private final String defeito;

    TipoSonhador(String nome, String descricao, String defeito) {
        this.nome = nome;
        this.descricao = descricao;
        this.defeito = defeito;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDefeito() {
        return defeito;
    }

    @Override
    public String toString() {
        return nome + " - " + descricao;
    }
}
