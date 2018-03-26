package github.pasiahopelto.scorelib.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Selection implements Serializable {

	private static final long serialVersionUID = 1L;

	private String vote;
	private String votingName;

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

	public String getVotingName() {
		return votingName;
	}

	public void setVotingName(String votingName) {
		this.votingName = votingName;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Selection) {
			Selection other = (Selection) obj;
			result = new EqualsBuilder().append(other.vote, vote).append(other.votingName, votingName).isEquals();
		}
		return result;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(vote).append(votingName).toHashCode();
	}
}
