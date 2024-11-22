import com.epam.impl.bank.impl.BankImpl;

module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;

    provides com.epam.jmp.bank.Bank with BankImpl;
    exports com.epam.impl.bank.impl;
}
