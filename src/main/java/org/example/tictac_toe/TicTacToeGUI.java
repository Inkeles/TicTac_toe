package org.example.tictac_toe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeGUI extends Application {
    private char[][] board = new char[3][3];  // Игровое поле
    private boolean player1Turn = true;  // Ход первого игрока (X)
    private Button[][] buttons = new Button[3][3];  // Кнопки для игрового поля

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Инициализация доски
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';  // Заполняем пустыми символами
            }
        }

        // Создание GridPane для отображения игрового поля
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);

        // Создание кнопок для каждой клетки игрового поля
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button(" ");
                btn.setMinSize(100, 100);
                btn.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
                final int row = i;  // Локальные переменные для обработки кликов
                final int col = j;
                btn.setOnAction(event -> handleButtonClick(row, col));  // Обработка кликов
                buttons[i][j] = btn;
                grid.add(btn, j, i);
            }
        }

        // Настройка сцены и окна
        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Обработка клика на кнопке (ход игрока)
    private void handleButtonClick(int row, int col) {
        // Игнорировать, если ячейка уже занята
        if (board[row][col] != ' ') {
            return;
        }

        // Помещаем символ в соответствующую ячейку
        if (player1Turn) {
            board[row][col] = 'X';
            buttons[row][col].setText("X");
        } else {
            board[row][col] = 'O';
            buttons[row][col].setText("O");
        }

        // Проверка на победителя
        if (checkWinner()) {
            String winner = player1Turn ? "Игрок 1 (X)" : "Игрок 2 (O)";
            showWinnerDialog(winner);  // Окно с результатом
            return;
        }

        // Проверка на ничью
        if (isBoardFull()) {
            showWinnerDialog("Ничья!");  // Сообщаем о ничьей
            return;
        }

        // Переключение хода
        player1Turn = !player1Turn;
    }

    // Проверка на победителя
    private boolean checkWinner() {
        // Проверка строк
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        // Проверка столбцов
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        // Проверка диагоналей
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    // Проверка на ничью
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Отображение окна с результатом игры
    private void showWinnerDialog(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Конец игры");
        alert.setHeaderText("Результат");
        alert.setContentText(message);
        alert.showAndWait();
        resetBoard();  // Перезапуск игры
    }

    // Перезапуск игры
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j].setText(" ");
            }
        }
        player1Turn = true;  // Снова начинает игрок 1
    }
}
