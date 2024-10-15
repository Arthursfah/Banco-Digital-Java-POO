public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
		super(cliente);
        tipoConta = "Conta Poupança";
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
