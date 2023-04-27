package crawler;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;

public class PrimaryController {
    private match searchedMatch;

    @FXML
    private Label responseText;
    @FXML
    private Label responseText2;
    @FXML
    private TextField url1Input;
    @FXML
    private TextField url2Input;
    @FXML
    private TextField url3Input;
    @FXML
    private TextField searchTerm;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void createURL() throws IOException, InterruptedException {
        match newMatch1 = new match();
        newMatch1.setUrl(url1Input.getText());
        String string1 = newMatch1.getUrl();
        String cont = requestString(string1);
        newMatch1.setContent(cont);
        newMatch1.addURLToStorage();

        match newMatch2 = new match();
        newMatch2.setUrl(url2Input.getText());
        String string2 = newMatch2.getUrl();
        String cont2 = requestString(string2);
        newMatch2.setContent(cont2);
        newMatch2.addURLToStorage();

        match newMatch3 = new match();
        newMatch3.setUrl(url3Input.getText());
        String string3 = newMatch3.getUrl();
        String cont3 = requestString(string3);
        newMatch3.setContent(cont3);
        newMatch3.addURLToStorage();
    }

    @FXML
    private void search() {
        String Content = searchTerm.getText();
        System.out.println("Searching...");
        searchedMatch = new match();
        searchedMatch.setContent(Content);
        searchedMatch.getMatchesFromStorage();
        responseText2.setText(searchedMatch.getUrl());
        System.out.println(searchedMatch.getUrl());
    }

    @FXML
    private String requestString(String urlString) throws IOException  {
        String url = urlString;
        Document doc = Jsoup.connect(url).get();
        String body = doc.select("body").text();
        String test = body;
        return test;
    }
}
