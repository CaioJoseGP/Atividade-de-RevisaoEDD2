package LabirintoMino;

import java.util.Scanner;

public class Labirinto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o tamanho do labirinto (N x N): ");
        int tamanho = scanner.nextInt();
        scanner.nextLine(); 
        
        char[][] labirinto = new char[tamanho][tamanho];
        boolean[][] visitado = new boolean[tamanho][tamanho];
        
        System.out.println("\n\nDigite o labirinto linha por linha: ");
        for (int i = 0; i < tamanho; i++) {
            String linha = scanner.nextLine();
            for (int j = 0; j < tamanho; j++) {
                labirinto[i][j] = linha.charAt(j);
            }
        }
        
        scanner.close();
        
        if (resolverLabirinto(labirinto, visitado)) {
            System.out.println("\n\nCaminho realizado por Teseu: ");
            imprimirLabirinto(labirinto);

        } else {
            System.out.println("Labirinto sem saída.");
        }
    }
    
    public static boolean resolverLabirinto(char[][] labirinto, boolean[][] visitado) {
        int[] entrada = encontrarPosicao(labirinto, 'E');
        int[] saida = encontrarPosicao(labirinto, 'S');
        
        if (entrada[0] == -1 || saida[0] == -1) {
            return false;
        }
        
        return buscarCaminho(labirinto, visitado, entrada[0], entrada[1], saida[0], saida[1]);
    }
    
    public static boolean buscarCaminho(char[][] labirinto, boolean[][] visitado, int x, int y, int sx, int sy) {
        if (x < 0 || y < 0 || x >= labirinto.length || y >= labirinto[0].length || labirinto[x][y] == '#' || visitado[x][y]) {
            return false;
        }
        
        if (x == sx && y == sy) {
            return true;
        }
        
        if (labirinto[x][y] != 'E') {
            labirinto[x][y] = '*';
        }
        visitado[x][y] = true;
        
        if (buscarCaminho(labirinto, visitado, x + 1, y, sx, sy) || 
            buscarCaminho(labirinto, visitado, x - 1, y, sx, sy) || 
            buscarCaminho(labirinto, visitado, x, y + 1, sx, sy) || 
            buscarCaminho(labirinto, visitado, x, y - 1, sx, sy)) {
            return true;
        }
        
        if (labirinto[x][y] != 'E') {
            labirinto[x][y] = '.';
        }
        return false;
    }
    
    public static int[] encontrarPosicao(char[][] labirinto, char alvo) {
        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[i].length; j++) {
                if (labirinto[i][j] == alvo) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
    
    public static void imprimirLabirinto(char[][] labirinto) {
        for (char[] linha : labirinto) {
            for (char celula : linha) {
                System.out.print(celula + " ");
            }
            System.out.println();
        }
    }
}
