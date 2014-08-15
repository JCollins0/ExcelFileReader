package gitProject;

public class Message
{
	private Message() {}
	
	/**
	 * @param error - the message to be sent
	 * @param classReference - ("this" (helps find errors quickly) )
	 */
	public static void error (String error,Object classReference)
	{
		System.out.println();
		System.out.println("ERROR: " + error + ", Error Origin: " + classReference.getClass().getName());
	}
	
	public static void test ()
	{
		System.out.println("Success");
	}
}
