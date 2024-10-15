import java.util.ArrayList;
import java.util.List;

public class Banco {

	private List<Conta> contas;


    public Banco() {
        contas = new ArrayList<>();
    }

    public void addConta(Conta t){
        contas.add(t);
    }

    public void removeConta(Conta t){
        contas.remove(t);
    }

    public List<Conta> getContas() {
        return contas;
    }


    public void transferir(Conta rico, double valor, Conta destino){
        if(rico.getSaldo()>=valor){
            rico.sacar(valor);
            destino.depositar(valor);
            System.out.println("Transferência realizada!");
            rico.imprimirExtrato();
            destino.imprimirExtrato();
        }else{
            System.out.println("Saldo insuficiente para transferencia");
        }        
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        List<Conta> corrente = new ArrayList<>();
        List<Conta> poupanca = new ArrayList<>();
        String p, c;
        
        for(Conta t: contas){
            if(t.tipoConta.equals("Conta Poupança")){
                poupanca.add(t);
            } else {
                corrente.add(t);
            }
        }

        if(!poupanca.isEmpty()){
            p = "Contas Poupança:\n";
        } else {
            p = "Não há contas poupança abertas.\n";
        }

        if(!corrente.isEmpty()){
            c = "Contas Corrente:\n";
        } else {
            c = "Não há contas corrente aberta.\n";
        }
        
        return String.format( p + poupanca +"\n" +c + corrente); 
    }
}
