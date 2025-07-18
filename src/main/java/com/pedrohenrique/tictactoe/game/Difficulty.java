// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.game;

/**
 * Enum para representar os níveis de dificuldade da Inteligência Artificial.
 * Segue o mesmo princípio do GameMode para garantir robustez e clareza no código.
 */
public enum Difficulty {
    EASY("Fácil"),
    HARD("Difícil");

    private final String displayName;

    Difficulty(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}