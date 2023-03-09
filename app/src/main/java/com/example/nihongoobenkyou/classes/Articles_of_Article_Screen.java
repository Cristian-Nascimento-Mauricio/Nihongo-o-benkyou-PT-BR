package com.example.nihongoobenkyou.classes;

public class Articles_of_Article_Screen {

    private String titulo;
    private String text;

    public Articles_of_Article_Screen(String titulo, String text) {
        this.titulo = titulo;
        this.text = text;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
