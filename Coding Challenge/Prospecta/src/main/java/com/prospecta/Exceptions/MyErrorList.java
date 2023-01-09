package com.prospecta.Exceptions;

import java.time.LocalDateTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyErrorList {
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime time;
	private String Message;
	private String Details;
}
