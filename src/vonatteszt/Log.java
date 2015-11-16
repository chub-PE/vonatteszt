package vonatteszt;

public class Log
{

	public static final int A_VEGALLOMASON_ALL = 1;
	public static final int AB_VONALON_HALAD = 2;
	public static final int B_VEGALLOMASON_ALL = 3;
	public static final int BA_VONALON_HALAD = 4;
	
	private double ido;
	private double pozicio;
	private boolean	lassuSzakasz;
	private int statusz;
	private double keslekedesiIdo;
	private int hanyadikMenet;
	
	public Log (Vonat vonat)
	{
		this(vonat.getVonal().getElteltIdo(), vonat.getPozicio(),
				vonat.getLassuSzakaszbanVan(),vonat.getVegallomasonVan(),
				vonat.megNemUtazott(), vonat.getVonalAzonosito(),
				vonat.getKeslekedesIdo(), vonat.getHanyadikMenet());
	}
	
	public Log(double ido, double pozicio, boolean lassuSzakasz, boolean vegallomasonVan,
			boolean megNemUtazott, int vonalAzonosito, double keslekedesiIdo,
			int hanyadikMenet)
	{
		this.ido = ido;
		this.pozicio = pozicio;
		this.lassuSzakasz = lassuSzakasz;
		this.hanyadikMenet = hanyadikMenet;
		this.keslekedesiIdo = keslekedesiIdo;
		
		if (vegallomasonVan)
		{
			if (megNemUtazott || vonalAzonosito == Szimulacio.BA_VONAL)
			{
				statusz = A_VEGALLOMASON_ALL;
			}
			else
			{
				statusz = B_VEGALLOMASON_ALL;
			}
		}
		else
		{
			if (vonalAzonosito == Szimulacio.AB_VONAL)
			{
				statusz = AB_VONALON_HALAD;
			}
			else
			{
				statusz = BA_VONALON_HALAD;
			}
		}
	}
	
	public String logSzoveg()
	{
		String result = "a " + ido + ". percben ";
		
		if (statusz == A_VEGALLOMASON_ALL || statusz == B_VEGALLOMASON_ALL)
		{
			if (statusz == A_VEGALLOMASON_ALL)
			{
				result = result + "az A vegallomason all, ahonnan ";
			}
			if  (statusz == B_VEGALLOMASON_ALL)
			{
				result = result + "az B vegallomason all, ahonnan ";
			}
			result = result + keslekedesiIdo + " perc mulva indul tovabb.";
		}
		else
		{
			if (statusz == AB_VONALON_HALAD)
			{
				result = result + "az A-B vonalon halad, ahol ";
			}
			else
			{
				result = result + "a B-A vonalon halad, ahol ";
			}
			result = result + "a "+ pozicio + " meternel van";
			
			if (lassuSzakasz)
			{
				result = result + ", egy lassitott szakaszon.";
			}
			else
			{
				result = result + " egy normal sebesseges szakaszon.";
			}
		}
		result = result + " A vonat eppen a " + hanyadikMenet + ". menetet vegzi.";
		return result;
	}
	
	
}
