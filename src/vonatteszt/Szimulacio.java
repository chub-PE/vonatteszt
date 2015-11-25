package vonatteszt;

import java.util.ArrayList;

public class Szimulacio
{
	//a ket vonal azonositoja
	public static final int AB_VONAL = 1;
	public static final int BA_VONAL = 2;
	
	//mivel csak az egyik iranyban van lassitas, a lassu vonalt ezzel a valtozoval lehet meghatarozni
	private int LASSU_VONAL = AB_VONAL;

	//a vonal hossza meterben, hogy konnyebb legyen tagolni
	private double vonalHossz = 6000;
	
	//a lassu szakaszon 6km/h-val, a hagyomanyos szakaszon 18km/h-val halad a vonat 
	//(meter per oraban megadva)
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
	private int vonatokSzama = 6;
	
	//eltelt ido
	private double elteltIdo;
	
	//vonatok listaja
	private ArrayList<Vonat> vonatLista = new ArrayList<Vonat>();
	
	public Szimulacio(double futasiIdo)
	{
		//kesleltetes ket vonat inditasa kozott.
		double keslekedes = 0;
		//elkesziti a vonat objektumokat
		for (int i = 0; i != vonatokSzama; i++)
		{
			vonatLista.add(new Vonat(this, i + 1, AB_VONAL, 0, keslekedes));
			keslekedes = keslekedes + indulasKesleltetes;
		}
		elteltIdo = 0;
	
		//lefuttatja a szimulaciot
		for (int i = 0; i < futasiIdo; i++)
		{
			idoMulato();
		}
	}
	
	/**Minden alkalommal mikor ezt a metodust hivjak, az ido elore mozdul egy perccel.*/
	public void idoMulato()
	{
		for (Vonat v : vonatLista)
		{
			//ha a vonat a vegallomason tartozkodik, mozgas helyett egy percet var
			if (v.getVegallomasonVan())
			{
				v.percVarakozas();
			}
			//ha a vonat uton van, megtesz egy bizonyos utat. Ha ezutan megerkezett az allomasra...
			else if (v.eloreMozgas(megtettTav(v)) >= vonalHossz)
			{
				//...akkor a vonat varakozik a vegallomason.
				v.setKeslekedes(vegallomasKesleltetes);
			}
		}
		elteltIdo++;
	}

	
	/**Utszakasztol fuggoen kiszamolja a megtett tavot.*/
	private double megtettTav(Vonat vonat)
	{
		if (lassuSzakaszbanVan(vonat))
		{
			return lassuSzakaszSebesseg / 60;
		}
		return hagyomanyosSzakaszSebesseg / 60;
	}
	
	/**Ha a vonat a lassu szakaszban van, igazat ad vissza.*/
	public boolean lassuSzakaszbanVan(Vonat vonat)
	{
		return (vonat.getVonalAzonosito() == LASSU_VONAL && 
				(vonat.getPozicio() > lassuSzakaszKezdet &&
						vonat.getPozicio() < lassuSzakaszVeg));
	}

	public double getElteltIdo()
	{
		return elteltIdo;
	}

	public double getVonalHossz()
	{
		return vonalHossz;
	}
	
	public Vonat[] getVonatLista()
	{
		return vonatLista.toArray(new Vonat[vonatLista.size()]);
	}
	
	public double getHagyomanyosSzakaszSebesseg()
	{
		return hagyomanyosSzakaszSebesseg;
	}
}
