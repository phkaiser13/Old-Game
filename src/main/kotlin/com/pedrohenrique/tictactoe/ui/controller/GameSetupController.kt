// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.ui.controller

import com.pedrohenrique.tictactoe.game.Difficulty
import com.pedrohenrique.tictactoe.game.GameMode
import com.pedrohenrique.tictactoe.game.Player
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.ChoiceBox
import javafx.scene.layout.HBox
import javafx.stage.Stage

/**
 * Controller para a tela de Configuração da Partida (game_setup.fxml).
 * Esta classe gerencia a lógica para o usuário selecionar as opções do jogo
 * antes de começar a partida.
 */
class GameSetupController {

    @FXML private lateinit var gameModeChoiceBox: ChoiceBox<GameMode>
    @FXML private lateinit var difficultyChoiceBox: ChoiceBox<Difficulty>
    @FXML private lateinit var firstPlayerChoiceBox: ChoiceBox<Player>
    @FXML private lateinit var difficultyBox: HBox

    @FXML
    fun initialize() {
        gameModeChoiceBox.items.addAll(GameMode.values())
        difficultyChoiceBox.items.addAll(Difficulty.values())
        firstPlayerChoiceBox.items.addAll(Player.values())

        gameModeChoiceBox.value = GameMode.PLAYER_VS_AI
        difficultyChoiceBox.value = Difficulty.EASY
        firstPlayerChoiceBox.value = Player.X

        gameModeChoiceBox.valueProperty().addListener { _, _, newValue ->
            difficultyBox.isVisible = (newValue == GameMode.PLAYER_VS_AI)
        }
        difficultyBox.isVisible = (gameModeChoiceBox.value == GameMode.PLAYER_VS_AI)
    }

    /**
     * Chamado quando o botão "Iniciar Jogo" é clicado.
     * Coleta todas as configurações, carrega a cena do tabuleiro e passa os dados
     * para o GameBoardController.
     */
    @FXML
    private fun handleStartGameAction(event: ActionEvent) {
        // 1. Coleta os valores selecionados de cada ChoiceBox.
        val selectedMode = gameModeChoiceBox.value
        val selectedFirstPlayer = firstPlayerChoiceBox.value
        val selectedDifficulty = if (selectedMode == GameMode.PLAYER_VS_AI) {
            difficultyChoiceBox.value
        } else {
            null
        }

        // ==================================================================
        // =================== LÓGICA DE NAVEGAÇÃO ATUALIZADA =================
        // ==================================================================
        try {
            // 2. Cria o FXMLLoader para a cena do tabuleiro.
            val loader = FXMLLoader(javaClass.getResource("/fxml/game_board.fxml"))
            val root: Parent = loader.load()

            // 3. **PASSO CRUCIAL**: Obtém a instância do controller da cena que acabamos de carregar.
            //    Isso só é possível DEPOIS de chamar loader.load().
            val gameBoardController = loader.getController<GameBoardController>()

            // 4. Chama o nosso método inicializador customizado para passar os dados da partida.
            gameBoardController.initData(selectedMode, selectedDifficulty, selectedFirstPlayer)

            // 5. Procede com a troca de cena normalmente.
            val stage = gameModeChoiceBox.scene.window as Stage
            stage.scene = Scene(root)
            stage.show()

        } catch (e: Exception) {
            println("Erro ao carregar a tela do jogo.")
            e.printStackTrace()
        }
    }

    @FXML
    private fun handleBackButtonAction(event: ActionEvent) {
        try {
            val loader = FXMLLoader(javaClass.getResource("/fxml/main_menu.fxml"))
            val root: Parent = loader.load()
            val stage = difficultyBox.scene.window as Stage
            stage.scene = Scene(root)
            stage.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}