package control;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ControllerGate {
	
	private HashMap<String, String> hosts = new HashMap<String, String>();
	private static ControllerGate instance;

	public static ControllerGate getInstance() {
		if(instance == null)
			instance = new ControllerGate();
		return instance;		 
	}
	
	public ControllerGate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void saveHost(String fileName, String url) {
		// TODO Auto-generated method stub
		hosts.put(fileName, url);
	}

	public String searchHost(String fileName) {
		// TODO Auto-generated method stub
		return hosts.get(fileName);
	}

	public void removeHost(String fileName) {
		// TODO Auto-generated method stub
		hosts.remove(fileName);
	}
	
	public String[] getAllFiles() {
		Iterator iterator = hosts.entrySet().iterator();
		List<String> fileNames = new ArrayList<String>();
		if(iterator.hasNext()) {
			Map.Entry<String,String> par = (Entry<String, String>) iterator.next();
			String fileName = par.getKey();
			fileNames.add(fileName);
			System.out.println("Adicionou " + fileName);
			iterator.remove();
		}
		return (String[]) fileNames.toArray();
	}

}
