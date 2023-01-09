package com.prospecta.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry {

	@JsonProperty("API")
	public String api;
	@JsonProperty("Description")
	public String description;
	@JsonProperty("Auth")
	public String auth;
	@JsonProperty("HTTPS")
	public Boolean https;
	@JsonProperty("Cors")
	public String cors;
	@JsonProperty("Link")
	public String link;
	@JsonProperty("Category")
	public String category;

}
