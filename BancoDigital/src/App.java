import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean mainLoop = true;
        Banco banco = new Banco();
        Cliente atual;
        String res;
        List<Cliente> clientes = new ArrayList<>();
        

        do{
            System.out.println("Deseja acessar o banco?");
            res = scan.next();
            if(res.equals("sim")){
                atual = clientes(scan, banco);
                clientes.add(atual);
            } else{
                System.out.println("Fim de Atendimento");
                mainLoop = false;
            }
            
        }while(mainLoop);
        
    }

    public static Conta criarConta(Cliente cli, Scanner scan){
        System.out.println("Quer abrir uma conta corrente[1] ou uma conta poupanca[2] ?");
        int tipoConta = scan.nextInt();
        Conta conta;
        if (tipoConta==1){
            conta = new ContaCorrente(cli);
        } else if (tipoConta==2) {
            conta = new ContaPoupanca(cli);
        } else {
            throw new RuntimeException("Opção inválida.");
        }
        return conta;
    }

    public static List<Conta> cancelarConta(Conta contaCancelada, Banco banco){
        banco.removeConta(contaCancelada);
        System.out.println("Conta cancelada!");
        return banco.getContas();
    }

    public static void movimentarConta(Cliente cli, Scanner scan, Banco banco){
        boolean loop= true;
        Conta conta;
        List<String> contaAberta = new ArrayList<>(); 
        List<Integer> numContaAberta = new ArrayList<>();
        int i, numContaMovi;

        i = 0;
        if(banco.getContas().isEmpty()){
            System.out.println("Não há contas abertas no banco.");
        } else{
            for(Conta t: banco.getContas()){
                if(t.getCliente().equals(cli.getNome())){
                    i++;
                    contaAberta.add(t.getTipoConta());
                    numContaAberta.add(t.getNumero());
                }
            }
        }
        

        if(i==0){
            throw new RuntimeException("Este cliente não tem conta aberta."); 
        }  else if(i>1){
            for(Integer a : numContaAberta){
               System.out.println(contaAberta.get(a-1)+": "+ a);
            }
            System.out.println("Qual conta você quer movimentar?");
            numContaMovi = scan.nextInt();
            conta = numSelConta(numContaMovi, banco,true);
        } else {
            conta =  numSelConta(numContaAberta.get(0), banco,true);
        }
        
        do{
            conta.imprimirExtrato();
            System.out.println("Digite:\n1 para depósito\n2 para saque\n3 para tranferências \n4 para mostrar saldo \n5 para finalizar movimentação.");
            int val = scan.nextInt();
            switch (val) {
                case 1:
                    System.out.println("Quanto você deseja depositar?");
                    double deposito = scan.nextFloat();
                    conta.depositar(deposito);
                    break;
                case 2:
                    System.out.println("Quanto você deseja sacar?");
                    double saque = scan.nextDouble();
                    conta.sacar(saque);
                    break;
                case 3:
                    System.out.println("Qual o valor que você quer transferir?");
                    double valor = scan.nextDouble();
                    System.out.println(banco.getContas());
                    System.out.println("Qual o número da conta destino da transferência?");
                    int numeroConta = scan.nextInt();
                    banco.transferir(conta, valor,numSelConta(numeroConta, banco,true));
                    break;
                case 4:
                    System.out.println(conta.getSaldo());
                    break;
                case 5: 
                    conta.imprimirExtrato();
                    System.out.println("Sessão finalizada!");
                    loop = false;
                    break;
                default:
                    System.out.println("Opção inválida. Sessão será finalizada.");
                    loop = false;
                    break;
            }
        }while(loop);

    }

    public static Conta numSelConta(int numContaMovi, Banco banco, boolean movi){
        Conta conta = null;
        boolean isConta = false;
        for(Conta c : banco.getContas()){
            if(c.getNumero()==numContaMovi){
                conta = c;
                isConta = true;
            }
        }
        if((isConta)&&(movi)){
            System.out.println("A conta a ser movimentada: ");
        } else if(!isConta) {
            System.out.println("Número inválido. Conta não existe.");
        }
        return conta;
    }


    public static Cliente clientes(Scanner scan, Banco banco){
        boolean cliLoop = true;
        String nomeCliente, temConta;
        

        System.out.print("Olá, para iniciar sessão bancária, digite seu nome: ");
        nomeCliente = scan.next();
        Cliente cli = new Cliente(nomeCliente);
        System.out.printf("Olá, %s! \n", cli.getNome());
        System.out.print("Você já possui uma conta no Banco? ");
        temConta = scan.next();
        if (temConta.equals("sim")){
            movimentarConta(cli, scan, banco);
        } else {
            banco.addConta(criarConta(cli, scan));   
        }

        do{
            System.out.println("Digite:\n1 para criar uma nova conta\n2 para movimentar sua conta\n3 para cancelar sua conta\n4 para mostrar as contas abertas\n5 para finalizar");
            int a = scan.nextInt();
            switch (a) {
                case 1:
                    banco.addConta(criarConta(cli, scan));
                    break;
                case 2:
                    movimentarConta(cli, scan, banco);
                    break;
                case 3:
                    System.out.println(banco);
                    System.out.println("Qual o número da conta que será cancelada?");
                    int numCancela = scan.nextInt();
                    cancelarConta(numSelConta(numCancela, banco,false),banco);
                    break;
                case 4:
                    System.out.println(banco);
                    break;
                case 5:
                    System.out.println("Esta sessão será encerrada.");
                    cliLoop = false;
                    break;
                default:
                    System.out.println("Opção inválida. a sessão será encerrada.");
                    cliLoop = false;
                    break;
            }
        }while(cliLoop);
        return cli;
    }
}