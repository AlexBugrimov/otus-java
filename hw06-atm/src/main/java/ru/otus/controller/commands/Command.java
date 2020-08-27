package ru.otus.controller.commands;


import ru.otus.controller.ControlBlock;
import ru.otus.dispenser.DispenserForBanknotes;
import ru.otus.safe.DepositBox;

public interface Command {

    Result execute(DispenserForBanknotes dispenser,
                   ControlBlock controlBlock,
                   DepositBox depositBox);
}
