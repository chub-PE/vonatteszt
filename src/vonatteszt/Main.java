package vonatteszt;

public class Main
{
	private Main(){}
	
	public static void main (String[] args)
	{
		Vonal vonal = new Vonal(10);
		for (String s : vonal.szovegesHelyMeghatarozas(60))
		{
			System.out.println(s);
		}
	}
	
}
