package github.pasiahopelto.scorelib.writer;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import github.pasiahopelto.scorelib.model.Votes;
import github.pasiahopelto.scorelib.model.Voting;

public class DataWriter {

	private DbMaker dbMaker;
	private PartyInserter partyInserter;
	private VotingInserter votingInserter;
	private VotesInserter votesInserter;

	public DataWriter(DbMaker dbMaker, PartyInserter partyInserter, VotingInserter votingInserter, VotesInserter votesInserter) {
		this.dbMaker = dbMaker;
		this.partyInserter = partyInserter;
		this.votingInserter = votingInserter;
		this.votesInserter = votesInserter;
	}

	public JdbcTemplate write(List<Voting> votings) {
		JdbcTemplate jdbcTemplate = dbMaker.createDatabase();
		Map<String, Integer> partyIds = insertParties(votings, jdbcTemplate);
		for(Voting voting : votings) {
			Integer votingId = votingInserter.insert(jdbcTemplate, voting);
			for(Votes votes : voting.getVotes()) {
				Integer partyId = partyIds.get(votes.getParty());
				votesInserter.insert(jdbcTemplate, votes, votingId, partyId);
			}
		}
		return jdbcTemplate;
	}

	private Map<String, Integer> insertParties(List<Voting> votings, JdbcTemplate jdbcTemplate) {
		Map<String, Integer> partyIds = Maps.newHashMap();
		for(String partyName : collectPartyNames(votings)) {
			partyIds.put(partyName, partyInserter.insert(jdbcTemplate, partyName));
		}
		return partyIds;
	}

	private Collection<String> collectPartyNames(List<Voting> votings) {
		Set<String> parties = Sets.newHashSet();
		for(Voting voting : votings) {
			for(Votes votes : voting.getVotes()) {
				parties.add(votes.getParty());
			}
		}
		return parties;
	}
}