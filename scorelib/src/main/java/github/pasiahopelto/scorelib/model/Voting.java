package github.pasiahopelto.scorelib.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Voting implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Scoring { EXACT, DISTANCE };
	
	private String name;
	private String description;
	private Scoring scoring;
	private List<String> options;

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

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(description).append(options).append(scoring).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Voting) {
			Voting other = (Voting) obj;
			result = new EqualsBuilder().append(other.description, description).append(other.name, name).append(other.options, options).append(other.scoring, scoring).isEquals();
		}
		return result;
	}
}
