package vonatteszt;

import java.util.ArrayList;

public class Vonat
{
	//vonalazonosito, ahogy a Szimulacio osztalyban meg van hatarozva.
	private int vonalAzonosito;
	
	private double jelenlegiPozicio;
	private double keslekedesIdo;
	private Szimulacio vonal;
	private int hanyadikMenet;
	//a vonat sorszama (az alapjan, melyik indult el eloszorre, masodszorra, stb.)
	private int vonatSorszam;
	
	private ArrayList<Log> logGyujtemeny = new ArrayList<Log>();
	
	
	//ha ez a boolean igaz, a vonat meg nem indult el ma.
	private boolean megNemUtazott = true;
	
	/**
	 * Keszit egy uj vonat objektumot.
	 * Vonal : az objektum, ami a szimulaciot futtatja
	 * vonatSorszam : vonat azonosito sorszama
	 * vonalAzonosito : a jelenlegi utvonal azonositoja (ahogy a szimulacio osztalyban meg van hatarozva)
	 * pozicio : a vonat kezdo poziciooja
	 * keslekedesIdo : a vonat a megadott ido utan fog csak elindulni eloszor.*/
	public Vonat(Szimulacio vonal, int vonatSorszam, int vonalAzonosito, double pozicio, double keslekedesIdo)
	{
		this.vonatSorszam = vonatSorszam;
		this.vonal = vonal;
		this.vonalAzonosito = vonalAzonosito;
		this.jelenlegiPozicio = pozicio;
		this.keslekedesIdo = keslekedesIdo;
		hanyadikMenet = 0;
		//ez mindig csak az elso vonatra lesz ervenyes
		if (keslekedesIdo == 0)
		{
			vonalValtas();
			megNemUtazott = false;
		}
	}
	
	/**
	 * Hozzaadja a megtett tavot a jelenlegi poziciohoz, visszaadja az uj jelenlegiPoziciot*/
	public double eloreMozgas(double megtettTav)
	{
		jelenlegiPozicio = jelenlegiPozicio + megtettTav;
		logKezelo();
		return jelenlegiPozicio;
		
	}
	
	/**A vonat egy percet varakozik ott ahol van.*/
	public void percVarakozas()
	{
		keslekedesIdo--;
		if (keslekedesIdo <= 0)
		{
			if (megNemUtazott)
			{
				megNemUtazott = false;
			}
			else
			{
				hanyadikMenet++;
			}
			jelenlegiPozicio = 0;
			vonalValtas();
		}
		logKezelo();
	}
	
	public void logKezelo()
	{
		logGyujtemeny.add(new Log(this));
	}
	
	private void vonalValtas()
	{
		if (vonalAzonosito == Szimulacio.AB_VONAL)
		{
			vonalAzonosito = Szimulacio.BA_VONAL;
		}
		else
		{
			vonalAzonosito = Szimulacio.AB_VONAL;
		}
	}
	
	/**
	 * Beallitja a jelenlegi vonal azonositojat */
	public void setVonalAzonosito(int ujAzonosito)
	{
		vonalAzonosito = ujAzonosito;
	}
	
	
	public void setKeslekedes(double keslekedes)
	{
		keslekedesIdo = keslekedes;
	}
		
	public void setPozicio(double pozicio)
	{
		jelenlegiPozicio = pozicio;
	}
	
	public double getKeslekedesIdo()
	{
		return keslekedesIdo;
	}
	
	/**
	 * Ha a vonat vegallomason tartozkodik, igazat ad vissza*/
	public boolean getVegallomasonVan()
	{
		return keslekedesIdo >= 0;
	}
	
	/**Visszaadja a vonat jelenlegi poziciojat*/
	public double getPozicio()
	{
		return jelenlegiPozicio;
	}

	//statisztikai adat, jelenleg nincs hasznalatban
	public int getMegtettUtSzazalek()
	{
		return (int) (jelenlegiPozicio / vonal.getVonalHossz() * 100);
	}
	
	/**
	 * Visszaadja a vonat poziciojat a jelenlegi vonalon.*/
	public double getJelenlegiPozicio()
	{
		return jelenlegiPozicio;
	}
	

	/**
	 * Visszaadja a jelenlegi vonal azonositojat.*/
	public int getVonalAzonosito()
	{
		return vonalAzonosito;
	}
	
	/**Igazat ad vissza ha a vonat lassu szakaszban van.*/
	public boolean getLassuSzakaszbanVan()
	{
		return vonal.lassuSzakaszbanVan(this);
	}
	
	/**Igazat ad vissza, ha a vonat meg nem utazott.*/
	public boolean megNemUtazott()
	{
		return megNemUtazott;
	}
	
	/**Visszaadja a vonalat amin a vonat jelenleg tartozkodik.*/
	public Szimulacio getVonal()
	{
		return vonal;
	}
	
	/**Visszaadja a teljes log tombot.
	 * Hasznalat = a vonatInstance.getLog()[29] log objektum tartalmaz minden adatot
	 * arrol, hogy a vonat hol tartozkodott/mit csinalt a 29. percben.*/
	public Log[] getLog()
	{
		return logGyujtemeny.toArray(new Log[logGyujtemeny.size()]);
	}
	
	/**Visszaadja hanyadik menetnel tart ez a vonat.
	 * Minden oda-vissza ut 2 menetnek szamit.*/
	public int getHanyadikMenet()
	{
		return hanyadikMenet;
	}
	
	/**
	 * Visszaadja a jelenlegi vonat sorszamat.*/
	public int getVonatSorszam()
	{
		return vonatSorszam;
	}
	
	
}
