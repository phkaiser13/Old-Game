// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.ui.controller

import com.pedrohenrique.tictactoe.ui.theme.Theme
import com.pedrohenrique.tictactoe.ui.theme.ThemeManager
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.ChoiceBox
import javafx.stage.Stage

/**
 * Controller para a tela de Configurações (settings.fxml).
 * Gerencia a lógica para a seleção e aplicação de temas visuais.
 */
class SettingsController {

    // Injeção do componente ChoiceBox do nosso FXML.
    @FXML
    private lateinit var themeChoiceBox: ChoiceBox<Theme>

    /**
     * Método de inicialização, chamado automaticamente pelo FXMLLoader.
     * Configura o estado inicial da tela de configurações.
     */
    @FXML
    fun initialize() {
        // 1. Popula a ChoiceBox com os valores do nosso enum Theme.
        themeChoiceBox.items.addAll(Theme.values())

        // 2. Define a seleção inicial da ChoiceBox para ser o tema que já está ativo.
        //    Isso garante que a UI esteja sincronizada com o estado atual da aplicação.
        themeChoiceBox.value = ThemeManager.currentTheme

        // 3. Adiciona um "ouvinte" que reage a mudanças na seleção da ChoiceBox.
        //    Este é o núcleo da funcionalidade de troca de tema.
        themeChoiceBox.valueProperty().addListener { _, _, newTheme ->
            // O 'newTheme' é o novo tema que o usuário acabou de selecionar.
            if (newTheme != null) {
                // Obtemos a cena atual a partir de qualquer componente nela (a própria ChoiceBox).
                val scene = themeChoiceBox.scene
                // Usamos nosso gerenciador centralizado para aplicar o novo tema à cena.
                ThemeManager.applyTheme(scene, newTheme)
            }
        }
    }

    /**
     * Manipulador para o botão "Voltar ao Menu".
     * Navega de volta para a tela do menu principal.
     */
    @FXML
    private fun handleBackButtonAction(event: ActionEvent) {
        try {
            // Obtém o Stage (a janela) atual.
            val stage = themeChoiceBox.scene.window as Stage

            // Carrega o FXML do menu principal.
            val loader = FXMLLoader(javaClass.getResource("/fxml/main_menu.fxml"))
            val root: Parent = loader.load()

            // Cria a nova cena.
            val newScene = Scene(root)

            // **PASSO CRUCIAL**: Antes de mostrar a nova cena, aplicamos o tema atual a ela.
            // Isso garante que, ao voltar, o menu principal já apareça com o tema que
            // o usuário pode ter acabado de selecionar.
            ThemeManager.applyCurrentTheme(newScene)

            // Define a nova cena no palco e a exibe.
            stage.scene = newScene
            stage.show()

        } catch (e: Exception) {
            println("Erro ao voltar para o menu principal.")
            e.printStackTrace()
        }
    }
}