module crawler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires org.jsoup;
    opens crawler to javafx.fxml;
    exports crawler;
}
 