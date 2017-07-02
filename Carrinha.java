public class Carrinha extends Viatura {
    
    public Carrinha(double vmedia, double preco, double kmsTotal, double kmsParcial, double fiabilidade, String matricula, Ponto2D localizacao) {
        super(vmedia, preco, kmsTotal, kmsParcial, fiabilidade, 1, matricula, localizacao);
    }
    
    public Carrinha() {
        super();
        setLugares(8);
    }
    
    public Carrinha(Carrinha c) {
        super(c);
    }
    
    public int getLugares() { return 8; }
    
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Carrinha c = (Carrinha) o;
        return (super.equals(c));
    }

    public String toString() {
        return super.toString();
    }

    public Carrinha clone() { return new Carrinha(this); }
}

