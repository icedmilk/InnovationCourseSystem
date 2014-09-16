package bean;

public class user {

String cno;
String cpwd;
String cname;
String role;


public String getUsername() {
	return cno;
}
public void setUsername(String username) {
	this.cno = username;
}
public String getPassword() {
	return cpwd;
}
public void setPassword(String password) {
	this.cpwd = password;
}
public String getRealname() {
	return cname;
}
public void setRealname(String realname) {
	this.cname = realname;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

}
