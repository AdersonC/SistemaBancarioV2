public class ContaCorrente extends ContaBancaria {
    @Override
    public void sacar(double valor) {
        if(valor > 10000){
            System.out.println("O valor excede o limite diário de R$" + limiteSaqueDiario);
            return;
        }
        if(valor <= saldo){
            saldo -= valor;
            System.out.println("O saque de R$" + valor + "foi realizado.");
            extrato.add("Saque: R$" + valor);
        } else
            System.out.println("Saldo Insuficiente.");
    }

    @Override
    public void transferir(double valor, Conta destino) {
        double limiteTransferencia = 20000;
        if (valor > limiteTransferencia) {
            System.out.println("O valor excede o limite de transferência de R$" + limiteTransferencia);
        }
        if (saldo >= valor) {
            saldo -= valor;

            if (destino instanceof ContaBancaria destinoConta) {
                destinoConta.saldo += valor;
                destinoConta.extrato.add("Transferência recebida: R$" + valor + " de " + nomeTitular);
            }

            extrato.add("Transferência enviada: R$" + valor + " para " + ((ContaBancaria) destino).nomeTitular);
            System.out.println("Transferência de R$" + valor + " para " + ((ContaBancaria)destino).nomeTitular + " foi realizada.");
        }
        else
            System.out.println("Saldo Insuficiente para transferência.");
    }

    @Override
    public void verTipoConta() {
        System.out.println("Tipo de Conta: Corrente.");
    }
}
