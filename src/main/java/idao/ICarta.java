package idao;

import java.util.ArrayList;

import Objectes.Carta;

public interface ICarta {
	public ArrayList<Carta> obtenirCartes();
	public Carta getCartaFromId(int id);
}
