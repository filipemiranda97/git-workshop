import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class Utilizador implements Serializable {
    
    private String email, nome, password, morada;
    private ArrayList<Viagem> viagens;
    private LocalDate nascimento;
    
    public Utilizador(String email, String nome, String password, String morada, LocalDate nascimento, ArrayList<Viagem> vi) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.nascimento = nascimento;
        this.viagens = new ArrayList<Viagem>();
    }
    
    public Utilizador() {
        this.email = "user@user.pt";
        this.nome = "Filipe Miranda";
        this.password = "123";
        this.nascimento = nascimento.of(1997,8,26);
        this.viagens = new ArrayList<Viagem>();
    }
    
    public Utilizador(Utilizador u) {
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.password = u.getPassword();
        this.morada = u.getMorada();
        this.nascimento = u.getNascimento();
        this.viagens = u.getViagens();
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getNome() {
        return nome;
    }
    
        public String getPassword() {
        return password;
    }
    
        public String getMorada() {
        return morada;
    }
    
        public LocalDate getNascimento() {
        return nascimento;
    }
    
        public ArrayList<Viagem> getViagens() {
        ArrayList<Viagem> temp = new ArrayList<Viagem>();
        for (int i = 0; i < this.viagens.size(); i++)
            temp.add(viagens.get(i).clone());
        return temp;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    
    public void setViagens (ArrayList<Viagem> trip) {
        this.viagens = new ArrayList<Viagem>();
        for (int i = 0; i < trip.size(); i++) viagens.add(trip.get(i).clone());
    }
    
    public void registarViagem(Viagem v) {
        viagens.add(v.clone());
    }
    
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Utilizador u = (Utilizador) o;
        return (this.getEmail().equals(u.getEmail()) && this.getNome().equals(u.getNome()) && this.getPassword().equals(u.getPassword()) && this.nascimento.equals(u.getNascimento()) && viagens.equals(u.getViagens()));
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ");
        sb.append(email+"\n");
        sb.append("Nome: ");
        sb.append(nome+"\n");
        sb.append("Password: ");
        sb.append(password+"\n");
        sb.append("Morada: ");
        sb.append(morada+"\n");
        sb.append("Data de nascimento: ");
        sb.append(nascimento+"\n");
        for (Viagem v: viagens) sb.append(v.toString());
        return sb.toString();
    }
    
    public abstract Utilizador clone();
    
    public void imprimeViagens(){
        System.out.printf("\n-----VIAGENS-----\n");
        for (Viagem v: viagens)System.out.println(v.toString());
    }
    
    public void imprimeViagensData(LocalDate data1, LocalDate data2){
        for (Viagem v: viagens){
            if (v.getDataViagem().isAfter(data1) && v.getDataViagem().isBefore(data2)) System.out.println(v.toString());
        }
    }
}