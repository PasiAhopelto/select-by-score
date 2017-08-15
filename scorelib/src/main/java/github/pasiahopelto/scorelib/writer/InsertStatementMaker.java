package github.pasiahopelto.scorelib.writer;

import java.io.Serializable;

import org.springframework.jdbc.core.PreparedStatementCreator;

public interface InsertStatementMaker {

	PreparedStatementCreator createStatementCreator(Serializable entity);
}
