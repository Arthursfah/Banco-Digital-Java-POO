
public abstract class Conta implements InterfaceConta {

    private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;
	protected abstract String getTipoConta();

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
    protected String tipoConta;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
        System.out.println("Conta criada com sucesso!");
        System.out.printf("O número da sua conta é: %d\n",this.getNumero());
	}

	@Override
	public void sacar(double valor) {
		if((this.getSaldo()==0)||(this.getSaldo()<valor)){
			System.out.println("Saldo insuficiente para saque.");
		} else{
			saldo -= valor;
		}
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
	}


	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

    public String getCliente() {
        return cliente.getNome();
    }

    

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f \n", this.saldo));
	}

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("Cliente: "+this.cliente.getNome()+ " | %s: %d",this.getTipoConta(),numero);
    }

	
    
}
