import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Cliente extends Utilizador {
    private Ponto2D localizacao;
    double carteira;
    public Cliente(String email, String nome, String password, String morada, LocalDate nascimento, int x, int y) {
        super(email, nome, password, morada, nascimento, new ArrayList<Viagem>());
        this.localizacao = new Ponto2D(x,y);
        this.carteira = carteira;
    }
    
    public Cliente() {
        super();
        this.localizacao = new Ponto2D();
        this.carteira = 450.50;
    }
    
    public Cliente(Cliente c) {
        super(c);
        this.localizacao = c.getLocalizacao();
        this.carteira = c.getCarteira();
    }
    
    public double getCarteira() { return carteira; }
    
    public void setCarteira (double carteira) { this.carteira = carteira; }
    
    public Ponto2D getLocalizacao() { return localizacao; }
    
    public void setLocalizacao(Ponto2D localizacao) { this.localizacao = localizacao; }
    
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Cliente c = (Cliente) o;
        return (super.equals(c) && this.localizacao.equals(c.getLocalizacao()) && this.carteira == c.getCarteira());
    }

    public String toString() {
        return super.toString();
    }
    
    public Cliente clone() { return new Cliente(this); }
}
