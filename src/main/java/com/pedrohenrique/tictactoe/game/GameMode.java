// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.game;

/**
 * Enum para representar os modos de jogo disponíveis.
 * Usar um Enum em vez de Strings ("PvP", "PvE") ou inteiros (1, 2) é crucial para a segurança de tipo.
 * Isso previne erros de digitação e garante que apenas valores válidos possam ser usados no nosso código,
 * tornando o sistema mais robusto e fácil de entender.
 */
public enum GameMode {
    // Define os valores possíveis para o modo de jogo.
    PLAYER_VS_PLAYER("Jogador vs Jogador"),
    PLAYER_VS_AI("Jogador vs IA");

    // Um campo para armazenar uma representação amigável do nome do enum.
    private final String displayName;

    /**
     * Construtor do enum.
     * @param displayName O texto que será exibido na interface do usuário.
     */
    GameMode(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Sobrescreve o método toString() padrão.
     * O JavaFX ChoiceBox, por padrão, chama este método para obter o texto a ser exibido para cada item.
     * Ao sobrescrevê-lo, garantimos que nossa UI mostre "Jogador vs Jogador" em vez de "PLAYER_VS_PLAYER".
     * Esta é uma maneira limpa e desacoplada de conectar nosso modelo de dados à nossa visão.
     * @return A representação amigável do modo de jogo.
     */
    @Override
    public String toString() {
        return displayName;
    }
}