package com.company.demo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BestEverService {

    private final IdValidator idValidator;
    private final ContactService contactService;

    public Integer getBestNumber() {
        var email = contactService.getEmail();
        var number = generateHashNumber(email);
        validate(number);
        return number;
    }

    private void validate(int number) {
        idValidator.validate(number);
    }

    private int generateHashNumber(String email) {
        return ("someToken_" + email).hashCode();
    }

    public void justDoIt() {
        System.out.println("Just did it.");
    }

}
