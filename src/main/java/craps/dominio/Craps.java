package craps.dominio;

public class Craps {
	private int soma, ponto, vencedor;
	private boolean primeiraRodada = true;
	private Dado dado = new Dado();

	public boolean isFimDeJogo() {
		return vencedor == 1 || vencedor == 2;
	}

	public int getSoma() {
		return soma;
	}

	public int getVencedor() {
		return vencedor;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
	}

	public int rolarDados() {
		soma = dado.rolar() + dado.rolar();
		if (primeiraRodada) {
			if (soma == 7 || soma == 11) {
				vencedor = 1;
			} else if (soma == 2 || soma == 3 || soma == 12) {
				vencedor = 2;
			} else {
				ponto = soma;
				primeiraRodada = false;
			}
		} else if (soma == ponto) {
			vencedor = 1;
		} else if (soma == 7) {
			vencedor = 2;
		}
		return soma;
	}
}
