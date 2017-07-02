public class Moto extends Viatura {
    
    public Moto(double vmedia, double preco, double kmsTotal, double kmsParcial, double fiabilidade, String matricula, Ponto2D localizacao) {
        super(vmedia, preco, kmsTotal, kmsParcial, fiabilidade, 1, matricula, localizacao);
    }
    
    public Moto() {
        super();
        setLugares(1);
    }
    
    public Moto(Moto m) {
        super(m);
    }
    
    public int getLugares() { return 1; }
    
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Moto m = (Moto) o;
        return (super.equals(m));
    }

    public String toString() {
        return super.toString();
    }

    public Moto clone() { return new Moto(this); }
}