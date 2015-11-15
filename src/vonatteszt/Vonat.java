package vonatteszt;

public class Vonat
{
	private int vonalAzonosito;
	private double jelenlegiPozicio;
	private boolean vegallomas;
	private double keslekedesIdo;
	
	public Vonat(int vonalAzonosito, double pozicio, boolean vegallomas, double keslekedesIdo)
	{
		this.vonalAzonosito = vonalAzonosito;
		this.jelenlegiPozicio = pozicio;
		this.vegallomas = vegallomas;
		this.keslekedesIdo = keslekedesIdo;
	}
	
	/**
	 * Hozzaadja a megtett tavot a jelenlegi poziciohoz, visszaadja az uj jelenlegiPoziciot*/
	public double kovetkezoLepes(double megtettTav)
	{
		jelenlegiPozicio = jelenlegiPozicio + megtettTav;
		return jelenlegiPozicio;
	}
	
	public void percVarakozas()
	{
		keslekedesIdo--;
		if (keslekedesIdo <= 0)
		{
			vegallomas = false;
		}
	}
	
	/**
	 * Visszaadja a jelenlegi vonal azonositojat.*/
	public int getVonalAzonosito()
	{
		return vonalAzonosito;
	}
	
	/**
	 * Beallitja a jelenlegi vonal azonositojat */
	public void setVonalAzonosito(int ujAzonosito)
	{
		vonalAzonosito = ujAzonosito;
	}
	
	/**
	 * Ha a vonat vegallomason tartozkodik, igazat ad vissza*/
	public boolean vegallomasonVan()
	{
		return vegallomas;
	}
	
	/**
	 * Beallitja a vegallomas boolean erteket*/
	public void setVegallomas(boolean ertek)
	{
		vegallomas = ertek;
	}
	
}
