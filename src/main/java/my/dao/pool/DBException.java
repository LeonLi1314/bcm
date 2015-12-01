package my.dao.pool;

public class DBException extends RuntimeException {

	public DBException(String msg) {
		super(msg);
	}

	public DBException(Exception e) {
		super(e);
	}

	/**
     *
     */
	private static final long serialVersionUID = 1L;

}
