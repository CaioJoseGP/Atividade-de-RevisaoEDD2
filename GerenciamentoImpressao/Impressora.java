import java.util.Scanner;

class TrabalhoImpressao {
    int idAluno;
    String nomeArquivo;
    int numeroPaginas;

    public TrabalhoImpressao(int idAluno, String nomeArquivo, int numeroPaginas) {
        this.idAluno = idAluno;
        this.nomeArquivo = nomeArquivo;
        this.numeroPaginas = numeroPaginas;
    }

    public String obterDetalhes() {
        return "ID do Aluno: " + idAluno + ", Arquivo: " + nomeArquivo + ", Páginas: " + numeroPaginas;
    }
}

class No {
    TrabalhoImpressao trabalho;
    No proximo;

    public No(TrabalhoImpressao trabalho) {
        this.trabalho = trabalho;
        this.proximo = null;
    }
}

class FilaImpressao {
    private No frente, tras;

    public FilaImpressao() {
        this.frente = this.tras = null;
    }

    public void enfileirar(TrabalhoImpressao trabalho) {
        No novoNo = new No(trabalho);
        if (tras == null) {
            frente = tras = novoNo;
        } else {
            tras.proximo = novoNo;
            tras = novoNo;
        }
    }

    public TrabalhoImpressao desenfileirar() {
        if (frente == null) {
            return null;
        }
        TrabalhoImpressao trabalho = frente.trabalho;
        frente = frente.proximo;
        if (frente == null) {
            tras = null;
        }
        return trabalho;
    }

    public boolean estaVazia() {
        return frente == null;
    }

    public void exibirFila() {
        if (estaVazia()) {
            System.out.println("A fila de impressão está vazia!\n");
            return;
        }
        No atual = frente;
        System.out.println("Fila de impressão:");
        while (atual != null) {
            System.out.println(atual.trabalho.obterDetalhes());
            atual = atual.proximo;
        }
        System.out.println();
    }
}

class Impressora {
    private static FilaImpressao filaImpressao = new FilaImpressao();
    private static Scanner scanner = new Scanner(System.in);

    public static void adicionarTrabalho() {
        System.out.print("Digite o ID do aluno (número inteiro): ");
        int idAluno = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();
        System.out.print("Digite o número de páginas (número inteiro): ");
        int numeroPaginas = scanner.nextInt();

        filaImpressao.enfileirar(new TrabalhoImpressao(idAluno, nomeArquivo, numeroPaginas));
        System.out.println("Trabalho adicionado à fila de impressão!\n");
    }

    public static void imprimirProximoTrabalho() {
        TrabalhoImpressao trabalho = filaImpressao.desenfileirar();
        if (trabalho == null) {
            System.out.println("A fila de impressão está vazia!\n");
        } else {
            System.out.println("Imprimindo: " + trabalho.obterDetalhes() + "\n");
        }
    }

    public static void exibirFila() {
        filaImpressao.exibirFila();
    }

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\nSistema de Gerenciamento de Impressão");
            System.out.println("1. Adicionar trabalho");
            System.out.println("2. Imprimir próximo trabalho");
            System.out.println("3. Exibir fila de impressão");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarTrabalho();
                    break;
                case 2:
                    imprimirProximoTrabalho();
                    break;
                case 3:
                    exibirFila();
                    break;
                case 4:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }
        } while (opcao != 4);

        scanner.close();
    }
}