import java.util.ArrayList;
import java.util.List;

public abstract class ContaBancaria implements Conta {

    protected String nomeTitular;
    protected int idadeTitular;
    protected int cpfTitular;
    protected double saldo;
    protected double limiteSaqueDiario = 10000;
    protected List<String> extrato = new ArrayList<>();

    @Override
    public void consultarSaldo() {
        System.out.println("Saldo Atual: R$" + saldo);
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de R$" + valor + " foi realizado com sucesso.");
        extrato.add("Depósito: R$" + valor);
    }

    @Override
    public void mostrarInformacoes() {
        System.out.println("Nome do Titular: " + nomeTitular);
        System.out.println("Idade do Titular: " + idadeTitular);
        System.out.println("Cpf do Titular: " + cpfTitular);
        verTipoConta();
        consultarSaldo();
    }

    @Override
    public void mostrarExtrato() {
        System.out.println("----------------- Extrato da conta de " + nomeTitular + "-----------------");
        if(extrato.isEmpty()){
            System.out.println("Nenhuma operação realizada.");
        }
        else
            for(String operações: extrato){
                System.out.println(operações);
            }
    }
}
