module com.xhomerly.sokoban {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.xhomerly.sokoban to javafx.fxml;
    exports com.xhomerly.sokoban;
}