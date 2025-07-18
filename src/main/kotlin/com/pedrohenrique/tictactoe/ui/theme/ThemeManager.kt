// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.ui.theme

import javafx.scene.Scene

/**
 * Um objeto singleton para gerenciar a aplicação de temas na aplicação.
 * Usar um 'object' do Kotlin garante que haverá apenas uma instância deste gerenciador,
 * tornando-o o ponto central e único para todas as operações de tema.
 */
object ThemeManager {

    // Define o caminho para a folha de estilos base que é sempre aplicada.
    private val basePath = "/css/base.css"

    // Mantém o controle do tema atualmente aplicado. Começa com o tema escuro por padrão.
    var currentTheme: Theme = Theme.DARK
        private set // O 'set' é privado para que o tema só possa ser mudado através do método applyTheme.

    /**
     * Aplica um novo tema a uma determinada cena.
     * Este método é a única forma de alterar o tema da aplicação.
     *
     * @param scene A cena na qual o tema será aplicado.
     * @param newTheme O novo tema a ser aplicado.
     */
    fun applyTheme(scene: Scene, newTheme: Theme) {
        // 1. Limpa TODAS as folhas de estilo da cena. Isso é mais robusto do que
        //    tentar remover apenas a antiga, pois garante um estado limpo.
        scene.stylesheets.clear()

        // 2. Adiciona a folha de estilos base primeiro. Ela contém a estrutura.
        scene.stylesheets.add(javaClass.getResource(basePath)?.toExternalForm())

        // 3. Adiciona a folha de estilos do tema específico. Ela contém as cores.
        scene.stylesheets.add(javaClass.getResource(newTheme.cssPath)?.toExternalForm())

        // 4. Atualiza o estado interno para refletir o novo tema.
        currentTheme = newTheme

        println("Tema '${newTheme.displayName}' aplicado com sucesso.")
    }

    /**
     * Aplica o tema ATUAL a uma nova cena.
     * Essencial para quando navegamos para uma nova tela, garantindo que ela
     * mantenha o tema que o usuário selecionou.
     *
     * @param scene A nova cena que precisa ser estilizada.
     */
    fun applyCurrentTheme(scene: Scene) {
        applyTheme(scene, currentTheme)
    }
}