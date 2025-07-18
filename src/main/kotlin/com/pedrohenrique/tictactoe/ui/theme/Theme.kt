// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.ui.theme

/**
 * Enum para representar os temas visuais disponíveis na aplicação.
 * Assim como nos modelos de jogo, usar um Enum garante segurança de tipo e clareza,
 * prevenindo o uso de strings arbitrárias para definir temas.
 *
 * @param displayName O nome amigável que será exibido na interface do usuário.
 * @param cssPath O caminho para o arquivo CSS correspondente ao tema.
 */
enum class Theme(val displayName: String, val cssPath: String) {
    DARK("Escuro", "/css/dark_theme.css"),
    LIGHT("Claro", "/css/light_theme.css");

    /**
     * Sobrescreve o método toString() para que o ChoiceBox na UI exiba o nome amigável
     * em vez do nome da constante do enum (DARK/LIGHT).
     */
    override fun toString(): String {
        return displayName
    }
}