package daoImpl;


import java.util.ArrayList;
import org.json.XML;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xmldb.api.base.Database; 
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.google.gson.Gson;

import org.xmldb.api.DatabaseManager; 
import org.xmldb.api.base.Collection; 


import	Objectes.Carta;
import idao.ICarta;

public class CartaExistImpl implements ICarta{
	//Model singleton
	private static CartaExistImpl cartaExistImpl;
	
	//Conexió amb eXIST
	private Class cExist;
	private Database dbExist;
	private final String driver = "org.exist.xmldb.DatabaseImpl"; 
	private final String uri = "xmldb:exist://localhost:8844/exist/xmlrpc/db/"; 
	private final String resourceName = "card_collection.xml"; 

	private ArrayList<Carta> llistaCartes;

	
	//Model Singleton 
	public static CartaExistImpl getConexion() {
		if (cartaExistImpl == null) {
			cartaExistImpl = new CartaExistImpl();
			return cartaExistImpl;
		}
		return cartaExistImpl;
	}
	private CartaExistImpl() {
		conectar();
		carregarCartes();
	}
	
	private void conectar() {
		try {
	        cExist = Class.forName(driver);
	        dbExist = (Database) cExist.newInstance();
			DatabaseManager.registerDatabase(dbExist);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void desconectar() {
		cExist = null;
		dbExist = null;
	}
	private void carregarCartes() {
		try {
            Collection collection = DatabaseManager.getCollection(uri); 
            XMLResource xmlResource = (XMLResource) collection.getResource(resourceName);
            JSONObject jsonObject = XML.toJSONObject((String)xmlResource.getContent());
            JSONArray llistatCartesParseades = jsonObject.getJSONObject("cards").getJSONArray("card");
            
            for (Object object : llistatCartesParseades) {
            	Carta tmp = new Gson().fromJson(object.toString(), Carta.class);
            	llistaCartes.add(tmp);
			}
			llistatCartesParseades = null;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Carta> obtenirCartes() {
		// TODO Auto-generated method stub
		return llistaCartes;
	}

	public Carta getCartaFromId(int id) {
		boolean encontrada = false;
		Carta retorno = null;
		for (Carta tmp : llistaCartes) {
			if (tmp.getId() == id) {
				encontrada = true;
				return tmp;
			}
		}
		//Es un chivato para saber lo que pasa por consola...
		System.out.println("Carta con id "+id+" no encontrada");
		return null;
	}

}
