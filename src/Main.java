import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc1 = new Scanner(System.in);

        System.out.println("Seja bem-vindo(a)!");
        System.out.print("Quantas unidades serão cadastradas? ");
        int numUnidades = sc1.nextInt();
        sc1.nextLine();

        String filePath = "";      // variável que vai armazenar o caminho do arquivo a ser lido
        FileReader fr = null;      // o objeto fr será usado para ler o arquivo no disco
        BufferedReader br = null;      // cria um buffer usado para melhorar a performance da leitura do arquivo

        for (int i = 0; i < numUnidades; i++){      // laço para ler os arquivos, na quantidade indicada pelo usuário

            System.out.print("Arquivo da Unidade " + (i+1) + ": ");
            filePath = "src/" + sc1.nextLine();

            try {        // tratamento de exceção para o caso de haver problemas na abertura/leitura dos arquivos
                fr = new FileReader(filePath);
                br = new BufferedReader(fr);
                String unidade = br.readLine().substring(8);      // Lê a primeira linha do arquivo e elimina a substring "Unidade ", retornando somente o nome da unidade
                Cardapio cardapio = new Cardapio(unidade);                  // Com o nome, a instância de Cardapio pode ser criada

                // Cada linha lida subsequente deverá ser quebrada em três elementos: nome, preço e categoria
                String linha;
                String[] textoSeparado;
                while ((linha = br.readLine()) != null){    // este laço garante que o arquivo será lido até que não haja mais linhas por ler
                    textoSeparado = linha.split("; ");
                    Prato prato = new Prato(textoSeparado[0], Float.parseFloat(textoSeparado[1]), textoSeparado[2]);   // cada linha gera as 3 informações necessárias para instanciar um Prato
                    cardapio.adicionaPrato(prato);
                }

            }
            catch (IOException e) {   // tratamento de exceção para o caso de haver problemas na abertura/leitura dos arquivos
                System.out.println("Erro: " + e.getMessage());
            }

            finally {
                try {     // tratamento de exceção para o caso de haver problemas no fechamento dos objetos fr e br (enquanto abertos, impedem acesso de outros programas ao arquivo)
                    if (br != null) {
                        br.close();
                    }
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }

        }

        System.out.print("Escolha uma categoria, ou digite “0” para encerrar: ");
        String resposta = sc1.nextLine();

        while (!resposta.equals("0")){          // laço para garantir que o usuário poderá fazer quantas pesquisas desejar
            System.out.println(Cardapio.exibeResumoCategoria(resposta));
            if (!Cardapio.exibeResumoCategoria(resposta).equalsIgnoreCase("Categoria inexistente")){
                System.out.print("Exibir Detalhes (“s” ou “n”)? ");
                String detalha = sc1.nextLine();
                if (detalha.equalsIgnoreCase("s")){          // permite que o usuário detalhe as informações retornadas pelo método exibeResumoCategoria
                    System.out.print(Cardapio.exibeDetalhesCategoria(resposta));
                }
            }

            System.out.print("Escolha uma categoria, ou digite “0” para encerrar: ");
            resposta = sc1.nextLine();

        }

    }

}