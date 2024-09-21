import java.util.Scanner;

class Paciente {
    String nome;
    int idade;
    String hMedico;
    int cpf; 
    String ultConsulta;
    Paciente prox; // Próximo nó
    Paciente ant; // Nó anterior

    public Paciente(String nome, int idade, String hMedico, String ultConsulta, int cpf) {
        this.nome = nome;
        this.idade = idade;
        this.hMedico = hMedico;
        this.cpf = cpf;
        this.ultConsulta = ultConsulta;
        this.prox = null;
        this.ant = null;
    }
}

class Medico {
    String nome;
    int crm;
    int cpf;
    String especialidade;
    boolean disponibilidade;
    int Patendidos;
    Medico prox; // Próximo nó
    Medico ant; // Nó anterior

    public Medico(String nome, int crm, String especialidade, boolean disponibilidade, int cpf) {
        this.nome = nome;
        this.crm = crm;
        this.cpf = cpf; // se for pesquisar algo do evento e do restauranrte o parametro de busca vai ser o cpf 
        this.especialidade = especialidade;
        this.disponibilidade = disponibilidade;
        this.Patendidos = 0;
        this.prox = null;
        this.ant = null;
    }
    public void incrementaPacientesAtendidos() {
        this.Patendidos++;
    }

}

class Consulta {
    String nome;
    String ultConsulta;
    Consulta prox; // Próximo nó
    Consulta ant; // Nó anterior

    public Consulta(String nome, String ultConsulta) {
        this.nome = nome;
        this.ultConsulta = ultConsulta;
        this.prox = null;
        this.ant = null;
    }
}

class ListaPacientes {
    private Paciente inicio;

    public ListaPacientes() {
        this.inicio = null;
    }

    // Método para criar um novo paciente
    public Paciente criarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do paciente:");
        String nome = scanner.nextLine();

        System.out.println("Digite a idade do paciente:");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        System.out.println("Histórico médico do paciente:");
        String hMedico = scanner.nextLine();

        System.out.println("Digite o CPF do paciente");
        int cpf = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Última consulta do paciente (formato DD/MM/AAAA):");
        String ultConsulta = scanner.nextLine();

        return new Paciente(nome, idade, hMedico, ultConsulta, cpf );
    }

    // Método para inserir paciente ordenado alfabeticamente
    public boolean inserirPacienteOrdenado(Paciente novo) {
        if (inicio == null) {
            inicio = novo;  //conferiu se tava vazia 
            return true;
        }
// se n tiver vazia 
        Paciente atual = inicio;// estabelece aux para percorrer a lista 
        Paciente anterior = null;// ta null pq ainda n percorreu a lista 

        while (atual != null && atual.nome.compareToIgnoreCase(novo.nome) < 0) {// enq o atual for antt do novo 
            anterior = atual; // encontrar a posiçao correta p botar 
            atual = atual.prox;
        }

        if (atual != null && atual.nome.equalsIgnoreCase(novo.nome)) {
            return false;
        }

        if (anterior == null) { // null pq ainda n tem nada na lista 
            novo.prox = inicio;
            inicio.ant = novo;
            inicio = novo;
        } else {
            anterior.prox = novo;
            novo.ant = anterior; // else pq tem paciente e vai ser botado ente o anterior e prox  
            novo.prox = atual;
            if (atual != null) {
                atual.ant = novo;
            }
        }

        return true;
    }

    public void imprimirLista() {
        Paciente atual = inicio;
        while (atual != null) {
            System.out.println("Nome: " + atual.nome);
            System.out.println("Idade: " + atual.idade);
             System.out.println("cpf " + atual.cpf);
            System.out.println("Histórico Médico: " + atual.hMedico);
            System.out.println("Última Consulta: " + atual.ultConsulta);
            System.out.println("----------------------");
            atual = atual.prox;
        }
    }


    // busca paciente  
    public Paciente buscarPaciente(String nome){
        Paciente atual = inicio;
        while (atual != null){// enqunto a lista nao ta vazia 
            if (atual.nome.equalsIgnoreCase(nome)){//compara se o nome do paciente na lista é igual ao q o usuario ta procurando 
                return atual;
            }
            atual = atual.prox; // o ponteiro vai andando e entra no while novamente  
        }
        return null;//se percorrer tudo e n achar ai null 


    }
    // excluir paciente
    public boolean excluirPaciente(String nome){

        Paciente atual = inicio;// definidno q vai do começo da lista 
        Paciente anterior = null;

        while (atual != null && !atual.nome.equalsIgnoreCase(nome)){// enquanto tem no na lista e nao chegou no fim 
            anterior = atual; 
            atual = atual.prox; 
        } 
            if(atual==null){
            return false; // pq tava vazia ou n encontrou o paciente 
         } 
            if(anterior == null){// tirar o primeiro paciente 
                inicio = atual.prox; 
                if(inicio!=null){
                    inicio.ant=null; 
                }
        } else {  // tirara do final ou meio 
                    anterior.prox = atual.prox;
                    if (atual.prox != null) {
                        atual.prox.ant = anterior;// CONFUSO 
                    }
                }
                return true; 
        }
    }




