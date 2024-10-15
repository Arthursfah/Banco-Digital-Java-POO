public class ContaCorrente extends Conta{

    public ContaCorrente(Cliente cliente) {
		super(cliente);
        tipoConta = "Conta Corrente";
	}

	@Override
	public void imprimirExtrato() {
		System.out.printf("=== Extrato %s ===\n",tipoConta);
		super.imprimirInfosComuns();
	}

	public String getTipoConta(){
		return this.tipoConta;
	}
}
