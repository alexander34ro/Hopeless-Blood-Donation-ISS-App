package Models;

public interface IUser extends IHasId, java.io.Serializable {
	String getParola();
	void setParola(String value);
	String getUsername();
	void setUsername(String value);
}