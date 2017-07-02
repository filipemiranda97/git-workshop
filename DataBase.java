import java.util.Arrays;
import java.util.HashMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class DataBase implements Serializable {
    private HashMap<String,Utilizador> users;
    
    public DataBase(Utilizador u) {
        users = new HashMap<String,Utilizador>();
        users.put(u.getEmail(),u.clone());
    }
    
    public DataBase() {
        users = new HashMap<String,Utilizador>();
    }
    
    public DataBase(DataBase d) {
        users = d.getUsers();
    }
    
    public HashMap<String,Utilizador> getUsers(){
        HashMap<String,Utilizador> temp = new HashMap<String,Utilizador>();
        for (Utilizador u: this.users.values()) temp.put(u.getEmail(),u.clone());
        return temp;
    }
    
    public void setUsers(HashMap<String,Utilizador> ux){
        users = new HashMap<String,Utilizador>();
        for (Utilizador u: ux.values()) users.put(u.getEmail(),u.clone());
    }
    
    public void adicionarUtilizador(Utilizador u) {
        users.put(u.getEmail(),u.clone());
    }
    
    public boolean userExiste(String email) {
        return users.containsKey(email);
    }
    
    public boolean loginCorreto(String email, String password) {
        if (users.containsKey(email) == false) return false;
        else return users.get(email).getPassword().equals(password);
    }
    
    public Utilizador getUser(String email){
        return users.get(email);
    }
    
    public ArrayList<Motorista> getMotoristas (int lugares) {
        ArrayList<Motorista> taxis = new ArrayList<Motorista>();
        Iterator<HashMap.Entry<String,Utilizador>> it = users.entrySet().iterator();
        while(it.hasNext()) {
            HashMap.Entry<String,Utilizador> temp = it.next();
            if (temp.getValue() instanceof Motorista) {
                Motorista m = (Motorista) temp.getValue();
                if (m.getATrabalhar() == true && m.getVia().getLugares() >= lugares) taxis.add(m);
            } 
        }
        return taxis;
    }
    
    public Motorista getMotoristaMaisProximo(Ponto2D localizacaoCliente, int lugares) {
        double menorDistancia = -1;
        Motorista maisProximo = null;
        Iterator<HashMap.Entry<String,Utilizador>> it = users.entrySet().iterator();
        while(it.hasNext()) {
            HashMap.Entry<String,Utilizador> temporario = it.next();
            if (temporario.getValue() instanceof Motorista) {
                Motorista m = (Motorista) temporario.getValue();
                if (maisProximo == null && m.getVia().getLugares() >= lugares && m.getATrabalhar() == true && menorDistancia == -1) {
                    maisProximo = m;
                    menorDistancia = m.getVia().getLocalizacao().distancia(localizacaoCliente);
                }
                if (m.getATrabalhar() == true && m.getVia().getLugares() >= lugares)
                    if (menorDistancia > m.getVia().getLocalizacao().distancia(localizacaoCliente)) {
                        menorDistancia = m.getVia().getLocalizacao().distancia(localizacaoCliente);
                        maisProximo = m;
                    }
            }
        }
        return maisProximo;
    }
    
    public void top5motoristas() {
       HashMap<Double,Motorista> temp = new HashMap<Double,Motorista>();
       for (Utilizador u: users.values())
       if (u instanceof Motorista) {
           Motorista m = (Motorista) u.clone();
           temp.put(m.getPrejuizo(),m);
       }  
       Iterator<Motorista> it = temp.values().iterator();
       String[] motoristas = new String[5];
       for (int i = 0; i < 5; i++) 
       if (it.hasNext()) {
            Motorista m = it.next();
            motoristas[i] = new String("Nome: " + m.getNome() + "; Prejuizo: " + m.getPrejuizo());
       }
       if (motoristas.length > 0) {
           for (int i = motoristas.length-1; i >= 0; i --)  if (motoristas[i] != null) System.out.println(motoristas[i]);  
       }
       if (motoristas.length == 0) System.out.println("Não existe registo de motoristas.");
    }
    
    public void top10clientes() {
       HashMap<Double,Cliente> temp = new HashMap<Double,Cliente>();
       for (Utilizador u: users.values())
       if (u instanceof Cliente) {
           Cliente c = (Cliente) u.clone();
           temp.put(c.getCarteira(),c);
       }    
       Iterator<Cliente> it = temp.values().iterator();
       String[] clientes = new String[10];
       for (int i = 0; i < 10; i++) 
       if (it.hasNext()) {
            Cliente c = it.next();
            clientes[i] = new String("Nome: " + c.getNome() + "; Total gasto: " + c.getCarteira());
       }
       if (clientes.length > 0) {
           for (int i = clientes.length-1; i >= 0; i --) if(clientes[i] != null) System.out.println(clientes[i]);
       } 
       if (clientes.length == 0) System.out.println("Não existe registo de clientes.");
    }
    
    public boolean equals(Object o) {
        if (o == this) return true;
        if ((o==null) || o.getClass() != this.getClass()) return false;
        DataBase d = (DataBase) o;
        return users.equals(d.getUsers());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Utilizador u: this.users.values()) sb.append(u.toString());
        return sb.toString();
    }
    
    public DataBase clone() {
        return new DataBase(this);
    }
}