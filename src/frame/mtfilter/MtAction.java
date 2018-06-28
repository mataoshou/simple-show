package frame.mtfilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public abstract class MtAction
{
	
//	{
//		showAction();
//	}
	
	protected Logger logger = Logger.getLogger(this.getClass());
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	final public boolean init(HttpServletRequest req, HttpServletResponse res)
	{
		request=req;
		response=res;
		session=request.getSession();
		return true;
	}
	
	public abstract void initAction();
	
	
	public abstract String excute(String content);
	
	
	public boolean check(){
		return true;
	}
	
	
	public void showAction()
	{
		logger.debug("..........."+this.getClass()+"................");
	}
	
	private boolean ischeck = false;
	private int code = 0;
	
	//获取状态信息
	final public String getReason() {
		return MtCodeCache.i().getVal(code);
	}

	public int getCode() {
		return code;
	}

	protected void setCode(int code) {
		this.code = code;
	}

	public abstract boolean ischeck();
}
