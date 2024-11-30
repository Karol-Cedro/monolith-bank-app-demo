package com.kcedro.monolithbankapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;

	private String ownerName;

	private String ownerSurname;

	private Long accountId;

	private String cardNumber;

	private int totalLimit;

	private int availableAmount;
	
}
