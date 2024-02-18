import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        Cell initialCell = new Cell(6, 6);
        Snake snake = new Snake(initialCell);
        Board board = new Board(11, 11);
        Game newSnakeGame = new Game(snake, board);
        newSnakeGame.isGameOver = false;
        newSnakeGame.currentDirection = newSnakeGame.DIRECTION_DOWN;

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        primaryStage.setTitle("Java Ssssnake Game!");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
