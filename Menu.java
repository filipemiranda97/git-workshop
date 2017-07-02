import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDate;
import java.time.DateTimeException;

public class Menu {
    private DataBase db;
    private Scanner input = new Scanner(System.in);
    public void main() {
        db = new DataBase();
        loadFile(); // carrega o estado da aplicação
        menuInicial(-1);
    }
    
    /** Dispõe o menu inicial*/
    private void menuInicial(int opcao) {
        opcao = lerInteiro("\n1 - Login | 2 - Registo | 3 - Informações | 0 - Sair\n\nOpção: ");
        while (opcao < 0 | opcao > 3) {
            System.out.printf("\nInput inválido. Tente novamente.\n");
            opcao = lerInteiro("\n1 - Login | 2 - Registo | 3 - Informações | 0 - Sair\n\nOpção: ");
        }
        System.out.println('\f');
        switch (opcao) {
            case 0: System.out.printf("Programa terminado.\n");
                saveFile();
                break;
            case 1: login();
                break;
            case 2: registo();
                break;
            case 3: informacoes();
                break;
        }
    }
    
    /** Apresenta o menu para efetuar login na aplicação */
    
    private void login() {
        String email, password;
        String s = input.nextLine();
        System.out.print("Email: ");
        email = input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();
        if (db.loginCorreto(email,password)) {
            System.out.println("\nLogin efetuado.\n");
            Utilizador u = db.getUser(email);
            if (u instanceof Cliente) menuCliente((Cliente) u);
            if (u instanceof Motorista) menuMotorista((Motorista) u);
        } else {
            System.out.println("\nEmail ou password incorretos. Tente novamente.");
            menuInicial(-1);
        }
    }
    
    /** Apresenta o menu do cliente e as opções que tem ao seu dispor */
    private void menuCliente (Cliente c) {
       int opcao = lerInteiro("Seja bem-vindo, " + c.getNome() + ". O que deseja fazer?\n1 - Fazer uma viagem | 2 - Ver histórico de viagens | 3 - Terminar Sessão ");
       while (opcao < 1 | opcao > 3){
           System.out.printf("Input inválido. Tente novamente.\n");
           opcao = lerInteiro("Seja bem-vindo, " + c.getNome() + ". O que deseja fazer?\n1 - Fazer uma viagem | 2 - Ver histórico de viagens | 3 - Terminar Sessão ");
       }
       if (opcao == 1) fazerViagem(c);
       if (opcao == 2) {
           int escolha = lerInteiro("Pretende ver viagens especificas?\n1 - Sim | 2 - Não ");
           while (escolha < 1 | escolha > 2) {
               System.out.printf("Input inválido. Tente novamente.\n");
               escolha = lerInteiro("Pretende ver viagens especificas?\n1 - Sim | 2 - Não ");
           }
           if (escolha == 1) {
               LocalDate data1, data2;
               data1 = lerData("Data inicial: \n");
               data2 = lerData("Data final: \n");
               c.imprimeViagensData(data1,data2);
            }
           if (escolha == 2) c.imprimeViagens();
           System.out.printf("\n");
           menuCliente(c);
       }
       if (opcao == 3) menuInicial(-1);
    }
    
    /** Apresenta o menu do motorista e as opções ao seu dispor */
    
