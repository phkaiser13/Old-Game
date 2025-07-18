// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.game;

/**
 * Representa o tabuleiro 3x3 do Jogo da Velha.
 * Esta classe é um modelo de dados puro. Sua única responsabilidade é manter o estado
 * das células do tabuleiro e fornecer métodos para interagir com elas (colocar uma marca,
 * verificar se uma célula está vazia, etc.).
 * Ela não contém nenhuma lógica de regras do jogo, como verificação de vitória.
 */
public class Board {

    // A constante para o tamanho do nosso tabuleiro. Usar uma constante torna o código
    // mais legível e fácil de manter. Se quiséssemos fazer um Jogo da Velha 4x4,
    // só precisaríamos mudar este valor.
    public static final int SIZE = 3;

    // A estrutura de dados principal: uma matriz (array 2D) do nosso enum 'Player'.
    // Cada posição na matriz pode conter 'Player.X', 'Player.O', ou 'null' se a célula estiver vazia.
    // O uso do enum 'Player' em vez de 'char' ou 'int' nos dá segurança de tipo.
    private final Player[][] grid;

    /**
     * Construtor da classe Board.
     * Inicializa um novo tabuleiro 3x3, com todas as células vazias (null).
     */
    public Board() {
        // Instancia a matriz com o tamanho definido.
        this.grid = new Player[SIZE][SIZE];
        // Por padrão, em Java, um array de objetos é inicializado com 'null' em todas as posições,
        // que é exatamente o que queremos para representar um tabuleiro vazio.
    }

    /**
     * Tenta colocar a marca de um jogador em uma célula específica do tabuleiro.
     *
     * @param row    A linha (0-2) onde colocar a marca.
     * @param col    A coluna (0-2) onde colocar a marca.
     * @param player O jogador (Player.X ou Player.O) a ser colocado.
     * @return 'true' se a marca foi colocada com sucesso (célula estava vazia), 'false' caso contrário
     *         (célula já ocupada ou coordenadas inválidas).
     */
    public boolean placeMark(int row, int col, Player player) {
        // Programação defensiva: primeiro, validamos as entradas.
        // Verificamos se as coordenadas estão dentro dos limites do tabuleiro.
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false; // Coordenadas fora do tabuleiro.
        }

        // Verificamos se a célula já está ocupada.
        if (grid[row][col] != null) {
            return false; // Célula não está vazia.
        }

        // Se todas as validações passaram, colocamos a marca do jogador na célula.
        grid[row][col] = player;
        return true; // Operação bem-sucedida.
    }

    /**
     * Retorna o jogador que ocupa uma determinada célula.
     *
     * @param row A linha (0-2) da célula.
     * @param col A coluna (0-2) da célula.
     * @return O 'Player' na célula, ou 'null' se a célula estiver vazia ou as coordenadas forem inválidas.
     */
    public Player getPlayerAt(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return null; // Retorna null para coordenadas inválidas.
        }
        return grid[row][col];
    }

    /**
     * Verifica se o tabuleiro está completamente cheio, o que indica um empate se ninguém venceu.
     *
     * @return 'true' se não houver mais células vazias, 'false' caso contrário.
     */
    public boolean isFull() {
        // Itera por cada célula do tabuleiro.
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Se encontrarmos UMA ÚNICA célula vazia (null), o tabuleiro não está cheio.
                if (grid[i][j] == null) {
                    return false;
                }
            }
        }
        // Se o loop terminar sem encontrar nenhuma célula vazia, o tabuleiro está cheio.
        return true;
    }
    /**
     * Construtor de cópia.
     * Cria uma nova instância do tabuleiro que é uma cópia exata de outro tabuleiro.
     * Isso é essencial para algoritmos como o Minimax, que precisam explorar futuros
     * estados do jogo sem modificar o tabuleiro real.
     *
     * @param other O tabuleiro a ser copiado.
     */
    public Board(Board other) {
        this.grid = new Player[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.grid[i][j] = other.grid[i][j];
            }
        }
    }


    /**
     * Limpa o tabuleiro, redefinindo todas as células para o estado vazio (null).
     * Essencial para iniciar uma nova partida.
     */
    public void clear() {
        // Itera por todo o tabuleiro, definindo cada célula como null.
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = null;
            }
        }
    }
}