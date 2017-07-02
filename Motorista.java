import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Motorista extends Utilizador {
    private int cHorario, classificacao;
    private double kmsRealizados, totalFaturado, prejuizo;
    private boolean aTrabalhar;
    private Viatura via;
    
    public Motorista(String email, String nome, String password, String morada, LocalDate nascimento, int cHorario, boolean aTrabalhar, Viatura via) {
        super(email, nome, password, morada, nascimento, new ArrayList<Viagem>());
        this.cHorario = 0;
        this.classificacao = 0;
        this.kmsRealizados = 0;
        this.totalFaturado = 0;
        this.aTrabalhar = aTrabalhar;
        this.via = via;
        this.prejuizo = prejuizo;
    }

    public Motorista() {
        super();
        this.cHorario = 89;
        this.classificacao = 77;
        this.kmsRealizados = 86580.23;
        this.totalFaturado = 345.50;
        this.aTrabalhar = true;
        this.via = new Carrinha();
        this.prejuizo = 80.5;
    }

    public Motorista(Motorista m) {
        super(m);
        this.cHorario = m.getCHorario();
        this.classificacao = m.getClassificacao();
        this.kmsRealizados = m.getKmsRealizados();
        this.totalFaturado = m.getTotalFaturado();
        this.aTrabalhar = m.getATrabalhar();
        this.via = m.getVia();
        this.prejuizo = m.getPrejuizo();
    }
    
    public void alterarLocalizacao (Ponto2D p){
        via.setLocalizacao(p);
    }
    
    public double getPrejuizo() { return prejuizo; }
    
    public void setPrejuizo(double prejuizo) { this.prejuizo = prejuizo; }
    
    public Viatura getVia() { return via; }
    
    public void setV(Viatura via) { this.via = via; }
        
    public int getCHorario() { return cHorario; }
    
    public int getClassificacao() { return classificacao; }
    
    public double getKmsRealizados() { return kmsRealizados; }
    
    public double getTotalFaturado() { return totalFaturado; }

    public boolean getATrabalhar() { return aTrabalhar; }

    public void setCHorario (int cHorario) { this.cHorario = cHorario; }

    public void setClassificacao (int classificacao) { this.classificacao = classificacao; }

    public void setKmsRealizados (double kmsRealizados) { this.kmsRealizados = kmsRealizados; }
    
    public void setTotalFaturado (double totalFaturado) { this.totalFaturado = totalFaturado; }
    
    public void setATrabalhar (boolean aTrabalhar) { this.aTrabalhar = aTrabalhar; }
    
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Motorista m = (Motorista) o;
        return (super.equals(m) && this.cHorario == m.getCHorario() && this.classificacao == m.getClassificacao() && this.aTrabalhar == m.getATrabalhar() && this.kmsRealizados == m.getKmsRealizados() && this.prejuizo == m.getPrejuizo() && this.totalFaturado == m.getTotalFaturado() && this.via.equals(m.getVia()));
    }

    public String toString() {
        return ("\n-----MOTORISTA-----\n\n" +super.toString() + "\nGrau de cumprimento de horário: " + cHorario + "\nClassificação: " + classificacao + "\nEstá a trabalhar? " + aTrabalhar + "\nQuilómetros realizados: " + kmsRealizados + "\n" + via);
    }
    
    public Motorista clone() { return new Motorista(this); }
}