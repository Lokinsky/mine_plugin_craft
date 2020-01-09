package lokinsky.dope.plugin.Models;

import java.util.UUID;

public class Client {
	
	private String name;
	private UUID uuid;
	private boolean isAuth;
	private int countCommandWrite;

	public Client(String name, UUID uuid) {
		this.name = name;
		this.uuid = uuid;
		this.isAuth = false; 
		countCommandWrite = 0;
	}

	public int getCountCommandWrite() {
		return countCommandWrite;
	}
	public UUID get_uuid() {
		return this.uuid;
	}
	public void setCountCommandWrite(int countCommandWrite) {
		this.countCommandWrite = countCommandWrite;
	}

	public boolean isAuth() {
		return isAuth;
	}
	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
