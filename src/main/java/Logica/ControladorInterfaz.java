package Logica;

import Interfaz.Editor;
import daoImpl.BarallaMongoImpl;
import daoImpl.CartaExistImpl;

public class ControladorInterfaz {
	private boolean cartasCargadas;
	private boolean deckCargado;
	private CartaExistImpl cartaExistDB;
	private BarallaMongoImpl barallaMongoDB;
	private int deckValue = 0;
	
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
	public	void randomDeck() {
		
	}
	public void cargarCardList() {
		cartaExistDB.getConexion().carregarCartes();
		Editor.cartesArray = cartaExistDB.getConexion().obtenirCartes();
		
	}

	public int getDeckValue() {
		return deckValue;
	}

	public void setDeckValue(int deckValue) {
		this.deckValue = deckValue;
	}
	
	
	
}