    private void menuMotorista (Motorista m) {
       int opcao = lerInteiro("Seja bem-vindo, " + m.getNome() + ". O que deseja fazer?\n\n1 - Verificar dados de perfil | 2 - Ver histórico de viagens | 3 - Alterar Disponibilidade | 4 - Terminar Sessão ");
       while (opcao < 1 | opcao > 4) {
           System.out.printf("Input inválido. Tente novamente.\n");
           opcao = lerInteiro("Seja bem-vindo, " + m.getNome() + ". O que deseja fazer?\n\n1 - Verificar dados de perfil | 2 - Ver histórico de viagens | 3 - Alterar Disponibilidade | 4 - Terminar Sessão ");
        }
       if (opcao == 1) {
           System.out.printf(m.toString() + "\n");
           int escolha = lerInteiro("Pretende ver o total faturado pela sua viatura ou quanto faturou entre datas?\n\n1 - Total faturado | 2 - Entre datas ");
           while (escolha < 1 | escolha > 2) {
               System.out.printf("Input inválido. Tente novamente.\n");  
               escolha = lerInteiro("Pretende ver o total faturado pela sua viatura ou quanto faturou entre datas?\n\n1 - Total faturado | 2 - Entre datas ");
           }
           if (escolha == 1) System.out.println("\n" + round(m.getTotalFaturado()) + " €\n");
           if (escolha == 2) {
               LocalDate data1, data2;
               data1 = lerData("Data inicial: \n");
               data2 = lerData("Data final: \n");
               System.out.printf("\n O total faturado entre " + data1 + " e " + data2 + " foi: " + round(faturadoData(m,data1,data2)) + " €.\n");
           }
           menuMotorista(m);
       }
        if (opcao == 2) {
           int escolha = lerInteiro("Pretende ver viagens especificas?\n1 - Sim | 2 - Não ");
           while (escolha < 1 | escolha > 2) {
               System.out.printf("Input inválido. Tente novamente.\n");
               escolha = lerInteiro("Pretende ver viagens especificas?\n1 - Sim | 2 - Não ");
           }
           if (escolha == 1) {
               LocalDate data1, data2;
               data1 = lerData("Data inicial: \n");
               data2 = lerData("Data final: \n");
               System.out.printf("\n-----VIAGENS-----\n\n");
               m.imprimeViagensData(data1,data2);
            }
           if (escolha == 2) m.imprimeViagens();
           System.out.printf("\n");
           menuMotorista(m);
       }
        if (opcao == 3) {
            int d = lerInteiro("\nDe momento a sua disponibilidade está a " + m.getATrabalhar() + ". Deseja alterar a sua disponibilidade?\n\n1 - Sim | 2 - Não  ");
            while (d < 1 | d > 2) {
                d = lerInteiro("\nDe momento a sua disponibilidade está a " + m.getATrabalhar() + ". Deseja alterar a sua disponibilidade?\n\n1 - Sim | 2 - Não  ");
            }
            switch(d){
                case 1: if (m.getATrabalhar() == true) m.setATrabalhar(false);
                        else m.setATrabalhar(true);
                        System.out.printf("\nA sua disponibilidade foi alterada para " + m.getATrabalhar() + ".\n\n");
                        menuMotorista(m);
                        break;
                case 2: System.out.printf("A sua disponibilidade permanece a " + m.getATrabalhar() + ".\n\n");
                        menuMotorista(m);
                        break;
            }
        }
        if (opcao == 4) menuInicial(-1);
    }
    
    /** Apresenta o menu para registar um utilizador */
    private void registo() {
        int opcao = lerInteiro("Seja bem-vindo. Deseja registar-se como cliente ou motorista?\n\n1- Cliente | 2- Motorista: ");
        while (opcao < 1 || opcao > 2){
            System.out.printf("Input inválido. Tente novamente.\n"); 
            opcao = lerInteiro("Seja bem-vindo. Deseja registar-se como cliente ou motorista?\n1- Cliente | 2- Motorista: ");
        }
        if (opcao == 1) registaCliente();
        else registaMotorista();
    }
    
    /** Regista um cliente*/
    private void registaCliente() {
        int x, y, dia, mes, ano;
        String email, nome, password, morada;
        String s = input.nextLine();
        System.out.printf("\nIntroduza os seus dados.\n\nEmail: ");
        email = input.nextLine();
        while (db.userExiste(email)){
            System.out.println("Este email já existe. Tente novamente.");
            System.out.print("Introduza o email do cliente: ");
            email = input.nextLine();
        }
        System.out.printf("Nome: ");
        nome = input.nextLine();
        System.out.printf("Password: ");
        password = input.nextLine();
        System.out.printf("Morada: ");
        morada = input.nextLine();
        LocalDate nascimento = lerData("Data de nascimento: \n");
        while (nascimento.isAfter(nascimento.now().minusYears(13))) {
            System.out.printf("Tem que ter no mínimo 13 anos para se registar como cliente.\n");
            nascimento = lerData("Data de nascimento: \n");
        }
        System.out.printf("\nIntroduza a sua localização.\n");
        x = lerInteiro("Coordenada X: ");
        y = lerInteiro("Coordenada Y: "); 
        db.adicionarUtilizador(new Cliente(email,nome,password,morada,nascimento,x,y));
        System.out.printf("\nO seu registo foi efetuado.\n");
        menuInicial(-1);
    }
    
