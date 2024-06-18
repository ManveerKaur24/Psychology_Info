package User;

public interface UserRepo {

	public void register(User user)throws Exception;
    public boolean checkUser(User user)throws Exception;
    public boolean login(User user)throws Exception;
    
}