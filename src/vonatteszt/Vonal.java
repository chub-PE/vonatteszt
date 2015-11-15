package vonatteszt;

import java.util.ArrayList;

public class Vonal
{
	//a ket vonal azonositoja
	public static final int ODA_VONAL = 1;
	public static final int VISSZA_VONAL = 2;
	
	//mivel csak az egyik iranyban van lassitas, a lassu vonalt ezzel a valtozoval lehet meghatarozni
	public static int LASSU_VONAL = ODA_VONAL;

	//a vonal hossza meterben, hogy konnyebb legyen tagolni
	private double vonalHossz = 6000;
	
	//a lassu szakaszon 6km/h-val, a hagyomanyos szakaszon 18km/h-val halad a vonat
	private double hagyomanyosSzakaszSebesseg = 18000;
	private double lassuSzakaszSebesseg = 6000;
	
	//varakozas a vegallomason (perc)
	private double vegallomasKesleltetes = 5;
	
	//az ido ket vonat inditasa kozott
	private double indulasKesleltetes = 10;
	
	//a lassu szakasz vege es kezdete
	private double lassuSzakaszKezdet = 2000;
	private double lassuSzakaszVeg = 4000;
	
	//az inditando vonatok szama
	private int vonatokSzama;
	
	//eltelt ido
	private double elteltIdo;
	
	//vonatok listaja
	private ArrayList<Vonat> vonatLista = new ArrayList<Vonat>();
	
	public Vonal(int vonatokSzama)
	{
		double keslekedes = 0;
		for (int i = 0; i != vonatokSzama; i++)
		{
			vonatLista.add(new Vonat(ODA_VONAL, 0, keslekedes, vonalHossz));
			keslekedes = keslekedes + indulasKesleltetes;
		}
		elteltIdo = 0;
	}
	
	/**Minden alkalommal mikor ezt a metodust hivjak, az ido elore mozdul egy perccel.*/
	public void idoKezelo()
	{
		elteltIdo++;
		for (Vonat v : vonatLista)
		{
			//ha a vonat a vegallomason tartozkodik, mozgas helyett egy percet var
			if (v.vegallomasonVan())
			{
				v.percVarakozas();
			}
			//ha a vonat uton van, megtesz egy bizonyos utat.
			else if (v.eloreMozgas(megtettTav(v)) >= vonalHossz)
			{
				//ha ezzel az utolso mozdulattal elerte a vonal veget, akkor a vonat varakozik a vegallomason.
				v.setKeslekedes(vegallomasKesleltetes);
			}
		}
	}

	/**Ez a vonat meghatarozza az osszes vonat helyet az adott idoben*/
	public String[] szovegesHelyMeghatarozas(double elteltIdo)
	{
		idoKezelo();
		if (this.elteltIdo > elteltIdo)
		{
			ArrayList<String> result = new ArrayList<String>();
			
			for (Vonat v : vonatLista)
			{
				result.add(v.helyzetJelentes());
			}
			
			return result.toArray(new String[result.size()]);
		}
		else
		{
			return szovegesHelyMeghatarozas(elteltIdo);
		}
	}
	
	
	/**Utszakasztol fuggoen kiszamolja a megtett tavot.*/
	private double megtettTav(Vonat vonat)
	{
		if (vonat.getVonalAzonosito() == LASSU_VONAL &&	lassuSzakaszbanVan(vonat))
		{
			return lassuSzakaszSebesseg / 60;
		}
		return hagyomanyosSzakaszSebesseg / 60;
	}
	
	/**Ha a vonat a lassu szakaszban van, igazat ad vissza.*/
	private boolean lassuSzakaszbanVan(Vonat vonat)
	{
		return (vonat.getPozicio() > lassuSzakaszKezdet && vonat.getPozicio() < lassuSzakaszVeg);
	}


}
