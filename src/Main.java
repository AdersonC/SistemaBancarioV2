public class Main {
    static void main(String[] args) {
        Banco banco = new Banco();
        Menu menu = new Menu(banco);
        menu.iniciar();
    }
}