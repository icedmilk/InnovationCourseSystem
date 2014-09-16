package bean;

public class course {
	private String courseId     = "";    //课程ID
    private String courseName   = "";    //课程名称
    private String ctype		= "";
    private int    cmax      = 0;     //选课人数
    private int   amount       =0;     //已选课人数
    private int asp = 0;
    
    public int getAsp(){
    	return asp;
    }
    public void setAsp(int asp){
    	this.asp = asp;
    }
    
    public String getCtype(){
    	return ctype;
    }
    public void setCtype(String type){
    	this.ctype = type;
    }
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getLimited() {
		return cmax;
	}
	public void setLimited(int limited) {
		this.cmax = limited;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
