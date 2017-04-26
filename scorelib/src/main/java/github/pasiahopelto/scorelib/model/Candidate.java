package github.pasiahopelto.scorelib.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Candidate {

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Candidate) {
			Candidate other = (Candidate) obj;
			result = new EqualsBuilder().append(other.id, id).append(other.name, name).isEquals();
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(name).toHashCode();
	}
}
