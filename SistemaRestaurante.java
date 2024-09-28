import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaRestaurante {
    private Mesa cabeca;
    private String[] cardapio = {"Hamburguer", "Pizza", "Salada", "Suco", "Refrigerante", "Rosquinha"}; // Adicionando rosquinha
    private Map<String, Integer> contagemItensPedidos = new HashMap<>(); // Contador de pedidos por item

    public SistemaRestaurante() {
        // Inicializando o contador de pedidos para cada item do cardápio
        for (String item : cardapio) {
            contagemItensPedidos.put(item, 0);
        }
    }

    class ItemPedido {
        String descricao;
        int quantidade;
        double total;

        ItemPedido(String descricao, int quantidade, double total) {
            this.descricao = descricao;
            this.quantidade = quantidade;
            this.total = total;
        }
    }

    class Pedido {
        ItemPedido item;
        Pedido proximo;

        Pedido(ItemPedido item) {
            this.item = item;
            this.proximo = null;
        }
    }

    class Mesa {
        int numero;
        String cliente;
        String status; // "livre" ou "ocupada"
        Pedido pedidos;
        Mesa proximo;

        Mesa(int numero, String cliente) {
            this.numero = numero;
            this.cliente = cliente;
            this.status = "ocupada"; // ao adicionar, a mesa é ocupada
            this.pedidos = null;
            this.proximo = null; // inicializa o próximo como nulo
        }
    }

    public void adicionarMesa(int numero, String cliente) {
        Mesa novaMesa = new Mesa(numero, cliente);
        if (cabeca == null) {
            cabeca = novaMesa;
            cabeca.proximo = cabeca;
        } else {
            Mesa atual = cabeca;
            while (atual.proximo != cabeca) {
                atual = atual.proximo;
            }
            atual.proximo = novaMesa;
            novaMesa.proximo = cabeca;
        }
        System.out.println("Mesa " + numero + " adicionada para o cliente " + cliente + ".");
    }

    // Função para exibir o cardápio
    public void exibirCardapio() {
        System.out.println("Cardápio:");
        for (String item : cardapio) {
            System.out.println("- " + item);
        }
    }

    // Verifica se o item está no cardápio
    public boolean itemValido(String descricao) {
        for (String item : cardapio) {
            if (item.equalsIgnoreCase(descricao)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarPedido(int numeroMesa, String descricao, int quantidade, double total) {
        if (!itemValido(descricao)) {
            System.out.println("Item fora do cardápio: " + descricao);
            return;
        }

        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa != null && mesa.status.equals("ocupada")) {
            ItemPedido novoItem = new ItemPedido(descricao, quantidade, total);
            Pedido novoPedido = new Pedido(novoItem);
            novoPedido.proximo = mesa.pedidos;
            mesa.pedidos = novoPedido;

            // Atualiza a contagem do item no cardápio
            contagemItensPedidos.put(descricao, contagemItensPedidos.get(descricao) + quantidade);

            // Verifica a quantidade de rosquinhas pedidas
            if (descricao.equalsIgnoreCase("rosquinha") && quantidade >= 2) {
                System.out.println("Um pedaço de rosquinha alegra muito. Dois pedaços causam tristeza.");
            }

            System.out.println("Pedido adicionado na mesa " + numeroMesa + ".");
        } else {
            System.out.println("Mesa não encontrada ou está livre.");
        }
    }

    // Função para exibir os itens mais pedidos
    public void exibirItensMaisPedidos() {
        System.out.println("Itens mais pedidos:");
        contagemItensPedidos.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Ordena em ordem decrescente de pedidos
                .forEach(entry -> {
                    if (entry.getValue() > 0) { // Mostra apenas itens com pedidos
                        System.out.println(entry.getKey() + ": " + entry.getValue() + " pedidos");
                    }
                });
    }

    public void fecharConta(int numeroMesa) {
        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa != null && mesa.status.equals("ocupada")) {
            double totalConta = 0;
            Pedido atual = mesa.pedidos;
            while (atual != null) {
                totalConta += atual.item.total;
                atual = atual.proximo;
                if (atual == mesa.pedidos) break; // Para evitar loop infinito
            }
            System.out.println("Total a pagar na mesa " + numeroMesa + ": R$ " + totalConta);
            mesa.status = "livre";
            mesa.pedidos = null;
        } else {
            System.out.println("Mesa não encontrada ou já está livre.");
        }
    }

    public Mesa buscarMesa(int numero) {
        if (cabeca == null) return null;
        Mesa atual = cabeca;
        do {
            if (atual.numero == numero) {
                return atual;
            }
            atual = atual.proximo;
        } while (atual != cabeca);
        return null;
    }

    public void imprimirMesas() {
        if (cabeca == null) {
            System.out.println("Nenhuma mesa cadastrada.");
            return;
        }
        Mesa atual = cabeca;
        do {
            System.out.println("Mesa: " + atual.numero + ", Cliente: " + atual.cliente + ", Status: " + atual.status);
            if (atual.pedidos != null) {
                System.out.println("Pedidos:");
                Pedido pedidoAtual = atual.pedidos;
                do {
                    System.out.println(" - " + pedidoAtual.item.descricao + ": " + pedidoAtual.item.quantidade + " (R$ " + pedidoAtual.item.total + ")");
                    pedidoAtual = pedidoAtual.proximo;
                } while (pedidoAtual != null && pedidoAtual != atual.pedidos);
            }
            atual = atual.proximo;
        } while (atual != cabeca);
    }

    public void executar(Scanner scanner) {
        String opcao;

        do {
            System.out.println("Sistema de Restaurante:");
            System.out.println("1. Adicionar Mesa");
            System.out.println("2. Adicionar Pedido");
            System.out.println("3. Fechar Conta");
            System.out.println("4. Imprimir Mesas");
            System.out.println("5. Exibir Cardápio");
            System.out.println("6. Exibir Itens Mais Pedidos");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Número da mesa: ");
                    int numeroMesa = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome do cliente: ");
                    String cliente = scanner.nextLine();
                    adicionarMesa(numeroMesa, cliente);
                    break;
                case "2":
                    System.out.print("Número da mesa: ");
                    numeroMesa = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Descrição do pedido: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Total a pagar: ");
                    double total = scanner.nextDouble();
                    scanner.nextLine();
                    adicionarPedido(numeroMesa, descricao, quantidade, total);
                    break;
                case "3":
                    System.out.print("Número da mesa: ");
                    numeroMesa = scanner.nextInt();
                    scanner.nextLine();
                    fecharConta(numeroMesa);
                    break;
                case "4":
                    imprimirMesas();
                    break;
                case "5":
                    exibirCardapio();
                    break;
                case "6":
                    exibirItensMaisPedidos();
                    break;
                case "0":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("0"));
    }

    public static void main(String[] args) {
        SistemaRestaurante sistema = new SistemaRestaurante();
        Scanner scanner = new Scanner(System.in);
        sistema.executar(scanner);
        scanner.close();
    }
}
