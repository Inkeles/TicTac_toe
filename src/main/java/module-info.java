module org.example.tictac_toe {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens org.example.tictac_toe to javafx.fxml;
    exports org.example.tictac_toe;
}