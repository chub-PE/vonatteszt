package vonatteszt;

import java.util.ArrayList;

public class Vonat
{
	//vonalazonosito, ahogy a Vonal osztalyban meg van hatarozva.
	private int vonalAzonosito;
	private double jelenlegiPozicio;
	private double keslekedesIdo;
	private Vonal vonal;
	private int hanyadikMenet;
	//a vonat sorszama (az alapjan, melyik indult el eloszorre, masodszorra, stb.)
	private int vonatIndex;
	
	private ArrayList<Log> logGyujtemeny = new ArrayList<Log>();
	
	
	//ha ez a boolean igaz, a vonat meg nem indult el ma.
	private boolean megNemUtazott = true;
	
	public Vonat(Vonal vonal, int vonatIndex, int vonalAzonosito, double pozicio, double keslekedesIdo)
	{
		this.vonatIndex = vonatIndex;
		this.vonal = vonal;
		this.vonalAzonosito = vonalAzonosito;
		this.jelenlegiPozicio = pozicio;
		this.keslekedesIdo = keslekedesIdo;
		hanyadikMenet = 0;
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
		if (vonalAzonosito == Vonal.AB_VONAL)
		{
			vonalAzonosito = Vonal.BA_VONAL;
		}
		else
		{
			vonalAzonosito = Vonal.AB_VONAL;
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
	
	public double getPozicio()
	{
		return jelenlegiPozicio;
	}

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
	
	public boolean getLassuSzakaszbanVan()
	{
		return vonal.lassuSzakaszbanVan(this);
	}
	
	public boolean megNemUtazott()
	{
		return megNemUtazott;
	}
	
	public Vonal getVonal()
	{
		return vonal;
	}
	
	public Log[] getLog()
	{
		return logGyujtemeny.toArray(new Log[logGyujtemeny.size()]);
	}
	
	public int getHanyadikMenet()
	{
		return hanyadikMenet;
	}
	
	public int getVonatIndex()
	{
		return vonatIndex;
	}
	
	
}
