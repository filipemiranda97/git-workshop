import java.io.Serializable;
import java.time.LocalDate;

public class Viagem implements Serializable{
    //VARS
    private Ponto2D partida, chegada;
    private int classificacao, cHorario;
    private double duracao, preco;
    private LocalDate dataViagem;
    
    //CTRS
    public Viagem (Ponto2D partida, Ponto2D chegada, double duracao, double preco, int classificacao, int cHorario){
        this.partida = partida;
        this.chegada = chegada;
        this.duracao = duracao;
        this.preco = preco;
        this.classificacao = classificacao;
        this.cHorario = cHorario;
        this.dataViagem = dataViagem.now();
    }
    public Viagem(){
        this.partida = new Ponto2D();
        this.chegada = new Ponto2D(5,5);
        this.duracao = 10;
        this.preco = 5;
        this.classificacao = 100;
        this.cHorario = 100;
        this.dataViagem = dataViagem.of(2017,5,24);
    }
    public Viagem (Viagem v){
        this.partida = v.getPartida();
        this.chegada = v.getChegada();
        this.duracao = v.getDuracao();
        this.preco = v.getPreco();
        this.classificacao = v.getClassificacao();
        this.cHorario = v.getcHorario();
        this.dataViagem = v.getDataViagem();
    }
    
    //GETS
    public Ponto2D getPartida(){
        return partida;
    }
    public Ponto2D getChegada(){
        return chegada;
    }
    public double getDuracao(){
        return duracao;
    }
    public double getPreco(){
        return preco;
    }
    public int getClassificacao(){
        return classificacao;
    }
    public int getcHorario(){
        return cHorario;
    }
    public void setcHorario(int cHorario) {
        this.cHorario = cHorario; 
    }
    public LocalDate getDataViagem() { return dataViagem; }
    public void setDataViagem(LocalDate dataViagem) { this.dataViagem = dataViagem; }
    //SDC
    public boolean equals(Object o){
        if (o==this) return true;
        if ((o==null) || (o.getClass() != this.getClass())) return false;
        Viagem v = (Viagem) o;
        return (partida.equals(v.getPartida()) && chegada.equals(v.getChegada()) && duracao == v.getDuracao() && preco == v.getPreco() && classificacao == v.getClassificacao() && cHorario == v.getcHorario() && dataViagem.equals(v.getDataViagem()));
    }
    public String toString(){
        return new String("\nData da Viagem: " + dataViagem.toString() + "\n\nPartida: " + partida.toString() + "; Destino: " + chegada.toString() + "; Tempo de Viagem: " + duracao + " minutos; Preço: " + preco + " €; Classificação: " + classificacao + "; Cumprimento de Horário: " + cHorario + ".");
    }
    public Viagem clone(){
        return new Viagem(this);
    }
}
