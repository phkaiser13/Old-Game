// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.ui.controller

import com.pedrohenrique.tictactoe
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.stage.Stage

/**
 * O Controller para a tela do tabuleiro de jogo (game_board.fxml).
 * Esta classe conecta a interface gráfica com o motor de jogo (GameEngine),
 * orquestrando toda a partida.
 */
class GameBoardController {

    // Injeção dos componentes FXML.
    @FXML private lateinit var statusLabel: Label
    @FXML private lateinit var gameGrid: GridPane

    // Propriedades para armazenar as configurações da partida e a instância do motor de jogo.
    // 'lateinit' é usado porque elas serão inicializadas externamente através do método initData.
    private lateinit var gameMode: GameMode
    private var difficulty: Difficulty? = null // Nullable para o modo PvP
    private lateinit var firstPlayer: Player
    private lateinit var gameEngine: GameEngine

    // Matriz para manter uma referência a cada botão do tabuleiro, facilitando o acesso.
    private lateinit var boardButtons: Array<Array<Button>>

    /**
     * Este método NÃO é o 'initialize()' padrão. Ele é um inicializador customizado
     * que é chamado pelo GameSetupController para passar os dados da partida.
     * Este é um padrão comum para passar dados entre cenas em JavaFX.
     */
    fun initData(mode: GameMode, diff: Difficulty?, startPlayer: Player) {
        this.gameMode = mode
        this.difficulty = diff
        this.firstPlayer = startPlayer

        // Agora que temos as configurações, podemos criar o motor de jogo e iniciar o tabuleiro.
        setupNewGame()
    }

    /**
     * Configura uma nova partida. Pode ser chamado na inicialização ou ao reiniciar.
     */
    private fun setupNewGame() {
        // Cria uma nova instância do motor de jogo com as configurações definidas.
        gameEngine = GameEngine(gameMode, difficulty, firstPlayer)
        boardButtons = Array(Board.SIZE) { Array(Board.SIZE) { Button() } }

        gameGrid.isDisable = false // Garante que a grade esteja habilitada.
        gameGrid.children.clear() // Limpa qualquer conteúdo anterior (importante para o 'Reiniciar').

        // Cria dinamicamente os botões do tabuleiro.
        for (row in 0 until Board.SIZE) {
            for (col in 0 until Board.SIZE) {
                val cellButton = Button()
                cellButton.prefWidth = 120.0  // Tamanho do botão
                cellButton.prefHeight = 120.0
                cellButton.alignment = Pos.CENTER
                cellButton.styleClass.add("game-grid-button") // Classe CSS para estilização.

                // Define a ação a ser executada quando o botão é clicado.
                cellButton.setOnAction { handleCellClick(row, col) }

                boardButtons[row][col] = cellButton
                gameGrid.add(cellButton, col, row) // Adiciona o botão à grade na posição (col, row).
            }
        }
        // Atualiza a UI para refletir o estado inicial do jogo.
        updateUI()
    }

    /**
     * Manipulador de evento para o clique em uma célula do tabuleiro.
     */
    private fun handleCellClick(row: Int, col: Int) {
        // Passa a jogada para o motor do jogo.
        val result = gameEngine.makeMove(row, col)

        // Se a jogada foi inválida (jogo acabou ou célula ocupada), não fazemos nada.
        if (result == GameEngine.MoveResult.INVALID) {
            return
        }

        // Atualiza a UI após qualquer jogada válida.
        updateUI()

        // Se a jogada resultou em vitória ou empate, finaliza o jogo.
        if (result == GameEngine.MoveResult.WIN || result == GameEngine.MoveResult.DRAW) {
            endGame()
        }
        // FUTURAMENTE: Se for a vez da IA, chamaríamos a jogada da IA aqui.
    }

    /**
     * Atualiza toda a interface do usuário para refletir o estado atual do gameEngine.
     */
    private fun updateUI() {
        // Atualiza o texto do status.
        statusLabel.text = if (gameEngine.isGameOver) {
            when (val winner = gameEngine.winner) {
                null -> "Empate!" // Se o jogo acabou e não há vencedor, é um empate.
                else -> "Jogador $winner Venceu!"
            }
        } else {
            "Vez do Jogador ${gameEngine.currentPlayer}"
        }

        // Atualiza o texto e o estilo de cada botão no tabuleiro.
        for (row in 0 until Board.SIZE) {
            for (col in 0 until Board.SIZE) {
                val player = gameEngine.board.getPlayerAt(row, col)
                val button = boardButtons[row][col]
                button.text = player?.name ?: "" // Usa o nome do enum (X ou O) ou string vazia se for nulo.

                // Adiciona classes CSS para colorir 'X' e 'O' de forma diferente.
                button.styleClass.removeAll("player-x", "player-o")
                if (player == Player.X) {
                    button.styleClass.add("player-x")
                } else if (player == Player.O) {
                    button.styleClass.add("player-o")
                }
            }
        }
    }

    /**
     * Finaliza o jogo, desabilitando os botões do tabuleiro para impedir mais jogadas.
     */
    private fun endGame() {
        gameGrid.isDisable = true
    }

    /**
     * Manipulador para o botão "Reiniciar".
     */
    @FXML
    private fun handleRestartAction(event: ActionEvent) {
        setupNewGame() // Simplesmente configura e inicia um jogo completamente novo.
    }

    /**
     * Manipulador para o botão "Menu Principal".
     */
    @FXML
    private fun handleBackToMenuAction(event: ActionEvent) {
        // Para navegar, precisamos obter o Stage atual. Podemos pegá-lo de qualquer nó na cena.
        val stage = statusLabel.scene.window as Stage

        // Carrega a cena do menu principal.
        val loader = FXMLLoader(javaClass.getResource("/fxml/main_menu.fxml"))
        val root: Parent = loader.load()
        stage.scene = Scene(root)
    }
}