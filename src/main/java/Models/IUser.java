package Models;

public interface IUser extends IHasId {
	String getParola();
	void setParola(String value);
	String getUsername();
	void setUsername(String value);
}