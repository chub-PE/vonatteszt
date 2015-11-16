package vonatteszt;

public class Main
{
	private Main(){}
	
	public static void main (String[] args)
	{
		Vonal vonal = new Vonal();
		for (Vonat v : vonal.szimulacioFuttatasa(40))
		{
			System.out.println(v.megtettUtSzazalek() + " a megtett utszazalek");
		}
	}
	
}