class ListaMedicos {
    private Medico inicio;

    public ListaMedicos() {
        this.inicio = null;
    }

    public Medico criarMedico() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do médico:");
        String nome = scanner.nextLine();

        System.out.println("Digite o CRM do médico:");
        int crm = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        System.out.println("Digite a especialidade do médico:");
        String especialidade = scanner.nextLine();

        System.out.println("Digite o CPF do medico");
        int cpf = scanner.nextInt();
        scanner.nextLine();

        System.out.println("O médico está disponível? (1 para sim, 0 para não):");
        int disponibilidade = scanner.nextInt();
        boolean disponivel = disponibilidade != 0;

        return new Medico(nome, crm, especialidade, disponivel, cpf);
    }

    public boolean inserirMedicoOrdenado(Medico novo) {
        if (inicio == null) {
            inicio = novo;
            return true;
        }

        Medico atual = inicio;
        Medico anterior = null;

        while (atual != null && atual.nome.compareToIgnoreCase(novo.nome) < 0) {
            anterior = atual;
            atual = atual.prox;
        }

        if (atual != null && atual.nome.equalsIgnoreCase(novo.nome)) {
            return false;
        }

        if (anterior == null) {
            novo.prox = inicio;
            inicio.ant = novo;
            inicio = novo;
        } else {
            anterior.prox = novo;
            novo.ant = anterior;
            novo.prox = atual;
            if (atual != null) {
                atual.ant = novo;
            }
        }

        return true;
    }

    public void imprimirLista() {
        Medico atual = inicio;
        while (atual != null) {
            System.out.println("Nome: " + atual.nome);
            System.out.println("CRM: " + atual.crm);
             System.out.println("cpf do medico " + atual.cpf);
            System.out.println("Especialidade: " + atual.especialidade);
            System.out.println("Disponibilidade: " + (atual.disponibilidade ? "Sim" : "Não"));
            System.out.println("----------------------");
            atual = atual.prox;
        }
    }
    // busca de medico 
    public Medico buscarMedico(int crm){
        Medico atual = inicio;

        while (atual != null){// enqunto a lista nao ta vazia 
            if (atual.crm==crm ){//compara se o nome do paciente na lista é igual ao q o usuario ta procurando 
                return atual;
            }
            atual = atual.prox; // o ponteiro vai andando e entra no while novamente  
        }
        return null;//se percorrer tudo e n achar ai null 

    }

    // excluir medico
    public boolean excluirMedico(int crm){

        Medico atual = inicio;// definidno q vai do começo da lista 
        Medico anterior = null;

        while (atual != null && atual.crm != crm) { // enquanto o crm NAO for o mesmo ele continua prpcurando
            anterior = atual;
            atual = atual.prox;
        }
            if(atual==null){ 
            return false; // pq tava vazia ou n encontrou o paciente 
         } 
            if(anterior == null){// tirar o primeiro paciente 
                inicio = atual.prox; 
                if(inicio!=null){
                    inicio.ant=null; 
                }
        } else {  // tirara do final ou meio 
                    anterior.prox = atual.prox;
                    if (atual.prox != null) {
                        atual.prox.ant = anterior;
                    }
                }
                return true; 
        }
    //medico que tem mais atendimento 
    public Medico medicoMaisAtendeu() {
        Medico atual = inicio;
        Medico medicoMaisAtendeu = null; // ainda nntem nada
        int maxPacientes = -1; // garantir q a qtd de pacientes seja maio q -1
        while (atual != null) {                                                      //REVERRRRR 
            if (atual.Patendidos > maxPacientes) {
                maxPacientes = atual.Patendidos;
                medicoMaisAtendeu = atual;
            }
            atual = atual.prox;
        }

        return medicoMaisAtendeu;



    }
}
                                // CONSULTA 
