package model;

import java.util.Random;

/**
 * Sistema de Desobediência dos Sacis
 * Baseado na documentação: "Durante os 3 primeiros níveis de amizade, 
 * a Desobediência pode ocorrer de forma mais frequente"
 */
public class SistemaDesobediencia {
    private final Random random;

    public SistemaDesobediencia() {
        this.random = new Random();
    }

    /**
     * Verifica se o Saci desobedece baseado no nível de amizade
     */
    public boolean verificarDesobediencia(SistemaAmizade amizade) {
        // Só acontece nos 3 primeiros níveis
        if (!amizade.estaPeriodoDesobediencia()) {
            return false;
        }

        // Rola 1 dado, se não obter sucesso (4-6), testa desobediência
        int dadoTeste = random.nextInt(6) + 1;
        if (dadoTeste >= 4) {
            return false; // Não desobedece
        }

        return true; // Desobedece
    }

    /**
     * Rola o efeito da desobediência
     */
    public EfeitoDesobediencia rolarEfeitoDesobediencia() {
        int dado = random.nextInt(6) + 1;
        
        switch (dado) {
            case 1: return EfeitoDesobediencia.IGNORA_ORDEM;
            case 2: return EfeitoDesobediencia.COCHILA;
            case 3: return EfeitoDesobediencia.USA_OUTRO_MOVIMENTO;
            case 4: return EfeitoDesobediencia.PERDE_ATRIBUTO;
            case 5: return EfeitoDesobediencia.GASTA_TRAQUINAGEM;
            case 6: return EfeitoDesobediencia.NADA_ACONTECE;
            default: return EfeitoDesobediencia.NADA_ACONTECE;
        }
    }

    /**
     * Enum dos efeitos de desobediência baseado na tabela da documentação
     */
    public enum EfeitoDesobediencia {
        IGNORA_ORDEM(1, "O Saci ignora a ordem e não ataca neste turno"),
        COCHILA(2, "O Saci começa a dormir, recebendo a condição Cochilando"),
        USA_OUTRO_MOVIMENTO(3, "O Saci usa outro Movimento a escolha do Narrador"),
        PERDE_ATRIBUTO(4, "O Saci perde 1 ponto em um atributo a escolha do Narrador"),
        GASTA_TRAQUINAGEM(5, "O Saci gasta um ponto de Traquinagem em um sucesso"),
        NADA_ACONTECE(6, "Nada acontece, o Saci age normalmente");

        private final int resultado;
        private final String descricao;

        EfeitoDesobediencia(int resultado, String descricao) {
            this.resultado = resultado;
            this.descricao = descricao;
        }

        public int getResultado() {
            return resultado;
        }

        public String getDescricao() {
            return descricao;
        }

        @Override
        public String toString() {
            return resultado + " - " + descricao;
        }
    }

    /**
     * Aplica o efeito de desobediência ao Saci
     */
    public String aplicarEfeito(EfeitoDesobediencia efeito, Saci saci) {
        switch (efeito) {
            case IGNORA_ORDEM:
                return saci.getNome() + " ignora sua ordem e não ataca neste turno!";
                
            case COCHILA:
                // Adicionar condição "Cochilando"
                return saci.getNome() + " começa a cochilar durante a batalha!";
                
            case USA_OUTRO_MOVIMENTO:
                return saci.getNome() + " decide usar um movimento diferente do que você ordenou!";
                
            case PERDE_ATRIBUTO:
                // Escolher atributo aleatório para reduzir
                String[] atributos = {"Potência", "Agilidade", "Resistência", "Magia", "Foco"};
                String atributoEscolhido = atributos[random.nextInt(atributos.length)];
                return saci.getNome() + " perde 1 ponto em " + atributoEscolhido + " temporariamente!";
                
            case GASTA_TRAQUINAGEM:
                return saci.getNome() + " gasta um ponto de Traquinagem desnecessariamente!";
                
            case NADA_ACONTECE:
                return saci.getNome() + " decide obedecer desta vez.";
                
            default:
                return "Efeito de desobediência desconhecido.";
        }
    }

    /**
     * Teste de desobediência em combates PvP
     * "um teste de desobediência deve ser feito uma vez por turno"
     */
    public boolean testeDesobedienciaPvP() {
        // Rola 2 dados, se não obter sucesso, desobedece
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        
        // Verifica se algum dado obteve sucesso (4-6)
        return !(dado1 >= 4 || dado2 >= 4);
    }
}
