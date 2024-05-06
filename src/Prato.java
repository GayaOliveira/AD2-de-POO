public class Prato {

    private String nome;
    private Float preco;
    private String categoria;

    public Prato(String nome, Float preco, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public Float getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    // não adicionei os setters porque acho que não fazem sentido para a AD (qualquer alteração, suponho que deveria vir de mudança no arquivo lido)

    public String exibeInfoPrato(){     // 2 - Implemente o método na classe Prato para exibir as informações do prato.
        return nome + " -> " + String.format("R$ %.2f", preco);
    }

}
