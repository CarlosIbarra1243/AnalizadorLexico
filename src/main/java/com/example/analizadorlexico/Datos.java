package com.example.analizadorlexico;

public class Datos {
    String palabra;
    String tipo;
    int noFila;

    int noColum;

    public Datos(String palabra, String tipo, int noColum, int noFila) {
        this.palabra = palabra;
        this.tipo = tipo;
        this.noFila = noFila;
        this.noColum = noColum;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNoFila() {
        return noFila;
    }

    public void setNoFila(int noFila) {
        this.noFila = noFila;
    }

    public int getNoColum() {
        return noColum;
    }

    public void setNoColum(int noColum) {
        this.noColum = noColum;
    }
}
