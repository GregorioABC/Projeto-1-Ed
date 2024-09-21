import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcao;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Sistema de Eventos");
            System.out.println("2. Sistema de Restaurante");
            System.out.println("3. Sistema de Clínica");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    SistemaEventos.executar(scanner); 
                    break;
                case "2":
                SistemaRestaurante sistemaRestaurante = new SistemaRestaurante(); // Crie uma instância
                sistemaRestaurante.executar(scanner);
                case "3":
                    SistemaClinica.executar(scanner);
                    break;
                case "0":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("0"));

        scanner.close();
    }
}