    /** Regista um motorista */
    private void registaMotorista() {
        Viatura via;
        String email, nome, password, morada;
        int tipoVeiculo = 0, tipoRegisto = 0, cHorario = 0;
        String s = input.nextLine();
        System.out.printf("\nIntroduza os seus dados.\n\nEmail: ");
        email = input.nextLine();
        while (db.userExiste(email)){
            System.out.println("Este email já existe. Tente novamente.");
            System.out.print("Introduza o email do cliente: ");
            email = input.nextLine();
        }
        System.out.printf("Nome: ");
        nome = input.nextLine();
        System.out.printf("Password: ");
        password = input.nextLine();
        System.out.printf("Morada: ");
        morada = input.nextLine();
        LocalDate nascimento = lerData("Data de nascimento: \n");
        while (nascimento.isAfter(nascimento.now().minusYears(18))) {
            System.out.printf("Tem que ter no mínimo 18 anos para se registar como motorista.\n");
            nascimento = lerData("Data de nascimento: \n");
        }
        tipoVeiculo = lerInteiro("\nQual é o seu tipo de veículo?\n\n1 - Carro | 2 - Moto | 3 - Carrinha ");
        while(tipoVeiculo < 1 | tipoVeiculo > 3) {
            System.out.printf("Input inválido. Tente novamente.\n");
            tipoVeiculo = lerInteiro("\nQual é o seu tipo de veículo?\n\n1 - Carro | 2 - Moto | 3 - Carrinha ");
        }
        tipoRegisto = lerInteiro("Deseja registar um veículo ou que lhe seja atribuido um automaticamente?\n1 - Registar Veículo | 2 - Registo Automático ");
        while(tipoRegisto < 1 | tipoRegisto > 2) {
            System.out.printf("\nInput inválido. Tente novamente.\n");
            tipoRegisto = lerInteiro("\nDeseja registar um veículo ou que lhe seja atribuido um automaticamente?\n\n1 - Registar Veículo | 2 - Registo Automático ");
        }
        if (tipoVeiculo == 1 && tipoRegisto == 2) {
            db.adicionarUtilizador(new Motorista(email,nome,password,morada,nascimento,cHorario,false,new Carro()));
            System.out.printf("O seu registo foi efetuado.\n");
        }
        if (tipoVeiculo == 2 && tipoRegisto == 2) {
            db.adicionarUtilizador(new Motorista(email,nome,password,morada,nascimento,cHorario,false,new Moto()));
            System.out.printf("O seu registo foi efetuado.\n");
        }
        if (tipoVeiculo == 3 && tipoRegisto == 2) {
            db.adicionarUtilizador(new Motorista(email,nome,password,morada,nascimento,cHorario,false,new Carrinha()));
            System.out.printf("O seu registo foi efetuado.\n");
        }
        if (tipoRegisto == 1) {
            String matricula;
            double vmedia, preco, kmsTotal, kmsParcial;
            int x, y, fiabilidade = geraAleatorio();
            input.nextLine();
            System.out.printf("Matrícula: ");
            matricula = input.nextLine();
            vmedia = lerDouble("Velocidade Média: ");
            preco = lerDouble("Preço: ");
            kmsTotal = lerDouble("Quilómetros totais: ");
            kmsParcial = lerDouble("Quilómetros parciais: ");
            x = lerInteiro("Introduza as coordenadas da sua localização.\nCoordenada X: ");
            y = lerInteiro("Introduza as coordenadas da sua localização.\nCoordenada Y: ");
            Ponto2D localizacao = new Ponto2D(x,y);
            if (tipoVeiculo == 1) db.adicionarUtilizador(new Motorista(email,nome,password,morada,nascimento,cHorario,false,new Carro(vmedia,preco,kmsTotal,kmsParcial,fiabilidade,matricula,localizacao)));
            if (tipoVeiculo == 2) db.adicionarUtilizador(new Motorista(email,nome,password,morada,nascimento,cHorario,false,new Moto(vmedia,preco,kmsTotal,kmsParcial,fiabilidade,matricula,localizacao)));
            if (tipoVeiculo == 3) db.adicionarUtilizador(new Motorista(email,nome,password,morada,nascimento,cHorario,false,new Carrinha(vmedia,preco,kmsTotal,kmsParcial,fiabilidade,matricula,localizacao)));
            System.out.printf("\nO seu registo foi efetuado.\n");
        }
        menuInicial(-1);
    }
    
