import java.util.Random;
import java.io.Serializable;

public abstract class Viatura implements Serializable {
    
    private double vmedia, preco, kmsTotal, kmsParcial, fiabilidade;
    private int lugares;
    private String matricula;
    private Ponto2D localizacao;
    
    public Viatura (double vmedia, double preco, double kmsTotal, double kmsParcial, double fiabilidade, int lugares, String matricula, Ponto2D localizacao) {
        this.vmedia = vmedia;
        this.preco = preco;
        this.kmsTotal = kmsTotal;
        this.kmsParcial = kmsParcial;
        this.fiabilidade = fiabilidade;
        this.lugares = lugares;
        this.matricula = matricula;
        this.localizacao = localizacao;
    }
    
    public Viatura() {
        this.vmedia = geraAleatorio();
        this.preco = 0.75;
        this.kmsTotal = 86945;
        this.kmsParcial = geraAleatorio();
        this.fiabilidade = geraAleatorio();
        this.matricula = "72-50-PO";
        this.localizacao = new Ponto2D(5,9);
    }
    
    public Viatura(Viatura v) {
        this.vmedia = v.getVmedia();
        this.preco = v.getPreco();
        this.kmsTotal = v.getKmsTotal();
        this.kmsParcial = v.getKmsParcial();
        this.fiabilidade = v.getFiabilidade();
        this.lugares = v.getLugares();
        this.matricula = v.getMatricula();
        this.localizacao = v.getLocalizacao();
    }
    
    private int geraAleatorio() {
        Random f = new Random();
        return f.nextInt(101);
    }
    
    public double getVmedia() {
        return vmedia;
    }

    public void setVmedia(double vmedia) {
        this.vmedia = vmedia;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getKmsTotal() {
        return kmsTotal;
    }

    public void setKmsTotal(double kmsTotal) {
        this.kmsTotal = kmsTotal;
    }

    public double getKmsParcial() {
        return kmsParcial;
    }

    public void setKmsParcial(double kmsParcial) {
        this.kmsParcial = kmsParcial;
    }

    public double getFiabilidade() {
        return fiabilidade;
    }

    public void setFiabilidade(double fiabilidade) {
        this.fiabilidade = fiabilidade;
    }

    public abstract int getLugares();
    
    public void setLugares(int lugares) { this.lugares = lugares; }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public Ponto2D getLocalizacao() { return localizacao; }
    
    public void setLocalizacao(Ponto2D localizacao) { this.localizacao = localizacao; }   
    
    public int geraFiabilidade() {
        Random f = new Random();
        return f.nextInt(101);
    }
    
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Viatura c = (Viatura) o;
        return (this.getVmedia() == c.getVmedia() && this.getPreco() == c.getPreco() && this.getKmsTotal() == c.getKmsTotal() && this.getKmsParcial() == c.getKmsParcial() && this.getFiabilidade() == c.getFiabilidade() && this.getLugares() == c.getLugares() && this.getMatricula().equals(c.getMatricula()) && this.getLocalizacao().equals(c.getLocalizacao()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-----VIATURA----\n\n");
        sb.append("Velocidade média: ");
        sb.append(vmedia+"\n");
        sb.append("Preço: ");
        sb.append(preco+"\n");
        sb.append("Quilómetros Parciais: ");
        sb.append(kmsParcial+"\n");
        sb.append("Quilómetros Totais: ");
        sb.append(kmsTotal+"\n");
        sb.append("Nº de lugares: ");
        sb.append(lugares+"\n");
        sb.append("Localização: ");
        sb.append(localizacao+"\n");
        sb.append("Matrícula: ");
        sb.append(matricula+"\n");
        sb.append("Fiabilidade: ");
        sb.append(fiabilidade+"\n");
        return sb.toString();
    }
    
    public abstract Viatura clone();
}