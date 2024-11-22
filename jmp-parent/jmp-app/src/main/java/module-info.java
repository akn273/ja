module jmp.app {
    requires transitive jmp.service.api;
    requires jmp.cloud.bank.impl;
    requires jmp.bank.api;
    uses  com.epam.jmp.bank.Bank;
    requires jmp.cloud.service.impl;
    requires jmp.dto;
}