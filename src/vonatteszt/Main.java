package vonatteszt;

public class Main
{
	private Main(){}
	
	public static void main (String[] args)
	{
		Szimulacio vonal = new Szimulacio(11000);
		for (Vonat v : vonal.getVonatLista())
		{
			System.out.println(v.getVonatSorszam() + ". vonat " +	v.getLog()[20].logSzoveg());
		}
		
	}
	
}
