package br.com.dio.desafio.dominio;

public class Coracoes{

    private int quantidadeCoracoes = 5;
    private int quantidadeErros = 0;

    public Coracoes() {
    }

    public int getQuantidadeCoracoes() {
        return quantidadeCoracoes;
    }

    public void setQuantidadeCoracoes(int quantidadeCoracoes) {
        this.quantidadeCoracoes = quantidadeCoracoes;
    }

    public int getQuantidadeErros() {
        return quantidadeErros;
    }

    public void setQuantidadeErros(int quantidadeErros) {
        this.quantidadeErros = quantidadeErros;
    }

    public void restaurarCoracoes(){
        setQuantidadeCoracoes(this.quantidadeCoracoes = 5);
    }

}
