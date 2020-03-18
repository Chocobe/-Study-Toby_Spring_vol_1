package springbook.user.sqlservice;

public interface SqlService {
	abstract public String getSql(String key) throws SqlRetrievalFailureException;
}
