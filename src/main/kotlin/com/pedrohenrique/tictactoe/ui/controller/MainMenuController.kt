// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.ui.controller

import javafx.animation.Interpolator
import javafx.animation.TranslateTransition
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.util.Duration

/**
 * O Controller para a nossa tela de Menu Principal (main_menu.fxml).
 * Esta classe, escrita em Kotlin, contém a lógica que dá vida aos componentes da UI.
 * Ela é responsável por:
 * 1. Manipular os eventos, como cliques nos botões.
 * 2. Acessar e manipular os elementos da UI definidos no FXML (como o título).
 * 3. Executar animações e outras lógicas de apresentação.
 */
class MainMenuController {

    /**
     * A anotação @FXML é a cola mágica entre o FXML e este Controller.
     * Ela instrui o FXMLLoader a injetar (atribuir) o componente com fx:id="titleLabel"
     * do nosso arquivo FXML para esta propriedade.
     *
     * 'lateinit var' é um recurso do Kotlin que nos permite declarar uma propriedade não nula
     * que será inicializada mais tarde. Isso é perfeito aqui, pois o JavaFX garante que
     * a propriedade será inicializada antes de a usarmos.
     */
    @FXML
    private lateinit var titleLabel: Label

    /**
     * O método initialize() é um método especial que o FXMLLoader chama automaticamente
     * DEPOIS que todos os campos anotados com @FXML foram injetados.
     * É o lugar ideal para configurar o estado inicial da nossa tela, como iniciar animações.
     */
    @FXML
    fun initialize() {
        // Vamos criar uma animação simples para o título, fazendo-o "flutuar" suavemente.
        val translateTransition = TranslateTransition(Duration.seconds(2.0), titleLabel)
        translateTransition.byY = -10.0 // Mover 10 pixels para cima.
        translateTransition.interpolator = Interpolator.EASE_BOTH // Suaviza o início e o fim da animação.
        translateTransition.cycleCount = TranslateTransition.INDEFINITE // Repetir indefinidamente.
        translateTransition.isAutoReverse = true // Faz o movimento de ida e volta.
        translateTransition.play() // Inicia a animação.
    }

    /**
     * Este método é chamado quando o botão "Jogar" é clicado.
     * A ligação é feita no FXML através do atributo onAction="#handlePlayButtonAction".
     * Por enquanto, apenas imprimiremos no console. No futuro, este método irá navegar
     * para a tela de configuração da partida.
     *
     * @param event O evento de ação gerado pelo clique do botão (não utilizado aqui, mas necessário na assinatura).
     */
    @FXML
    private fun handlePlayButtonAction(event: ActionEvent) {
        println("Botão 'Jogar' clicado! Navegando para a tela de setup do jogo...")
        // Futuramente, aqui teremos o código para carregar a cena 'game_setup.fxml'.
    }

    /**
     * Este método é chamado quando o botão "Configurações" é clicado.
     * A ligação é feita no FXML através do atributo onAction="#handleSettingsButtonAction".
     * Por enquanto, apenas imprimiremos no console. No futuro, este método irá navegar
     * para a tela de configurações de tema.
     *
     * @param event O evento de ação gerado pelo clique do botão.
     */
    @FXML
    private fun handleSettingsButtonAction(event: ActionEvent) {
        println("Botão 'Configurações' clicado! Navegando para a tela de configurações...")
        // Futuramente, aqui teremos o código para carregar a cena 'settings.fxml'.
    }

    /**
     * Este método é chamado quando o botão "Sair" é clicado.
     * A ligação é feita no FXML através do atributo onAction="#handleExitButtonAction".
     * Ele encerra a aplicação de forma limpa.
     *
     * @param event O evento de ação gerado pelo clique do botão.
     */
    @FXML
    private fun handleExitButtonAction(event: ActionEvent) {
        println("Botão 'Sair' clicado! Encerrando a aplicação.")
        // Platform.exit() é a maneira correta e segura de fechar uma aplicação JavaFX.
        // Ele executa os procedimentos de desligamento necessários.
        Platform.exit()
    }
}