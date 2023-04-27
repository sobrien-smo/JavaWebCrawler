package crawler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class match {
    private String url;
    private String content;

    private String connection = "jdbc:mysql://<filepath>?user=<userhere>&password=<passwordhere>";

    public String getUrl() {
        return url;
    }

    public void setUrl(String Url) {
        url = Url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String Content) {
        content = Content;
    }

    public void getMatchesFromStorage() {
        try {
            Connection dbConnection = DriverManager
                    .getConnection(connection);
            String sqlString = "SELECT * FROM newurls WHERE content LIKE ?" ;
            PreparedStatement matchStatement = dbConnection.prepareStatement(sqlString);
            matchStatement.setString(1, "%" + content + "%");
            ResultSet results = matchStatement.executeQuery();
            if (results.next()) {
                url = results.getString("url");
                content = results.getString("content");
            }
            dbConnection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void addURLToStorage() {
        try {
            Connection dbConnection = DriverManager
                    .getConnection(connection);
            String insertString = "INSERT INTO newurls VALUES(0, ?, ?)";
            PreparedStatement preparedInsert = dbConnection.prepareStatement(insertString);
            preparedInsert.setString(1, url);
            preparedInsert.setString(2, content);
            preparedInsert.executeUpdate();
            dbConnection.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

    }
}
