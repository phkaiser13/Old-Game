// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEngine {

    public enum MoveResult { VALID, WIN, DRAW, INVALID }

    private final Board board;
    private Player currentPlayer;
    private boolean isGameOver;
    private Player winner;

    private final GameMode gameMode;
    private final Difficulty difficulty;
    private final Player humanPlayer; // Adicionado para clareza no Minimax
    private final Player aiPlayer;    // Adicionado para clareza no Minimax
    private final Random random = new Random();

    public GameEngine(GameMode gameMode, Difficulty difficulty, Player firstPlayer) {
        this.board = new Board();
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.currentPlayer = firstPlayer;
        this.isGameOver = false;
        this.winner = null;

        // Define quem é o humano e quem é a IA. Assumimos que o primeiro jogador é o humano.
        // Se o modo for PvP, essas variáveis não são realmente usadas.
        this.humanPlayer = firstPlayer;
        this.aiPlayer = (firstPlayer == Player.X) ? Player.O : Player.X;

        // Se a IA for o primeiro a jogar, aciona sua jogada inicial.
        if (gameMode == GameMode.PLAYER_VS_AI && currentPlayer == aiPlayer) {
            makeAiMove();
        }
    }

    public MoveResult makeMove(int row, int col) {
        if (isGameOver || !board.placeMark(row, col, currentPlayer)) {
            return MoveResult.INVALID;
        }

        if (checkForWin(row, col)) {
            isGameOver = true;
            winner = currentPlayer;
            return MoveResult.WIN;
        }

        if (board.isFull()) {
            isGameOver = true;
            return MoveResult.DRAW;
        }

        switchPlayer();

        // Se, após a jogada do humano, for a vez da IA, aciona a jogada da IA.
        if (gameMode == GameMode.PLAYER_VS_AI && !isGameOver && currentPlayer == aiPlayer) {
            return makeAiMove();
        }

        return MoveResult.VALID;
    }

    /**
     * Orquestra a jogada da IA com base na dificuldade selecionada.
     */
    private MoveResult makeAiMove() {
        int[] bestMove;
        if (difficulty == Difficulty.EASY) {
            bestMove = findRandomMove();
        } else { // HARD
            bestMove = findBestMove();
        }
        // A IA faz sua jogada usando o mesmo método makeMove, mas sem acionar outra IA.
        // Isso garante que a lógica de vitória/empate seja verificada corretamente.
        return makeMove(bestMove[0], bestMove[1]);
    }

    /**
     * Lógica da IA FÁCIL: Encontra uma célula vazia aleatória.
     */
    private int[] findRandomMove() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.getPlayerAt(i, j) == null) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        return emptyCells.get(random.nextInt(emptyCells.size()));
    }

    /**
     * Lógica da IA DIFÍCIL: Ponto de entrada para o algoritmo Minimax.
     */
    private int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.getPlayerAt(i, j) == null) {
                    Board newBoard = new Board(board); // Usa o construtor de cópia
                    newBoard.placeMark(i, j, aiPlayer);
                    int score = minimax(newBoard, 0, false);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    /**
     * Algoritmo Minimax recursivo.
     * @param currentBoard O tabuleiro hipotético a ser avaliado.
     * @param depth A profundidade da recursão (usado para otimizações, não essencial aqui).
     * @param isMaximizing 'true' se for o turno da IA (queremos maximizar a pontuação),
     *                     'false' se for o turno do humano (queremos minimizar a pontuação).
     * @return A pontuação daquele ramo do jogo.
     */
    private int minimax(Board currentBoard, int depth, boolean isMaximizing) {
        // Verifica se o estado hipotético é um estado terminal (vitória/derrota/empate).
        if (checkWinOnBoard(currentBoard, aiPlayer)) return 10;
        if (checkWinOnBoard(currentBoard, humanPlayer)) return -10;
        if (currentBoard.isFull()) return 0;

        if (isMaximizing) { // Turno da IA
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    if (currentBoard.getPlayerAt(i, j) == null) {
                        Board newBoard = new Board(currentBoard);
                        newBoard.placeMark(i, j, aiPlayer);
                        bestScore = Math.max(bestScore, minimax(newBoard, depth + 1, false));
                    }
                }
            }
            return bestScore;
        } else { // Turno do Humano
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    if (currentBoard.getPlayerAt(i, j) == null) {
                        Board newBoard = new Board(currentBoard);
                        newBoard.placeMark(i, j, humanPlayer);
                        bestScore = Math.min(bestScore, minimax(newBoard, depth + 1, true));
                    }
                }
            }
            return bestScore;
        }
    }

    /**
     * Helper para verificar vitória em um tabuleiro hipotético para o Minimax.
     */
    private boolean checkWinOnBoard(Board boardToCheck, Player player) {
        // Lógica de verificação de vitória simplificada para o tabuleiro inteiro.
        // Horizontal
        for (int i = 0; i < Board.SIZE; i++) {
            if (boardToCheck.getPlayerAt(i, 0) == player && boardToCheck.getPlayerAt(i, 1) == player && boardToCheck.getPlayerAt(i, 2) == player) return true;
        }
        // Vertical
        for (int j = 0; j < Board.SIZE; j++) {
            if (boardToCheck.getPlayerAt(0, j) == player && boardToCheck.getPlayerAt(1, j) == player && boardToCheck.getPlayerAt(2, j) == player) return true;
        }
        // Diagonais
        if (boardToCheck.getPlayerAt(0, 0) == player && boardToCheck.getPlayerAt(1, 1) == player && boardToCheck.getPlayerAt(2, 2) == player) return true;
        if (boardToCheck.getPlayerAt(0, 2) == player && boardToCheck.getPlayerAt(1, 1) == player && boardToCheck.getPlayerAt(2, 0) == player) return true;
        return false;
    }

    // ... (métodos restantes como switchPlayer, checkForWin, getters, etc. permanecem os mesmos)
    private void switchPlayer() { currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X; }
    private boolean checkForWin(int row, int col) { /* ... implementação anterior ... */ return checkWinOnBoard(this.board, this.currentPlayer); }
    public Player getCurrentPlayer() { return currentPlayer; }
    public boolean isGameOver() { return isGameOver; }
    public Player getWinner() { return winner; }
    public Board getBoard() { return board; }
    public GameMode getGameMode() { return gameMode; }
}