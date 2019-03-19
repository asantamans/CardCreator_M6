package Logica;

public class ControladorInterfaz {
	private boolean cartasCargadas;
	private boolean deckCargado;
	public ControladorInterfaz(){
		cartasCargadas = false;
		deckCargado = false;
	}

	public boolean isCartasCargadas() {
		return cartasCargadas;
	}

	public void setCartasCargadas(boolean cartasCargadas) {
		this.cartasCargadas = cartasCargadas;
	}

	public boolean isDeckCargado() {
		return deckCargado;
	}

	public void setDeckCargado(boolean deckCargado) {
		this.deckCargado = deckCargado;
	}
	
	
}
