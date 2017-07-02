public class Carro extends Viatura {
    
    public Carro(double vmedia, double preco, double kmsTotal, double kmsParcial, double fiabilidade, String matricula, Ponto2D localizacao) {
        super(vmedia, preco, kmsTotal, kmsParcial, fiabilidade, 4, matricula, localizacao);
    }
    
    public Carro() {
     super();
     setLugares(4);
    }
    
    public Carro(Carro c) {
     super(c);
    }
    
    public int getLugares() { return 4; }
    
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Carro c = (Carro) o;
        return (super.equals(c));
    }

    public String toString() {
        return super.toString();
    }
    
    public Carro clone() { return new Carro(this); }
}