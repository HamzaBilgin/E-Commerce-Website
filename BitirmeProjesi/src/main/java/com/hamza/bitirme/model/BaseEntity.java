package com.hamza.bitirme.model;



import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BaseEntity {

	@Getter @Setter  Boolean deleted = false;

	@Getter @Setter private Boolean disabled = false;

	/*@Column(name = "VERSION")
	@Version
	@Setter @Getter private int version;*/

	/**
	 * Genel yaklaşımımız Class Member lar üstünden gitmektir.
	 * Lakin istenirse Getter lar üstüne de gerekli Annotationlar
	 * Yerleştirilerek gidilebilir
	 * @return
	 */
	
	
	 	
	
	
}
