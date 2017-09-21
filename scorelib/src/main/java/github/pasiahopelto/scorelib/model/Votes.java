package github.pasiahopelto.scorelib.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Votes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private VotingOption votingOption;
	private Party party;
	
	public VotingOption getVotingOption() {
		return votingOption;
	}

	public void setVotingOption(VotingOption votingOption) {
		this.votingOption = votingOption;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(votingOption).append(party).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Votes) {
			Votes other = (Votes) obj;
			result = new EqualsBuilder().append(other.votingOption, votingOption).append(other.party, party).isEquals();
		}
		return result;
	}
}
