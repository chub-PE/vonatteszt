package vonatteszt;

import java.util.ArrayList;

public class Vonal
{
	//a ket vonal azonositoja
	public static final int ODA_VONAL = 1;
	public static final int VISSZA_VONAL = 2;
	
	//mivel csak az egyik iranyban van lassitas, a lassu vonalt ezzel a valtozoval lehet meghatarozni
	private int lassuVonal = ODA_VONAL;

	//a vonal hossza meterben, hogy konnyebb legyen tagolni
	private double vonalHossz = 6000;
	
	//a lassu szakaszon 6km/h-val, a hagyomanyos szakaszon 18km/h-val halad a vonat
	private double hagyomanyosSzakaszSebesseg = 18000;
	private double lassuSzakaszSebesseg = 6000;
	
	//varakozas a vegallomason (perc)
	private double vegallomasVarakozas = 5;
	
	//az ido ket vonat inditasa kozott
	private double indulasKesletetes = 10;
	
	//a lassu szakasz vege es kezdete
	private double lassuSzakaszKezdet = 2000;
	private double lassuSzakaszVeg = 4000;
	
	private int vonatokSzama;
	private double elteltIdo;
	private ArrayList<Vonat> vonatLista;
	
	
	
}
