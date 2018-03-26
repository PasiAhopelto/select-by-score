package github.pasiahopelto.scorelib.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Score implements Serializable {

	private static final long serialVersionUID = 1L;

	private int matchingVotes;
	private int totalVotes;
	private String party;

	public float getPercentage() {
		return (float) matchingVotes / (float) totalVotes;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	public int getMatchingVotes() {
		return matchingVotes;
	}

	public void setMatchingVotes(int matchingVotes) {
		this.matchingVotes = matchingVotes;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Score) {
			Score other = (Score) obj;
			result = new EqualsBuilder().append(other.getMatchingVotes(), getMatchingVotes()).append(other.totalVotes, totalVotes).append(other.party, party).isEquals();
		}
		return result;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getMatchingVotes()).append(totalVotes).append(party).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("percentage", getPercentage()).append("matchingVotes", getMatchingVotes()).append("totalVotes", totalVotes).append("party", party).build();
	}
}
