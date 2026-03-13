import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    Conta buscarConta(int cpf) {
        for (Conta c : contas) {
            if (c instanceof ContaBancaria cB && cB.cpfTitular == cpf) return c;
        }
        return null;
    }

    void mostrarTodasContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        for (Conta c : contas) {
            System.out.println("-------------------------");
            c.mostrarInformacoes();
        }
    }

    public void aplicarJurosPoupanca() {
        for (Conta c: contas){
            if (c instanceof ContaPoupanca cP){
                cP.aplicarJuros();
            }
        }
    }
}
