/*
 * Dieses Interface hat nur eine Methode:
 * Wer OBSERVER sein will, muss eine Methode update zur Verfügung stellen.
 */
public interface OBSERVER {
	
	public void update();
	
	public void add(Object o);
	
	public void remove(Object o);
	
	public Object get(int i);
	
}
