package com.prospecta.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="Entries")
public class EntryEntity {
	@Id
	@NotNull
	@NotBlank(message = "Api is Mandatory")
	@Column(name = "API")
	public String api;
	@NotNull
	@Column(name ="Description")
	public String description;
	@NotNull
	@Column(name = "Auth")
	public String auth;
	@NotNull
	@Column(name="HTTPS")
	public Boolean https;
	@NotNull
	@Column(name = "Cors")
	public String cors;
	@NotNull
	@Column(name="Link",unique = true)
	public String link;
	@NotNull
	@Column(name ="Category")
	public String category;

}