    /** Apresenta informações particulares pedidas no enunciado do trabalho */
    private void informacoes() {
        int opcao = lerInteiro("\nNesta página são listadas algumas informações relativas à nossa aplicação. Selecione uma das opções para saber mais.\n\n1 - Listagem dos 10 clientes que mais gastam\n\n2 - Listagem dos 5 motoristas que apresentam mais desvios\n\n3 - Base de dados\n\n4 - Voltar atrás\n\nOpção: ");
        while (opcao < 1 | opcao > 4) {
            System.out.printf("Input inválido. Tente novamente.\n"); 
            opcao = lerInteiro("\nNesta página são listadas algumas informações relativas à nossa aplicação. Selecione uma das opções para saber mais.\n\n1 - Listagem dos 10 clientes que mais gastam\n\n2 - Listagem dos 5 motoristas que apresentam mais desvios\n\n3 - Base de dados\n\n4 - Voltar atrás\n\nOpção: ");
        }
        switch(opcao){
            case 1: db.top10clientes();
                    informacoes();
                    break;
            case 2: db.top5motoristas();
                    informacoes();
                    break;
            case 3: for(Utilizador u: db.getUsers().values()) System.out.println(u.toString() + "\n");
                    informacoes();
                    break;
            case 4: menuInicial(-1);
        }
    }
    
    /** Determina os parametros para realizar a viagem */
    private void fazerViagem(Cliente c) {
        System.out.printf("Introduza as coordenadas da sua localização atual.\n\n");
        int a = lerInteiro("Coordenada X: ");
        int b = lerInteiro("Coordenada Y: ");
        c.setLocalizacao(new Ponto2D(a,b));
        System.out.printf("Introduza as coordenadas para onde deseja ir.\n\n");
        int x = lerInteiro("Coordenada X: ");
        int y = lerInteiro("Coordenada Y: ");
        Ponto2D destino = new Ponto2D(x,y);
        int lugares = lerInteiro("Quantos lugares precisa? (1-8) ");
        while(lugares < 1 | lugares > 8) { 
            System.out.printf("Input inválido. Tente novamente.\n");
            lugares = lerInteiro("Quantos lugares precisa? (1-8) ");
        }
        int opcao = lerInteiro("Pretende chamar um táxi específico ou o que estiver mais próximo?\n1- Veículo Específico | 2- Veículo mais Próximo ");
        while (opcao < 1 | opcao > 2) {
            System.out.printf("Input inválido. Tente novamente.\n");
            opcao = lerInteiro("Pretende chamar um táxi específico ou o que está mais próximo?\n1- Veículo Específico | 2- Veículo mais Próximo ");
        }
        if (opcao == 1) {
            ArrayList<Motorista> mo = db.getMotoristas(lugares);
            System.out.printf("\nDe momento, os motoristas disponíveis são os seguintes:\n");
            for (Motorista m: mo) 
            System.out.println("\nMotorista " + mo.indexOf(m) + " -\n" + "Nome: " + m.getNome() + "\nVeículo: " + m.getVia() + "\nLocalização: " + m.getVia().getLocalizacao() + "\nCumprimento do horário: " + m.getCHorario() + "\nClassificação: " + m.getClassificacao() + "\nPreço: " + m.getVia().getPreco());
            int escolher = lerInteiro("\nEscolha um motorista entre os que foram listados: ");
            while (escolher < 0 | escolher > mo.size()) {
                System.out.printf("Input inválido. Tente novamente.\n");
                escolher = lerInteiro("Escolha um motorista entre os que foram listados: ");
            }
            Motorista sr = mo.get(escolher); // fazer um try catch aqui para o caso de nao houver motoristas disponiveis
            viajar(c,sr,destino);
        }
        if (opcao == 2) {
            Motorista sr = db.getMotoristaMaisProximo(c.getLocalizacao(),lugares);
            System.out.println("\nA sua localização atual é " + c.getLocalizacao() + ". O táxi mais próximo da sua localização é do motorista " + sr.getNome() + " que se encontra na posição " + sr.getVia().getLocalizacao() + ".\n");
            int prosseguir = lerInteiro("Deseja proseguir com a viagem?\n\n1- Sim | 2- Não ");
            while (prosseguir < 1 | prosseguir > 2) {
                System.out.printf("Input inválido. Tente novamente.\n");
                prosseguir = lerInteiro("Deseja proseguir com a viagem?\n\n1- Sim | 2- Não ");
            }
            if (prosseguir == 1) viajar(c,sr,destino);
            if (prosseguir == 2) menuCliente(c);
        }
    }
    
