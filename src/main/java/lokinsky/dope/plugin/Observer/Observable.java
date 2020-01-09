package lokinsky.dope.plugin.Observer;

public interface Observable {
	void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
