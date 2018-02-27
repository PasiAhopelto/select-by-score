package github.pasiahopelto.scorelib;

import java.io.IOException;
import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import github.pasiahopelto.scorelib.fetcher.VotingsLister;
import github.pasiahopelto.scorelib.model.Voting;
import github.pasiahopelto.scorelib.reader.InputFileReader;
import github.pasiahopelto.scorelib.reader.LineSplitter;
import github.pasiahopelto.scorelib.reader.ParseException;
import github.pasiahopelto.scorelib.rest.VotingsRest;
import github.pasiahopelto.scorelib.writer.DataWriter;
import github.pasiahopelto.scorelib.writer.DbMaker;
import github.pasiahopelto.scorelib.writer.PartyInsertMaker;
import github.pasiahopelto.scorelib.writer.PartyInserter;
import github.pasiahopelto.scorelib.writer.VotesInsertMaker;
import github.pasiahopelto.scorelib.writer.VotesInserter;
import github.pasiahopelto.scorelib.writer.VotingInsertMaker;
import github.pasiahopelto.scorelib.writer.VotingInserter;

@Configuration
public class DbConfig {

	@Autowired
	@Singleton
	@Bean
	public VotingsRest votingsRest(JdbcTemplate jdbcTemplate, VotingsLister votingsLister) {
		return new VotingsRest(jdbcTemplate, votingsLister);
	}

	@Singleton
	@Bean
	public VotingsLister votingsLister() {
		return new VotingsLister();
	}

	@Singleton
	@Bean
	public JdbcTemplate jdbcTemplate(DbMaker dbMaker, InputFileReader inputFileReader, DataWriter dataWriter) throws IOException, ParseException {
		List<Voting> votings = inputFileReader.read("input.txt");
		return dataWriter.write(votings);
	}

	@Singleton
	@Bean
	public DataWriter dataWriter(DbMaker dbMaker, PartyInserter partyInserter, VotingInserter votingInserter, VotesInserter votesInserter) {
		return new DataWriter(dbMaker, partyInserter, votingInserter, votesInserter);
	}

	@Singleton
	@Bean
	public DbMaker dbMaker() {
		return new DbMaker();
 	}

	@Singleton
	@Bean
	public LineSplitter lineSplitter() {
		return new LineSplitter();
	}

	@Singleton
	@Bean
	public InputFileReader inputFileReader(LineSplitter lineSplitter) {
		return new InputFileReader(lineSplitter);
	}

	@Singleton
	@Bean
	public PartyInsertMaker partyInsertMaker() {
		return new PartyInsertMaker();
	}

	@Singleton
	@Bean
	public PartyInserter partyInserter(PartyInsertMaker partyInsertMaker) {
		return new PartyInserter(partyInsertMaker);
	}

	@Singleton
	@Bean
	public VotingInsertMaker votingInsertMaker() {
		return new VotingInsertMaker();
	}

	@Singleton
	@Bean
	public VotingInserter votingInserter(VotingInsertMaker votingInsertMaker) {
		return new VotingInserter(votingInsertMaker);
	}

	@Singleton
	@Bean
	public VotesInsertMaker votesInsertMaker() {
		return new VotesInsertMaker();
	}

	@Singleton
	@Bean
	public VotesInserter votesInserter(VotesInsertMaker votesInsertMaker) {
		return new VotesInserter(votesInsertMaker);
	}
}
