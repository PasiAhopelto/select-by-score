package github.pasiahopelto.scorelib.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Voting implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private List<Votes> votes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Votes> getVotes() {
		return votes;
	}

	public void setVotes(List<Votes> votes) {
		this.votes = votes;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(votes).append(description).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Voting) {
			Voting other = (Voting) obj;
			result = new EqualsBuilder().append(other.name, name).append(other.votes, votes).append(other.description, description).isEquals();
		}
		return result;
	}
}
