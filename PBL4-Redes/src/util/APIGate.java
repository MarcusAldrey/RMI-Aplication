package util;

public interface APIGate {
	
	public void saveHost(String fileName, String url);
	public String searchHost(String fileName);;
	public void removeHost(String fileName);
	public String[] getAllFiles(); 

}
