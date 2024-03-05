package com.example.analizadorlexico;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class HelloController implements Initializable {
    @FXML
    private Button btnCal;

    @FXML
    private TableColumn colum1;

    @FXML
    private TableColumn colum2;

    @FXML
    private TableColumn colum3;

    @FXML
    private TableView<Datos> tbl1;

    @FXML
    private TextArea text1;

    //lista palabra reservadas
    ObservableList<String> palabrasReservadas = FXCollections.observableArrayList("byte", "short", "int","long","char","float","double","boolean","void","if","else","switch","case","default"
        ,"while","do","for","break","continue","try","catch","finally","throw","throws","private","protected","public","class","interface","enum","import"
        ,"package","extends","implements","static","final","abstract","default","new","instanceof","this","super","return","var","synchronized","volatile","native","transient","assert","strictfp"
        ,"const","goto","true","false","null");

    //lista de simbolos
    ObservableList<String> simbolos = FXCollections.observableArrayList(
            // Operadores aritméticos
            "+", "-", "*", "/", "%",
            // Operadores de asignación
            "=", "+=", "-=", "*=", "/=", "%=",
            // Operadores relacionales
            "==", "!=", "<", ">", "<=", ">=",
            // Operadores especiales
            "++", "--", ".", "(", ")",
            "&&", "||", "{", "}", ";"
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colum1.setCellValueFactory(new PropertyValueFactory<>("palabra"));
        colum2.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colum3.setCellValueFactory(new PropertyValueFactory<>("noFila"));
    }

    @FXML
    protected void calcular() {
        //limpiar tabla
        tbl1.getItems().clear();
        //iniciar contador (para las filas)
        int contador = 1;
        // Dividir el contenido del TextArea en líneas
        String[] lineas = text1.getText().split("\n");

        // Recorrer cada línea
        for (String linea : lineas) {
            // Dividir la cadena por el espacio en blanco
            String[] elementos = linea.split("\\s+");
            //Recorrer lista de elementos
            for (String elemento : elementos) {
                System.out.println(elemento);
                definirTipo(elemento, contador);
            }
            contador++;
        }
    }

    public void definirTipo(String cadena, int fila){
        //varibles validas
        Pattern variable = Pattern.compile("[a-zA-Z_$][a-zA-Z_$0-9]*");
        //numeros validos
        Pattern numero = Pattern.compile("-?\\d+(\\.\\d+)?([eE]-?\\d+)?");

        //verificando tipo
        if(palabrasReservadas.contains(cadena)){
            tbl1.getItems().add(new Datos(cadena, "Reservada", fila));
        } else if (simbolos.contains(cadena)) {
            tbl1.getItems().add(new Datos(cadena, "Simbolo", fila));
        } else if (variable.matcher(cadena).matches()) {
            tbl1.getItems().add(new Datos(cadena, "variable", fila));
        }else if (numero.matcher(cadena).matches()) {
            tbl1.getItems().add(new Datos(cadena, "numero", fila));
        } else System.out.println("No es fila: " + String.valueOf(fila));
    }

    //limpiar tabla y textArea
    @FXML
    protected void limpiar(){
        tbl1.getItems().clear();
        text1.clear();
    }
}