package br.com.dio.desafio.dominio;

import java.util.*;

public class Dev{
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    Coracoes coracoes = new Coracoes();

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
            coracoes.restaurarCoracoes();
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();
        double soma = 0;
        while(iterator.hasNext()){
            double next = iterator.next().calcularXp();
            soma += next;
        }
        soma += -coracoes.getQuantidadeErros() * 5;
        return soma;
    }

    public void erroProva(){
        if(coracoes.getQuantidadeCoracoes() < 1 ){
            System.out.println("Tenha mais calma, procure rever o conteudo abordado\nAguarde ate ser restaurado seu coracao para a proxima tentativa");
        } else {
            coracoes.setQuantidadeCoracoes(coracoes.getQuantidadeCoracoes() - 1);
            coracoes.setQuantidadeErros(coracoes.getQuantidadeErros() + 1);
        }
    }

    public void restaurarCoracoesTempo(){
        if (coracoes.getQuantidadeCoracoes() < 5){
            coracoes.setQuantidadeCoracoes(coracoes.getQuantidadeCoracoes() + 1);
        }else {
            System.out.println("Boa prova, voce ja possui chance de tentar novamente");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
