import java.util.Scanner;

class Pedido {
    String descricao;
    int quantidade;
    double total;
    Pedido prox; // Próximo nó

    public Pedido(String descricao, int quantidade, double total) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.total = total;
        this.prox = null;
    }
}

class Mesa {
    int numeroMesa;
    String cliente;
    boolean ocupada; // true = ocupada, false = livre
    Pedido pedidos; // Lista encadeada de pedidos para a mesa
    Mesa prox; // Próximo nó

    public Mesa(int numeroMesa, String cliente) {
        this.numeroMesa = numeroMesa;
        this.cliente = cliente;
        this.ocupada = true;
        this.pedidos = null;
        this.prox = null;
    }
}

class Restaurante {
    private Mesa inicio;

    public Restaurante() {
        this.inicio = null;
    }

    // Método para adicionar uma nova mesa
    public void adicionarMesa(int numeroMesa, String cliente) {
        Mesa novaMesa = new Mesa(numeroMesa, cliente);
        if (inicio == null) {
            inicio = novaMesa;
            inicio.prox = inicio; // Lista circular: único nó aponta para si mesmo
        } else {
            Mesa atual = inicio;
            while (atual.prox != inicio) {
                atual = atual.prox;
            }
            atual.prox = novaMesa;
            novaMesa.prox = inicio; // Fecha o ciclo circular
        }
        System.out.println("Mesa " + numeroMesa + " adicionada com sucesso.");
    }

    // Método para adicionar um pedido a uma mesa específica
    public void adicionarPedido(int numeroMesa, String descricao, int quantidade, double total) {
        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa != null) {
            Pedido novoPedido = new Pedido(descricao, quantidade, total);
            if (mesa.pedidos == null) {
                mesa.pedidos = novoPedido;
                mesa.pedidos.prox = mesa.pedidos; // Lista circular de pedidos
            } else {
                Pedido atual = mesa.pedidos;
                while (atual.prox != mesa.pedidos) {
                    atual = atual.prox;
                }
                atual.prox = novoPedido;
                novoPedido.prox = mesa.pedidos; // Fecha o ciclo circular
            }
            System.out.println("Pedido adicionado à mesa " + numeroMesa + ".");
        } else {
            System.out.println("Mesa " + numeroMesa + " não encontrada.");
        }
    }

    // Método para buscar uma mesa específica
    public Mesa buscarMesa(int numeroMesa) {
        if (inicio == null) {
            return null;
        }
        Mesa atual = inicio;
        do {
            if (atual.numeroMesa == numeroMesa) {
                return atual;
            }
            atual = atual.prox;
        } while (atual != inicio);
        return null;
    }

    // Método para fechar a conta de uma mesa e liberar a mesa
    public void fecharConta(int numeroMesa) {
        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa != null) {
            System.out.println("Conta da mesa " + numeroMesa + ":");
            double totalConta = 0;
            Pedido atualPedido = mesa.pedidos;
            if (atualPedido != null) {
                do {
                    System.out.println("Pedido: " + atualPedido.descricao + " - Quantidade: " + atualPedido.quantidade + " - Total: R$" + atualPedido.total);
                    totalConta += atualPedido.total;
                    atualPedido = atualPedido.prox;
                } while (atualPedido != mesa.pedidos);
            } else {
                System.out.println("Nenhum pedido registrado.");
            }
            System.out.println("Total a pagar: R$" + totalConta);
            liberarMesa(mesa);
        } else {
            System.out.println("Mesa " + numeroMesa + " não encontrada.");
        }
    }

    // Método para liberar uma mesa
    public void liberarMesa(Mesa mesa) {
        mesa.ocupada = false;
        mesa.pedidos = null; // Limpa os pedidos
        System.out.println("Mesa " + mesa.numeroMesa + " liberada.");
    }

    // Método para imprimir o status das mesas
    public void imprimirMesas() {
        if (inicio == null) {
            System.out.println("Nenhuma mesa cadastrada.");
            return;
        }
        Mesa atual = inicio;
        do {
            System.out.println("Mesa " + atual.numeroMesa + " - Cliente: " + atual.cliente + " - " + (atual.ocupada ? "Ocupada" : "Livre"));
            atual = atual.prox;
        } while (atual != inicio);
    }
}

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        String opcao;

        // Adicionar mesas
        do {
            System.out.print("Deseja adicionar uma mesa? (s/n): ");
            opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("s")) {
                System.out.print("Número da mesa: ");
                int numeroMesa = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                System.out.print("Nome do cliente: ");
                String cliente = scanner.nextLine();
                restaurante.adicionarMesa(numeroMesa, cliente);
            }
        } while (opcao.equalsIgnoreCase("s"));

        // Adicionar pedidos
        do {
            System.out.print("Deseja adicionar um pedido a uma mesa? (s/n): ");
            opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("s")) {
                System.out.print("Número da mesa: ");
                int numeroMesa = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                System.out.print("Descrição do pedido: ");
                String descricao = scanner.nextLine();
                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                System.out.print("Total a pagar: ");
                double total = scanner.nextDouble();
                scanner.nextLine(); // Limpar o buffer do teclado
                restaurante.adicionarPedido(numeroMesa, descricao, quantidade, total);
            }
        } while (opcao.equalsIgnoreCase("s"));

        // Fechar contas
        do {
            System.out.print("Deseja fechar a conta de uma mesa? (s/n): ");
            opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("s")) {
                System.out.print("Número da mesa: ");
                int numeroMesa = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                restaurante.fecharConta(numeroMesa);
            }
        } while (opcao.equalsIgnoreCase("s"));

        // Imprimir mesas
        System.out.println("\nStatus das Mesas:");
        restaurante.imprimirMesas();

        scanner.close();
    }
}