    /** Realiza a viagem consoante os parâmetros dados no menu do cliente */
    
    private void viajar(Cliente c, Motorista m, Ponto2D destino) {
        int fiabilidade = geraAleatorio();
        double distanciaViagem = c.getLocalizacao().distancia(destino);
        double distanciaTotal = c.getLocalizacao().distancia(m.getVia().getLocalizacao()) + distanciaViagem; // distância total = distância(cliente,motorista) + distância(cliente,destino)
        double preco = distanciaTotal * m.getVia().getPreco(); // preço = distância total * preço
        double precoReal = preco * (100 / fiabilidade); // preço real = distância total * preço * (100 / fiabilidade da viatura)
        double tempo = (distanciaTotal / m.getVia().getVmedia()) * 60; // tempo = (distância total / velocidade média) * 60 em minutos
        double tempoReal = tempo * (100 / fiabilidade); // tempo que demorará tendo em conta a fiabilidade da viatura
        int cHorario = 100;
        if (tempoReal > tempo * 1.25) { // se a diferença entre o tempo previsto e o tempo que demorou a viagem for superior a 25%
            System.out.printf("O preço da viagem é de " + round(precoReal) + "€, porém como a viagem demorou mais tempo do que o que foi estimado, terá que se combinar um preço.\n");
            double precoProposto = lerDouble("Sugira um preço: ");
            if (precoProposto < precoReal * 0.75) // se o preço proposto pelo cliente for inferior a 75% do preço da viagem
                System.out.printf("\nO preço que sugeriu é muito baixo. Como tal, a sua viagem de " + round(tempoReal) + " minutos tem um custo de " + round(precoReal) + " €.\n");
            else {
                precoReal = precoProposto;
                System.out.printf("\nA sua viagem teve um custo de " + round(precoReal) + "€ e uma duração de " + round(tempoReal) + " minutos.\n");
            }
        } 
        else System.out.printf("\nA sua viagem teve um custo de " + round(precoReal) + " euros e uma duração de " + round(tempoReal) + " minutos.\n");
        int classificacao = lerInteiro("Atribua uma classificação ao motorista (0-100): ");
        while (classificacao < 0 | classificacao > 100) {
            System.out.printf("Input inválido. Tente novamente.\n");
            classificacao = lerInteiro("Atribua uma classificação ao motorista (0-100): ");
        }
        if (tempoReal > tempo * 2) cHorario = 0;
        if (tempoReal > tempo * 1.75) cHorario = 25;
        if (tempoReal > tempo * 1.5) cHorario = 50;
        if (tempoReal > tempo * 1.25) cHorario = 75;
        Viagem v = new Viagem(c.getLocalizacao(),destino,round(tempoReal),round(precoReal),classificacao,cHorario);
        c.registarViagem(v); // regista a viagem do cliente
        c.setLocalizacao(v.getChegada().clone()); //altera a localização do cliente para o ponto de chegada da viagem
        m.registarViagem(v); // regista a viagem do motorista
        m.setClassificacao((m.getClassificacao() + classificacao) / m.getViagens().size()); // atualiza a classificação do motorista
        m.setCHorario((m.getCHorario() + cHorario) / m.getViagens().size()); // atualiza o cumprimento de horário do motorista
        m.getVia().setLocalizacao(v.getChegada().clone()); // altera a localização do motorista para o ponto de chegada da viagem
        m.setTotalFaturado(m.getTotalFaturado() + precoReal); // é adicionado o preço real ao total faturado
        m.setPrejuizo(m.getPrejuizo() + round(precoReal - preco)); // é adicionado a diferença entre o valor previsto e o valor real da viagem ao prejuizo do motorista
        m.setKmsRealizados(m.getKmsRealizados() + round(distanciaTotal));
        c.setCarteira(c.getCarteira() + precoReal);
        input.nextLine();
        menuCliente(c);
    }  
    
