package craps.dominio;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CrapsTest {

	@Test
	public void jogadorPerdeNaPrimeiraRolagemComResultadoDois() {

		Dado dadoMock = Mockito.mock(Dado.class);
		Mockito.when(dadoMock.rolar()).thenReturn(1, 1);

		Craps craps = new Craps();
		craps.setDado(dadoMock);
		craps.rolarDados();

		assertThat(craps.isFimDeJogo(), is(equalTo(true)));
		assertThat(craps.getVencedor(), is(equalTo(2)));
		assertThat(craps.getSoma(), is(equalTo(2)));

	}

	@Test
	public void jogadorPerdeNaPrimeiraRolagemComResultadoDoze() {

		Dado dadoMock = Mockito.mock(Dado.class);
		Mockito.when(dadoMock.rolar()).thenReturn(6, 6);

		Craps craps = new Craps();
		craps.setDado(dadoMock);
		craps.rolarDados();

		assertThat(craps.isFimDeJogo(), is(equalTo(true)));
		assertThat(craps.getVencedor(), is(equalTo(2)));
		assertThat(craps.getSoma(), is(equalTo(12)));

	}

	@Test
	public void jogadorPerdeNaPrimeiraRolagemComResultadoTres() {

		Dado dadoMock = Mockito.mock(Dado.class);
		Mockito.when(dadoMock.rolar()).thenReturn(1, 2);

		Craps craps = new Craps();
		craps.setDado(dadoMock);
		craps.rolarDados();

		assertThat(craps.isFimDeJogo(), is(equalTo(true)));
		assertThat(craps.getVencedor(), is(equalTo(2)));
		assertThat(craps.getSoma(), is(equalTo(3)));

	}

	@Test
	public void jogadorGanhaNaPrimeiraRolagemComResultadoSete() {

		Dado dadoMock = Mockito.mock(Dado.class);
		Mockito.when(dadoMock.rolar()).thenReturn(5, 2);

		Craps craps = new Craps();
		craps.setDado(dadoMock);
		craps.rolarDados();

		assertThat(craps.isFimDeJogo(), is(equalTo(true)));
		assertThat(craps.getVencedor(), is(equalTo(1)));
		assertThat(craps.getSoma(), is(equalTo(7)));
	}

	@Test
	public void jogadorPerdeNaSegundaRolagem() {
		Dado dadoMock = Mockito.mock(Dado.class);
		Craps craps = new Craps();
		craps.setDado(dadoMock);

		Mockito.when(dadoMock.rolar()).thenReturn(2, 2);
		craps.rolarDados();
		assertFalse(craps.isFimDeJogo());
		assertThat(craps.getSoma(), is(equalTo(4)));

		Mockito.when(dadoMock.rolar()).thenReturn(4, 3);
		craps.rolarDados();
		assertTrue(craps.isFimDeJogo());
		assertThat(craps.getSoma(), is(equalTo(7)));

		assertThat(craps.getVencedor(), is(equalTo(2)));
	}

	@Test
	public void jogadorPerdeNaTerceiraRolagem() {
		Dado dadoMock = Mockito.mock(Dado.class);
		Craps craps = new Craps();
		craps.setDado(dadoMock);

		Mockito.when(dadoMock.rolar()).thenReturn(3, 5);
		craps.rolarDados();
		assertFalse(craps.isFimDeJogo());
		assertThat(craps.getSoma(), is(equalTo(8)));

		Mockito.when(dadoMock.rolar()).thenReturn(6, 5);
		craps.rolarDados();
		assertFalse(craps.isFimDeJogo());
		assertThat(craps.getSoma(), is(equalTo(11)));

		Mockito.when(dadoMock.rolar()).thenReturn(5, 2);
		craps.rolarDados();
		assertTrue(craps.isFimDeJogo());
		assertThat(craps.getSoma(), is(equalTo(7)));
		assertThat(craps.getVencedor(), is(equalTo(2)));
	}

	@Test
	public void jogadorGanhaNaSegundaRolagem() {
		Dado dadoMock = Mockito.mock(Dado.class);
		Craps craps = new Craps();
		craps.setDado(dadoMock);

		Mockito.when(dadoMock.rolar()).thenReturn(1, 3);
		craps.rolarDados();
		assertFalse(craps.isFimDeJogo());
		assertThat(craps.getSoma(), is(equalTo(4)));

		Mockito.when(dadoMock.rolar()).thenReturn(2, 2);
		craps.rolarDados();
		assertTrue(craps.isFimDeJogo());
		assertThat(craps.getSoma(), is(equalTo(4)));

		assertThat(craps.getVencedor(), is(equalTo(1)));
	}

}
