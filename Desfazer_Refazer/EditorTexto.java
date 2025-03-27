package Desfazer_Refazer;

import java.util.Scanner;

class No {
    String comando;
    String dado;
    No proximo;

    public No(String comando, String dado) {
        this.comando = comando;
        this.dado = dado;
        this.proximo = null;
    }
}

class Pilha {
    private No topo;

    public void push(String comando, String dado) {
        No novoNo = new No(comando, dado);
        novoNo.proximo = topo;
        topo = novoNo;
    }

    public No pop() {
        if (isEmpty()) {
            return null;
        }
        No noRemovido = topo;
        topo = topo.proximo;
        return noRemovido;
    }

    public No peek() {
        return topo;
    }

    public boolean isEmpty() {
        return topo == null;
    }
}

public class EditorTexto {
    private static StringBuilder texto = new StringBuilder();
    private static Pilha pilhaDesfazer = new Pilha();
    private static Pilha pilhaRefazer = new Pilha();
    private static Scanner scanner = new Scanner(System.in);

    public static void inserir(String dado) {
        texto.append(dado + " ");
        pilhaDesfazer.push("INSERIR", dado);
        pilhaRefazer = new Pilha();
    }

    public static void remover(int n) {
        if (n > texto.length()) {
            n = texto.length();
        }
        String removido = texto.substring(texto.length() - n);
        texto.delete(texto.length() - n, texto.length());
        pilhaDesfazer.push("REMOVER", removido);
        pilhaRefazer = new Pilha();
    }

    public static void desfazer() {
        No ultimaOperacao = pilhaDesfazer.pop();
        if (ultimaOperacao == null) {
            System.out.println("Nada para desfazer.");
            return;
        }
        if (ultimaOperacao.comando.equals("INSERIR")) {
            texto.delete(texto.length() - ultimaOperacao.dado.length(), texto.length());
            pilhaRefazer.push("INSERIR", ultimaOperacao.dado);
        } else if (ultimaOperacao.comando.equals("REMOVER")) {
            texto.append(ultimaOperacao.dado);
            pilhaRefazer.push("REMOVER", ultimaOperacao.dado);
        }
    }

    public static void refazer() {
        No ultimaOperacao = pilhaRefazer.pop();
        if (ultimaOperacao == null) {
            System.out.println("Nada para refazer.");
            return;
        }
        if (ultimaOperacao.comando.equals("INSERIR")) {
            texto.append(ultimaOperacao.dado);
            pilhaDesfazer.push("INSERIR", ultimaOperacao.dado);

        } else if (ultimaOperacao.comando.equals("REMOVER")) {
            texto.delete(texto.length() - ultimaOperacao.dado.length(), texto.length());
            pilhaDesfazer.push("REMOVER", ultimaOperacao.dado);
        }
    }

    public static void imprimir() {
        System.out.println(texto.toString());
    }

    public static void main(String[] args) {
        while (true) {
            System.out.print("\n\nDigite um comando (INSERIR <texto>, REMOVER <n>, DESFAZER, REFAZER, IMPRIMIR ou SAIR): ");

            String comando = scanner.nextLine();
            String[] partes = comando.split(" ", 2);
            String acao = partes[0].toUpperCase();
            String argumento = partes.length > 1 ? partes[1] : "";

            switch (acao) {
                case "INSERIR":
                    inserir(argumento);
                    break;
                case "REMOVER":
                    remover(Integer.parseInt(argumento));
                    break;
                case "DESFAZER":
                    desfazer();
                    break;
                case "REFAZER":
                    refazer();
                    break;
                case "IMPRIMIR":
                    imprimir();
                    break;
                case "SAIR":
                    System.out.println("Encerrando o editor de texto.");
                    return;
                default:
                    System.out.println("Comando inválido!");
            }
        }
    }
}