    /** Converte um double para um inteiro */
    private int round(double d){
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;            
        }else{
            return d<0 ? -(i+1) : i+1;          
        }
    }
    
    /** Calcula o valor faturado de um motorista entre datas*/
    private double faturadoData(Motorista m,LocalDate data1, LocalDate data2) {
        double faturado = 0;
        for (Viagem v: m.getViagens()) {
            if (v.getDataViagem().isAfter(data1) && v.getDataViagem().isBefore(data2)) faturado += v.getPreco();
        }
        return faturado;
    }
        
    /** Gera a um valor aleatorio de 1 a 100 */
    private int geraAleatorio() {
        Random f = new Random();
        return f.nextInt(101);
    }
    
    /** Verifica se o input é um inteiro */
    private int lerInteiro(String x) {
        Integer opcao = null;
        do {
            try {
                System.out.print(x);
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input inválido. Tente novamente.");
                input.nextLine();
            }
        } while (opcao == null);
        return (int) opcao;
    }
    
    /** Verifica se o input é um double */
    private double lerDouble(String x) {
        double opcao = -1;
        do {
            try {
                System.out.print(x);
                opcao = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Input inválido. Tente novamente.");
                input.nextLine();
            }
        } while (opcao == -1);
        return opcao;
    }
    
    /** Verifica se o o input é uma data */
    
    private LocalDate lerData(String x) {
        LocalDate data = null;
        do {
            try {
                System.out.printf(x);
                int ano = lerInteiro("Ano: ");
                int mes = lerInteiro("Mês: ");
                int dia = lerInteiro("Dia: ");
                data = data.of(ano,mes,dia);
            }
            catch (DateTimeException e) {
                System.out.println("Input inválido. Tente novamente. " + e.getMessage());
            }
        }
        while (data == null);
        return data;
    }
    
    /** Carrega estado da aplicação */
    private void loadFile() {
        try {
             FileInputStream fis = new FileInputStream("Grupo21_POO2017.txt");
             ObjectInputStream ois = new ObjectInputStream(fis);
             db = (DataBase) ois.readObject();
            }
        catch(IOException e) { 
            System.out.println("Ficheiro não encontrado!" + e.getMessage()); 
        }
        catch(ClassNotFoundException e) {
            System.out.println("Classe não encontrada!" + e.getMessage()); 
        }
    }
    
    /** Guarda estado da aplicação */
    
    private void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream("Grupo21_POO2017.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(db);
            oos.flush();
            oos.close();
        }
        catch (IOException e) {
            System.out.println("Ficheiro não encontrado! " + e.getMessage());
        }
    }
}