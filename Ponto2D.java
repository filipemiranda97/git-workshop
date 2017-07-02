import static java.lang.Math.abs;
import java.io.Serializable;

public class Ponto2D implements Serializable {
    //Variáveis de Instância
    private double x, y;
    
    //Construtores usuais
    public Ponto2D(double cx, double cy) {
        x=cx;
        y=cy;
    }
    public Ponto2D(){
        this(0.0, 0.0);
    } //usa o outro constutor
    public Ponto2D(Ponto2D p){
        x = p.getX();
        y = p.getY();
    }
    
    //Métodos de Instância
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    
    /** incremento das coordenadas */
    public void incCoord(double dx, double dy){
        x+=dx;
        y+=dy;
    }
    
    public double distancia (Ponto2D p) {
    return Math.sqrt(Math.pow((p.getX()-this.x),2)+Math.pow((p.getY()-this.y),2));
    }
    
    /** decremento das coordenadas */
    public void decCoord(double dx, double dy){
        x -= dx;
        y -= dy;
    }
    
    /** soma as coordenadas do ponto parâmetro ao ponto receptor */
    public void somaPonto(Ponto2D p) {
        x+=p.getX();
        y+=p.getY();
    }
    
    /** soma os valores parâmetro e devolve um novo ponto */
    public Ponto2D somaPonto(double dx, double dy){
        return new Ponto2D (x+dx, y+dy);
    }
    
    /** determina se um ponto é simétrico (dista do eixo dos XX o
     * mesmo que do eixo dos YY */
    public boolean simetrico() {
        return abs(x) == abs (y);
    }
    
    /** verifica se ambas as coordenadas são positivas */
    public boolean coordPos() {
        return x > 0 && y > 0;
    }
    
    //Métodos complementares usuais
    
    /** verifica se os 2 pontos são iguais */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Ponto2D p = (Ponto2D) o;
        return (this.getX() == p.getX() && this.getY() == p.getY());
    }
    
    /** Converte para uma representação textual */
    public String toString() {
        return new String(x + ", " + y);
    }
    
    /** Cria uma cópia do ponto receptor (receptor = this) */
    public Ponto2D clone() {
        return new Ponto2D(this);
    }
}
