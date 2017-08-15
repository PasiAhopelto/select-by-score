package github.pasiahopelto.scorelib.writer;

import java.io.Serializable;

public class UnknownEntityException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnknownEntityException(Class<? extends Serializable> entityClass) {
		super("unknown class: " + entityClass.getName());
	}
}
