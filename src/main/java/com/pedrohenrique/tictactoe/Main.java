// 2025
// By Pedro henrique garcia.
// Github/gitlab: Phkaiser13

package com.pedrohenrique.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

/**
 * Classe principal da aplicação.
 * Esta classe é o ponto de entrada (entry point) para o nosso jogo.
 * Sua responsabilidade é inicializar o toolkit do JavaFX e carregar a primeira cena da interface gráfica,
 * que será o nosso menu principal.
 */
public class Main extends Application {

    /**
     * O método start é o principal ponto de entrada para todas as aplicações JavaFX.
     * O JavaFX runtime chama este método após chamar o método init() e o sistema estar pronto para a aplicação começar.
     *
     * @param primaryStage O "palco" principal da nossa aplicação. O palco é a janela de nível superior
     *                     (por exemplo, a janela com título, bordas, etc.) onde nossas cenas serão exibidas.
     * @throws Exception Lançada se houver um erro ao carregar os recursos da UI, como o arquivo FXML.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // O FXMLLoader é a classe responsável por carregar a hierarquia de objetos de um documento FXML.
        // Usamos getClass().getResource() para obter a URL do nosso arquivo de layout de forma segura,
        // garantindo que ele seja encontrado no classpath, de acordo com a estrutura de diretórios que definimos.
        // O caminho "/fxml/main_menu.fxml" é absoluto a partir da raiz do diretório 'resources'.
        URL fxmlUrl = getClass().getResource("/fxml/main_menu.fxml");

        // É uma boa prática de programação defensiva verificar se o recurso foi realmente encontrado.
        // Se fxmlUrl for nulo, significa que o arquivo não existe no local esperado, e a aplicação não pode continuar.
        if (fxmlUrl == null) {
            System.err.println("Erro crítico: Não foi possível encontrar o arquivo FXML do menu principal.");
            // Em uma aplicação real, poderíamos mostrar um alerta de erro aqui antes de sair.
            return; // Encerra a execução do método start.
        }

        // Carrega o layout FXML. O resultado é um objeto 'Parent', que é o nó raiz da nossa cena.
        Parent root = FXMLLoader.load(fxmlUrl);

        // O título da janela. Isso aparecerá na barra de título do sistema operacional.
        primaryStage.setTitle("Goblin Slayer - Jogo da Velha");

        // A 'Scene' (Cena) é o contêiner para todo o conteúdo em um grafo de cena.
        // Nossa cena é criada com o nó raiz 'root' que carregamos do FXML.
        primaryStage.setScene(new Scene(root));

        // Impedir que o usuário redimensione a janela.
        // Para um jogo com layout fixo e estilizado, isso previne que a UI fique distorcida
        // e mantém a estética que planejamos.
        primaryStage.setResizable(false);

        // Torna o palco (nossa janela) visível para o usuário.
        primaryStage.show();
    }

    /**
     * O método main é o ponto de entrada convencional para uma aplicação Java.
     * Para uma aplicação JavaFX, seu único propósito é chamar o método launch(),
     * que configura e inicia o ciclo de vida da aplicação JavaFX.
     *
     * @param args Argumentos de linha de comando (não utilizados neste projeto).
     */
    public static void main(String[] args) {
        launch(args);
    }
}