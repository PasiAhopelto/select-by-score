package github.pasiahopelto.scorelib.reader;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ParsedLine implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;
	private List<String> values;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(type).append(values).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof ParsedLine) {
			ParsedLine other = (ParsedLine) obj;
			result = new EqualsBuilder().append(other.type, type).append(other.values, values).isEquals();
		}
		return result;
	}

	@Override
	public String toString() {
		return type + ": " + values;
	}
}
