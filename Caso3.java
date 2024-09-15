class ItemPedido {
    String descricao;
    int quantidade;
    double precoUnitario;
    ItemPedido prox;

    public ItemPedido(String descricao, int quantidade, double precoUnitario) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.prox = null;
    }
}
class Pedido {
    int id;
    String cliente;
    ItemPedido inicioItens;
    double total;
    Pedido prox;
    public Pedido(int id, String cliente) {
        this.id = id;
        this.cliente = cliente;
        this.inicioItens = null;
        this.total = 0.0;
        this.prox = null;
    }
    // Adiciona um item ao pedido e atualiza o total
    public void adicionarItem(String descricao, int quantidade, double precoUnitario) {
        ItemPedido novoItem = new ItemPedido(descricao, quantidade, precoUnitario);
        if (inicioItens == null) {
            inicioItens = novoItem;
        } else {
            ItemPedido atual = inicioItens;
            while (atual.prox != null) {
                atual = atual.prox;
            }
            atual.prox = novoItem;
        }
        total += quantidade * precoUnitario;
    }
    // Atualiza o preço total
    public void atualizarTotal() {
        total = 0.0;
        ItemPedido atual = inicioItens;
        while (atual != null) {
            total += atual.quantidade * atual.precoUnitario;
            atual = atual.prox;
        }
    }
}
// Classe para mesas
class Mesa {
    int numero;
    String clienteAssociado;
    boolean ocupada;
    Pedido inicioPedidos;
    Mesa prox;
    public Mesa(int numero, String clienteAssociado, boolean ocupada) {
        this.numero = numero;
        this.clienteAssociado = clienteAssociado;
        this.ocupada = ocupada;
        this.inicioPedidos = null;
        this.prox = null;
    }
    // Adiciona um pedido à mesa
    public void adicionarPedido(Pedido novoPedido) {
        if (inicioPedidos == null) {
            inicioPedidos = novoPedido;
        } else {
            Pedido atual = inicioPedidos;
            while (atual.prox != null) {
                atual = atual.prox;
            }
            atual.prox = novoPedido;
        }
    }
    // Fecha todos os pedidos da mesa e limpa o cliente associado
    public void fecharConta() {
        Pedido atual = inicioPedidos;
        while (atual != null) {
            System.out.println("Pedido ID: " + atual.id);
            System.out.println("Cliente: " + atual.cliente);
            System.out.println("Total: R$ " + atual.total);
            atual = atual.prox;
        }
        clienteAssociado = null;
        ocupada = false;
        inicioPedidos = null;
    }
}
// Classe para gerenciar as mesas
class ListaMesas {
    private Mesa inicio;
    public ListaMesas() {
        this.inicio = null;
    }
    // Adiciona uma nova mesa
    public void adicionarMesa(int numero, String clienteAssociado, boolean ocupada) {
        Mesa novaMesa = new Mesa(numero, clienteAssociado, ocupada);
        if (inicio == null) {
            inicio = novaMesa;
        } else {
            Mesa atual = inicio;
            while (atual.prox != null) {
                atual = atual.prox;
            }
            atual.prox = novaMesa;
        }
    }
    // Busca uma mesa pelo número
    public Mesa buscarMesa(int numero) {
        Mesa atual = inicio;
        while (atual != null) {
            if (atual.numero == numero) {
                return atual;
            }
            atual = atual.prox;
        }
        return null;
    }
    // Imprime a lista de mesas
    public void imprimirMesas() {
        Mesa atual = inicio;
        while (atual != null) {
            System.out.println("Mesa: " + atual.numero);
            System.out.println("Cliente Associado: " + (atual.clienteAssociado != null ? atual.clienteAssociado : "Nenhum"));
            System.out.println("Status: " + (atual.ocupada ? "Ocupada" : "Livre"));
            System.out.println("----------------------");
            atual = atual.prox;
        }
    }
}
