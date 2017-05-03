package github.pasiahopelto.scorelib.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Vote {
	
	private String votingName;
	private String candidateName;
	private String optionName;
	
	public String getVotingName() {
		return votingName;
	}
	
	public void setVotingName(String votingName) {
		this.votingName = votingName;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(votingName).append(candidateName).append(optionName).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Vote) {
			Vote other = (Vote) obj;
			result = new EqualsBuilder().append(other.votingName, votingName).append(other.candidateName, candidateName).append(other.optionName, optionName).isEquals();
		}
		return result;
	}
}
