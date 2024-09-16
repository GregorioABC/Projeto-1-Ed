import java.util.Scanner;

class Paciente {
    String nome;
    int idade;
    String hMedico;
    int cpf; 
    String ultConsulta;
    Paciente prox;
    Paciente ant;

    public Paciente(String nome, int idade, String hMedico, String ultConsulta) {
        this.nome = nome;
        this.idade = idade;
        this.hMedico = hMedico;
        this.cpf = cpf;
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

        System.out.println("Última consulta do paciente (formato DD/MM/AAAA):");
        String ultConsulta = scanner.nextLine();

        return new Paciente(nome, idade, hMedico, ultConsulta);
    }

    // Método para inserir paciente ordenado alfabeticamente (lista circular)
    public boolean inserirPacienteOrdenado(Paciente novo) {
        if (inicio == null) { // Se a lista estiver vazia
            inicio = novo;
            inicio.prox = inicio;  // O único nó aponta para si mesmo
            inicio.ant = inicio;
            return true;
        }

        Paciente atual = inicio;
        Paciente anterior = null;

        do {
            anterior = atual;
            atual = atual.prox;
       
