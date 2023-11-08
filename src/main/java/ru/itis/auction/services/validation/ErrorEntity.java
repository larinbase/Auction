package ru.itis.auction.services.validation;

import lombok.Getter;

@Getter
// @JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorEntity {
    // Общие ошибки
    INVALID_REQUEST(400, "Неверный запрос"),
    INVALID_TOKEN(403, "Ошибка авторизации"),
    FORBIDDEN(403, "Доступ запрещен"),
    NOT_FOUND(404, "Не найдено"),
    INVALID_NAME(450, "Некорректное имя"),
    NAME_ALREADY_TAKEN(453, "Имя уже занято"),
    INVALID_PASSWORD(450, "Некорректный пароль"),

    // Вход
    USER_NOT_FOUND(404,"Пользователь не найден"),
    INCORRECT_PASSWORD(460, "Неверный пароль"),

    // Регистрация
    PASSWORD_TOO_SIMPLE(462,"Пароль слишком простой"),

    //  Лоты
    LOT_NOT_FOUND(404, "Лот с таким именем не найден"),
    INVALID_LOT_NAME(450, "Некорректное имя лота"),
    INVALID_LOT_DESCRIPTION(450, "Некорректное описание лота"),
    INVALID_LOT_ARTIKUL(450, "Некорректный артикул лота"),

    // Ставки
    INVALID_BET_AMOUNT(450, "Некорректное имя лота");

    int status;
    String message;

    ErrorEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }
}