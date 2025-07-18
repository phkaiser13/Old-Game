// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe.game;

/**
 * Enum para representar os jogadores (e seus marcadores no tabuleiro).
 * Este é o modelo mais fundamental do nosso jogo.
 * Neste caso, a representação padrão do enum (X, O) já é o que queremos mostrar na UI,
 * então não precisamos do padrão 'displayName'. O toString() padrão já retornará "X" ou "O".
 */
public enum Player {
    X,
    O;
}