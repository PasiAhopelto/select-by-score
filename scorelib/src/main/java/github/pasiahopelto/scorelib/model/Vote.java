package github.pasiahopelto.scorelib.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Vote implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String votingName;
	private String optionName;
	
	private Voting voting;
	private VotingOption votingOption;
	
	public String getVotingName() {
		return votingName;
	}
	
	public void setVotingName(String votingName) {
		this.votingName = votingName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Voting getVoting() {
		return voting;
	}

	public void setVoting(Voting voting) {
		this.voting = voting;
	}

	public VotingOption getVotingOption() {
		return votingOption;
	}

	public void setVotingOption(VotingOption votingOption) {
		this.votingOption = votingOption;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(votingName).append(optionName).append(voting).append(votingOption).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Vote) {
			Vote other = (Vote) obj;
			result = new EqualsBuilder().append(other.votingName, votingName).append(other.optionName, optionName).append(other.voting, voting).append(other.votingOption, votingOption).isEquals();
		}
		return result;
	}
}
