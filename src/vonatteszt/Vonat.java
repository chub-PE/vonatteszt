package vonatteszt;

public class Vonat
{
	//vonalazonosito, ahogy a Vonal osztalyban meg van hatarozva.
	private int vonalAzonosito;
	private double jelenlegiPozicio;
	private double keslekedesIdo;
	private double vonalHossz;
	
	//ha ez a boolean igaz, a vonat meg nem indult el ma.
	private boolean elsoUt = true;
	
	public Vonat(int vonalAzonosito, double pozicio, double keslekedesIdo, double vonalHossz)
	{
		this.vonalAzonosito = vonalAzonosito;
		this.jelenlegiPozicio = pozicio;
		this.keslekedesIdo = keslekedesIdo;
		this.vonalHossz = vonalHossz;
	}
	
	/**
	 * Hozzaadja a megtett tavot a jelenlegi poziciohoz, visszaadja az uj jelenlegiPoziciot*/
	public double eloreMozgas(double megtettTav)
	{
		elsoUt = false;
		jelenlegiPozicio = jelenlegiPozicio + megtettTav;
		return jelenlegiPozicio;
	}
	
	public void percVarakozas()
	{
		keslekedesIdo--;
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
		return keslekedesIdo > 0;
	}
	
	public void setKeslekedes(double keslekedes)
	{
		keslekedesIdo = keslekedes;
	}
		
	public double getPozicio()
	{
		return jelenlegiPozicio;
	}
	
	public void setPozicio(double pozicio)
	{
		jelenlegiPozicio = pozicio;
	}
	
	public String helyzetJelentes()
	{
		String result = "A vonat jelenleg ";
		
		if (vonalAzonosito == Vonal.ODA_VONAL)
		{
			result = result + "az A allomasrol a B allomasra tarto vonalon ";
		}
		else
		{
			result = result + "a B allomasrol az A allomasra tarto vonalon ";
		}
		
		if (vegallomasonVan())
		{
			//ha az elso ut igaz, akkor a vonat meg el sem indult aznap.
			if (elsoUt)
			{
				result = result + "all az elobbi pont vegallomasan. "
						+ "Varhatoan " + keslekedesIdo + " perc mulva indul el.";
			}
			else
			{
				result = result + "all az utobbi pont vegallomasan, utasokra varva. "
						+ "Varhatoan " + keslekedesIdo + " perc mulva indul vissza.";
			}
		}
		else
		{
			result = result + "halad. A jelenlegi menet " 
		+ megtettUtSzazalek() + "%-anal tart.";
		}
		
		return result;
	}
	
	public int megtettUtSzazalek()
	{
		return (int) (jelenlegiPozicio / vonalHossz * 100);
	}
	
}
