package vonatteszt;

public class Main
{
	private Main(){}
	
	private static Szimulacio sim;
	
	public static void main (String[] args)
	{
		sim = new Szimulacio(120);
		//mivel az elso vonat a 0. percben indult el, utana minden vonat 10 perc kesessel, a hatodik vonat a 49. percben indult el.
		System.out.println(feladat0(5));
		System.out.println(feladat2(5));
		
	}
	
	public static String feladat0 (int vonat_index)
	{
		String result = "0. feladat : Mikor indult el a hatodik vonat? \n\t";
		
		Vonat vonat = sim.getVonatLista()[vonat_index];
		
		for (Log l : sim.getVonatLista()[5].getLog())
		{
			if (!l.isMegNemUtazott())
			{
				return result + "A hatodik vonat " + oraPercFormatum(l.getIdo()) + "-kor indult el.";
			}
		}
		return result + "A vonat meg nem indult el.";
	}
	
	public static String feladat2 (int vonat_index)
	{
		String result = "2. feladat : Miket lat az utolso villamos vezetoje idorendben?\n\t";
	
		Vonat vonat = sim.getVonatLista()[vonat_index];
		Log[] log = vonat.getLog();
		
		boolean indulas = false;
		boolean lassu_szakasz_eleje = false;
		boolean lassu_szakasz_vege = false;
		boolean vegallomas = false;
		boolean visszafele = false;
		
		int utolso_vonat_ami_mellett_elhaladt = vonat_index;
		
		for (int i = 0; i < log.length; i++)
		{
			if (!log[i].isMegNemUtazott() && !indulas)
			{
				indulas = true;
				result = result + "A villamos " + oraPercFormatum(log[i].getIdo()) + "-kor indult az A allomasrol.\n\t";
			}
			if(log[i].isLassuSzakasz() && !lassu_szakasz_eleje)
			{
				lassu_szakasz_eleje = true;
				result = result + "A villamos " + oraPercFormatum(log[i].getIdo()) + "-kor ert a lassu szakaszhoz.\n\t";
			}
			if(!log[i].isLassuSzakasz() && lassu_szakasz_eleje && !lassu_szakasz_vege)
			{
				lassu_szakasz_vege = true;
				result = result + "A villamos " + oraPercFormatum(log[i].getIdo()) + "-kor hagyta el a lassu szakaszt.\n\t";
			}
			if(log[i].getStatusz() == Log.B_VEGALLOMASON_ALL && !vegallomas)
			{
				vegallomas = true;
				result = result + "A villamos " + oraPercFormatum(log[i].getIdo()) + "-kor ert a B vegallomasra.\n\t";
			}
			if(log[i].getStatusz() == Log.BA_VONALON_HALAD && !visszafele)
			{
				visszafele = true;
				result = result + "A villamos " + oraPercFormatum(log[i].getIdo()) + "-kor indult vissza.\n\t";
			}
			for (Vonat v : sim.getVonatLista())
			{
				//ha a ket vonat megegyezik, az iteracio lep egyet
				if (!v.equals(vonat))
				{
					if (szembenHaladnakEl(v, vonat, i) && utolso_vonat_ami_mellett_elhaladt != v.getVonatSorszam())
					{
						utolso_vonat_ami_mellett_elhaladt = v.getVonatSorszam();
						result = result + "A villamos " + oraPercFormatum(log[i].getIdo()) + "-kor elhaladt a "
								+ v.getVonatSorszam() + "-as villamos mellett.\n\t";
					}
				}
			}
		}
		return result;
	}
	
	//TODO kicserelni, ha valaki erti mirol szol a kerdes
	public static String feladat7()
	{
		return "az osszes? nincs ertelme a kerdesnek.";
	}
	
	
	
	
	
	/**Eldonti ket adott villamosrol, hogy adott idopontban egymas mellett haladnak-e el.*/
	public static boolean szembenHaladnakEl(Vonat elso_villamos, Vonat masodik_villamos, int idopont)
	{
		
		
		//osszeadom a ket vonat eddigi megtett utjat
		double sum = masodik_villamos.getLog()[idopont].getPozicio() + elso_villamos.getLog()[idopont].getPozicio();
		
		//kiszamolom, hany metert mehet maximum egy villamos egy perc alatt.
		double lehetsegesKulonbseg = (sim.getHagyomanyosSzakaszSebesseg() / 60);
		//kiszamolok ket hatarerteket, amin belul a ket villamos "egymassal mellett" halad el
		double legnagyobbLehetseges = sim.getVonalHossz() + lehetsegesKulonbseg;
		double legkisebbLehetseges = sim.getVonalHossz() - lehetsegesKulonbseg;
		
		//meghatarozom, hogy a ket villamos egymassal szemben halad-e az adott pillanatban
		if (sum < legnagyobbLehetseges && sum > legkisebbLehetseges)
		{
			return true;
		}
		return false;
	}
	
	/**Az adott perc ertekbol kiszamolja az ora-perc idot.*/
	public static String oraPercFormatum(double ido)
	{
		int ora = (int) ido / 60;
		int perc = (int) ido % 60;
		
		return ora + ":" + perc;
	}
}
