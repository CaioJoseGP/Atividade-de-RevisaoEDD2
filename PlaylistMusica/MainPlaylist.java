import java.util.Scanner;

class Musica {
    String titulo;
    String artista;
    Musica proxima;

    public Musica(String titulo, String artista) {
        this.titulo = titulo;
        this.artista = artista;
        this.proxima = null;
    }
}

class Playlist {
    private Musica primeira;

    public void adicionarMusica(String titulo, String artista) {
        Musica novaMusica = new Musica(titulo, artista);
        if (primeira == null) {
            primeira = novaMusica;
        } else {
            Musica atual = primeira;
            while (atual.proxima != null) {
                atual = atual.proxima;
            }
            atual.proxima = novaMusica;
        }
        System.out.println("\nMúsica adicionada com sucesso!");
    }

    public void removerMusica(String titulo) {
        if (primeira == null) {
            System.out.println("\nA playlist está vazia.");
            return;
        }

        if (primeira.titulo.equalsIgnoreCase(titulo)) {
            primeira = primeira.proxima;
            System.out.println("\nMúsica removida com sucesso!");
            return;
        }

        Musica atual = primeira;
        while (atual.proxima != null && !atual.proxima.titulo.equalsIgnoreCase(titulo)) {
            atual = atual.proxima;
        }

        if (atual.proxima == null) {
            System.out.println("\nMúsica não encontrada.");
        } else {
            atual.proxima = atual.proxima.proxima;
            System.out.println("\nMúsica removida com sucesso!");
        }
    }

    public void buscarMusica(String titulo) {
        Musica atual = primeira;
        while (atual != null) {
            if (atual.titulo.equalsIgnoreCase(titulo)) {
                System.out.println("Música encontrada: " + atual.titulo + " - " + atual.artista);
                return;
            }
            atual = atual.proxima;
        }
        System.out.println("\nMúsica não encontrada.");
    }

    public void listarMusicas() {
        if (primeira == null) {
            System.out.println("\nA playlist está vazia.");
            return;
        }
        System.out.println("Playlist:");
        Musica atual = primeira;
        while (atual != null) {
            System.out.println("- " + atual.titulo + " - " + atual.artista);
            atual = atual.proxima;
        }
    }
}

public class MainPlaylist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist playlist = new Playlist();

        while(true) {
            System.out.println("\nGerenciador de Playlist");
            System.out.println("1. Adicionar Música");
            System.out.println("2. Remover Música");
            System.out.println("3. Buscar Música");
            System.out.println("4. Listar Músicas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\n\nDigite o título da música: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o artista: ");
                    String artista = scanner.nextLine();
                    playlist.adicionarMusica(titulo, artista);
                    break;
                case 2:
                    System.out.print("\n\nDigite o título da música a remover: ");
                    String tituloRemover = scanner.nextLine();
                    playlist.removerMusica(tituloRemover);
                    break;
                case 3:
                    System.out.print("\n\nDigite o título da música a buscar: ");
                    String tituloBuscar = scanner.nextLine();
                    playlist.buscarMusica(tituloBuscar);
                    break;
                case 4:
                    playlist.listarMusicas();
                    break;
                case 5:
                    System.out.println("\n\nSaindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("\n\nOpção inválida, tente novamente.");
            }
        }
    }
}