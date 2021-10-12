package randomico.dominio;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import randomico.exception.IntervaloInvalidoException;

public class numeroRandomicoTest {

	private MyRandomNumber myRandomNumber;
	private MyRandomNumber myRandomNumberMock;
	private Random randomMock;

	@BeforeEach
	void setup() {
		myRandomNumber = new MyRandomNumber();
		randomMock = Mockito.mock(Random.class);
		myRandomNumberMock = Mockito.mock(MyRandomNumber.class);
	}

	@Test
	void beginAndEndDevemSerMaiorQueZero() {

		assertThrows(IntervaloInvalidoException.class, () -> {
			myRandomNumber.nextRandomNumber(-1, 0);
		});

		assertThrows(IntervaloInvalidoException.class, () -> {
			myRandomNumber.nextRandomNumber(0, -5);
		});

	}

	@Test
	void beginDeveSerMenorQueEnd() {

		assertThrows(IntervaloInvalidoException.class, () -> {
			myRandomNumber.nextRandomNumber(20, 10);
		});

		assertThrows(IntervaloInvalidoException.class, () -> {
			myRandomNumber.nextRandomNumber(10, 10);
		});

	}

	@Test
	void numeroAleatorioAtualDeveSerZero() {
		assertThat(myRandomNumber.getNumeroAleatorioAtual(), is(equalTo(0)));
	}

	@Test
	void numeroAleatorioAnteriorDeveSerZero() {
		assertThat(myRandomNumber.getNumeroAleatorioAnterior(), is(equalTo(0)));
	}

	@Test
	void numeroAleatorioAtualDeveSerDiferenteDoAnteriorMock() {
		
//		myRandomNumber.setRandom(randomMock);
//		Mockito.when(randomMock.nextInt(20)).thenReturn(15);
//		Mockito.when(randomMock.nextInt(17)).thenReturn(17);
//		
//		int randomNumber1 = myRandomNumber.nextRandomNumber(20);
//		int randomNumber2 = myRandomNumber.nextRandomNumber(17);
//		
//		assertThat(randomNumber1, not(equalTo(randomNumber2)));
//		assertThat(myRandomNumberMock.isEqualToBefore(), is(equalTo(false)));
		
		MyRandomNumber myRandomNumberMock = Mockito.mock(MyRandomNumber.class);
		Mockito.when(myRandomNumberMock.nextRandomNumber(1, 10)).thenReturn(5);
		Mockito.when(myRandomNumberMock.getNumeroAleatorioAnterior()).thenReturn(5);

		int numeroAleatorioAnterior = myRandomNumberMock.nextRandomNumber(1, 10);
		
		Mockito.when(myRandomNumberMock.nextRandomNumber(1, 8)).thenReturn(7);
		Mockito.when(myRandomNumberMock.getNumeroAleatorioAtual()).thenReturn(7);

		int numeroAleatorioAtual = myRandomNumberMock.nextRandomNumber(1, 8);

		assertThat(numeroAleatorioAtual, not(equalTo(numeroAleatorioAnterior)));
		assertThat(myRandomNumberMock.isEqualToBefore(), is(equalTo(false)));

	}

	@Test
	void numeroAleatorioAtualDeveSerIgualAnteriorMock() {

		Mockito.when(randomMock.nextInt(20)).thenReturn(15);
		myRandomNumberMock.setRandom(randomMock);

		Mockito.when(myRandomNumberMock.nextRandomNumber(20)).thenReturn(15);
		Mockito.when(myRandomNumberMock.getNumeroAleatorioAtual()).thenReturn(15);
		Mockito.when(myRandomNumberMock.getNumeroAleatorioAnterior()).thenReturn(15);

		var random1 = myRandomNumberMock.nextRandomNumber(20);
		var random2 = myRandomNumberMock.nextRandomNumber(20);

		assertThat(random1, is(equalTo(random2)));
		assertThat(random1, is(equalTo(15)));
		assertThat(random2, is(equalTo(15)));

		assertThat(myRandomNumberMock.getNumeroAleatorioAtual(), is(equalTo(15)));
		assertThat(myRandomNumberMock.getNumeroAleatorioAnterior(), is(equalTo(15)));

		assertThat(myRandomNumberMock.getNumeroAleatorioAtual(),
				is(equalTo(myRandomNumberMock.getNumeroAleatorioAnterior())));

	}

	@Test
	void numeroAleatorioAtualDeveSerDiferenteDoAnterior() {

		for (var i = 0; i < 1000; i++) {
			int numeroAtual = myRandomNumber.nextRandomNumber(10, 50);
			int numeroAnterior = myRandomNumber.getNumeroAleatorioAnterior();

			assertThat(numeroAtual, not(equalTo(numeroAnterior)));
			assertThat(myRandomNumber.isEqualToBefore(), is(equalTo(false)));

			numeroAtual = myRandomNumber.nextRandomNumber(10, 50);
			numeroAnterior = myRandomNumber.getNumeroAleatorioAnterior();

			assertThat(numeroAtual, not(equalTo(numeroAnterior)));
			assertThat(myRandomNumber.isEqualToBefore(), is(equalTo(false)));
		}

	}

	@Test
	void numeroAleatorioDeveEstarNoIntervalo() {

		var numeroAtual = myRandomNumber.nextRandomNumber(10, 20);
		assertThat(numeroAtual, is(greaterThanOrEqualTo(10)));
		assertThat(numeroAtual, is(lessThanOrEqualTo(20)));

		numeroAtual = myRandomNumber.nextRandomNumber(6, 7);
		assertThat(numeroAtual, is(equalTo(7)));

		numeroAtual = myRandomNumber.nextRandomNumber(1000, 50000);
		assertThat(numeroAtual, is(greaterThanOrEqualTo(1000)));
		assertThat(numeroAtual, is(lessThanOrEqualTo(50000)));

	}

}
