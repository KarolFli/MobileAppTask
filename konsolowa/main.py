"""INF.04-02-25.01-SG - obsługa urządzeń domowych."""


class Device:
    # ************************************************
    # nazwa: display_message
    # opis: Wyświetla przekazany komunikat na standardowym wyjściu.
    # parametry: message - treść komunikatu do wyświetlenia
    # zwracany typ i opis: brak (None) - metoda nie zwraca wartości
    # autor: DO_UZUPELNIENIA_NUMER_ZDAJACEGO
    # ************************************************
    def display_message(self, message: str) -> None:
        print(message)


class WashingMachine(Device):
    def __init__(self) -> None:
        self.__program_number = 0

    def set_program(self, program_number: int) -> int:
        if 1 <= program_number <= 12:
            self.__program_number = program_number
        else:
            self.__program_number = 0
        return self.__program_number


class VacuumCleaner(Device):
    def __init__(self) -> None:
        self.__is_on = False

    def on(self) -> None:
        if not self.__is_on:
            self.__is_on = True
            self.display_message("Odkurzacz włączono")

    def off(self) -> None:
        if self.__is_on:
            self.__is_on = False
            self.display_message("Odkurzacz wyłączono")


def main() -> None:
    washing_machine = WashingMachine()
    vacuum_cleaner = VacuumCleaner()

    try:
        program_number = int(input("Podaj numer prania 1..12\n"))
    except ValueError:
        program_number = 0

    if washing_machine.set_program(program_number) != 0:
        washing_machine.display_message("Program został ustawiony")
    else:
        washing_machine.display_message("Podano niepoprawny numer programu")

    vacuum_cleaner.on()
    vacuum_cleaner.on()
    vacuum_cleaner.on()
    vacuum_cleaner.display_message("Odkurzacz wyładował się")
    vacuum_cleaner.off()


if __name__ == "__main__":
    main()
