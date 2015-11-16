package vonatteszt;

public class Main
{
	private Main(){}
	
	public static void main (String[] args)
	{
		Vonal vonal = new Vonal(100);
		for (Vonat v : vonal.getVonatLista())
		{
			System.out.println(v.getVonatIndex() + ". vonat " +	v.getLog()[27].logSzoveg());
		}
		
	}
	
}
