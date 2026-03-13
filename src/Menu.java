import java.util.Scanner;

public class Menu {

    private Banco banco;
    private Scanner sc;

    public Menu(Banco banco) {
        this.banco = banco;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {

        int opcao;

        do {

            mostrarMenu();

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    criarConta("corrente");
                    break;

                case 2:
                    criarConta("poupanca");
                    break;

                case 3:
                    depositar();
                    break;

                case 4:
                    sacar();
                    break;

                case 5:
                    consultarSaldo();
                    break;

                case 6:
                    banco.mostrarTodasContas();
                    break;

                case 7:
                    transferir();
                    break;

                case 8:
                    mostrarExtrato();
                    break;

                case 9:
                    aplicarJuros();
                    break;

                case 0:
                    System.out.println("\nEncerrando sistema...");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);
    }

    private void mostrarMenu() {

        System.out.println();
        System.out.println("================================================");
        System.out.println("                SISTEMA BANCÁRIO");
        System.out.println("================================================");

        System.out.println("1 - Criar Conta Corrente");
        System.out.println("2 - Criar Conta Poupança");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Consultar Saldo");
        System.out.println("6 - Mostrar todas as contas");
        System.out.println("7 - Transferir");
        System.out.println("8 - Extrato");
        System.out.println("9 - Aplicar Juros (Poupança)");
        System.out.println("0 - Sair");

        System.out.println("------------------------------------------------");
        System.out.print("Escolha uma opção: ");
    }

    private void criarConta(String tipo) {

        System.out.println();
        System.out.println("----------- CRIAÇÃO DE CONTA -----------");

        System.out.print("Nome do titular: ");
        String nome = sc.nextLine();

        System.out.print("Idade: ");
        int idade = sc.nextInt();

        System.out.print("CPF: ");
        int cpf = sc.nextInt();
        sc.nextLine();

        ContaBancaria conta;

        if (tipo.equals("corrente")) {
            conta = new ContaCorrente();
        } else {
            conta = new ContaPoupanca();
        }

        conta.nomeTitular = nome;
        conta.idadeTitular = idade;
        conta.cpfTitular = cpf;

        banco.adicionarConta(conta);

        System.out.println();
        System.out.println("Conta criada com sucesso!");
        System.out.println("----------------------------------------");
        System.out.println();
    }

    private void depositar() {

        System.out.println();
        System.out.println("-------------- DEPÓSITO ----------------");

        ContaBancaria conta = obterContaPorCPF();

        if (conta != null) {

            System.out.print("Valor a depositar: ");
            double valor = sc.nextDouble();
            sc.nextLine();

            conta.depositar(valor);

            System.out.println();
            System.out.println("Depósito realizado com sucesso!");
            conta.consultarSaldo();
        }

        System.out.println("----------------------------------------");
        System.out.println();
    }

    private void sacar() {

        System.out.println();
        System.out.println("--------------- SAQUE ------------------");

        ContaBancaria conta = obterContaPorCPF();

        if (conta != null) {

            System.out.print("Valor a sacar: ");
            double valor = sc.nextDouble();
            sc.nextLine();

            conta.sacar(valor);

            System.out.println();
            System.out.println("Operação finalizada.");
            conta.consultarSaldo();
        }

        System.out.println("----------------------------------------");
        System.out.println();
    }

    private void consultarSaldo() {

        System.out.println();
        System.out.println("----------- CONSULTAR SALDO ------------");

        ContaBancaria conta = obterContaPorCPF();

        if (conta != null) {
            conta.consultarSaldo();
        }

        System.out.println("----------------------------------------");
        System.out.println();
    }

    private void transferir() {

        System.out.println();
        System.out.println("------------- TRANSFERÊNCIA ------------");

        System.out.print("CPF da conta de origem: ");
        int cpfOrigem = sc.nextInt();

        ContaBancaria origem = obterContaPorCPF(cpfOrigem);

        if (origem == null) return;

        System.out.print("CPF da conta de destino: ");
        int cpfDestino = sc.nextInt();

        ContaBancaria destino = obterContaPorCPF(cpfDestino);

        if (destino == null) return;

        System.out.print("Valor a transferir: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        origem.transferir(valor, destino);

        System.out.println();
        System.out.println("Transferência realizada com sucesso!");

        System.out.println();
        System.out.println("Saldo da conta de origem:");
        origem.consultarSaldo();

        System.out.println("----------------------------------------");
        System.out.println();
    }

    private void mostrarExtrato() {

        System.out.println();
        System.out.println("--------------- EXTRATO ----------------");

        ContaBancaria conta = obterContaPorCPF();

        if (conta != null) {
            conta.mostrarExtrato();
        }

        System.out.println("----------------------------------------");
        System.out.println();
    }

    private void aplicarJuros() {

        System.out.println();
        System.out.println("----------- APLICAR JUROS --------------");

        ContaBancaria conta = obterContaPorCPF();

        if (conta instanceof ContaPoupanca) {

            ((ContaPoupanca) conta).aplicarJuros();

            System.out.println();
            System.out.println("Juros aplicados com sucesso!");
            conta.consultarSaldo();
        } else {
            System.out.println("Esta operação é apenas para conta poupança.");
        }

        System.out.println("----------------------------------------");
        System.out.println();
    }

    private ContaBancaria obterContaPorCPF() {

        System.out.print("Informe o CPF da conta: ");
        int cpf = sc.nextInt();
        sc.nextLine();

        return obterContaPorCPF(cpf);
    }

    private ContaBancaria obterContaPorCPF(int cpf) {

        Conta c = banco.buscarConta(cpf);

        if (c == null) {
            System.out.println("\nConta não encontrada!");
            return null;
        }

        return (ContaBancaria) c;
    }
}
