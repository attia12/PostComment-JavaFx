package com.example.rafafx;

import com.example.rafafx.entities.Post;
import com.example.rafafx.services.PostService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {




    public TextArea textArea;
    public VBox postContainer;
    public TextField searchField;
    PostService postService=new PostService();
    public void openInsertPostWindow(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("post-dialog.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Insert Post");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Post> posts = postService.getAll();
        System.out.println(posts);


        for (Post post : posts) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PostItem.fxml"));
                BorderPane postItemRoot = loader.load();
                PostItem controller = loader.getController();
                controller.setPost(post);
                controller.setHelloController(this);
                postContainer.getChildren().add(postItemRoot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public void reloadPosts() {
        postContainer.getChildren().clear(); // Clear existing posts
        initialize(null, null); // Reload posts
    }
}