class ListaConsultas {
    private Consulta inicio;

    public ListaConsultas() {
        this.inicio = null;
    }

    public Consulta criarConsulta(ListaMedicos listaMedicos) { 
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do paciente para a consulta:");
        String nome = scanner.nextLine();

        System.out.println("Digite a data da última consulta (formato DD/MM/AAAA):");
        String ultConsulta = scanner.nextLine();

        System.out.println("diga o crm do medico que ira atender");
        int crm = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado


          Medico medico = listaMedicos.buscarMedico(crm); // ENTENDI DIREITO NAOOOOO
        if (medico != null) {
            medico.incrementaPacientesAtendidos();
        } else {
            System.out.println("Médico não encontrado.");
        }


        return new Consulta(nome, ultConsulta);
    }
//inserir consulta ordenada por data
    public boolean inserirConsultaOrdenada(Consulta nova) {
        if (inicio == null) {
            inicio = nova;
            return true;
        }

        Consulta atual = inicio;
        Consulta anterior = null;

        while (atual != null && atual.nome.compareToIgnoreCase(nova.nome) < 0) {
            anterior = atual;// enquanto o atual for menor que o novo os ponteiros vao andando ate achar o lugar certo
            atual = atual.prox;
        }

        if (atual != null && atual.nome.equalsIgnoreCase(nova.nome)) {
            return false; // se o nome atual for igual ao novo n entra pq n permite repetido 
        }

        if (anterior == null) {// inserir no inicoo da lisa 
            nova.prox = inicio; 
            inicio.ant = nova;
            inicio = nova;
        } else {
            anterior.prox = nova; // inserir no meio ou final 
            nova.ant = anterior;
            nova.prox = atual;
            if (atual != null) {
                atual.ant = nova;
            }
        }

        return true;
    }

    public void imprimirLista() {
        Consulta atual = inicio;
        while (atual != null) {
            System.out.println("Nome: " + atual.nome);
            System.out.println("Última Consulta: " + atual.ultConsulta);

            System.out.println("----------------------");
            atual = atual.prox;
        }
    } 
    // busca consulta
    public Consulta buscarConsulta( String ultConsulta){
        Consulta atual = inicio;

        while (atual != null){// enqunto a lista nao ta vazia 
            if (atual.ultConsulta.equalsIgnoreCase(ultConsulta) ){//compara se o nome do paciente na lista é igual ao q o usuario ta procurando 
                return atual;
            }
            atual = atual.prox; // o ponteiro vai andando e entra no while novamente  
        }
        return null;//se percorrer tudo e n achar ai null 

    } 
     //EXCLUIR CONSULTA 
    public boolean excluirConsulta(String ultConsulta){

        Consulta atual = inicio;
        Consulta anterior = null;

        while (atual != null && !atual.ultConsulta.equalsIgnoreCase(ultConsulta)){
            anterior = atual; 
            atual = atual.prox; 
        } 
            if(atual==null){
            return false; 
         } 
            if(anterior == null){
                inicio = atual.prox; 
                if(inicio!=null){
                    inicio.ant=null; 
                }
        } else {  
                    anterior.prox = atual.prox;
                    if (atual.prox != null) {
                        atual.prox.ant = anterior;  // CONFUSO 
                    }
                }
                return true; 
        }

}


public class Main {
    private static final int CODIGO = 000; // Defina o código de acesso para funcionários

