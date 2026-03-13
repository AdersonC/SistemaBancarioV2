public interface Conta {
      void consultarSaldo();
      void depositar(double valor);
      void verTipoConta();
      void mostrarInformacoes();
      void mostrarExtrato();
      void sacar(double valor);
      void transferir(double valor, Conta destino);
}
