package src;

import java.io.IOException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Comparator;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ImobiliariaApp
{
    private static Imobiliaria imobiliaria;
    private static Menu mainMenu, compradorMenu, vendedorMenu, tipoUserMenu, tipoImovelMenu;

    public static void main(String[] args)
    {
        // 0 - noUser, 1 - comprador, 2 - Vendedor
        int op, x;
        boolean noUser = true;
        boolean comprador = false;
        boolean vendedor = false;
        
        loadMenus();
        carregarDados();
        //Será que podemos fazer um getUtilizador e ver se é null senão a instanceof e não tinhas que ter esta logica toda

        do{
            if(noUser)
            {   
                mainMenu.executa();
                op = mainMenu.getOpcao();
                x = executaOpcaoMain(op);
                if(x == 1)
                {
                    comprador = true;
                    noUser = vendedor = false;
                }
                else if(x == 2)
                {
                    vendedor = true;
                    noUser = comprador = false;
                }
            }
            else if(comprador)
            {
                compradorMenu.executa();
                op = compradorMenu.getOpcao();
                x = executaOpcaoComprador(op);
                if(x == 0)
                {
                    noUser = true;
                    vendedor = comprador = false;
                }
            }
            else
            {
                vendedorMenu.executa();
                op = vendedorMenu.getOpcao();
                x = executaOpcaoVendedor(op);
                if(x == 0)
                {
                    noUser = true;
                    vendedor = comprador = false;
                }
            }

        }while(op != 0);

        try{
            imobiliaria.gravaObj("Imobiliaria.ser");
            imobiliaria.log("log.txt", true);
        }catch(IOException e){ System.out.println("Erro ao gravar os dados\n"); }
        
        System.out.println("Volte sempre...");
    }
    
    /**
     * Funçao que carrega os menus.
     */
    public static void loadMenus()
    {
        String[] opMainMenu = { "Registar Utilizador",
                                "Iniciar Sessão",
                                "Consultar a lista de todos os imoveis de um dado tipo",
                                "Consultar a lista de todos os imoveis habitáveis",
                                "Obter um mapeamento entre todos os imoveis e os respectivos vendedores",
                                "Gravar estado"};

        String[] opComprMenu = { "Marcar um imovel como favorito",
                                 "Consultar imoveis favoritos",
                                 opMainMenu[2],
                                 opMainMenu[3],
                                 opMainMenu[4],
                                 "Entrar no leilao",
                                 opMainMenu[5],
                                 "Fechar Sessão"};

        String[] opVendMenu = { "Colocar um imovel á venda",
                                "Obter lista das ultimas 10 consultas",
                                "Alterar um estado de um imovel",
                                "Obter lista de id's dos imoveis mais consultados",
                                opMainMenu[2],
                                opMainMenu[3],
                                opMainMenu[4],
                                "Criar um Leilao",
                                "Iniciar leilao",
                                opMainMenu[5],
                                "Fechar Sessão"};

        String[] opUserMenu = { "Registar Comprador",
                                "Registar Vendedor"};

        String[] opImovelMenu = { "Registar Moradia",
                                  "Registar Apartamento",
                                  "Registar Loja (nao habitavel)",
                                  "Registar Loja (habitável)",
                                  "Registar Terreno",};

        mainMenu = new Menu(opMainMenu, "Menu Principal");
        compradorMenu = new Menu(opComprMenu, "Menu Comprador");
        vendedorMenu = new Menu(opVendMenu, "Menu Vendedor");
        tipoUserMenu = new Menu(opUserMenu, "Registar utilizador");
        tipoImovelMenu = new Menu(opImovelMenu, "Registar Imovel");
    }

    /**
     * Funçao que le os dados de um estado gravado em ficheiro.
     */
    public static void carregarDados()
    {
        try{
            imobiliaria = imobiliaria.initApp();
        }
        catch(IOException e){ 
            imobiliaria = new Imobiliaria();
            System.out.println("Erro ao ler os dados\nErro de leitura.\n");
        }
        catch(ClassNotFoundException e){
            imobiliaria = new Imobiliaria();
            System.out.println("Erro ao ler os dados!\nFicheiro com formato desconhecido.\n");
        }
        catch(ClassCastException e){
            imobiliaria = new Imobiliaria();
            System.out.println("Erro ao ler os dados!\nErro de formato.\n");
        }
    }

    /**
     * Metodo que mapeia a opçao introduzida com a respectiva funçao (Menu main)
     * @param op Opçao escolhida pelo utilizador
     */
    private static int executaOpcaoMain(int op)
    {
        int cod = 0;
    
        switch(op)
        {
            case 1:
                opRegistarUtilizador();
                break;
            case 2:
                cod = opIniciaSessao(); // 1 - Comprador, 2 - Vendedor; 0 - se der erro!!!
                break;
            case 3:
                opGetImovel();
                break;
            case 4:
                opGetHabitavel();
                break;
            case 5:
                opGetMapeamento();
                break;
            case 6:
                opGravaEstado();
                break;
            default: 
                break;
        }

        return cod;
    }

    /**
     * Metodo que mapeia a opçao introduzida com a respectiva funçao (Menu comprador)
     * @param op Opçao escolhida pelo utilizador
     */
    private static int executaOpcaoComprador(int op)
    {
        int cod = 1;

        switch(op)
        {
            case 1:
                opSetFavorito();
                break;
            case 2:
                opGetFavoritos();
                break;
            case 3:
                opGetImovel();
                break;
            case 4:
                opGetHabitavel();
                break;
            case 5:
                opGetMapeamento();
                break;
            case 6:
                opAdicionaComprador();
                break;
            case 7:
                opGravaEstado();
                break;
            case 8:
                opFechaSessao();
                cod = 0;
                break;
            default:
                break;
        }

        return cod;
    }
    
    /**
     * Metodo que mapeia a opçao introduzida com a respectiva funçao (Menu vendedor)
     * @param op Opçao escolhida pelo utilizador
     */
    private static int executaOpcaoVendedor(int op)
    {
        int cod = 1;

        switch(op)
        {
            case 1:
                opRegistaImovel();
                break;
            case 2:
                opGetConsultas();
                break;
            case 3:
                opSetEstado();
                break;
            case 4:
                opGetTopImoveis();
                break;
            case 5:
                opGetImovel();
                break;
            case 6:
                opGetHabitavel();
                break;
            case 7:
                opGetMapeamento();
                break;
            case 8:
                opCriaLeilao();
                break;
            case 9:
                opIniciaLeilao();
                break;
            case 10:
                opGravaEstado();
                break;
            case 11:
                opFechaSessao();
                cod = 0;
                break;
            default:
                break;
        }

        return cod;
    }

    /**
     * Funçao que grava o estado atual para ficheiro.
     */
    public static void opGravaEstado()
    {
        String standard = "Imobiliaria.ser";
        try{
            imobiliaria.gravaObj(standard);
            imobiliaria.log("log.txt", true);
        }catch(IOException e) {System.out.println(e.getMessage());}
        System.out.println("\nO estado atual foi guardado com sucesso em Imobiliaria.ser");
    }
    
    /**
     * Funçao que pede todas as informaçoes necessarias ao utilizador para criar um utilizador e regista-lo, tanto Comprador como Vendedor.
     */
    public static void opRegistarUtilizador()
    {
        int op;
        Utilizador novoU = null;
        Scanner input = new Scanner(System.in);
        String email, nome, password, morada, data;
        GregorianCalendar d;
        boolean validDate= true;

        tipoUserMenu.executa();
        op = tipoUserMenu.getOpcao();
        if(op == 1 || op == 2)
        {
            try{
                System.out.print("Email: ");
                email = input.nextLine();
                System.out.print("Nome: ");
                nome = input.nextLine();
                System.out.print("Password: ");
                password = input.nextLine();
                System.out.print("Morada: ");
                morada = input.nextLine();
                System.out.print("Data de nascimento (aaaa/MM/dd): ");
                data = input.nextLine();
                d = validateDate(data);
                switch(op)
                {
                    case 1: // 1 - comprador
                        novoU = new Comprador(email, nome, password, morada, d);
                        break;
                    case 2: // 2 - Vendedor
                        novoU = new Vendedor(email, nome, password, morada, d);
                        break;
                }
                imobiliaria.registarUtilizador(novoU);

            }
            catch(NoSuchElementException e){System.err.println("Linha em branco\n"); }
            catch(UtilizadorExistenteException e) {System.out.println(e.getMessage());}
            catch(ParseException e) { System.out.println("Formato de data inválido!\n");}
        }
        else
        {
            if(op != 0)
                System.out.println("Opçao inválida, registo cancelado!\n");
        }
        input.close();
    }
    
    /**
     * Funçao que pede informaçao necessaria ao utilizador para o mesmo iniciar sessao no sistema
     */
    public static int opIniciaSessao()
    {
        Scanner input = new Scanner(System.in);
        String email, pass;
        int cod = 0;
        
        System.out.println("Login");
        try{    
            System.out.print("\tEmail: ");
            email = input.nextLine();
            System.out.print("\tPassword: ");
            pass = input.nextLine();
            
            imobiliaria.iniciaSessao(email, pass);

        }catch(NoSuchElementException e) { System.err.println("Linha em branco\n"); }
         catch(SemAutorizacaoException e) { System.out.println(e.getMessage()); }
        
         if(imobiliaria.isCompradorLogged())
            cod = 1;
         else if(imobiliaria.isVendedorLogged())
            cod = 2;
        
        input.close();
        return cod;
    }
    
    /**
     * Funçao que fecha a sessao do utilizador.
     */
    public static void opFechaSessao()
    {
        System.out.println("A fechar sessao...");
        imobiliaria.fechaSessao();
    }
    
    /**
     * Funçao que pede toda a informaçao necessaria ao utilizador para que possa criar um Imovel e regista-lo no sistema
     */
    public static void opRegistaImovel()
    {
        int op;
        Scanner input = new Scanner(System.in);
        String id, rua, estado;
        Double precoPedido, precoAceite;
        
        Imovel novoI = null;
        
        tipoImovelMenu.executa();
        op = tipoImovelMenu.getOpcao();
        if(op > 0 || op < tipoImovelMenu.nmrOpcoes())
        {
            try{
                System.out.print("ID: ");
                id = input.nextLine();
                System.out.print("Rua: ");
                rua = input.nextLine();
                System.out.print("Estado (venda/reservado/vendido): ");
                do{
                    estado = input.nextLine();
                }while(!Imovel.validaEstado(estado));
                System.out.print("Preço Pedido: ");
                precoPedido = input.nextDouble();
                input.nextLine();
                System.out.print("Preço Aceite: ");
                precoAceite = input.nextDouble();
                input.nextLine();
                switch(op)
                {
                    case 1:
                        novoI = criaMoradia(id, rua, estado, precoPedido, precoAceite);
                        break;
                    case 2:
                        novoI = criaApartamento(id, rua, estado, precoPedido, precoAceite);
                        break;
                    case 3:
                        novoI = criaLojaNH(id, rua, estado, precoPedido, precoAceite);
                        break;
                    case 4:
                        novoI = criaLojaH(id, rua, estado, precoPedido, precoAceite);
                        break;
                    case 5:
                        novoI = criaTerreno(id, rua, estado, precoPedido, precoAceite);
                }
                
                imobiliaria.registaImovel(novoI);
            }catch(NoSuchElementException e) {System.err.println("Linha em branco!");}
             catch(ImovelExisteException e) {System.out.println("Imovel ja existe na base de dados!");}
             catch(SemAutorizacaoException e) {System.out.println("Nao tem autorizaçao para realizar esta acçao");}
             catch(TipoMoradiaInvalidoException e) {System.out.println("Tipo de moradia invalido!");}
        }
        input.close();
    }
    
    /**
     * Funçao que cria uma Moradia conforme os dados inseridos pelo utilizador
     */
    public static Moradia criaMoradia(String id, String rua, String estado, Double precoPedido, Double precoAceite) throws TipoMoradiaInvalidoException
    {
        Scanner input = new Scanner(System.in);
        String tipo = "";
        int areaImpl, areaTCoberta, areaTerrEnv, nrQuartos;
        Moradia m = null;
        try{
            do{
                System.out.print("Tipo de moradia (isolada/geminada/banda/gaveto): ");
                tipo = input.nextLine();
            }while(!Moradia.validaTipo(tipo.toLowerCase()));
        
            System.out.print("Area implantaçao: ");
            areaImpl = input.nextInt();
            input.nextLine();
            System.out.print("Area total coberta: ");
            areaTCoberta = input.nextInt();
            input.nextLine();
            System.out.print("Area terreno envolvente: ");
            areaTerrEnv = input.nextInt();
            input.nextLine();
            System.out.print("Numero de quartos: ");
            nrQuartos = input.nextInt();
            input.nextLine();
            m = new Moradia(id, rua, estado, precoPedido, precoAceite, 
                           tipo, areaImpl, areaTCoberta, areaTerrEnv, nrQuartos);
        }catch(NoSuchElementException e) {System.out.println(e.getMessage());}
            input.close();
        return m;
    }
    
    
    /**
     * Funçao que cria um Apartamento conforme os dados inseridos pelo utilizador
     */
    public static Apartamento criaApartamento(String id, String rua, String estado, Double precoPedido, Double precoAceite)
    {
        Scanner input = new Scanner(System.in);
        String tipo, gar;
        int area, nrQuartos, nrWC, nrPorta, andar;
        boolean garagem;
        
        do{
            System.out.print("Tipo de apartamento (simples/duplex/triplex): ");
            tipo = input.nextLine();
        }while(!Apartamento.validaTipo(tipo.toLowerCase()));
        
        System.out.print("Area: ");
        area = input.nextInt();
        input.nextLine();
        System.out.print("Numero de quartos: ");
        nrQuartos = input.nextInt();
        input.nextLine();
        System.out.print("Numero de WC's: ");
        nrWC = input.nextInt();
        input.nextLine();
        System.out.print("Numero da porta: ");
        nrPorta = input.nextInt();
        input.nextLine();
        System.out.print("Andar: ");
        andar = input.nextInt();
        input.nextLine();
        do{
            System.out.print("Tem garagem ([S]im/[N]ao): ");
            gar = input.nextLine().toLowerCase();
        }while(!gar.equals("sim") && !gar.equals("nao")
             &&!gar.equals("s") && !gar.equals("n"));
        
        garagem = gar.charAt(0) == 's';
        
        input.close();
        return new Apartamento(id, rua, estado, precoPedido, precoAceite,
                               tipo, area, nrQuartos, nrWC, nrPorta, andar, garagem);
    }
    
    
    /**
     * Funçao que cria uma Loja conforme os dados inseridos pelo utilizador
     */
    public static Loja criaLojaNH(String id, String rua, String estado, Double precoPedido, Double precoAceite)
    {
        Scanner input = new Scanner(System.in);
        String tipoNegocio, cb;
        int area, nrPorta;
        boolean wc;
        
        System.out.print("Area: ");
        area = input.nextInt();
        input.nextLine();
        System.out.print("Tipo de Negocio: ");
        tipoNegocio = input.nextLine();
        System.out.print("Numero da porta: ");
        nrPorta = input.nextInt();
        input.nextLine();
        do{
            System.out.print("Tem WC ([S]im/[N]ao): ");
            cb = input.nextLine().toLowerCase();
        }while(!cb.equals("sim") && !cb.equals("nao")
             &&!cb.equals("s") && !cb.equals("n"));
        
        wc = cb.charAt(0) == 's';
    
        input.close();
        return new Loja(id, rua, estado, precoPedido, precoAceite,
                        area, wc, tipoNegocio, nrPorta);
    }
    
    
    /**
     * Funçao que cria uma LojaHabitavel conforme os dados inseridos pelo utilizador
     */
    public static LojaHabitavel criaLojaH(String id, String rua, String estado, Double precoPedido, Double precoAceite)
    {
        Apartamento ap = criaApartamento(id, rua, estado,precoPedido, precoAceite);
        Loja lj = criaLojaNH(id, rua, estado, precoPedido, precoAceite);
        
        return new LojaHabitavel(lj, ap);
    }
    
    
    /**
     * Funçao que cria um Terreno conforme os dados inseridos pelo utilizador
     */
    public static Terreno criaTerreno(String id, String rua, String estado, Double precoPedido, Double precoAceite)
    {
        Scanner input = new Scanner(System.in);
        String tipoConstr, rEsg;
        int area;
        double diametro, kWhmax;
        boolean redeEsgotos;
        
        System.out.print("Area: ");
        area = input.nextInt();
        input.nextLine();
        System.out.print("Diametro canalizaçoes: ");
        diametro = input.nextDouble();
        input.nextLine();
        System.out.print("kWh maximo: ");
        kWhmax = input.nextDouble();
        input.nextLine(); /*porque o nextDouble, tal como o nextInt, nao consome o ultimo \n*/
        do{
            System.out.print("Tipo de construçao (habitacional/armazens): ");
            tipoConstr = input.nextLine().toLowerCase();
        }while(!Terreno.validaTipoConstrucao(tipoConstr));
        
        do{
            System.out.print("Tem rede esgotos ([S]im/[N]ao): ");
            rEsg = input.nextLine().toLowerCase();
        }while(!rEsg.equals("sim") && !rEsg.equals("nao")
             &&!rEsg.equals("s") && !rEsg.equals("n"));
        
        redeEsgotos = rEsg.charAt(0) == 's';
        
        input.close();
        return new Terreno(id, rua, estado, precoPedido, precoAceite,
                           area, tipoConstr, diametro, kWhmax, redeEsgotos);
    }
    
    /**
     * Funçao que imprime as 10 ultimas consultas feitas aos imoveis em venda de um vendedor.
     */
    public static void opGetConsultas()
    {
        List<Consulta> consultas = null; 
        try{
            consultas = new ArrayList<>(imobiliaria.getConsultas());
        }catch(SemAutorizacaoException e) { System.out.println(e.getMessage());}
        
        Collections.reverse(consultas);
        
        /*Talvez fazer disto navegavel!?*/
        if(consultas.isEmpty())
        {
            System.out.println("\nNao existem consultas.");
        }
        else{
            System.out.println("\nUltimas 10 consultas: ");
            for(Consulta c: consultas)
                System.out.println(c.toString());
        }
    }
    
    /**
     * Funçao que altera o estado de um imovel inserido pelo utilizador
     */
    public static void opSetEstado()
    {
        String id, estado;
        Scanner input = new Scanner(System.in);

        try{
            System.out.print("ID: ");
            id = input.nextLine();
            System.out.print("Estado: ");
            estado = input.nextLine();
            
            imobiliaria.setEstado(id, estado);
        }catch(NoSuchElementException e) {System.out.println(e.getMessage());}
         catch(SemAutorizacaoException e) {System.out.println(e.getMessage());}
         catch(ImovelInexistenteException e) {System.out.println(e.getMessage());}
         catch(EstadoInvalidoException e) {System.out.println(e.getMessage());}
         
         input.close();
    }
    
    /**
     * Funçao que imprime uma lista de ID's dos imoveis mais consultados do vendedor que a chama
     */
    public static void opGetTopImoveis()
    {
        Scanner input = new Scanner(System.in);
        int nrConsultas = 0;
        Set<String> ids = null;
        Iterator<String> it = null;
        
        try{
            System.out.print("Numero consultas: ");
            nrConsultas = input.nextInt();
            
            ids = new TreeSet<>(imobiliaria.getTopImoveis(nrConsultas));
        }catch(SemAutorizacaoException e) {System.out.println(e.getMessage());}
         catch(NoSuchElementException e) {System.out.println(e.getMessage());}
        
        if(ids.isEmpty())
        {
            System.out.println("\nNao existem Imoveis com mais de "+nrConsultas+" consultas.");
        }
        else{
            System.out.println("\nImoveis (id) com mais de " + nrConsultas + " consultas: ");
            it = ids.iterator();
            while(it.hasNext())
                System.out.println(it.next());
        }
        input.close();
    }
    
    /**
     * Funçao que imprime a lista dos imoveis de um dado tipo inserido pelo utilizador ate um certo preço tambem escolhido pelo utilizador
     */
    public static void opGetImovel()
    {
        Scanner input = new Scanner(System.in);
        String classe;
        int preco = 0;
        List<Imovel> im = null;
        
        try{
            System.out.print("Classe de imovel (Moradia/Apartamento/Terreno/Loja/LojaHabitavel): ");
            classe = input.nextLine();
            System.out.print("Preco (ate): ");
            preco = input.nextInt();
            
            /*Tentar ordenar isto por preço (comparator)*/
            im = new ArrayList<>(imobiliaria.getImovel(classe, preco));
            if(im.isEmpty())
            {
                System.out.println("Nao existem Imoveis do tipo "+classe+" com preço menor ou igual a "+preco+".");
            }
            else{
                System.out.println("\nImoveis (id) com preco ate "+preco+":");
                for(Imovel i: im)
                     System.out.println(i.toString()+"\n");
            }
        }catch(NoSuchElementException e){System.out.println(e.getMessage());}
        
        
        input.close();
    }
    
    /**
     * Funçao que imprime todos os imoveis habitaveis do sistema ate um certo preço escolhido pelo utilizador
     */
    public static void opGetHabitavel()
    {
        Scanner input = new Scanner(System.in);
        List<Habitavel> im = null;
        int preco = 0;
        
        try{
            System.out.print("Gostaria de ver Imoveis habitaveis com valor ate: ");
            preco = input.nextInt();
            
            im = new ArrayList<>(imobiliaria.getHabitavel(preco));
        }catch(InputMismatchException e) {System.out.println("Por favor, insira um numero inteiro.");}
         catch(NoSuchElementException e){System.out.println("Deixou a linha em branco possivelmente.");}
        
         if(im.isEmpty())
            System.out.println("Nao existem Imoveis habitaveis abaixo de "+preco+" euros.");
         else
         {
             System.out.println("\nImoveis Habitaveis (<="+preco+"): ");
             for(Habitavel h: im)
             {
                Imovel i = (Imovel) h;
                System.out.println(i.toString()+"\n");
             }
         }
         
        input.close();
    }
    
    /**
     * Funçao que imprime o mapeamento de todos os imoveis com os respectivos vendedores
     */
    public static void opGetMapeamento()
    {
        Map<Imovel, Vendedor> map = imobiliaria.getMapeamentoImoveis();
        Map<Imovel, Vendedor> sortedV = null;
        int size;
        if(map.isEmpty()) System.out.println("Nao existem vendedores com imoveis para venda");
        else
        {
            size = map.size();
            System.out.println("Mapeamento dos Imoveis com os seus respectivos Vendedores: ");
            sortedV = new HashMap<>(size);
            List<Map.Entry<Imovel, Vendedor>> eList = new ArrayList<>(map.entrySet());
            
            Collections.sort(eList, new Comparator<Map.Entry<Imovel, Vendedor>>()
            {
                @Override
                public int compare(Map.Entry<Imovel, Vendedor> e1, Map.Entry<Imovel, Vendedor> e2)
                {
                    return (e1.getValue().compareTo(e2.getValue()));
                }
            } );
            
            Iterator<Map.Entry<Imovel,Vendedor>> it = eList.iterator();
            boolean mudou = false;
            Vendedor aux = null;
            
            while(it.hasNext())
            {
                Map.Entry<Imovel, Vendedor> entry = it.next();
                
                if(aux != entry.getValue())
                {
                    aux = entry.getValue();
                    mudou = true;
                }
                else
                    mudou = false;
                
                if(mudou)
                {
                    System.out.println("-------------------------------------Vendedor-------------------------------------------");
                    System.out.println("Nome: "+entry.getValue().getNome() + "\nEmail: "+entry.getValue().getEmail()+"\n");
                }
                else
                {
                    System.out.println("------------------------------------------------");
                }
                
                System.out.println(entry.getKey().toString()+"\n");
                
            }
            
                    System.out.println("--------------------------------------------------------------------------------------- ");
        }
    }
    
    /**
     * Funçao que marca como favorito um imovel escolhido pelo utilizador
     */
    public static void opSetFavorito()
    {
        Scanner input = new Scanner(System.in);
        String id;
        
        try{
            System.out.print("ID do imovel: ");
            id = input.nextLine();
            
            imobiliaria.setFavorito(id);
        }catch(NoSuchElementException e){System.out.println(e.getMessage());}
         catch(SemAutorizacaoException e){ System.out.println(e.getMessage());}
         catch(ImovelInexistenteException e) {System.out.println(e.getMessage());}
        
        input.close();
    }
    
    /**
     * Funçao que imprime todos os imoveis favoritos de um utilizador
     */
    public static void opGetFavoritos()
    {
        Scanner input = new Scanner(System.in);
        Set<Imovel> favs = null;
        Iterator<Imovel> it = null;
        
        try{
            favs = new TreeSet<>(imobiliaria.getFavoritos());
            
        }catch(SemAutorizacaoException e) {System.out.println(e.getMessage());}
        
        if(favs.isEmpty())
           System.out.println("\nNao tem imoveis favoritos.");
        else
        {
            System.out.println("\nLista dos seus Imoveis favoritos: ");
            it = favs.iterator();
            while(it.hasNext()){
                Imovel im = it.next();
                System.out.println(im.toString());
            }
        }
        
        input.close();
    }
    
    /**
     * Funçao que valida o formato de uma data em String
     */
    public static GregorianCalendar validateDate(String data) throws ParseException
    {
        String format = "yyyy/MM/dd";

        Date d = new SimpleDateFormat(format).parse(data);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);        
        
        return (GregorianCalendar) cal;
    }
    
    /**
     * Funçao que cria leilao sobre um imovel escolhido pelo utilizador
     */
    public static void opCriaLeilao()
    {
        Scanner input = new Scanner(System.in);
        String id;
        try{
            System.out.print("ID do imovel a leilao: ");
            id = input.nextLine();
            imobiliaria.criaLeilao(imobiliaria.getImovelFromId(id));
            System.out.println("Leilao criado com sucesso!");
        }catch(SemAutorizacaoException e) {System.out.println(e.getMessage());}
         catch(ImovelInexistenteException e) {System.out.println(e.getMessage());}
        
    }
    
    /**
     * Funçao que permite a um comprador entrar no leilao existente.
     */
    public static void opAdicionaComprador()
    {
        Scanner input = new Scanner(System.in);
        String email, resp;
        double limite, incrementos, minutos;
        
        
        try{
            System.out.println(imobiliaria.getInfoLeilao());
            do{
                System.out.print("Pretende entrar no leilao? ([S]im|[N]ao)");
                resp = input.nextLine().toLowerCase();
            }while(!resp.equals("sim") && !resp.equals("nao")
                 &&!resp.equals("s") && !resp.equals("n"));
            
            if(resp.equals("nao") || resp.equals("n")) return;
            
            email = imobiliaria.loggedEmail();
            System.out.print("Limite: ");
            limite = input.nextDouble();
            input.nextLine();
            System.out.print("Incrementos: ");
            incrementos = input.nextDouble();
            input.nextLine();
            System.out.print("Minutos: ");
            minutos = input.nextDouble();
            input.nextLine();
            
            imobiliaria.adicionaComprador(email, limite, incrementos, minutos);

        }catch(NoSuchElementException e) {System.out.println(e.getMessage());}
         catch(LeilaoTerminadoException e) {System.out.println(e.getMessage());}
         catch(SemAutorizacaoException e) {System.out.println(e.getMessage());}
         catch(LeilaoInexistenteException e) {System.out.println(e.getMessage());}
    }
    
    /**
     * Funçao que inicia o leilao
     */
    public static void opIniciaLeilao()
    {
        Scanner input = new Scanner(System.in);
        int tempo;
        try{
            System.out.print("Duraçao: ");
            tempo = input.nextInt();
            input.nextLine();
            imobiliaria.iniciaLeilao(tempo, System.out);
            Comprador c = imobiliaria.encerraLeilao();
            System.out.println("O Vencedor do leilao foi o utilizador com email: "+c.getEmail());
        }catch(SemAutorizacaoException e){System.out.println(e.getMessage());}
         catch(InterruptedException e){System.out.println(e.getMessage());}
         catch(IOException e) {System.out.println(e.getMessage());}
         catch(ImovelInexistenteException e) {System.out.println(e.getMessage());}
         catch(LeilaoSemLicitadoresException e) {System.out.println(e.getMessage());}
         catch(LeilaoInexistenteException e) {System.out.println(e.getMessage());}

         input.close();
         
         try{
             imobiliaria.setEstado(imobiliaria.imovelEmLeilao() , "reservado");
         }catch(SemAutorizacaoException e) {System.out.println(e.getMessage());}
          catch(ImovelInexistenteException e) {System.out.println(e.getMessage());}
          catch(EstadoInvalidoException e) {System.out.println(e.getMessage());}
          
          imobiliaria.apagaLeilao();
    }
}