    public static void main(String[] args) {
        // Inicializando
        ListaPacientes listaPacientes = new ListaPacientes();
        ListaMedicos listaMedicos = new ListaMedicos();
        ListaConsultas listaConsultas = new ListaConsultas();
        Scanner scanner = new Scanner(System.in);
        String opcao;
        int codigoFuncionario;

        // Loop principal
        while (true) {
            System.out.print("Você é um paciente ou um funcionário? (1/2) ou 'sair' para encerrar: ");
            String tipo = scanner.nextLine();

            if (tipo.equalsIgnoreCase("sair")) {
                break;
            } else if (tipo.equalsIgnoreCase("1")) {
                // Opções para pacientes
                do {
                    System.out.print(" 1 - Inserir paciente\n 2 - Marcar consulta\n 0 - Voltar ao menu principal: ");
                    opcao = scanner.nextLine();
                    if (opcao.equals("1")) {
                        Paciente novo = listaPacientes.criarPaciente();
                        if (listaPacientes.inserirPacienteOrdenado(novo)) {
                            System.out.println("Paciente inserido com sucesso!");
                        } else {
                            System.out.println("Erro ao inserir paciente");
                        }
                    } else if (opcao.equals("2")) {
                        Consulta nova = listaConsultas.criarConsulta(listaMedicos);
                        if (listaConsultas.inserirConsultaOrdenada(nova)) {
                            System.out.println("Consulta inserida com sucesso!");
                        } else {
                            System.out.println("Erro ao inserir consulta");
                        }
                    }
                } while (!opcao.equals("0"));
            } else if (tipo.equalsIgnoreCase("2")) {
                // Verificar código do funcionário
                System.out.print("Digite o código do funcionário: ");
                codigoFuncionario = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado

                if (codigoFuncionario == CODIGO) {
                    // Opções para funcionários
                    do {
                        System.out.print("Escolha uma opção:\n 1 - Inserir paciente\n 2 - Inserir médico\n 3 - Inserir consulta\n 4 - Exibir médico que atendeu mais pacientes\n 5-exibir lista de pacientes\n 6-exibir lista de medicos\n 0 - Voltar ao menu principal: ");
                        opcao = scanner.nextLine();
                        if (opcao.equals("1")) {
                            Paciente novo = listaPacientes.criarPaciente();
                            if (listaPacientes.inserirPacienteOrdenado(novo)) {
                                System.out.println("Paciente inserido com sucesso!");
                            } else {
                                System.out.println("Erro ao inserir paciente. Paciente já existe.");
                            }
                        } else if (opcao.equals("2")) {
                            Medico novo = listaMedicos.criarMedico();
                            if (listaMedicos.inserirMedicoOrdenado(novo)) {
                                System.out.println("Médico inserido com sucesso!");
                            } else {
                                System.out.println("Erro ao inserir médico. Médico já existe.");
                            }
                        } else if (opcao.equals("3")) {
                            Consulta nova = listaConsultas.criarConsulta(listaMedicos);
                            if (listaConsultas.inserirConsultaOrdenada(nova)) {
                                System.out.println("Consulta inserida com sucesso!");
                            } else {
                                System.out.println("Erro ao inserir consulta. Consulta já existe.");
                            }
                        } else if (opcao.equals("4")) {
                            Medico medicoTop = listaMedicos.medicoMaisAtendeu();
                            if (medicoTop != null) {
                                System.out.println("\nMédico que atendeu mais pacientes:");
                                System.out.println("Nome: " + medicoTop.nome);
                                System.out.println("CRM: " + medicoTop.crm);
                                System.out.println("Especialidade: " + medicoTop.especialidade);
                                System.out.println("Número de Pacientes Atendidos: " + medicoTop.Patendidos);
                            } else {
                                System.out.println("Nenhum médico encontrado.");
                            }
                        } else if (opcao.equals("5")){
                            listaPacientes.imprimirLista();
                        } else if(opcao.equals("6")){
                            listaMedicos.imprimirLista();
                        }
                    } while (!opcao.equals("0"));
                } else {
                    System.out.println("Código de funcionário incorreto.");
                }
            } else {
                System.out.println("Tipo inválido. Digite 'p' para paciente ou 'f' para funcionário.");
            }
        }

        scanner.close();
    }
}

