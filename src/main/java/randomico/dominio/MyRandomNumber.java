package randomico.dominio;

import java.util.Random;

import randomico.exception.IntervaloInvalidoException;

public class MyRandomNumber {

	private Random random = new Random();
	private int numeroAleatorioAnterior;
	private int numeroAleatorioAtual;

	public int nextRandomNumber(int end) {
		return nextRandomNumber(0, end);
	}

	public int nextRandomNumber(int beginExclusive, int endExclusive) {

		if (beginExclusive < 0 || endExclusive < 0) {
			throw new IntervaloInvalidoException("Begin ou End não podem ser menor que zero");
		}

		if (beginExclusive >= endExclusive) {
			throw new IntervaloInvalidoException("Begin deve ser menor que end");
		}

		numeroAleatorioAnterior = numeroAleatorioAtual;

		do {
			numeroAleatorioAtual = random.nextInt(endExclusive - beginExclusive) + beginExclusive + 1;
		} while (numeroAleatorioAtual == numeroAleatorioAnterior);

		return numeroAleatorioAtual;
	}

	public int getNumeroAleatorioAnterior() {
		return numeroAleatorioAnterior;
	}

	public boolean isEqualToBefore() {
		return (getNumeroAleatorioAnterior() == getNumeroAleatorioAtual());
	}

	public int getNumeroAleatorioAtual() {
		return numeroAleatorioAtual;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

}
