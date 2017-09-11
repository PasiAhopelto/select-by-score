package github.pasiahopelto.scorelib.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VotingOption implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer votingId;
	private String name;
	private Integer position;
	
	public Integer getVotingId() {
		return votingId;
	}
	
	public void setVotingId(Integer votingId) {
		this.votingId = votingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(votingId).append(name).append(position).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof VotingOption) {
			VotingOption other = (VotingOption) obj;
			result = new EqualsBuilder().append(other.votingId, votingId).append(other.name, name).append(other.position, position).isEquals();
		}
		return result;
	}
}