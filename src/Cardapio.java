import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private String unidade;
    private List<Prato> pratos;

    // Criei uma lista estática que irá conter cada instância de Cardapio (ao ser criado na classe Main, o objeto será adicionado a esta lista aqui)
    private static List<Cardapio> cardapios = new ArrayList<>();

    public Cardapio(String unidade) {
        this.unidade = unidade;
        pratos = new ArrayList<>();
        adicionaCardapio(this);
    }

    public String getUnidade() {
        return unidade;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public static List<Cardapio> getCardapios() {
        return cardapios;
    }

    // não adicionei os setters porque acho que não fazem sentido para a AD (qualquer alteração, suponho que deveria vir de mudança no arquivo lido)

    public void adicionaPrato(Prato prato){     // 4 - Implemente um método na classe Cardapio para adicionar um novo prato no cardápio.
        pratos.add(prato);
    }

    // método que vai adicionar cada instância de Cardapio à lista "cardapios"
    public static void adicionaCardapio(Cardapio cardapio){
        cardapios.add(cardapio);
    }

    // método que vai manipular a lista estática "cardapios" e exibir a quantidade e o preço total de pratos por unidade para uma categoria dada
    public static String exibeResumoCategoria(String categoria){
        String resumo = "";
        int qtde = 0;
        float precoTotal = 0f;
        for (Cardapio c: cardapios){
            int qtdeUnidade = 0;
            float precoTotalUnidade = 0f;
            resumo += "     Unidade " + c.unidade + ": ";
            for (Prato p: c.pratos){
                if (p.getCategoria().equalsIgnoreCase(categoria)){
                    qtdeUnidade += 1;
                    precoTotalUnidade += p.getPreco();
                }
            }
            resumo += "Quantidade = " + qtdeUnidade + ", " + "Preço = " + String.format("R$ %.2f", precoTotalUnidade) + "\n";
            qtde += qtdeUnidade;
            precoTotal += precoTotalUnidade;
        }
        if (qtde > 0){
            resumo += "     Total: Quantidade = " + qtde + ", " + "Preço = " + String.format("R$ %.2f", precoTotal) + "\n";
            return resumo;
        }
        else return "Categoria inexistente";
    }

    // método que vai manipular a lista estática "cardapios" e exibir, por unidade, os pratos e seus preços para uma categoria dada
    public static String exibeDetalhesCategoria(String categoria){
        String detalhes = "";
        for (Cardapio c: cardapios){
            detalhes += "     Unidade " + c.unidade + ":\n";
            for (Prato p: c.pratos){
                if (p.getCategoria().equalsIgnoreCase(categoria)){
                    detalhes += "     " + p.exibeInfoPrato() + "\n";
                }
            }
            detalhes += "\n";
        }
        return detalhes;
    }

    public String exibePratosUnidade(){    // "5 - Implemente um método na classe Cardapio para exibir todos os pratos do cardápio." Seria isso? Não é usado...
        String pratosUnidade = "";
        pratosUnidade += "     Unidade " + unidade + ":\n";
        for (Prato p: pratos){
            pratosUnidade += "     " + p.exibeInfoPrato() + "\n";
        }
        pratosUnidade += "\n";
        return pratosUnidade;
    }

}
