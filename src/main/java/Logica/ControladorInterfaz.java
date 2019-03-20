package Logica;

import java.util.ArrayList;
import java.util.Random;

import Interfaz.Editor;
import Objectes.Carta;
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
		setDeckValue(0);
		boolean end = false;
		ArrayList<Carta> deckRandomBuild = new ArrayList<Carta>();
		ArrayList<Carta> listaCartasTMP  = cartaExistDB.getConexion().obtenirCartes();
		ArrayList<Carta> posiblesCandidatos  =new ArrayList<Carta>();
		while (!end) {
			posiblesCandidatos = new ArrayList<Carta>();
			for (Carta a : listaCartasTMP) {
				if (a.getValue() <= getDeckValue() - 20) {
					posiblesCandidatos.add(a);
				}
			}
			if (posiblesCandidatos.size() > 0) {
				//Obtenemos una carta aleatoria de todos los posibles candidatos
				Random rand = new Random();
				Carta tmp = posiblesCandidatos.get(rand.nextInt(posiblesCandidatos.size()));
				//Eliminamos la carta de las proximas posibles cartas
				listaCartasTMP.remove(tmp);
				deckRandomBuild.add(tmp);
				for (Carta a : deckRandomBuild) {
					//Actualizamos el deckValue del mazo
					setDeckValue(getDeckValue()+a.getValue());
				}
				
			}else {
				end = true;
			}
		}
		Editor.cartesArray = listaCartasTMP;
		Editor.deckArray = deckRandomBuild;
		//Actualizamos los DefaultModelList
		Editor.cartesDLM.clear();
		Editor.deckDLM.clear();
		for (Carta a:	listaCartasTMP) {
			Editor.cartesDLM.addElement(a);
		}
		for (Carta a:	deckRandomBuild) {
			Editor.deckDLM.addElement(a);
		}
	}
	public void randomDeck(ArrayList <Carta> mantener) {
		//Mismo metodo anterior de generacion de deck a excepcion de mantener las cartas de antes
		setDeckValue(0);
		boolean end = false;
		ArrayList<Carta> deckRandomBuild = mantener;
		ArrayList<Carta> listaCartasTMP  = cartaExistDB.getConexion().obtenirCartes();
		ArrayList<Carta> posiblesCandidatos  =new ArrayList<Carta>();
		while (!end) {
			posiblesCandidatos = new ArrayList<Carta>();
			for (Carta a : listaCartasTMP) {
				if (a.getValue() <= getDeckValue() - 20) {
					posiblesCandidatos.add(a);
				}
			}
			if (posiblesCandidatos.size() > 0) {
				//Obtenemos una carta aleatoria de todos los posibles candidatos
				Random rand = new Random();
				Carta tmp = posiblesCandidatos.get(rand.nextInt(posiblesCandidatos.size()));
				//Eliminamos la carta de las proximas posibles cartas
				listaCartasTMP.remove(tmp);
				deckRandomBuild.add(tmp);
				for (Carta a : deckRandomBuild) {
					//Actualizamos el deckValue del mazo
					setDeckValue(getDeckValue()+a.getValue());
				}
				
			}else {
				end = true;
			}
		}
		Editor.cartesArray = listaCartasTMP;
		Editor.deckArray = deckRandomBuild;
		//Actualizamos los DefaultModelList
		Editor.cartesDLM.clear();
		Editor.deckDLM.clear();
		for (Carta a:	listaCartasTMP) {
			Editor.cartesDLM.addElement(a);
		}
		for (Carta a:	deckRandomBuild) {
			Editor.deckDLM.addElement(a);
		}
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
