// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.ui.controller

import com.pedrohenrique.tictactoe.ui.theme.ThemeManager
import javafx.animation.Interpolator
import javafx.animation.TranslateTransition
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage
import javafx.util.Duration

/**
 * O Controller para a nossa tela de Menu Principal (main_menu.fxml).
 */
class MainMenuController {

    @FXML
    private lateinit var titleLabel: Label

    @FXML
    fun initialize() {
        // Animação do título.
        val translateTransition = TranslateTransition(Duration.seconds(2.0), titleLabel)
        translateTransition.byY = -10.0
        translateTransition.interpolator = Interpolator.EASE_BOTH
        translateTransition.cycleCount = TranslateTransition.INDEFINITE
        translateTransition.isAutoReverse = true
        translateTransition.play()
    }

    /**
     * Navega para a tela de configuração da partida.
     */
    @FXML
    private fun handlePlayButtonAction(event: ActionEvent) {
        try {
            val stage = titleLabel.scene.window as Stage
            val loader = FXMLLoader(javaClass.getResource("/fxml/game_setup.fxml"))
            val root: Parent = loader.load()
            val newScene = Scene(root)

            // Garante que a próxima cena mantenha o tema atual.
            ThemeManager.applyCurrentTheme(newScene)

            stage.scene = newScene
            stage.show()
        } catch (e: Exception) {
            println("Erro ao carregar a tela de setup do jogo.")
            e.printStackTrace()
        }
    }

    /**
     * Navega para a tela de configurações de tema.
     */
    @FXML
    private fun handleSettingsButtonAction(event: ActionEvent) {
        // ==================================================================
        // =================== LÓGICA DE NAVEGAÇÃO ATUALIZADA =================
        // ==================================================================
        try {
            // Obtém o Stage (a janela) atual a partir de qualquer componente.
            val stage = titleLabel.scene.window as Stage

            // Carrega o FXML da tela de configurações.
            val loader = FXMLLoader(javaClass.getResource("/fxml/settings.fxml"))
            val root: Parent = loader.load()

            // Cria a nova cena.
            val newScene = Scene(root)

            // **PASSO CRUCIAL**: Aplica o tema atual à nova cena ANTES de exibi-la.
            ThemeManager.applyCurrentTheme(newScene)

            // Define a nova cena no palco.
            stage.scene = newScene
            stage.show()

        } catch (e: Exception) {
            println("Erro ao carregar a tela de configurações.")
            e.printStackTrace()
        }
    }

    /**
     * Encerra a aplicação.
     */
    @FXML
    private fun handleExitButtonAction(event: ActionEvent) {
        Platform.exit()
    }
}