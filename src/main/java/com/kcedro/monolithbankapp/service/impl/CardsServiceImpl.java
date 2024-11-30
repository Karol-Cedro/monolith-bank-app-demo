package com.kcedro.monolithbankapp.service.impl;

import com.kcedro.monolithbankapp.dto.CardDebtDto;
import com.kcedro.monolithbankapp.dto.CardDto;
import com.kcedro.monolithbankapp.entity.Card;
import com.kcedro.monolithbankapp.exception.CardAlreadyExistsException;
import com.kcedro.monolithbankapp.exception.ResourceNotFoundException;
import com.kcedro.monolithbankapp.mapper.CardsMapper;
import com.kcedro.monolithbankapp.repository.CardsRepository;
import com.kcedro.monolithbankapp.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(CardDto cardDto) {
        Optional<Card> optionalCards = cardsRepository.findByAccountId(cardDto.getAccountId());
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with this account " + cardDto.getAccountId());
        }
        cardsRepository.save(createNewCard(cardDto));
    }

    private Card createNewCard(CardDto cardDto) {
        Card newCard = CardsMapper.mapToCards(cardDto, new Card());
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setTotalLimit(cardDto.getTotalLimit());
        newCard.setAvailableAmount(cardDto.getTotalLimit());
        return newCard;
    }

    @Override
    public void updateCardAvailableAmount(CardDebtDto cardDebtDto) {
        Card card = cardsRepository.findByAccountId(cardDebtDto.getAccountId()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "accountId", cardDebtDto.getAccountId().toString())
        );
        card.setAvailableAmount(cardDebtDto.getAmount());
        cardsRepository.save(card);
    }

    @Override
    public int getAvailableAmount(Long accountId) {
        Card card = cardsRepository.findByAccountId(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Card", "accountId", accountId.toString())
        );
        return card.getAvailableAmount();
    }

    @Override
    public int getCardLimit(Long accountId) {
        Card card = cardsRepository.findByAccountId(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Card", "accountId", accountId.toString())
        );
        return card.getTotalLimit();
    }

    @Override
    public boolean payDebt(CardDebtDto cardDebtDto) {
        Card card = cardsRepository.findByAccountId(cardDebtDto.getAccountId()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "accountId", cardDebtDto.getAccountId().toString()));
        card.setAvailableAmount(card.getAvailableAmount() + cardDebtDto.getAmount());
        cardsRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(Long cardId) {
        cardsRepository.findById(cardId).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardId", cardId.toString())
        );
        cardsRepository.deleteById(cardId);
        return true;
    }

}
