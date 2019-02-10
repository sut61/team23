package com.Team23.backend.Controller;

import com.Team23.backend.Entity.AcceptToUser;
import com.Team23.backend.Entity.Card;
import com.Team23.backend.Entity.Expenses;
import com.Team23.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CardController {
    @Autowired
    private  final CardRepository cardRepository;
    @Autowired
    private AcceptToUserRepository acceptToUserRepository;
    @Autowired
    private RightRegistrationRepository rightRegistrationRepository;
    @Autowired
    private OfficerRepository officerRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ExpensesRepository expensesRepository;

    public CardController(CardRepository cardRepository, AcceptToUserRepository acceptToUserRepository, RightRegistrationRepository rightRegistrationRepository
            , OfficerRepository officerRepository, StatusRepository statusRepository, ExpensesRepository expensesRepository) {
        this.cardRepository = cardRepository;
        this.acceptToUserRepository = acceptToUserRepository;
        this.statusRepository = statusRepository;
        this.officerRepository = officerRepository;
        this.rightRegistrationRepository = rightRegistrationRepository;
        this.expensesRepository = expensesRepository;
    }
    @GetMapping("/Expenses")
    public Collection<Expenses> Expenses() {
        return expensesRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/Card")
    public Collection<Card> Card() {
        return cardRepository.findAll().stream().collect(Collectors.toList());
    }


    @GetMapping("/PassAcceptToUser")
    public Collection<AcceptToUser> AcceptToUser() {
        return acceptToUserRepository.findAll().stream().filter(this::Pass).collect(Collectors.toList());
           }
    private boolean Pass(AcceptToUser acceptToUser) {

        return acceptToUser.getStatus().getStatusName().equals("Pass");
    }
    @RequestMapping(value ="/deleteCard/{IdCard}")
    public  void  deleteCard(Card card, @PathVariable Long IdCard){

        cardRepository.deleteById(IdCard);

    }

    @PostMapping("/Card/add/{cardcord},{datenow},{comment},{accid},{expenses}")
    public Card newCard(Card card, @PathVariable String cardcord, @PathVariable String datenow
            , @PathVariable String comment
            , @PathVariable long accid , @PathVariable Long expenses){

        card.setCardcord(cardcord);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate dateadd = LocalDate.parse(datenow, formatter);
        card.setDate(dateadd);

        card.setComment(comment);
        AcceptToUser acceptToUser = acceptToUserRepository.findByAccId(accid);
        card.setAcceptToUser(acceptToUser);
        Expenses expenses1 = expensesRepository.findByIdExpenses(expenses);
        card.setExpenses(expenses1);







        return cardRepository.save(card);
    }
}
