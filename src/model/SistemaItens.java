package model;

/**
 * Sistema de Itens do Caçador
 * Baseado na documentação: Garrafas de Captura, Tampinhas, Curativos, etc.
 */
public class SistemaItens {
    private int garrafasCaptura;
    private int tampinhas;
    private int curativos;
    private int itemEspecial; // Usa por sessão

    public SistemaItens() {
        // Valores iniciais baseados na documentação
        this.garrafasCaptura = 2; // "Todo Caçador começa o jogo com duas Garrafas de Captura"
        this.tampinhas = 0;
        this.curativos = 0;
        this.itemEspecial = 1; // Usado uma vez por sessão
    }

    public SistemaItens(int garrafas, int tampinhas, int curativos) {
        this.garrafasCaptura = garrafas;
        this.tampinhas = tampinhas;
        this.curativos = curativos;
        this.itemEspecial = 1;
    }

    /**
     * Usa uma garrafa de captura
     * Se falhar, a garrafa se quebra
     */
    public boolean usarGarrafaCaptura() {
        if (garrafasCaptura > 0) {
            garrafasCaptura--;
            return true;
        }
        return false;
    }

    /**
     * Compra garrafa com tampinha
     * "podendo comprar mais por 1 Tampinha com o Mercoruja"
     */
    public boolean comprarGarrafa() {
        if (tampinhas >= 1) {
            tampinhas--;
            garrafasCaptura++;
            return true;
        }
        return false;
    }

    /**
     * Recebe tampinhas por capturar Saci
     * Baseado na tabela de tampinhas por rank
     */
    public void receberTampinhas(Rank rankSaci, boolean sucessoMaximo) {
        int tampinhasRecebidas;
        
        switch (rankSaci) {
            case UMA_ESTRELA:
                tampinhasRecebidas = sucessoMaximo ? 3 : 1;
                break;
            case DUAS_ESTRELAS:
                tampinhasRecebidas = sucessoMaximo ? 5 : 3;
                break;
            case TRES_ESTRELAS:
                tampinhasRecebidas = sucessoMaximo ? 7 : 5;
                break;
            case QUATRO_ESTRELAS:
                tampinhasRecebidas = sucessoMaximo ? 9 : 7;
                break;
            default:
                tampinhasRecebidas = 1;
        }
        
        this.tampinhas += tampinhasRecebidas;
    }

    /**
     * Usa curativo
     */
    public boolean usarCurativo() {
        if (curativos > 0) {
            curativos--;
            return true;
        }
        return false;
    }

    /**
     * Usa item especial da classe
     */
    public boolean usarItemEspecial() {
        if (itemEspecial > 0) {
            itemEspecial--;
            return true;
        }
        return false;
    }

    /**
     * Reset do item especial (nova sessão)
     */
    public void resetarItemEspecial() {
        this.itemEspecial = 1;
    }

    /**
     * Compra itens com tampinhas
     */
    public boolean comprarItem(TipoItem tipo, int quantidade) {
        int custoTotal = tipo.getCusto() * quantidade;
        
        if (tampinhas >= custoTotal) {
            tampinhas -= custoTotal;
            
            switch (tipo) {
                case GARRAFA_CAPTURA:
                    garrafasCaptura += quantidade;
                    break;
                case CURATIVO:
                    curativos += quantidade;
                    break;
            }
            return true;
        }
        return false;
    }

    /**
     * Enum dos tipos de itens compráveis
     */
    public enum TipoItem {
        GARRAFA_CAPTURA("Garrafa de Captura", 1),
        CURATIVO("Curativo", 2);

        private final String nome;
        private final int custo;

        TipoItem(String nome, int custo) {
            this.nome = nome;
            this.custo = custo;
        }

        public String getNome() {
            return nome;
        }

        public int getCusto() {
            return custo;
        }
    }

    // Getters e Setters
    public int getGarrafasCaptura() {
        return garrafasCaptura;
    }

    public void setGarrafasCaptura(int garrafasCaptura) {
        this.garrafasCaptura = Math.max(0, garrafasCaptura);
    }

    public int getTampinhas() {
        return tampinhas;
    }

    public void setTampinhas(int tampinhas) {
        this.tampinhas = Math.max(0, tampinhas);
    }

    public int getCurativos() {
        return curativos;
    }

    public void setCurativos(int curativos) {
        this.curativos = Math.max(0, curativos);
    }

    public int getItemEspecial() {
        return itemEspecial;
    }

    public void setItemEspecial(int itemEspecial) {
        this.itemEspecial = Math.max(0, Math.min(1, itemEspecial));
    }

    @Override
    public String toString() {
        return String.format("Itens - Garrafas: %d | Tampinhas: %d | Curativos: %d | Item Especial: %d/1",
                garrafasCaptura, tampinhas, curativos, itemEspecial);
    }
}
