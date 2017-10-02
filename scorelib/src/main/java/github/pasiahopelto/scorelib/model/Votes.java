package github.pasiahopelto.scorelib.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Votes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String vote;
	private String party;
	private Integer votes;
	
	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(vote).append(votes).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Votes) {
			Votes other = (Votes) obj;
			result = new EqualsBuilder().append(other.vote, vote).append(other.party, party).append(other.votes, votes).isEquals();
		}
		return result;
	}
}
