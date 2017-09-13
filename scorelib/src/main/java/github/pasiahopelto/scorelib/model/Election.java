package github.pasiahopelto.scorelib.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Election implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Party> parties;
	private List<Voting> votings;
	private List<Vote> votes;
	
	public List<Party> getParties() {
		return parties;
	}
	
	public void setParties(List<Party> parties) {
		this.parties = parties;
	}
	
	public List<Voting> getVotings() {
		return votings;
	}
	
	public void setVotings(List<Voting> votings) {
		this.votings = votings;
	}
	
	public List<Vote> getVotes() {
		return votes;
	}
	
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(parties).append(votes).append(votings).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Election) {
			Election other = (Election) obj;
			result = new EqualsBuilder().append(other.parties, parties).append(other.votes, votes).append(other.votings, votings).isEquals();
		}
		return result;
	}
}
