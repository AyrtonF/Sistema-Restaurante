import java.util.Scanner;

public class Restaurante {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opc;

        do {
           System.out.println("--------------------------------------------------");
            // limparTela();
            exibirMenu();
            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    adicionarPedido(sc);
                    break;
                case 2:
                    atenderPedido();
                    break;
                case 3:
                    listarPedidos();
                    break;
                case 4:
                    pesquisarPedido(sc);
                    break;
                case 5:
                    encerrar();
                    break;
                default:
                    System.out.println("Opção inválida");

            }

            for (int i = 0; i < 2; i++) {
                System.out.println("");
            }
        } while (opc != 5 || quantidadePedidos > 0);
        sc.close();
    }

    // public static void limparTela() {
    // System.out.print("\033[H\033[2J");
    // System.out.flush();
    // }

    private static final int maxPedidos = 10;
    private static Pedido[] pedidos = new Pedido[maxPedidos];
    private static int quantidadePedidos = 0;
    private static double faturamentoTotal = 0.0;
    private static int qtdPedidosAtendidos = 0;

    private static void exibirMenu() {
        System.out.println("###### LANCHONETE ######");
        System.out.println("# 1 - INCLUIR PEDIDO    #");
        System.out.println("# 2 - ATENDER PEDIDO    #");
        System.out.println("# 3 - LISTAR PEDIDOS    #");
        System.out.println("# 4 - PESQUISAR PEDIDO  #");
        System.out.println("# 5 - ENCERRAR          #");
        System.out.println("########################");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarPedido(Scanner sc) {
        if (quantidadePedidos == maxPedidos) {
            System.out.println("Fila cheia - Não é possível adicionar mais pedidos");
            return;
        }
        System.out.print("Numero do pedido: ");
        int numPedido = sc.nextInt();
        sc.nextLine();

        System.out.print("Tipo de atendimento [1 = Em loja - 2 = Delivery]: ");
        int tipoAtendimento = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome do Cliente: ");
        String nomeCliente = sc.nextLine();

        System.out.print("Valor Total: ");
        double valorTotal = sc.nextDouble();

        Pedido novoPedido = new Pedido(numPedido, tipoAtendimento, nomeCliente, valorTotal);
        pedidos[quantidadePedidos] = novoPedido;
        quantidadePedidos++;

        System.out.println("Pedido adicionado à fila");
    }

    private static void atenderPedido() {
        if (quantidadePedidos == 0) {
            System.out.println("Lista vazia - Não existem pedidos para serem atendidos");
            return;
        }

        Pedido pedidoAtendido = pedidos[0];

        for (int i = 0; i < quantidadePedidos - 1; i++) {
            pedidos[i] = pedidos[i + 1];
        }

        qtdPedidosAtendidos = qtdPedidosAtendidos + 1;

        quantidadePedidos--;

        faturamentoTotal += pedidoAtendido.getValorTotal();

        System.out.println("Pedido atendido: " + pedidoAtendido);
    }

    private static void pesquisarPedido(Scanner sc) {
        if (quantidadePedidos == 0) {
            System.out.println("Lista Vazia - Não existem pedidos para serem pesquisados");
            return;
        }

        System.out.println("Digite o número do pedido: ");
        int numPedido = sc.nextInt();

        for (int i = 0; i < quantidadePedidos; i++) {
            if (pedidos[i].getNumPedido() == numPedido) {
                System.out.println("Pedido encontrado: " + pedidos[i]);
                return;
            }
        }

        System.out.println("Pedido não encontrado");
    }

    private static void listarPedidos() {
        if (quantidadePedidos == 0) {
            System.out.println("Não há nenhum pedido na lista!");
            return;
        }

        System.out.println("Lista de pedidos: ");
        for (int i = 0; i < quantidadePedidos; i++) {
            System.out.println(pedidos[i]);
        }
    }

    private static void encerrar() {
        if (quantidadePedidos > 0) {
            System.out.println("Não é possível encerrar - Ainda existem pedidos para serem atendidos");
            return;
        }

        System.out.println("Faturamento total: " + faturamentoTotal);
        System.out.println("Quantidade de pedidos atendidos: " + qtdPedidosAtendidos);
        System.out.println("Programa Encerrado ;)");
    }

    public static class Pedido {
        private int numPedido;
        private int tipoAtendimento;
        private String nomeCliente;
        private double valorTotal;

        public Pedido(int numPedido, int tipoAtendimento, String nomeCliente, double valorTotal) {
            this.numPedido = numPedido;
            this.tipoAtendimento = tipoAtendimento;
            this.nomeCliente = nomeCliente;
            this.valorTotal = valorTotal;
        }

        public int getNumPedido() {
            return numPedido;
        }

        public int getTipoAtendimento() {
            if (tipoAtendimento == 1) {
                System.out.print("Pedido em loja");
            } else if (tipoAtendimento == 2) {
                System.out.print("Pedido por delivery");
            } else {
                System.out.print("Tipo de atendimento inválido");
            }
            return tipoAtendimento;
        }

        public String getNomeCliente() {
            return nomeCliente;
        }

        public double getValorTotal() {
            return valorTotal;
        }

        @Override
        public String toString() {
            return "Pedido{" +
                    "Numero do pedido: " + numPedido +
                    ", Tipo de atendimento: " + tipoAtendimento +
                    ", Nome do cliente: " + nomeCliente +
                    ", Valor Total: " + valorTotal +
                    "}";
        }
    }

}
