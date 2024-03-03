package com.example.rafafx;

import com.example.rafafx.entities.Post;
import com.example.rafafx.services.PostService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PostDialog {
    Post li=new Post();
    public JFXTextField description;
    public Label selectedImageLabel;
    public JFXButton add;
    public JFXButton chooseImageButton;
    public JFXButton Annuler;
    public JFXButton retour;
    public JFXTextField titre;
    private PostService postService=new PostService();

    public void AddPost(ActionEvent actionEvent) {
        Post P =new Post();
        Boolean valid=true;
        titre.resetValidation();
        description.resetValidation();



        if(titre.getText().isEmpty()||description.getText().isEmpty())

        {
            titre.validate();description.validate();
            valid=false;
        }

        if(add.getText()!="Modifier"&&valid) {

            Post p = new Post();
            p.setTitre(titre.getText());
            p.setDescription(description.getText());
            p.setImage(selectedImageLabel.getText());

            postService.ajouter(p);
            vider();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);alert1.setHeaderText(null);
            alert1.setContentText("Post Ajouté avec réussite");
            alert1.showAndWait();

            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();

        } else if (add.getText()=="Modifier"&&valid) {

           Post updated=new Post(titre.getText(),description.getText(),selectedImageLabel.getText());
            postService.modifier(updated);
            vider();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);alert.setHeaderText(null);
            alert.setContentText("Post Modifiée Avec réussite  ");alert.showAndWait();
            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();

        }
        
    }



    public void Retour(ActionEvent actionEvent) {
        retour.getScene().getWindow().hide();
    }

    public void chooseImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            selectedImageLabel.setText(file.getName());
            // You can do further processing with the selected image file here
        } else {
            selectedImageLabel.setText("No image selected");
        }
    }
    public void vider() {
        titre.clear();description.clear();
        titre.resetValidation();

        description.resetValidation();

    }
    public void setUpdate(String modifier) {
        add.setText(modifier.toString());
    }
    public void setcommd(Post c) {
        this.li=c;

        titre.setText(li.getTitre());
        description.setText(li.getDescription());
       selectedImageLabel.setText(li.getImage());

    }



    public void SetVisibilite(boolean b) {
        Annuler.setVisible(b);
    }
}
